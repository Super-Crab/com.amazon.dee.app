package com.amazon.alexa.accessory.avsclient.utils;
/* loaded from: classes.dex */
public interface JsonConverter {
    <T> T fromJson(String str, Class<T> cls);

    String toJson(Object obj);
}
