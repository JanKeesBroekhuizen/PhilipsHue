package com.dlvjkb.hueapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dlvjkb.hueapplication.model.LightBulb;
import com.flask.colorpicker.ColorPickerView;

import org.json.JSONException;
import org.json.JSONObject;

public class LightBulbDetailView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailview_lightbulb);

        LightBulb lightBulb = (LightBulb) getIntent().getSerializableExtra("LightBulb");
        int position = getIntent().getIntExtra("position", 1);

        ColorPickerView colorPickerView = findViewById(R.id.color_picker_view);
        Button button = findViewById(R.id.button);
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
    }
}
