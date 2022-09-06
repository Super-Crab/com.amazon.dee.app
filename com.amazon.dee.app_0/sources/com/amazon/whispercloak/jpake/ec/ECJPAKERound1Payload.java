package com.amazon.whispercloak.jpake.ec;

import org.bouncycastle.crypto.agreement.jpake.JPAKEUtil;
import org.bouncycastle.math.ec.ECPoint;
/* loaded from: classes13.dex */
public class ECJPAKERound1Payload {
    private final ECPoint mGx1;
    private final ECPoint mGx2;
    private final ECJPAKEZeroKnowledgeProof mKnowledgeProofForX1;
    private final ECJPAKEZeroKnowledgeProof mKnowledgeProofForX2;
    private final String mParticipantId;

    public ECJPAKERound1Payload(String str, ECPoint eCPoint, ECPoint eCPoint2, ECJPAKEZeroKnowledgeProof eCJPAKEZeroKnowledgeProof, ECJPAKEZeroKnowledgeProof eCJPAKEZeroKnowledgeProof2) {
        JPAKEUtil.validateNotNull(str, "participantId");
        JPAKEUtil.validateNotNull(eCPoint, "Gx1");
        JPAKEUtil.validateNotNull(eCPoint2, "Gx2");
        JPAKEUtil.validateNotNull(eCJPAKEZeroKnowledgeProof, "knowledgeProofForX1");
        JPAKEUtil.validateNotNull(eCJPAKEZeroKnowledgeProof2, "knowledgeProofForX2");
        this.mParticipantId = str;
        this.mGx1 = eCPoint;
        this.mGx2 = eCPoint2;
        this.mKnowledgeProofForX1 = eCJPAKEZeroKnowledgeProof;
        this.mKnowledgeProofForX2 = eCJPAKEZeroKnowledgeProof2;
    }

    public ECPoint getGx1() {
        return this.mGx1;
    }

    public ECPoint getGx2() {
        return this.mGx2;
    }

    public ECJPAKEZeroKnowledgeProof getKnowledgeProofForX1() {
        return this.mKnowledgeProofForX1;
    }

    public ECJPAKEZeroKnowledgeProof getKnowledgeProofForX2() {
        return this.mKnowledgeProofForX2;
    }

    public String getParticipantId() {
        return this.mParticipantId;
    }
}
