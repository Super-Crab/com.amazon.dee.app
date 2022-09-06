package com.amazon.deviceevents.serialization;

import com.amazon.deviceevents.com.google.gson.Gson;
import com.amazon.deviceevents.com.google.gson.GsonBuilder;
import com.amazon.deviceevents.com.google.gson.JsonElement;
/* loaded from: classes12.dex */
public class GsonFacade implements IGson {
    private final Gson mGson = new GsonBuilder().create();

    @Override // com.amazon.deviceevents.serialization.IGson
    public <T> T fromJson(JsonElement jsonElement, Class<T> cls) {
        return (T) this.mGson.fromJson(jsonElement, (Class<Object>) cls);
    }

    @Override // com.amazon.deviceevents.serialization.IGson
    public String toJson(Object obj) {
        return this.mGson.toJson(obj);
    }

    @Override // com.amazon.deviceevents.serialization.IGson
    public JsonElement toJsonTree(Object obj) {
        return this.mGson.toJsonTree(obj);
    }

    @Override // com.amazon.deviceevents.serialization.IGson
    public <T> T fromJson(String str, Class<T> cls) {
        return (T) this.mGson.fromJson(str, (Class<Object>) cls);
    }
}
