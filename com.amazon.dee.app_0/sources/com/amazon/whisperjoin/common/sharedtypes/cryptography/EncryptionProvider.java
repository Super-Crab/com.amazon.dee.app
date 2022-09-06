package com.amazon.whisperjoin.common.sharedtypes.cryptography;
/* loaded from: classes13.dex */
public interface EncryptionProvider {
    byte[] decrypt(byte[] bArr) throws DecryptionFailedException;

    byte[] encrypt(byte[] bArr) throws EncryptionFailedException;
}
