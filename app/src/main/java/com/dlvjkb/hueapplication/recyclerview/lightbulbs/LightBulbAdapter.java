package com.dlvjkb.hueapplication.recyclerview.lightbulbs;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dlvjkb.hueapplication.LightBulbStateManager;
import com.dlvjkb.hueapplication.R;
import com.dlvjkb.hueapplication.model.lightbulbs.LightBulb;

import java.util.ArrayList;

public class LightBulbAdapter extends RecyclerView.Adapter<LightBulbViewHolder>{

    private Context context;
    private ArrayList<LightBulb> lightBulbArrayList;
    private LightBulbClickListener clickListener;

    public LightBulbAdapter(Context context, ArrayList<LightBulb> lightBulbArrayList, LightBulbClickListener clickListener){
        this.context = context;
        this.lightBulbArrayList = lightBulbArrayList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public LightBulbViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lightbulb_item_row, parent, false);
        LightBulbViewHolder viewHolder = new LightBulbViewHolder(itemView, clickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LightBulbViewHolder holder, int position) {
        final LightBulb lightBulb = lightBulbArrayList.get(position);

        holder.lightBulbName.setText(lightBulb.name);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.app_logo);
        holder.lightBulbImage.setImageBitmap(bitmap);
        holder.lightSwitch.setChecked(lightBulb.state.on);
        holder.lightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                lightBulb.state.on = isChecked;
                LightBulbStateManager.getInstance(context).setLightBulb(lightBulb);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lightBulbArrayList.size();
    }
}
