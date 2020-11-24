package com.dlvjkb.hueapplication;

import org.json.JSONException;
import org.json.JSONObject;

public class LightBulb {
    private String modelId;
    private String name;
    private String swVersion;
    private State state;
    private String type;
    private String uniqueId;

    public LightBulb(JSONObject jsonObject) {
        try{
            this.modelId = jsonObject.getString("modelid");
            this.name = jsonObject.getString("name");
            this.swVersion = jsonObject.getString("swversion");
            this.state = new State(jsonObject.getJSONObject("state"));
            this.type = jsonObject.getString("type");
            this.uniqueId = jsonObject.getString("uniqueid");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "LightBulb{" +
                "modelId='" + modelId + '\'' +
                ", name='" + name + '\'' +
                ", swVersion='" + swVersion + '\'' +
                ", state=" + state +
                ", type='" + type + '\'' +
                ", uniqueId='" + uniqueId + '\'' +
                '}';
    }
}
