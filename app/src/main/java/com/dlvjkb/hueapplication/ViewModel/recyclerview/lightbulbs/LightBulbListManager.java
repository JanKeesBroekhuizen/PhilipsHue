package com.dlvjkb.hueapplication.ViewModel.recyclerview.lightbulbs;

import android.content.Context;
import android.util.Log;
import com.dlvjkb.hueapplication.model.lightbulbs.HueLightBulbConnection;
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
        Log.d("CLEAR","" + lightBulbs.size());
        this.listener.onLightBulbListChanged();
        HueLightBulbConnection.getInstance(context, this).getLightBulbs();
    }

    @Override
    public void onLightBulbAvailable(LightBulb lightBulb) {
        lightBulbs.add(lightBulb);
        this.listener.onLightBulbListChanged();
        Log.d(TAG, "ListAmount: " + lightBulbs.size());
    }
}
