package org.bouncycastle.tls.crypto;

import java.math.BigInteger;
/* loaded from: classes5.dex */
public class DHGroup {
    private final BigInteger g;
    private final int l;
    private final BigInteger p;
    private final BigInteger q;

    public DHGroup(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int i) {
        this.p = bigInteger;
        this.g = bigInteger3;
        this.q = bigInteger2;
        this.l = i;
    }

    public BigInteger getG() {
        return this.g;
    }

    public int getL() {
        return this.l;
    }

    public BigInteger getP() {
        return this.p;
    }

    public BigInteger getQ() {
        return this.q;
    }
}
