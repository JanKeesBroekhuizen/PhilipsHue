package com.dlvjkb.hueapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.dlvjkb.hueapplication.R;

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
        initButtons();
        return view;    }


    public void onThemeButtonClickAction(View view, String text){
        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
    }

    public void initButtons(){
        btnSunsetTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onThemeButtonClickAction(v, "SUNSET");
            }
        });
        btnForestTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onThemeButtonClickAction(v, "FOREST");
            }
        });
        btnSunLightTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onThemeButtonClickAction(v, "SUNLIGHT");
            }
        });
        btnStarsTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onThemeButtonClickAction(v,"STARS");
            }
        });
        btnWorkTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onThemeButtonClickAction(v,"WORK");
            }
        });
    }
}
