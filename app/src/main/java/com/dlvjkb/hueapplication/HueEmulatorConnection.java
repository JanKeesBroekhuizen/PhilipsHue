package com.dlvjkb.hueapplication;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dlvjkb.hueapplication.model.LightBulb;
import com.dlvjkb.hueapplication.model.LightBulbLoadListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HueEmulatorConnection {
    private static HueEmulatorConnection instance = null;

    synchronized public static HueEmulatorConnection getInstance(Context context, LightBulbLoadListener listener){
        if (instance == null){
            instance = new HueEmulatorConnection(context, listener);
        }
        return instance;
    }

    private final String TAG = HueEmulatorConnection.class.getName();

    private RequestQueue requestQueue;
    private LightBulbLoadListener listener;


    HueEmulatorConnection(Context context, LightBulbLoadListener listener){
        this.requestQueue = Volley.newRequestQueue(context);
        this.listener = listener;
    }

    public void initLightBulbs(){
        final String url = "http://192.168.178.91:80/api/newdeveloper/lights";
        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int lightAmount = response.length();
                            Log.d(TAG,"Got " + lightAmount + " lightbulbs!");
                            for (int lightNumber = 1; lightNumber < lightAmount + 1; lightNumber++){

                                JSONObject lightObject = response.getJSONObject(lightNumber + "");
                                LightBulb lightBulb = new LightBulb(lightObject);
                                listener.onLightBulbAvailable(lightBulb);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG,"Volley error " + error.getLocalizedMessage());
                        error.printStackTrace();
                    }
                }
        );
        requestQueue.add(request);
    }
}
