package com.example.jotlapp.models.relations;

import androidx.room.Entity;

@Entity(primaryKeys = {"heroId", "perkId"})
public class HeroPerkCrossRef {
    public int heroId;
    public int perkId;
}
