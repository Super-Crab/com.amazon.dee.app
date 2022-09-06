package org.bouncycastle.tls.crypto;

import java.io.IOException;
/* loaded from: classes5.dex */
public interface TlsSecret {
    TlsSecret deriveUsingPRF(int i, String str, byte[] bArr, int i2);

    void destroy();

    byte[] encrypt(TlsCertificate tlsCertificate) throws IOException;

    byte[] extract();

    TlsSecret hkdfExpand(short s, byte[] bArr, int i);

    TlsSecret hkdfExtract(short s, byte[] bArr);

    boolean isAlive();
}
