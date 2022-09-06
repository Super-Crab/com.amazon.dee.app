package com.amazon.dee.app.services.network.interceptor;

import androidx.annotation.NonNull;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;
/* loaded from: classes12.dex */
public class ClientTimestampInterceptor implements Interceptor {
    @Override // okhttp3.Interceptor
    @NonNull
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        return chain.proceed(chain.request().newBuilder().addHeader("timestamp", Long.toString(System.currentTimeMillis() / 1000)).build());
    }
}
