package com.amazon.whispercloak.keygen.provider;

import com.amazon.whispercloak.error.CryptoDependencyException;
import javax.crypto.KeyAgreement;
/* loaded from: classes13.dex */
public interface KeyAgreementProvider {
    KeyAgreement getKeyAgreement() throws CryptoDependencyException;
}
