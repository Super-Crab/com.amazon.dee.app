package com.amazon.alexa.accessory.internal.util;

import org.json.JSONArray;
import org.json.JSONException;
/* loaded from: classes.dex */
public interface JsonArraySerializable {

    /* loaded from: classes.dex */
    public interface Factory<T> {
        T create(JSONArray jSONArray) throws JSONException;
    }

    JSONArray toJsonArray() throws JSONException;
}
