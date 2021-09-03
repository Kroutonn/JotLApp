package com.example.jotlapp.async;

import android.os.AsyncTask;

import com.example.jotlapp.models.Item;
import com.example.jotlapp.persistence.HeroDao;

public class AddItemAsyncTask extends AsyncTask<Item, Void, Void> {

    private HeroDao mHeroDao;

    public AddItemAsyncTask(HeroDao dao) {
        mHeroDao = dao;
    }

    @Override
    protected Void doInBackground(Item... items) {
        mHeroDao.insertItem(items);
        return null;
    }

}
