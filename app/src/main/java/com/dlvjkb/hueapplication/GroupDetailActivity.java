package com.dlvjkb.hueapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.dlvjkb.hueapplication.model.groups.Group;
import com.dlvjkb.hueapplication.model.lightbulbs.LightBulb;
import com.dlvjkb.hueapplication.recyclerview.groups.GroupListManager;

import static java.security.AccessController.getContext;


public class GroupDetailActivity extends AppCompatActivity {

    private CardView btnSunsetTheme;
    private CardView btnForestTheme;
    private CardView btnStarsTheme;
    private CardView btnWorkTheme;
    private CardView btnSunLightTheme;
//    private Spinner spinner;
    private Group group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailactivity_group);

        group = (Group) getIntent().getSerializableExtra("Group");


        btnSunsetTheme = findViewById(R.id.cvSunset);
        btnForestTheme = findViewById(R.id.cvForest);
        btnStarsTheme = findViewById(R.id.cvStars);
        btnSunLightTheme = findViewById(R.id.cvSunLight);
        btnWorkTheme = findViewById(R.id.cvWork);
//        spinner = findViewById(R.id.spinnerGroups);
//        ArrayAdapter<Group> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, GroupListManager.getInstance().getGroups());
//        spinner.setAdapter(adapter);
        initButtons();
    }

    public void onThemeButtonClickAction(int hexvalue){
        //Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
        float[] hsvColor = new float[3];
        Color.colorToHSV(hexvalue, hsvColor);

        System.out.println("ONE: " + hsvColor[0]);
        System.out.println("TWO: " + hsvColor[1]);
        System.out.println("THREE: " + hsvColor[2]);
        //group.action.on = true;
//        group.action.hue = (int) hsv[0] * 182;
//        group.action.sat = (int) hsv[1] * 254;
//        group.action.bri = (int) hsv[2] * 254;

        int brightness = (int)hsvColor[2] * 254;

        group.action.setHue((int)hsvColor[0] * 182);
        group.action.setSat((int)hsvColor[1] * 254);
        group.action.setBri((int)hsvColor[2] * 254);

        System.out.println("HUE: " + group.action.hue);
        System.out.println("SAT: " + group.action.sat);
        System.out.println("BRI: " + group.action.bri);

        System.out.println("FRAGMENT: " + group.toString());


//        for (int i = 0; i < group.lightBulbs.size(); i++){
//            LightBulb lightBulb = group.lightBulbs.get(i);
//            lightBulb.state.on = true;
//            lightBulb.state.hue = (int) hsv[0] * 182;
//            lightBulb.state.sat = (int) hsv[1] * 254;
//            lightBulb.state.bri = (int) hsv[2] * 254;
//        }

        GroupActionManager.getInstance(getApplicationContext()).setGroup(group);
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
