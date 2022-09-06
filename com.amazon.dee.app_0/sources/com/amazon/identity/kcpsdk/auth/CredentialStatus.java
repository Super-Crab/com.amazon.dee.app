package com.amazon.identity.kcpsdk.auth;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public enum CredentialStatus {
    CredentialStatusOK(0),
    CredentialStatusStale(1),
    CredentialStatusInvalid(2),
    CredentialStatusUnknownWarning(3),
    CredentialStatusUnknownError(4);
    
    private final int mValue;

    CredentialStatus(int i) {
        this.mValue = i;
    }

    public int getValue() {
        return this.mValue;
    }
}
