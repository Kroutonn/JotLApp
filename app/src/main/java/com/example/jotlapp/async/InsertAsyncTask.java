package com.example.jotlapp.async;

import android.os.AsyncTask;

import com.example.jotlapp.models.Hero;
import com.example.jotlapp.persistence.HeroDao;

public class InsertAsyncTask extends AsyncTask<Hero, Void, Void> {

    private HeroDao mHeroDao;

    public InsertAsyncTask(HeroDao dao) {
        mHeroDao = dao;
    }

    @Override
    protected Void doInBackground(Hero... heroes) {
        mHeroDao.insertHero(heroes);
        return null;
    }
}
