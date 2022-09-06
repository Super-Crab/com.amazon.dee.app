package com.amazon.crypto.symmetric;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.crypto.ByteCipherSymmetric;
import com.amazon.crypto.ByteDecryptorSymmetric;
/* loaded from: classes12.dex */
public final class SymmetricDecryptor implements ByteDecryptorSymmetric {
    private final ByteCipherSymmetric backend;

    SymmetricDecryptor(ByteCipherSymmetric byteCipherSymmetric) {
        this.backend = byteCipherSymmetric;
    }

    @NonNull
    public static SymmetricDecryptor using(@NonNull SymmetricKey symmetricKey) {
        return new SymmetricDecryptor(AndroidSymmetricCipher.using(symmetricKey));
    }

    @Override // com.amazon.crypto.ByteDecryptorSymmetric, com.amazon.crypto.ByteDecryptor
    @Nullable
    public byte[] decrypt(@Nullable byte[] bArr) throws IllegalArgumentException, SecurityException {
        return this.backend.decrypt(bArr);
    }

    @Override // com.amazon.crypto.ByteDecryptorSymmetric
    @Nullable
    public byte[] decrypt(@Nullable byte[] bArr, byte[] bArr2) throws IllegalArgumentException, SecurityException {
        return this.backend.decrypt(bArr, bArr2);
    }
}
