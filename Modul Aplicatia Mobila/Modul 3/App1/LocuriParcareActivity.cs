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
    [Activity(Label = "LocuriParcareActivity")]
    public class LocuriParcareActivity : Activity
    {
        private TextView txtLocuriDisponibile;
        private MySqlConnection con;
        private bool isActivityRunning = true;
        private string username;
        private string password;

        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);
            SetContentView(Resource.Layout.LocuriParcare);

            // Extrage username-ul și parola din intent
            username = Intent.GetStringExtra("Username");
            password = Intent.GetStringExtra("Password");

            // Inițializează conexiunea la baza de date
            InitializeDatabaseConnection();

            // Obține TextView-ul pentru afișarea numărului de locuri disponibile
            txtLocuriDisponibile = FindViewById<TextView>(Resource.Id.txtLocuriDisponibile);

            // Inițializează butonul de întoarcere
            var backButton = FindViewById<ImageView>(Resource.Id.backButton);
            backButton.Click += BackButton_Click;

            // Obține și afișează numărul de locuri de parcare disponibile într-un thread separat
            Task.Run(async () =>
            {
                while (isActivityRunning)
                {
                    await Task.Delay(5000); // Verificarea disponibilității la fiecare 5 secunde
                    if (!isActivityRunning)
                        break; // Ieșirea din buclă dacă activitatea nu mai este activă
                    await GetNumarLocuriDisponibile();
                }
            });
        }

        protected override void OnDestroy()
        {
            base.OnDestroy();
            isActivityRunning = false; // Setează indicatorul de stare a activității ca inactiv
            if (con != null && con.State == System.Data.ConnectionState.Open)
                con.Close(); // Închide conexiunea la baza de date dacă este deschisă
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

        private async Task GetNumarLocuriDisponibile()
        {
            try
            {
                await con.OpenAsync();
                MySqlCommand cmd = new MySqlCommand("SELECT numar_locuri FROM locuri_parcare", con); // Selectați doar numărul de locuri de parcare
                object result = await cmd.ExecuteScalarAsync();
                RunOnUiThread(() =>
                {
                    txtLocuriDisponibile.Text = result.ToString();
                });
            }
            catch (MySqlException ex)
            {
                // Tratarea excepțiilor
                RunOnUiThread(() =>
                {
                    txtLocuriDisponibile.Text = "Eroare la conectare: " + ex.Message;
                });
            }
            finally
            {
                if (con.State == System.Data.ConnectionState.Open)
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
