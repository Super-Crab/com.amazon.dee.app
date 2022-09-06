package org.bouncycastle.tls.crypto;

import java.math.BigInteger;
/* loaded from: classes5.dex */
public class SRP6Group {
    private BigInteger N;
    private BigInteger g;

    public SRP6Group(BigInteger bigInteger, BigInteger bigInteger2) {
        this.N = bigInteger;
        this.g = bigInteger2;
    }

    public BigInteger getG() {
        return this.g;
    }

    public BigInteger getN() {
        return this.N;
    }
}
