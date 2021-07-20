package com.example.newsapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

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


    private ImageView status;

    private Context context;



    private String searchKey;

    public HomeFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);


        return view;
    }


}
