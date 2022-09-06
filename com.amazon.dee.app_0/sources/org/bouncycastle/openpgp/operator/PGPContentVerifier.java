package org.bouncycastle.openpgp.operator;

import java.io.OutputStream;
/* loaded from: classes5.dex */
public interface PGPContentVerifier {
    int getHashAlgorithm();

    int getKeyAlgorithm();

    long getKeyID();

    OutputStream getOutputStream();

    boolean verify(byte[] bArr);
}
