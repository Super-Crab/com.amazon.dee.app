package com.amazon.alexa.wakeword.davs;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.auth.TokenProvider;
import okhttp3.OkHttpClient;
/* loaded from: classes11.dex */
public class NetworkManager {
    private final OkHttpClient authorizedHttpClient;
    private final OkHttpClient unauthorizedHttpClient;

    public NetworkManager(TokenProvider tokenProvider) {
        this(tokenProvider, OkHttpClientFactory.createClient());
    }

    private void disposeHttpClient(OkHttpClient okHttpClient) {
        okHttpClient.dispatcher().executorService().shutdown();
        okHttpClient.connectionPool().evictAll();
    }

    public OkHttpClient getAuthorizedHttpClient() {
        return this.authorizedHttpClient;
    }

    public OkHttpClient getUnauthorizedHttpClient() {
        return this.unauthorizedHttpClient;
    }

    public void teardown() {
        disposeHttpClient(this.authorizedHttpClient);
        disposeHttpClient(this.unauthorizedHttpClient);
    }

    @VisibleForTesting
    NetworkManager(TokenProvider tokenProvider, OkHttpClient okHttpClient) {
        AuthorizationInterceptor authorizationInterceptor = new AuthorizationInterceptor(tokenProvider);
        this.unauthorizedHttpClient = okHttpClient;
        this.authorizedHttpClient = okHttpClient.newBuilder().addInterceptor(authorizationInterceptor).retryOnConnectionFailure(false).build();
    }
}
