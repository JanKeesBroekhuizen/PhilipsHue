package com.dlvjkb.hueapplication.recyclerview.groups;

import android.content.Context;

import com.dlvjkb.hueapplication.HueGroupConnection;
import com.dlvjkb.hueapplication.model.Group;
import com.dlvjkb.hueapplication.model.GroupLoadListener;

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
        HueGroupConnection connection = HueGroupConnection.getInstance(context, this);
        connection.initGroups();
    }

    @Override
    public void onGroupAvailable(Group group) {
        groups.add(group);
        this.listener.onGroupListChanged();
    }
}
