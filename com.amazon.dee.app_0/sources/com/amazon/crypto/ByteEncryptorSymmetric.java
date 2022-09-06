package com.amazon.crypto;
/* loaded from: classes12.dex */
public interface ByteEncryptorSymmetric extends ByteEncryptor {
    @Override // com.amazon.crypto.ByteEncryptor
    byte[] encrypt(byte[] bArr) throws IllegalArgumentException, SecurityException;

    byte[] encrypt(byte[] bArr, byte[] bArr2) throws SecurityException;
}
