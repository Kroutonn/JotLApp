package com.example.jotlapp.async;

import android.os.AsyncTask;

import com.example.jotlapp.models.Hero;
import com.example.jotlapp.persistence.HeroDao;

public class DeleteHeroAsyncTask extends AsyncTask<Hero, Void, Void> {

    private HeroDao mHeroDao;

    public DeleteHeroAsyncTask(HeroDao dao) {
        mHeroDao = dao;
    }

    @Override
    protected Void doInBackground(Hero... hero) {
        mHeroDao.delete(hero);
        return null;
    }
}
