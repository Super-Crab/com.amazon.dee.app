package com.amazon.identity.auth.device.api;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.device.fp;
import com.amazon.identity.auth.device.token.OAuthTokenManager;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class MAPErrorCallbackHelper {
    private MAPErrorCallbackHelper() {
    }

    @FireOsSdk
    public static Bundle getErrorBundle(MAPError mAPError) {
        return getErrorBundle(mAPError, mAPError.getErrorMessage());
    }

    public static Bundle getErrorBundleForActorAPI(MAPError mAPError, String str, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putInt(MAPError.KEY_ERROR_CODE, mAPError.getErrorCode());
        bundle.putString(MAPError.KEY_ERROR_MESSAGE, str);
        bundle.putString(MAPError.KEY_ERROR_TYPE, mAPError.getErrorType());
        bundle.putBoolean(MAPActorManager.KEY_RETRYABLE, z);
        return bundle;
    }

    @FireOsSdk
    public static void onError(Callback callback, MAPError mAPError) {
        callback.onError(getErrorBundle(mAPError));
    }

    @FireOsSdk
    public static Bundle getErrorBundle(MAPError mAPError, String str) {
        Bundle bundle = new Bundle();
        bundle.putInt(MAPError.KEY_ERROR_CODE, mAPError.getErrorCode());
        bundle.putString(MAPError.KEY_ERROR_MESSAGE, str);
        bundle.putString(MAPError.KEY_ERROR_TYPE, mAPError.getErrorType());
        return bundle;
    }

    @FireOsSdk
    public static void onError(Callback callback, MAPError mAPError, String str) {
        callback.onError(getErrorBundle(mAPError, str));
    }

    public static Bundle getErrorBundle(MAPError mAPError, String str, @NonNull fp.a aVar) {
        Bundle errorBundle = getErrorBundle(mAPError, str);
        fp eE = aVar.eE();
        if (eE != null) {
            errorBundle.putAll(eE.eC());
        }
        if (aVar instanceof OAuthTokenManager.OAuthTokenManagerException) {
            errorBundle.putBoolean(MAPError.KEY_SHOULD_CLEAR_AUTH_COOKIES, ((OAuthTokenManager.OAuthTokenManagerException) aVar).fU());
        }
        return errorBundle;
    }
}
