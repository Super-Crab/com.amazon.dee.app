package org.bouncycastle.openpgp.operator;

import java.io.InputStream;
/* loaded from: classes5.dex */
public interface PGPDataDecryptor {
    int getBlockSize();

    InputStream getInputStream(InputStream inputStream);

    PGPDigestCalculator getIntegrityCalculator();
}
