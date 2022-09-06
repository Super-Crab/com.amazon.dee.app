package com.amazon.identity.kcpsdk.auth;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.device.information.contract.DeviceInformationContract;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.fm;
import com.amazon.identity.auth.device.hr;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.jv;
import com.amazon.identity.auth.device.lc;
import com.amazon.identity.auth.device.lg;
import com.amazon.identity.auth.device.lq;
import com.amazon.identity.auth.device.ma;
import com.amazon.identity.auth.device.mb;
import com.amazon.identity.auth.device.mc;
import com.amazon.identity.auth.device.md;
import com.amazon.identity.auth.device.mz;
import com.amazon.identity.auth.device.token.MAPCookie;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.map.device.AccountManagerConstants;
import com.amazon.identity.kcpsdk.common.HttpVerb;
import com.amazon.identity.kcpsdk.common.WebProtocol;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class RegisterDeviceRequest extends lg {
    static final String TAG = "com.amazon.identity.kcpsdk.auth.RegisterDeviceRequest";
    private String bn;
    private String eg;
    private String nm;
    private String nn;
    private ed o;
    private String qZ;
    private mc sA;
    private String sB;
    private String sC;
    private String sD;
    private boolean sF;
    private String sS;
    private String sY;
    private String sZ;
    private String sx;
    private String tA;
    private String tB;
    private String tC;
    private String tD;
    private String tE;
    private a tF;
    private lc tG;
    private String ta;
    private CustomerAccountTokenType tb;
    private Bundle tc;
    private String td;
    private String te;
    private String tf;
    private String tg;
    private String th;
    private String ti;
    private String tj;
    private boolean tk;
    private boolean tl;
    private DeviceAccountRole tm;
    private boolean tn;
    private boolean to;
    private RegisterEndpointEnum tp;
    private String tq;
    private List<MAPCookie> tr;
    private JSONObject ts;
    private String tt;
    private String tu;
    private Map<String, mb> tv;
    private String tw;
    private String tx;
    private String ty;
    private String tz;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum CustomerAccountTokenType {
        AT_MAIN("ATMain"),
        ACCESS_TOKEN("AccessToken"),
        AUTH_TOKEN("AuthToken");
        
        private final String mValue;

        CustomerAccountTokenType(String str) {
            this.mValue = str;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum DeviceAccountRole {
        UNDEFINED,
        PRIMARY,
        SECONDARY
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum RegisterEndpointEnum {
        FIRS,
        Panda
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a {
        String dk;

        public void l(String str) {
            this.dk = str;
        }
    }

    public RegisterDeviceRequest(ed edVar) {
        this(edVar, new Bundle());
    }

    public static boolean eh(String str) {
        if (ma.isNullOrEmpty(str)) {
            io.i(TAG, "isValidCustomerAccountToken: returning false because a null or empty auth token was given");
            return false;
        }
        return true;
    }

    private md hF() {
        md mdVar = this.rT;
        if (mdVar == null || this.tp != RegisterEndpointEnum.Panda) {
            if (this.tb == CustomerAccountTokenType.AUTH_TOKEN && (ma.isNullOrEmpty(this.ta) || ma.isNullOrEmpty(this.tq))) {
                io.e(TAG, "Customer token or ClientContext is not set. Use MAPAccountManager.KEY_AUTH_TOKEN and MAPAccountManager.KEY_AUTH_TOKEN_CONTEXT to pass them in regData.");
                return null;
            }
            this.rT = new md();
            this.rT.a(WebProtocol.WebProtocolHttps);
            this.rT.setHost(EnvironmentUtils.cd().getPandaHost(hr.I(this.tc)));
            this.rT.a(HttpVerb.HttpVerbPost);
            this.rT.setHeader("Content-Type", "application/json");
            this.rT.setHeader("x-amzn-identity-auth-domain", EnvironmentUtils.cd().A(this.tc));
            md mdVar2 = this.rT;
            EnvironmentUtils.cd();
            mdVar2.iF();
            this.rT.setPath("/auth/register");
            String str = this.td;
            if (str != null) {
                this.rT.setHeader("Accept-Language", str);
            }
            try {
                JSONObject jSONObject = new JSONObject();
                if (this.sF) {
                    jSONObject.put("use_global_authentication", "true");
                } else {
                    jSONObject.put("use_global_authentication", PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE);
                }
                if (this.tl) {
                    md mdVar3 = this.rT;
                    mdVar3.setHeader("Authorization", "Bearer " + this.tw);
                }
                if (!TextUtils.isEmpty(this.tj)) {
                    jSONObject.put("code", this.tj);
                } else if (!TextUtils.isEmpty(this.ti) && !TextUtils.isEmpty(this.th)) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("public_code", this.th);
                    jSONObject2.put("private_code", this.ti);
                    jSONObject.put("code_pair", jSONObject2);
                } else if (!TextUtils.isEmpty(this.tB) && !TextUtils.isEmpty(this.tC)) {
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("sso_code", this.tB);
                    jSONObject3.put("register_directedId", this.tC);
                    jSONObject3.put("host_device_type", this.tD);
                    jSONObject3.put("host_device_serial", this.tE);
                    jSONObject.put("sso_data", jSONObject3);
                } else if (!TextUtils.isEmpty(this.tx) && !TextUtils.isEmpty(this.tA)) {
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("user_id", this.tx);
                    jSONObject4.put(MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD, this.tA);
                    jSONObject.put("user_id_password", jSONObject4);
                } else if (!TextUtils.isEmpty(this.ty)) {
                    if (!TextUtils.isEmpty(this.tA)) {
                        JSONObject jSONObject5 = new JSONObject();
                        jSONObject5.put("directedId", this.ty);
                        jSONObject5.put(MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD, this.tA);
                        jSONObject.put("directedId_password", jSONObject5);
                    } else {
                        JSONObject jSONObject6 = new JSONObject();
                        jSONObject6.put("directedId", this.ty);
                        jSONObject.put("delegation_data", jSONObject6);
                    }
                } else if (TextUtils.isEmpty(this.ta) && !TextUtils.isEmpty(this.sY) && !TextUtils.isEmpty(this.sx)) {
                    JSONObject jSONObject7 = new JSONObject();
                    jSONObject7.put("user_id", this.sY);
                    jSONObject7.put(MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD, this.sx);
                    jSONObject.put("user_id_password", jSONObject7);
                } else if (TextUtils.isEmpty(this.ta) && !TextUtils.isEmpty(this.sZ) && !TextUtils.isEmpty(this.sx)) {
                    JSONObject jSONObject8 = new JSONObject();
                    jSONObject8.put("directedId", this.sZ);
                    jSONObject8.put(MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD, this.sx);
                    jSONObject.put("directedId_password", jSONObject8);
                } else if (TextUtils.isEmpty(this.ta) && !TextUtils.isEmpty(this.nm)) {
                    jSONObject.put(AccountConstants.KEY_AUTHORIZATION_CODE, this.nm);
                    jSONObject.put(AccountConstants.KEY_CODE_VERIFIER, this.nn);
                    jSONObject.put("code_algorithm", this.qZ);
                    jSONObject.put(AccountConstants.KEY_CLIENT_DOMAIN, AccountConstants.CLIENT_DOMAIN_DEVICE_LEGACY);
                    jSONObject.put("client_id", this.eg);
                } else if (this.tb == CustomerAccountTokenType.ACCESS_TOKEN) {
                    jSONObject.put(AbstractJSONTokenResponse.ACCESS_TOKEN, this.ta);
                } else if (this.tb == CustomerAccountTokenType.AUTH_TOKEN) {
                    JSONObject jSONObject9 = new JSONObject();
                    jSONObject9.put("atmain", this.ta);
                    jSONObject9.put("client_context", this.tq);
                    jSONObject9.put("max_age", 1209600);
                    jSONObject.put("auth_token", jSONObject9);
                }
                JSONObject jSONObject10 = new JSONObject();
                jSONObject10.put("domain", AccountConstants.CLIENT_DOMAIN_DEVICE_LEGACY);
                jSONObject10.put("device_type", this.bk);
                jSONObject10.put("device_serial", this.sz);
                jSONObject10.put("app_name", this.sB != null ? this.sB : "defaultAppName");
                jSONObject10.put("app_version", this.sC != null ? this.sC : "defaultAppVersion");
                jSONObject10.put("device_model", Build.MODEL);
                jSONObject10.put("os_version", Build.FINGERPRINT);
                jSONObject10.put("software_version", this.sA != null ? this.sA.getString() : "defaultSoftwareVersion");
                if (!mz.f(this.o)) {
                    String ib = ib();
                    if (!TextUtils.isEmpty(ib)) {
                        jSONObject10.put("device_authentication_token", ib);
                    }
                    if (!TextUtils.isEmpty(this.ua)) {
                        String str2 = TAG;
                        new StringBuilder("Setting device secret: ").append(this.ua);
                        io.dm(str2);
                        jSONObject10.put("device_secret", this.ua);
                    }
                }
                if (!TextUtils.isEmpty(this.sS)) {
                    jSONObject10.put("device_name", this.sS);
                }
                if (!TextUtils.isEmpty(this.tt)) {
                    jSONObject10.put("preload_device_info", this.tt);
                }
                if (this.sA == null) {
                    io.e(TAG, " software_version was undefined.");
                }
                if (this.tm.equals(DeviceAccountRole.PRIMARY)) {
                    jSONObject10.put("register_as_primary", "true");
                }
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(HttpClientModule.ElementsRequestKey.BEARER);
                jSONArray.put("mac_dms");
                jSONArray.put("store_authentication_cookie");
                jSONArray.put("website_cookies");
                JSONObject jSONObject11 = new JSONObject();
                jSONObject11.put("domain", hH());
                JSONArray jSONArray2 = new JSONArray();
                for (MAPCookie mAPCookie : this.tr) {
                    JSONObject jSONObject12 = new JSONObject();
                    jSONObject12.put(com.amazon.identity.auth.map.device.token.MAPCookie.KEY_NAME, mAPCookie.getName());
                    jSONObject12.put(com.amazon.identity.auth.map.device.token.MAPCookie.KEY_VALUE, mAPCookie.getValue());
                    jSONArray2.put(jSONObject12);
                }
                jSONObject11.put("website_cookies", jSONArray2);
                JSONArray jSONArray3 = new JSONArray();
                jSONArray3.put(DeviceInformationContract.DeviceInfo.CONTENT_DIRECTORY);
                jSONArray3.put("customer_info");
                JSONObject jSONObject13 = new JSONObject();
                if (this.tF != null) {
                    JSONObject jSONObject14 = new JSONObject();
                    jSONObject14.put("country_of_residence", this.tF.dk);
                    jSONObject13.put("anonymous_data", jSONObject14);
                } else if (this.tl) {
                    jSONObject13.put("secondary_auth_data", jSONObject);
                } else {
                    jSONObject.remove("auth_token");
                    jSONObject13.put("auth_data", jSONObject);
                }
                jSONObject13.put("registration_data", jSONObject10);
                jSONObject13.put("requested_token_type", jSONArray);
                jSONObject13.put(AccountManagerConstants.GetCookiesParams.COOKIES, jSONObject11);
                fm.a(this.sD, jSONObject13);
                if (this.ts != null && this.ts.length() > 0) {
                    jSONObject13.put("device_metadata", this.ts);
                }
                jSONObject13.put("requested_extensions", jSONArray3);
                this.rT.eL(jSONObject13.toString());
                String str3 = TAG;
                Object[] objArr = new Object[4];
                objArr[0] = this.bk;
                objArr[1] = Boolean.toString(this.tl);
                objArr[2] = this.sA == null ? "None" : this.sA.getString();
                objArr[3] = this.td == null ? "Default" : this.td;
                io.a(str3, "getWebRequest: constructed a web request with:\nDevice Type: %s\nIs Secondary Account: %s\nSoftware Version: %s\nLocale: %s", objArr);
                return this.rT;
            } catch (JSONException e) {
                io.c(TAG, "Error Creating Panda web requst. Error: %s", e.getMessage());
                return null;
            }
        }
        return mdVar;
    }

    public void S(Bundle bundle) {
        if (bundle == null) {
            this.tc = new Bundle();
        } else {
            this.tc = bundle;
        }
    }

    public void a(CustomerAccountTokenType customerAccountTokenType) {
        this.tb = customerAccountTokenType;
    }

    public void aV(String str) {
        this.tq = str;
    }

    public void aW(String str) {
        this.eg = str;
    }

    public void ax(String str, String str2) {
        this.tD = str;
        this.tE = str2;
    }

    public void d(String str) {
        this.sS = str;
    }

    public boolean dS(String str) {
        boolean z;
        if (ma.isNullOrEmpty(str)) {
            io.i(TAG, "isValidPassword: returning false because a null or empty password was given.");
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            io.e(TAG, "setPassword: login was invalid. Cannot be set.");
            return false;
        } else if (this.ta != null) {
            io.e(TAG, "setPassword: cannot specify both a password and an auth token. Cannot be set.");
            return false;
        } else {
            this.sx = str;
            return true;
        }
    }

    public void dV(String str) {
        this.sB = str;
    }

    public void dW(String str) {
        this.sC = str;
    }

    public void dY(String str) {
        if (!TextUtils.isEmpty(str)) {
            io.dm(TAG);
        }
        this.sD = str;
    }

    public boolean dZ(String str) {
        this.td = str;
        return true;
    }

    public boolean ea(String str) {
        boolean z;
        if (ma.isNullOrEmpty(str)) {
            io.i(TAG, "isValidLogin: returning false because a null or empty login was given.");
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            io.e(TAG, "setLogin: login was invalid. Cannot be set.");
            return false;
        } else if (this.ta != null) {
            io.e(TAG, "setLogin: cannot specify both a login and an auth token. Cannot be set.");
            return false;
        } else {
            this.sY = str;
            return true;
        }
    }

    public boolean eb(String str) {
        boolean z;
        if (ma.isNullOrEmpty(str)) {
            io.i(TAG, "isValidDirectedId: returning false because a null or empty directedId was given.");
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            io.e(TAG, "setPrimaryDirectedId: directed id was invalid. Cannot be set.");
            return false;
        } else if (this.ta != null) {
            io.e(TAG, "setPrimaryDirectedId: cannot specify both a directed id and an auth token. Cannot be set.");
            return false;
        } else {
            this.sZ = str;
            return true;
        }
    }

    public boolean ec(String str) {
        if (!eh(str)) {
            io.e(TAG, "setCustomerAccountToken: password was invalid. Cannot be set.");
            return false;
        } else if (this.sY == null && this.sx == null) {
            this.ta = str;
            return true;
        } else {
            io.e(TAG, "setCustomerAccountToken: cannot specify both an auth token and a login/password. Cannot be set.");
            return false;
        }
    }

    public boolean ed(String str) {
        if (!eh(str)) {
            io.e(TAG, "setmPrimaryToken: token is invalid. Cannot be set.");
            return false;
        }
        this.tw = str;
        return true;
    }

    public void ee(String str) {
        this.tx = str;
    }

    public void ef(String str) {
        this.ty = str;
    }

    public void eg(String str) {
        this.tA = str;
    }

    public void ei(String str) {
        this.bn = str;
    }

    public void ej(String str) {
        this.tt = str;
    }

    public void ek(String str) {
        this.th = str;
    }

    public void el(String str) {
        this.tj = str;
    }

    public void em(String str) {
        this.ti = str;
    }

    public void en(String str) {
        this.nm = str;
    }

    public void eo(String str) {
        if (this.nm != null) {
            this.nn = str;
        }
    }

    public void ep(String str) {
        if (this.nm != null) {
            this.qZ = str;
        }
    }

    public void eq(String str) {
        this.tu = str;
    }

    public void er(String str) {
        this.tB = str;
    }

    public void es(String str) {
        this.tC = str;
    }

    public boolean hA() {
        return this.tl;
    }

    public void hB() {
        a(false, DeviceAccountRole.UNDEFINED);
    }

    public void hC() {
        this.tn = true;
    }

    public void hD() {
        this.tk = true;
    }

    public void hE() {
        this.to = true;
    }

    protected lq hG() {
        return new lq();
    }

    public String hH() {
        return this.tu;
    }

    public RegisterEndpointEnum hI() {
        return this.tp;
    }

    @Override // com.amazon.identity.auth.device.lg
    public JSONObject hJ() throws JSONException {
        JSONObject ha = jv.ha();
        if (!TextUtils.isEmpty(this.ta)) {
            ha.put(AbstractJSONTokenResponse.ACCESS_TOKEN, this.ta);
        }
        return ha;
    }

    public lc hK() {
        return this.tG;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x02ab  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02e4  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x025b  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0299  */
    @Override // com.amazon.identity.auth.device.lg
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.amazon.identity.auth.device.md ho() {
        /*
            Method dump skipped, instructions count: 783
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.kcpsdk.auth.RegisterDeviceRequest.ho():com.amazon.identity.auth.device.md");
    }

    public boolean isValid() {
        if (this.bk == null) {
            io.w(TAG, "isValid: returning false because a valid device type has not been set.");
            return false;
        } else if (this.sz == null) {
            io.w(TAG, "isValid: returning false because a valid serial number has not been set.");
            return false;
        } else if (this.tp == null) {
            io.w(TAG, "isValid: returning false because a register endpoint has not been set.");
            return false;
        } else if ((!TextUtils.isEmpty(this.tB) && !TextUtils.isEmpty(this.tC) && !TextUtils.isEmpty(this.tD) && !TextUtils.isEmpty(this.tE)) || !TextUtils.isEmpty(this.tj) || ((!TextUtils.isEmpty(this.th) && !TextUtils.isEmpty(this.ti)) || this.tF != null)) {
            return true;
        } else {
            if (this.sY == null && this.ta == null && this.tx == null && this.ty == null && this.sZ == null && !this.tk && this.nm == null) {
                io.w(TAG, "isValid: returning false because a valid login has not been set.");
                return false;
            } else if (this.sx == null && this.ta == null && !this.tk && this.tA == null && !this.tn && this.nm == null) {
                io.w(TAG, "isValid: returning false because a valid password has not been set.");
                return false;
            } else {
                if (this.ta == null && this.sY == null && this.sx == null && !this.tk && this.tw == null && !this.tn && this.nm == null) {
                    io.w(TAG, "isValid: returning false because a valid auth token has not been set.");
                    return false;
                }
                return true;
            }
        }
    }

    public void k(boolean z) {
        this.sF = z;
    }

    public void l(Map<String, mb> map) {
        this.tv = new HashMap(map);
    }

    public void z(JSONObject jSONObject) {
        this.ts = jSONObject;
    }

    public RegisterDeviceRequest(ed edVar, lc lcVar) {
        this.th = null;
        this.ti = null;
        this.tj = null;
        this.tm = DeviceAccountRole.UNDEFINED;
        this.to = false;
        this.tB = null;
        this.tb = CustomerAccountTokenType.AT_MAIN;
        this.tp = RegisterEndpointEnum.FIRS;
        this.tv = null;
        this.o = edVar;
        this.sF = true;
        this.tG = lcVar;
    }

    public void a(boolean z, DeviceAccountRole deviceAccountRole) {
        this.tl = z;
        if (!z) {
            deviceAccountRole = DeviceAccountRole.UNDEFINED;
        }
        this.tm = deviceAccountRole;
    }

    public void l(List<MAPCookie> list) {
        this.tr = list;
    }

    public void a(mc mcVar) {
        if (!mcVar.isValid()) {
            io.e(TAG, "setDeviceSoftwareVersion: device software version is invalid. Cannot be set.");
        } else {
            this.sA = mcVar;
        }
    }

    public void a(RegisterEndpointEnum registerEndpointEnum) {
        this.tp = registerEndpointEnum;
    }

    public void a(a aVar) {
        this.tF = aVar;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public RegisterDeviceRequest(com.amazon.identity.auth.device.ed r3, android.os.Bundle r4) {
        /*
            r2 = this;
            boolean r0 = com.amazon.identity.auth.device.mz.f(r3)
            if (r0 == 0) goto L23
            if (r4 == 0) goto L23
            r0 = 0
            java.lang.String r1 = "ignore_name_for_isolated_app"
            boolean r4 = r4.getBoolean(r1, r0)
            if (r4 == 0) goto L23
            java.lang.String r4 = com.amazon.identity.kcpsdk.auth.RegisterDeviceRequest.TAG
            java.lang.String r0 = "Using special isolated app parser"
            com.amazon.identity.auth.device.io.i(r4, r0)
            com.amazon.identity.auth.device.lc r4 = new com.amazon.identity.auth.device.lc
            com.amazon.identity.auth.device.lf r0 = new com.amazon.identity.auth.device.lf
            r0.<init>()
            r4.<init>(r0)
            goto L28
        L23:
            com.amazon.identity.auth.device.lc r4 = new com.amazon.identity.auth.device.lc
            r4.<init>()
        L28:
            r2.<init>(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.kcpsdk.auth.RegisterDeviceRequest.<init>(com.amazon.identity.auth.device.ed, android.os.Bundle):void");
    }
}
