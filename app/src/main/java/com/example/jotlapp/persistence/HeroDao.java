package com.example.jotlapp.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.jotlapp.models.Hero;
import com.example.jotlapp.models.Perk;
import com.example.jotlapp.models.relations.HeroPerkCrossRef;
import com.example.jotlapp.models.relations.HeroWithItem;
import com.example.jotlapp.models.relations.HeroWithPerk;

import java.util.List;

@Dao
public interface HeroDao {

    // Insert methods
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertHero(Hero... heroes); // Returns array of rows inserted. -1 if false

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHeroPerkCrossRef(HeroPerkCrossRef... crossRef);

    // Query methods
    @Query("SELECT * FROM heroes")
    LiveData<List<Hero>> getHeroes();

    @Query("SELECT * FROM heroes WHERE heroId = :id")
    List<Hero> getHeroesWithCustomQuery(String id);

    @Query("SELECT EXISTS(SELECT * FROM HeroPerkCrossRef WHERE heroId = :heroId AND perkId = :perkId)")
    boolean heroHasPerk(int heroId, int perkId);

    @Query("SELECT * FROM perks WHERE character = :character")
    LiveData<List<Perk>> getPerksForCharacter(String character);

    @Transaction
    @Query("SELECT * FROM heroes WHERE heroId = :id")
    public List<HeroWithItem> getHeroWithItems(String id);

    @Transaction
    @Query("SELECT * FROM heroes WHERE heroId = :id")
    public List<HeroWithPerk> getHeroWithPerks(String id);

    @Delete
    void delete(Hero... heroes);

    @Update
    void update(Hero... heroes);
}
