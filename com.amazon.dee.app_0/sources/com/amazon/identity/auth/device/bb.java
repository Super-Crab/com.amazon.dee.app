package com.amazon.identity.auth.device;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.device.activity.ActorEnrollActivity;
import com.amazon.identity.auth.device.activity.ActorSignUpAndEnrollActivity;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.MAPErrorCallbackHelper;
import com.amazon.identity.auth.device.callback.RemoteCallbackWrapper;
import com.amazon.identity.auth.device.endpoint.OpenIdRequest;
import com.amazon.identity.auth.device.fr;
import com.amazon.identity.auth.device.framework.AuthEndpointErrorParser;
import com.amazon.identity.auth.device.token.OAuthTokenManager;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class bb {
    private final OAuthTokenManager B;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* renamed from: com.amazon.identity.auth.device.bb$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] fu = new int[AuthEndpointErrorParser.AuthErrorType.values().length];

        static {
            try {
                fu[AuthEndpointErrorParser.AuthErrorType.InvalidRequest.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                fu[AuthEndpointErrorParser.AuthErrorType.InvalidToken.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                fu[AuthEndpointErrorParser.AuthErrorType.ActorNotAssociated.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public bb(OAuthTokenManager oAuthTokenManager) {
        this.B = oAuthTokenManager;
    }

    private String b(JSONObject jSONObject) throws JSONException {
        try {
            return jSONObject.getJSONObject("ui_action").getString("url");
        } catch (Exception unused) {
            io.dm("ActorSignUpAndEnrollHelper");
            throw new JSONException("Cannot get loading url from json response");
        }
    }

    private String r(Bundle bundle) {
        String str = MAPAccountManager.KEY_OVERRIDE_RETURN_TO_DOMAIN;
        if (TextUtils.isEmpty(bundle.getString(str))) {
            str = MAPAccountManager.KEY_SIGN_IN_ENDPOINT;
        }
        return OpenIdRequest.aX(bundle.getString(str));
    }

    public void a(bl blVar, fr.a aVar) {
        JSONObject jSONObject = aVar.na;
        if (jSONObject == null) {
            io.e("ActorSignUpAndEnrollHelper", "No json in the response!");
            blVar.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.PARSE_ERROR, "No json in the response!", true));
            return;
        }
        String a = ik.a(jSONObject, "error", "ParseError");
        String a2 = ik.a(aVar.na, "error_description", "Service returned known error.");
        int i = AnonymousClass1.fu[AuthEndpointErrorParser.AuthErrorType.getAuthErrorTypeFromCode(a).ordinal()];
        if (i == 1) {
            blVar.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.BAD_REQUEST, a2, false));
        } else if (i == 2) {
            blVar.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.ActorError.ACTOR_SIGNUP_FAILED, a2, true));
        } else if (i != 3) {
            blVar.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.PARSE_ERROR, a2, true));
        } else {
            blVar.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.ActorError.ACTOR_NOT_ASSOCIATED, a2, false));
        }
    }

    public void a(Context context, String str, bl blVar, Bundle bundle, JSONObject jSONObject, ej ejVar) {
        Intent t = ii.t(context, ActorSignUpAndEnrollActivity.class.getName());
        if (t != null) {
            if (ejVar != null) {
                ejVar.e(t);
            }
            try {
                bundle.putString("account_id", str);
                bundle.putString("load_url", b(jSONObject));
                bundle.putString("return_to_url", r(bundle));
                this.B.b(str, bundle.getString(MAPAccountManager.KEY_SIGN_IN_ENDPOINT), bundle, ejVar);
                t.putExtras(bundle);
                t.putExtra("callback", new RemoteCallbackWrapper(blVar));
                if (!(context instanceof Activity)) {
                    t.addFlags(268435456);
                }
                context.startActivity(t);
                return;
            } catch (OAuthTokenManager.OAuthTokenManagerException e) {
                io.e("ActorSignUpAndEnrollHelper", "Cannot get cookies before launching the signUpAndEnroll UI");
                MAPErrorCallbackHelper.onError(blVar, e.getError(), "Cannot get cookies before launching the signUpAndEnroll UI");
                return;
            } catch (JSONException unused) {
                MAPErrorCallbackHelper.onError(blVar, MAPError.CommonError.PARSE_ERROR, "Cannot parse response");
                return;
            }
        }
        throw new RuntimeException("No activity can handle the intent. Probably because you do not declare ActorSignUpAndEnrollActivity in Android manifest");
    }

    public void a(Context context, String str, String str2, bl blVar, Bundle bundle, JSONObject jSONObject, ej ejVar) {
        Intent t = ii.t(context, ActorEnrollActivity.class.getName());
        if (ejVar != null) {
            ejVar.e(t);
        }
        if (t != null) {
            try {
                bundle.putString("account_id", str);
                bundle.putString("load_url", b(jSONObject));
                bundle.putString("return_to_url", r(bundle));
                this.B.b(str, str2, bundle.getString(MAPAccountManager.KEY_SIGN_IN_ENDPOINT), bundle, ejVar);
                t.putExtras(bundle);
                t.putExtra("callback", new RemoteCallbackWrapper(blVar));
                if (!(context instanceof Activity)) {
                    t.addFlags(268435456);
                }
                context.startActivity(t);
                return;
            } catch (OAuthTokenManager.OAuthTokenManagerException e) {
                io.e("ActorSignUpAndEnrollHelper", "Cannot get cookies before launching the actor enroll UI");
                MAPErrorCallbackHelper.onError(blVar, e.getError(), "Cannot get cookies before launching the actor enroll UI");
                return;
            } catch (JSONException unused) {
                MAPErrorCallbackHelper.onError(blVar, MAPError.CommonError.PARSE_ERROR, "Cannot parse response for enroll actor request.");
                return;
            }
        }
        throw new RuntimeException("No activity can handle the intent. Probably because you do not declare ActorEnrollActivity in Android manifest");
    }
}
