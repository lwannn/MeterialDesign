package com.lwapp.luowang.coordinator_toolbar_tab.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * json转换工具类
 * Created by luowang on 2016/11/3.
 */
public class jsonUtils {
    private static Gson mGson=new Gson();

    /**
     * 将json字符串转换为实体类
     * @param json
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> T deserialize(JsonObject json, Class<T> clz){
        return mGson.fromJson(json,clz);
    }
}
