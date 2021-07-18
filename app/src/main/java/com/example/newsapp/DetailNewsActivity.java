package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newsapp.newsapi.models.Article;
import com.example.newsapp.newsapi.models.Source;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailNewsActivity extends AppCompatActivity {
    private Article article;
    private Source source;
    private Intent intent;
    private ImageView detailNewsIV;
    private TextView detailTitleTV, authorTV, detailPublisherTV, detailDateTV, descTV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        intent = getIntent();
        article = intent.getParcelableExtra("article");
        source = intent.getParcelableExtra("source");

        layoutInflater();
        fillLayout();



    }

    private void layoutInflater() {
        detailNewsIV = findViewById(R.id.detailNewsIV);
        detailTitleTV = findViewById(R.id.detailTitleTV);
        authorTV = findViewById(R.id.authorTV);
        detailPublisherTV = findViewById(R.id.detailPublisherTV);
        detailDateTV = findViewById(R.id.detailDateTV);
        descTV = findViewById(R.id.descTV);
    }

    private void fillLayout() {
        Picasso.get()
                .load(article.getUrlToImage())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(detailNewsIV);
        detailTitleTV.setText(article.getTitle());
        authorTV.setText(article.getAuthor());
        detailPublisherTV.setText(source.getName());
        detailDateTV.setText(article.getPublishedAt());
        descTV.setText(article.getDescription());

    }
}
