package com.amazon.alexa;

import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
/* compiled from: BaseOkHttpClientFactory.java */
/* loaded from: classes.dex */
public final class QuV {
    public QuV() {
        throw new IllegalStateException("No instances of BaseOkHttpClientFactory utility are allowed.");
    }

    public static OkHttpClient zZm() {
        return new OkHttpClient.Builder().writeTimeout(2L, TimeUnit.SECONDS).connectionPool(new ConnectionPool(0, 5L, TimeUnit.MINUTES)).build();
    }
}
