package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.AuthenticationMethod;
import com.amazon.identity.auth.device.api.AuthenticationMethodFactory;
import com.amazon.identity.auth.device.api.AuthenticationType;
import com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class fv extends fr {
    protected final String bP;
    protected final hb nl;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a extends fv {
        private final String eg;
        private final String nm;
        private final String nn;
        private final String no;
        private final String np;

        public a(hb hbVar, Context context, String str, String str2, String str3, String str4, String str5, String str6) {
            super(context, hbVar, str);
            this.nm = str2;
            this.nn = str3;
            this.no = str4;
            this.eg = str5;
            this.np = str6;
        }

        @Override // com.amazon.identity.auth.device.fr
        protected JSONObject b(ej ejVar) throws JSONException {
            return this.nl.a(this.nm, this.nn, this.no, this.eg, this.np);
        }

        @Override // com.amazon.identity.auth.device.fr
        protected AuthenticationMethod eH() {
            return null;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class b extends fv {
        private final String bm;

        public b(hb hbVar, Context context, String str, String str2) {
            super(context, hbVar, str);
            this.bm = str2;
        }

        @Override // com.amazon.identity.auth.device.fr
        protected JSONObject b(ej ejVar) throws JSONException {
            return this.nl.c("dms_token", "source_token", AbstractJSONTokenResponse.REFRESH_TOKEN, ejVar);
        }

        @Override // com.amazon.identity.auth.device.fr
        protected AuthenticationMethod eH() {
            AuthenticationMethodFactory authenticationMethodFactory = new AuthenticationMethodFactory(this.o, this.bP);
            authenticationMethodFactory.setPackageName(this.bm);
            return authenticationMethodFactory.newAuthenticationMethod(AuthenticationType.ADPAuthenticator);
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class c extends fv {
        private final Bundle ec;
        private final String nq;

        public c(hb hbVar, Context context, String str, String str2, Bundle bundle) {
            super(context, hbVar, str);
            this.nq = str2;
            this.ec = bundle;
        }

        @Override // com.amazon.identity.auth.device.fr
        protected JSONObject b(ej ejVar) throws JSONException {
            return this.nl.i(this.nq, this.bP, ejVar);
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x001e  */
        @Override // com.amazon.identity.auth.device.fv, com.amazon.identity.auth.device.fr
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        protected java.lang.String eF() {
            /*
                r3 = this;
                android.os.Bundle r0 = r3.ec
                if (r0 == 0) goto L17
                java.lang.String r0 = com.amazon.identity.auth.device.hr.H(r0)
                boolean r1 = android.text.TextUtils.isEmpty(r0)
                if (r1 != 0) goto L17
                com.amazon.identity.auth.device.env.EnvironmentUtils r1 = com.amazon.identity.auth.device.env.EnvironmentUtils.cd()
                java.lang.String r0 = r1.getPandaHost(r0)
                goto L18
            L17:
                r0 = 0
            L18:
                boolean r1 = android.text.TextUtils.isEmpty(r0)
                if (r1 == 0) goto L26
                com.amazon.identity.auth.device.ed r0 = r3.o
                java.lang.String r1 = r3.bP
                java.lang.String r0 = com.amazon.identity.auth.device.hr.c(r0, r1)
            L26:
                java.lang.String r1 = java.lang.String.valueOf(r0)
                java.lang.String r2 = "Delegated token exchange endpoint: "
                java.lang.String r1 = r2.concat(r1)
                java.lang.String r2 = "PandaTokenExchangeApiCall"
                com.amazon.identity.auth.device.io.i(r2, r1)
                java.lang.String r1 = java.lang.String.valueOf(r0)
                java.lang.String r2 = "PandaDelegateTokenExchange:"
                java.lang.String r1 = r2.concat(r1)
                r2 = 0
                java.lang.String[] r2 = new java.lang.String[r2]
                com.amazon.identity.auth.device.mq.incrementCounterAndRecord(r1, r2)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.fv.c.eF():java.lang.String");
        }

        @Override // com.amazon.identity.auth.device.fr
        protected AuthenticationMethod eH() {
            return null;
        }
    }

    public fv(Context context, hb hbVar, String str) {
        super(ed.M(context));
        this.bP = str;
        this.nl = hbVar;
    }

    public static b a(hb hbVar, Context context, String str, String str2) {
        return new b(hbVar, context, str, str2);
    }

    @Override // com.amazon.identity.auth.device.fr
    protected String eF() {
        return hr.c(this.o, this.bP);
    }

    @Override // com.amazon.identity.auth.device.fr
    protected String eG() {
        return hr.n(this.o, this.bP);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.identity.auth.device.fr
    public Map<String, String> eK() {
        return super.eK();
    }

    @Override // com.amazon.identity.auth.device.fr
    protected String getHttpVerb() {
        return "POST";
    }

    @Override // com.amazon.identity.auth.device.fr
    protected String getPath() {
        return "/auth/token";
    }

    @Override // com.amazon.identity.auth.device.fr
    protected String j(JSONObject jSONObject) {
        return ik.a(jSONObject, "error_index", null);
    }

    public static c a(hb hbVar, Context context, String str, String str2, Bundle bundle) {
        return new c(hbVar, context, str, str2, bundle);
    }

    public static a a(hb hbVar, Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        return new a(hbVar, context, str, str2, str3, str4, str5, str6);
    }
}
