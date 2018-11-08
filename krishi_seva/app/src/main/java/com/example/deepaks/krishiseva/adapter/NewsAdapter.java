package com.example.deepaks.krishiseva.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deepaks.krishiseva.R;
import com.example.deepaks.krishiseva.bean.Article;
import com.example.deepaks.krishiseva.util.ImageUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private final List<Article> mNewsList;
    private final Context mContext;
    private final OnItemClickListener mClickListener;

    public interface OnItemClickListener {
        void onClickItemListener(String newsUrl);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_news_image)
        ImageView mThumbnailNews;
        @BindView(R.id.tv_news_title)
        TextView mNewsTitle;
        @BindView(R.id.tv_author_name)
        TextView mAuthorName;
        @BindView(R.id.tv_date_time)
        TextView mDateTime;

        @SuppressWarnings("WeakerAccess")
        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mClickListener.onClickItemListener(mNewsList.get(getAdapterPosition()).getWebUrl());
        }
    }

    public NewsAdapter(Context context, OnItemClickListener onClickListener, List<Article> newsList) {
        this.mNewsList = newsList;
        this.mContext = context;
        this.mClickListener = onClickListener;
    }

    @Override
    public NewsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_news, parent, false);
        return new NewsAdapter.MyViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final NewsAdapter.MyViewHolder holder, final int position) {
        Article newsData = mNewsList.get(holder.getAdapterPosition());
        holder.mAuthorName.setText(newsData.getAuthorName());
        String[] dateAndTime = newsData.getDate().split("T");
        String date = dateAndTime[0];
        holder.mDateTime.setText(date);
        holder.mNewsTitle.setText(newsData.getWebTitle());
        ImageUtils.setImageFromUrl(mContext, newsData.getImageUrl(), holder.mThumbnailNews);
    }


    /**
     * @return the list count
     * @author deepaks
     * @description method for getting the item count
     */
    @Override
    public int getItemCount() {
        return mNewsList.size();
    }
}

