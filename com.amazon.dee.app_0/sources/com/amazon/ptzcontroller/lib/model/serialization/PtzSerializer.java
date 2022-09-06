package com.amazon.ptzcontroller.lib.model.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.typeadapters.UtcDateTypeAdapter;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import java.lang.reflect.Type;
import java.util.Date;
/* loaded from: classes13.dex */
public enum PtzSerializer {
    INSTANCE;
    
    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().registerTypeAdapter(Date.class, new UtcDateTypeAdapter()).create();

    @CheckForNull
    public <T> T fromJson(@Nullable JsonElement jsonElement, @NonNull Class<T> cls) {
        return (T) GSON.fromJson(jsonElement, (Class<Object>) cls);
    }

    @CheckForNull
    public <T> String toJson(@Nullable T t) {
        if (t == null) {
            return null;
        }
        return GSON.toJson(t);
    }

    @CheckForNull
    public <T> T fromJson(@Nullable String str, @NonNull Type type) {
        return (T) GSON.fromJson(str, type);
    }
}
