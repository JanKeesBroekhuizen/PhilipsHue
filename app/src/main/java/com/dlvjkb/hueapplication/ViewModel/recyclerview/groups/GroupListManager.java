package com.dlvjkb.hueapplication.ViewModel.recyclerview.groups;

import android.content.Context;
import android.util.Log;
import com.dlvjkb.hueapplication.model.groups.HueGroupConnection;
import com.dlvjkb.hueapplication.model.groups.Group;
import com.dlvjkb.hueapplication.model.groups.GroupLoadListener;
import java.util.ArrayList;

public class GroupListManager implements GroupLoadListener {
    private static final String TAG = GroupListManager.class.getName();

    private static GroupListManager instance = null;
    synchronized public static GroupListManager getInstance(){
        if (instance == null){
            instance = new GroupListManager();
        }
        return instance;
    }

    private ArrayList<Group> groups;
    private GroupListListener listener;

    private GroupListManager(){
        this.groups = new ArrayList<>();
    }

    public ArrayList<Group> getGroups(){
        return this.groups;
    }

    public Group getGroup(int position){
        return this.groups.get(position);
    }

    public void clearGroups(){
        this.groups.clear();
    }

    public void startGroups(Context context, GroupListListener listener){
        this.listener = listener;
        groups.clear();
        this.listener.onGroupListChanged();
        HueGroupConnection.getInstance(context, this).getGroups();
    }

    @Override
    public void onGroupAvailable(Group group) {
        groups.add(group);
        this.listener.onGroupListChanged();
        Log.d(TAG, "Size of list: " + groups.size());
    }
}
