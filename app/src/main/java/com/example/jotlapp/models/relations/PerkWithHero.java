package com.example.jotlapp.models.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.jotlapp.models.Hero;
import com.example.jotlapp.models.Perk;

import java.util.List;

public class PerkWithHero {

    @Embedded
    public Perk perk;

    @Relation(
            parentColumn = "perkId",
            entityColumn = "heroId",
            associateBy = @Junction(HeroPerkCrossRef.class)
    )
    public List<Hero> heroes;
}
