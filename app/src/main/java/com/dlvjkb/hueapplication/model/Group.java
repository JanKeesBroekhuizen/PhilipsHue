package com.dlvjkb.hueapplication.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Group {
    public String name;
    private GroupAction action;
    private ArrayList<Integer> lights;

    public Group(JSONObject jsonObject){
        try {
            this.name = jsonObject.getString("name");
            this.action = new GroupAction(jsonObject.getJSONObject("action"));
            this.lights = jsonArrayToList(jsonObject.getJSONArray("lights"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> jsonArrayToList(JSONArray array){
        ArrayList<Integer> list = new ArrayList<>();
        for (int arrayIndex = 0; arrayIndex < array.length(); arrayIndex++){
            try {
                list.add(array.getInt(arrayIndex));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
