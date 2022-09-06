package com.amazon.whispercloak;

import com.amazon.whispercloak.cipher.AesGcmCipher;
import com.amazon.whispercloak.error.SecureChannelInitializationError;
/* loaded from: classes13.dex */
public interface SecureChannel {
    Payload decrypt(byte[] bArr);

    byte[] encrypt(Payload payload);

    AesGcmCipher getCipher();

    byte[] getDerEncodedPublicKey();

    String getPemEncodedPublicKey();

    byte[] getSymmetricKey();

    void init(String str) throws SecureChannelInitializationError;

    void init(byte[] bArr) throws SecureChannelInitializationError;
}
