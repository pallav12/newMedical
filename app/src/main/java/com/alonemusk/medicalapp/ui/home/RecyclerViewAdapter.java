package com.alonemusk.medicalapp.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alonemusk.medicalapp.R;
import com.alonemusk.medicalapp.ui.models.ButtonHome;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context mContext;
    List<ButtonHome> mButtons;
    public RecyclerViewAdapter(Context context, List<ButtonHome> buttonList){
        super();
        mContext = context;
        mButtons = buttonList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.custom_button_home,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final ButtonHome button = mButtons.get(position);

        holder.image.setImageResource(button.getImage());
        holder.label.setText(button.getLabel());

        if(button.getIntentClass()!=null) {

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, button.getIntentClass()));

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mButtons.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView label;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            label = itemView.findViewById(R.id.label);
        }
    }
}
