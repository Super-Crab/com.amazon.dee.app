package com.amazon.alexa.accessory.crypto.cipher;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.google.protobuf.ByteString;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.GCMParameterSpec;
/* loaded from: classes.dex */
public interface AlgorithmParameterSpecBuilder {
    public static final int AES_GCM_TAG_LENGTH_BITS = 128;
    public static final AlgorithmParameterSpecBuilder GCM_PARAMETER_BUILDER = $$Lambda$AlgorithmParameterSpecBuilder$NxTbJzo0bQkQDJcv9leNd8zGc4.INSTANCE;
    public static final AlgorithmParameterSpecBuilder IV_PARAMETER_BUILDER = $$Lambda$j883aPM4LC7YGKdYYcHE4x68ZzQ.INSTANCE;

    static /* synthetic */ AlgorithmParameterSpec lambda$static$0(byte[] bArr, int i, int i2) {
        return new GCMParameterSpec(128, bArr, i, i2);
    }

    default AlgorithmParameterSpec build(ByteString byteString) {
        Preconditions.notNull(byteString, "iv");
        return build(byteString.toByteArray(), 0, byteString.size());
    }

    AlgorithmParameterSpec build(byte[] bArr, int i, int i2);

    default AlgorithmParameterSpec build(byte[] bArr) {
        Preconditions.notNull(bArr, "iv");
        return build(bArr, 0, bArr.length);
    }
}
