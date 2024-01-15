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
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import ch.txispa.shaketcg.customs.ShakeDetector;
import ch.txispa.shaketcg.service.CharacterService;
import ch.txispa.shaketcg.R;
import ch.txispa.shaketcg.database.entity.Character;

public class PackFragment extends Fragment {
    private CharacterService characterService;
    private boolean mBound = false;
    private ShakeDetector shakeDetector;
    private SensorManager sensorManager;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pack_fragment, container, false);

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

            requireActivity().runOnUiThread(() -> {
                if (isAdded()) {
                    if (randomCharacter != null) {
                        PackedFragment targetFragment = new PackedFragment();

                        Bundle args = new Bundle();
                        args.putParcelable("randomCharacter", randomCharacter);
                        targetFragment.setArguments(args);

                        NavController navController = NavHostFragment.findNavController(this);
                        navController.navigate(R.id.action_packFragment_to_packedFragment, args);
                    }
                } else {
                    Log.d("PackFragment", "Fragment not added to its activity.");
                }
            });
        } else {
            Log.e("PackFragment", "Service not bound");
        }
    }


}
