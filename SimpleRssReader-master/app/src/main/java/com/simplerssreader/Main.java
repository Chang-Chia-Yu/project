package com.simplerssreader;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import static com.simplerssreader.RssFragment.LINK_RES;


public class Main extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        if (savedInstanceState == null) {
            addRssFragment();
        }
    }

    private void addRssFragment() {
        String link = getIntent().getStringExtra(LINK_RES);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        RssFragment fragment = RssFragment.getInstance(link);
        transaction.add(R.id.fragment_container, fragment);
        transaction.commit();
    }
}