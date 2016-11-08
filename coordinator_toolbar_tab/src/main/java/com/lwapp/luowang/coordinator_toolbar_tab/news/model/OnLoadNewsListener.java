package com.lwapp.luowang.coordinator_toolbar_tab.news.model;

import com.lwapp.luowang.coordinator_toolbar_tab.bean.NewsBean;

import java.util.List;

/**
 * Created by luowang on 2016/11/3.
 */
public interface OnLoadNewsListener {
    public void onSuccess(List<NewsBean> newsBeans);
    public void onFailure(String msg,Exception e);
}
