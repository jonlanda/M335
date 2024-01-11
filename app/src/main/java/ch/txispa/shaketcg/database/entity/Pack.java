package ch.txispa.shaketcg.database.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;

public class Pack {

    public String name;

    public double chanceCommon = 5.0;

    public double chanceRare = 3.5;

    public double chanceEpic = 1.2;

    public double chanceLegendary = 0.3;

    public int price = 100;

    public Character character;

    public Timestamp freeTimeStamp;

    public Pack(String name, Character character, Timestamp freeTimeStamp) {
        this.name = name;
        this.character = character;
        this.freeTimeStamp = freeTimeStamp;
    }

    public String getName() {
        return name;
    }

    public double getChanceCommon() {
        return chanceCommon;
    }

    public double getChanceRare() {
        return chanceRare;
    }

    public double getChanceEpic() {
        return chanceEpic;
    }

    public double getChanceLegendary() {
        return chanceLegendary;
    }

    public int getPrice() {
        return price;
    }

    public Character getCharacter() {
        return character;
    }

    public Timestamp getFreeTimeStamp() {
        return freeTimeStamp;
    }
}
