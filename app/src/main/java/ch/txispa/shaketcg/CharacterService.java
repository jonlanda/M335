package ch.txispa.shaketcg;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.List;
import java.util.Random;

import ch.txispa.shaketcg.database.AppDatabase;
import ch.txispa.shaketcg.database.entity.Character;

public class CharacterService extends Service {
    private final IBinder binder = new LocalBinder();

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
}
