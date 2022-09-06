package com.amazon.whisperjoin.common.sharedtypes.cryptography;

import com.amazon.whispercloak.SecureChannel;
import com.amazon.whispercloak.SecureMessage;
import com.amazon.whispercloak.cipher.AesGcmCipher;
import com.amazon.whisperjoin.common.sharedtypes.utility.Serializer;
import com.google.common.reflect.TypeToken;
import java.util.Arrays;
/* loaded from: classes13.dex */
public class AesGcmEncryptionProvider implements EncryptionProvider {
    private final AesGcmCipher mAesGcmCipher;
    private final byte[] mKey;
    private final Serializer mSerializer;

    public AesGcmEncryptionProvider(SecureChannel secureChannel, Serializer serializer) {
        this(secureChannel.getCipher(), secureChannel.getSymmetricKey(), serializer);
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.cryptography.EncryptionProvider
    public byte[] decrypt(byte[] bArr) {
        return this.mAesGcmCipher.decrypt(this.mKey, (SecureMessage) this.mSerializer.deserialize(bArr, TypeToken.of(SecureMessage.class)));
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.cryptography.EncryptionProvider
    public byte[] encrypt(byte[] bArr) {
        return this.mSerializer.serialize(this.mAesGcmCipher.encrypt(this.mKey, bArr));
    }

    public AesGcmEncryptionProvider(AesGcmCipher aesGcmCipher, byte[] bArr, Serializer serializer) {
        this.mAesGcmCipher = aesGcmCipher;
        this.mSerializer = serializer;
        this.mKey = Arrays.copyOf(bArr, bArr.length);
    }
}
