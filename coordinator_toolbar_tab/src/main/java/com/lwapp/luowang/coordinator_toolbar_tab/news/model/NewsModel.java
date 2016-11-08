package com.lwapp.luowang.coordinator_toolbar_tab.news.model;

/**
 * Created by luowang on 2016/11/3.
 */
public interface NewsModel {
    public void loadNews(String url,String type,int pageIndex,OnLoadNewsListener listener);
}
