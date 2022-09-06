package com.amazon.identity.auth.device;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class kg {
    private static final String TAG = "com.amazon.identity.auth.device.kg";

    private kg() {
    }

    public static Map<String, Map<String, String>> dN(String str) {
        if (str == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                JSONObject jSONObject2 = jSONObject.getJSONObject(str2);
                Iterator keys2 = jSONObject2.keys();
                HashMap hashMap2 = new HashMap();
                while (keys2.hasNext()) {
                    String str3 = (String) keys2.next();
                    hashMap2.put(str3, jSONObject2.getString(str3));
                }
                hashMap.put(str2, hashMap2);
            }
        } catch (JSONException e) {
            io.w(TAG, "CredentialMapSerializer.toMap failed - ignoring the credentials received: %s", e);
        }
        return hashMap;
    }

    public static String k(Map<String, Map<String, String>> map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            for (Map.Entry<String, Map<String, String>> entry : map.entrySet()) {
                JSONObject jSONObject2 = new JSONObject();
                for (Map.Entry<String, String> entry2 : entry.getValue().entrySet()) {
                    jSONObject2.putOpt(entry2.getKey(), entry2.getValue());
                }
                jSONObject.put(entry.getKey(), jSONObject2);
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
