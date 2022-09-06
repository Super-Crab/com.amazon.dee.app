package org.bouncycastle.tls.crypto.impl.jcajce.srp;

import java.math.BigInteger;
import org.bouncycastle.tls.crypto.SRP6Group;
import org.bouncycastle.tls.crypto.TlsHash;
/* loaded from: classes5.dex */
public class SRP6VerifierGenerator {
    protected BigInteger N;
    protected TlsHash digest;
    protected BigInteger g;

    public BigInteger generateVerifier(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return this.g.modPow(SRP6Util.calculateX(this.digest, this.N, bArr, bArr2, bArr3), this.N);
    }

    public void init(BigInteger bigInteger, BigInteger bigInteger2, TlsHash tlsHash) {
        this.N = bigInteger;
        this.g = bigInteger2;
        this.digest = tlsHash;
    }

    public void init(SRP6Group sRP6Group, TlsHash tlsHash) {
        this.N = sRP6Group.getN();
        this.g = sRP6Group.getG();
        this.digest = tlsHash;
    }
}
