package org.bouncycastle.openpgp.operator;

import java.io.OutputStream;
/* loaded from: classes5.dex */
public interface PGPContentSigner {
    byte[] getDigest();

    int getHashAlgorithm();

    int getKeyAlgorithm();

    long getKeyID();

    OutputStream getOutputStream();

    byte[] getSignature();

    int getType();
}
