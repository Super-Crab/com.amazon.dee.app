package com.amazon.alexa.mobilytics.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.util.Log;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/* loaded from: classes9.dex */
public class DefaultJsonConverter implements JsonConverter {
    private static final String TAG = Log.tag(DefaultJsonConverter.class);
    private final Gson gson;

    public DefaultJsonConverter(@NonNull Gson gson) {
        this.gson = (Gson) Preconditions.checkNotNull(gson);
    }

    @Override // com.amazon.alexa.mobilytics.internal.JsonConverter
    public <T> T fromJson(String str, Class<T> cls) {
        return (T) this.gson.fromJson(str, (Class<Object>) cls);
    }

    @Override // com.amazon.alexa.mobilytics.internal.JsonConverter
    @Nullable
    public String toJson(Object obj) {
        return this.gson.toJson(obj);
    }

    public DefaultJsonConverter() {
        this.gson = new GsonBuilder().create();
    }
}
