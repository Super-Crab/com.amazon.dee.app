package com.amazon.alexa.audiopersonalization.factory;

import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class JSONObjectFactory {
    public JSONObject createJSONObject(String str) throws JSONException {
        return new JSONObject(str);
    }

    public JSONObject createJSONObject() {
        return new JSONObject();
    }
}
