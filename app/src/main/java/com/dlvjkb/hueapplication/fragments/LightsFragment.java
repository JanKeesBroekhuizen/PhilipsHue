package com.dlvjkb.hueapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dlvjkb.hueapplication.GroupDetailActivity;
import com.dlvjkb.hueapplication.LightBulbDetailActivity;
import com.dlvjkb.hueapplication.R;
import com.dlvjkb.hueapplication.recyclerview.groups.GroupAdapter;
import com.dlvjkb.hueapplication.recyclerview.groups.GroupClickListener;
import com.dlvjkb.hueapplication.recyclerview.groups.GroupListListener;
import com.dlvjkb.hueapplication.recyclerview.groups.GroupListManager;
import com.dlvjkb.hueapplication.recyclerview.lightbulbs.LightBulbAdapter;
import com.dlvjkb.hueapplication.recyclerview.lightbulbs.LightBulbClickListener;
import com.dlvjkb.hueapplication.recyclerview.lightbulbs.LightBulbListListener;
import com.dlvjkb.hueapplication.recyclerview.lightbulbs.LightBulbListManager;

import java.time.LocalTime;

public class LightsFragment extends Fragment implements LightBulbClickListener, GroupClickListener {
    private static final String TAG = LightsFragment.class.getName();

    private RecyclerView lightBulbRecyclerView;
    private RecyclerView groupRecyclerView;
    private LightBulbAdapter lightBulbAdapter;
    private GroupAdapter groupAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lights, container, false);

        LightBulbListManager.getInstance().clearLightBulbs();

        //Initialisation methods..
        setGooddayMessage(view);
        initRecyclerViews(view);
        startLightBulbs();
        startGroups();
        return view;
    }

    private void initRecyclerViews(View view){
        lightBulbRecyclerView = view.findViewById(R.id.rvLightBulb);
        lightBulbRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,false));

        lightBulbAdapter = new LightBulbAdapter(getContext(), LightBulbListManager.getInstance().getLightBulbs(), this);
        lightBulbRecyclerView.setAdapter(lightBulbAdapter);
        lightBulbAdapter.notifyDataSetChanged();

        groupRecyclerView = view.findViewById(R.id.rvGroup);
        groupRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,
                false));

        groupAdapter = new GroupAdapter(getContext(), GroupListManager.getInstance().getGroups(), this);
        groupRecyclerView.setAdapter(groupAdapter);
        groupAdapter.notifyDataSetChanged();
    }

    private void setGooddayMessage(View view){
        TextView messageView = view.findViewById(R.id.tvGoodday);
        LocalTime morning = LocalTime.parse("00:00");
        LocalTime afternoon = LocalTime.parse("12:00");
        LocalTime evening = LocalTime.parse("18:00");
        LocalTime localTime = LocalTime.now();
        if (localTime.isAfter(evening) && localTime.isAfter(afternoon) && localTime.isAfter(morning)){
            messageView.setText(messageView.getText(), TextView.BufferType.EDITABLE);
            ((Editable) messageView.getText()).insert(messageView.length(), "evening.");
        }else if (localTime.isAfter(afternoon) && localTime.isAfter(morning) && localTime.isBefore(evening)){
            messageView.setText(messageView.getText(), TextView.BufferType.EDITABLE);
            ((Editable) messageView.getText()).insert(messageView.length(), "afternoon.");
        }else if (localTime.isAfter(morning) && localTime.isBefore(afternoon) && localTime.isBefore(evening)){
            messageView.setText(messageView.getText(), TextView.BufferType.EDITABLE);
            ((Editable) messageView.getText()).insert(messageView.length(), "morning.");
        }
    }

    @Override
    public void onLightBulbClick(int position) {
        Log.d(TAG, "Clicked on fragment " + position);
        //Toast.makeText(getContext(), "Clicked on fragment " + position, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getContext(), LightBulbDetailActivity.class);
        intent.putExtra("LightBulb", LightBulbListManager.getInstance().getLightBulb(position));
        intent.putExtra("position", position);
        startActivity(intent);
    }

    public void startLightBulbs(){
        LightBulbListManager.getInstance().startLightBulbs(getContext(), new LightBulbListListener() {
            @Override
            public void onLightBulbListChanged() {
                lightBulbAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void onGroupClick(int position) {
        Log.d(TAG,"Clicked on grouprecyclerview: " + position);
        Intent intent = new Intent(getContext(), GroupDetailActivity.class);
        startActivity(intent);
    }

    public void startGroups(){
        GroupListManager.getInstance().startGroups(getContext(), new GroupListListener() {
            @Override
            public void onGroupListChanged() {
                groupAdapter.notifyDataSetChanged();
            }
        });
    }
}