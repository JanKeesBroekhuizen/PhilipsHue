package com.dlvjkb.hueapplication.recyclerview.lightbulbs;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dlvjkb.hueapplication.R;
import com.dlvjkb.hueapplication.model.LightBulb;

import java.util.ArrayList;

public class LightBulbAdapter extends RecyclerView.Adapter<LightBulbViewHolder>{

    private Context context;
    private ArrayList<LightBulb> lightBulbArrayList;
    private LightBulbClickListener listener;

    public LightBulbAdapter(Context context, ArrayList<LightBulb> lightBulbArrayList, LightBulbClickListener listener){
        this.context = context;
        this.lightBulbArrayList = lightBulbArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LightBulbViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lightbulb_item_row, parent, false);
        LightBulbViewHolder viewHolder = new LightBulbViewHolder(itemView, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LightBulbViewHolder holder, int position) {
        final LightBulb lightBulb = lightBulbArrayList.get(position);

        holder.lightBulbName.setText(lightBulb.name);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.app_logo);
        holder.lightBulbImage.setImageBitmap(bitmap);

//        if (!lightBulb.state.on){
//            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.app_logo);
//            viewHolder.lightBulbImage.setImageBitmap(bitmap);
//        }else {
//            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.menu_btn_lights);
//            viewHolder.lightBulbImage.setImageBitmap(bitmap);
//        }
    }

    @Override
    public int getItemCount() {
        return lightBulbArrayList.size();
    }
}
