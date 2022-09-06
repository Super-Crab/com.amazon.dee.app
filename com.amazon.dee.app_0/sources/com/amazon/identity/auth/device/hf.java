package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.util.SparseIntArray;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.TokenKeys;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.identity.auth.device.fp;
import com.amazon.identity.auth.device.token.OAuthTokenManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class hf {
    private static final String TAG = "com.amazon.identity.auth.device.hf";
    private static final SparseIntArray aS;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        aS = sparseIntArray;
        sparseIntArray.append(7, MAPAccountManager.RegistrationError.BAD_REQUEST.value());
        aS.append(8, MAPAccountManager.RegistrationError.BAD_REQUEST.value());
        aS.append(4, MAPAccountManager.RegistrationError.REGISTER_FAILED.value());
        aS.append(5, MAPAccountManager.RegistrationError.PARSE_ERROR.value());
        aS.append(3, MAPAccountManager.RegistrationError.NETWORK_FAILURE.value());
        aS.append(1, MAPAccountManager.RegistrationError.REGISTER_FAILED.value());
        aS.append(6, MAPAccountManager.RegistrationError.UNRECOGNIZED.value());
    }

    private hf() {
    }

    @Deprecated
    public static void a(Callback callback, String str, boolean z) {
        if (callback == null) {
            io.e(TAG, "Cannot callback success because no callback was given");
        } else {
            callback.onSuccess(d(str, z));
        }
    }

    @Deprecated
    public static void c(Callback callback, String str) {
        a(callback, str, false);
    }

    @Deprecated
    public static void d(Callback callback, MAPError mAPError, String str, int i, String str2) {
        if (callback == null) {
            io.e(TAG, "Cannot callback error because no callback was given");
        } else {
            callback.onError(a(mAPError, str, i, str2));
        }
    }

    @Deprecated
    public static Bundle getErrorBundle(MAPError mAPError, String str, fp.a aVar) {
        Bundle a = a(mAPError, str, aVar.bE(), aVar.bF());
        fp eE = aVar.eE();
        if (eE != null) {
            a.putAll(eE.eC());
        }
        if (aVar instanceof OAuthTokenManager.OAuthTokenManagerException) {
            a.putBoolean(MAPError.KEY_SHOULD_CLEAR_AUTH_COOKIES, ((OAuthTokenManager.OAuthTokenManagerException) aVar).fU());
        }
        return a;
    }

    @Deprecated
    public static void a(Callback callback, String str, long j) {
        if (callback == null) {
            io.e(TAG, "Cannot callback success because no callback was given");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("value_key", str);
        bundle.putLong(TokenKeys.Options.KEY_OAUTH_TTL_MS_LONG, j);
        callback.onSuccess(bundle);
    }

    @Deprecated
    public static Bundle d(String str, boolean z) {
        Bundle outline11 = GeneratedOutlineSupport1.outline11("value_key", str);
        if (z) {
            io.i(TAG, "This valid token is from cache while service returns error.");
            outline11.putBoolean(TokenManagement.KEY_ACCESS_TOKEN_FROM_CACHE, true);
        }
        return outline11;
    }

    @Deprecated
    public static void a(Callback callback, MAPError mAPError, String str, fp.a aVar) {
        if (callback == null) {
            io.e(TAG, "Cannot callback error because no callback was given");
        } else {
            callback.onError(getErrorBundle(mAPError, str, aVar));
        }
    }

    @Deprecated
    public static Bundle a(MAPError mAPError, String str, int i, String str2) {
        Bundle bundle = new Bundle();
        bundle.putInt("com.amazon.dcp.sso.ErrorCode", i);
        bundle.putString("com.amazon.dcp.sso.ErrorMessage", str2);
        bundle.putInt(MAPError.KEY_ERROR_CODE, mAPError.getErrorCode());
        bundle.putString(MAPError.KEY_ERROR_MESSAGE, str);
        bundle.putString(MAPError.KEY_ERROR_TYPE, mAPError.getErrorType());
        return bundle;
    }

    @Deprecated
    public static Bundle a(MAPError mAPError, String str, MAPAccountManager.RegistrationError registrationError, String str2) {
        return a(mAPError, str, registrationError.value(), str2);
    }

    @Deprecated
    public static void a(Callback callback, MAPError mAPError, String str, int i, String str2) {
        if (callback == null) {
            return;
        }
        int i2 = aS.get(i, Integer.MIN_VALUE);
        Integer valueOf = i2 == Integer.MIN_VALUE ? null : Integer.valueOf(i2);
        Bundle bundle = new Bundle();
        if (valueOf != null) {
            bundle = a(mAPError, str, valueOf.intValue(), str2);
        }
        bundle.putInt("errorCode", i);
        bundle.putString("errorMessage", str2);
        callback.onError(bundle);
    }
}
