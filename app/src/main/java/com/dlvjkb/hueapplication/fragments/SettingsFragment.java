package com.dlvjkb.hueapplication.fragments;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dlvjkb.hueapplication.HueLightBulbConnection;
import com.dlvjkb.hueapplication.LightBulbStateManager;
import com.dlvjkb.hueapplication.MainScreenActivity;
import com.dlvjkb.hueapplication.R;
import com.dlvjkb.hueapplication.SplashScreenActivity;

public class SettingsFragment extends Fragment {

    private EditText etIpAddress;
    private EditText etPortNumber;
    private Button btnSave;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        etIpAddress = view.findViewById(R.id.etBridgeIPAdress);
        etPortNumber = view.findViewById(R.id.etBridgePortNumber);
        btnSave = view.findViewById(R.id.btnSaveAddress);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonSaveClick();
            }
        });

        loadSharedPreferences();

        return view;
    }

    public void onButtonSaveClick(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("settings",0);
        SharedPreferences.Editor sedt = sharedPreferences.edit();
        sedt.putString("ipAddress", etIpAddress.getText().toString());
        sedt.putString("portNumber", etPortNumber.getText().toString());
        sedt.commit();

        LightsFragment.ipAddress = etIpAddress.getText().toString();
        LightsFragment.portNumber = etPortNumber.getText().toString();
        Toast.makeText(getContext(),"PLEASE RESTART",Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getContext(), SplashScreenActivity.class);
        getActivity().finish();
        startActivity(intent);
    }

    public void loadSharedPreferences(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("settings",0);
        etIpAddress.setText(sharedPreferences.getString("ipAddress",""));
        etPortNumber.setText(sharedPreferences.getString("portNumber",""));
    }
}
