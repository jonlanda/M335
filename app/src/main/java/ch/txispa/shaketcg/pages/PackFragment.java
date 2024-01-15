package ch.txispa.shaketcg.pages;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import ch.txispa.shaketcg.customs.ShakeDetector;
import ch.txispa.shaketcg.service.CharacterService;
import ch.txispa.shaketcg.R;
import ch.txispa.shaketcg.database.entity.Character;

public class PackFragment extends Fragment {
    private TextView randomCharacterInfoTextView;
    private CharacterService characterService;
    private boolean mBound = false;
    private ShakeDetector shakeDetector;
    private SensorManager sensorManager;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pack_fragment, container, false);

        randomCharacterInfoTextView = view.findViewById(R.id.randomCharacterInfoTextView);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        shakeDetector = new ShakeDetector();

        shakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake() {
                AsyncTask.execute(() -> {
                    displayRandomCharacter();
                });
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = new Intent(getContext(), CharacterService.class);
        getContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(shakeDetector, accelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onStop() {
        if (mBound) {
            getContext().unbindService(connection);
            mBound = false;
        }
        sensorManager.unregisterListener(shakeDetector);
        super.onStop();

    }

    private void displayRandomCharacter() {
        if (mBound) {
            Character randomCharacter = characterService.randomCharacter();

            getActivity().runOnUiThread(() -> {
                if (randomCharacter != null) {
                    String characterInfo = "Name: " + randomCharacter.getName() + "\n" +
                            "Rarity: " + randomCharacter.getRarity() + "\n" +
                            "Series: " + randomCharacter.getSeries();
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
}
