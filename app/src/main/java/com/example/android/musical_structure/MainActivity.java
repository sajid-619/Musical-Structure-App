package com.example.philoniare.musicalstructure;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.philoniare.musicalstructure.adapter.SmartFragmentStatePagerAdapter;
import com.example.philoniare.musicalstructure.fragment.TabFragment;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        setSupportActionBar(toolbar);

        // Create & Add tabs to the TabLayout
        final TabLayout.Tab playlistsTab = tabLayout.newTab();
        final TabLayout.Tab tracksTab = tabLayout.newTab();
        final TabLayout.Tab albumsTab = tabLayout.newTab();
        final TabLayout.Tab artistsTab = tabLayout.newTab();
        final TabLayout.Tab genresTab = tabLayout.newTab();

        playlistsTab.setText("PLAYLISTS");
        tracksTab.setText("TRACKS");
        albumsTab.setText("ALBUMS");
        artistsTab.setText("ARTISTS");

        tabLayout.addTab(playlistsTab, 0);
        tabLayout.addTab(tracksTab, 1);
        tabLayout.addTab(albumsTab, 2);
        tabLayout.addTab(artistsTab, 3);

        tabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.color.tab_selector));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.indicator));
        //tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    public static class ViewPagerAdapter extends SmartFragmentStatePagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return TabFragment.newInstance(position, "Will display playlists");
                case 1:
                    return TabFragment.newInstance(position, "Will display tracks");
                case 2:
                    return TabFragment.newInstance(position, "Will display albums");
                case 3:
                    return TabFragment.newInstance(position, "Will display artists");
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
