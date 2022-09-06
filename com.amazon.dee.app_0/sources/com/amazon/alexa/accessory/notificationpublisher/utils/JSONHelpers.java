package com.amazon.alexa.accessory.notificationpublisher.utils;

import androidx.annotation.NonNull;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class JSONHelpers {
    private static final String TAG = "JSONHelpers";

    private JSONHelpers() {
    }

    public static boolean isArrayContains(@NonNull JSONArray jSONArray, @NonNull Object obj) {
        for (int i = 0; i < jSONArray.length(); i++) {
            if (jSONArray.opt(i).equals(obj)) {
                return true;
            }
        }
        return false;
    }

    public static void mergeJSONObjects(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            jSONObject2.put(str, jSONObject.optString(str));
        }
    }

    public static void mergeJSONObjects(JSONObject jSONObject, JSONObject jSONObject2, String[] strArr) throws JSONException {
        mergeJSONObjects(new JSONObject(jSONObject, strArr), jSONObject2);
    }
}
