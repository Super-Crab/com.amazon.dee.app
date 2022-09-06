package org.bouncycastle.openpgp.operator;

import java.io.OutputStream;
/* loaded from: classes5.dex */
public interface PGPDigestCalculator {
    int getAlgorithm();

    byte[] getDigest();

    OutputStream getOutputStream();

    void reset();
}
