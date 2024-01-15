package ch.txispa.shaketcg;

import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ch.txispa.shaketcg.database.AppDatabase;
import ch.txispa.shaketcg.database.entity.Character;
import ch.txispa.shaketcg.database.entity.UserCharacterCrossRef;

public class MainActivity extends AppCompatActivity {
    private TextView randomCharacterInfoTextView;

    CharacterService characterService;
    Boolean mBound = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.navigation_home){
                return true;
            } else if (item.getItemId() == R.id.navigation_collection) {
                Intent intent = new Intent(MainActivity.this, CollectionActivity.class);
                startActivity(intent);
                return true;
            }
            return false;
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, CharacterService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);

        randomCharacterInfoTextView = findViewById(R.id.randomCharacterInfoTextView);
        Button randomCharacterButton = findViewById(R.id.randomCharacterButton);

        randomCharacterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask.execute(() -> {
                    displayRandomCharacter();
                });
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
        mBound = false;
    }

    private void displayRandomCharacter() {
        if (mBound) {
            Character randomCharacter = characterService.randomCharacter();

            runOnUiThread(() -> {
                if (randomCharacter != null) {
                    String characterInfo = "Name: " + randomCharacter.getName() + "\n";
                    characterInfo += "Rarity: " + randomCharacter.getRarity() + "\n";
                    characterInfo += "Series: " + randomCharacter.getSeries();

                    randomCharacterInfoTextView.setText(characterInfo);
                    characterService.assignCharacterToUser(1, randomCharacter.getId());
                } else {
                    randomCharacterInfoTextView.setText("No characters found in the database.");
                }
            });
        } else {
            randomCharacterInfoTextView.setText("Service not working!");
        }
    }

    private final ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            CharacterService.LocalBinder binder = (CharacterService.LocalBinder) service;
            characterService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };


}
