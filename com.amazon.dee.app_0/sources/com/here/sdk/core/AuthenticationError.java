package com.here.sdk.core;
/* loaded from: classes3.dex */
public enum AuthenticationError {
    INVALID_PARAMETER(1),
    AUTHENTICATION_FAILED(2),
    NO_CONNECTION(3);
    
    public final int value;

    AuthenticationError(int i) {
        this.value = i;
    }
}
