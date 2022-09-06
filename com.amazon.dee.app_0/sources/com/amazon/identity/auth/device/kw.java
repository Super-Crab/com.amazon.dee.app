package com.amazon.identity.auth.device;

import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse;
import java.security.MessageDigest;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class kw {
    private static final String TAG = "com.amazon.identity.auth.device.kw";
    private String bP;
    private String bk;
    private String mAccessToken;
    private mc sA;
    private String sB;
    private String sC;
    private String sD;
    private ks sE;
    private boolean sF = true;
    private String sw;
    private String sx;
    private String sy;
    private String sz;

    public void a(ks ksVar) {
        this.sE = ksVar;
    }

    public void b(ea eaVar) {
        dU(eaVar.getDeviceSerialNumber());
        dT(eaVar.getDeviceType());
        a(eaVar.dR());
    }

    public boolean dP(String str) {
        boolean z;
        if (ma.isNullOrEmpty(str)) {
            io.i(TAG, " isValidUserID: returning false because a null or empty user ID was given.");
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            io.e(TAG, " setUserID: userID was invalid. Cannot be set.");
            return false;
        }
        this.sw = str;
        return true;
    }

    public boolean dQ(String str) {
        boolean z;
        if (ma.isNullOrEmpty(str)) {
            io.i(TAG, " isValidDirectedId: returning false because a null or empty directedId was given.");
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            io.e(TAG, " setDirectedId: directedId was invalid. Cannot be set.");
            return false;
        }
        this.bP = str;
        return true;
    }

    public boolean dR(String str) {
        if (TextUtils.isEmpty(str)) {
            io.e(TAG, "setAccessToken: access_token is not empty. Cannot be set.");
            return false;
        }
        this.mAccessToken = str;
        return true;
    }

    public boolean dS(String str) {
        boolean z;
        if (ma.isNullOrEmpty(str)) {
            io.i(TAG, " isValidPassword: returning false because a null or empty password was given.");
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            io.e(TAG, " setPassword: password was invalid. Cannot be set.");
            return false;
        }
        this.sx = str;
        return true;
    }

    public boolean dT(String str) {
        if (!ma.eG(str)) {
            io.e(TAG, " setDeviceType: deviceType was invalid. Cannot be set.");
            return false;
        }
        this.bk = str;
        return true;
    }

    public boolean dU(String str) {
        if (!ma.eH(str)) {
            io.e(TAG, " setDeviceSerialNumber: device serial number was invalid. Cannot be set.");
            return false;
        }
        this.sz = str;
        return true;
    }

    public void dV(String str) {
        this.sB = str;
    }

    public void dW(String str) {
        this.sC = str;
    }

    public void dX(String str) {
        this.sy = str;
    }

    public void dY(String str) {
        if (!TextUtils.isEmpty(str)) {
            io.dm(TAG);
        }
        this.sD = str;
    }

    public JSONObject hy() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject.put("auth_data", jSONObject2);
        if (this.sF) {
            jSONObject2.put("use_global_authentication", "true");
        } else {
            jSONObject2.put("use_global_authentication", PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE);
        }
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put(MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD, this.sx);
        if (!TextUtils.isEmpty(this.sw) && !TextUtils.isEmpty(this.bP)) {
            throw new IllegalStateException("Invalid parameters. Cannot set both login and directedId.");
        }
        if (!TextUtils.isEmpty(this.sw)) {
            jSONObject3.put("user_id", this.sw);
            jSONObject2.put("user_id_password", jSONObject3);
        } else if (!TextUtils.isEmpty(this.bP)) {
            jSONObject3.put("directedId", this.bP);
            jSONObject2.put("directedId_password", jSONObject3);
        } else if (!TextUtils.isEmpty(this.mAccessToken)) {
            jSONObject3.put(AbstractJSONTokenResponse.ACCESS_TOKEN, this.mAccessToken);
            jSONObject2.put("access_token_password", jSONObject3);
        } else {
            throw new IllegalStateException("Invalid parameters.");
        }
        if (!TextUtils.isEmpty(this.sy)) {
            jSONObject2.put("trusted_device_token", this.sy);
        }
        JSONObject jSONObject4 = new JSONObject();
        jSONObject.put("signin_data", jSONObject4);
        jSONObject4.put("device_serial", this.sz);
        jSONObject4.put("device_type", this.bk);
        jSONObject4.put("domain", "Device");
        String str = this.sB;
        if (str == null) {
            str = "defaultAppName";
        }
        jSONObject4.put("app_name", str);
        String str2 = this.sC;
        if (str2 == null) {
            str2 = "defaultAppVersion";
        }
        jSONObject4.put("app_version", str2);
        jSONObject4.put("device_model", Build.MODEL);
        jSONObject4.put("os_version", Build.FINGERPRINT);
        mc mcVar = this.sA;
        jSONObject4.put("software_version", mcVar != null ? mcVar.getString() : "defaultSoftwareVersion");
        fm.a(this.sD, jSONObject);
        if (this.sA == null) {
            io.e(TAG, " software_version was undefined.");
        }
        if (this.sE != null) {
            try {
                JSONObject w = jv.w(this.bk, this.sz, null);
                JSONObject ha = jv.ha();
                if (!TextUtils.isEmpty(this.sw)) {
                    ha.put("email_hash", Base64.encodeToString(MessageDigest.getInstance("SHA256").digest(this.sw.getBytes("UTF-8")), 2));
                } else if (!TextUtils.isEmpty(this.bP)) {
                    ha.put("directed_id", this.bP);
                }
                jSONObject4.put("device_authentication_token", this.sE.d("drvV2", jv.a(w, ha, null)));
            } catch (Exception e) {
                io.e(TAG, "Failed to sign JWT", e);
            }
        }
        return jSONObject;
    }

    public void k(boolean z) {
        this.sF = z;
    }

    public void a(mc mcVar) {
        if (mcVar != null && mcVar.isValid()) {
            this.sA = mcVar;
        } else {
            io.e(TAG, " setDeviceSoftwareVersion: device software version is invalid. Cannot be set.");
        }
    }
}
