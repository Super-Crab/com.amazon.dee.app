package com.amazon.alexa.wakeword.davs;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.auth.AccessToken;
import com.amazon.alexa.auth.AuthorizationException;
import com.amazon.alexa.auth.TokenProvider;
import com.amazon.alexa.utils.validation.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
/* loaded from: classes11.dex */
public class AuthorizationInterceptor implements Interceptor {
    @VisibleForTesting
    static final String AUTHORIZATION_HEADER_KEY = "authorization";
    @VisibleForTesting
    static final String AUTHORIZATION_HEADER_VALUE_PREFIX = "Bearer ";
    private final TokenProvider tokenProvider;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AuthorizationInterceptor(TokenProvider tokenProvider) {
        Preconditions.notNull(tokenProvider, "token provider is null");
        this.tokenProvider = tokenProvider;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        try {
            AccessToken token = this.tokenProvider.getToken();
            if (token != null && token.getValue() != null) {
                Request.Builder newBuilder = chain.request().newBuilder();
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107(AUTHORIZATION_HEADER_VALUE_PREFIX);
                outline107.append(token.getValue());
                return chain.proceed(newBuilder.addHeader(AUTHORIZATION_HEADER_KEY, outline107.toString()).build());
            }
            throw new IOException("Received token was null");
        } catch (AuthorizationException e) {
            throw new IOException("Exception while retrieving token", e);
        }
    }
}
