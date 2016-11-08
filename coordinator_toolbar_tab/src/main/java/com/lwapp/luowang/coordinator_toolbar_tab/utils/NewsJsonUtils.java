package com.lwapp.luowang.coordinator_toolbar_tab.utils;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.lwapp.luowang.coordinator_toolbar_tab.bean.NewsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luowang on 2016/11/3.
 */
public class NewsJsonUtils {
    public static List<NewsBean> getNewsBeanList(String res, String value) {
        List<NewsBean> newsBeanList = new ArrayList<NewsBean>();
        try {
            Log.e("System.out", res);
            JsonParser parser = new JsonParser();
            JsonObject jsonObj = parser.parse(res.trim()).getAsJsonObject();
            JsonElement jsonElement = jsonObj.get(value);
            if (jsonElement == null) {
                return null;
            }
            JsonArray ja = jsonElement.getAsJsonArray();
            for (int i = 0; i < ja.size(); i++) {
                JsonObject jo = ja.get(i).getAsJsonObject();
                if (jo.has("skipType") && "special".equals(jo.get("skipType").getAsString())) {
                    continue;
                }

                if (jo.has("TAGS") && !jo.has("TAG")) {
                    continue;
                }

                if (!jo.has("imgextra")) {
                    NewsBean newBean = jsonUtils.deserialize(jo, NewsBean.class);
                    newsBeanList.add(newBean);
                }
            }
        } catch (JsonSyntaxException e) {
            LogUtils.e("TAG", "getNewsBeanList error", e);
        }
        return newsBeanList;
    }
}
