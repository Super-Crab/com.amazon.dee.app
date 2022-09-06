package com.amazon.whisperjoin.credentiallocker;

import com.amazon.identity.auth.device.api.AuthenticatedURLConnection;
import com.amazon.identity.auth.device.api.AuthenticationMethod;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
/* loaded from: classes13.dex */
public class AuthenticatedUrlConnectionFactory implements HttpUrlConnectionFactory {
    private final AuthenticationMethod authMethod;

    public AuthenticatedUrlConnectionFactory(AuthenticationMethod authenticationMethod) {
        this.authMethod = authenticationMethod;
    }

    @Override // com.amazon.whisperjoin.credentiallocker.HttpUrlConnectionFactory
    public HttpURLConnection newConnection(URL url) throws IOException {
        return AuthenticatedURLConnection.openConnection(url, this.authMethod);
    }
}
