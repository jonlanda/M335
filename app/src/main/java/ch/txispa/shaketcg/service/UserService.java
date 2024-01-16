package ch.txispa.shaketcg.service;

import android.app.Service;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ch.txispa.shaketcg.database.AppDatabase;
import ch.txispa.shaketcg.database.entity.Character;
import ch.txispa.shaketcg.database.entity.User;
import ch.txispa.shaketcg.database.entity.UserCharacterCrossRef;

public class UserService extends Service {
    private final IBinder binder = new LocalBinder();
    private final static String TAG = "UserService";

    public class LocalBinder extends Binder {
        public UserService getService() {
            return UserService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public List<Character> getAllCharactersByUserId(int id) {
        try{
            return AppDatabase.getInstance(this).userDao().getCharactersByUserId(1);
        } catch (SQLiteConstraintException e) {
            Log.e(TAG, "Error while getting characters: " + e.getMessage());
            return null;
        }
    }

    public User getUser() {
        try {
            return AppDatabase.getInstance(this).userDao().findByUsername("user");
        } catch (SQLiteConstraintException e) {
            Log.e(TAG, "Error while getting user: " + e.getMessage());
            return null;
        }
    }

    public List<String> getOwnedCharacterNames(List<Character> characters) {
        List<String> names = new ArrayList<>();
        for (Character character : characters) {
            names.add(character.name);
        }
        return names;
    }

    public void updateMoney(int id, int amount) {
        try{
            AppDatabase.getInstance(this).userDao().updateMoney(id, amount);
        } catch (SQLiteConstraintException e) {
            Log.e(TAG, "Error while updating money: " + e.getMessage());
        }
    }

    public void sellCharacter(int characterId) {
        try{
            UserCharacterCrossRef deletedRef = AppDatabase.getInstance(this).userCharacterCrossRefDao().getUserCharacterCrossRef(1, characterId);
            AppDatabase.getInstance(this).userCharacterCrossRefDao().delete(deletedRef);
            Log.e(TAG, "sold character with id: " + characterId);
        } catch (SQLiteConstraintException e) {
            Log.e(TAG, "Error while selling character: " + e.getMessage());
        }
    }
}
