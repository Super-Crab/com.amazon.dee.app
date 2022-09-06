package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class bz extends cb {
    private final String eg;
    private final String hL;
    private final String hM;
    private JSONArray hN;

    public bz(Context context, String str, String str2, String str3, List<String> list) {
        super(context);
        this.hN = new JSONArray();
        this.hL = str;
        this.eg = str3;
        this.hM = str2;
        if (!hz.isEmpty(list)) {
            for (String str4 : list) {
                this.hN.put(str4);
            }
            return;
        }
        this.hN.put("profile:user_id");
    }

    @Override // com.amazon.identity.auth.device.cb
    public JSONObject a(ej ejVar) throws JSONException {
        JSONObject a = super.a(ejVar);
        a.put("scope_list", this.hN);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("client_id", this.eg);
        jSONObject.put("client_secret", this.hM);
        a.put("client_auth_data", jSONObject);
        return a;
    }

    @Override // com.amazon.identity.auth.device.cb
    public String bA() {
        return this.hL;
    }

    @Override // com.amazon.identity.auth.device.cb
    public String bB() {
        return "scope_access_token";
    }

    @Override // com.amazon.identity.auth.device.cb
    public String bz() {
        return AbstractJSONTokenResponse.REFRESH_TOKEN;
    }
}
