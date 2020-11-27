package com.dlvjkb.hueapplication.recyclerview.groups;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dlvjkb.hueapplication.R;
import com.dlvjkb.hueapplication.recyclerview.lightbulbs.LightBulbClickListener;

public class GroupViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

    public TextView GroupName;
    public ImageView GroupImage;
    private GroupClickListener listener;

    public GroupViewHolder(@NonNull View itemView, GroupClickListener listener) {
        super(itemView);

        this.GroupName = itemView.findViewById(R.id.tvGroupName);
        this.GroupImage = itemView.findViewById(R.id.ivGroup);
        this.listener = listener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        listener.onGroupClick(position);
    }
}
