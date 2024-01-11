package ch.txispa.shaketcg.database.entity;

import android.os.AsyncTask;

import androidx.room.Entity;

import ch.txispa.shaketcg.database.AppDatabase;

@Entity(primaryKeys = {"userId", "characterId"})
public class UserCharacterCrossRef {
    public int userId;
    public int characterId;
}

