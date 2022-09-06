package com.amazon.alexa.accessory.crypto.cipher;

import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.IvParameterSpec;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.crypto.cipher.-$$Lambda$j883aPM4LC7YGKdYYcHE4x68ZzQ  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$j883aPM4LC7YGKdYYcHE4x68ZzQ implements AlgorithmParameterSpecBuilder {
    public static final /* synthetic */ $$Lambda$j883aPM4LC7YGKdYYcHE4x68ZzQ INSTANCE = new $$Lambda$j883aPM4LC7YGKdYYcHE4x68ZzQ();

    private /* synthetic */ $$Lambda$j883aPM4LC7YGKdYYcHE4x68ZzQ() {
    }

    @Override // com.amazon.alexa.accessory.crypto.cipher.AlgorithmParameterSpecBuilder
    public final AlgorithmParameterSpec build(byte[] bArr, int i, int i2) {
        return new IvParameterSpec(bArr, i, i2);
    }
}
