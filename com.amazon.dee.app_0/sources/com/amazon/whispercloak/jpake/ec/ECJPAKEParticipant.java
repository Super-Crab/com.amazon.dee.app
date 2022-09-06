package com.amazon.whispercloak.jpake.ec;

import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.agreement.jpake.JPAKEUtil;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Strings;
/* loaded from: classes13.dex */
public class ECJPAKEParticipant {
    private ECPoint mB;
    private State mGenerateState;
    private final ECJPAKEPrimeOrderGroup mGroup;
    private ECPoint mGx1;
    private ECPoint mGx2;
    private ECPoint mGx3;
    private ECPoint mGx4;
    private final Digest mMessageDigest;
    private final String mParticipantId;
    private String mPartnerParticipantId;
    private char[] mPassword;
    private final SecureRandom mRandom;
    private State mValidateState;
    private BigInteger mX1;
    private BigInteger mX2;
    private BigInteger mX2s;
    private final ECJPAKEZeroKnowledgeProofFactory mZeroKnowledgeProofFactory;

    /* loaded from: classes13.dex */
    private enum State {
        INITIALIZED,
        ROUND_1_COMPLETE,
        ROUND_2_COMPLETE,
        KEY_CALCULATED,
        ROUND_3_COMPLETE
    }

    public ECJPAKEParticipant(String str, char[] cArr, ECJPAKEPrimeOrderGroup eCJPAKEPrimeOrderGroup) {
        this(str, cArr, eCJPAKEPrimeOrderGroup, new SHA256Digest(), new SecureRandom());
    }

    private BigInteger calculateDigest(BigInteger bigInteger) {
        byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(bigInteger);
        this.mMessageDigest.reset();
        this.mMessageDigest.update(asUnsignedByteArray, 0, asUnsignedByteArray.length);
        byte[] bArr = new byte[this.mMessageDigest.getDigestSize()];
        this.mMessageDigest.doFinal(bArr, 0);
        return BigIntegers.fromUnsignedByteArray(bArr);
    }

    private static void validateParticipantIdsDiffer(String str, String str2) throws CryptoException {
        if (!str.equals(str2)) {
            return;
        }
        throw new CryptoException(GeneratedOutlineSupport1.outline75("Both participants are using the same ID (", str, ")."));
    }

    private static void validateParticipantIdsMatch(String str, String str2) throws CryptoException {
        if (str.equals(str2)) {
            return;
        }
        throw new CryptoException(GeneratedOutlineSupport1.outline77("Received payload from participant with ID (", str2, "); expected (", str, ")."));
    }

