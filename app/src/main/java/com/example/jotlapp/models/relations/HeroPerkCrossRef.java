package com.example.jotlapp.models.relations;

import androidx.room.Entity;

@Entity(primaryKeys = {"heroId", "perkId"})
public class HeroPerkCrossRef {
    public int heroId;
    public int perkId;

    public HeroPerkCrossRef(int heroId, int perkId) {
        this.heroId = heroId;
        this.perkId = perkId;
    }

    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }

    @Override
    public String toString() {
        return "HeroPerkCrossRef{" +
                "heroId=" + heroId +
                ", perkId=" + perkId +
                '}';
    }
}
