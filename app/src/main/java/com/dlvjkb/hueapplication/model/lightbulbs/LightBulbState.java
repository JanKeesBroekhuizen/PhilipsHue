package com.dlvjkb.hueapplication.model.lightbulbs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LightBulbState implements Serializable {
    private ArrayList<Double> xy;
    private int ct;
    private String alert;
    public int sat;
    private String effect;
    public int bri;
    public int hue;
    private String colorMode;
    private boolean reachable;
    public boolean on;

    public LightBulbState(JSONObject jsonObject) {

        try {
            this.xy = jsonArrayToList(jsonObject.getJSONArray("xy"));
            this.ct = jsonObject.getInt("ct");
            this.alert = jsonObject.getString("alert");
            this.sat = jsonObject.getInt("sat");
            this.effect = jsonObject.getString("effect");
            this.bri = jsonObject.getInt("bri");
            this.hue = jsonObject.getInt("hue");
            this.colorMode = jsonObject.getString("colormode");
            this.reachable = jsonObject.getBoolean("reachable");
            this.on = jsonObject.getBoolean("on");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Double> jsonArrayToList(JSONArray array){
        ArrayList<Double> list = new ArrayList<>();
        for (int arrayIndex = 0; arrayIndex < array.length(); arrayIndex++){
            try {
                list.add(array.getDouble(arrayIndex));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public String toString() {
        return "State{" +
                "xy=" + xy +
                ", ct=" + ct +
                ", alert='" + alert + '\'' +
                ", sat=" + sat +
                ", effect='" + effect + '\'' +
                ", bri=" + bri +
                ", hue=" + hue +
                ", colorMode='" + colorMode + '\'' +
                ", reachable=" + reachable +
                ", on=" + on +
                '}';
    }
}
