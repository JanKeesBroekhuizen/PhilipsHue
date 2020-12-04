package com.dlvjkb.hueapplication.ViewModel;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dlvjkb.hueapplication.View.fragments.LightsFragment;
import com.dlvjkb.hueapplication.model.lightbulbs.LightBulb;

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
    private String portNumber;
    private String ipAddress;


    LightBulbStateManager(Context context){
        this.requestQueue = Volley.newRequestQueue(context);
        this.portNumber = LightsFragment.portNumber;
        this.ipAddress = LightsFragment.ipAddress;
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

        final String url = "http://" + LightsFragment.ipAddress + ":" + LightsFragment.portNumber + "/api/newdeveloper/lights/"+ lightBulb.number + "/state";
        JsonObjectRequest putRequest = new JsonObjectRequest(
                Request.Method.PUT,
                url,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG,"Volley response " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG,"Volley error " + error.getLocalizedMessage());
                    }
                }

        );
        requestQueue.add(putRequest);
    }



    public void changeName(LightBulb lightBulb){
        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject.put("name", lightBulb.name);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String url = "http://" + LightsFragment.ipAddress + ":" + LightsFragment.portNumber + "/api/newdeveloper/lights/"+ lightBulb.number;
        JsonObjectRequest putRequest = new JsonObjectRequest(
                Request.Method.PUT,
                url,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        );
        requestQueue.add(putRequest);
    }

    public void setPortNumber(String portNumber){
        this.portNumber = portNumber;
    }
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
