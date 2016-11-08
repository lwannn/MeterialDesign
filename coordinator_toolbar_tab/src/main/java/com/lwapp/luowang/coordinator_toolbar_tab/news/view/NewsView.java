package com.lwapp.luowang.coordinator_toolbar_tab.news.view;

import com.lwapp.luowang.coordinator_toolbar_tab.bean.NewsBean;

import java.util.List;

/**
 * Created by luowang on 2016/10/30.
 */
public interface NewsView {
    public void showProgressBar();//显示进度条

    public void hideProgressBar();//隐藏进度条

    public void addNews(List<NewsBean> newsBeans);//添加新闻

    public void showLoadFailMsg(String msg, Exception e);//显示加载失败信息
}
