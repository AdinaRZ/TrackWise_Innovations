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
    [Activity(Label = "DetaliiUtilizatorActivity")]
    public class DetaliiUtilizatorActivity : Activity
    {
        private string username;
        private string password;
        private TextView txtNume;
        private TextView txtPrenume;
        private TextView txtNumarInmatriculare;
        private TextView txtDepartament;
        private TextView txtRol;
        private TextView txtBirou;
        private TextView txtEtaj;
        private TextView txtPozitieBirou;
        private ImageView backButton;

        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);
            SetContentView(Resource.Layout.Detalii);

            username = Intent.GetStringExtra("Username");
            password = Intent.GetStringExtra("Password");

            txtNume = FindViewById<TextView>(Resource.Id.txtNume);
            txtPrenume = FindViewById<TextView>(Resource.Id.txtPrenume);
            txtNumarInmatriculare = FindViewById<TextView>(Resource.Id.txtNumarInmatriculare);
            txtDepartament = FindViewById<TextView>(Resource.Id.txtDepartament);
            txtRol = FindViewById<TextView>(Resource.Id.txtRol);
            txtBirou = FindViewById<TextView>(Resource.Id.txtBirou);
            txtEtaj = FindViewById<TextView>(Resource.Id.txtEtaj);
            txtPozitieBirou = FindViewById<TextView>(Resource.Id.txtPozitieBirou);
            backButton = FindViewById<ImageView>(Resource.Id.backButton);

            backButton.Click += BackButton_click;

            Task.Run(GetAndDisplayUserData);
        }

        private void BackButton_click(object sender, EventArgs e)
        {
            Intent intent = new Intent(this, typeof(MeniuActivity));
            intent.PutExtra("Username", username);
            intent.PutExtra("Password", password);
            StartActivity(intent);
        }

        private async Task GetAndDisplayUserData()
        {
            MySqlConnection mySqlConnection = null;
            try
            {
                StringBuilder connBuilder = new StringBuilder();
                connBuilder.Append("Server=35.197.71.26;");
                connBuilder.Append("Uid=balet;");
                connBuilder.Append("Pwd=balet;");
                connBuilder.Append("Database=ip;");
                connBuilder.Append("Port=3306;");
                connBuilder.Append("Connect Timeout=60;");

                mySqlConnection = new MySqlConnection(connBuilder.ToString());
                await mySqlConnection.OpenAsync();

                string query = "SELECT nume, prenume, nr_inmatriculare, departament, role, birou, etaj, pozitie_birou FROM utilizatori WHERE email = @username AND password = @password";
                using MySqlCommand cmd = new MySqlCommand(query, mySqlConnection);
                cmd.Parameters.AddWithValue("@username", username);
                cmd.Parameters.AddWithValue("@password", password);

                using (var reader = await cmd.ExecuteReaderAsync())
                {
                    if (await reader.ReadAsync())
                    {
                        string nume = reader["nume"].ToString();
                        string prenume = reader["prenume"].ToString();
                        string nrInmatriculare = reader["nr_inmatriculare"].ToString();
                        string departament = reader["departament"].ToString();
                        string rol = reader["role"].ToString();
                        string birou = reader["birou"].ToString();
                        string etaj = reader["etaj"].ToString();
                        string pozitieBirou = reader["pozitie_birou"].ToString();

                        RunOnUiThread(() =>
                        {
                            txtNume.Text = "Nume: " + nume;
                            txtPrenume.Text = "Prenume: " + prenume;
                            txtNumarInmatriculare.Text = "Numar Inmatriculare: " + nrInmatriculare;
                            txtDepartament.Text = "Departament: " + departament;
                            txtRol.Text = "Rol: " + rol;
                            txtBirou.Text = "Birou: " + birou;
                            txtEtaj.Text = "Etaj: " + etaj;
                            txtPozitieBirou.Text = "Pozitie Birou: " + pozitieBirou;
                        });
                    }
                }
            }
            catch (MySqlException ex)
            {
                RunOnUiThread(() => Toast.MakeText(this, "Eroare la conectare: " + ex.Message, ToastLength.Long).Show());
                Console.WriteLine("Eroare MySQL: " + ex.Message);
            }
            catch (Exception ex)
            {
                RunOnUiThread(() => Toast.MakeText(this, "Eroare: " + ex.Message, ToastLength.Long).Show());
                Console.WriteLine("Eroare: " + ex.Message);
            }
            finally
            {
                if (mySqlConnection != null && mySqlConnection.State == System.Data.ConnectionState.Open)
                {
                    await mySqlConnection.CloseAsync();
                }
            }
        }

        

    }
}
