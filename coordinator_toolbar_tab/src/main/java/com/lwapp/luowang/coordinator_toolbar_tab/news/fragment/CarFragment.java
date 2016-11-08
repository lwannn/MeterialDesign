package com.lwapp.luowang.coordinator_toolbar_tab.news.fragment;


import android.support.v4.app.Fragment;

import com.lwapp.luowang.coordinator_toolbar_tab.common.Urls;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarFragment extends BaseFragment {

    @Override
    public String getUrl() {
        return Urls.TOP_URL+Urls.CAR_ID+"/";
    }

    @Override
    public String getType() {
        return Urls.CAR_ID;
    }
}
