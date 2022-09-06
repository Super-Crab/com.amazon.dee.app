package com.amazon.CoralAndroidClient.Model;

import org.json.JSONObject;
/* loaded from: classes.dex */
public class NullValue implements Value {
    @Override // com.amazon.CoralAndroidClient.Model.Value
    public Object toJsonInternal() {
        return JSONObject.NULL;
    }
}
