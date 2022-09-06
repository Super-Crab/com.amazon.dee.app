package com.amazon.whisperjoin.common.sharedtypes.protobuf.provisioning.data.crypto;

import com.amazon.whispercloak.jpake.ec.ECJPAKEPrimeOrderGroup;
import com.amazon.whispercloak.jpake.ec.ECJPAKERound1Payload;
import com.amazon.whispercloak.jpake.ec.ECJPAKERound2Payload;
import com.amazon.whispercloak.jpake.ec.ECJPAKERound3Payload;
import com.amazon.whispercloak.jpake.ec.ECJPAKEZeroKnowledgeProof;
import com.amazon.whispercloak.protobuf.ProtobufBigIntegerClass;
import com.amazon.whispercloak.protobuf.ProtobufCalculateKnowledgeProofClass;
import com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass;
import com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass;
import com.amazon.whispercloak.protobuf.ProtobufJPAKERound3PayloadClass;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.DataSerializationError;
import com.amazon.whisperjoin.common.sharedtypes.utility.Serializer;
import com.amazon.whisperjoin.util.JPAKEProtobufConverter;
import com.google.common.reflect.TypeToken;
import com.google.protobuf.InvalidProtocolBufferException;
import java.math.BigInteger;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
/* loaded from: classes13.dex */
public final class ProtoJPAKEPayloadSerializer implements Serializer {
    private final ECCurve mEcCurve;

    public ProtoJPAKEPayloadSerializer(ECJPAKEPrimeOrderGroup eCJPAKEPrimeOrderGroup) {
        this.mEcCurve = eCJPAKEPrimeOrderGroup.getCurve();
    }

    private ECPoint deserializeEcPoint(ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger) {
        return JPAKEProtobufConverter.convertToECPoint(this.mEcCurve, protobufBigInteger);
    }

    private ECJPAKERound1Payload deserializeRound1Payload(byte[] bArr) {
        try {
            ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1Payload parseFrom = ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1Payload.parseFrom(bArr);
            return new ECJPAKERound1Payload(parseFrom.getParticipantId(), deserializeEcPoint(parseFrom.getGx1()), deserializeEcPoint(parseFrom.getGx2()), new ECJPAKEZeroKnowledgeProof(deserializeEcPoint(parseFrom.getKnowledgeProofForX1().getGv()), deserializeUnsignedBigInteger(parseFrom.getKnowledgeProofForX1().getR())), new ECJPAKEZeroKnowledgeProof(deserializeEcPoint(parseFrom.getKnowledgeProofForX2().getGv()), deserializeUnsignedBigInteger(parseFrom.getKnowledgeProofForX2().getR())));
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError("Unable to deserialize JPAKE round 1 payload", e);
        }
    }

    private ECJPAKERound2Payload deserializeRound2Payload(byte[] bArr) {
        try {
            ProtobufJPAKERound2PayloadClass.ProtobufJPAKERound2Payload parseFrom = ProtobufJPAKERound2PayloadClass.ProtobufJPAKERound2Payload.parseFrom(bArr);
            return new ECJPAKERound2Payload(parseFrom.getParticipantId(), deserializeEcPoint(parseFrom.getA()), new ECJPAKEZeroKnowledgeProof(deserializeEcPoint(parseFrom.getKnowledgeProofForX2S().getGv()), deserializeUnsignedBigInteger(parseFrom.getKnowledgeProofForX2S().getR())));
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError("Unable to deserialize JPAKE round 2 payload", e);
        }
    }

    private ECJPAKERound3Payload deserializeRound3Payload(byte[] bArr) {
        try {
            ProtobufJPAKERound3PayloadClass.ProtobufJPAKERound3Payload parseFrom = ProtobufJPAKERound3PayloadClass.ProtobufJPAKERound3Payload.parseFrom(bArr);
            return new ECJPAKERound3Payload(parseFrom.getParticipantId(), deserializeSignedBigInteger(parseFrom.getMacTag()));
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError("Unable to deserialize JPAKE round 3 payload", e);
        }
    }

    private BigInteger deserializeSignedBigInteger(ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger) {
        return JPAKEProtobufConverter.convertToSignedBigInteger(protobufBigInteger);
    }

