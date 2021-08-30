package com.example.jotlapp.persistence;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.jotlapp.async.InsertAsyncTask;
import com.example.jotlapp.models.Hero;

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

    }

    public LiveData<List<Hero>> retrieveHeroesTask() {
        return mHeroDatabase.getHeroDao().getHeroes();
    }

    public void deleteHero(Hero hero) {

    }
}
