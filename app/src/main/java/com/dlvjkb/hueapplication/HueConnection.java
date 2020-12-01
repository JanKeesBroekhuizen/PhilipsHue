package com.dlvjkb.hueapplication;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dlvjkb.hueapplication.model.groups.Group;
import com.dlvjkb.hueapplication.model.groups.GroupLoadListener;
import com.dlvjkb.hueapplication.model.lightbulbs.LightBulb;

import org.json.JSONException;
import org.json.JSONObject;

public class HueConnection {
    private static HueConnection instance = null;

    /*synchronized public static HueConnection getInstance(Context context, LoadListener listener){

        if (instance == null){
            Log.d("HueGroupConnection","getInstance()");
            instance = new HueConnection(context, listener);
        }
        return instance;
    }*/

    private final String TAG = HueConnection.class.getName();

    private RequestQueue requestQueue;
    private LoadListener listener;
    private int portNumber;

    HueConnection(Context context, LoadListener listener){
        this.requestQueue = Volley.newRequestQueue(context);
        this.listener = listener;
        this.portNumber = 80;
    }

    public void getLightBulbs(){
        final String url = "http://192.168.178.91:" + portNumber + "/api/newdeveloper/lights";
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
                                LightBulb lightBulb = new LightBulb(lightNumber ,lightObject);
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
                        Log.d(TAG,"Lightbulb Volley error " + error.getLocalizedMessage());
                    }
                }
        );
        requestQueue.add(request);
    }

    public void getGroups(){
        final String url = "http://192.168.178.91:" + portNumber + "/api/newdeveloper/groups";
        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            int groupAmount = response.length();
                            Log.d(TAG, "Got " + groupAmount + " groups!");
                            for (int groupNumber = 1; groupNumber < groupAmount + 1; groupNumber++) {
                                JSONObject groupObject = response.getJSONObject(groupNumber + "");
                                Group group = new Group(groupNumber, groupObject);
                                listener.onGroupAvailable(group);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG,"Group Volley error " + error.getLocalizedMessage());
                    }
                }
        );
        requestQueue.add(request);
    }

    public void setPortNumber(int portNumber){
        this.portNumber = portNumber;
    }
}
