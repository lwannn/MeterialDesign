package com.lwapp.luowang.coordinator_toolbar_tab.news;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lwapp.luowang.coordinator_toolbar_tab.R;
import com.lwapp.luowang.coordinator_toolbar_tab.news.fragment.FragmentFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    private String[] Title = new String[]{"热点", "科技", "军事", "娱乐"};

    public NewsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.getFragment(position);
        }

        @Override
        public int getCount() {
            return Title.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return Title[position];
        }
    }

}
