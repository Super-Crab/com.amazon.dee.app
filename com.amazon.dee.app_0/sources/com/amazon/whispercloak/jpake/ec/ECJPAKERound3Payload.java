package com.amazon.whispercloak.jpake.ec;

import java.math.BigInteger;
import org.bouncycastle.crypto.agreement.jpake.JPAKEUtil;
/* loaded from: classes13.dex */
public class ECJPAKERound3Payload {
    private final BigInteger mMacTag;
    private final String mParticipantId;

    public ECJPAKERound3Payload(String str, BigInteger bigInteger) {
        JPAKEUtil.validateNotNull(str, "participantId");
        JPAKEUtil.validateNotNull(bigInteger, "macTag");
        this.mParticipantId = str;
        this.mMacTag = bigInteger;
    }

    public BigInteger getMacTag() {
        return this.mMacTag;
    }

    public String getParticipantId() {
        return this.mParticipantId;
    }
}
