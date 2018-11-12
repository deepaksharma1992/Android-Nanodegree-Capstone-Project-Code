package com.example.deepaks.krishiseva.view.dashboard.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
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
import com.example.deepaks.krishiseva.service.NewsService;

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
    }

    @Override
    public void onClickItemListener(String newsUrl) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsUrl));
        startActivity(browserIntent);
    }

    public BroadcastReceiver mNewsDataBroadcast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mLoadingProgress.setVisibility(View.GONE);
            List<Article> articleList =
                    intent.getParcelableArrayListExtra(NewsService.NEWS_DATA_EXTRA);
            setUpAdapter(articleList);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(mNewsDataBroadcast
                , new IntentFilter(NewsService.NEWS_BROADCAST_ACTION));
        mLoadingProgress.setVisibility(View.VISIBLE);

        if (NetworkConnection.isNetworkConnected(getActivity())) {
            NewsService.startNewsService(getActivity(), NewsService.NEWS_SEVICE_ACTION);
        } else {
            mNewsRv.setVisibility(View.GONE);
            mLoadingProgress.setVisibility(View.INVISIBLE);
            mNoNewsText.setText(getString(R.string.no_internet_connection));
            mNoNewsText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(mNewsDataBroadcast);
    }
}
