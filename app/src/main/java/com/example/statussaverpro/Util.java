package com.example.statussaverpro;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import com.bumptech.glide.request.Request;

import java.io.File;

public class Util {

    public static String RootDirectoryFacebbok = "/FB Story Saver/";


    public static File rootDirectoryWhatsapp = new
            File(Environment.getExternalStorageDirectory()+"/My Status Saver/");
    public static void createFileFolder(){

        if (!rootDirectoryWhatsapp.exists()){
            rootDirectoryWhatsapp.mkdirs();
        }
    }

    public static void download (String downloadPath, String destinationPath, Context context, String filename){

        Toast.makeText(context, "Starting Download", Toast.LENGTH_SHORT).show();

        Uri uri = Uri.parse(downloadPath);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle(filename);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, destinationPath + filename);
        ((DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE)).enqueue(request);


    }

}
