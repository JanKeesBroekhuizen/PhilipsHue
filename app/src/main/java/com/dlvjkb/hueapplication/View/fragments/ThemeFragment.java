package com.dlvjkb.hueapplication.View.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.dlvjkb.hueapplication.ViewModel.GroupActionManager;
import com.dlvjkb.hueapplication.R;
import com.dlvjkb.hueapplication.model.groups.Group;
import com.dlvjkb.hueapplication.model.lightbulbs.LightBulb;
import com.dlvjkb.hueapplication.ViewModel.recyclerview.groups.GroupListManager;

public class ThemeFragment extends Fragment {

    private CardView btnSunsetTheme;
    private CardView btnForestTheme;
    private CardView btnStarsTheme;
    private CardView btnWorkTheme;
    private CardView btnSunLightTheme;
    private Spinner spinner;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_themes, container, false);
        btnSunsetTheme = view.findViewById(R.id.cvSunset);
        btnForestTheme = view.findViewById(R.id.cvForest);
        btnStarsTheme = view.findViewById(R.id.cvStars);
        btnSunLightTheme = view.findViewById(R.id.cvSunLight);
        btnWorkTheme = view.findViewById(R.id.cvWork);
        spinner = view.findViewById(R.id.spinnerGroups);
        ArrayAdapter<Group> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, GroupListManager.getInstance().getGroups());
        spinner.setAdapter(adapter);
        initButtons();
        return view;    }


    public void onThemeButtonClickAction(int hexvalue){
        //Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
        float[] hsvColor = new float[3];
        Color.colorToHSV(hexvalue, hsvColor);
        Group group = (Group) spinner.getSelectedItem();
        if (group != null){
            group.action.on = true;

            group.action.setHue(hsvColor[0] * 182);
            group.action.setSat(hsvColor[1] * 254);
            group.action.setBri(hsvColor[2] * 254);

            for (int i = 0; i < group.lightBulbs.size(); i++){
                LightBulb lightBulb = group.lightBulbs.get(i);
                lightBulb.state.on = true;
                lightBulb.state.hue = hsvColor[0] * 182;
                lightBulb.state.sat = hsvColor[1] * 254;
                lightBulb.state.bri = hsvColor[2] * 254;
            }

            GroupActionManager.getInstance(getContext()).setGroup(group);
        } else {
            Toast.makeText(getContext(), R.string.String_NoGroup, Toast.LENGTH_SHORT).show();
        }

    }

    public void initButtons(){
        btnSunsetTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onThemeButtonClickAction(Integer.parseInt("FCB737", 16));
            }
        });
        btnForestTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onThemeButtonClickAction(Integer.parseInt("32c137", 16));
            }
        });
        btnSunLightTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onThemeButtonClickAction(Integer.parseInt("edf902", 16));
            }
        });
        btnStarsTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onThemeButtonClickAction(Integer.parseInt("090e26", 16));
            }
        });
        btnWorkTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onThemeButtonClickAction(Integer.parseInt("99f7fc", 16));
            }
        });
    }
}
