package ch.txispa.shaketcg.service;

import android.app.Service;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.List;
import java.util.Random;

import ch.txispa.shaketcg.database.AppDatabase;
import ch.txispa.shaketcg.database.entity.Character;
import ch.txispa.shaketcg.database.entity.UserCharacterCrossRef;

public class CharacterService extends Service {
    private final IBinder binder = new LocalBinder();

    private static final String TAG = "CharacterService";

    public class LocalBinder extends Binder {
        public CharacterService getService() {
            return CharacterService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public Character randomCharacter() {
        double commonChance = 0.50;
        double rareChance = 0.35;
        double epicChance = 0.12;

        Random random = new Random();
        double randomNumber = random.nextDouble();

        String selectedRarity;
        if (randomNumber < commonChance) {
            selectedRarity = "common";
        } else if (randomNumber < commonChance + rareChance) {
            selectedRarity = "rare";
        } else if (randomNumber < commonChance + rareChance + epicChance) {
            selectedRarity = "epic";
        } else {
            selectedRarity = "legendary";
        }

        List<Character> charactersOfSelectedRarity = AppDatabase.getInstance(this).characterDao().getAllOfRarity(selectedRarity);

        int randomIndex = random.nextInt(charactersOfSelectedRarity.size());
        return charactersOfSelectedRarity.get(randomIndex);
    }


    public void assignCharacterToUser(int userId, int characterId) {
        UserCharacterCrossRef userCharacterCrossRef = new UserCharacterCrossRef();
        userCharacterCrossRef.userId = userId;
        userCharacterCrossRef.characterId = characterId;

        AsyncTask.execute(() -> {
            try {
                AppDatabase.getInstance(this).userCharacterCrossRefDao().insert(userCharacterCrossRef);
                Log.i(TAG, "Character Assigned");
            } catch (SQLiteConstraintException e) {
                Log.e(TAG, "Duplicate relation: " + e.getMessage());
            }
        });
    }

}
