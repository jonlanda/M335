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
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.List;

import ch.txispa.shaketcg.R;
import ch.txispa.shaketcg.customs.CustomArrayAdapter;
import ch.txispa.shaketcg.database.entity.Character;
import ch.txispa.shaketcg.service.UserService;

public class CollectionFragment extends Fragment {
    private UserService userService;
    private boolean mBound = false;

    private static String TAG = "CollectionFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.collection_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = new Intent(getContext(), UserService.class);
        getContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mBound) {
            getContext().unbindService(connection);
            mBound = false;
        }
    }

    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            UserService.LocalBinder binder = (UserService.LocalBinder) service;
            userService = binder.getService();
            mBound = true;
            Log.i(TAG, "Service connected, updating collection");
            updateCollection(getView());
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void updateCollection(View view) {
        ListView characterListView = view.findViewById(R.id.character_list);

        if (mBound) {
            AsyncTask.execute(() -> {
                List<Character> charactersOwnedByUser = userService.getAllCharactersByUserId(1);

                getActivity().runOnUiThread(() -> {
                    CustomArrayAdapter adapter = new CustomArrayAdapter(
                            getContext(),
                            R.layout.list_item_character,
                            charactersOwnedByUser
                    );

                    characterListView.setAdapter(adapter);
                });
            });
        }
    }

}
