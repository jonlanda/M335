package ch.txispa.shaketcg.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Character {
    @PrimaryKey(autoGenerate = true)
    public int characterId;

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
}
