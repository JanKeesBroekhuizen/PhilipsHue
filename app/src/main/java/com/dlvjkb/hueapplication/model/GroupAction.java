package com.dlvjkb.hueapplication.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GroupAction {
    private ArrayList<Double> xy;
    private int ct;
    private int sat;
    private  String effect;
    private int bri;
    private int hue;
    private String colorMode;
    private boolean on;

    public GroupAction (JSONObject jsonObject){
        try {
            this.xy = jsonArrayToList(jsonObject.getJSONArray("xy"));
            this.ct = jsonObject.getInt("ct");
            this.sat = jsonObject.getInt("sat");
            this.effect = jsonObject.getString("effect");
            this.bri = jsonObject.getInt("bri");
            this.hue = jsonObject.getInt("hue");
            this.colorMode = jsonObject.getString("colormode");
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
}
