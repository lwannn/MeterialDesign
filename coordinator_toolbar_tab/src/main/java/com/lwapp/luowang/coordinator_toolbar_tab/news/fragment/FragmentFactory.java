package com.lwapp.luowang.coordinator_toolbar_tab.news.fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by luowang on 2016/10/29.
 */
public class FragmentFactory {
    private static Map<Integer,BaseFragment> fragmentMap=new HashMap<>();

    public static BaseFragment getFragment(int position){
        BaseFragment fragment=fragmentMap.get(position);
        if(fragment==null){
            switch (position){
                case 0:
                    fragment=new HostSpotFragment();
                    break;
                case 1:
                    fragment=new NbaFragment();
                    break;
                case 2:
                    fragment=new CarFragment();
                    break;
                case 3:
                    fragment=new EntertainmentFragment();
                    break;
            }

            fragmentMap.put(position,fragment);
        }
        return fragment;
    }

}
