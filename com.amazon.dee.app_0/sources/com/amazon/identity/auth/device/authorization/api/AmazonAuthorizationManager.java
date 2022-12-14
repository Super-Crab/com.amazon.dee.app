package com.amazon.identity.auth.device.authorization.api;

import android.content.Context;
import android.os.Bundle;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.api.authorization.AuthorizationManager;
import com.amazon.identity.auth.device.appid.ThirdPartyAppIdentifier;
import com.amazon.identity.auth.device.authorization.InternalAuthManager;
import com.amazon.identity.auth.device.authorization.api.AuthzConstants;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.device.shared.APIListener;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.util.concurrent.Future;
/* loaded from: classes12.dex */
public class AmazonAuthorizationManager {
    private static final String LOG_TAG = "com.amazon.identity.auth.device.authorization.api.AmazonAuthorizationManager";
    private static final ThirdPartyAppIdentifier appIdentifier = new ThirdPartyAppIdentifier();
    private String clientId;
    private final Context mContext;

    public AmazonAuthorizationManager(Context context, Bundle bundle) {
        String str = LOG_TAG;
        MAPLog.pii(str, "AmazonAuthorizationManager:sdkVer=3.0.2 libVer=3.5.3", "options=" + bundle);
        if (context != null) {
            this.mContext = context;
            if (bundle == null) {
                MAPLog.i(LOG_TAG, "Options bundle is null");
            }
            AppInfo appInfo = appIdentifier.getAppInfo(this.mContext.getPackageName(), this.mContext);
            if (appInfo != null && appInfo.getClientId() != null) {
                this.clientId = appInfo.getClientId();
                if (bundle == null) {
                    return;
                }
                AuthorizationManager.setSandboxMode(context, bundle.getBoolean(AuthzConstants.BUNDLE_KEY.SANDBOX.val, false));
                return;
            }
            throw new IllegalArgumentException("Invalid API Key");
        }
        throw new IllegalArgumentException("context must not be null!");
    }

    private boolean isAPIKeyValid() {
        return InternalAuthManager.getInstance(this.mContext).isAPIKeyValid(this.mContext);
    }

    @Deprecated
    public Future<Bundle> authorize(String[] strArr, Bundle bundle, AuthorizationListener authorizationListener) {
        return InternalAuthManager.getInstance(this.mContext).authorize(null, this.mContext, strArr, bundle, authorizationListener);
    }

    @Deprecated
    public Future<Bundle> clearAuthorizationState(APIListener aPIListener) {
        return InternalAuthManager.getInstance(this.mContext).clearAuthorizationState(this.mContext, aPIListener);
    }

    public String getAppId() throws AuthError {
        if (isAPIKeyValid()) {
            String str = LOG_TAG;
            MAPLog.i(str, this.mContext.getPackageName() + " calling getAppId");
            AppInfo appInfo = new ThirdPartyAppIdentifier().getAppInfo(this.mContext.getPackageName(), this.mContext);
            if (appInfo != null) {
                return appInfo.getAppFamilyId();
            }
            return null;
        }
        throw new AuthError("APIKey is invalid", AuthError.ERROR_TYPE.ERROR_ACCESS_DENIED);
    }

    public String getAppVariantId() throws AuthError {
        if (isAPIKeyValid()) {
            String str = LOG_TAG;
            MAPLog.i(str, this.mContext.getPackageName() + " calling getAppId");
            AppInfo appInfo = new ThirdPartyAppIdentifier().getAppInfo(this.mContext.getPackageName(), this.mContext);
            if (appInfo != null) {
                return appInfo.getAppVariantId();
            }
            return null;
        }
        throw new AuthError("APIKey is invalid", AuthError.ERROR_TYPE.ERROR_ACCESS_DENIED);
    }

    @Deprecated
    public String getClientId() {
        return InternalAuthManager.getInstance(this.mContext).getClientId();
    }

    @Deprecated
    public Future<Bundle> getProfile(APIListener aPIListener) {
        return InternalAuthManager.getInstance(this.mContext).getProfile(this.mContext, null, aPIListener);
    }

    @Deprecated
    public String getRedirectUri() throws AuthError {
        return InternalAuthManager.getInstance(this.mContext).getRedirectURI(this.mContext);
    }

    @Deprecated
    public Future<Bundle> getToken(String[] strArr, APIListener aPIListener) {
        return InternalAuthManager.getInstance(this.mContext).getToken(this.mContext, strArr, aPIListener);
    }
}
