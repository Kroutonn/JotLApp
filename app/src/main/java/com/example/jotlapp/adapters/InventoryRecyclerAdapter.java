package com.example.jotlapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jotlapp.R;

import java.util.ArrayList;

public class InventoryRecyclerAdapter extends RecyclerView.Adapter<InventoryRecyclerAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemName;
        TextView itemNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.LayoutInventoryRecyclerItem_TextView_Name);
            itemNumber = itemView.findViewById(R.id.LayoutInventoryRecyclerItem_TextView_Number);
        }
    }

    private ArrayList<String> mItemList;

    public InventoryRecyclerAdapter(ArrayList<String> itemList) {
        this.mItemList = itemList;
    }

    @NonNull
    @Override
    public InventoryRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_inventory_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryRecyclerAdapter.ViewHolder holder, int position) {
        int currentPosition = holder.getAdapterPosition();

        holder.itemName.setText(mItemList.get(currentPosition));
        holder.itemNumber.setText(Integer.toString(currentPosition));
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}
