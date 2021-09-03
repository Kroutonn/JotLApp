package com.example.jotlapp.models.relations;


import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.jotlapp.models.Hero;
import com.example.jotlapp.models.Perk;

import java.util.List;

public class HeroWithPerk {
    @Embedded public Hero hero;
    @Relation(
            parentColumn = "heroId",
            entityColumn = "perkId",
            associateBy = @Junction(HeroPerkCrossRef.class)
    )
    public List<Perk> perks;
}
