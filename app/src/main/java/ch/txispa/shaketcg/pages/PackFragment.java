package ch.txispa.shaketcg.pages;

import android.app.AlertDialog;
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
import ch.txispa.shaketcg.database.entity.User;
import ch.txispa.shaketcg.service.CharacterService;
import ch.txispa.shaketcg.R;
import ch.txispa.shaketcg.database.entity.Character;
import ch.txispa.shaketcg.service.UserService;

public class PackFragment extends Fragment {
    private CharacterService characterService;
    private boolean mBound = false;
    private ShakeDetector shakeDetector;
    private SensorManager sensorManager;
    private UserService userService;

    private boolean userBound = false;




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

    private final ServiceConnection connectionUser = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            UserService.LocalBinder binder = (UserService.LocalBinder) service;
            userService = binder.getService();
            userBound = true;
            displayMoney();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            userBound = false;
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = new Intent(getContext(), CharacterService.class);
        getContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);

        Intent intentUser = new Intent(getContext(), UserService.class);
        getContext().bindService(intentUser, connectionUser, Context.BIND_AUTO_CREATE);

        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(shakeDetector, accelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onStop() {
        if (mBound) {
            getContext().unbindService(connection);
            mBound = false;
        }
        if (userBound) {
            getContext().unbindService(connectionUser);
            userBound = false;
        }
        sensorManager.unregisterListener(shakeDetector);
        super.onStop();
    }

    private void displayRandomCharacter() {
        AsyncTask.execute(() -> {
            int userAccount = userService.getUser().getMoney();

            if(userAccount > 100) {
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
                                AsyncTask.execute(() -> {
                                    userService.updateMoney(1, userAccount - 100);
                                });
                            }
                        } else {
                            Log.d("PackFragment", "Fragment not added to its activity.");
                        }
                    });
                } else {
                    Log.e("PackFragment", "Service not bound");
                }
            } else {
                requireActivity().runOnUiThread(() -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("You don't have enough money");
                    builder.setMessage("Try and sell some characters or wait until you get more money");

                    builder.setPositiveButton("OK", (dialog, which) -> {
                        dialog.cancel();
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                });
            }
        });
    }

    public void displayMoney() {
        TextView money = getView().findViewById(R.id.moneyAccount);
        AsyncTask.execute(() -> {
            User user = userService.getUser();
            int accountWorth = user.getMoney();
            getActivity().runOnUiThread(() -> {
                money.setText("You have: $" + accountWorth);
            });
        });
    }
}
