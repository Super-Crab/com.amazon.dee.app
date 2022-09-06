package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.deecomms.platform.identity.CommunicableEntity;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse;
import com.amazon.identity.auth.device.fr;
import com.amazon.identity.auth.device.framework.AuthEndpointErrorParser;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import java.io.IOException;
import java.util.Locale;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class n {
    private static final String TAG = "com.amazon.identity.auth.device.n";
    private static final a aT = new a(new AuthEndpointErrorParser.a(AuthEndpointErrorParser.AuthErrorType.CredentialError, "Credential Error", "Credential Error", "Credential Error", "No Request Id"));
    private final AuthEndpointErrorParser aU;
    private final ed o;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a {
        public final String aV;
        public final kc aW;
        public final AuthEndpointErrorParser.a aX;
        public final fp aY;
        public final String directedId;

        public a(String str, String str2) {
            this(str, str2, null, null, null);
        }

        public boolean isError() {
            return this.aX != null;
        }

        public a(AuthEndpointErrorParser.a aVar) {
            this(null, null, null, aVar, null);
        }

        public a(AuthEndpointErrorParser.a aVar, fp fpVar) {
            this(null, null, null, aVar, fpVar);
        }

        public a(kc kcVar, AuthEndpointErrorParser.a aVar) {
            this(null, null, kcVar, aVar, null);
        }

        private a(String str, String str2, kc kcVar, AuthEndpointErrorParser.a aVar, fp fpVar) {
            this.aV = str;
            this.directedId = str2;
            this.aW = kcVar;
            this.aX = aVar;
            this.aY = fpVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(Context context) {
        this(ed.M(context), new AuthEndpointErrorParser());
    }

    private a b(Bundle bundle, ej ejVar) {
        a aVar;
        AuthEndpointErrorParser.a aVar2;
        a aVar3;
        AuthEndpointErrorParser.AuthErrorType authErrorType;
        try {
            fu i = i(bundle);
            fr.a c = i.c(ejVar);
            Integer num = c.nd;
            if (num != null) {
                if (num.intValue() == 0) {
                    return aT;
                }
                if (c.nd.intValue() == 1 && (c.nc instanceof IOException)) {
                    authErrorType = AuthEndpointErrorParser.AuthErrorType.ServiceUnavailable;
                } else if (c.nd.intValue() == 2 && (c.nc instanceof IOException)) {
                    authErrorType = AuthEndpointErrorParser.AuthErrorType.NetworkFailure;
                } else if (c.nd.intValue() == 3) {
                    authErrorType = AuthEndpointErrorParser.AuthErrorType.ParseError;
                } else {
                    authErrorType = AuthEndpointErrorParser.AuthErrorType.GenericError;
                }
                return new a(new AuthEndpointErrorParser.a(authErrorType, "Error getting response from server", null, null, null));
            }
            try {
                JSONObject jSONObject = c.na;
                Integer num2 = c.nb;
                if (jSONObject == null) {
                    io.e(TAG, "Error parsing JSON in Panda response");
                    aVar = new a(new AuthEndpointErrorParser.a(AuthEndpointErrorParser.AuthErrorType.ParseError, "Error parsing JSON in Panda response", null, null, null));
                } else if (AuthEndpointErrorParser.a(num2) && !this.aU.i(jSONObject)) {
                    AuthEndpointErrorParser.a g = this.aU.g(jSONObject);
                    if (g == null) {
                        g = AuthEndpointErrorParser.iS;
                    }
                    io.c(TAG, "Error making request. Code: %s \n Message: %s \n Detail: %s \n Index: %s", g.cG().getCode(), g.getMessage(), g.cH(), g.cI());
                    aVar = new a(g);
                } else {
                    io.i(TAG, String.format("Request to panda signin API with request id %s", jSONObject.getString(AbstractJSONTokenResponse.REQUEST_ID)));
                    JSONObject jSONObject2 = jSONObject.getJSONObject("response");
                    if (jSONObject2.has("success")) {
                        JSONObject jSONObject3 = jSONObject2.getJSONObject("success");
                        aVar3 = new a(jSONObject3.getJSONObject("tokens").getJSONObject(HttpClientModule.ElementsRequestKey.BEARER).getString(AbstractJSONTokenResponse.ACCESS_TOKEN), jSONObject3.getString("customer_id"));
                    } else if (jSONObject2.has("challenge")) {
                        kc s = kc.s(jSONObject2.getJSONObject("challenge"));
                        String a2 = ik.a(jSONObject2, AbstractJSONTokenResponse.REQUEST_ID, null);
                        String reason = s.getReason();
                        if (!"AuthenticationFailed".equals(reason) && !"InvalidAuthenticationData".equals(reason)) {
                            aVar2 = new AuthEndpointErrorParser.a(AuthEndpointErrorParser.AuthErrorType.AuthenticationChallenged, null, null, null, a2);
                            aVar3 = new a(s, aVar2);
                        }
                        aVar2 = new AuthEndpointErrorParser.a(AuthEndpointErrorParser.AuthErrorType.CredentialError, null, null, null, a2);
                        aVar3 = new a(s, aVar2);
                    } else {
                        io.e(TAG, "Error parsing response. Empty response body.");
                        aVar = new a(new AuthEndpointErrorParser.a(AuthEndpointErrorParser.AuthErrorType.ParseError, "Error parsing response. Empty response body.", null, null, null));
                    }
                    aVar = aVar3;
                }
                if (aVar.isError()) {
                    i.a(c.nb, aVar.aX.cG().getCode());
                } else {
                    i.a(c.nb, (String) null);
                }
                return aVar;
            } catch (JSONException e) {
                ejVar.incrementCounter(mp.j(i.eM()), FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
                String format = String.format(Locale.US, "Error parsing Panda sign-in response. Not of an expected format. Error: %s", e.getMessage());
                io.e(TAG, format);
                return new a(new AuthEndpointErrorParser.a(AuthEndpointErrorParser.AuthErrorType.ParseError, format, null, null, null));
            }
        } catch (MAPCallbackErrorException e2) {
            fp a3 = fp.a(e2);
            if (a3 != null) {
                return new a(new AuthEndpointErrorParser.a(AuthEndpointErrorParser.AuthErrorType.InvalidToken, "MAP internally can't get access_token for authentication", null, null, null), a3);
            }
            return new a(new AuthEndpointErrorParser.a(AuthEndpointErrorParser.AuthErrorType.GenericError, "MAP internally can't get access_token for authentication", null, null, null));
        } catch (Exception unused) {
            return new a(new AuthEndpointErrorParser.a(AuthEndpointErrorParser.AuthErrorType.GenericError, "MAP internally can't get access_token for authentication", null, null, null));
        }
    }

    public Bundle a(Bundle bundle, ej ejVar) throws MAPCallbackErrorException {
        String hj;
        String string = bundle.getString(MAPAccountManager.KEY_AMAZON_ACCOUNT_LOGIN_NAME);
        String string2 = bundle.getString(MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD);
        String string3 = bundle.getString("com.amazon.dcp.sso.property.account.acctId");
        if (!TextUtils.isEmpty(string2) && (!TextUtils.isEmpty(string) || !TextUtils.isEmpty(string3))) {
            a b = b(bundle, ejVar);
            if (b.isError()) {
                AuthEndpointErrorParser.AuthErrorType cG = b.aX.cG();
                kc kcVar = b.aW;
                Bundle a2 = m.a(cG.getError(), cG.getErrorMessage(), cG.getRegistrationError().value(), cG.getCode());
                if (kcVar != null) {
                    if (b.aX.cG() == AuthEndpointErrorParser.AuthErrorType.CredentialError && (hj = kcVar.hj()) != null) {
                        a2.putString(MAPAccountManager.KEY_AUTH_DATA_ADDITIONAL_INFO, hj);
                    }
                    a2.putBundle(MAPAccountManager.KEY_AUTHENTICATION_CHALLENGE, kcVar.toBundle());
                }
                fp fpVar = b.aY;
                if (fpVar != null) {
                    a2.putAll(fpVar.eC());
                }
                mq.b("PandaService:SignIn:" + cG.getCode(), new String[0]);
                throw new MAPCallbackErrorException(a2, cG.getError(), cG.getErrorMessage());
            }
            mq.b("PandaService:SignIn:Success", new String[0]);
            Bundle bundle2 = new Bundle();
            bundle2.putString("com.amazon.dcp.sso.property.account.acctId", b.directedId);
            bundle2.putString("com.amazon.dcp.sso.AddAccount.options.AccessToken", b.aV);
            return bundle2;
        }
        throw new MAPCallbackErrorException(m.a(MAPError.CommonError.BAD_REQUEST, "A login/directedId and password are required to authenticate/confirmCredential.", MAPAccountManager.RegistrationError.BAD_REQUEST.value(), "A login/directedId and password are required to authenticate/confirmCredential."), MAPError.CommonError.BAD_REQUEST, "A login/directedId and password are required to authenticate/confirmCredential.");
    }

    protected fu i(Bundle bundle) throws Exception {
        String string = bundle.getString("com.amazon.dcp.sso.property.account.acctId");
        if (!TextUtils.isEmpty(string) && (!string.startsWith(CommunicableEntity.ENTITY_ID_PROVIDER_AMAZON) || string.contains(ProcessIdUtil.DEFAULT_PROCESSID))) {
            io.i(TAG, "Legacy device with non-standard directed id.");
            try {
                bundle.putString("com.amazon.dcp.sso.token.oauth.amazon.access_token", new TokenManagement(this.o).getToken(string, "com.amazon.dcp.sso.token.oauth.amazon.access_token", null, null).get().getString("value_key"));
            } catch (Exception e) {
                io.e(TAG, "Can't get the access_token for authentication", e);
                throw e;
            }
        }
        return new fu(bundle, this.o);
    }

    n(ed edVar, AuthEndpointErrorParser authEndpointErrorParser) {
        this.o = edVar;
        this.aU = authEndpointErrorParser;
    }
}
