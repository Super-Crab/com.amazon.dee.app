package com.amazon.identity.auth.device.api;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.ej;
import com.amazon.identity.auth.device.hh;
import com.amazon.identity.auth.device.hi;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.mq;
import com.amazon.identity.auth.device.mv;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class TokenManagement {
    @FireOsSdk
    public static final int ERROR_INVALID_PARAMETER = 1;
    @FireOsSdk
    public static final String KEY_ACCESS_TOKEN_FROM_CACHE = "access_token_from_cache";
    @FireOsSdk
    public static final String KEY_ACCOUNT_RECOVER_CONTEXT_BUNDLE = "com.amazon.identity.mobi.account.recover.context";
    @FireOsSdk
    public static final String KEY_ERROR_CODE = "com.amazon.dcp.sso.ErrorCode";
    @FireOsSdk
    public static final String KEY_ERROR_MESSAGE = "com.amazon.dcp.sso.ErrorMessage";
    public static final String TAG = "com.amazon.identity.auth.device.api.TokenManagement";
    @FireOsSdk
    public static final String VALUE_KEY = "value_key";
    private static final String fL = "TokenManagement";
    hh gL;
    private final Context mContext;

    @FireOsSdk
    public TokenManagement(Context context) {
        MAPInit.getInstance(context).initialize();
        this.mContext = ed.M(context);
    }

    private synchronized hh bl() {
        if (this.gL == null) {
            this.gL = hi.ad(this.mContext);
        }
        return this.gL;
    }

    @FireOsSdk
    public MAPFuture<Bundle> getCookies(String str, String str2, Bundle bundle, Callback callback) {
        ej by = ej.by("TokenManagement:GetCookies");
        return bl().f(str, str2, bundle, mq.a(by, by.f(this.mContext, "Time"), callback), by);
    }

    @FireOsSdk
    public MAPFuture<Bundle> getCookiesForActor(String str, String str2, String str3, Bundle bundle, Callback callback) {
        ej by = ej.by("TokenManagement:GetCookiesForActor");
        return bl().a(str, str2, str3, bundle, mq.a(by, by.f(this.mContext, "Time"), callback), by);
    }

    @FireOsSdk
    public MAPFuture<Bundle> getToken(String str, String str2, Bundle bundle, Callback callback) {
        ej by = ej.by("TokenManagement:GetToken");
        return bl().e(str, str2, bundle, mq.a(by, by.f(this.mContext, "Time"), callback), by);
    }

    @FireOsSdk
    public MAPFuture<Bundle> getTokenForActor(Context context, String str, String str2, String str3, String str4, Bundle bundle, Callback callback) {
        ej by = ej.by("TokenManagement:GetTokenForActor");
        return bl().a(context, str, str2, str3, str4, bundle, mq.a(by, by.f(this.mContext, "Time"), callback), by);
    }

    @FireOsSdk
    public String getValue(String str, String str2, Bundle bundle, long j) throws MAPCallbackErrorException, InterruptedException, ExecutionException, TimeoutException {
        return getToken(str, str2, bundle, null).get(j, TimeUnit.MILLISECONDS).getString("value_key");
    }

    @FireOsSdk
    public MAPFuture<Bundle> invalidateCookies(String str, String str2, Bundle bundle, Callback callback) {
        ej by = ej.by("TokenManagement:InvalidateCookies");
        return bl().g(str, str2, bundle, mq.a(by, by.f(this.mContext, "Time"), callback), by);
    }

    @FireOsSdk
    public MAPFuture<Bundle> peekCookies(String str, String str2, Callback callback) {
        ej by = ej.by("TokenManagement:PeekCookies");
        if (!TextUtils.isEmpty(str)) {
            mv f = by.f(this.mContext, "Time");
            return bl().f(str, str2, GeneratedOutlineSupport1.outline13("com.amazon.identity.auth.device.internal.cookiekeys.options.ignorefresh", true), mq.a(by, f, callback), by);
        }
        io.e(TAG, "Directed Id given was null. Cannot peek cookies for a deregistered device");
        throw new IllegalArgumentException("Directed Id given was null. Cannot peek cookies for a deregistered device");
    }
}
