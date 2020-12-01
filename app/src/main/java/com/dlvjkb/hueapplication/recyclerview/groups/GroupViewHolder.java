package com.dlvjkb.hueapplication.recyclerview.groups;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.dlvjkb.hueapplication.R;
import com.dlvjkb.hueapplication.recyclerview.lightbulbs.LightBulbClickListener;
import com.dlvjkb.hueapplication.recyclerview.lightbulbs.LightBulbViewHolder;

public class GroupViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

    public TextView GroupName;
    public ImageView GroupImage;
    public SwitchCompat groupSwitch;
    private GroupClickListener listener;

    public GroupViewHolder(@NonNull View itemView, GroupClickListener listener) {
        super(itemView);
        this.GroupName = itemView.findViewById(R.id.tvGroupName);
        this.GroupImage = itemView.findViewById(R.id.ivGroup);
        this.groupSwitch = itemView.findViewById(R.id.groupSwitch);
        this.listener = listener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        listener.onGroupClick(position);
    }
}
