package com.example.jotlapp.async;

import android.os.AsyncTask;

import com.example.jotlapp.models.Hero;
import com.example.jotlapp.models.Item;
import com.example.jotlapp.persistence.HeroDao;

public class UpdateHeroAsyncTask extends AsyncTask<Hero, Void, Void> {

    private HeroDao mHeroDao;

    public UpdateHeroAsyncTask(HeroDao dao) {
        mHeroDao = dao;
    }

    @Override
    protected Void doInBackground(Hero... heroes) {
        mHeroDao.update(heroes);
        return null;
    }
}
