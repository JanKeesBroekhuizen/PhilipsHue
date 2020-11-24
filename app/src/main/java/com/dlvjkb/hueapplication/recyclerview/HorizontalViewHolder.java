package com.dlvjkb.hueapplication.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dlvjkb.hueapplication.R;

public class HorizontalViewHolder extends RecyclerView.ViewHolder  {

    public TextView lightBulbName;
    public ImageView lightBulbImage;

    public HorizontalViewHolder(@NonNull View itemView) {
        super(itemView);

        this.lightBulbName = itemView.findViewById(R.id.tvLightBulbName);
        this.lightBulbImage = itemView.findViewById(R.id.ivLightBulb);
    }
}
