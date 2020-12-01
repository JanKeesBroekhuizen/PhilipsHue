package com.dlvjkb.hueapplication.recyclerview.lightbulbs;

import android.content.Context;
import android.util.Log;

import com.dlvjkb.hueapplication.HueConnection;
import com.dlvjkb.hueapplication.HueLightBulbConnection;
import com.dlvjkb.hueapplication.LoadListener;
import com.dlvjkb.hueapplication.model.groups.Group;
import com.dlvjkb.hueapplication.model.lightbulbs.LightBulb;
import com.dlvjkb.hueapplication.model.lightbulbs.LightBulbLoadListener;

import java.util.ArrayList;

public class LightBulbListManager implements LightBulbLoadListener {
    private static final String TAG = LightBulbListManager.class.getName();

    private static LightBulbListManager instance = null;
    synchronized  public static LightBulbListManager getInstance(){
        if (instance == null){
            instance = new LightBulbListManager();
        }
        return instance;
    }

    private ArrayList<LightBulb> lightBulbs;
    private LightBulbListListener listener;

    private LightBulbListManager(){
        this.lightBulbs = new ArrayList<>();
    }

    public ArrayList<LightBulb> getLightBulbs(){
        return this.lightBulbs;
    }

    public LightBulb getLightBulb(int position){
        return this.lightBulbs.get(position);
    }

    public void clearLightBulbs(){
        this.lightBulbs.clear();
    }

    public void startLightBulbs(Context context, LightBulbListListener listener){
        this.listener = listener;
        lightBulbs.clear();
        this.listener.onLightBulbListChanged();
        //HueLightBulbConnection connection = HueLightBulbConnection.getInstance(context, this);
        HueLightBulbConnection.getInstance(context, this).getLightBulbs();
    }

    @Override
    public void onLightBulbAvailable(LightBulb lightBulb) {
        lightBulbs.add(lightBulb);
        this.listener.onLightBulbListChanged();
        Log.d("LightBulbListManager", "ListAmount: " + lightBulbs.size());
    }
}
