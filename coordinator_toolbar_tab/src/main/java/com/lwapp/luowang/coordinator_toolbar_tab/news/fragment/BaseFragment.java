package com.lwapp.luowang.coordinator_toolbar_tab.news.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lwapp.luowang.coordinator_toolbar_tab.R;
import com.lwapp.luowang.coordinator_toolbar_tab.bean.NewsBean;
import com.lwapp.luowang.coordinator_toolbar_tab.common.Urls;
import com.lwapp.luowang.coordinator_toolbar_tab.news.NewsDetailActivity;
import com.lwapp.luowang.coordinator_toolbar_tab.news.adapter.NewsAdapter;
import com.lwapp.luowang.coordinator_toolbar_tab.news.presenter.NewsPresenter;
import com.lwapp.luowang.coordinator_toolbar_tab.news.presenter.NewsPresenterImpl;
import com.lwapp.luowang.coordinator_toolbar_tab.news.view.NewsView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment implements NewsView, SwipeRefreshLayout.OnRefreshListener {
    private List<NewsBean> newsBeanList = new ArrayList<NewsBean>();
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshWidget;
    private NewsAdapter newsAdapter;
    private LinearLayoutManager mManager;
    private LinearLayout mFooterView;
    private NewsPresenter presenter;
    private Context mContext;
    private Handler mHandler = new Handler();

    public BaseFragment() {
    }

    private int pageIndex = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        initView(view);
        initEvent();

        presenter = new NewsPresenterImpl(this);
        onRefresh();
        return view;
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.srl_refresh);
        mFooterView = (LinearLayout) view.findViewById(R.id.footer_view);
    }

    private void initEvent() {
        mManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setHasFixedSize(true);//大致的意思就是将item的大小已经固定了，不需要每次刷新的时候进行重绘（提高效率）

        newsAdapter = new NewsAdapter(getContext(), newsBeanList);
        newsAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecyclerView.setAdapter(newsAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisiblePosition = mManager.findLastVisibleItemPosition();
                if (lastVisiblePosition + 1 == newsBeanList.size() && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mFooterView.setVisibility(View.VISIBLE);
//                    addNews();
                    presenter.loadNews(getUrl(), getType(), pageIndex + Urls.PAZE_SIZE);
                    pageIndex += Urls.PAZE_SIZE;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        mRefreshWidget.setOnRefreshListener(this);
        mRefreshWidget.setColorSchemeResources(R.color.colorPrimary);//设置加载条的颜色
    }

    private NewsAdapter.OnItemClickListener mOnItemClickListener = new NewsAdapter.OnItemClickListener() {
        @Override
        public void OnItemClick(View view, NewsBean bean) {
            Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
            intent.putExtra("news", bean);

            View transitionView = view.findViewById(R.id.iv_icon);
            //安卓5.x以上才会有动画效果
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), transitionView, "img_transition");
            ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
        }
    };

    public abstract String getUrl();

    public abstract String getType();

    @Override
    public void showProgressBar() {
        mRefreshWidget.setRefreshing(true);
    }

    @Override
    public void hideProgressBar() {
        mRefreshWidget.setRefreshing(false);
    }

    @Override
    public void addNews(List<NewsBean> newsBeans) {
        if (pageIndex == 0) {
            newsBeanList.clear();
            newsBeanList.addAll(newsBeans);
        } else {
            newsBeanList.addAll(newsBeans);
        }
        newsAdapter.notifyDataSetChanged();
        mFooterView.setVisibility(View.GONE);
    }

    @Override
    public void showLoadFailMsg(String msg, Exception e) {
        Snackbar.make(getView(),msg,2000)
                .show();
    }

    @Override
    public void onRefresh() {
        mFooterView.setVisibility(View.VISIBLE);
        pageIndex = 0;
        presenter.loadNews(getUrl(), getType(), pageIndex);
    }
}
