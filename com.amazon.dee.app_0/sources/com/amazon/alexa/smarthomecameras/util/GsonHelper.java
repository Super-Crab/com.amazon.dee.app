package com.amazon.alexa.smarthomecameras.util;

import com.google.gson.Gson;
import java.lang.reflect.Type;
/* loaded from: classes10.dex */
public final class GsonHelper {
    private static final Gson gson = new Gson();

    private GsonHelper() {
    }

    public static <T> T fromJson(String str, Class<T> cls) {
        return (T) gson.fromJson(str, (Class<Object>) cls);
    }

    public static <T> String toJson(T t) {
        return gson.toJson(t);
    }

    public static <T> T fromJson(String str, Type type) {
        return (T) gson.fromJson(str, type);
    }
}
