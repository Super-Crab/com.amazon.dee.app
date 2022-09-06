package com.amazon.identity.auth.device;

import android.os.Build;
import android.text.TextUtils;
import com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse;
import com.amazon.identity.auth.map.device.token.MAPCookie;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class lq {
    public static final String TAG = "com.amazon.identity.auth.device.lq";
    private String mAccessToken;
    private String uq;
    private Map<String, String> ur = new HashMap();
    private String us;
    private String ut;

    public lq az(String str, String str2) {
        this.ur.put(str, str2);
        return this;
    }

    public lq eB(String str) {
        this.mAccessToken = str;
        return this;
    }

    public lq eC(String str) {
        this.uq = str;
        return this;
    }

    public lq eD(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.us = str;
        }
        return this;
    }

    public lq eE(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.ut = str;
        }
        return this;
    }

    public lp il() {
        boolean z;
        LinkedList linkedList = new LinkedList();
        if (TextUtils.isEmpty(this.mAccessToken)) {
            linkedList.add("AccessToken");
        }
        if (TextUtils.isEmpty(this.uq)) {
            linkedList.add("CookieDomain");
        }
        if (!linkedList.isEmpty()) {
            io.e(TAG, "Missing arguments for Panda: " + TextUtils.join(",", linkedList));
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(AbstractJSONTokenResponse.ACCESS_TOKEN, this.mAccessToken);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(HttpClientModule.ElementsRequestKey.BEARER, jSONObject2);
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("token_type", HttpClientModule.ElementsRequestKey.BEARER);
            JSONArray jSONArray = new JSONArray();
            for (Map.Entry<String, String> entry : this.ur.entrySet()) {
                JSONObject jSONObject5 = new JSONObject();
                jSONObject5.put(MAPCookie.KEY_NAME, entry.getKey());
                jSONObject5.put(MAPCookie.KEY_VALUE, entry.getValue());
                jSONArray.put(jSONObject5);
            }
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("domain", this.uq);
            jSONObject6.put("existing_cookies", jSONArray);
            JSONArray jSONArray2 = new JSONArray();
            jSONArray2.put(jSONObject6);
            JSONObject jSONObject7 = new JSONObject();
            jSONObject7.put("token_type", "website_cookies");
            jSONObject7.put("website_cookies", jSONArray2);
            JSONArray jSONArray3 = new JSONArray();
            jSONArray3.put(jSONObject4);
            jSONArray3.put(jSONObject7);
            jSONObject.put("source_tokens", jSONObject3);
            jSONObject.put("requested_token_types", jSONArray3);
            jSONObject.put("device_hw_version", Build.MODEL);
            jSONObject.put("device_os_version", Build.FINGERPRINT);
            jSONObject.put("device_sdk_version", Integer.toString(Build.VERSION.SDK_INT));
            jSONObject.put("device_os_name", "android");
            jSONObject.putOpt("app_name", this.us);
            jSONObject.putOpt("app_version", this.ut);
            return new lp(jSONObject);
        } catch (JSONException e) {
            String str = TAG;
            io.e(str, "Error creating JSON request: " + e.getMessage());
            return null;
        }
    }
}
