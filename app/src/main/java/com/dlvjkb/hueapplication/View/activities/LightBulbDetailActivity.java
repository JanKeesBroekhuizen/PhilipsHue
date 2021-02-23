package com.dlvjkb.hueapplication.View.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.dlvjkb.hueapplication.ViewModel.LightBulbStateManager;
import com.dlvjkb.hueapplication.R;
import com.dlvjkb.hueapplication.model.lightbulbs.LightBulb;
import com.flask.colorpicker.ColorPickerView;

public class LightBulbDetailActivity extends AppCompatActivity {
    private LightBulb lightBulb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailactivity_lightbulb);

        lightBulb = (LightBulb) getIntent().getSerializableExtra("LightBulb");

        float[] hsvArray = new float[]{lightBulb.state.hue / 182, lightBulb.state.sat / 254, lightBulb.state.bri / 254};
        int color = Color.HSVToColor(hsvArray);

        ColorPickerView colorPickerView = findViewById(R.id.color_picker_view);
        colorPickerView.setInitialColor(color, true);
        Button button = findViewById(R.id.button);

        button.setBackgroundColor(color);

        System.out.println("Hue: " + (int) lightBulb.state.hue + " - Sat: " + (int) lightBulb.state.sat + " - Bri: " + (int) lightBulb.state.bri + " - Color: " + color);

        SwitchCompat lightSwitch = findViewById(R.id.LightSwitch);
        lightSwitch.setChecked(lightBulb.state.on);
        EditText etLightBulbName = findViewById(R.id.etLightBulbNameDetail);
        etLightBulbName.setText(lightBulb.name);
        TextView tvLightBulbInfo = findViewById(R.id.tvLightBulbInfo);
        tvLightBulbInfo.setText("Hue: " + (int) lightBulb.state.hue + " - Sat: " + (int) lightBulb.state.sat + " - Bri: " + (int) lightBulb.state.bri);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("KLEUREN", "COLOR: " + colorPickerView.getSelectedColor());
                button.setBackgroundColor(colorPickerView.getSelectedColor());

                float[] hsv = new float[3];
                Color.colorToHSV(colorPickerView.getSelectedColor(), hsv);

                lightBulb.state.hue = hsv[0] * 182;
                lightBulb.state.sat = hsv[1] * 254;
                lightBulb.state.bri = hsv[2] * 254;

                if (!etLightBulbName.getText().toString().equals(lightBulb.name)){
                    lightBulb.name = etLightBulbName.getText().toString();
                    LightBulbStateManager.getInstance(getApplicationContext()).changeName(lightBulb);
                }

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
}