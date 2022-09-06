package org.bouncycastle.tls.crypto.impl;

import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.TlsCryptoParameters;
import org.bouncycastle.tls.crypto.TlsHMAC;
import org.bouncycastle.tls.crypto.TlsMAC;
import org.bouncycastle.util.Arrays;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class TlsSuiteHMac implements TlsSuiteMac {
    protected final TlsCryptoParameters cryptoParams;
    protected final int digestBlockSize;
    protected final int digestOverhead;
    protected final TlsHMAC mac;
    protected final int macSize;

    public TlsSuiteHMac(TlsCryptoParameters tlsCryptoParameters, TlsHMAC tlsHMAC) {
        this.cryptoParams = tlsCryptoParameters;
        this.mac = tlsHMAC;
        this.macSize = getMacSize(tlsCryptoParameters, tlsHMAC);
        this.digestBlockSize = tlsHMAC.getInternalBlockSize();
        this.digestOverhead = (!TlsImplUtils.isSSL(tlsCryptoParameters) || tlsHMAC.getMacLength() != 20) ? this.digestBlockSize / 8 : 4;
    }

    static int getMacSize(TlsCryptoParameters tlsCryptoParameters, TlsMAC tlsMAC) {
        int macLength = tlsMAC.getMacLength();
        return tlsCryptoParameters.getSecurityParametersHandshake().isTruncatedHMac() ? Math.min(macLength, 10) : macLength;
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsSuiteMac
    public byte[] calculateMac(long j, short s, byte[] bArr, int i, int i2) {
        ProtocolVersion serverVersion = this.cryptoParams.getServerVersion();
        boolean isSSL = serverVersion.isSSL();
        byte[] bArr2 = new byte[isSSL ? 11 : 13];
        TlsUtils.writeUint64(j, bArr2, 0);
        TlsUtils.writeUint8(s, bArr2, 8);
        if (!isSSL) {
            TlsUtils.writeVersion(serverVersion, bArr2, 9);
        }
        TlsUtils.writeUint16(i2, bArr2, bArr2.length - 2);
        this.mac.update(bArr2, 0, bArr2.length);
        this.mac.update(bArr, i, i2);
        return truncate(this.mac.calculateMAC());
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsSuiteMac
    public byte[] calculateMacConstantTime(long j, short s, byte[] bArr, int i, int i2, int i3, byte[] bArr2) {
        byte[] calculateMac = calculateMac(j, s, bArr, i, i2);
        int i4 = TlsImplUtils.isSSL(this.cryptoParams) ? 11 : 13;
        int digestBlockCount = getDigestBlockCount(i3 + i4) - getDigestBlockCount(i4 + i2);
        while (true) {
            digestBlockCount--;
            if (digestBlockCount < 0) {
                this.mac.update(bArr2, 0, 1);
                this.mac.reset();
                return calculateMac;
            }
            this.mac.update(bArr2, 0, this.digestBlockSize);
        }
    }

    protected int getDigestBlockCount(int i) {
        return (i + this.digestOverhead) / this.digestBlockSize;
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsSuiteMac
    public int getSize() {
        return this.macSize;
    }

    protected byte[] truncate(byte[] bArr) {
        int length = bArr.length;
        int i = this.macSize;
        return length <= i ? bArr : Arrays.copyOf(bArr, i);
    }
}
