package me.luraframework.core.commons;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {

    private static Gson gson = new GsonBuilder().create();

    public static String toStr(Object obj) {
       return gson.toJson(obj);
    }

    public static <T> T toObj(String jsonStr, Class<T> clazz) {
        return gson.fromJson(jsonStr, clazz);
    }
}
