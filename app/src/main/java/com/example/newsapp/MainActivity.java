package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;

import com.example.newsapp.newsapi.NewsApiClient;
import com.example.newsapp.newsapi.models.Article;
import com.example.newsapp.newsapi.models.ArticleAdapter;
import com.example.newsapp.newsapi.models.request.EverythingRequest;
import com.example.newsapp.newsapi.models.request.TopHeadlinesRequest;
import com.example.newsapp.newsapi.models.response.ArticleResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Article> articleList;
    private RecyclerView newsRV;
    private ArticleAdapter articleAdapter;

    private ImageButton homeBtn;
    private ImageButton bookmarkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeBtn = findViewById(R.id.homeBtn);
        bookmarkBtn = findViewById(R.id.bookmarkBtn);
        newsRV = findViewById(R.id.newsRV);

        List<Article> articleList = new ArrayList<>();

        NewsApiClient newsApiClient = new NewsApiClient("45b104ac780141b6b74bc9fdc536e402");

        getTopHeadLines(newsApiClient);


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
        newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder()
                        .country("id")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        articleList = response.getArticles();
                        articleAdapter = new ArticleAdapter(articleList);
                        newsRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        newsRV.setAdapter(articleAdapter);
                        System.out.println("aarr: " + articleList.get(0).getSource().getName());
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("err: " + throwable.getMessage());
                    }
                }
        );
    }


}