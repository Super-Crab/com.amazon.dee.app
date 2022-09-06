package com.amazon.alexa.accessory.internal.util;

import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public interface JsonObjectSerializable {

    /* loaded from: classes.dex */
    public interface Factory<T> {
        /* renamed from: create */
        T mo1239create(JSONObject jSONObject) throws JSONException;
    }

    JSONObject toJsonObject() throws JSONException;
}
