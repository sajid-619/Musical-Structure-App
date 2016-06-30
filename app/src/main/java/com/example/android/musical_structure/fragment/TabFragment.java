package com.example.philoniare.musicalstructure.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.philoniare.musicalstructure.R;
import com.example.philoniare.musicalstructure.activity.AlbumViewActivity;
import com.example.philoniare.musicalstructure.activity.ArtistViewActivity;
import com.example.philoniare.musicalstructure.activity.MusicPlayerActivity;
import com.example.philoniare.musicalstructure.activity.PlaylistViewActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class TabFragment extends Fragment {
    private TextView textView;
    private ListView listView;
    private ArrayList<String> items;

    public static TabFragment newInstance(int fragmentType, String description) {
        TabFragment tabFragment = new TabFragment();
        Bundle arguments = new Bundle();
        arguments.putString("description", description);
        arguments.putInt("type", fragmentType);
        tabFragment.setArguments(arguments);
        return tabFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Bundle arguments = this.getArguments();
        View view = inflater.inflate(R.layout.tabs, container, false);
        textView = (TextView) view.findViewById(R.id.textView);
        listView = (ListView) view.findViewById(R.id.listView);
        if (arguments != null) {
            String fragmentDescription = arguments.getString("description");
            textView.setText(fragmentDescription);
            int fragmentType = arguments.getInt("type");

            switch(fragmentType) {
                case 0:
                    // Add playlist items
                    items = new ArrayList<>(Arrays.asList("Playlist 0", "Playlist 1", "Playlist 2"));
                    listView.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent playListIntent = new Intent(getActivity(), PlaylistViewActivity.class);
                            startActivity(playListIntent);
                        }
                    });
                    break;
                case 1:
                    // Add track items
                    items = new ArrayList<>(Arrays.asList("Track 0", "Track 1", "Track 2"));
                    listView.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent musicPlayerIntent = new Intent(getActivity(), MusicPlayerActivity.class);
                            startActivity(musicPlayerIntent);
                        }
                    });
                    break;
                case 2:
                    // Add album items
                    items = new ArrayList<>(Arrays.asList("Album 0", "Album 1", "Album 2"));
                    listView.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent albumIntent = new Intent(getActivity(), AlbumViewActivity.class);
                            startActivity(albumIntent);
                        }
                    });
                    break;
                case 3:
                    // Add artist items
                    items = new ArrayList<>(Arrays.asList("Artist 0", "Artist 1", "Artist 2"));
                    listView.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent artistIntent = new Intent(getActivity(), ArtistViewActivity.class);
                            startActivity(artistIntent);
                        }
                    });
                    break;
                default:
                    items = new ArrayList<>();
                    break;
            }
            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(getActivity(), R.layout.list_item, items);
            listView.setAdapter(itemsAdapter);
        }

        return view;
    }
}
