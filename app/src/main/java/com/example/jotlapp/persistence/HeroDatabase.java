package com.example.jotlapp.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.jotlapp.models.Hero;
import com.example.jotlapp.models.Item;
import com.example.jotlapp.models.Perk;
import com.example.jotlapp.models.relations.HeroItemCrossRef;
import com.example.jotlapp.models.relations.HeroPerkCrossRef;

@Database(entities = {
            Hero.class,
            Item.class,
            Perk.class,
            HeroItemCrossRef.class,
            HeroPerkCrossRef.class
        },
        version = 1
)
public abstract class HeroDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "hero_db";

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
