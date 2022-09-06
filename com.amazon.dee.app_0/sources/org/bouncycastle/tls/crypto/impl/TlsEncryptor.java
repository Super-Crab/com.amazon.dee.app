package org.bouncycastle.tls.crypto.impl;

import java.io.IOException;
/* loaded from: classes5.dex */
public interface TlsEncryptor {
    byte[] encrypt(byte[] bArr, int i, int i2) throws IOException;
}
