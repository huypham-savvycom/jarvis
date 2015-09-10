package com.jarvis.android.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jarvis.android.model.UnixTime;

import java.lang.reflect.Type;

/**
 * Init Gson Create Object
 *
 */
public class GsonUtils {

    private static final GsonBuilder gsonBuilder = new GsonBuilder()
            .setPrettyPrinting();

    public static void registerType(Type type, Object typeAdapter) {
        gsonBuilder.registerTypeAdapter(type, typeAdapter);
    }

    public static Gson getGson() {
    	registerType(UnixTime.class, new UnixTimeDeserializer());
        return gsonBuilder.create();
    }
}