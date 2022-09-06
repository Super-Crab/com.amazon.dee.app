package com.amazon.clouddrive.auth;

import android.os.Bundle;
import android.util.Log;
import com.amazon.clouddrive.utils.AssertUtils;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.authorization.api.AmazonAuthorizationManager;
import com.amazon.identity.auth.device.authorization.api.AuthzConstants;
import com.amazon.identity.auth.device.shared.APIListener;
import java.util.concurrent.CountDownLatch;
import okhttp3.Request;
/* loaded from: classes11.dex */
public class AmazonTokenAuthenticator implements RequestAuthenticator {
    private static final String ACCESS_TOKEN_HEADER = "x-amz-access-token";
    private static final String TAG = "AmazonTokenAuthenticator";
    private AmazonAuthorizationManager mAmazonAuthorizationManager;
    private String[] mAppScopes;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class ApiResultHolder {
        AuthError authError;
        Bundle bundle;

        private ApiResultHolder() {
        }
    }

    public AmazonTokenAuthenticator(AmazonAuthorizationManager amazonAuthorizationManager, String[] strArr) {
        AssertUtils.assertNotNull(amazonAuthorizationManager, "AuthorizationManager was null or empty.");
        AssertUtils.assertNotNullOrEmpty(strArr, "AppScopes was null or empty.");
        this.mAmazonAuthorizationManager = amazonAuthorizationManager;
        this.mAppScopes = strArr;
    }

    private String getAccessTokenBlocking() {
        Bundle bundle;
        String string;
        final ApiResultHolder apiResultHolder = new ApiResultHolder();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.mAmazonAuthorizationManager.getToken(this.mAppScopes, new APIListener() { // from class: com.amazon.clouddrive.auth.AmazonTokenAuthenticator.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.identity.auth.device.api.Listener
            public void onError(AuthError authError) {
                apiResultHolder.authError = authError;
                countDownLatch.countDown();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.identity.auth.device.api.Listener
            public void onSuccess(Bundle bundle2) {
                apiResultHolder.bundle = bundle2;
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            Log.e(TAG, "Interrupted while awaiting access token.");
            e.printStackTrace();
        }
        if (apiResultHolder.authError != null || (bundle = apiResultHolder.bundle) == null || (string = bundle.getString(AuthzConstants.BUNDLE_KEY.TOKEN.val)) == null) {
            return null;
        }
        return string;
    }

    @Override // com.amazon.clouddrive.auth.RequestAuthenticator
    public Request authenticateRequest(Request request, boolean z) {
        String accessTokenBlocking = getAccessTokenBlocking();
        if (accessTokenBlocking == null) {
            Log.e(TAG, "LWA returned null token.");
            return null;
        }
        return request.newBuilder().header("x-amz-access-token", accessTokenBlocking).build();
    }
}
