package com.dlvjkb.hueapplication.recyclerview.groups;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dlvjkb.hueapplication.GroupActionManager;
import com.dlvjkb.hueapplication.R;
import com.dlvjkb.hueapplication.model.groups.Group;
import com.dlvjkb.hueapplication.model.lightbulbs.LightBulb;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupViewHolder> {

    private Context context;
    private ArrayList<Group> groupArrayList;
    private GroupClickListener clickListener;

    public GroupAdapter(Context context, ArrayList<Group> groupArrayList, GroupClickListener clickListener){
        this.context = context;
        this.groupArrayList = groupArrayList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_item_row, parent, false);
        GroupViewHolder viewHolder = new GroupViewHolder(itemView, clickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        final Group group = groupArrayList.get(position);
        Log.d("onBindViewHolder", group.groupNumber + "");
        holder.GroupName.setText(group.name);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.app_logo);
        holder.groupSwitch.setChecked(group.action.on);
        holder.groupSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                group.action.on = isChecked;
                for (int i = 0; i < group.lightBulbs.size(); i++){
                    LightBulb lightBulb = group.lightBulbs.get(i);
                    lightBulb.state.on = isChecked;
                }
                GroupActionManager.getInstance(context).setGroup(group);
            }
        });
        System.out.println("ADAPTER: " + group.toString());
        holder.GroupImage.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return groupArrayList.size();
    }
}
