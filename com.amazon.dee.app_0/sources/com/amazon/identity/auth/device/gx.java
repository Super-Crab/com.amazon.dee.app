package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.MAPErrorCallbackHelper;
import com.amazon.identity.auth.device.api.MAPFuture;
import com.amazon.identity.auth.device.api.TokenKeys;
import com.amazon.identity.auth.device.he;
import com.amazon.identity.auth.device.token.OAuthTokenManager;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.HashMap;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class gx implements hh {
    private static final String TAG = "com.amazon.identity.auth.device.gx";
    private OAuthTokenManager B;
    private final ed o;

    public gx(Context context) {
        this.o = ed.M(context);
        this.B = new OAuthTokenManager(this.o);
    }

    @Override // com.amazon.identity.auth.device.hh
    public MAPFuture<Bundle> e(final String str, final String str2, final Bundle bundle, Callback callback, final ej ejVar) {
        im dl = im.dl(str2);
        if ((bundle == null || !bundle.getBoolean("authorizationCode")) && "com.amazon.dcp.sso.token.oauth.amazon.access_token".equals(dl.getKey()) && ie.q(this.o, dl.getPackageName())) {
            io.dm(TAG);
            final bl blVar = new bl(callback);
            final Account o = hu.o(this.o, str);
            if (o == null) {
                MAPError.AccountError accountError = MAPError.AccountError.CUSTOMER_NOT_FOUND;
                blVar.onError(hf.a(accountError, accountError.getErrorMessage(), MAPAccountManager.RegistrationError.NO_ACCOUNT.value(), "Given Customer Does not exist"));
                return blVar;
            } else if (TextUtils.isEmpty(str2)) {
                blVar.onError(hf.a(MAPError.CommonError.BAD_REQUEST, "Token key was empty.", 8, "Token key was empty."));
                return blVar;
            } else {
                ji.b(new Runnable() { // from class: com.amazon.identity.auth.device.gx.1
                    @Override // java.lang.Runnable
                    public void run() {
                        gx.a(gx.this, str2, o, str, bundle, blVar, ejVar);
                    }
                });
                return blVar;
            }
        }
        return hj.ae(this.o).e(str, str2, bundle, callback, ejVar);
    }

    @Override // com.amazon.identity.auth.device.hh
    public MAPFuture<Bundle> f(String str, String str2, Bundle bundle, Callback callback, ej ejVar) {
        return hj.ae(this.o).f(str, str2, bundle, callback, ejVar);
    }

    @Override // com.amazon.identity.auth.device.hh
    public MAPFuture<Bundle> g(String str, String str2, Bundle bundle, Callback callback, ej ejVar) {
        return hj.ae(this.o).g(str, str2, bundle, callback, ejVar);
    }

    @Override // com.amazon.identity.auth.device.hh
    public MAPFuture<Bundle> a(Context context, String str, String str2, String str3, String str4, Bundle bundle, Callback callback, ej ejVar) {
        bl blVar = new bl(callback);
        hf.d(blVar, MAPError.CommonError.FEATURE_NOT_IMPLEMENTED, "getTokenForActor is not supported in pre-merge devices", 6, "getTokenForActor is not supported in pre-merge devices");
        return blVar;
    }

    @Override // com.amazon.identity.auth.device.hh
    public MAPFuture<Bundle> a(String str, String str2, String str3, Bundle bundle, Callback callback, ej ejVar) {
        bl blVar = new bl(callback);
        hf.d(blVar, MAPError.CommonError.FEATURE_NOT_IMPLEMENTED, "getCookiesForActor is not supported in pre-merge devices", 6, "getCookiesForActor is not supported in pre-merge devices");
        return blVar;
    }

    @Override // com.amazon.identity.auth.device.hh
    public MAPFuture<Bundle> a(String str, String str2, Bundle bundle, Callback callback, gy gyVar, ej ejVar) {
        bl blVar = new bl(callback);
        MAPErrorCallbackHelper.onError(blVar, MAPError.CommonError.FEATURE_NOT_IMPLEMENTED);
        return blVar;
    }

    private void a(String str, he heVar) {
        hu.k(this.o, str, heVar.ca("com.amazon.dcp.sso.token.oauth.amazon.access_token"));
    }

    static /* synthetic */ void a(gx gxVar, String str, Account account, final String str2, Bundle bundle, final bl blVar, ej ejVar) {
        final im dl = im.dl(str);
        final he b = gw.ac(gxVar.o).b(account);
        final boolean z = !TextUtils.equals("true", gxVar.o.dV().b(str2, "force_refresh_dms_to_oauth_done_once"));
        if (TextUtils.equals(dl.getKey(), "com.amazon.dcp.sso.token.oauth.amazon.access_token")) {
            if (!z && (bundle == null || !bundle.getBoolean(TokenKeys.Options.KEY_FORCE_REFRESH_DMS_TO_OAUTH))) {
                if (gxVar.B.c(str2, dl, bundle, ejVar)) {
                    gxVar.a(str2, b);
                }
            } else {
                gxVar.a(str2, b);
                hu.k(gxVar.o, str2, b.ca(AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN));
            }
        }
        final String key = dl.getKey();
        b.a(new String[]{key}, new he.a() { // from class: com.amazon.identity.auth.device.gx.2
            @Override // com.amazon.identity.auth.device.he.a
            public void a(MAPError mAPError, Bundle bundle2) {
                bundle2.putInt(MAPError.KEY_ERROR_CODE, mAPError.getErrorCode());
                bundle2.putString(MAPError.KEY_ERROR_MESSAGE, mAPError.getErrorMessage());
                bundle2.putString(MAPError.KEY_ERROR_TYPE, mAPError.getErrorType());
                blVar.onError(bundle2);
            }

            @Override // com.amazon.identity.auth.device.he.a
            public void c(MAPError mAPError, String str3, int i, String str4) {
                hf.a(blVar, mAPError, str3, i, str4);
            }

            @Override // com.amazon.identity.auth.device.he.a
            public void w() {
                String ca = b.ca(key);
                gm gmVar = new gm(gx.this.o);
                String W = gv.W(dl.getPackageName(), AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_REFRESHED_AT);
                HashMap hashMap = new HashMap();
                hashMap.put(W, Long.toString(System.currentTimeMillis()));
                gmVar.d(str2, hashMap);
                hf.c(blVar, ca);
                if (z) {
                    gmVar.a(str2, "force_refresh_dms_to_oauth_done_once", "true");
                    mq.b("fixCentralTokenOnGrover/Canary", new String[0]);
                }
            }
        });
    }
}
