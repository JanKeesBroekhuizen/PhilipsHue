package com.dlvjkb.hueapplication;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dlvjkb.hueapplication.model.Group;
import com.dlvjkb.hueapplication.model.GroupLoadListener;
import com.dlvjkb.hueapplication.model.LightBulbLoadListener;

import org.json.JSONException;
import org.json.JSONObject;

public class HueGroupConnection {
    private static HueGroupConnection instance = null;

    synchronized public static HueGroupConnection getInstance(Context context, GroupLoadListener listener){
        if (instance == null){
            instance = new HueGroupConnection(context, listener);
        }
        return instance;
    }

    private final String TAG = HueLightBulbConnection.class.getName();

    private RequestQueue requestQueue;
    private GroupLoadListener listener;

    HueGroupConnection(Context context, GroupLoadListener listener){
        this.requestQueue = Volley.newRequestQueue(context);
        this.listener = listener;
    }

    public void initGroups(){
        final String url = "http://192.168.178.91:80/api/newdeveloper/groups";
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
                            for (int groupNumber = 0; groupNumber < groupAmount; groupNumber++) {
                                JSONObject groupObject = response.getJSONObject(groupNumber + "");
                                Group group = new Group(groupObject);
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
                        Log.d(TAG,"Volley error " + error.getLocalizedMessage());
                    }
                }
        );
        requestQueue.add(request);
    }
}
