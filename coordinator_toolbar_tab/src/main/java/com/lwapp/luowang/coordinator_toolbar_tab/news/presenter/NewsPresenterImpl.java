package com.lwapp.luowang.coordinator_toolbar_tab.news.presenter;

import com.lwapp.luowang.coordinator_toolbar_tab.bean.NewsBean;
import com.lwapp.luowang.coordinator_toolbar_tab.news.model.NewsModel;
import com.lwapp.luowang.coordinator_toolbar_tab.news.model.NewsModelImpl;
import com.lwapp.luowang.coordinator_toolbar_tab.news.model.OnLoadNewsListener;
import com.lwapp.luowang.coordinator_toolbar_tab.news.view.NewsView;

import java.util.List;

/**
 * Created by luowang on 2016/11/3.
 */
public class NewsPresenterImpl implements NewsPresenter, OnLoadNewsListener {
    private NewsView newsView;
    private NewsModel newsModel;

    public NewsPresenterImpl(NewsView newsView) {
        this.newsView = newsView;
    }

    @Override
    public void loadNews(String url, String type, int pageIndex) {
        newsModel = new NewsModelImpl();
        newsModel.loadNews(url, type, pageIndex, this);
    }

    @Override
    public void onSuccess(List<NewsBean> newsBeans) {
        newsView.hideProgressBar();
        newsView.addNews(newsBeans);
    }

    @Override
    public void onFailure(String msg, Exception e) {
        newsView.showLoadFailMsg(msg, e);
    }
}
