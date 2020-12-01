package com.dlvjkb.hueapplication.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorLong;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.dlvjkb.hueapplication.GroupActionManager;
import com.dlvjkb.hueapplication.R;
import com.dlvjkb.hueapplication.model.groups.Group;
import com.dlvjkb.hueapplication.model.lightbulbs.LightBulb;
import com.dlvjkb.hueapplication.recyclerview.groups.GroupListManager;

public class ThemeFragment extends Fragment {
//
//    private CardView btnSunsetTheme;
//    private CardView btnForestTheme;
//    private CardView btnStarsTheme;
//    private CardView btnWorkTheme;
//    private CardView btnSunLightTheme;
//    private Spinner spinner;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_themes, container, false);
//        btnSunsetTheme = view.findViewById(R.id.cvSunset);
//        btnForestTheme = view.findViewById(R.id.cvForest);
//        btnStarsTheme = view.findViewById(R.id.cvStars);
//        btnSunLightTheme = view.findViewById(R.id.cvSunLight);
//        btnWorkTheme = view.findViewById(R.id.cvWork);
//        spinner = view.findViewById(R.id.spinnerGroups);
//        ArrayAdapter<Group> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, GroupListManager.getInstance().getGroups());
//        spinner.setAdapter(adapter);
//        initButtons();
        return view;    }


//    public void onThemeButtonClickAction(int hexvalue){
//        //Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
//        float[] hsvColor = new float[3];
//        Color.colorToHSV(hexvalue, hsvColor);
//        Group group = (Group) spinner.getSelectedItem();
//
//        System.out.println("ONE: " + hsvColor[0]);
//        System.out.println("TWO: " + hsvColor[1]);
//        System.out.println("THREE: " + hsvColor[2]);
//        //group.action.on = true;
////        group.action.hue = (int) hsv[0] * 182;
////        group.action.sat = (int) hsv[1] * 254;
////        group.action.bri = (int) hsv[2] * 254;
//
//        int brightness = (int)hsvColor[2] * 254;
//
//        group.action.setHue((int)hsvColor[0] * 182);
//        group.action.setSat((int)hsvColor[1] * 254);
//        group.action.setBri((int)hsvColor[2] * 254);
//
//        System.out.println("HUE: " + group.action.hue);
//        System.out.println("SAT: " + group.action.sat);
//        System.out.println("BRI: " + group.action.bri);
//
//        System.out.println("FRAGMENT: " + group.toString());
//
//
////        for (int i = 0; i < group.lightBulbs.size(); i++){
////            LightBulb lightBulb = group.lightBulbs.get(i);
////            lightBulb.state.on = true;
////            lightBulb.state.hue = (int) hsv[0] * 182;
////            lightBulb.state.sat = (int) hsv[1] * 254;
////            lightBulb.state.bri = (int) hsv[2] * 254;
////        }
//
//        GroupActionManager.getInstance(getContext()).setGroup(group);
//    }
//
//    public void initButtons(){
//        btnSunsetTheme.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onThemeButtonClickAction(Integer.parseInt("FCB737", 16));
//            }
//        });
//        btnForestTheme.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onThemeButtonClickAction(Integer.parseInt("32c137", 16));
//            }
//        });
//        btnSunLightTheme.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onThemeButtonClickAction(Integer.parseInt("edf902", 16));
//            }
//        });
//        btnStarsTheme.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onThemeButtonClickAction(Integer.parseInt("090e26", 16));
//            }
//        });
//        btnWorkTheme.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onThemeButtonClickAction(Integer.parseInt("99f7fc", 16));
//            }
//        });
//    }
}
