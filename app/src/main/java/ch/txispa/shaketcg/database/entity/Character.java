package ch.txispa.shaketcg.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Character {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "rarity")
    public String rarity;

    @ColumnInfo(name = "series")
    public String series;

    @ColumnInfo(name = "picture_link")
    public String pictureLink;

    @ColumnInfo(name = "worth")
    public int worth;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRarity() {
        return rarity;
    }

    public String getSeries() {
        return series;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public int getWorth() {
        return worth;
    }
}
