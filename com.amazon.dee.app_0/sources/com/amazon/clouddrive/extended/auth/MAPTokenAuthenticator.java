package com.amazon.clouddrive.extended.auth;

import android.content.Context;
import android.util.Log;
import com.amazon.clouddrive.auth.RequestAuthenticator;
import com.amazon.clouddrive.utils.AssertUtils;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.TokenKeys;
import com.amazon.identity.auth.device.api.TokenManagement;
import okhttp3.Request;
/* loaded from: classes11.dex */
public class MAPTokenAuthenticator implements RequestAuthenticator {
    private static final String ACCESS_TOKEN_HEADER = "x-amz-access-token";
    private static final String TAG = "com.amazon.clouddrive.extended.auth.MAPTokenAuthenticator";
    private final Context mContext;
    private final MAPAccountManager mMapAccountManager;

    public MAPTokenAuthenticator(Context context, MAPAccountManager mAPAccountManager) {
        AssertUtils.assertNotNull(context, "Context was null.");
        AssertUtils.assertNotNull(mAPAccountManager, "MAPAccountManager was null.");
        this.mContext = context;
        this.mMapAccountManager = mAPAccountManager;
    }

    private String getAccessTokenBlocking() {
        String tokenNoRetry = getTokenNoRetry();
        return tokenNoRetry == null ? getTokenNoRetry() : tokenNoRetry;
    }

    private String getTokenNoRetry() {
        String account = this.mMapAccountManager.getAccount();
        if (account == null) {
            return null;
        }
        TokenManagement tokenManagement = new TokenManagement(this.mContext);
        String accessTokenKeyForPackage = TokenKeys.getAccessTokenKeyForPackage(this.mContext.getPackageName());
        BlockingMAPListener blockingMAPListener = new BlockingMAPListener();
        tokenManagement.getToken(account, accessTokenKeyForPackage, null, blockingMAPListener);
        return blockingMAPListener.getResults().getString("value_key");
    }

    @Override // com.amazon.clouddrive.auth.RequestAuthenticator
    public Request authenticateRequest(Request request, boolean z) {
        String accessTokenBlocking = getAccessTokenBlocking();
        if (accessTokenBlocking == null) {
            Log.e(TAG, "MAP returned null token.");
            return null;
        }
        return request.newBuilder().header("x-amz-access-token", accessTokenBlocking).build();
    }
}
