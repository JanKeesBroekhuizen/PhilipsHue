package com.dlvjkb.hueapplication.model.lightbulbs;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;

public class LightBulb implements Serializable {
    public int number;
    private String modelId;
    public String name;
    private String swVersion;
    public LightBulbState state;
    private String type;
    private String uniqueId;

    public LightBulb(int lightBulbNumber, JSONObject jsonObject) {
        try{
            this.number = lightBulbNumber;
            this.modelId = jsonObject.getString("modelid");
            this.name = jsonObject.getString("name");
            this.swVersion = jsonObject.getString("swversion");
            this.state = new LightBulbState(jsonObject.getJSONObject("state"));
            this.type = jsonObject.getString("type");
            this.uniqueId = jsonObject.getString("uniqueid");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
