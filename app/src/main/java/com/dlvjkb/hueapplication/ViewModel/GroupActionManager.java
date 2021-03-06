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
import com.dlvjkb.hueapplication.model.groups.Group;
import org.json.JSONException;
import org.json.JSONObject;

public class GroupActionManager {
    private static GroupActionManager instance = null;
    private final String TAG = GroupActionManager.class.getName();

    synchronized public static GroupActionManager getInstance(Context context){

        if (instance == null){
            Log.d("HueLightBulbConnection","getInstance()");
            instance = new GroupActionManager(context);
        }
        return instance;
    }

    private RequestQueue requestQueue;

    GroupActionManager(Context context){
        this.requestQueue = Volley.newRequestQueue(context);
    }
    public void setGroup(Group group){
        JSONObject jsonObject = new JSONObject();

        try{
            if (!group.action.on){
                jsonObject.put("on", false);
            } else {
                jsonObject.put("on", true);
                jsonObject.put("hue", group.action.hue);
                jsonObject.put("bri", group.action.bri);
                jsonObject.put("sat", group.action.sat);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String url = "http://" + LightsFragment.ipAddress + ":" + LightsFragment.portNumber + "/api/newdeveloper/groups/"+ group.groupNumber + "/action";
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

    public void changeName(Group group){
        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject.put("name", group.name);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String url = "http://" + LightsFragment.ipAddress + ":" + LightsFragment.portNumber + "/api/newdeveloper/groups/"+ group.groupNumber;
        JsonObjectRequest putRequest = new JsonObjectRequest(
                Request.Method.PUT,
                url,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) { }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) { }
                }

        );
        requestQueue.add(putRequest);
    }
}
