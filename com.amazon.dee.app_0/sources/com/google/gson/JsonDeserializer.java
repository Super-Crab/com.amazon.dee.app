package com.google.gson;

import java.lang.reflect.Type;
/* loaded from: classes3.dex */
public interface JsonDeserializer<T> {
    /* renamed from: deserialize */
    T mo5482deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException;
}
