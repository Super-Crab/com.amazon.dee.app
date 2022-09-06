package com.amazon.alexa.device.setup.echo.softap.linkcode;

import okhttp3.OkHttpClient;
/* loaded from: classes6.dex */
public interface LinkCodeAuthorizer {
    void authorizeLinkCode(String str, AuthorizationCallback authorizationCallback);

    void authorizeLinkCode(String str, OkHttpClient okHttpClient, AuthorizationCallback authorizationCallback);

    void generatePreAuthorizedLinkCode(PreAuthorizationCallback preAuthorizationCallback);
}
