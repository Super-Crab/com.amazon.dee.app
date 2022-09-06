package com.amazon.whispercloak.jpake.ec;

import java.math.BigInteger;
import org.bouncycastle.crypto.agreement.jpake.JPAKEUtil;
import org.bouncycastle.math.ec.ECPoint;
/* loaded from: classes13.dex */
public class ECJPAKEZeroKnowledgeProof {
    private final ECPoint mGv;
    private final BigInteger mR;

    public ECJPAKEZeroKnowledgeProof(ECPoint eCPoint, BigInteger bigInteger) {
        JPAKEUtil.validateNotNull(eCPoint, "Gv");
        JPAKEUtil.validateNotNull(bigInteger, "r");
        this.mGv = eCPoint;
        this.mR = bigInteger;
    }

    public ECPoint getGv() {
        return this.mGv;
    }

    public BigInteger getR() {
        return this.mR;
    }
}
