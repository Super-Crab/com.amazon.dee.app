package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class bd {
    private static final String TAG = "com.amazon.identity.auth.device.bd";
    private ej bR;
    private String gP;
    private String gQ;
    private HttpURLConnection gR;
    private String mAccessToken;

    public bd(String str, Bundle bundle, Set<String> set, ej ejVar) {
        this.mAccessToken = str;
        this.gP = EnvironmentUtils.cd().A(bundle);
        this.gQ = EnvironmentUtils.cd().getPandaHost(hr.I(bundle));
        this.bR = ejVar;
        this.gR = e(set);
    }

    private HttpURLConnection e(Set<String> set) {
        String str;
        if (hz.isEmpty(set)) {
            str = "/user/profile";
        } else {
            StringBuilder sb = new StringBuilder();
            for (String str2 : set) {
                if (sb.length() > 0) {
                    sb.append(WebConstants.UriConstants.AMPERSAND_KEY);
                }
                sb.append("attributes=");
                sb.append(str2);
            }
            str = "/user/profile?" + sb.toString();
        }
        try {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) cy.openConnection(EnvironmentUtils.cd().n(this.gQ, str));
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestProperty("Authorization", "Bearer " + this.mAccessToken);
                httpURLConnection.setRequestProperty("User-Agent", iy.rA);
                httpURLConnection.setRequestProperty("x-amzn-identity-auth-domain", this.gP);
                String str3 = TAG;
                new StringBuilder("PandaUserProfileRequest url: ").append(httpURLConnection.getURL());
                io.dm(str3);
                return httpURLConnection;
            } catch (IOException e) {
                io.a(TAG, this.bR, "IOException happens when trying to open Panda connection", "MAPUserProfileError:IOException", e);
                return null;
            }
        } catch (MalformedURLException e2) {
            throw new IllegalArgumentException(String.format("MalformedURLException when generating %s url. This should never happen.", str), e2);
        }
    }

    ArrayList<String> a(JSONObject jSONObject, List<String> list) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (hz.isEmpty(list)) {
            return arrayList;
        }
        if (jSONObject != null && jSONObject.length() != 0) {
            for (String str : list) {
                if (!jSONObject.has(str)) {
                    arrayList.add(str);
                } else {
                    try {
                        if (TextUtils.isEmpty(jSONObject.getString(str))) {
                            arrayList.add(str);
                        }
                    } catch (JSONException unused) {
                        arrayList.add(str);
                    }
                }
            }
            return arrayList;
        }
        io.e(TAG, "ProfileJSON is null or empty");
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0078, code lost:
        if (r1 == null) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0089, code lost:
        if (r1 == null) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x008b, code lost:
        r1.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x008e, code lost:
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.json.JSONObject bn() {
        /*
            r6 = this;
            r0 = 0
            java.net.HttpURLConnection r1 = r6.gR     // Catch: java.lang.Throwable -> L68 java.io.IOException -> L6a org.json.JSONException -> L7b
            if (r1 != 0) goto Ld
            java.net.HttpURLConnection r1 = r6.gR
            if (r1 == 0) goto Lc
            r1.disconnect()
        Lc:
            return r0
        Ld:
            java.net.HttpURLConnection r1 = r6.gR     // Catch: java.lang.Throwable -> L68 java.io.IOException -> L6a org.json.JSONException -> L7b
            com.amazon.identity.auth.device.framework.RetryLogic.e(r1)     // Catch: java.lang.Throwable -> L68 java.io.IOException -> L6a org.json.JSONException -> L7b
            java.net.HttpURLConnection r1 = r6.gR     // Catch: java.lang.Throwable -> L68 java.io.IOException -> L6a org.json.JSONException -> L7b
            int r1 = com.amazon.identity.auth.device.framework.RetryLogic.d(r1)     // Catch: java.lang.Throwable -> L68 java.io.IOException -> L6a org.json.JSONException -> L7b
            java.lang.String r2 = com.amazon.identity.auth.device.bd.TAG     // Catch: java.lang.Throwable -> L68 java.io.IOException -> L6a org.json.JSONException -> L7b
            java.lang.String r3 = "Response received from Panda user profile API. Response Code:"
            java.lang.String r4 = java.lang.String.valueOf(r1)     // Catch: java.lang.Throwable -> L68 java.io.IOException -> L6a org.json.JSONException -> L7b
            java.lang.String r3 = r3.concat(r4)     // Catch: java.lang.Throwable -> L68 java.io.IOException -> L6a org.json.JSONException -> L7b
            com.amazon.identity.auth.device.io.i(r2, r3)     // Catch: java.lang.Throwable -> L68 java.io.IOException -> L6a org.json.JSONException -> L7b
            com.amazon.identity.auth.device.framework.AuthEndpointErrorParser r2 = new com.amazon.identity.auth.device.framework.AuthEndpointErrorParser     // Catch: java.lang.Throwable -> L68 java.io.IOException -> L6a org.json.JSONException -> L7b
            r2.<init>()     // Catch: java.lang.Throwable -> L68 java.io.IOException -> L6a org.json.JSONException -> L7b
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: java.lang.Throwable -> L68 java.io.IOException -> L6a org.json.JSONException -> L7b
            boolean r1 = com.amazon.identity.auth.device.framework.AuthEndpointErrorParser.a(r1)     // Catch: java.lang.Throwable -> L68 java.io.IOException -> L6a org.json.JSONException -> L7b
            if (r1 == 0) goto L45
            java.lang.String r1 = com.amazon.identity.auth.device.bd.TAG     // Catch: java.lang.Throwable -> L68 java.io.IOException -> L6a org.json.JSONException -> L7b
            java.lang.String r2 = "Error happens when calling Panda user profile api"
            com.amazon.identity.auth.device.io.e(r1, r2)     // Catch: java.lang.Throwable -> L68 java.io.IOException -> L6a org.json.JSONException -> L7b
            java.net.HttpURLConnection r1 = r6.gR
            if (r1 == 0) goto L44
            r1.disconnect()
        L44:
            return r0
        L45:
            java.net.HttpURLConnection r1 = r6.gR     // Catch: java.lang.Throwable -> L68 java.io.IOException -> L6a org.json.JSONException -> L7b
            org.json.JSONObject r1 = com.amazon.identity.auth.device.ik.f(r1)     // Catch: java.lang.Throwable -> L68 java.io.IOException -> L6a org.json.JSONException -> L7b
            if (r1 == 0) goto L60
            java.lang.String r2 = com.amazon.identity.auth.device.bd.TAG     // Catch: java.lang.Throwable -> L68 java.io.IOException -> L6a org.json.JSONException -> L7b
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L68 java.io.IOException -> L6a org.json.JSONException -> L7b
            java.lang.String r4 = "Panda user profile response json:"
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L68 java.io.IOException -> L6a org.json.JSONException -> L7b
            java.lang.String r4 = r1.toString()     // Catch: java.lang.Throwable -> L68 java.io.IOException -> L6a org.json.JSONException -> L7b
            r3.append(r4)     // Catch: java.lang.Throwable -> L68 java.io.IOException -> L6a org.json.JSONException -> L7b
            com.amazon.identity.auth.device.io.dm(r2)     // Catch: java.lang.Throwable -> L68 java.io.IOException -> L6a org.json.JSONException -> L7b
        L60:
            java.net.HttpURLConnection r0 = r6.gR
            if (r0 == 0) goto L67
            r0.disconnect()
        L67:
            return r1
        L68:
            r0 = move-exception
            goto L8f
        L6a:
            r1 = move-exception
            java.lang.String r2 = com.amazon.identity.auth.device.bd.TAG     // Catch: java.lang.Throwable -> L68
            com.amazon.identity.auth.device.ej r3 = r6.bR     // Catch: java.lang.Throwable -> L68
            java.lang.String r4 = "IOException happens when trying to call user profile"
            java.lang.String r5 = "MAPUserProfileError:IOException"
            com.amazon.identity.auth.device.io.a(r2, r3, r4, r5, r1)     // Catch: java.lang.Throwable -> L68
            java.net.HttpURLConnection r1 = r6.gR
            if (r1 == 0) goto L8e
            goto L8b
        L7b:
            r1 = move-exception
            java.lang.String r2 = com.amazon.identity.auth.device.bd.TAG     // Catch: java.lang.Throwable -> L68
            com.amazon.identity.auth.device.ej r3 = r6.bR     // Catch: java.lang.Throwable -> L68
            java.lang.String r4 = "JSONException happens when trying to call user profile"
            java.lang.String r5 = "MAPUserProfileError:JSONException"
            com.amazon.identity.auth.device.io.a(r2, r3, r4, r5, r1)     // Catch: java.lang.Throwable -> L68
            java.net.HttpURLConnection r1 = r6.gR
            if (r1 == 0) goto L8e
        L8b:
            r1.disconnect()
        L8e:
            return r0
        L8f:
            java.net.HttpURLConnection r1 = r6.gR
            if (r1 == 0) goto L96
            r1.disconnect()
        L96:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.bd.bn():org.json.JSONObject");
    }

    public ArrayList<String> c(List<String> list) {
        return a(bn(), list);
    }
}
