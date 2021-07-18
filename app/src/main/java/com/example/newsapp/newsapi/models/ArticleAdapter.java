package com.example.newsapp.newsapi.models;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.MainActivity;
import com.example.newsapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private List<Article> articleList;

    public ArticleAdapter(List<Article> articleList) {
        this.articleList = articleList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.ViewHolder holder, int position) {
        Article article = articleList.get(position);

        Picasso.get()
                .load(article.getUrlToImage())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.newsIV);
        holder.titleTV.setText(article.getTitle());
        holder.publisherTV.setText(article.getSource().getName());
        holder.dateTV.setText(article.getPublishedAt());
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView newsIV;
        TextView titleTV, publisherTV, dateTV;

        public ViewHolder(@NonNull View view) {
            super(view);

            newsIV = view.findViewById(R.id.newsIV);
            titleTV = view.findViewById(R.id.titleTV);
            publisherTV = view.findViewById(R.id.publisherTV);
            dateTV = view.findViewById(R.id.dateTV);
        }
    }




}
