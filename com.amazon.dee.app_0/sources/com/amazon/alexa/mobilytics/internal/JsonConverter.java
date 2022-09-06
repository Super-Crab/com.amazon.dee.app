package com.amazon.alexa.mobilytics.internal;
/* loaded from: classes9.dex */
public interface JsonConverter {
    <T> T fromJson(String str, Class<T> cls);

    String toJson(Object obj);
}
