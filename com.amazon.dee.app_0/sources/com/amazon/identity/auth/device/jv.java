package com.amazon.identity.auth.device;

import android.text.TextUtils;
import com.amazon.dee.sdk.iotsoftap.Constants;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class jv {
    private static final String TAG = "com.amazon.identity.auth.device.jv";

    private jv() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JSONObject a(JSONObject jSONObject, JSONObject jSONObject2, String str) {
        try {
            JSONObject jSONObject3 = new JSONObject();
            if (!TextUtils.isEmpty(str)) {
                jSONObject3.put("dat", str);
            } else {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'", Locale.US);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone(Constants.UTC));
                jSONObject3.put("dat", simpleDateFormat.format(new Date()));
            }
            jSONObject3.put("dev", jSONObject);
            jSONObject3.put("cust", jSONObject2);
            return jSONObject3;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject ha() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("typ", "v1");
        return jSONObject;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r1v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static java.lang.String hb() {
        /*
            java.lang.String r0 = "Error closing bufferReader in getCPUId()!"
            r1 = 0
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L3c
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L3c
            java.lang.String r4 = "/proc/cpuinfo"
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L3c
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L3c
        Lf:
            java.lang.String r3 = r2.readLine()     // Catch: java.lang.Exception -> L35 java.lang.Throwable -> L78
            if (r3 == 0) goto L2a
            java.lang.String r4 = "Serial"
            boolean r4 = r3.startsWith(r4)     // Catch: java.lang.Exception -> L35 java.lang.Throwable -> L78
            if (r4 == 0) goto Lf
            java.lang.String r4 = ":"
            java.lang.String[] r3 = r3.split(r4)     // Catch: java.lang.Exception -> L35 java.lang.Throwable -> L78
            r4 = 1
            r3 = r3[r4]     // Catch: java.lang.Exception -> L35 java.lang.Throwable -> L78
            java.lang.String r1 = r3.trim()     // Catch: java.lang.Exception -> L35 java.lang.Throwable -> L78
        L2a:
            r2.close()     // Catch: java.io.IOException -> L2e
            goto L34
        L2e:
            r2 = move-exception
            java.lang.String r3 = com.amazon.identity.auth.device.jv.TAG
            com.amazon.identity.auth.device.io.e(r3, r0, r2)
        L34:
            return r1
        L35:
            r3 = move-exception
            goto L3e
        L37:
            r2 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
            goto L79
        L3c:
            r3 = move-exception
            r2 = r1
        L3e:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L78
            java.lang.String r5 = "GetCPUInfoFailed:"
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L78
            java.lang.Class r5 = r3.getClass()     // Catch: java.lang.Throwable -> L78
            java.lang.String r5 = r5.getSimpleName()     // Catch: java.lang.Throwable -> L78
            r4.append(r5)     // Catch: java.lang.Throwable -> L78
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L78
            java.lang.String r5 = "GenerateJWTFailure:"
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch: java.lang.Throwable -> L78
            java.lang.String r4 = r5.concat(r4)     // Catch: java.lang.Throwable -> L78
            r5 = 0
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch: java.lang.Throwable -> L78
            com.amazon.identity.auth.device.mq.b(r4, r5)     // Catch: java.lang.Throwable -> L78
            java.lang.String r4 = com.amazon.identity.auth.device.jv.TAG     // Catch: java.lang.Throwable -> L78
            java.lang.String r5 = "Exception thrown when getting the serial number(cpuId)"
            com.amazon.identity.auth.device.io.e(r4, r5, r3)     // Catch: java.lang.Throwable -> L78
            if (r2 == 0) goto L77
            r2.close()     // Catch: java.io.IOException -> L71
            goto L77
        L71:
            r2 = move-exception
            java.lang.String r3 = com.amazon.identity.auth.device.jv.TAG
            com.amazon.identity.auth.device.io.e(r3, r0, r2)
        L77:
            return r1
        L78:
            r1 = move-exception
        L79:
            if (r2 == 0) goto L85
            r2.close()     // Catch: java.io.IOException -> L7f
            goto L85
        L7f:
            r2 = move-exception
            java.lang.String r3 = com.amazon.identity.auth.device.jv.TAG
            com.amazon.identity.auth.device.io.e(r3, r0, r2)
        L85:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.jv.hb():java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JSONObject w(String str, String str2, String str3) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("typ", "v1");
        jSONObject.put("dt", str);
        jSONObject.put("dsn", str2);
        if (!TextUtils.isEmpty(str3)) {
            jSONObject.put("ds", str3);
        }
        jSONObject.put("cpuid", hb());
        return jSONObject;
    }
}
