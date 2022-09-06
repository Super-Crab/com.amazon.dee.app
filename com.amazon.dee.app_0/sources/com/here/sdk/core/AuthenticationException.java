package com.here.sdk.core;
/* loaded from: classes3.dex */
public final class AuthenticationException extends Exception {
    public final AuthenticationError error;

    public AuthenticationException(AuthenticationError authenticationError) {
        super(authenticationError.toString());
        this.error = authenticationError;
    }
}
