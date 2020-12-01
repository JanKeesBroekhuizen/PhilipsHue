package com.dlvjkb.hueapplication;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dlvjkb.hueapplication.model.lightbulbs.LightBulb;
import com.dlvjkb.hueapplication.model.lightbulbs.LightBulbLoadListener;

import org.json.JSONException;
import org.json.JSONObject;

public class LightBulbStateManager {
    private static LightBulbStateManager instance = null;
    private final String TAG = LightBulbStateManager.class.getName();

    synchronized public static LightBulbStateManager getInstance(Context context){

        if (instance == null){
            Log.d("HueLightBulbConnection","getInstance()");
            instance = new LightBulbStateManager(context);
        }
        return instance;
    }

    private RequestQueue requestQueue;
    private int portNumber;


    LightBulbStateManager(Context context){
        this.requestQueue = Volley.newRequestQueue(context);
        this.portNumber = 8000;
    }
    public void setLightBulb(LightBulb lightBulb){
        JSONObject jsonObject = new JSONObject();

        try{
            if (!lightBulb.state.on){
                jsonObject.put("on", false);
            } else {
                jsonObject.put("on", lightBulb.state.on);
                jsonObject.put("hue", lightBulb.state.hue);
                jsonObject.put("bri", lightBulb.state.bri);
                jsonObject.put("sat", lightBulb.state.sat);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String url = "http://192.168.178.91:" + portNumber + "/api/newdeveloper/lights/"+ lightBulb.number + "/state";
        JsonObjectRequest putRequest = new JsonObjectRequest(
                Request.Method.PUT,
                url,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //TODO
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //TODO
                    }
                }

        );
        requestQueue.add(putRequest);
    }

    public void setPortNumber(int portNumber){
        this.portNumber = portNumber;
    }
}
