package ch.txispa.shaketcg;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import ch.txispa.shaketcg.database.AppDatabase;
import ch.txispa.shaketcg.database.entity.Character;
import ch.txispa.shaketcg.database.entity.UserCharacterCrossRef;

public class CollectionActivity extends AppCompatActivity {
    private static final String TAG = "CollectionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collection_view);

        Button buttonToB = findViewById(R.id.buttonToMain);
        ListView characterListView = findViewById(R.id.character_list);


        try {
            assignCharacterToUser(1,1);
        } catch (SQLiteConstraintException e) {
            Log.e(TAG, "Duplicate relation: " + e.getMessage());
        }

        AsyncTask.execute(() -> {
            List<Character> charactersOwnedByUser = AppDatabase.getInstance(this).userDao().getCharactersByUserId(1);
            runOnUiThread(() -> {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        getCharacterNames(charactersOwnedByUser)
                );

                characterListView.setAdapter(adapter);
            });
        });
        buttonToB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CollectionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void assignCharacterToUser(int userId, int characterId) {
        UserCharacterCrossRef userCharacterCrossRef = new UserCharacterCrossRef();
        userCharacterCrossRef.userId = userId;
        userCharacterCrossRef.characterId = characterId;

        AsyncTask.execute(() -> {
            AppDatabase.getInstance(this).userCharacterCrossRefDao().insert(userCharacterCrossRef);;
        });
    }

    private List<String> getCharacterNames(List<Character> characters) {
        List<String> names = new ArrayList<>();
        for (Character character : characters) {
            names.add(character.name);
        }
        return names;
    }
}
