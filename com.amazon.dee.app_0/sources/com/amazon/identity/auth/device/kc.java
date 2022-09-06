package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class kc {
    private static final String TAG = "com.amazon.identity.auth.device.kc";
    private String mReason;
    private String se;
    private String sf;
    private String sg;
    private String sh;

    public kc(String str, String str2, String str3, String str4, String str5) {
        this.mReason = str;
        this.sf = str3;
        this.se = dL(str2);
        this.sg = str4;
        this.sh = str5;
    }

    private String dL(String str) {
        try {
            return new URL(str).toString();
        } catch (MalformedURLException unused) {
            io.w(TAG, " Malformed URL received: ".concat(String.valueOf(str)));
            return null;
        }
    }

    public static kc s(JSONObject jSONObject) throws JSONException {
        return new kc(jSONObject.getString("challenge_reason"), jSONObject.optString("uri", null), jSONObject.optString("challenge_context", null), jSONObject.optString("required_authentication_method", null), jSONObject.optString(MAPAccountManager.KEY_AUTH_DATA_ADDITIONAL_INFO, null));
    }

    public void Q(Bundle bundle) {
        String str = this.sh;
        if (str != null) {
            bundle.putString(MAPAccountManager.KEY_AUTH_DATA_ADDITIONAL_INFO, str);
        }
    }

    public String getReason() {
        return this.mReason;
    }

    public String hj() {
        return this.sh;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("com.amazon.identity.auth.ChallengeException.Reason", this.mReason);
        bundle.putString("com.amazon.identity.auth.ChallengeException.oAuthURI", this.se);
        bundle.putString("com.amazon.identity.auth.ChallengeException.Context", this.sf);
        bundle.putString("com.amazon.identity.auth.ChallengeException.requiredAuthenticationMethod", this.sg);
        bundle.putString(MAPAccountManager.KEY_AUTH_DATA_ADDITIONAL_INFO, this.sh);
        return bundle;
    }
}
