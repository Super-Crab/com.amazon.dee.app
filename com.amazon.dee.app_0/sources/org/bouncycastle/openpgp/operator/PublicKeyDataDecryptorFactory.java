package org.bouncycastle.openpgp.operator;

import org.bouncycastle.openpgp.PGPException;
/* loaded from: classes5.dex */
public interface PublicKeyDataDecryptorFactory extends PGPDataDecryptorFactory {
    byte[] recoverSessionData(int i, byte[][] bArr) throws PGPException;
}
