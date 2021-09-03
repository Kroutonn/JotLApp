package com.example.jotlapp.models.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.jotlapp.models.Hero;
import com.example.jotlapp.models.Item;

import java.util.List;

public class ItemWithHero {
    @Embedded
    public Item item;

    @Relation(parentColumn = "itemId",
            entityColumn = "studentId",
            associateBy = @Junction(HeroItemCrossRef.class)
    )
    public List<Hero> heroes;
}
