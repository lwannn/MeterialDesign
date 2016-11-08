package com.lwapp.luowang.coordinator_toolbar_tab.news.model;

import android.os.Handler;

import com.lwapp.luowang.coordinator_toolbar_tab.bean.NewsBean;
import com.lwapp.luowang.coordinator_toolbar_tab.common.Urls;
import com.lwapp.luowang.coordinator_toolbar_tab.utils.NewsJsonUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by luowang on 2016/11/3.
 */
public class NewsModelImpl implements NewsModel {
    private Handler mHandler = new Handler();

    @Override
    public void loadNews(String portionUrl, final String type, int pageIndex, final OnLoadNewsListener listener) {
//        StringCallback stringCallback = new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e) {
//                listener.onFailure("", e);
//            }
//
//            @Override
//            public void onResponse(String s) {
//                List<NewsBean> newsBeanList = NewsJsonUtils.getNewsBeanList(s, type);
//                listener.onSuccess(newsBeanList);
//            }
//        };
//
//        String url = portionUrl + pageIndex + Urls.END_URL;
//        OkHttpUtils
//                .get()
//                .url(url)
//                .build()
//                .execute(stringCallback);
        //TODO: OkhttpUtils获取不到数据了
        final String url = portionUrl + pageIndex + Urls.END_URL;
        new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                    conn.setRequestMethod("POST");
                    conn.setConnectTimeout(5000);

                    if (conn.getResponseCode() == conn.HTTP_OK) {
                        InputStream is = conn.getInputStream();
                        InputStreamReader isr = new InputStreamReader(is);
                        BufferedReader br = new BufferedReader(isr);
                        final StringBuffer sb = new StringBuffer();
                        String line = "";

                        while ((line = br.readLine()) != null) {
                            sb.append(line);
                        }

                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                List<NewsBean> newsBeanList = NewsJsonUtils.getNewsBeanList(sb.toString(), type);
                                listener.onSuccess(newsBeanList);
                            }
                        });

                        br.close();
                        isr.close();
                        is.close();
                    }else {
                        listener.onFailure("网络连接错误！！！",null);
                    }
                    conn.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
