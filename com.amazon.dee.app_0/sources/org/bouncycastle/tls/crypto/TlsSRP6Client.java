package org.bouncycastle.tls.crypto;

import java.io.IOException;
import java.math.BigInteger;
/* loaded from: classes5.dex */
public interface TlsSRP6Client {
    BigInteger calculateSecret(BigInteger bigInteger) throws IOException;

    BigInteger generateClientCredentials(byte[] bArr, byte[] bArr2, byte[] bArr3);
}
