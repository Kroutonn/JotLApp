package com.example.jotlapp.persistence;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.jotlapp.async.AddItemToHeroAsyncTask;
import com.example.jotlapp.async.AddPerkToHeroAsyncTask;
import com.example.jotlapp.async.DeleteHeroAsyncTask;
import com.example.jotlapp.async.InsertAsyncTask;
import com.example.jotlapp.async.UpdateHeroAsyncTask;
import com.example.jotlapp.models.Hero;
import com.example.jotlapp.models.Item;
import com.example.jotlapp.models.Perk;
import com.example.jotlapp.models.relations.HeroItemCrossRef;
import com.example.jotlapp.models.relations.HeroPerkCrossRef;
import com.example.jotlapp.models.relations.HeroWithItem;

import java.util.List;

public class HeroRepository {

    private HeroDatabase mHeroDatabase;

    public HeroRepository(Context context) {
        mHeroDatabase = HeroDatabase.getInstance(context);
    }

    public void insertHeroTask(Hero hero) {
        new InsertAsyncTask(mHeroDatabase.getHeroDao()).execute(hero);
    }

    public void updateHero(Hero hero) {
        new UpdateHeroAsyncTask(mHeroDatabase.getHeroDao()).execute(hero);
    }

    public void addHeroPerkCrossRef(HeroPerkCrossRef crossRef) {
        new AddPerkToHeroAsyncTask(mHeroDatabase.getHeroDao()).execute(crossRef);
    }

    public void addHeroItemCrossRef(HeroItemCrossRef crossRef) {
        new AddItemToHeroAsyncTask(mHeroDatabase.getHeroDao()).execute(crossRef);
    }

    public LiveData<List<Hero>> retrieveHeroesTask() {
        return mHeroDatabase.getHeroDao().getHeroes();
    }

    public List<Item> retrieveItemsTask() {
        return mHeroDatabase.getHeroDao().getItems();
    }

    public LiveData<List<Item>> retrieveLiveItemsTask() {
        return mHeroDatabase.getHeroDao().getItemsLive();
    }

    public Item getItemObject(String itemName) {
        return mHeroDatabase.getHeroDao().getItemsByName(itemName);
    }

    public LiveData<List<Perk>> getPerksTask(String character) {
        return mHeroDatabase.getHeroDao().getPerksForCharacter(character);
    }

    public boolean heroHasPerk(int heroId, int perkId) {
        return mHeroDatabase.getHeroDao().heroHasPerk(heroId, perkId);
    }

    public LiveData<List<HeroWithItem>> getItemsForHero(int heroId) {
        return mHeroDatabase.getHeroDao().getHeroWithItems(heroId);
    }

    public void deleteHero(Hero hero) {
        new DeleteHeroAsyncTask(mHeroDatabase.getHeroDao()).execute(hero);
    }
}
