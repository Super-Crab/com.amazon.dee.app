package com.amazon.clouddrive.cdasdk;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.clouddrive.cdasdk.util.Logger;
import java.io.IOException;
import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class TokenAuthenticator implements Authenticator, Interceptor {
    static final String ACCESS_TOKEN_HEADER = "x-amz-access-token";
    static final String ATTEMPTS_COUNT_HEADER = "attempts-count";
    static final int MAX_ATTEMPTS = 2;
    private static final String TAG = "TokenAuthenticator";
    @NonNull
    private final Context context;
    @NonNull
    private final Logger logger;
    @NonNull
    private final TokenProvider tokenProvider;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TokenAuthenticator(@NonNull Context context, @NonNull TokenProvider tokenProvider, @NonNull Logger logger) {
        this.context = context;
        this.tokenProvider = tokenProvider;
        this.logger = logger;
    }

    @Nullable
    private Request authenticateRequest(Request request) {
        String accessTokenBlocking = this.tokenProvider.getAccessTokenBlocking(this.context);
        if (accessTokenBlocking != null && !accessTokenBlocking.isEmpty()) {
            return request.newBuilder().header("x-amz-access-token", accessTokenBlocking).build();
        }
        this.logger.e(TAG, "Unable to authenticate request");
        return null;
    }

    @Override // okhttp3.Authenticator
    @Nullable
    public Request authenticate(@NonNull Route route, @NonNull Response response) {
        String header = response.request().header(ATTEMPTS_COUNT_HEADER);
        int parseInt = header == null ? 0 : Integer.parseInt(header);
        if (parseInt == 2) {
            this.logger.e(TAG, "Reached max attempts to authenticate request");
            return null;
        }
        Request request = response.request();
        Request authenticateRequest = authenticateRequest(request);
        if (authenticateRequest != null) {
            request = authenticateRequest;
        }
        return request.newBuilder().header(ATTEMPTS_COUNT_HEADER, String.valueOf(parseInt + 1)).build();
    }

    @Override // okhttp3.Interceptor
    @NonNull
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Request authenticateRequest = authenticateRequest(request);
        if (authenticateRequest == null) {
            return chain.proceed(request);
        }
        return chain.proceed(authenticateRequest);
    }
}
