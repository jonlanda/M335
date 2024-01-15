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

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import ch.txispa.shaketcg.database.AppDatabase;
import ch.txispa.shaketcg.database.entity.Character;
import ch.txispa.shaketcg.database.entity.UserCharacterCrossRef;

public class CollectionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collection_view);

        ListView characterListView = findViewById(R.id.character_list);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.navigation_collection){
                return true;
            } else if (item.getItemId() == R.id.navigation_home) {
                Intent intent = new Intent(CollectionActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            }
            return false;
        });

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
    }

    private List<String> getCharacterNames(List<Character> characters) {
        List<String> names = new ArrayList<>();
        for (Character character : characters) {
            names.add(character.name);
        }
        return names;
    }
}
