package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate;
import com.amazon.identity.auth.device.api.AuthenticationMethod;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse;
import com.amazon.identity.auth.device.endpoint.OpenIdRequest;
import com.amazon.identity.auth.device.token.MAPCookie;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class fq extends fr {
    protected final String eO;
    protected final String mAccessToken;
    protected final String mW;
    protected final Bundle mX;
    protected final Bundle mY;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static final class a extends fq {
        private a(ed edVar, String str, String str2, String str3, Bundle bundle, Bundle bundle2) {
            super(edVar, str, str2, str3, bundle, bundle2);
        }

        public static a a(ed edVar, String str, String str2, String str3, Bundle bundle, Bundle bundle2) {
            return new a(edVar, str, str2, str3, bundle, bundle2);
        }

        @Override // com.amazon.identity.auth.device.fr
        protected String getPath() {
            return "/auth/startActorEnrollment";
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static final class b extends fq {
        private b(ed edVar, String str, String str2, String str3, Bundle bundle, Bundle bundle2) {
            super(edVar, str, str2, str3, bundle, bundle2);
        }

        public static b b(ed edVar, String str, String str2, String str3, Bundle bundle, Bundle bundle2) {
            return new b(edVar, str, str2, str3, bundle, bundle2);
        }

        @Override // com.amazon.identity.auth.device.fr
        protected String getPath() {
            return "/auth/startActorCreationAndEnrollment";
        }
    }

    public fq(ed edVar, String str, String str2, String str3, Bundle bundle, Bundle bundle2) {
        super(edVar);
        this.eO = str;
        this.mW = str2;
        this.mAccessToken = str3;
        this.mX = bundle;
        this.mY = bundle2;
    }

    private String eJ() {
        Bundle bundle = this.mX.getBundle(MAPAccountManager.AuthPortalOptions.KEY_REQUEST_PARAMETERS);
        if (bundle != null && !TextUtils.isEmpty(bundle.getString("openid.assoc_handle"))) {
            return bundle.getString("openid.assoc_handle");
        }
        io.w("PandaActorInvolvedApiCall", "assoc_handle is not passed in, fall back to MAP's default: amzn_device_android");
        return "amzn_device_android";
    }

    private String getDomain() {
        return TextUtils.isEmpty(this.mX.getString(MAPAccountManager.KEY_SIGN_IN_ENDPOINT)) ? hr.m(this.o, this.eO) : this.mX.getString(MAPAccountManager.KEY_SIGN_IN_ENDPOINT);
    }

    @Override // com.amazon.identity.auth.device.fr
    protected JSONObject b(ej ejVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(AbstractJSONTokenResponse.ACCESS_TOKEN, this.mAccessToken);
        jSONObject.put("policy_handle", this.mW);
        jSONObject.put("ui_capacity", MAPWebviewActivityTemplate.ba());
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("domain", getDomain());
        jSONObject2.put("client_config", eJ());
        String eJ = eJ();
        Bundle bundle = this.mX.getBundle(MAPAccountManager.AuthPortalOptions.KEY_REQUEST_PARAMETERS);
        if (bundle != null && !TextUtils.isEmpty(bundle.getString("pageId"))) {
            eJ = bundle.getString("pageId");
        } else {
            io.w("PandaActorInvolvedApiCall", "page_iid is not passed in, fall back to MAP's assoc_handle");
        }
        jSONObject2.put("ui_config", eJ);
        jSONObject2.put("redirect_uri", OpenIdRequest.aX(TextUtils.isEmpty(this.mX.getString(MAPAccountManager.KEY_OVERRIDE_RETURN_TO_DOMAIN)) ? getDomain() : this.mX.getString(MAPAccountManager.KEY_OVERRIDE_RETURN_TO_DOMAIN)));
        jSONObject2.put("cancel_uri", OpenIdRequest.aY(TextUtils.isEmpty(this.mX.getString(MAPAccountManager.KEY_OVERRIDE_RETURN_TO_DOMAIN)) ? getDomain() : this.mX.getString(MAPAccountManager.KEY_OVERRIDE_RETURN_TO_DOMAIN)));
        jSONObject.put("auth_portal_config", jSONObject2);
        return jSONObject;
    }

    @Override // com.amazon.identity.auth.device.fr
    protected String eF() {
        return hr.c(this.o, this.eO);
    }

    @Override // com.amazon.identity.auth.device.fr
    protected String eG() {
        return hr.n(this.o, this.eO);
    }

    @Override // com.amazon.identity.auth.device.fr
    protected AuthenticationMethod eH() {
        return null;
    }

    @Override // com.amazon.identity.auth.device.fr
    protected List<MAPCookie> eI() {
        return ia.e(this.mY, this.eO);
    }

    @Override // com.amazon.identity.auth.device.fr
    protected String getHttpVerb() {
        return "POST";
    }

    @Override // com.amazon.identity.auth.device.fr
    protected String j(JSONObject jSONObject) {
        return ik.a(jSONObject, "error_index", null);
    }
}
