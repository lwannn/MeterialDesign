package com.lwapp.luowang.coordinator_toolbar_tab.main.presenter;

import com.lwapp.luowang.coordinator_toolbar_tab.R;
import com.lwapp.luowang.coordinator_toolbar_tab.main.view.MainView;

/**
 * Created by luowang on 2016/10/29.
 */
public class MianPresenterImpl implements MainPresenter {
    private MainView mainView;

    public MianPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void SwitchFragment(int id) {
        switch (id) {
            case R.id.item_news:
                mainView.Switch2News();
                break;
            case R.id.item_images:
                mainView.Switch2Images();
                break;
            case R.id.item_weather:
                mainView.Switch2Weather();
                break;
            case R.id.item_about:
                mainView.Switch2About();
                break;
        }
    }
}
