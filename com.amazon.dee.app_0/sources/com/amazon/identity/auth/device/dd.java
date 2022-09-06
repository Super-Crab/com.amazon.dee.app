package com.amazon.identity.auth.device;

import com.amazon.identity.auth.device.api.AuthenticationMethod;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class dd {
    public HttpURLConnection openConnection(URL url, AuthenticationMethod authenticationMethod) throws IOException {
        return cy.openConnection(url, authenticationMethod);
    }
}
