package com.amazon.identity.auth.device;

import android.os.Build;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class bo {
    private static final String TAG = "bo";
    private ea at;
    private ej bR;
    private ed o;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a {
        public String hs;
        public String ht;
        public long hw;
        public long hx;
        public long hy;

        a(String str, String str2, String str3, String str4) {
            this.hs = str;
            this.ht = str2;
            this.hw = Long.parseLong(str3) * 1000;
            this.hx = System.currentTimeMillis() + this.hw;
            this.hy = Long.parseLong(str4);
        }
    }

    public bo(ed edVar, ej ejVar) {
        this.o = edVar;
        this.bR = ejVar;
        this.at = (ea) this.o.getSystemService("dcp_device_info");
    }

    public String a(int i, long j) throws JSONException {
        JSONObject bw = bw();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("code_data", bw);
        a(jSONObject, i, j);
        fm.a(fm.h(this.o, this.at.getDeviceSerialNumber()), jSONObject);
        return jSONObject.toString();
    }

    public JSONObject bw() throws JSONException {
        String packageName = this.o.getPackageName();
        Long x = it.x(this.o, packageName);
        String l = x != null ? Long.toString(x.longValue()) : "defaultAppVersion";
        try {
            String deviceSerialNumber = this.at.getDeviceSerialNumber();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("domain", "Device");
            if (TextUtils.isEmpty(packageName)) {
                packageName = "defaultAppName";
            }
            jSONObject.put("app_name", packageName);
            jSONObject.put("app_version", l);
            jSONObject.put("device_model", Build.MODEL);
            jSONObject.put("os_version", Integer.toString(Build.VERSION.SDK_INT));
            jSONObject.put("device_type", this.at.getDeviceType());
            jSONObject.put("device_serial", deviceSerialNumber);
            return jSONObject;
        } catch (UnsupportedOperationException e) {
            io.e(TAG, "Got an unsupported operation exception while trying to get the device serial number", e);
            return null;
        }
    }

    public a c(JSONObject jSONObject) {
        try {
            return new a(jSONObject.getString("public_code"), jSONObject.getString("private_code"), jSONObject.getString("expires_in"), jSONObject.getString("polling_interval_in_seconds"));
        } catch (JSONException e) {
            io.e(TAG, "JSONException while parsing createCodePair response", e);
            return null;
        }
    }

    public void a(JSONObject jSONObject, int i, long j) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        if (i > 0) {
            jSONObject2.put("code_length", Integer.toString(i));
        }
        if (j > 0) {
            jSONObject2.put("code_duration", Long.toString(j / 1000));
        }
        if (jSONObject2.length() > 0) {
            jSONObject.put("code_properties", jSONObject2);
        }
    }
}
