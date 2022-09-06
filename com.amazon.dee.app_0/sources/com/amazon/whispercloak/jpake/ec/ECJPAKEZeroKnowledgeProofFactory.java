package com.amazon.whispercloak.jpake.ec;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.BigIntegers;
/* loaded from: classes13.dex */
public class ECJPAKEZeroKnowledgeProofFactory {
    final ECJPAKEPrimeOrderGroup mGroup;
    final Digest mMessageDigest;
    final SecureRandom mRandom;

    public ECJPAKEZeroKnowledgeProofFactory(ECJPAKEPrimeOrderGroup eCJPAKEPrimeOrderGroup, SecureRandom secureRandom, Digest digest) {
        this.mGroup = eCJPAKEPrimeOrderGroup;
        this.mRandom = secureRandom;
        this.mMessageDigest = digest;
    }

    private BigInteger getDigest(String str, ECPoint eCPoint, ECPoint eCPoint2, ECPoint eCPoint3) {
        byte[] encoded = eCPoint.getEncoded(false);
        byte[] encoded2 = eCPoint2.getEncoded(false);
        byte[] encoded3 = eCPoint3.getEncoded(false);
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        this.mMessageDigest.reset();
        this.mMessageDigest.update(ByteBuffer.allocate(4).putInt(encoded.length).array(), 0, 4);
        this.mMessageDigest.update(encoded, 0, encoded.length);
        this.mMessageDigest.update(ByteBuffer.allocate(4).putInt(encoded2.length).array(), 0, 4);
        this.mMessageDigest.update(encoded2, 0, encoded2.length);
        this.mMessageDigest.update(ByteBuffer.allocate(4).putInt(encoded3.length).array(), 0, 4);
        this.mMessageDigest.update(encoded3, 0, encoded3.length);
        this.mMessageDigest.update(ByteBuffer.allocate(4).putInt(bytes.length).array(), 0, 4);
        this.mMessageDigest.update(bytes, 0, bytes.length);
        byte[] bArr = new byte[this.mMessageDigest.getDigestSize()];
        this.mMessageDigest.doFinal(bArr, 0);
        return new BigInteger(1, bArr);
    }

    public ECJPAKEZeroKnowledgeProof generate(String str, ECPoint eCPoint, BigInteger bigInteger) {
        return generate(str, eCPoint, bigInteger, eCPoint.multiply(bigInteger));
    }

    public void validate(String str, ECPoint eCPoint, ECPoint eCPoint2, ECJPAKEZeroKnowledgeProof eCJPAKEZeroKnowledgeProof) throws CryptoException {
        if (!eCPoint2.isInfinity()) {
            ECPoint normalize = eCPoint2.normalize();
            BigInteger bigInteger = normalize.getXCoord().toBigInteger();
            BigInteger bigInteger2 = normalize.getYCoord().toBigInteger();
            if (bigInteger.compareTo(BigInteger.ZERO) >= 0 && bigInteger.compareTo(this.mGroup.getQ()) < 0 && bigInteger2.compareTo(BigInteger.ZERO) >= 0 && bigInteger2.compareTo(this.mGroup.getQ()) < 0) {
                try {
                    this.mGroup.getCurve().importPoint(eCPoint2);
                    if (!eCPoint2.multiply(this.mGroup.getCoFactor()).isInfinity()) {
                        try {
                            if (eCJPAKEZeroKnowledgeProof.getGv().equals(eCPoint.multiply(eCJPAKEZeroKnowledgeProof.getR()).add(eCPoint2.multiply(getDigest(str, eCPoint, eCJPAKEZeroKnowledgeProof.getGv(), eCPoint2).mod(this.mGroup.getN()))))) {
                                return;
                            }
                            throw new CryptoException("invalid zero-knowledge proof: G*v != G*r + G*x*h");
                        } catch (IllegalArgumentException unused) {
                            throw new CryptoException("invalid zero-knowledge proof: values invalid for elements");
                        }
                    }
                    throw new CryptoException("invalid zero-knowledge proof: n*G*x is not infinity");
                } catch (IllegalArgumentException unused2) {
                    throw new CryptoException("invalid zero-knowledge proof: G*x is not on the curve");
                }
            }
            throw new CryptoException("invalid zero-knowledge proof: G*x is not in Fq");
        }
        throw new CryptoException("invalid zero-knowledge proof: G*x is infinity");
    }

    public ECJPAKEZeroKnowledgeProof generate(String str, ECPoint eCPoint, BigInteger bigInteger, ECPoint eCPoint2) {
        BigInteger createRandomInRange = BigIntegers.createRandomInRange(BigInteger.ONE, this.mGroup.getN().subtract(BigInteger.ONE), this.mRandom);
        ECPoint multiply = eCPoint.multiply(createRandomInRange);
        return new ECJPAKEZeroKnowledgeProof(multiply, createRandomInRange.subtract(bigInteger.multiply(getDigest(str, eCPoint, multiply, eCPoint2))).mod(this.mGroup.getN()));
    }
}
