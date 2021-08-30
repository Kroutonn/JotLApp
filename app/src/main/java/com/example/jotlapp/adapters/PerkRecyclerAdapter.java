package com.example.jotlapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jotlapp.R;
import com.example.jotlapp.models.Hero;

import java.util.ArrayList;
import java.util.Arrays;

public class PerkRecyclerAdapter extends RecyclerView.Adapter<PerkRecyclerAdapter.ViewHolder> {


    private ItemClickListener mItemClickListener;
    private ArrayList<String> mPerksList;

    public PerkRecyclerAdapter(ArrayList<String> perkList, ItemClickListener clickListener) {
        this.mPerksList = perkList;
        this.mItemClickListener = clickListener;
    }

    @NonNull
    @Override
    public PerkRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_perk_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PerkRecyclerAdapter.ViewHolder holder, int position) {
        int currentPosition = holder.getAdapterPosition();
        holder.description.setText(mPerksList.get(currentPosition));
        holder.selectedStatus.setActivated(false);

        holder.selectedStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemClickListener.onItemClick(mPerksList.get(currentPosition));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPerksList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView description;
        CheckBox selectedStatus;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            description = itemView.findViewById(R.id.LayoutPerkRecyclerItem_TextView_Perk);
            selectedStatus = itemView.findViewById(R.id.LayoutPerkRecyclerItem_CheckBox_Status);
        }
    }

    public interface ItemClickListener {
        public void onItemClick(String perk);
    }
}
