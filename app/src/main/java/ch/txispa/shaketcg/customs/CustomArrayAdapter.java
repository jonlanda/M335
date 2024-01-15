package ch.txispa.shaketcg.customs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.util.List;

import ch.txispa.shaketcg.R;

public class CustomArrayAdapter extends ArrayAdapter<Character> {
    private Context mContext;
    private int mResource;

    public CustomArrayAdapter(Context context, int resource, List<Character> characters) {
        super(context, resource, characters);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(mResource, parent, false);
        }

        ImageView characterImageView = convertView.findViewById(R.id.character_image);
        TextView characterNameTextView = convertView.findViewById(R.id.character_name);
        TextView characterSeriesTextView = convertView.findViewById(R.id.character_series);
        TextView characterRarityTextView = convertView.findViewById(R.id.character_rarity);
        TextView characterWorthTextView = convertView.findViewById(R.id.character_worth);

        Character character = getItem(position);

        if (character != null) {
            Picasso.get().load(character.getImageLink()).placeholder(R.drawable.collection_icon).into(characterImageView);
            characterNameTextView.setText(character.getName());
            characterSeriesTextView.setText("Series: " + character.getSeries());
            characterRarityTextView.setText("Rarity: " + character.getRarity());
            characterWorthTextView.setText("Worth: $" + character.getWorth());
        }

        return convertView;
    }
}
