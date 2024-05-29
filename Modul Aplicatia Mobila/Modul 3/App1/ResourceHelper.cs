using Android.Content;
using Android.Database;
using Android.Net;
using Android.Provider;

namespace App1
{
    public static class ResourceHelper
    {
        public static int GetDrawableResourceIdFromUri(Uri uri, ContentResolver contentResolver)
        {
            string[] projection = { MediaStore.Images.Media.InterfaceConsts.Id };
            ICursor cursor = contentResolver.Query(uri, projection, null, null, null);
            int columnIndex = cursor.GetColumnIndexOrThrow(MediaStore.Images.Media.InterfaceConsts.Id);
            cursor.MoveToFirst();
            int imageId = cursor.GetInt(columnIndex);
            cursor.Close();
            return imageId;
        }
    }
}
