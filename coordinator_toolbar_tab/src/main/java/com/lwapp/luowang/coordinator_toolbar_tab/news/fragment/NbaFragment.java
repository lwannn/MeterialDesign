package com.lwapp.luowang.coordinator_toolbar_tab.news.fragment;


import android.support.v4.app.Fragment;

import com.lwapp.luowang.coordinator_toolbar_tab.common.Urls;

/**
 * A simple {@link Fragment} subclass.
 */
public class NbaFragment extends BaseFragment {

    @Override
    public String getUrl() {
        return Urls.TOP_URL + Urls.NBA_ID + "/";
    }

    @Override
    public String getType() {
        return Urls.NBA_ID;
    }
}
