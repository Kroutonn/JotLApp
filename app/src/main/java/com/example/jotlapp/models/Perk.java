package com.example.jotlapp.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Perk {

    @PrimaryKey
    private int id;

    @NonNull
    @ColumnInfo(name = "description")
    private String description;

    public Perk(@NonNull String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Perk{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
