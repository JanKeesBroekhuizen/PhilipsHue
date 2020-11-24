package com.dlvjkb.hueapplication.recyclerview;

import android.content.Context;

import com.dlvjkb.hueapplication.HueEmulatorConnection;
import com.dlvjkb.hueapplication.model.LightBulb;
import com.dlvjkb.hueapplication.model.LightBulbLoadListener;

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
        HueEmulatorConnection connection = HueEmulatorConnection.getInstance(context, this);
        connection.initLightBulbs();
    }

    @Override
    public void onLightBulbAvailable(LightBulb lightBulb) {
        lightBulbs.add(lightBulb);
        this.listener.onLightBulbListChanged();
    }
}
