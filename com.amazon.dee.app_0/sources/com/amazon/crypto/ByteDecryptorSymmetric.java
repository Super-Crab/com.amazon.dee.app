package com.amazon.crypto;
/* loaded from: classes12.dex */
public interface ByteDecryptorSymmetric extends ByteDecryptor {
    @Override // com.amazon.crypto.ByteDecryptor
    byte[] decrypt(byte[] bArr) throws IllegalArgumentException, SecurityException;

    byte[] decrypt(byte[] bArr, byte[] bArr2) throws IllegalArgumentException, SecurityException;
}
