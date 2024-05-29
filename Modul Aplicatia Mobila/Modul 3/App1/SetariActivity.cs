using Android.App;
using Android.Content;
using Android.Graphics;
using Android.OS;
using Android.Widget;
using System;
using System.IO;

namespace App1
{
    [Activity(Label = "SetariActivity")]
    public class SetariActivity : Activity
    {
        private static int SELECT_PICTURE = 1;
        private string username;
        private string password;

        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);
            SetContentView(Resource.Layout.Setari);

            var backButton = FindViewById<ImageView>(Resource.Id.backButton);
            backButton.Click += BackButton_Click;

            Button logoutButton = FindViewById<Button>(Resource.Id.btnDelogare);
            logoutButton.Click += LogoutButton_Click;

            Button schimbaPozaButton = FindViewById<Button>(Resource.Id.btnSchimbarePozaProfil);
            schimbaPozaButton.Click += SchimbaPozaButton_Click;

            Button btnSchimbareParola = FindViewById<Button>(Resource.Id.btnSchimbareParola);
            btnSchimbareParola.Click += BtnSchimbareParola_Click;

            username = Intent.GetStringExtra("Username");
            password = Intent.GetStringExtra("Password");

        }

        private void LogoutButton_Click(object sender, EventArgs e)
        {
            Intent intent = new Intent(this, typeof(SignupActivity));
            StartActivity(intent);
        }

        private void BackButton_Click(object sender, EventArgs e)
        {
            Intent intent = new Intent(this, typeof(MeniuActivity));
            intent.PutExtra("Username", username);
            intent.PutExtra("Password", password);
            StartActivity(intent);
        }

        private void SchimbaPozaButton_Click(object sender, EventArgs e)
        {
            Intent intent = new Intent(Intent.ActionGetContent);
            intent.SetType("image/*");
            StartActivityForResult(Intent.CreateChooser(intent, "Select Picture"), SELECT_PICTURE);
        }

        private void BtnSchimbareParola_Click(object sender, EventArgs e)
        {
            Intent intent = new Intent(this, typeof(SchimbareParolaActivity));
            // Transmitere username și password către SchimbareParolaActivity
            intent.PutExtra("Username", username);
            intent.PutExtra("Password", password);
            StartActivity(intent);
        }

        protected override void OnActivityResult(int requestCode, Result resultCode, Intent data)
        {
            base.OnActivityResult(requestCode, resultCode, data);

            if (resultCode == Result.Ok)
            {
                if (requestCode == SELECT_PICTURE)
                {
                    Android.Net.Uri selectedImageUri = data.Data;

                    try
                    {
                        var inputStream = ContentResolver.OpenInputStream(selectedImageUri);
                        if (inputStream != null)
                        {
                            Bitmap bitmap = BitmapFactory.DecodeStream(inputStream);
                            inputStream.Close();

                            MemoryStream stream = new MemoryStream();
                            bitmap.Compress(Bitmap.CompressFormat.Png, 100, stream);
                            byte[] byteArray = stream.ToArray();

                            var editor = GetSharedPreferences("MyPrefs", FileCreationMode.Private).Edit();
                            editor.PutString("SelectedImage", Convert.ToBase64String(byteArray));
                            editor.Apply();

                            Intent intent = new Intent();
                            intent.PutExtra("SelectedImage", byteArray);
                            SetResult(Result.Ok, intent);
                            Finish();
                        }
                        else
                        {
                            Toast.MakeText(this, "Imaginea selectată nu mai este disponibilă.", ToastLength.Short).Show();
                        }
                    }
                    catch (Java.IO.IOException ex)
                    {
                        ex.PrintStackTrace();
                    }
                }
            }
        }
    }
}       