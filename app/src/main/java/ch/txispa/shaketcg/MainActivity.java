package ch.txispa.shaketcg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ch.txispa.shaketcg.database.AppDatabase;
import ch.txispa.shaketcg.database.entity.Character;
import ch.txispa.shaketcg.database.entity.UserCharacterCrossRef;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout characterContainer = findViewById(R.id.character_container);

        AsyncTask.execute(() -> {
            List<Character> allCharacters = AppDatabase.getInstance(this).characterDao().getAll();
            runOnUiThread(() -> {
                for (Character character : allCharacters) {
                    TextView characterName = new TextView(this);
                    characterName.setText(character.name);
                    characterName.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    characterName.setPadding(10, 10, 10, 10); // Optional for padding

                    characterContainer.addView(characterName);
                }
            });
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
}
