package com.amazon.identity.auth.device;

import com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class bq {
    private static final String TAG = "bq";
    private ea at;
    private ej bR;
    private ed o;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a {
        public String hA;
        public long hw;

        a(String str, String str2) {
            this.hA = str;
            this.hw = Long.parseLong(str2) * 1000;
        }
    }

    public bq(ed edVar, ej ejVar) {
        this.o = edVar;
        this.bR = ejVar;
        this.at = (ea) this.o.getSystemService("dcp_device_info");
    }

    public String a(long j, String str, String str2) throws JSONException {
        bo boVar = new bo(this.o, this.bR);
        JSONObject bw = boVar.bw();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(AbstractJSONTokenResponse.ACCESS_TOKEN, str);
        jSONObject.put("actor_id", str2);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("code_data", bw);
        jSONObject2.put("auth_data", jSONObject);
        boVar.a(jSONObject2, 0, j);
        fm.a(fm.h(this.o, this.at.getDeviceSerialNumber()), jSONObject2);
        return jSONObject2.toString();
    }

    public a e(JSONObject jSONObject) {
        try {
            return new a(jSONObject.getString("code"), jSONObject.getString("expires_in"));
        } catch (JSONException e) {
            io.e(TAG, "JSONException while parsing generatePreAuthorizedCode response, probably due to server response is missing some data", e);
            return null;
        }
    }
}
