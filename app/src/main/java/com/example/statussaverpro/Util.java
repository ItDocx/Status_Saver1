package com.example.statussaverpro;

import android.os.Environment;

import java.io.File;

public class Util {

    public static File rootDirectoryWhatsapp = new
            File(Environment.getExternalStorageDirectory()+"/Download/MyStatusSaver/Whatsapp");
    public static void createFileFolder(){

        if (!rootDirectoryWhatsapp.exists()){
            rootDirectoryWhatsapp.mkdirs();
        }

    }
}
