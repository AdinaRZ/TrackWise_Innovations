using Android.App;
using Android.Content;
using Android.Graphics;
using Android.OS;
using Android.Widget;
using Javax.Crypto;
using Javax.Crypto.Spec;
using MySqlConnector;
using System;
using System.Text;
using System.Threading.Tasks;
using ZXing;
using ZXing.Mobile;
using ZXing.QrCode;

namespace App1
{
    [Activity(Label = "GenerareCodQRActivity")]
    public class GenerareCodQRActivity : Activity
    {
        private ImageView imageViewQR;
        private string username;
        private string password;
        private MySqlConnection con;

        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);
            SetContentView(Resource.Layout.GenerareQR);

            imageViewQR = FindViewById<ImageView>(Resource.Id.imageViewQR);
            username = Intent.GetStringExtra("Username");
            password = Intent.GetStringExtra("Password");

            // Inițializare conexiune la baza de date
            InitializeDatabaseConnection();

            var backButton = FindViewById<ImageView>(Resource.Id.backButton);
            backButton.Click += BackButton_Click;

            Button btnRegenerareCodQR = FindViewById<Button>(Resource.Id.btnRegenerareCodQR);
            btnRegenerareCodQR.Click += BtnRegenerareCodQR_Click;

            // Generare și afișare cod QR inițial
            GenerateAndDisplayEncryptedQRCodeAsync();
        }

        protected override void OnDestroy()
        {
            base.OnDestroy();
            if (con != null && con.State == System.Data.ConnectionState.Open)
            {
                con.Close();
                con.Dispose();
            }
        }

        private void InitializeDatabaseConnection()
        {
            StringBuilder connBuilder = new StringBuilder();
            connBuilder.Append("Server=35.197.71.26;");
            connBuilder.Append("Uid=balet;");
            connBuilder.Append("Pwd=balet;");
            connBuilder.Append("Database=ip;");
            connBuilder.Append("Port=3306;");
            connBuilder.Append("Connect Timeout=60;");

            con = new MySqlConnection(connBuilder.ToString());
        }


        private async Task GenerateAndDisplayEncryptedQRCodeAsync()
        {
            try
            {
                // Obține data și ora curentă
                DateTime currentTime = DateTime.Now;
                string currentDateAndTime = currentTime.ToString();

                // Obține CNP-ul
                string cnp = await GetEmployeeCNPAsync();

                // Verifică dacă CNP-ul este valid
                if (!string.IsNullOrEmpty(cnp))
                {
                    // Concatenează data și ora la CNP
                    string dataToEncrypt = $"{cnp}-{currentDateAndTime}";
                    Console.WriteLine(currentTime.ToString() + "\n");
                    Console.WriteLine(dataToEncrypt);
                    // Criptează informațiile
                    string encryptedData = Encrypt(dataToEncrypt);

                    // Generează codul QR și afișează-l
                    Bitmap bitmap = GenerateQRCode(encryptedData);
                    imageViewQR.SetImageBitmap(bitmap);
                }
            }
            catch (Exception ex)
            {
                // Tratarea excepțiilor
                // Înregistrarea în jurnal, afișarea într-un mesaj elegant, etc.
            }
            finally
            {
                if (con != null && con.State == System.Data.ConnectionState.Open)
                    con.Close();
            }
        }


        private async Task<string> GetEmployeeCNPAsync()
        {
            try
            {
                await con.OpenAsync();
                MySqlCommand cmd = new MySqlCommand("SELECT cnp FROM utilizatori WHERE email = @username AND password = @password", con);
                cmd.Parameters.AddWithValue("@nume", username);
                cmd.Parameters.AddWithValue("@prenume", password);
                object result = await cmd.ExecuteScalarAsync();
                return result != null ? result.ToString() : null;
            }
            catch (MySqlException ex)
            {
                // Tratarea excepțiilor
                // Înregistrarea în jurnal, afișarea într-un mesaj elegant, etc.
                return "0123456789"; // Valoare implicită criptată
            }
        }

        private Bitmap GenerateQRCode(string data)
        {
            var writer = new BarcodeWriter
            {
                Format = BarcodeFormat.QR_CODE,
                Options = new QrCodeEncodingOptions
                {
                    Height = 400,
                    Width = 400
                }
            };
            return writer.Write(data);
        }

        private string Encrypt(string plainText)
        {
            try
            {
                byte[] key = Encoding.UTF8.GetBytes("CheiaSecreta12345");
                byte[] iv = Encoding.UTF8.GetBytes("IVSecret12345678");
                Cipher cipher = Cipher.GetInstance("AES/CBC/PKCS5Padding");
                SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
                IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
                cipher.Init(CipherMode.EncryptMode, secretKeySpec, ivParameterSpec);
                byte[] encrypted = cipher.DoFinal(Encoding.UTF8.GetBytes(plainText));
                return BitConverter.ToString(encrypted).Replace("-", "");
            }
            catch (System.Exception ex)
            {
                // Tratarea excepțiilor
                // Înregistrarea în jurnal, afișarea într-un mesaj elegant, etc.
                return "Error: " + ex.Message;
            }
        }

        private void BackButton_Click(object sender, EventArgs e)
        {
            Intent intent = new Intent(this, typeof(MeniuActivity));

            // Adaugă numele de utilizator și parola ca extrasuri în Intent
            intent.PutExtra("Username", username);
            intent.PutExtra("Password", password);

            // Pornirea activității GenerareCodQRActivity
            StartActivity(intent);
        }

        private void BtnRegenerareCodQR_Click(object sender, EventArgs e)
        {
            GenerateAndDisplayEncryptedQRCodeAsync();
        }
    }
}
