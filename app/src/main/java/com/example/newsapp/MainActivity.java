package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private static final int homeFragment = 1;
    private static final int bookmarksFragment = 2;
    private int currentFragment;

    private ImageButton homeBtn;
    private ImageButton bookmarkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeBtn = findViewById(R.id.homeBtn);
        bookmarkBtn = findViewById(R.id.bookmarkBtn);


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, new HomeFragment(getApplicationContext()));
        ft.commit();
        currentFragment = homeFragment;
    }

    @Override
    protected void onResume() {
        super.onResume();

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentFragment != homeFragment) {
                    currentFragment = homeFragment;

                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.container, new HomeFragment(getApplicationContext()));
                    ft.commit();
                }

            }
        });

        bookmarkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentFragment != bookmarksFragment) {
                    currentFragment = bookmarksFragment;
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.container, new BookmarksFragment(getApplicationContext()));
                    ft.commit();
                }
            }
        });
    }

}