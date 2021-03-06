package com.dlvjkb.hueapplication.View.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.dlvjkb.hueapplication.View.activities.LightBulbDetailActivity;
import com.dlvjkb.hueapplication.R;
import com.dlvjkb.hueapplication.model.groups.Group;
import com.dlvjkb.hueapplication.model.lightbulbs.LightBulb;
import com.dlvjkb.hueapplication.ViewModel.recyclerview.groups.GroupAdapter;
import com.dlvjkb.hueapplication.ViewModel.recyclerview.groups.GroupClickListener;
import com.dlvjkb.hueapplication.ViewModel.recyclerview.groups.GroupListListener;
import com.dlvjkb.hueapplication.ViewModel.recyclerview.groups.GroupListManager;
import com.dlvjkb.hueapplication.ViewModel.recyclerview.lightbulbs.LightBulbAdapter;
import com.dlvjkb.hueapplication.ViewModel.recyclerview.lightbulbs.LightBulbClickListener;
import com.dlvjkb.hueapplication.ViewModel.recyclerview.lightbulbs.LightBulbListListener;
import com.dlvjkb.hueapplication.ViewModel.recyclerview.lightbulbs.LightBulbListManager;
import java.time.LocalTime;
import java.util.ArrayList;

public class LightsFragment extends Fragment implements LightBulbClickListener, GroupClickListener {
    private static final String TAG = LightsFragment.class.getName();

    private RecyclerView lightBulbRecyclerView;
    private RecyclerView groupRecyclerView;
    private LightBulbAdapter lightBulbAdapter;
    private GroupAdapter groupAdapter;
    private SwipeRefreshLayout refreshLayoutLights;
    private SwipeRefreshLayout refreshLayoutGroups;
    public static String ipAddress;
    public static String  portNumber;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lights, container, false);

        LightBulbListManager.getInstance().clearLightBulbs();
        GroupListManager.getInstance().clearGroups();


        try {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("settings",0);
            ipAddress = sharedPreferences.getString("ipAddress","");
            portNumber = sharedPreferences.getString("portNumber","");
            Log.d("IP:", "" + ipAddress);

        }catch (Exception e){
            e.printStackTrace();
        }
        //Initialisation methods..
        setGooddayMessage(view);
        initRefreshViews(view);
        initRecyclerViews(view);
        startLightBulbs();
        startGroups();
        addLightsToGroup();
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
                LinearLayoutManager.HORIZONTAL,
                false));

        groupAdapter = new GroupAdapter(getContext(), GroupListManager.getInstance().getGroups(), this);
        groupRecyclerView.setAdapter(groupAdapter);
        groupAdapter.notifyDataSetChanged();
    }

    private void setGooddayMessage(View view){
        TextView messageView = view.findViewById(R.id.tvLightsTitle);
        LocalTime morning = LocalTime.parse("00:00");
        LocalTime afternoon = LocalTime.parse("12:00");
        LocalTime evening = LocalTime.parse("18:00");
        LocalTime localTime = LocalTime.now();
        if (localTime.isAfter(evening) && localTime.isAfter(afternoon) && localTime.isAfter(morning)){
            messageView.setText(R.string.String_GoodEvening);
        }else if (localTime.isAfter(afternoon) && localTime.isAfter(morning) && localTime.isBefore(evening)){
            messageView.setText(messageView.getText(), TextView.BufferType.EDITABLE);
            messageView.setText(R.string.String_GoodAfternoon);
        }else if (localTime.isAfter(morning) && localTime.isBefore(afternoon) && localTime.isBefore(evening)){
            messageView.setText(R.string.String_GoodMorning);
        }
    }

    @Override
    public void onLightBulbClick(int position) {
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
        Log.d(TAG, "Clicked on Group " + position);
    }

    public void startGroups(){
        GroupListManager.getInstance().startGroups(getContext(), new GroupListListener() {
            @Override
            public void onGroupListChanged() {
                groupAdapter.notifyDataSetChanged();
            }
        });
    }

    public void addLightsToGroup(){
        ArrayList<Group> groups = GroupListManager.getInstance().getGroups();
        ArrayList<LightBulb> lightBulbs = LightBulbListManager.getInstance().getLightBulbs();

        for (int groupIndex = 0; groupIndex < groups.size(); groupIndex++){
            for (int i = 0; i < groups.get(i).lightsNumbers.size(); i++){
                for (int lightBulbIndex = 0; lightBulbIndex < lightBulbs.size(); lightBulbIndex++){
                    if (groups.get(i).lightsNumbers.get(i) == lightBulbs.get(lightBulbIndex).number){
                        groups.get(groupIndex).lightBulbs.add(lightBulbs.get(lightBulbIndex));
                    }
                }
            }
        }
    }

    public void initRefreshViews(View view){

        refreshLayoutLights = view.findViewById(R.id.refreshViewLights);
        refreshLayoutLights.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                startLightBulbs();
                startGroups();
                refreshLayoutLights.setRefreshing(false);
            }
        });

        refreshLayoutGroups = view.findViewById(R.id.refreshViewGroups);
        refreshLayoutGroups.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                startGroups();
                startLightBulbs();
                refreshLayoutGroups.setRefreshing(false);
            }
        });
    }
}