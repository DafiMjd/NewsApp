package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.newsapp.newsapi.NewsApiClient;
import com.example.newsapp.newsapi.models.Article;
import com.example.newsapp.newsapi.models.ArticleAdapter;
import com.example.newsapp.newsapi.models.request.EverythingRequest;
import com.example.newsapp.newsapi.models.request.TopHeadlinesRequest;
import com.example.newsapp.newsapi.models.response.ArticleResponse;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int homeFragment = 1;
    private static final int bookmarksFragment = 2;
    private int currentFragment;

    private EditText searchBar;
    private ImageButton searchBtn;

    private ImageButton homeBtn;
    private ImageButton bookmarkBtn;
    private ImageView status;

    private List<Article> articleList;
    private RecyclerView newsRV;
    private ArticleAdapter articleAdapter;

    private String searchKey;

    private NewsApiClient newsApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeBtn = findViewById(R.id.homeBtn);
        bookmarkBtn = findViewById(R.id.bookmarkBtn);
        newsRV = findViewById(R.id.newsRV);
        status = findViewById(R.id.status);

        searchBar = findViewById(R.id.searchNews);
        searchBtn = findViewById(R.id.searchBtn);

        searchKey = "";

        newsApiClient = new NewsApiClient("45b104ac780141b6b74bc9fdc536e402");
        getEverything(newsApiClient);


//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.replace(R.id.container, new HomeFragment(getApplicationContext()));
//        ft.commit();
//        currentFragment = homeFragment;
    }

    @Override
    protected void onResume() {
        super.onResume();

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchKey = searchBar.getText().toString();
                getTopHeadLines(newsApiClient);
            }
        });

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


    private void getEverything(NewsApiClient newsApiClient) {
        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q("trump")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        articleList = response.getArticles();
                        System.out.println("aarr: " + articleList.get(0).getSource());
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("err: " + throwable.getMessage());
                    }
                }
        );
    }

    private void getTopHeadLines(NewsApiClient newsApiClient) {
        status.setImageResource(R.drawable.ic_loading);
        newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder()
                        .q(searchKey)
                        .country("id")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        status.setImageResource(0);
                        articleList = response.getArticles();
                        articleAdapter = new ArticleAdapter(articleList, getApplicationContext());
                        newsRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        newsRV.setItemAnimator(new DefaultItemAnimator());
                        newsRV.setAdapter(articleAdapter);
                        System.out.println("aarr: " + articleList.get(0).getSource().getName());
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        status.setImageResource(R.drawable.ic_error);
                        System.out.println("err: " + throwable.getMessage());
                    }
                }
        );
    }

}