package com.dlvjkb.hueapplication;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LightBulbStateManager {
    private Context context;
    private RequestQueue requestQueue;

    public LightBulbStateManager(Context context){
        this.context = context;
        this.requestQueue = Volley.newRequestQueue(this.context);
    }

    public void setLightState(JSONObject jsonObject, int lightNumber, int portNumber){
        final String url = "http://192.168.178.91:" + portNumber + "/api/newdeveloper/lights/"+ lightNumber + "/state";
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
        this.requestQueue.add(putRequest);
    }
}
