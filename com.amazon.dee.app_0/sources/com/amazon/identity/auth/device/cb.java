package com.amazon.identity.auth.device;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class cb extends ca {
    public cb(Context context) {
        super(context);
    }

    public JSONObject a(ej ejVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("app_name", bC().getPackageName());
        jSONObject.put("app_version", hv.gt());
        jSONObject.put("device_metadata", fm.b(ie.s(bC(), bC().getPackageName()), bD().cu(), ejVar));
        jSONObject.put("source_token_type", bz());
        jSONObject.put("source_token", bA());
        jSONObject.put("requested_token_type", bB());
        return jSONObject;
    }

    public abstract String bA();

    public abstract String bB();

    @Override // com.amazon.identity.auth.device.ca
    public String bo() {
        return "/auth/token";
    }

    public abstract String bz();
}
