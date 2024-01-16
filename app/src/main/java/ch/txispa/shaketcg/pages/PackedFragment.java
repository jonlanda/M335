package ch.txispa.shaketcg.pages;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.squareup.picasso.Picasso;

import ch.txispa.shaketcg.R;
import ch.txispa.shaketcg.database.entity.Character;
import ch.txispa.shaketcg.service.CharacterService;
import ch.txispa.shaketcg.service.UserService;

public class PackedFragment extends Fragment {
    private TextView randomCharacterInfoTextView;

    private Button continueButton;
    private Button sellButton;
    private CharacterService characterService;
    private UserService userService;
    private boolean characterBound = false;
    private boolean userBound = false;

    private Character randomCharacter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.packed_fragment, container, false);

        randomCharacterInfoTextView = view.findViewById(R.id.randomCharacterInfoTextView);
        ImageView characterImageView = view.findViewById(R.id.packed_character_image);
        continueButton = view.findViewById(R.id.packed_character_continue);
        sellButton = view.findViewById(R.id.packed_character_sell);

        Bundle args = getArguments();
        if (args != null) {
            randomCharacter = args.getParcelable("randomCharacter");
        }

        if (randomCharacter != null && randomCharacter.getPictureLink() != null) {
            Picasso.get().load(randomCharacter.getPictureLink()).placeholder(R.drawable.collection_icon).into(characterImageView);
        }

        return view;
    }

    private final ServiceConnection connectionCharacter = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            CharacterService.LocalBinder binder = (CharacterService.LocalBinder) service;
            characterService = binder.getService();
            characterBound = true;
            displayRandomCharacter(randomCharacter);
            buttonOptions(randomCharacter);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            characterBound = false;
        }
    };

    private final ServiceConnection connectionUser = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            UserService.LocalBinder binder = (UserService.LocalBinder) service;
            userService = binder.getService();
            userBound = true;
            buttonOptions(randomCharacter);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            userBound = false;
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        Intent intentCharacter = new Intent(getContext(), CharacterService.class);
        getContext().bindService(intentCharacter, connectionCharacter, Context.BIND_AUTO_CREATE);

        Intent intentUser = new Intent(getContext(), UserService.class);
        getContext().bindService(intentUser, connectionUser, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onStop() {
        if (characterBound) {
            getContext().unbindService(connectionCharacter);
            characterBound = false;
        }
        if (userBound) {
            getContext().unbindService(connectionUser);
            userBound = false;
        }
        super.onStop();
    }

    private void displayRandomCharacter(Character randomCharacter) {
        if (characterBound) {
            getActivity().runOnUiThread(() -> {
                if (randomCharacter != null) {
                    String characterInfo = "Name: " + randomCharacter.getName() + "\n" +
                            "Rarity: " + randomCharacter.getRarity() + "\n" +
                            "Series: " + randomCharacter.getSeries();
                    randomCharacterInfoTextView.setText(characterInfo);
                } else {
                    randomCharacterInfoTextView.setText("No characters found in the database.");
                }
            });
        } else {
            randomCharacterInfoTextView.setText("Service not working!");
            Log.e("PackedFragment", "Service not connected");
        }
    }

    private void buttonOptions(Character randomCharacter) {
        continueButton.setOnClickListener(v -> {
            if (isAdded() && characterBound) {
                characterService.assignCharacterToUser(1, randomCharacter.getId());
                navigateToPackFragment();
            }
        });

        sellButton.setOnClickListener(v -> {
            if (isAdded() && userBound) {
                AsyncTask.execute(() -> {
                    int worth = randomCharacter.getWorth();
                    int userAccount = userService.getUser().getMoney();
                    userService.updateMoney(1, worth + userAccount);
                    getActivity().runOnUiThread(() -> {
                        navigateToPackFragment();
                    });
                });
            }
        });

    }

    private void navigateToPackFragment() {
        if(isAdded()) {
            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.action_packedFragment_to_packFragment);
        } else {
            Log.d("PackedFragment", "Fragment not added to its activity.");
        }
    }

}

