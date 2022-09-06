package org.bouncycastle.openpgp.operator;

import java.io.OutputStream;
/* loaded from: classes5.dex */
public interface PGPDataEncryptor {
    int getBlockSize();

    PGPDigestCalculator getIntegrityCalculator();

    OutputStream getOutputStream(OutputStream outputStream);
}
