package com.amazon.whispercloak.jpake;

import com.amazon.whispercloak.error.JPAKECryptoException;
import com.amazon.whispercloak.jpake.ec.ECJPAKEParticipant;
import com.amazon.whispercloak.jpake.ec.ECJPAKEPrimeOrderGroup;
import com.amazon.whispercloak.jpake.ec.ECJPAKERound1Payload;
import com.amazon.whispercloak.jpake.ec.ECJPAKERound2Payload;
import com.amazon.whispercloak.jpake.ec.ECJPAKERound3Payload;
import java.math.BigInteger;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.agreement.jpake.JPAKEUtil;
import org.bouncycastle.util.BigIntegers;
/* loaded from: classes13.dex */
public class JPAKESessionKeyNegotiatorImpl implements JPAKESessionKeyNegotiator {
    private static final String PRIME_256_V1_EC_GROUP_NAME = "prime256v1";
    private static final String SHA_256_MESSAGE_DIGEST_INSTANCE = "SHA-256";
    private ECJPAKEParticipant mParticipant;

    public JPAKESessionKeyNegotiatorImpl(String str, String str2) {
        this.mParticipant = new ECJPAKEParticipant(str, str2.toCharArray(), new ECJPAKEPrimeOrderGroup(PRIME_256_V1_EC_GROUP_NAME));
    }

    @Override // com.amazon.whispercloak.jpake.JPAKESessionKeyNegotiator
    public BigInteger computeCommonKeyMaterial() {
        return this.mParticipant.calculateKeyingMaterial();
    }

    @Override // com.amazon.whispercloak.jpake.JPAKESessionKeyNegotiator
    public ECJPAKERound1Payload createRound1PayloadToSend() {
        return this.mParticipant.createRound1PayloadToSend();
    }

    @Override // com.amazon.whispercloak.jpake.JPAKESessionKeyNegotiator
    public ECJPAKERound2Payload createRound2PayloadToSend() {
        return this.mParticipant.createRound2PayloadToSend();
    }

    @Override // com.amazon.whispercloak.jpake.JPAKESessionKeyNegotiator
    public ECJPAKERound3Payload createRound3PayloadToSend(BigInteger bigInteger) {
        return this.mParticipant.createRound3PayloadToSend(bigInteger);
    }

    @Override // com.amazon.whispercloak.jpake.JPAKESessionKeyNegotiator
    public byte[] deriveSessionKey(BigInteger bigInteger) {
        JPAKEUtil.validateNotNull(bigInteger, "Keying Material");
        return BigIntegers.asUnsignedByteArray(bigInteger);
    }

    @Override // com.amazon.whispercloak.jpake.JPAKESessionKeyNegotiator
    public ECJPAKEParticipant getParticipant() {
        return this.mParticipant;
    }

    @Override // com.amazon.whispercloak.jpake.JPAKESessionKeyNegotiator
    public void validateRound1PayloadReceived(ECJPAKERound1Payload eCJPAKERound1Payload) {
        try {
            this.mParticipant.validateRound1PayloadReceived(eCJPAKERound1Payload);
        } catch (CryptoException e) {
            throw new JPAKECryptoException(e);
        }
    }

    @Override // com.amazon.whispercloak.jpake.JPAKESessionKeyNegotiator
    public void validateRound2PayloadReceived(ECJPAKERound2Payload eCJPAKERound2Payload) {
        try {
            this.mParticipant.validateRound2PayloadReceived(eCJPAKERound2Payload);
        } catch (CryptoException e) {
            throw new JPAKECryptoException(e);
        }
    }

    @Override // com.amazon.whispercloak.jpake.JPAKESessionKeyNegotiator
    public void validateRound3PayloadReceived(ECJPAKERound3Payload eCJPAKERound3Payload, BigInteger bigInteger) {
        try {
            this.mParticipant.validateRound3PayloadReceived(eCJPAKERound3Payload, bigInteger);
        } catch (CryptoException e) {
            throw new JPAKECryptoException(e);
        }
    }
}
