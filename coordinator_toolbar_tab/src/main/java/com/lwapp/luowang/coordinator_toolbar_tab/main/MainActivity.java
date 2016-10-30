package com.lwapp.luowang.coordinator_toolbar_tab.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.lwapp.luowang.coordinator_toolbar_tab.R;
import com.lwapp.luowang.coordinator_toolbar_tab.about.AboutFragment;
import com.lwapp.luowang.coordinator_toolbar_tab.image.ImageFragment;
import com.lwapp.luowang.coordinator_toolbar_tab.main.presenter.MainPresenter;
import com.lwapp.luowang.coordinator_toolbar_tab.main.presenter.MianPresenterImpl;
import com.lwapp.luowang.coordinator_toolbar_tab.main.view.MainView;
import com.lwapp.luowang.coordinator_toolbar_tab.news.NewsFragment;
import com.lwapp.luowang.coordinator_toolbar_tab.weather.WeatherFragment;

public class MainActivity extends AppCompatActivity implements MainView {

    private Toolbar mToolBar;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolBar = (Toolbar) findViewById(R.id.toolBar);
        mToolBar.setTitle("第一次使用ToolBar");
        setSupportActionBar(mToolBar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();

        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mNavigationView = (NavigationView) findViewById(R.id.navigationView);
//        mNavigationView.setItemIconTintList(null);//将menu(item)中的图片不要改变
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                return true;
            }
        });

        final MainPresenter mainPresenter = new MianPresenterImpl(this);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mainPresenter.SwitchFragment(item.getItemId());
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        Switch2News();
    }

    @Override
    public void Switch2News() {
        //记得commit,谢谢！
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new NewsFragment()).commit();
        mToolBar.setTitle("新闻");
    }

    @Override
    public void Switch2Images() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new ImageFragment()).commit();
        mToolBar.setTitle("图片");
    }

    @Override
    public void Switch2Weather() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new WeatherFragment()).commit();
        mToolBar.setTitle("天气");
    }

    @Override
    public void Switch2About() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new AboutFragment()).commit();
        mToolBar.setTitle("关于");
    }
}