    private BigInteger deserializeUnsignedBigInteger(ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger) {
        return JPAKEProtobufConverter.convertToUnsignedBigInteger(protobufBigInteger);
    }

    private ProtobufBigIntegerClass.ProtobufBigInteger serializeEcPoint(ECPoint eCPoint) {
        return JPAKEProtobufConverter.convertToBInteger(eCPoint);
    }

    private byte[] serializeRound1Payload(ECJPAKERound1Payload eCJPAKERound1Payload) {
        ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof mo10084build = ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.newBuilder().setGv(serializeEcPoint(eCJPAKERound1Payload.getKnowledgeProofForX1().getGv())).setR(serializeUnsignedBigInteger(eCJPAKERound1Payload.getKnowledgeProofForX1().getR())).mo10084build();
        return ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1Payload.newBuilder().setParticipantId(eCJPAKERound1Payload.getParticipantId()).setGx1(serializeEcPoint(eCJPAKERound1Payload.getGx1())).setGx2(serializeEcPoint(eCJPAKERound1Payload.getGx2())).setKnowledgeProofForX1(mo10084build).setKnowledgeProofForX2(ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.newBuilder().setGv(serializeEcPoint(eCJPAKERound1Payload.getKnowledgeProofForX2().getGv())).setR(serializeUnsignedBigInteger(eCJPAKERound1Payload.getKnowledgeProofForX2().getR())).mo10084build()).mo10084build().toByteArray();
    }

    private byte[] serializeRound2Payload(ECJPAKERound2Payload eCJPAKERound2Payload) {
        return ProtobufJPAKERound2PayloadClass.ProtobufJPAKERound2Payload.newBuilder().setParticipantId(eCJPAKERound2Payload.getParticipantId()).setA(serializeEcPoint(eCJPAKERound2Payload.getA())).setKnowledgeProofForX2S(ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.newBuilder().setGv(serializeEcPoint(eCJPAKERound2Payload.getKnowledgeProofForX2s().getGv())).setR(serializeUnsignedBigInteger(eCJPAKERound2Payload.getKnowledgeProofForX2s().getR())).mo10084build()).mo10084build().toByteArray();
    }

    private byte[] serializeRound3Payload(ECJPAKERound3Payload eCJPAKERound3Payload) {
        return ProtobufJPAKERound3PayloadClass.ProtobufJPAKERound3Payload.newBuilder().setParticipantId(eCJPAKERound3Payload.getParticipantId()).setMacTag(serializeSignedBigInteger(eCJPAKERound3Payload.getMacTag())).mo10084build().toByteArray();
    }

    private ProtobufBigIntegerClass.ProtobufBigInteger serializeSignedBigInteger(BigInteger bigInteger) {
        return JPAKEProtobufConverter.convertToSignedBInteger(bigInteger);
    }

    private ProtobufBigIntegerClass.ProtobufBigInteger serializeUnsignedBigInteger(BigInteger bigInteger) {
        return JPAKEProtobufConverter.convertToUnsignedBInteger(bigInteger);
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.utility.Serializer
    public <T> T deserialize(byte[] bArr, TypeToken<T> typeToken) {
        if (bArr == null) {
            return null;
        }
        if (typeToken.equals(TypeToken.of(ECJPAKERound1Payload.class))) {
            return (T) deserializeRound1Payload(bArr);
        }
        if (typeToken.equals(TypeToken.of(ECJPAKERound2Payload.class))) {
            return (T) deserializeRound2Payload(bArr);
        }
        if (typeToken.equals(TypeToken.of(ECJPAKERound3Payload.class))) {
            return (T) deserializeRound3Payload(bArr);
        }
        throw new RuntimeException("Could not find a serializer to deserialize object.");
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.utility.Serializer
    public <T> byte[] serialize(T t) {
        if (t == null) {
            return null;
        }
        if (t instanceof ECJPAKERound1Payload) {
            return serializeRound1Payload((ECJPAKERound1Payload) t);
        }
        if (t instanceof ECJPAKERound2Payload) {
            return serializeRound2Payload((ECJPAKERound2Payload) t);
        }
        if (t instanceof ECJPAKERound3Payload) {
            return serializeRound3Payload((ECJPAKERound3Payload) t);
        }
        throw new RuntimeException("Could not find a serializer to serialize object.");
    }
}
