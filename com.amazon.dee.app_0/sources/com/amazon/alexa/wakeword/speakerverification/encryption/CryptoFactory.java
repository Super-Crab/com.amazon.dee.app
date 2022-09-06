package com.amazon.alexa.wakeword.speakerverification.encryption;

import androidx.annotation.NonNull;
import com.amazon.crypto.ByteDecryptor;
import com.amazon.crypto.ByteEncryptor;
import com.amazon.crypto.asymmetric.AsymmetricDecryptor;
import com.amazon.crypto.asymmetric.AsymmetricEncryptor;
import com.amazon.crypto.asymmetric.PublishedKey;
import com.amazon.crypto.asymmetric.WithheldKey;
import com.amazon.crypto.symmetric.SymmetricDecryptor;
import com.amazon.crypto.symmetric.SymmetricEncryptor;
import com.amazon.crypto.symmetric.SymmetricKey;
/* loaded from: classes11.dex */
public class CryptoFactory {
    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteDecryptor getAsymmetricDecryptor(@NonNull WithheldKey withheldKey) {
        return AsymmetricDecryptor.using(withheldKey);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteEncryptor getAsymmetricEncryptor(@NonNull PublishedKey publishedKey) {
        return AsymmetricEncryptor.using(publishedKey);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteDecryptor getSymmetricDecryptor(@NonNull SymmetricKey symmetricKey) {
        return SymmetricDecryptor.using(symmetricKey);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteEncryptor getSymmetricEncryptor(@NonNull SymmetricKey symmetricKey) {
        return SymmetricEncryptor.using(symmetricKey);
    }
}
