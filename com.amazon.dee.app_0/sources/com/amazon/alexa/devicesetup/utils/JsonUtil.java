package com.amazon.alexa.devicesetup.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/* loaded from: classes7.dex */
public final class JsonUtil {
    private static JsonUtil instance = new JsonUtil();
    private final Gson gson = new GsonBuilder().create();

    private JsonUtil() {
    }

    public static JsonUtil instance() {
        return instance;
    }

    public <T> T fromJson(String str, Class<T> cls) {
        return (T) this.gson.fromJson(str, (Class<Object>) cls);
    }

    public String toJson(Object obj) {
        return this.gson.toJson(obj);
    }
}
