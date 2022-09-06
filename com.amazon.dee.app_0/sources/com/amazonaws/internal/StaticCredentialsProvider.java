package com.amazonaws.internal;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
/* loaded from: classes13.dex */
public class StaticCredentialsProvider implements AWSCredentialsProvider {
    private final AWSCredentials credentials;

    public StaticCredentialsProvider(AWSCredentials aWSCredentials) {
        this.credentials = aWSCredentials;
    }

    @Override // com.amazonaws.auth.AWSCredentialsProvider
    /* renamed from: getCredentials */
    public AWSCredentials mo6648getCredentials() {
        return this.credentials;
    }

    @Override // com.amazonaws.auth.AWSCredentialsProvider
    public void refresh() {
    }
}
