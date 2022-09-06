package com.amazon.alexa.accessory.crypto.cipher;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.google.protobuf.ByteString;
import java.security.SecureRandom;
import java.util.Random;
/* loaded from: classes.dex */
public final class Nonce {
    private static final int SIZE_IN_BYTES = 16;
    private static final Random random = new SecureRandom();
    private final ByteString nonceValue;

    public Nonce(byte[] bArr) {
        this(ByteString.copyFrom(bArr, 0, bArr.length));
    }

    public static Nonce random() {
        byte[] bArr = new byte[16];
        random.nextBytes(bArr);
        return new Nonce(bArr);
    }

    public void copyToByteArray(byte[] bArr, int i) throws IndexOutOfBoundsException {
        Preconditions.notNull(bArr, "writeTo");
        this.nonceValue.copyTo(bArr, i);
    }

    public ByteString getNonceValue() {
        return this.nonceValue;
    }

    public byte[] toByteArray() {
        return this.nonceValue.toByteArray();
    }

    public Nonce(byte[] bArr, int i) {
        this(ByteString.copyFrom(bArr, i, 16));
    }

    public Nonce(ByteString byteString) {
        Preconditions.notNull(byteString, "nonceValue");
        Preconditions.precondition(byteString.size() == 16, "nonceValue");
        this.nonceValue = byteString;
    }
}
