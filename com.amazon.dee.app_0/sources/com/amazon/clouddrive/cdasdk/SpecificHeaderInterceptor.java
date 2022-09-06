package com.amazon.clouddrive.cdasdk;

import androidx.annotation.NonNull;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;
/* loaded from: classes11.dex */
class SpecificHeaderInterceptor implements Interceptor {
    @NonNull
    private final String headerKey;
    @NonNull
    private final String headerValue;

    SpecificHeaderInterceptor(@NonNull String str, @NonNull String str2) {
        this.headerKey = str;
        this.headerValue = str2;
    }

    @Override // okhttp3.Interceptor
    @NonNull
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        return chain.proceed(chain.request().newBuilder().header(this.headerKey, this.headerValue).build());
    }
}
