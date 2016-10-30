package com.lwapp.luowang.coordinator_toolbar_tab.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lwapp.luowang.coordinator_toolbar_tab.R;
import com.lwapp.luowang.coordinator_toolbar_tab.bean.NewsBean;

import java.util.List;

/**
 * Created by luowang on 2016/10/30.
 */
public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<NewsBean> mDatas;

    public NewsAdapter(Context mContext, List<NewsBean> newsBeanList) {
        this.mContext = mContext;
        this.mDatas = newsBeanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //TODO: 这个方法和View.inflate()到底有什么区别呢
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewsBean newsBean = mDatas.get(position);
        String title = newsBean.getTitle();
        String digest = newsBean.getDigest();
        String imgsrc = newsBean.getImgsrc();
        ((MyViewHolder) holder).mTitle.setText(title);
        ((MyViewHolder) holder).mDesgit.setText(digest);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitle;
        public TextView mDesgit;
        public ImageView mIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mDesgit = (TextView) itemView.findViewById(R.id.tv_digest);
            mIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
        }
    }

}
