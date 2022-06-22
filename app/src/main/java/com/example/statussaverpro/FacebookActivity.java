package com.example.statussaverpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.statussaverpro.databinding.ActivityFacebookBinding;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class FacebookActivity extends AppCompatActivity {

    private ActivityFacebookBinding binding2;
    private FacebookActivity fbactivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding2 = DataBindingUtil.setContentView(FacebookActivity.this,R.layout.activity_facebook);
        fbactivity = this;

        binding2.btnFacebookdwnld.setOnClickListener(v ->{

            getFacebookData();

        });

    }

    private void getFacebookData() {

        URL url;
        try {
            url = new URL(binding2.edttxtUrlFacebook.getText().toString());

            String host = url.getHost();
            if (host.contains("facebook.com")){

                new getFBUrl().execute(binding2.edttxtUrlFacebook.getText().toString());


            }else
            {
                Toast.makeText(fbactivity, "The Input URL is Invalid....!", Toast.LENGTH_SHORT).show();
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
        class getFBUrl extends AsyncTask<String, Void, Document>{

            Document fbDocs;
            @Override
            protected Document doInBackground(String... strings) {

                try {
                    fbDocs = Jsoup.connect(strings[0]).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return fbDocs;
            }

            @Override
            protected void onPostExecute(Document document) {
                String videoUrl2 = document.select("meta[property= \"og:video\"]").last().attr("content");

                if (!videoUrl2.equals("")){

                    Util.download(videoUrl2, Util.RootDirectoryFacebbok, fbactivity,"facebook"+System.currentTimeMillis() + ".mp4");

                }
            }
        }

    }
