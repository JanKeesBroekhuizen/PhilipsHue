package com.dlvjkb.hueapplication.recyclerview.groups;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dlvjkb.hueapplication.R;
import com.dlvjkb.hueapplication.model.Group;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupViewHolder> {

    private Context context;
    private ArrayList<Group> groupArrayList;
    private GroupClickListener listener;

    public GroupAdapter(Context context, ArrayList<Group> groupArrayList, GroupClickListener listener){
        this.context = context;
        this.groupArrayList = groupArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lightbulb_item_row, parent, false);
        GroupViewHolder viewHolder = new GroupViewHolder(itemView, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        final Group group = groupArrayList.get(position);

        holder.GroupName.setText(group.name);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.app_logo);
        holder.GroupImage.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return groupArrayList.size();
    }
}
