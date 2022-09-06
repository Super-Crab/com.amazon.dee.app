package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.amazon.identity.auth.device.api.DefaultCallback;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.identity.auth.device.utils.AccountConstants;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class bh extends ca {
    private static final String TAG = "com.amazon.identity.auth.device.bh";
    private final TokenManagement au;
    private final String bP;
    private final String bm;
    private final String gT;
    private final String gU;
    private final Context mContext;

    public bh(Context context, String str, String str2, String str3, String str4) {
        this(context, str, str2, str4, new TokenManagement(context), str3);
    }

    private String aI(String str) {
        try {
            return this.au.getToken(str, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN, new Bundle(), new DefaultCallback()).get().getString("value_key");
        } catch (Exception e) {
            io.e(TAG, "Exception while trying to get the refresh token in the authorizeLinkCode API", e);
            return null;
        }
    }

    @Override // com.amazon.identity.auth.device.ca
    public String bo() {
        return "/auth/bootstrap/sso";
    }

    public JSONObject bp() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME, this.bm);
        jSONObject2.put("appSignature", this.gT);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("refreshToken", aI(this.bP));
        jSONObject3.put("appSignature", this.gU);
        jSONObject.put("bootstrapAppInfo", jSONObject2);
        jSONObject.put("hostAppInfo", jSONObject3);
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String bq() {
        return bD().cu();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getDeviceType() {
        return ie.s(bC(), bC().getPackageName());
    }

    bh(Context context, String str, String str2, String str3, TokenManagement tokenManagement, String str4) {
        super(context);
        this.mContext = context;
        this.bm = str4;
        this.gT = str2;
        this.gU = str3;
        this.bP = str;
        this.au = tokenManagement;
    }
}
