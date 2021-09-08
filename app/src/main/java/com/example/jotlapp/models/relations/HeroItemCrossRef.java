package com.example.jotlapp.models.relations;

import androidx.room.Entity;

@Entity(primaryKeys = {"heroId", "itemId"})
public class HeroItemCrossRef {
    public int heroId;
    public int itemId;

    public HeroItemCrossRef(int heroId, int itemId) {
        this.heroId = heroId;
        this.itemId = itemId;
    }

    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "HeroItemCrossRef{" +
                "heroId=" + heroId +
                ", itemId=" + itemId +
                '}';
    }
}
