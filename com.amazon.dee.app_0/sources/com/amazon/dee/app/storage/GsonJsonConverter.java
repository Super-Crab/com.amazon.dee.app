package com.amazon.dee.app.storage;

import com.google.gson.Gson;
/* loaded from: classes12.dex */
public class GsonJsonConverter implements JsonConverter {
    private final Gson gson;

    public GsonJsonConverter(Gson gson) {
        this.gson = gson;
    }

    @Override // com.amazon.dee.app.storage.JsonConverter
    public <T> T fromJson(String str, Class<T> cls) {
        return (T) this.gson.fromJson(str, (Class<Object>) cls);
    }

    @Override // com.amazon.dee.app.storage.JsonConverter
    public String toJson(Object obj) {
        return this.gson.toJson(obj);
    }
}
