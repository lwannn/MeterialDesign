package com.lwapp.luowang.coordinator_toolbar_tab.news.fragment;


import android.os.Bundle;
import android.os.Handler;
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
import com.lwapp.luowang.coordinator_toolbar_tab.news.adapter.NewsAdapter;
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

    private Handler mHandler = new Handler();

    public BaseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        initView(view);
        initData();
        initEvent();

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
        mRecyclerView.setAdapter(newsAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisiblePosition = mManager.findLastVisibleItemPosition();
                if (lastVisiblePosition + 1 == newsBeanList.size() && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mFooterView.setVisibility(View.VISIBLE);
                    addNews();
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

    private void initData() {
        String title = "旺神";
        String digest = "旺神！听说你很帅啊！！！";
        String imgSrc = "....";
        for (int i = 0; i < 15; i++) {
            NewsBean newsBean = new NewsBean(title, digest, imgSrc);
            newsBeanList.add(newsBean);
        }
    }

    public abstract String getUrl();

    @Override
    public void showProgressBar() {
        mRefreshWidget.setRefreshing(true);
    }

    @Override
    public void hideProgressBar() {
        mRefreshWidget.setRefreshing(false);
    }

    @Override
    public void addNews() {
        initData();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mFooterView.setVisibility(View.GONE);
                newsAdapter.notifyDataSetChanged();
            }
        }, 2000);
    }

    @Override
    public void showLoadFailMsg() {
//        Snackbar.make()
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideProgressBar();
            }
        }, 2000);
    }
}
