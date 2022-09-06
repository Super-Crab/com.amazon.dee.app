package com.amazon.alexa.wakeword.davs;

import java.util.Collections;
import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
/* loaded from: classes11.dex */
public final class OkHttpClientFactory {
    public static final long NETWORK_WRITE_TIMEOUT_SECONDS = 2;

    private OkHttpClientFactory() {
        throw new UnsupportedOperationException("don't instantiate");
    }

    public static OkHttpClient createClient() {
        return new OkHttpClient.Builder().writeTimeout(2L, TimeUnit.SECONDS).connectionPool(new ConnectionPool(0, 5L, TimeUnit.MINUTES)).connectionSpecs(Collections.singletonList(new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).tlsVersions(TlsVersion.TLS_1_2).build())).retryOnConnectionFailure(false).build();
    }
}
