package com.example.jotlapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "heroes")
public class Hero implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int heroId;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "level")
    private String level;

    @NonNull
    @ColumnInfo(name = "experiance")
    private String experiance;

    @NonNull
    @ColumnInfo(name = "gold")
    private String gold;

    @NonNull
    @ColumnInfo(name = "character")
    private String character;

    @NonNull
    @ColumnInfo(name = "notes")
    private int notes;

    public Hero(String name, String level, String experiance, String gold, String character, int notes) {
        this.name = name;
        this.level = level;
        this.experiance = experiance;
        this.gold = gold;
        this.character = character;
        this.notes = notes;
    }

    @Ignore
    public Hero() {

    }

    protected Hero(Parcel in) {
        heroId = in.readInt();
        name = in.readString();
        level = in.readString();
        experiance = in.readString();
        gold = in.readString();
        character = in.readString();
        notes = in.readInt();
    }

    public static final Creator<Hero> CREATOR = new Creator<Hero>() {
        @Override
        public Hero createFromParcel(Parcel in) {
            return new Hero(in);
        }

        @Override
        public Hero[] newArray(int size) {
            return new Hero[size];
        }
    };

    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getExperiance() {
        return experiance;
    }

    public void setExperiance(String experiance) {
        this.experiance = experiance;
    }

    public String getGold() {
        return gold;
    }

    public void setGold(String gold) {
        this.gold = gold;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public int getNotes() {
        return notes;
    }

    public void setNotes(int notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "heroId=" + heroId +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", experiance='" + experiance + '\'' +
                ", gold='" + gold + '\'' +
                ", character='" + character + '\'' +
                ", notes=" + notes +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(heroId);
        parcel.writeString(name);
        parcel.writeString(level);
        parcel.writeString(experiance);
        parcel.writeString(gold);
        parcel.writeString(character);
        parcel.writeInt(notes);
    }
}
