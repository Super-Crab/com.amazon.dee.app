package com.amazonaws.internal.config;
/* loaded from: classes13.dex */
public class SignerConfig {
    private final String signerType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SignerConfig(String str) {
        this.signerType = str;
    }

    public String getSignerType() {
        return this.signerType;
    }

    public String toString() {
        return this.signerType;
    }

    SignerConfig(SignerConfig signerConfig) {
        this.signerType = signerConfig.getSignerType();
    }
}