    public BigInteger calculateKeyingMaterial() {
        State state = this.mGenerateState;
        State state2 = State.ROUND_2_COMPLETE;
        if (state == state2) {
            if (this.mValidateState == state2) {
                BigInteger calculateDigest = calculateDigest(this.mB.subtract(this.mGx4.multiply(this.mX2s)).multiply(this.mX2).normalize().getXCoord().toBigInteger());
                Arrays.fill(this.mPassword, (char) 0);
                this.mPassword = null;
                this.mX1 = null;
                this.mX2 = null;
                this.mX2s = null;
                this.mB = null;
                this.mGenerateState = State.KEY_CALCULATED;
                return calculateDigest;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Must follow round 2 payload validation for ");
            outline107.append(this.mParticipantId);
            throw new IllegalStateException(outline107.toString());
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Must follow round 2 payload creation for ");
        outline1072.append(this.mParticipantId);
        throw new IllegalStateException(outline1072.toString());
    }

    public ECJPAKERound1Payload createRound1PayloadToSend() {
        if (this.mGenerateState == State.INITIALIZED) {
            this.mX1 = BigIntegers.createRandomInRange(BigInteger.ONE, this.mGroup.getN().subtract(BigInteger.ONE), this.mRandom);
            this.mX2 = BigIntegers.createRandomInRange(BigInteger.ONE, this.mGroup.getN().subtract(BigInteger.ONE), this.mRandom);
            this.mGx1 = this.mGroup.getG().multiply(this.mX1);
            this.mGx2 = this.mGroup.getG().multiply(this.mX2);
            ECJPAKEZeroKnowledgeProof generate = this.mZeroKnowledgeProofFactory.generate(this.mParticipantId, this.mGroup.getG(), this.mX1, this.mGx1);
            ECJPAKEZeroKnowledgeProof generate2 = this.mZeroKnowledgeProofFactory.generate(this.mParticipantId, this.mGroup.getG(), this.mX2, this.mGx2);
            this.mGenerateState = State.ROUND_1_COMPLETE;
            return new ECJPAKERound1Payload(this.mParticipantId, this.mGx1, this.mGx2, generate, generate2);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Round 1 payload already created for ");
        outline107.append(this.mParticipantId);
        throw new IllegalStateException(outline107.toString());
    }

    public ECJPAKERound2Payload createRound2PayloadToSend() {
        State state = this.mGenerateState;
        State state2 = State.ROUND_1_COMPLETE;
        if (state == state2) {
            State state3 = this.mValidateState;
            if (state3 != state2 && state3 != State.ROUND_2_COMPLETE) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Must follow round 1 or round 2 payload validation for ");
                outline107.append(this.mParticipantId);
                throw new IllegalStateException(outline107.toString());
            }
            this.mX2s = this.mX2.multiply(new BigInteger(Strings.toUTF8ByteArray(this.mPassword))).mod(this.mGroup.getN());
            ECPoint add = this.mGx1.add(this.mGx3).add(this.mGx4);
            ECPoint multiply = add.multiply(this.mX2s);
            ECJPAKEZeroKnowledgeProof generate = this.mZeroKnowledgeProofFactory.generate(this.mParticipantId, add, this.mX2s, multiply);
            this.mGenerateState = State.ROUND_2_COMPLETE;
            return new ECJPAKERound2Payload(this.mParticipantId, multiply, generate);
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Must follow round 1 payload creation for ");
        outline1072.append(this.mParticipantId);
        throw new IllegalStateException(outline1072.toString());
    }

    public ECJPAKERound3Payload createRound3PayloadToSend(BigInteger bigInteger) {
        if (this.mGenerateState == State.KEY_CALCULATED) {
            JPAKEUtil.validateNotNull(bigInteger, "keyingMaterial");
            BigInteger calculateMacTag = JPAKEUtil.calculateMacTag(this.mParticipantId, this.mPartnerParticipantId, new BigInteger(1, this.mGx1.getEncoded(false)), new BigInteger(1, this.mGx2.getEncoded(false)), new BigInteger(1, this.mGx3.getEncoded(false)), new BigInteger(1, this.mGx4.getEncoded(false)), bigInteger, this.mMessageDigest);
            if (this.mValidateState == State.ROUND_3_COMPLETE) {
                this.mGx1 = null;
                this.mGx2 = null;
                this.mGx3 = null;
                this.mGx4 = null;
            }
            this.mGenerateState = State.ROUND_3_COMPLETE;
            return new ECJPAKERound3Payload(this.mParticipantId, calculateMacTag);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Must follow key material calculation for ");
        outline107.append(this.mParticipantId);
        throw new IllegalStateException(outline107.toString());
    }

    public ECJPAKEPrimeOrderGroup getGroup() {
        return this.mGroup;
    }

    public void validateRound1PayloadReceived(ECJPAKERound1Payload eCJPAKERound1Payload) throws CryptoException {
        if (this.mValidateState == State.INITIALIZED) {
            JPAKEUtil.validateNotNull(eCJPAKERound1Payload, "round1PayloadReceived");
            validateParticipantIdsDiffer(this.mParticipantId, eCJPAKERound1Payload.getParticipantId());
            this.mPartnerParticipantId = eCJPAKERound1Payload.getParticipantId();
            this.mGx3 = eCJPAKERound1Payload.getGx1();
            this.mGx4 = eCJPAKERound1Payload.getGx2();
            this.mZeroKnowledgeProofFactory.validate(this.mPartnerParticipantId, this.mGroup.getG(), eCJPAKERound1Payload.getGx1(), eCJPAKERound1Payload.getKnowledgeProofForX1());
            this.mZeroKnowledgeProofFactory.validate(this.mPartnerParticipantId, this.mGroup.getG(), eCJPAKERound1Payload.getGx2(), eCJPAKERound1Payload.getKnowledgeProofForX2());
            this.mValidateState = State.ROUND_1_COMPLETE;
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Validation already attempted for round 1 payload for ");
        outline107.append(this.mParticipantId);
        throw new IllegalStateException(outline107.toString());
    }

    public void validateRound2PayloadReceived(ECJPAKERound2Payload eCJPAKERound2Payload) throws CryptoException {
        State state = this.mGenerateState;
        if (state != State.ROUND_1_COMPLETE && state != State.ROUND_2_COMPLETE) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Must follow round 1 or round 2 payload creation for ");
            outline107.append(this.mParticipantId);
            throw new IllegalStateException(outline107.toString());
        } else if (this.mValidateState == State.ROUND_1_COMPLETE) {
            JPAKEUtil.validateNotNull(eCJPAKERound2Payload, "round2PayloadReceived");
            validateParticipantIdsDiffer(this.mParticipantId, eCJPAKERound2Payload.getParticipantId());
            validateParticipantIdsMatch(this.mPartnerParticipantId, eCJPAKERound2Payload.getParticipantId());
            this.mZeroKnowledgeProofFactory.validate(this.mPartnerParticipantId, this.mGx1.add(this.mGx2).add(this.mGx3), eCJPAKERound2Payload.getA(), eCJPAKERound2Payload.getKnowledgeProofForX2s());
            this.mB = eCJPAKERound2Payload.getA();
            this.mValidateState = State.ROUND_2_COMPLETE;
        } else {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Must follow round 1 payload validation for ");
            outline1072.append(this.mParticipantId);
            throw new IllegalStateException(outline1072.toString());
        }
    }

    public void validateRound3PayloadReceived(ECJPAKERound3Payload eCJPAKERound3Payload, BigInteger bigInteger) throws CryptoException {
        State state = this.mGenerateState;
        if (state != State.KEY_CALCULATED && state != State.ROUND_3_COMPLETE) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Must follow key material calculation for ");
            outline107.append(this.mParticipantId);
            throw new IllegalStateException(outline107.toString());
        } else if (this.mValidateState == State.ROUND_2_COMPLETE) {
            JPAKEUtil.validateNotNull(eCJPAKERound3Payload, "round3PayloadReceived");
            JPAKEUtil.validateNotNull(bigInteger, "keyingMaterial");
            validateParticipantIdsDiffer(this.mParticipantId, eCJPAKERound3Payload.getParticipantId());
            validateParticipantIdsMatch(this.mPartnerParticipantId, eCJPAKERound3Payload.getParticipantId());
            JPAKEUtil.validateMacTag(this.mParticipantId, this.mPartnerParticipantId, new BigInteger(1, this.mGx1.getEncoded(false)), new BigInteger(1, this.mGx2.getEncoded(false)), new BigInteger(1, this.mGx3.getEncoded(false)), new BigInteger(1, this.mGx4.getEncoded(false)), bigInteger, this.mMessageDigest, eCJPAKERound3Payload.getMacTag());
            if (this.mGenerateState == State.ROUND_3_COMPLETE) {
                this.mGx1 = null;
                this.mGx2 = null;
                this.mGx3 = null;
                this.mGx4 = null;
            }
            this.mValidateState = State.ROUND_3_COMPLETE;
        } else {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Must follow round 2 payload validation for ");
            outline1072.append(this.mParticipantId);
            throw new IllegalStateException(outline1072.toString());
        }
    }

    public ECJPAKEParticipant(String str, char[] cArr, ECJPAKEPrimeOrderGroup eCJPAKEPrimeOrderGroup, Digest digest, SecureRandom secureRandom) {
        JPAKEUtil.validateNotNull(str, "participantId");
        JPAKEUtil.validateNotNull(cArr, MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD);
        JPAKEUtil.validateNotNull(eCJPAKEPrimeOrderGroup, "group");
        JPAKEUtil.validateNotNull(digest, "messageDigest");
        JPAKEUtil.validateNotNull(secureRandom, "random");
        if (cArr.length != 0) {
            this.mParticipantId = str;
            this.mPassword = Arrays.copyOf(cArr, cArr.length);
            this.mGroup = eCJPAKEPrimeOrderGroup;
            this.mMessageDigest = digest;
            this.mRandom = secureRandom;
            this.mZeroKnowledgeProofFactory = new ECJPAKEZeroKnowledgeProofFactory(eCJPAKEPrimeOrderGroup, secureRandom, digest);
            State state = State.INITIALIZED;
            this.mGenerateState = state;
            this.mValidateState = state;
            return;
        }
        throw new IllegalArgumentException("Password must not be empty.");
    }
}
