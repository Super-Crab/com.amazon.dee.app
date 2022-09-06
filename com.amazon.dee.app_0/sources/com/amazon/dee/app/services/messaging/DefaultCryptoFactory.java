package com.amazon.dee.app.services.messaging;

import com.amazon.crypto.ByteDecryptor;
import com.amazon.crypto.asymmetric.AsymmetricDecryptor;
import com.amazon.crypto.asymmetric.WithheldKey;
import com.amazon.crypto.symmetric.SymmetricDecryptor;
import com.amazon.crypto.symmetric.SymmetricKey;
/* loaded from: classes12.dex */
public class DefaultCryptoFactory implements CryptoFactory {
    @Override // com.amazon.dee.app.services.messaging.CryptoFactory
    public ByteDecryptor createAsymmetricDecryptor(WithheldKey withheldKey) {
        return AsymmetricDecryptor.using(withheldKey);
    }

    @Override // com.amazon.dee.app.services.messaging.CryptoFactory
    public ByteDecryptor createSymmetricDecryptor(SymmetricKey symmetricKey) {
        return SymmetricDecryptor.using(symmetricKey);
    }
}
