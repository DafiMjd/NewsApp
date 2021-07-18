package com.example.newsapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.newsapi.NewsApiClient;
import com.example.newsapp.newsapi.models.Article;
import com.example.newsapp.newsapi.models.ArticleAdapter;
import com.example.newsapp.newsapi.models.request.EverythingRequest;
import com.example.newsapp.newsapi.models.request.TopHeadlinesRequest;
import com.example.newsapp.newsapi.models.response.ArticleResponse;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private List<Article> articleList;
    private RecyclerView fragmentRV;
    private ArticleAdapter articleAdapter;

    private ImageView status;

    Context context;

    public HomeFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        fragmentRV = view.findViewById(R.id.fragmentRV);
        status = view.findViewById(R.id.status);
        articleList = new ArrayList<>();


        NewsApiClient newsApiClient = new NewsApiClient("45b104ac780141b6b74bc9fdc536e402");
        getTopHeadLines(newsApiClient);

        return view;
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
                        .country("id")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        status.setImageResource(0);
                        articleList = response.getArticles();
                        articleAdapter = new ArticleAdapter(articleList, context);
                        fragmentRV.setLayoutManager(new LinearLayoutManager(context));
                        fragmentRV.setItemAnimator(new DefaultItemAnimator());
                        fragmentRV.setAdapter(articleAdapter);
                        System.out.println("aarr: " + articleList.get(0).getSource().getName());
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        status.setImageResource(R.drawable.ic_loading);
                        System.out.println("err: " + throwable.getMessage());
                    }
                }
        );
    }
}
