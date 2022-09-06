package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.device.api.AuthenticationMethod;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.features.Feature;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class fu extends fr {
    private static final String TAG = "com.amazon.identity.auth.device.fu";
    private final ea at;
    private final Bundle nk;
    private final gg w;

    public fu(Bundle bundle, Context context) {
        super(ed.M(context));
        this.nk = bundle;
        this.w = this.o.dV();
        this.at = (ea) this.o.getSystemService("dcp_device_info");
    }

    @Override // com.amazon.identity.auth.device.fr
    protected JSONObject b(ej ejVar) throws JSONException {
        kw kwVar = new kw();
        String string = this.nk.getString("com.amazon.dcp.sso.property.account.acctId");
        String string2 = this.nk.getString(MAPAccountManager.KEY_AMAZON_ACCOUNT_LOGIN_NAME);
        String string3 = this.nk.getString(MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD);
        String string4 = this.nk.getString("com.amazon.dcp.sso.token.oauth.amazon.access_token");
        boolean z = this.nk.getBoolean(MAPAccountManager.KEY_DISABLE_GLOBAL_SIGNIN);
        if (!TextUtils.isEmpty(string)) {
            if (TextUtils.isEmpty(string4)) {
                kwVar.dQ(string);
            } else {
                kwVar.dR(string4);
            }
            String z2 = this.w.z(string, AccountConstants.TOKEN_TYPE_COOKIE_MFA_SID_TOKEN);
            if (!TextUtils.isEmpty(z2)) {
                kwVar.dX(z2.replaceAll("^\"|\"$", ""));
            }
        }
        if (!TextUtils.isEmpty(string2)) {
            kwVar.dP(string2);
        }
        kwVar.dS(string3);
        kwVar.b(this.at);
        kwVar.a(ks.hw());
        kwVar.dY(fm.h(this.o, this.at.getDeviceSerialNumber()));
        String string5 = this.nk.getString("calling_package");
        if (string5 != null) {
            kwVar.dV(string5);
            Long x = it.x(this.o, string5);
            if (x != null) {
                kwVar.dW(Long.toString(x.longValue()));
            }
        }
        if (z) {
            kwVar.k(false);
        } else {
            kwVar.k(true);
        }
        return kwVar.hy();
    }

    @Override // com.amazon.identity.auth.device.fr
    protected String eF() {
        return EnvironmentUtils.cd().getPandaHost(hr.I(this.nk));
    }

    @Override // com.amazon.identity.auth.device.fr
    protected String eG() {
        return EnvironmentUtils.cd().A(this.nk);
    }

    @Override // com.amazon.identity.auth.device.fr
    protected AuthenticationMethod eH() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.identity.auth.device.fr
    public Map<String, String> eK() {
        Map<String, String> eK = super.eK();
        if (this.o.dW().a(Feature.UseDeviceLocaleAsLanguagePreference)) {
            String a = lz.a(Locale.getDefault());
            String str = TAG;
            "Setting Language to: ".concat(String.valueOf(a));
            io.dm(str);
            eK.put("Accept-Language", a);
        }
        return eK;
    }

    @Override // com.amazon.identity.auth.device.fr
    protected String getHttpVerb() {
        return "POST";
    }

    @Override // com.amazon.identity.auth.device.fr
    protected String getPath() {
        return "/auth/signin";
    }

    @Override // com.amazon.identity.auth.device.fr
    protected String j(JSONObject jSONObject) {
        return ik.a(ik.b(ik.b(jSONObject, "response"), "error"), "index", null);
    }
}
