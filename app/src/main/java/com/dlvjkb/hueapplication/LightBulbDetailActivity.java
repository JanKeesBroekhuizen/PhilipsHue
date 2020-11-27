package com.dlvjkb.hueapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.dlvjkb.hueapplication.model.LightBulb;
import com.flask.colorpicker.ColorPickerView;

import org.json.JSONException;
import org.json.JSONObject;

public class LightBulbDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailactivity_lightbulb);

        LightBulb lightBulb = (LightBulb) getIntent().getSerializableExtra("LightBulb");
        int position = getIntent().getIntExtra("position", 1);

        ColorPickerView colorPickerView = findViewById(R.id.color_picker_view);
        Button button = findViewById(R.id.button);
        Switch lightSwitch = findViewById(R.id.LightSwitch);
        lightSwitch.setChecked(true); //TODO get real switch state
        LightBulbStateManager stateManager = new LightBulbStateManager(getApplicationContext());


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("KLEUREN", "COLOR: " + colorPickerView.getSelectedColor());
                button.setBackgroundColor(colorPickerView.getSelectedColor());

                String hexColor = String.format("#%06X", (0xFFFFFF & colorPickerView.getSelectedColor()));

                float[] hsv = new float[3];
                Color.colorToHSV(colorPickerView.getSelectedColor(), hsv);

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("hue", hsv[0] * 182);
                    jsonObject.put("sat", hsv[1] * 254);
                    jsonObject.put("bri", hsv[2] * 254);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                stateManager.setLightState(jsonObject, position + 1, 80);
            }
        });

        lightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("on", isChecked);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                button.setEnabled(isChecked);
                stateManager.setLightState(jsonObject, position + 1, 80);
            }
        });
    }
}
