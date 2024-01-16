package ch.txispa.shaketcg.database.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Character implements Parcelable {
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

    public Character() {
        // Empty constructor is required
    }

    protected Character(Parcel in) {
        id = in.readInt();
        name = in.readString();
        rarity = in.readString();
        series = in.readString();
        pictureLink = in.readString();
        worth = in.readInt();
    }

    public static final Creator<Character> CREATOR = new Creator<Character>() {
        @Override
        public Character createFromParcel(Parcel in) {
            return new Character(in);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(rarity);
        dest.writeString(series);
        dest.writeString(pictureLink);
        dest.writeInt(worth);
    }

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
