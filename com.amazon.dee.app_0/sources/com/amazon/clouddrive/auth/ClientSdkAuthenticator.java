package com.amazon.clouddrive.auth;

import android.util.Log;
import com.amazon.clouddrive.utils.AssertUtils;
import java.io.IOException;
import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
/* loaded from: classes11.dex */
public class ClientSdkAuthenticator implements Authenticator, Interceptor {
    private static final int MAX_ATTEMPTS = 2;
    private static final String TAG = "ClientSdkAuthenticator";
    private final RequestAuthenticator requestAuthenticator;

    public ClientSdkAuthenticator(RequestAuthenticator requestAuthenticator) {
        AssertUtils.assertNotNull(requestAuthenticator, "AuthTokenProvider cannot be null!");
        this.requestAuthenticator = requestAuthenticator;
    }

    private boolean shouldRetry(Response response) {
        Response response2 = response;
        int i = 0;
        while (i < 2 && response2.priorResponse() != null) {
            response2 = response2.priorResponse();
            i++;
        }
        return i < 2;
    }

    @Override // okhttp3.Authenticator
    public final Request authenticate(Route route, Response response) throws IOException {
        if (!shouldRetry(response)) {
            logError("Reached max attempts to authenticate request.");
            return null;
        }
        return this.requestAuthenticator.authenticateRequest(response.request(), true);
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Request authenticateRequest = this.requestAuthenticator.authenticateRequest(request, false);
        if (authenticateRequest == null) {
            return chain.proceed(request);
        }
        return chain.proceed(authenticateRequest);
    }

    protected void logError(String str) {
        Log.e(TAG, str);
    }
}
