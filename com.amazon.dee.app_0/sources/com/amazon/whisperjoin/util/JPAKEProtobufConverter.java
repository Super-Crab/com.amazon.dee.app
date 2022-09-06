package com.amazon.whisperjoin.util;

import com.amazon.whispercloak.protobuf.ProtobufBigIntegerClass;
import com.google.protobuf.ByteString;
import java.math.BigInteger;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.BigIntegers;
/* loaded from: classes13.dex */
public final class JPAKEProtobufConverter {
    public static final ProtobufBigIntegerClass.ProtobufBigInteger convertToBInteger(ECPoint eCPoint) {
        ProtobufBigIntegerClass.ProtobufBigInteger.Builder newBuilder = ProtobufBigIntegerClass.ProtobufBigInteger.newBuilder();
        newBuilder.setValue(ByteString.copyFrom(eCPoint.getEncoded(false)));
        return newBuilder.mo10084build();
    }

    public static final ECPoint convertToECPoint(ECCurve eCCurve, ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger) {
        return eCCurve.decodePoint(protobufBigInteger.getValue().toByteArray());
    }

    public static final ProtobufBigIntegerClass.ProtobufBigInteger convertToSignedBInteger(BigInteger bigInteger) {
        ProtobufBigIntegerClass.ProtobufBigInteger.Builder newBuilder = ProtobufBigIntegerClass.ProtobufBigInteger.newBuilder();
        newBuilder.setValue(ByteString.copyFrom(bigInteger.toByteArray()));
        return newBuilder.mo10084build();
    }

    public static final BigInteger convertToSignedBigInteger(ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger) {
        return new BigInteger(protobufBigInteger.getValue().toByteArray());
    }

    public static final ProtobufBigIntegerClass.ProtobufBigInteger convertToUnsignedBInteger(BigInteger bigInteger) {
        ProtobufBigIntegerClass.ProtobufBigInteger.Builder newBuilder = ProtobufBigIntegerClass.ProtobufBigInteger.newBuilder();
        newBuilder.setValue(ByteString.copyFrom(BigIntegers.asUnsignedByteArray(bigInteger)));
        return newBuilder.mo10084build();
    }

    public static final BigInteger convertToUnsignedBigInteger(ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger) {
        return BigIntegers.fromUnsignedByteArray(protobufBigInteger.getValue().toByteArray());
    }
}
