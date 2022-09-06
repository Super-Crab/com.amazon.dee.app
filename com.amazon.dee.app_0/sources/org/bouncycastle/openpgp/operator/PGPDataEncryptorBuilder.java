package org.bouncycastle.openpgp.operator;

import java.security.SecureRandom;
import org.bouncycastle.openpgp.PGPException;
/* loaded from: classes5.dex */
public interface PGPDataEncryptorBuilder {
    PGPDataEncryptor build(byte[] bArr) throws PGPException;

    int getAlgorithm();

    SecureRandom getSecureRandom();
}
