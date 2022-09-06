package org.bouncycastle.tls.crypto;

import java.io.IOException;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.TlsUtils;
/* loaded from: classes5.dex */
public abstract class TlsCryptoUtils {
    private static final byte[] TLS13_PREFIX = {116, 108, 115, 49, 51, 32};

    public static TlsSecret hkdfExpandLabel(TlsSecret tlsSecret, short s, String str, byte[] bArr, int i) throws IOException {
        int length = str.length();
        if (length >= 1) {
            int length2 = bArr.length;
            int length3 = TLS13_PREFIX.length + length;
            int i2 = length3 + 1 + 2;
            byte[] bArr2 = new byte[length2 + 1 + i2];
            TlsUtils.checkUint16(i);
            TlsUtils.writeUint16(i, bArr2, 0);
            TlsUtils.checkUint8(length3);
            TlsUtils.writeUint8(length3, bArr2, 2);
            byte[] bArr3 = TLS13_PREFIX;
            System.arraycopy(bArr3, 0, bArr2, 3, bArr3.length);
            int length4 = TLS13_PREFIX.length + 1 + 2;
            for (int i3 = 0; i3 < length; i3++) {
                bArr2[length4 + i3] = (byte) str.charAt(i3);
            }
            TlsUtils.writeOpaque8(bArr, bArr2, i2);
            return tlsSecret.hkdfExpand(s, bArr2, i);
        }
        throw new TlsFatalAlert((short) 80);
    }
}
