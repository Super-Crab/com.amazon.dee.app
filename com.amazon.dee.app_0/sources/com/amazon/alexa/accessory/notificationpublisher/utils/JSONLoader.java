package com.amazon.alexa.accessory.notificationpublisher.utils;

import android.content.Context;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class JSONLoader {
    private static final String TAG = "JSONLoader";

    private JSONLoader() {
    }

    public static JSONArray loadJSONArrayFromAsset(Context context, String str) throws JSONException {
        String loadJSONStringFromAsset = loadJSONStringFromAsset(context, str);
        if (!Strings.isNullOrEmpty(loadJSONStringFromAsset)) {
            return new JSONArray(loadJSONStringFromAsset);
        }
        return null;
    }

    public static JSONObject loadJSONObjectFromAsset(Context context, String str) throws JSONException {
        String loadJSONStringFromAsset = loadJSONStringFromAsset(context, str);
        if (!Strings.isNullOrEmpty(loadJSONStringFromAsset)) {
            return new JSONObject(loadJSONStringFromAsset);
        }
        return null;
    }

    private static String loadJSONStringFromAsset(Context context, String str) {
        Exception e;
        String str2;
        try {
            InputStream open = context.getAssets().open(str);
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            open.close();
            str2 = new String(bArr, "UTF-8");
        } catch (Exception e2) {
            e = e2;
            str2 = null;
        }
        try {
            Log.d(TAG, "json string: " + str2);
        } catch (Exception e3) {
            e = e3;
            String str3 = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("error: ");
            outline107.append(e.getMessage());
            Log.d(str3, outline107.toString());
            return str2;
        }
        return str2;
    }

    public static Set<String> loadJsonAsSetFromAsset(Context context, String str) throws JSONException {
        HashSet hashSet = new HashSet();
        String loadJSONStringFromAsset = loadJSONStringFromAsset(context, str);
        if (Strings.isNullOrEmpty(loadJSONStringFromAsset)) {
            return hashSet;
        }
        JSONArray jSONArray = new JSONArray(loadJSONStringFromAsset);
        for (int i = 0; i < jSONArray.length(); i++) {
            hashSet.add(jSONArray.getString(i));
        }
        return hashSet;
    }
}
