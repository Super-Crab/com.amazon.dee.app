package org.bouncycastle.openpgp.operator;

import org.bouncycastle.openpgp.PGPException;
/* loaded from: classes5.dex */
public interface PGPDataDecryptorFactory {
    PGPDataDecryptor createDataDecryptor(boolean z, int i, byte[] bArr) throws PGPException;
}
