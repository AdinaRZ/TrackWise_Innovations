using Android.App;
using Android.Content;
using Android.OS;
using Android.Widget;
using MySqlConnector;
using System;
using System.Text;

namespace App1
{
    [Activity(Label = "Aplicatie Mobile")]
    public class MainActivity : Activity
    {
        private EditText etUsername;
        private EditText etPassword;
        private Button btnInsert;
        private TextView txtSysLog;
        private MySqlConnection mySqlConnection;

        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);
            SetContentView(Resource.Layout.Main);

            etUsername = FindViewById<EditText>(Resource.Id.XetUsername);
            etPassword = FindViewById<EditText>(Resource.Id.XetPassword);
            btnInsert = FindViewById<Button>(Resource.Id.XbtnLogin);
            txtSysLog = FindViewById<TextView>(Resource.Id.XtxtSysLog);

            btnInsert.Click += BtnInsert_Click;
        }

        protected override void OnPause()
        {
            base.OnPause();
            if (mySqlConnection != null && mySqlConnection.State == System.Data.ConnectionState.Open)
            {
                mySqlConnection.Close();
            }
        }

        public void BtnInsert_Click(object sender, EventArgs e)
        {
            StringBuilder connBuilder = new StringBuilder();
            //connBuilder.Append("Server=35.197.71.26;");
            connBuilder.Append("Server=35.197.71.26;");
            connBuilder.Append("Uid=balet;");
            connBuilder.Append("Pwd=balet;");
            connBuilder.Append("Database=ip;");
            connBuilder.Append("Port=3306;");
            //connBuilder.Append("Charset=utf-8;");
            connBuilder.Append("Connect Timeout=60;"); // Increased timeout

            mySqlConnection = new MySqlConnection(connBuilder.ToString());

            try
            {
                txtSysLog.Text = "Se încearcă conectarea la baza de date...";
                mySqlConnection.Open();

                txtSysLog.Text = "Conectat la baza de date!";

                string query = "SELECT COUNT(*) FROM utilizatori WHERE email = @nume AND password = @prenume";
                MySqlCommand cmd = new MySqlCommand(query, mySqlConnection);
                cmd.Parameters.AddWithValue("@nume", etUsername.Text);
                cmd.Parameters.AddWithValue("@prenume", etPassword.Text);

                object result = cmd.ExecuteScalar();
                int count = Convert.ToInt32(result);

                if (count > 0)
                {
                    Intent meniuIntent = new Intent(this, typeof(MeniuActivity));
                    meniuIntent.PutExtra("Username", etUsername.Text);
                    meniuIntent.PutExtra("Password", etPassword.Text);
                    StartActivity(meniuIntent);
                }
                else
                {
                    txtSysLog.Text = "Utilizatorul sau parola incorecte.";
                }
            }
            catch (MySqlException ex)
            {
                txtSysLog.Text = "Eroare la conectare: " + ex.Message;
                Console.WriteLine("Eroare la conectare: " + ex.Message);
            }
            catch (Exception ex)
            {
                txtSysLog.Text = "Eroare: " + ex.Message;
                Console.WriteLine("Eroare: " + ex.Message);
            }
        }
    }
}
