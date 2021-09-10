package com.example.jotlapp.models.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.jotlapp.models.Hero;
import com.example.jotlapp.models.Item;

import java.util.List;

public class HeroWithItem {
    @Embedded
    public Hero hero;

    @Relation(parentColumn = "heroId",
    entityColumn = "itemId",
    associateBy = @Junction(HeroItemCrossRef.class)
    )
    public List<Item> items;

    public List<Item> getItems() {
        return items;
    }
}
