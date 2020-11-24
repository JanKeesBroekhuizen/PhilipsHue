package com.dlvjkb.hueapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpClient client = new OkHttpClient();
        String bridgeUri = "http://localhost:8000/api/";
        String username = "newdeveloper";
        String category = "/lights";

        final String uri = bridgeUri + username + category;
        final Request allLightsRequest = new Request.Builder().url(uri).build();
        try (Response response = client.newCall(allLightsRequest).execute()){
            System.out.println(response.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}