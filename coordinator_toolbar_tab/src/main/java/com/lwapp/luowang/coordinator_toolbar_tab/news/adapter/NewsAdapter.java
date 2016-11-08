package com.lwapp.luowang.coordinator_toolbar_tab.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

        Glide.with(mContext)
                .load(imgsrc)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .centerCrop()
                .into(((MyViewHolder) holder).mIcon);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    //设置回调接口
    public interface OnItemClickListener {
        public void OnItemClick(View view, NewsBean bean);
    }

    private OnItemClickListener myListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        myListener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTitle;
        public TextView mDesgit;
        public ImageView mIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mDesgit = (TextView) itemView.findViewById(R.id.tv_digest);
            mIcon = (ImageView) itemView.findViewById(R.id.iv_icon);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            myListener.OnItemClick(view, mDatas.get(getAdapterPosition()));
        }
    }

}
