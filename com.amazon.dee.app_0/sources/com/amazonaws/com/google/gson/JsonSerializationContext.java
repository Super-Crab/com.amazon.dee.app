package com.amazonaws.com.google.gson;

import java.lang.reflect.Type;
/* loaded from: classes13.dex */
public interface JsonSerializationContext {
    JsonElement serialize(Object obj);

    JsonElement serialize(Object obj, Type type);
}
