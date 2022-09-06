package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class bf extends ca {
    private final String mSignature;

    public bf(Context context, String str) {
        super(context);
        this.mSignature = str;
    }

    @Override // com.amazon.identity.auth.device.ca
    public String bo() {
        return "/auth/authority/signature";
    }

    public JSONObject bp() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("appSignature", this.mSignature);
        jSONObject.put(CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME, bC().getApplicationContext().getPackageName());
        return jSONObject;
    }
}
