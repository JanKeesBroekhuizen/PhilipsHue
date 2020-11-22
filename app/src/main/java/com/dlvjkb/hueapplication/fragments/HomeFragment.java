package com.dlvjkb.hueapplication.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dlvjkb.hueapplication.R;

import java.sql.Time;
import java.time.LocalTime;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        setGooddayMessage(view);
        return view;
    }

    private void setGooddayMessage(View view){
        TextView messageView = view.findViewById(R.id.tvGoodday);
        LocalTime morning = LocalTime.parse("00:00");
        LocalTime afternoon = LocalTime.parse("12:00");
        LocalTime evening = LocalTime.parse("19:00");
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
}