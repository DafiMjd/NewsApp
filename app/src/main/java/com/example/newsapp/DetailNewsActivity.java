package com.example.newsapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newsapp.newsapi.models.Article;
import com.example.newsapp.newsapi.models.Source;
import com.squareup.picasso.Picasso;

public class DetailNewsActivity extends AppCompatActivity {
    private Article article;
    private Source source;
    private Intent intent;
    private ImageView detailNewsIV;
    private TextView detailTitleTV, authorTV, detailPublisherTV, detailDateTV, descTV;
    private Button readMoreBtn;
    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        intent = getIntent();
        article = intent.getParcelableExtra("article");
        source = intent.getParcelableExtra("source");
        readMoreBtn = findViewById(R.id.readMoreBtn);

        layoutInflater();
        fillLayout();


    }

    @Override
    protected void onResume() {
        super.onResume();
        readMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    public void showDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        webView = new WebView(this);
        webView.loadUrl(article.getUrl());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        alert.setView(webView);
        alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
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
