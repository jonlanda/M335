package ch.txispa.shaketcg.pages;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import ch.txispa.shaketcg.R;
import ch.txispa.shaketcg.database.entity.Character;
import ch.txispa.shaketcg.service.CharacterService;

public class PackedFragment extends Fragment {
    private TextView randomCharacterInfoTextView;

    private CharacterService characterService;
    private boolean mBound = false;

    private Character randomCharacter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.packed_fragment, container, false);

        randomCharacterInfoTextView = view.findViewById(R.id.randomCharacterInfoTextView);

        Bundle args = getArguments();
        if (args != null) {
            randomCharacter = args.getParcelable("randomCharacter");
        }

        displayRandomCharacter(randomCharacter);
        return view;
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

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = new Intent(getContext(), CharacterService.class);
        getContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onStop() {
        if (mBound) {
            getContext().unbindService(connection);
            mBound = false;
        }
        super.onStop();
    }

    private void displayRandomCharacter(Character randomCharacter) {
        if (mBound) {
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

