package com.amazon.deviceevents.utils;

import com.amazon.deviceevents.serialization.GsonFacade;
import com.amazon.deviceevents.serialization.IGson;
/* loaded from: classes12.dex */
public class GsonSingleton {
    private static IGson mGson = new GsonFacade();

    public static <T> T castObjectTo(Object obj, Class<T> cls) {
        return (T) getInstance().fromJson(getInstance().toJsonTree(obj), cls);
    }

    public static IGson getInstance() {
        return mGson;
    }
}
