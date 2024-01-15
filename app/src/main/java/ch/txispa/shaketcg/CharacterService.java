package ch.txispa.shaketcg;

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
        CharacterService getService() {
            return CharacterService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public Character randomCharacter() {
        List<Character> allCharacters = AppDatabase.getInstance(this).characterDao().getAll();

        Random random = new Random();
        int randomIndex = random.nextInt(allCharacters.size());

        return allCharacters.get(randomIndex);
    }

    public void assignCharacterToUser(int userId, int characterId) {
        UserCharacterCrossRef userCharacterCrossRef = new UserCharacterCrossRef();
        userCharacterCrossRef.userId = userId;
        userCharacterCrossRef.characterId = characterId;

        AsyncTask.execute(() -> {
            try {
                AppDatabase.getInstance(this).userCharacterCrossRefDao().insert(userCharacterCrossRef);
            } catch (SQLiteConstraintException e) {
                Log.e(TAG, "Duplicate relation: " + e.getMessage());
            }
        });
    }
}
