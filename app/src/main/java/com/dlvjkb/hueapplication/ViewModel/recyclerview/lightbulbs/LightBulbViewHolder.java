package com.dlvjkb.hueapplication.ViewModel.recyclerview.lightbulbs;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.dlvjkb.hueapplication.R;

public class LightBulbViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

    public TextView lightBulbName;
    public ImageView lightBulbImage;
    public SwitchCompat lightSwitch;
    private LightBulbClickListener listener;

    public LightBulbViewHolder(@NonNull View itemView, LightBulbClickListener listener) {
        super(itemView);

        this.lightBulbName = itemView.findViewById(R.id.tvLightBulbName);
        this.lightBulbImage = itemView.findViewById(R.id.ivLightBulb);
        this.lightSwitch = itemView.findViewById(R.id.LightSwitch);
        this.listener = listener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        listener.onLightBulbClick(position);
    }
}
