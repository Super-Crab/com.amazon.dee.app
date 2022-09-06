package com.amazon.whispercloak.keygen.provider;

import com.amazon.whispercloak.error.CryptoDependencyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyAgreement;
/* loaded from: classes13.dex */
public class EcdhKeyAgreementProvider implements KeyAgreementProvider {
    @Override // com.amazon.whispercloak.keygen.provider.KeyAgreementProvider
    public KeyAgreement getKeyAgreement() throws CryptoDependencyException {
        try {
            return KeyAgreement.getInstance("ECDH");
        } catch (NoSuchAlgorithmException e) {
            throw new CryptoDependencyException(e);
        }
    }
}
