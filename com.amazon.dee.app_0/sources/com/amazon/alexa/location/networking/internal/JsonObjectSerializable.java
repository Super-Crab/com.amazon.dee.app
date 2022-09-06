package com.amazon.alexa.location.networking.internal;

import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public interface JsonObjectSerializable {

    /* loaded from: classes9.dex */
    public interface Factory<T> {
        /* renamed from: create */
        T mo1662create(JSONObject jSONObject) throws JSONException;
    }

    JSONObject toJsonObject() throws JSONException;
}
