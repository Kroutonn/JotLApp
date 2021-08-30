package com.example.jotlapp.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.jotlapp.models.Hero;

import java.util.List;

@Dao
public interface HeroDao {

    @Insert
    Long[] insertHero(Hero... heroes); // Returns array of rows inserted. -1 if false

    @Query("SELECT * FROM heroes")
    LiveData<List<Hero>> getHeroes();

    @Query("SELECT * FROM heroes WHERE id = :id")
    List<Hero> getHeroesWithCustomQuery(String id);

    @Delete
    void delete(Hero... heroes);

    @Update
    void update(Hero... heroes);
}
