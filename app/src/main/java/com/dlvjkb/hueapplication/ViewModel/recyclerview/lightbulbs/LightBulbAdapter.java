package com.dlvjkb.hueapplication.ViewModel.recyclerview.lightbulbs;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devs.vectorchildfinder.VectorChildFinder;
import com.devs.vectorchildfinder.VectorDrawableCompat;
import com.dlvjkb.hueapplication.ViewModel.LightBulbStateManager;
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
        System.out.println("HUE: " + lightBulb.state.hue);
        System.out.println("SAT: " + lightBulb.state.sat);
        System.out.println("BRI: " + lightBulb.state.bri);

        holder.lightBulbName.setText(lightBulb.name);
        changeBulbColor(lightBulb,holder);
        //Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_bulb);
        //holder.lightBulbImage.setImageBitmap(bitmap);
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

    private void changeBulbColor(LightBulb lightBulb, LightBulbViewHolder holder){
        VectorChildFinder vectorChildFinder = new VectorChildFinder(context,R.drawable.ic_bulb,holder.lightBulbImage);
        VectorDrawableCompat.VFullPath light = vectorChildFinder.findPathByName("lightBulbBase");
        float[] floats = new float[3];
        floats[0] = lightBulb.state.hue / 182;
        floats[1] = lightBulb.state.sat / 254;
        floats[2] = lightBulb.state.bri /254;
        light.setFillColor(Color.HSVToColor(floats));
        for (int i = 1; i <= 7 ; i++){
            VectorDrawableCompat.VFullPath lightTest = vectorChildFinder.findPathByName("lightBulb" + i);
            lightTest.setFillColor(Color.HSVToColor(floats));
        }
    }

}
