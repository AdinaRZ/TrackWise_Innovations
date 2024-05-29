using Android.App;
using Android.Content;
using Android.OS;
using Android.Widget;
using MySqlConnector;
using System;
using System.Text;
using System.Threading.Tasks;

namespace App1
{
    [Activity(Label = "SchimbareParolaActivity")]
    public class SchimbareParolaActivity : Activity
    {
        private string username;
        private string password;
        private MySqlConnection con;

        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);
            SetContentView(Resource.Layout.SchimbareParola);

            // Extrage Username și Password din Intent
            username = Intent.GetStringExtra("Username");
            password = Intent.GetStringExtra("Password");

            // Inițializează conexiunea la baza de date
            InitializeDatabaseConnection();

            var schimbaParolaButton = FindViewById<Button>(Resource.Id.btnSchimbaParola);
            schimbaParolaButton.Click += SchimbaParolaButton_Click;

            var backButton = FindViewById<ImageView>(Resource.Id.backButton);
            backButton.Click += BackButton_Click;
        }

        private void InitializeDatabaseConnection()
        {
            // Construiește șirul de conexiune folosind MySqlConnectionStringBuilder
            StringBuilder connBuilder = new StringBuilder();
            connBuilder.Append("Server=35.197.71.26;");
            connBuilder.Append("Uid=balet;");
            connBuilder.Append("Pwd=balet;");
            connBuilder.Append("Database=ip;");
            connBuilder.Append("Port=3306;");
            connBuilder.Append("Connect Timeout=60;");

            // Creează obiectul MySqlConnection utilizând șirul de conexiune
            con = new MySqlConnection(connBuilder.ToString());
        }

        private async void SchimbaParolaButton_Click(object sender, EventArgs e)
        {
            EditText editTextParolaNoua = FindViewById<EditText>(Resource.Id.editTextParolaNoua);
            string parolaNoua = editTextParolaNoua.Text;

            try
            {
                bool success = await UpdateParolaAsync(parolaNoua);

                if (success)
                {
                    Toast.MakeText(this, "Parola a fost schimbată cu succes!", ToastLength.Short).Show();
                    Intent intent = new Intent(this, typeof(SignupActivity));
                    StartActivity(intent);
                }
                else
                {
                    Toast.MakeText(this, "Eroare la schimbarea parolei!", ToastLength.Short).Show();
                }
            }
            catch (Exception ex)
            {
                Toast.MakeText(this, "Eroare: " + ex.Message, ToastLength.Short).Show();
            }
        }

        private async Task<bool> UpdateParolaAsync(string parolaNoua)
        {
            try
            {
                await con.OpenAsync();

                MySqlCommand cmd = new MySqlCommand("UPDATE utilizatori SET password = @ParolaNoua WHERE email = @username AND password = @password", con);
                cmd.Parameters.AddWithValue("@ParolaNoua", parolaNoua);
                cmd.Parameters.AddWithValue("@username", username);
                cmd.Parameters.AddWithValue("@password", password);

                int rowsAffected = await cmd.ExecuteNonQueryAsync();
                return rowsAffected > 0;
            }
            catch (Exception ex)
            {
                // Tratarea excepțiilor
                return false;
            }
            finally
            {
                if (con != null && con.State == System.Data.ConnectionState.Open)
                    con.Close();
            }
        }

        private void BackButton_Click(object sender, EventArgs e)
        {
            Intent intent = new Intent(this, typeof(MeniuActivity));
            intent.PutExtra("Username", username);
            intent.PutExtra("Password", password);
            StartActivity(intent);
        }
    }
}
