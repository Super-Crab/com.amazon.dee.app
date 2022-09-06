package com.amazon.dee.app.storage;
/* loaded from: classes12.dex */
public interface JsonConverter {
    <T> T fromJson(String str, Class<T> cls);

    String toJson(Object obj);
}
