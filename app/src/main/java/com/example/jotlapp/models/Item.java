package com.example.jotlapp.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "items")
public class Item {

    @PrimaryKey
    private int itemId;

    @NonNull
    @ColumnInfo(name="name")
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                '}';
    }
}
