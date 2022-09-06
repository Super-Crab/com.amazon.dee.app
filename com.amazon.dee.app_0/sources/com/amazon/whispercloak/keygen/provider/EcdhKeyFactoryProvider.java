package com.amazon.whispercloak.keygen.provider;

import com.amazon.whispercloak.KeyUtils;
import com.amazon.whispercloak.error.CryptoDependencyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
/* loaded from: classes13.dex */
public class EcdhKeyFactoryProvider implements KeyFactoryProvider {
    @Override // com.amazon.whispercloak.keygen.provider.KeyFactoryProvider
    public KeyFactory getKeyFactory() {
        try {
            return KeyFactory.getInstance(KeyUtils.ALGORITHM_EC);
        } catch (NoSuchAlgorithmException e) {
            throw new CryptoDependencyException(e);
        }
    }
}
