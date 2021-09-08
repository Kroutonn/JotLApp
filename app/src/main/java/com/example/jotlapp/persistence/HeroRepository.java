package com.example.jotlapp.persistence;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.jotlapp.async.InsertAsyncTask;
import com.example.jotlapp.async.UpdateHeroAsyncTask;
import com.example.jotlapp.models.Hero;
import com.example.jotlapp.models.Perk;
import com.example.jotlapp.models.relations.HeroPerkCrossRef;

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
        mHeroDatabase.getHeroDao().insertHeroPerkCrossRef(crossRef);
    }

    public LiveData<List<Hero>> retrieveHeroesTask() {
        return mHeroDatabase.getHeroDao().getHeroes();
    }

    public LiveData<List<Perk>> getPerksTask(String character) {
        return mHeroDatabase.getHeroDao().getPerksForCharacter(character);
    }

    public void deleteHero(Hero hero) {

    }
}
