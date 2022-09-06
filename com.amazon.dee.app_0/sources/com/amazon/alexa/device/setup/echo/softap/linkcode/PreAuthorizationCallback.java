package com.amazon.alexa.device.setup.echo.softap.linkcode;
/* loaded from: classes6.dex */
public interface PreAuthorizationCallback {
    void authorizationFailure(Throwable th);

    void authorizationSuccess(PreAuthorizedLinkCode preAuthorizedLinkCode);
}
