package com.dlvjkb.hueapplication.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dlvjkb.hueapplication.R;

public class LightBulbViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

    public TextView lightBulbName;
    public ImageView lightBulbImage;
    private LightBulbClickListener listener;

    public LightBulbViewHolder(@NonNull View itemView, LightBulbClickListener listener) {
        super(itemView);

        this.lightBulbName = itemView.findViewById(R.id.tvLightBulbName);
        this.lightBulbImage = itemView.findViewById(R.id.ivLightBulb);
        this.listener = listener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        listener.onLightBulbClick(position);
    }
}
