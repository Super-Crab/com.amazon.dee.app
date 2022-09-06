package com.amazon.crypto.symmetric;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.crypto.ByteCipherSymmetric;
import com.amazon.crypto.ByteDecryptorSymmetric;
import com.amazon.crypto.ByteEncryptorSymmetric;
import com.amazon.crypto.utils.Preconditions;
/* loaded from: classes12.dex */
public final class SymmetricCipher implements ByteCipherSymmetric {
    private final ByteDecryptorSymmetric decryptor;
    private final ByteEncryptorSymmetric encryptor;

    SymmetricCipher(ByteEncryptorSymmetric byteEncryptorSymmetric, ByteDecryptorSymmetric byteDecryptorSymmetric) {
        this.encryptor = byteEncryptorSymmetric;
        this.decryptor = byteDecryptorSymmetric;
    }

    @NonNull
    public static SymmetricCipher using(@NonNull SymmetricKey symmetricKey) {
        Preconditions.notNull(symmetricKey);
        return new SymmetricCipher(SymmetricEncryptor.using(symmetricKey), SymmetricDecryptor.using(symmetricKey));
    }

    @Override // com.amazon.crypto.ByteDecryptor
    @Nullable
    public byte[] decrypt(@Nullable byte[] bArr) throws IllegalArgumentException, SecurityException {
        return this.decryptor.decrypt(bArr);
    }

    @Override // com.amazon.crypto.ByteEncryptor
    @Nullable
    public byte[] encrypt(@Nullable byte[] bArr) throws IllegalArgumentException, SecurityException {
        return this.encryptor.encrypt(bArr);
    }

    @Override // com.amazon.crypto.ByteDecryptorSymmetric
    public byte[] decrypt(byte[] bArr, byte[] bArr2) throws IllegalArgumentException, SecurityException {
        return this.decryptor.decrypt(bArr, bArr2);
    }

    @Override // com.amazon.crypto.ByteEncryptorSymmetric
    public byte[] encrypt(byte[] bArr, byte[] bArr2) throws IllegalArgumentException, SecurityException {
        return this.encryptor.encrypt(bArr, bArr2);
    }
}
