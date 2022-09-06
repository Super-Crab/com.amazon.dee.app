package com.amazon.deviceevents.serialization;

import com.amazon.deviceevents.com.google.gson.JsonElement;
/* loaded from: classes12.dex */
public interface IGson {
    <T> T fromJson(JsonElement jsonElement, Class<T> cls);

    <T> T fromJson(String str, Class<T> cls);

    String toJson(Object obj);

    JsonElement toJsonTree(Object obj);
}
