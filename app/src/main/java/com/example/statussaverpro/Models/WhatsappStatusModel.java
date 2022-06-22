package com.example.statussaverpro.Models;

import android.net.Uri;

public class WhatsappStatusModel {

    private String name;
    private Uri uri;
    private String path;
    private String file;

    public WhatsappStatusModel(String name, Uri uri, String path, String file) {
        this.name = name;
        this.uri = uri;
        this.path = path;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
