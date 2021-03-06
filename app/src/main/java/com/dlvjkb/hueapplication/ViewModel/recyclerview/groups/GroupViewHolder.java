package com.dlvjkb.hueapplication.ViewModel.recyclerview.groups;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.dlvjkb.hueapplication.R;

public class GroupViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

    public EditText GroupName;
    public ImageView GroupImage;
    public SwitchCompat groupSwitch;
    private GroupClickListener listener;

    public GroupViewHolder(@NonNull View itemView, GroupClickListener listener) {
        super(itemView);
        this.GroupName = itemView.findViewById(R.id.etGroupName);
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
