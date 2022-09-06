package com.amazon.alexa.accessory.avsclient.utils;

import com.google.gson.Gson;
/* loaded from: classes.dex */
public final class GsonJsonConverter implements JsonConverter {
    private final Gson gson;

    public GsonJsonConverter(Gson gson) {
        this.gson = gson;
    }

    @Override // com.amazon.alexa.accessory.avsclient.utils.JsonConverter
    public <T> T fromJson(String str, Class<T> cls) {
        return (T) this.gson.fromJson(str, (Class<Object>) cls);
    }

    @Override // com.amazon.alexa.accessory.avsclient.utils.JsonConverter
    public String toJson(Object obj) {
        return this.gson.toJson(obj);
    }
}
