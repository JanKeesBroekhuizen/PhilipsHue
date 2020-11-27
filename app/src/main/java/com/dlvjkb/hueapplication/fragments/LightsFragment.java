package com.dlvjkb.hueapplication.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dlvjkb.hueapplication.LightBulbDetailView;
import com.dlvjkb.hueapplication.LightBulbStateManager;
import com.dlvjkb.hueapplication.R;
import com.dlvjkb.hueapplication.model.LightBulb;
import com.dlvjkb.hueapplication.recyclerview.LightBulbAdapter;
import com.dlvjkb.hueapplication.recyclerview.LightBulbClickListener;
import com.dlvjkb.hueapplication.recyclerview.LightBulbListListener;
import com.dlvjkb.hueapplication.recyclerview.LightBulbListManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalTime;
import java.util.ArrayList;

public class LightsFragment extends Fragment implements LightBulbClickListener {
    private static final String TAG = LightsFragment.class.getName();

    private static RecyclerView horizontalRecyclerView;
    private LightBulbStateManager stateManager;
    private LightBulbAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lights, container, false);
        setGooddayMessage(view);

        LightBulbListManager.getInstance().clearLightBulbs();

        horizontalRecyclerView = view.findViewById(R.id.rvHorizontalBulbs);
        horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL,
                false));

        adapter = new LightBulbAdapter(getContext(), LightBulbListManager.getInstance().getLightBulbs(), this);
        horizontalRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        startLightBulbs();
        return view;
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
        Intent intent = new Intent(getContext(), LightBulbDetailView.class);
        intent.putExtra("LightBulb", LightBulbListManager.getInstance().getLightBulb(position));
        intent.putExtra("position", position);
        startActivity(intent);
    }

    public void startLightBulbs(){
        LightBulbListManager.getInstance().startLightBulbs(getContext(), new LightBulbListListener() {
            @Override
            public void onLightBulbListChanged() {
                adapter.notifyDataSetChanged();
            }
        });
    }
}