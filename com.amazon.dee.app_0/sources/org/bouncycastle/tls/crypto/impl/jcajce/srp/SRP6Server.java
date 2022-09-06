package org.bouncycastle.tls.crypto.impl.jcajce.srp;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.tls.crypto.SRP6Group;
import org.bouncycastle.tls.crypto.TlsHash;
/* loaded from: classes5.dex */
public class SRP6Server {
    protected BigInteger A;
    protected BigInteger B;
    protected BigInteger Key;
    protected BigInteger M1;
    protected BigInteger M2;
    protected BigInteger N;
    protected BigInteger S;
    protected BigInteger b;
    protected TlsHash digest;
    protected BigInteger g;
    protected SecureRandom random;
    protected BigInteger u;
    protected BigInteger v;

    private BigInteger calculateS() {
        return this.v.modPow(this.u, this.N).multiply(this.A).mod(this.N).modPow(this.b, this.N);
    }

    public BigInteger calculateSecret(BigInteger bigInteger) throws IllegalArgumentException {
        this.A = SRP6Util.validatePublicValue(this.N, bigInteger);
        this.u = SRP6Util.calculateU(this.digest, this.N, this.A, this.B);
        this.S = calculateS();
        return this.S;
    }

    public BigInteger calculateServerEvidenceMessage() throws IllegalStateException {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger bigInteger3 = this.A;
        if (bigInteger3 == null || (bigInteger = this.M1) == null || (bigInteger2 = this.S) == null) {
            throw new IllegalStateException("Impossible to compute M2: some data are missing from the previous operations (A,M1,S)");
        }
        this.M2 = SRP6Util.calculateM2(this.digest, this.N, bigInteger3, bigInteger, bigInteger2);
        return this.M2;
    }

    public BigInteger calculateSessionKey() throws IllegalArgumentException {
        BigInteger bigInteger = this.S;
        if (bigInteger == null || this.M1 == null || this.M2 == null) {
            throw new IllegalStateException("Impossible to compute Key: some data are missing from the previous operations (S,M1,M2)");
        }
        this.Key = SRP6Util.calculateKey(this.digest, this.N, bigInteger);
        return this.Key;
    }

    public BigInteger generateServerCredentials() {
        BigInteger calculateK = SRP6Util.calculateK(this.digest, this.N, this.g);
        this.b = selectPrivateValue();
        this.B = calculateK.multiply(this.v).mod(this.N).add(this.g.modPow(this.b, this.N)).mod(this.N);
        return this.B;
    }

    public void init(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, TlsHash tlsHash, SecureRandom secureRandom) {
        this.N = bigInteger;
        this.g = bigInteger2;
        this.v = bigInteger3;
        this.random = secureRandom;
        this.digest = tlsHash;
    }

    public void init(SRP6Group sRP6Group, BigInteger bigInteger, TlsHash tlsHash, SecureRandom secureRandom) {
        init(sRP6Group.getN(), sRP6Group.getG(), bigInteger, tlsHash, secureRandom);
    }

    protected BigInteger selectPrivateValue() {
        return SRP6Util.generatePrivateValue(this.N, this.g, this.random);
    }

    public boolean verifyClientEvidenceMessage(BigInteger bigInteger) throws IllegalStateException {
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        BigInteger bigInteger4 = this.A;
        if (bigInteger4 == null || (bigInteger2 = this.B) == null || (bigInteger3 = this.S) == null) {
            throw new IllegalStateException("Impossible to compute and verify M1: some data are missing from the previous operations (A,B,S)");
        }
        if (!SRP6Util.calculateM1(this.digest, this.N, bigInteger4, bigInteger2, bigInteger3).equals(bigInteger)) {
            return false;
        }
        this.M1 = bigInteger;
        return true;
    }
}
