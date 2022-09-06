package com.amazon.clouddrive.cdasdk;

import androidx.annotation.NonNull;
import java.io.IOException;
import java.util.UUID;
import okhttp3.Interceptor;
import okhttp3.Response;
/* loaded from: classes11.dex */
public class TraceInterceptor implements Interceptor {
    @NonNull
    static final String REQUEST_ID_HEADER = "x-amzn-RequestId";
    @NonNull
    static final String TRACE_ID_HEADER = "x-amzn-Trace-Id";

    @Override // okhttp3.Interceptor
    @NonNull
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        String uuid = UUID.randomUUID().toString();
        return chain.proceed(chain.request().newBuilder().header(REQUEST_ID_HEADER, uuid).header(TRACE_ID_HEADER, uuid).build());
    }
}
