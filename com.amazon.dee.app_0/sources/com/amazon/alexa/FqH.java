package com.amazon.alexa;

import com.amazon.alexa.auth.AccessToken;
import com.amazon.alexa.auth.AuthorizationException;
import com.amazon.alexa.auth.TokenProvider;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
/* compiled from: AuthorizationInterceptor.java */
@Singleton
/* loaded from: classes.dex */
public class FqH implements Interceptor {
    public final TokenProvider zZm;

    @Inject
    public FqH(TokenProvider tokenProvider) {
        this.zZm = tokenProvider;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        try {
            AccessToken token = this.zZm.getToken();
            if (token != null && token.getValue() != null) {
                Request.Builder newBuilder = chain.request().newBuilder();
                StringBuilder zZm = C0179Pya.zZm("Bearer ");
                zZm.append(token.getValue());
                return chain.proceed(newBuilder.addHeader("authorization", zZm.toString()).build());
            }
            throw new IOException("Received token was null");
        } catch (AuthorizationException e) {
            throw new IOException("Exception while retrieving token", e);
        }
    }
}
