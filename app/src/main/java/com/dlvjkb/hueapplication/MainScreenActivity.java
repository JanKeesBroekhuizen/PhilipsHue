package com.dlvjkb.hueapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HueEmulatorConnection connection = new HueEmulatorConnection(getApplicationContext());
        ArrayList<LightBulb> lightBulbs = connection.getLightBulbs();
        for (int i = 0; i < lightBulbs.size(); i++){
            lightBulbs.get(i).toString();
        }
    }
}