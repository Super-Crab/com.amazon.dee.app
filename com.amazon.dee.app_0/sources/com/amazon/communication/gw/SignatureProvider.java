package com.amazon.communication.gw;

import amazon.communication.authentication.SigningException;
/* loaded from: classes12.dex */
public interface SignatureProvider {

    /* loaded from: classes12.dex */
    public static class SigningResult {
        public final byte[] signature;
        public final String token;

        public SigningResult(byte[] bArr, String str) {
            this.signature = bArr;
            this.token = str;
        }
    }

    String getAlgorithm();

    SigningResult sign(byte[] bArr, String str) throws SigningException;
}
