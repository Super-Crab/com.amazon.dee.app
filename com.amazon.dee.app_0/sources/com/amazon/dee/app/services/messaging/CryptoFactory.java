package com.amazon.dee.app.services.messaging;

import com.amazon.crypto.ByteDecryptor;
import com.amazon.crypto.asymmetric.WithheldKey;
import com.amazon.crypto.symmetric.SymmetricKey;
/* loaded from: classes12.dex */
public interface CryptoFactory {
    ByteDecryptor createAsymmetricDecryptor(WithheldKey withheldKey);

    ByteDecryptor createSymmetricDecryptor(SymmetricKey symmetricKey);
}
