package com.amazon.alexa.accessory.internal.util;

import com.amazon.alexa.accessory.internal.util.JsonArraySerializable;
import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class JsonUtils {
    private JsonUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static List<List<String>> convertJsonArrayToListOfStringList(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList(jSONArray.length());
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            arrayList.add(convertJsonArrayToStringList(jSONArray.getJSONArray(i)));
        }
        return arrayList;
    }

    public static List<String> convertJsonArrayToStringList(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList(jSONArray.length());
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            arrayList.add(jSONArray.getString(i));
        }
        return arrayList;
    }

    public static <T> List<T> fromJsonArray(JSONArray jSONArray, JsonObjectSerializable.Factory<T> factory) throws JSONException {
        ArrayList arrayList = new ArrayList(jSONArray.length());
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            arrayList.add(factory.mo1239create(jSONArray.getJSONObject(i)));
        }
        return arrayList;
    }

    public static JSONObject getJsonObjectOrNull(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getJSONObject(str);
        } catch (JSONException unused) {
            return null;
        }
    }

    public static String getStringOrNull(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getString(str);
        } catch (JSONException unused) {
            return null;
        }
    }

    public static <T extends JsonObjectSerializable> JSONArray toJsonArray(List<T> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (T t : list) {
            jSONArray.put(t.toJsonObject());
        }
        return jSONArray;
    }

    public static <T extends JsonObjectSerializable> JSONArray toNestedJsonArray(List<List<T>> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (List<T> list2 : list) {
            jSONArray.put(toJsonArray(list2));
        }
        return jSONArray;
    }

    public static <T> List<T> fromJsonArray(JSONArray jSONArray, JsonArraySerializable.Factory<T> factory) throws JSONException {
        ArrayList arrayList = new ArrayList(jSONArray.length());
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            arrayList.add(factory.create(jSONArray.getJSONArray(i)));
        }
        return arrayList;
    }
}
