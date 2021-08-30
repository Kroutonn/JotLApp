package com.example.jotlapp.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.jotlapp.models.Hero;

@Database(entities = {Hero.class}, version = 1)
public abstract class HeroDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "heroes_db";

    private static HeroDatabase instance;

    static HeroDatabase getInstance(final Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    HeroDatabase.class,
                    DATABASE_NAME
            ).build();
        }
        return instance;
    }

    public abstract HeroDao getHeroDao();
}
