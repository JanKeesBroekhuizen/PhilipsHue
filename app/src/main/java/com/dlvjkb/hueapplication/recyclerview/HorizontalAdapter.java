package com.dlvjkb.hueapplication.recyclerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dlvjkb.hueapplication.R;
import com.dlvjkb.hueapplication.model.LightBulb;

import java.util.ArrayList;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalViewHolder>{

    private Context context;
    private ArrayList<LightBulb> lightBulbArrayList;

    public HorizontalAdapter(Context context, ArrayList<LightBulb> lightBulbArrayList){
        this.context = context;
        this.lightBulbArrayList = lightBulbArrayList;
    }

    @NonNull
    @Override
    public HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewGroup group = (ViewGroup) inflater.inflate(R.layout.lightbulb_item_row,parent,false);
        return new HorizontalViewHolder(group);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalViewHolder holder, int position) {
        final LightBulb lightBulb = lightBulbArrayList.get(position);

        HorizontalViewHolder viewHolder = holder;
        viewHolder.lightBulbName.setText(lightBulb.name);
        if (lightBulb.state != true){
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.app_logo);
            viewHolder.lightBulbImage.setImageBitmap(bitmap);
        }else {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.menu_btn_lights);
            viewHolder.lightBulbImage.setImageBitmap(bitmap);
        }
    }

    @Override
    public int getItemCount() {
        return lightBulbArrayList.size();
    }
}
