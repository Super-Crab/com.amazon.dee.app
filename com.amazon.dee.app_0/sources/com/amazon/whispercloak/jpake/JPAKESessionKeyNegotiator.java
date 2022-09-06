package com.amazon.whispercloak.jpake;

import com.amazon.whispercloak.jpake.ec.ECJPAKEParticipant;
import com.amazon.whispercloak.jpake.ec.ECJPAKERound1Payload;
import com.amazon.whispercloak.jpake.ec.ECJPAKERound2Payload;
import com.amazon.whispercloak.jpake.ec.ECJPAKERound3Payload;
import java.math.BigInteger;
/* loaded from: classes13.dex */
public interface JPAKESessionKeyNegotiator {
    BigInteger computeCommonKeyMaterial();

    ECJPAKERound1Payload createRound1PayloadToSend();

    ECJPAKERound2Payload createRound2PayloadToSend();

    ECJPAKERound3Payload createRound3PayloadToSend(BigInteger bigInteger);

    byte[] deriveSessionKey(BigInteger bigInteger);

    ECJPAKEParticipant getParticipant();

    void validateRound1PayloadReceived(ECJPAKERound1Payload eCJPAKERound1Payload);

    void validateRound2PayloadReceived(ECJPAKERound2Payload eCJPAKERound2Payload);

    void validateRound3PayloadReceived(ECJPAKERound3Payload eCJPAKERound3Payload, BigInteger bigInteger);
}
