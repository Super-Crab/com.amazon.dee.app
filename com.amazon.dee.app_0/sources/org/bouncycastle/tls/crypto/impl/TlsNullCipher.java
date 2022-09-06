package org.bouncycastle.tls.crypto.impl;

import java.io.IOException;
import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.TlsCipher;
import org.bouncycastle.tls.crypto.TlsCryptoParameters;
import org.bouncycastle.tls.crypto.TlsDecodeResult;
import org.bouncycastle.tls.crypto.TlsEncodeResult;
import org.bouncycastle.tls.crypto.TlsHMAC;
/* loaded from: classes5.dex */
public class TlsNullCipher implements TlsCipher {
    protected final TlsCryptoParameters cryptoParams;
    protected final TlsSuiteHMac readMac;
    protected final TlsSuiteHMac writeMac;

    public TlsNullCipher(TlsCryptoParameters tlsCryptoParameters, TlsHMAC tlsHMAC, TlsHMAC tlsHMAC2) throws IOException {
        if (!TlsImplUtils.isTLSv13(tlsCryptoParameters)) {
            this.cryptoParams = tlsCryptoParameters;
            int macLength = tlsHMAC2.getMacLength() + tlsHMAC.getMacLength();
            byte[] calculateKeyBlock = TlsImplUtils.calculateKeyBlock(tlsCryptoParameters, macLength);
            tlsHMAC.setKey(calculateKeyBlock, 0, tlsHMAC.getMacLength());
            int macLength2 = tlsHMAC.getMacLength() + 0;
            tlsHMAC2.setKey(calculateKeyBlock, macLength2, tlsHMAC2.getMacLength());
            if (tlsHMAC2.getMacLength() + macLength2 != macLength) {
                throw new TlsFatalAlert((short) 80);
            }
            if (tlsCryptoParameters.isServer()) {
                this.writeMac = new TlsSuiteHMac(tlsCryptoParameters, tlsHMAC2);
                this.readMac = new TlsSuiteHMac(tlsCryptoParameters, tlsHMAC);
                return;
            }
            this.writeMac = new TlsSuiteHMac(tlsCryptoParameters, tlsHMAC);
            this.readMac = new TlsSuiteHMac(tlsCryptoParameters, tlsHMAC2);
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public TlsDecodeResult decodeCiphertext(long j, short s, ProtocolVersion protocolVersion, byte[] bArr, int i, int i2) throws IOException {
        int size = this.readMac.getSize();
        if (i2 >= size) {
            int i3 = i2 - size;
            if (!TlsUtils.constantTimeAreEqual(size, this.readMac.calculateMac(j, s, bArr, i, i3), 0, bArr, i + i3)) {
                throw new TlsFatalAlert((short) 20);
            }
            return new TlsDecodeResult(bArr, i, i3, s);
        }
        throw new TlsFatalAlert((short) 50);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public TlsEncodeResult encodePlaintext(long j, short s, ProtocolVersion protocolVersion, int i, byte[] bArr, int i2, int i3) throws IOException {
        byte[] calculateMac = this.writeMac.calculateMac(j, s, bArr, i2, i3);
        int i4 = i + i3;
        byte[] bArr2 = new byte[calculateMac.length + i4];
        System.arraycopy(bArr, i2, bArr2, i, i3);
        System.arraycopy(calculateMac, 0, bArr2, i4, calculateMac.length);
        return new TlsEncodeResult(bArr2, 0, bArr2.length, s);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public int getCiphertextDecodeLimit(int i) {
        return i + this.writeMac.getSize();
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public int getCiphertextEncodeLimit(int i, int i2) {
        return i + this.writeMac.getSize();
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public int getPlaintextLimit(int i) {
        return i - this.writeMac.getSize();
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public void rekeyDecoder() throws IOException {
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public void rekeyEncoder() throws IOException {
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public boolean usesOpaqueRecordType() {
        return false;
    }
}
