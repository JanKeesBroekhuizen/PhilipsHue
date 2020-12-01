package com.dlvjkb.hueapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.dlvjkb.hueapplication.fragments.LightsFragment;
import com.dlvjkb.hueapplication.model.lightbulbs.LightBulb;
import com.flask.colorpicker.ColorPickerView;

import org.json.JSONException;
import org.json.JSONObject;

public class LightBulbDetailActivity extends AppCompatActivity {
    private int currentColor = 0;
    private LightBulb lightBulb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailactivity_lightbulb);

        lightBulb = (LightBulb) getIntent().getSerializableExtra("LightBulb");
        //int position = getIntent().getIntExtra("position", 1);

        ColorPickerView colorPickerView = findViewById(R.id.color_picker_view);
        Button button = findViewById(R.id.button);
        SwitchCompat lightSwitch = findViewById(R.id.LightSwitch);
        lightSwitch.setChecked(lightBulb.state.on);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("KLEUREN", "COLOR: " + colorPickerView.getSelectedColor());
                button.setBackgroundColor(colorPickerView.getSelectedColor());
                currentColor = colorPickerView.getSelectedColor();
                String hexColor = String.format("#%06X", (0xFFFFFF & colorPickerView.getSelectedColor()));

                float[] hsv = new float[3];
                Color.colorToHSV(colorPickerView.getSelectedColor(), hsv);

                lightBulb.state.hue = (int) hsv[0] * 182;
                lightBulb.state.sat = (int) hsv[1] * 254;
                lightBulb.state.bri = (int) hsv[2] * 254;
                LightBulbStateManager.getInstance(getApplicationContext()).setLightBulb(lightBulb);
            }
        });

        lightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                lightBulb.state.on = isChecked;
                button.setEnabled(isChecked);
                LightBulbStateManager.getInstance(getApplicationContext()).setLightBulb(lightBulb);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LightBulbStateManager.getInstance(getApplicationContext()).setLightBulb(lightBulb);
    }}
