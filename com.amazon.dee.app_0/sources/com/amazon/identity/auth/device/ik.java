package com.amazon.identity.auth.device;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class ik {
    private static final String TAG = "com.amazon.identity.auth.device.ik";

    private ik() {
    }

    public static String a(JSONObject jSONObject, String str, String str2) {
        if (jSONObject != null) {
            try {
                if (jSONObject.has(str) && !jSONObject.isNull(str)) {
                    return jSONObject.getString(str);
                }
            } catch (JSONException e) {
                io.i(TAG, String.format("Problem parsing JSON for key %s. Error: %s", str, e.getMessage()));
            }
        }
        return str2;
    }

    public static JSONObject b(JSONObject jSONObject, String str) {
        if (jSONObject != null) {
            try {
                if (jSONObject.has(str) && !jSONObject.isNull(str)) {
                    return jSONObject.getJSONObject(str);
                }
                return null;
            } catch (JSONException e) {
                io.i(TAG, String.format("Problem parsing JSON for key %s. Error: %s", str, e.getMessage()));
                return null;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0084 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.json.JSONObject f(java.net.HttpURLConnection r11) throws org.json.JSONException {
        /*
            java.lang.String r0 = "IOException thrown closing input stream"
            r1 = 1
            r2 = 0
            r3 = 0
            java.io.InputStream r4 = r11.getInputStream()     // Catch: java.lang.Throwable -> Lb java.io.IOException -> Lf
            r5 = r2
            goto L14
        Lb:
            r11 = move-exception
            r4 = r3
            goto L82
        Lf:
            java.io.InputStream r4 = r11.getErrorStream()     // Catch: java.lang.Throwable -> Lb
            r5 = r1
        L14:
            if (r4 != 0) goto L22
            if (r4 == 0) goto L21
            r4.close()     // Catch: java.io.IOException -> L1c
            goto L21
        L1c:
            java.lang.String r11 = com.amazon.identity.auth.device.ik.TAG
            com.amazon.identity.auth.device.io.e(r11, r0)
        L21:
            return r3
        L22:
            byte[] r6 = com.amazon.identity.auth.device.jd.a(r4)     // Catch: java.lang.Throwable -> L6e java.io.IOException -> L70
            java.lang.String r7 = new java.lang.String     // Catch: java.lang.Throwable -> L6e java.io.IOException -> L70
            r7.<init>(r6)     // Catch: java.lang.Throwable -> L6e java.io.IOException -> L70
            java.lang.String r6 = "Request to %s received response %s"
            r8 = 2
            java.lang.Object[] r9 = new java.lang.Object[r8]     // Catch: java.lang.Throwable -> L6e java.io.IOException -> L70
            java.net.URL r10 = r11.getURL()     // Catch: java.lang.Throwable -> L6e java.io.IOException -> L70
            java.lang.String r10 = r10.toString()     // Catch: java.lang.Throwable -> L6e java.io.IOException -> L70
            r9[r2] = r10     // Catch: java.lang.Throwable -> L6e java.io.IOException -> L70
            r9[r1] = r7     // Catch: java.lang.Throwable -> L6e java.io.IOException -> L70
            com.amazon.identity.auth.device.io.a(r6, r9)     // Catch: java.lang.Throwable -> L6e java.io.IOException -> L70
            if (r5 == 0) goto L5f
            java.lang.String r5 = "Request to %s received error code %s and response %s"
            r6 = 3
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.lang.Throwable -> L6e java.io.IOException -> L70
            java.net.URL r9 = r11.getURL()     // Catch: java.lang.Throwable -> L6e java.io.IOException -> L70
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> L6e java.io.IOException -> L70
            r6[r2] = r9     // Catch: java.lang.Throwable -> L6e java.io.IOException -> L70
            int r11 = r11.getResponseCode()     // Catch: java.lang.Throwable -> L6e java.io.IOException -> L70
            java.lang.String r11 = java.lang.Integer.toString(r11)     // Catch: java.lang.Throwable -> L6e java.io.IOException -> L70
            r6[r1] = r11     // Catch: java.lang.Throwable -> L6e java.io.IOException -> L70
            r6[r8] = r7     // Catch: java.lang.Throwable -> L6e java.io.IOException -> L70
            com.amazon.identity.auth.device.io.a(r5, r6)     // Catch: java.lang.Throwable -> L6e java.io.IOException -> L70
        L5f:
            org.json.JSONObject r11 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L6e java.io.IOException -> L70
            r11.<init>(r7)     // Catch: java.lang.Throwable -> L6e java.io.IOException -> L70
            r4.close()     // Catch: java.io.IOException -> L68
            goto L6d
        L68:
            java.lang.String r1 = com.amazon.identity.auth.device.ik.TAG
            com.amazon.identity.auth.device.io.e(r1, r0)
        L6d:
            return r11
        L6e:
            r11 = move-exception
            goto L82
        L70:
            r11 = move-exception
            java.lang.String r1 = com.amazon.identity.auth.device.ik.TAG     // Catch: java.lang.Throwable -> L6e
            java.lang.String r2 = "Could not parse response because of network issue"
            com.amazon.identity.auth.device.io.e(r1, r2, r11)     // Catch: java.lang.Throwable -> L6e
            r4.close()     // Catch: java.io.IOException -> L7c
            goto L81
        L7c:
            java.lang.String r11 = com.amazon.identity.auth.device.ik.TAG
            com.amazon.identity.auth.device.io.e(r11, r0)
        L81:
            return r3
        L82:
            if (r4 == 0) goto L8d
            r4.close()     // Catch: java.io.IOException -> L88
            goto L8d
        L88:
            java.lang.String r1 = com.amazon.identity.auth.device.ik.TAG
            com.amazon.identity.auth.device.io.e(r1, r0)
        L8d:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.ik.f(java.net.HttpURLConnection):org.json.JSONObject");
    }

    public static String j(Map<String, String> map) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            arrayList.add(String.format("\"%s\":\"%s\"", entry.getKey(), entry.getValue()));
        }
        return String.format("{%s}", TextUtils.join(", ", arrayList));
    }
}
