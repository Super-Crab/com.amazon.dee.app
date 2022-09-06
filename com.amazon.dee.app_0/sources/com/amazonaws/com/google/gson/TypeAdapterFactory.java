package com.amazonaws.com.google.gson;

import com.amazonaws.com.google.gson.reflect.TypeToken;
/* loaded from: classes13.dex */
public interface TypeAdapterFactory {
    <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken);
}
