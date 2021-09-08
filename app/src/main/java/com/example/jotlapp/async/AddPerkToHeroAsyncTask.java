package com.example.jotlapp.async;

import android.os.AsyncTask;

import com.example.jotlapp.models.Hero;
import com.example.jotlapp.models.Perk;
import com.example.jotlapp.models.relations.HeroPerkCrossRef;
import com.example.jotlapp.persistence.HeroDao;

public class AddPerkToHeroAsyncTask extends AsyncTask<HeroPerkCrossRef, Void, Void> {

    private HeroDao mHeroDao;

    public AddPerkToHeroAsyncTask(HeroDao dao) {
        mHeroDao = dao;
    }

    @Override
    protected Void doInBackground(HeroPerkCrossRef... crossRefs) {
        mHeroDao.insertHeroPerkCrossRef(crossRefs);
        return null;
    }
}
