package com.example.jotlapp.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "perks")
public class Perk {

    @PrimaryKey
    private int perkId;

    @NonNull
    @ColumnInfo(name = "character")
    private String character;

    @NonNull
    @ColumnInfo(name = "description")
    private String description;

    public Perk(@NonNull String description) {
        this.description = description;
    }

    public int getPerkId() {
        return perkId;
    }

    public void setPerkId(int perkId) {
        this.perkId = perkId;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    @NonNull
    public String getCharacter() {
        return character;
    }

    public void setCharacter(@NonNull String character) {
        this.character = character;
    }

    @Override
    public String toString() {
        return "Perk{" +
                "perkId=" + perkId +
                ", character='" + character + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
