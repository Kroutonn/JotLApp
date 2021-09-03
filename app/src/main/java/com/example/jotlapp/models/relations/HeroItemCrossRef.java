package com.example.jotlapp.models.relations;

import androidx.room.Entity;

@Entity(primaryKeys = {"heroId", "itemId"})
public class HeroItemCrossRef {
    public int heroId;
    public int itemId;
}
