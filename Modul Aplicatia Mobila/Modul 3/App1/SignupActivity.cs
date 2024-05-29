using Android.App;
using Android.OS;
using Android.Widget;
using System;

namespace App1
{
    [Activity(Label = "SignupActivity", MainLauncher = true)]
    public class SignupActivity : Activity
    {
        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);

            // Setarea layout-ului pentru activitatea SignupActivity
            SetContentView(Resource.Layout.signup);

            // Obținerea referinței către butonul de signup
            Button btnSignIn = FindViewById<Button>(Resource.Id.btnSignIn);

            // Adăugarea unui eveniment de click pentru butonul de signup
            btnSignIn.Click += BtnSignIn_Click;
        }

        // Metoda care este apelată la apăsarea butonului de signup
        private void BtnSignIn_Click(object sender, EventArgs e)
        {
            // Redirecționarea către activitatea MainActivity
            StartActivity(typeof(MainActivity));
        }
    }
}
