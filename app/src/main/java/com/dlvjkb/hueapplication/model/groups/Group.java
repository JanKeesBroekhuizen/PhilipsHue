package com.dlvjkb.hueapplication.model.groups;

import com.dlvjkb.hueapplication.model.lightbulbs.LightBulb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Group {
    public int groupNumber;
    public String name;
    public GroupAction action;
    public ArrayList<Integer> lightsNumbers;
    public ArrayList<LightBulb> lightBulbs;

    public Group(int groupNumber, JSONObject jsonObject){
        try {
            this.groupNumber = groupNumber;
            this.name = jsonObject.getString("name");
            this.action = new GroupAction(jsonObject.getJSONObject("action"));
            this.lightsNumbers = jsonArrayToList(jsonObject.getJSONArray("lights"));
            this.lightBulbs = new ArrayList<>();
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

    public void setLightBulbs(ArrayList<LightBulb> lightBulbs){
        this.lightBulbs = lightBulbs;
    }
}
