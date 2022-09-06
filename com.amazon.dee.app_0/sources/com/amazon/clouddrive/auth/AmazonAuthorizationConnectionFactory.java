package com.amazon.clouddrive.auth;

import android.os.Bundle;
import com.amazon.clouddrive.exceptions.AuthorizationException;
import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.authorization.api.AmazonAuthorizationManager;
import com.amazon.identity.auth.device.authorization.api.AuthzConstants;
import com.amazon.identity.auth.device.shared.APIListener;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.conn.ssl.SSLSocketFactory;
@Deprecated
/* loaded from: classes11.dex */
public class AmazonAuthorizationConnectionFactory implements AuthenticatedURLConnectionFactory {
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

    public AmazonAuthorizationConnectionFactory(AmazonAuthorizationManager amazonAuthorizationManager, String[] strArr) {
        this.mAmazonAuthorizationManager = amazonAuthorizationManager;
        this.mAppScopes = strArr;
    }

    @Override // com.amazon.clouddrive.auth.AuthenticatedURLConnectionFactory
    public HttpURLConnection createHttpURLConnection(URL url) throws CloudDriveException, InterruptedException, IOException {
        final ApiResultHolder apiResultHolder = new ApiResultHolder();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.mAmazonAuthorizationManager.getToken(this.mAppScopes, new APIListener() { // from class: com.amazon.clouddrive.auth.AmazonAuthorizationConnectionFactory.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.identity.auth.device.api.Listener
            public void onError(AuthError authError) {
                apiResultHolder.authError = authError;
                countDownLatch.countDown();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.identity.auth.device.api.Listener
            public void onSuccess(Bundle bundle) {
                apiResultHolder.bundle = bundle;
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
        AuthError authError = apiResultHolder.authError;
        if (authError == null) {
            Bundle bundle = apiResultHolder.bundle;
            if (bundle != null) {
                String string = bundle.getString(AuthzConstants.BUNDLE_KEY.TOKEN.val);
                if (string != null) {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    if (httpURLConnection instanceof HttpsURLConnection) {
                        httpURLConnection.addRequestProperty("Authorization", "Bearer " + string);
                        ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
                    }
                    return httpURLConnection;
                }
                throw new AuthorizationException("No token");
            }
            throw new AuthorizationException("No token");
        }
        throw new AuthorizationException(authError);
    }
}
