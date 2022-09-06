package com.amazon.clouddrive.internal;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;
/* loaded from: classes11.dex */
public class UserAgentInterceptor implements Interceptor {
    private static final String USER_AGENT_HEADER = "User-Agent";
    private final String userAgent;

    public UserAgentInterceptor(String str) {
        this.userAgent = str;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        return chain.proceed(chain.request().newBuilder().header("User-Agent", this.userAgent).build());
    }
}
