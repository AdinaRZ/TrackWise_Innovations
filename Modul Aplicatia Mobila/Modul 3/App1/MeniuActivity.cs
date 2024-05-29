using Android.App;
using Android.Content;
using Android.Graphics;
using Android.OS;
using Android.Util;
using Android.Widget;
using System;

namespace App1
{
    [Activity(Label = "MeniuActivity")]
    public class MeniuActivity : Activity
    {
        private ImageView ivPozaProfil;
        private const int REQUEST_SETARI = 3;
        private Bitmap selectedImageBitmap;

        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);
            SetContentView(Resource.Layout.Meniu);

            string username = Intent.GetStringExtra("Username");
            string password = Intent.GetStringExtra("Password");

            ivPozaProfil = FindViewById<ImageView>(Resource.Id.ivPozaProfil);

            LoadSelectedImage(); // Încarcă poza de profil salvată

            

            Button btnDetaliiUtilizator = FindViewById<Button>(Resource.Id.btnDetaliiAngajat);
            btnDetaliiUtilizator.Click += (sender, e) =>
            {
                Intent detaliiIntent = new Intent(this, typeof(DetaliiUtilizatorActivity));
                detaliiIntent.PutExtra("Username", username);
                detaliiIntent.PutExtra("Password", password);
                StartActivity(detaliiIntent);
            };

            Button btnLocuriParcare = FindViewById<Button>(Resource.Id.btnLocuriParcareDisponibile);
            btnLocuriParcare.Click += (sender, e) =>
            {
                Intent locuriParcareIntent = new Intent(this, typeof(LocuriParcareActivity));
                locuriParcareIntent.PutExtra("Username", username);
                locuriParcareIntent.PutExtra("Password", password);
                StartActivity(locuriParcareIntent);
            };

            Button btnSetari = FindViewById<Button>(Resource.Id.btnSetari);
            btnSetari.Click += (sender, e) =>
            {
                Intent setariIntent = new Intent(this, typeof(SetariActivity));
                setariIntent.PutExtra("Username", username);
                setariIntent.PutExtra("Password", password);
                StartActivityForResult(setariIntent, REQUEST_SETARI);
            };

            Button btnGenerareCodQR = FindViewById<Button>(Resource.Id.btnGenerareCodQR);
            btnGenerareCodQR.Click += (sender, e) =>
            {
                Intent intent = new Intent(this, typeof(GenerareCodQRActivity));
                intent.PutExtra("Username", username);
                intent.PutExtra("Password", password);
                StartActivity(intent);
            };
        }

        protected override void OnActivityResult(int requestCode, Result resultCode, Intent data)
        {
            base.OnActivityResult(requestCode, resultCode, data);

            if (requestCode == REQUEST_SETARI && resultCode == Result.Ok)
            {
                LoadSelectedImage(); // Încarcă poza de profil salvată după revenirea din SetariActivity
            }
        }

        private void LoadSelectedImage()
        {
            var preferences = GetSharedPreferences("MyPrefs", FileCreationMode.Private);
            string selectedImageBase64 = preferences.GetString("SelectedImage", null);

            if (!string.IsNullOrEmpty(selectedImageBase64))
            {
                byte[] imageAsBytes = Base64.Decode(selectedImageBase64, Base64Flags.Default);
                selectedImageBitmap = BitmapFactory.DecodeByteArray(imageAsBytes, 0, imageAsBytes.Length);
                ivPozaProfil.SetImageBitmap(selectedImageBitmap);
            }
            else
            {
                SetDefaultImage();
            }
        }

        private void SetDefaultImage()
        {
            ivPozaProfil.SetImageResource(Resource.Drawable.poza_avatar);
        }
    }
}
