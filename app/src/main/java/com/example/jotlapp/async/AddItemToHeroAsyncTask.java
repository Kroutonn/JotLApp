package com.example.jotlapp.async;

import android.os.AsyncTask;

import com.example.jotlapp.models.relations.HeroItemCrossRef;
import com.example.jotlapp.models.relations.HeroPerkCrossRef;
import com.example.jotlapp.persistence.HeroDao;

public class AddItemToHeroAsyncTask extends AsyncTask<HeroItemCrossRef, Void, Void> {

    private HeroDao mHeroDao;

    public AddItemToHeroAsyncTask(HeroDao dao) {
        mHeroDao = dao;
    }

    @Override
    protected Void doInBackground(HeroItemCrossRef... crossRefs) {
        mHeroDao.insertHeroItemCrossRef(crossRefs);
        return null;
    }
}
