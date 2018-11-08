package com.example.deepaks.krishiseva.view.dashboard.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.deepaks.krishiseva.R;
import com.example.deepaks.krishiseva.adapter.NewsAdapter;
import com.example.deepaks.krishiseva.bean.Article;
import com.example.deepaks.krishiseva.network.NetworkConnection;
import com.example.deepaks.krishiseva.network.NewsResponseData;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsFragment extends Fragment implements NewsAdapter.OnItemClickListener {

    @BindView(R.id.rv_list)
    RecyclerView mNewsRv;
    @BindView(R.id.tv_no_data)
    TextView mNoNewsText;
    @BindView(R.id.pb_loading)
    ProgressBar mLoadingProgress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        setUpFragmentComponent(view);
        return view;
    }

    private void setUpAdapter(List<Article> articleList) {
        NewsAdapter adapter = new NewsAdapter(getActivity(), this, articleList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mNewsRv.setLayoutManager(mLayoutManager);
        mNewsRv.setItemAnimator(new DefaultItemAnimator());
        mNewsRv.setAdapter(adapter);
    }

    private void setUpFragmentComponent(View view) {
        ButterKnife.bind(this, view);
        if (NetworkConnection.isNetworkConnected(getActivity())) {
            new DownloadNewsTask().execute();
        } else {
            mNewsRv.setVisibility(View.GONE);
            mLoadingProgress.setVisibility(View.INVISIBLE);
            mNoNewsText.setText(getString(R.string.no_internet_connection));
            mNoNewsText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClickItemListener(String newsUrl) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsUrl));
        startActivity(browserIntent);
    }

    private class DownloadNewsTask extends AsyncTask<Void, List<Article>, List<Article>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Article> doInBackground(Void... lists) {
            List<Article> bookList = null;
            try {
                String responseString = NetworkConnection.getResponseFromHttpUrl(NetworkConnection.buildUrl());
                bookList = NewsResponseData.getArticleData(responseString);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bookList;
        }

        @Override
        protected void onPostExecute(List<Article> articles) {
            super.onPostExecute(articles);
            mLoadingProgress.setVisibility(View.INVISIBLE);
            if (articles != null && articles.size() > 0) {
                setUpAdapter(articles);
            } else {
                mNoNewsText.setText(getString(R.string.no_news_message));
            }
        }
    }
}
