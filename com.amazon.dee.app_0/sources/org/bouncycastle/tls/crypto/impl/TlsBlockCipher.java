package org.bouncycastle.tls.crypto.impl;

import java.io.IOException;
import java.security.SecureRandom;
import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.SecurityParameters;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.TlsCipher;
import org.bouncycastle.tls.crypto.TlsCrypto;
import org.bouncycastle.tls.crypto.TlsCryptoParameters;
import org.bouncycastle.tls.crypto.TlsDecodeResult;
import org.bouncycastle.tls.crypto.TlsEncodeResult;
import org.bouncycastle.tls.crypto.TlsHMAC;
/* loaded from: classes5.dex */
public class TlsBlockCipher implements TlsCipher {
    protected final boolean acceptExtraPadding;
    protected final TlsCrypto crypto;
    protected final TlsCryptoParameters cryptoParams;
    protected final TlsBlockCipherImpl decryptCipher;
    protected final TlsBlockCipherImpl encryptCipher;
    protected final boolean encryptThenMAC;
    protected final byte[] randomData;
    protected final TlsSuiteMac readMac;
    protected final boolean useExplicitIV;
    protected final boolean useExtraPadding;
    protected final TlsSuiteMac writeMac;

    public TlsBlockCipher(TlsCrypto tlsCrypto, TlsCryptoParameters tlsCryptoParameters, TlsBlockCipherImpl tlsBlockCipherImpl, TlsBlockCipherImpl tlsBlockCipherImpl2, TlsHMAC tlsHMAC, TlsHMAC tlsHMAC2, int i) throws IOException {
        TlsSuiteHMac tlsSuiteHMac;
        SecurityParameters securityParametersHandshake = tlsCryptoParameters.getSecurityParametersHandshake();
        ProtocolVersion negotiatedVersion = securityParametersHandshake.getNegotiatedVersion();
        if (!TlsImplUtils.isTLSv13(negotiatedVersion)) {
            this.cryptoParams = tlsCryptoParameters;
            this.crypto = tlsCrypto;
            this.randomData = tlsCryptoParameters.getNonceGenerator().generateNonce(256);
            this.encryptThenMAC = securityParametersHandshake.isEncryptThenMAC();
            this.useExplicitIV = TlsImplUtils.isTLSv11(negotiatedVersion);
            boolean z = true;
            this.acceptExtraPadding = !negotiatedVersion.isSSL();
            if (!securityParametersHandshake.isExtendedPadding() || !ProtocolVersion.TLSv10.isEqualOrEarlierVersionOf(negotiatedVersion) || (!this.encryptThenMAC && securityParametersHandshake.isTruncatedHMac())) {
                z = false;
            }
            this.useExtraPadding = z;
            this.encryptCipher = tlsBlockCipherImpl;
            this.decryptCipher = tlsBlockCipherImpl2;
            if (tlsCryptoParameters.isServer()) {
                tlsBlockCipherImpl2 = tlsBlockCipherImpl;
                tlsBlockCipherImpl = tlsBlockCipherImpl2;
            }
            int macLength = tlsHMAC2.getMacLength() + tlsHMAC.getMacLength() + (i * 2);
            if (!this.useExplicitIV) {
                macLength += tlsBlockCipherImpl2.getBlockSize() + tlsBlockCipherImpl.getBlockSize();
            }
            byte[] calculateKeyBlock = TlsImplUtils.calculateKeyBlock(tlsCryptoParameters, macLength);
            tlsHMAC.setKey(calculateKeyBlock, 0, tlsHMAC.getMacLength());
            int macLength2 = tlsHMAC.getMacLength() + 0;
            tlsHMAC2.setKey(calculateKeyBlock, macLength2, tlsHMAC2.getMacLength());
            int macLength3 = tlsHMAC2.getMacLength() + macLength2;
            tlsBlockCipherImpl.setKey(calculateKeyBlock, macLength3, i);
            int i2 = macLength3 + i;
            tlsBlockCipherImpl2.setKey(calculateKeyBlock, i2, i);
            int i3 = i2 + i;
            if (!this.useExplicitIV) {
                tlsBlockCipherImpl.init(calculateKeyBlock, i3, tlsBlockCipherImpl.getBlockSize());
                int blockSize = tlsBlockCipherImpl.getBlockSize() + i3;
                tlsBlockCipherImpl2.init(calculateKeyBlock, blockSize, tlsBlockCipherImpl2.getBlockSize());
                i3 = tlsBlockCipherImpl2.getBlockSize() + blockSize;
            }
            if (i3 != macLength) {
                throw new TlsFatalAlert((short) 80);
            }
            if (tlsCryptoParameters.isServer()) {
                this.writeMac = new TlsSuiteHMac(tlsCryptoParameters, tlsHMAC2);
                tlsSuiteHMac = new TlsSuiteHMac(tlsCryptoParameters, tlsHMAC);
            } else {
                this.writeMac = new TlsSuiteHMac(tlsCryptoParameters, tlsHMAC);
                tlsSuiteHMac = new TlsSuiteHMac(tlsCryptoParameters, tlsHMAC2);
            }
            this.readMac = tlsSuiteHMac;
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    protected int checkPaddingConstantTime(byte[] bArr, int i, int i2, int i3, int i4) {
        byte b;
        int i5;
        int i6 = i + i2;
        byte b2 = bArr[i6 - 1];
        int i7 = (b2 & 255) + 1;
        if (this.acceptExtraPadding) {
            i3 = 256;
        }
        if (i7 > Math.min(i3, i2 - i4)) {
            i5 = 0;
            b = 0;
            i7 = 0;
        } else {
            int i8 = i6 - i7;
            b = 0;
            while (true) {
                int i9 = i8 + 1;
                b = (byte) (b | (bArr[i8] ^ b2));
                if (i9 >= i6) {
                    break;
                }
                i8 = i9;
            }
            i5 = i7;
            if (b != 0) {
                i7 = 0;
            }
        }
        byte[] bArr2 = this.randomData;
        while (i5 < 256) {
            b = (byte) ((bArr2[i5] ^ b2) | b);
            i5++;
        }
        bArr2[0] = (byte) (bArr2[0] ^ b);
        return i7;
    }

    protected int chooseExtraPadBlocks(SecureRandom secureRandom, int i) {
        return Math.min(lowestBitSet(secureRandom.nextInt()), i);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public TlsDecodeResult decodeCiphertext(long j, short s, ProtocolVersion protocolVersion, byte[] bArr, int i, int i2) throws IOException {
        int i3;
        int i4;
        byte[] bArr2;
        int i5 = i;
        int blockSize = this.decryptCipher.getBlockSize();
        int size = this.readMac.getSize();
        int max = this.encryptThenMAC ? blockSize + size : Math.max(blockSize, size + 1);
        if (this.useExplicitIV) {
            max += blockSize;
        }
        if (i2 >= max) {
            int i6 = this.encryptThenMAC ? i2 - size : i2;
            if (i6 % blockSize != 0) {
                throw new TlsFatalAlert((short) 21);
            }
            if (this.encryptThenMAC) {
                i3 = 0;
                if (!TlsUtils.constantTimeAreEqual(size, this.readMac.calculateMac(j, s, bArr, i, i2 - size), 0, bArr, (i2 + i5) - size)) {
                    throw new TlsFatalAlert((short) 20);
                }
            } else {
                i3 = 0;
            }
            if (this.useExplicitIV) {
                this.decryptCipher.init(bArr, i5, blockSize);
                i5 += blockSize;
                i6 -= blockSize;
            }
            int i7 = i5;
            int i8 = i6;
            int i9 = i3;
            this.decryptCipher.doFinal(bArr, i7, i8, bArr, i7);
            int checkPaddingConstantTime = checkPaddingConstantTime(bArr, i7, i8, blockSize, this.encryptThenMAC ? i9 : size);
            int i10 = checkPaddingConstantTime == 0 ? 1 : i9;
            int i11 = i8 - checkPaddingConstantTime;
            if (!this.encryptThenMAC) {
                i11 -= size;
                i4 = i7;
                bArr2 = bArr;
                i10 |= !TlsUtils.constantTimeAreEqual(size, this.readMac.calculateMacConstantTime(j, s, bArr, i7, i11, i8 - size, this.randomData), 0, bArr2, i4 + i11) ? 1 : 0;
            } else {
                i4 = i7;
                bArr2 = bArr;
            }
            if (i10 != 0) {
                throw new TlsFatalAlert((short) 20);
            }
            return new TlsDecodeResult(bArr2, i4, i11, s);
        }
        throw new TlsFatalAlert((short) 50);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public TlsEncodeResult encodePlaintext(long j, short s, ProtocolVersion protocolVersion, int i, byte[] bArr, int i2, int i3) throws IOException {
        byte[] bArr2;
        int i4;
        int i5;
        int blockSize = this.encryptCipher.getBlockSize();
        int size = this.writeMac.getSize();
        int i6 = blockSize - ((!this.encryptThenMAC ? i3 + size : i3) % blockSize);
        if (this.useExtraPadding) {
            i6 += chooseExtraPadBlocks(this.crypto.getSecureRandom(), (256 - i6) / blockSize) * blockSize;
        }
        int i7 = size + i3 + i6;
        if (this.useExplicitIV) {
            i7 += blockSize;
        }
        byte[] bArr3 = new byte[i7 + i];
        if (this.useExplicitIV) {
            byte[] generateNonce = this.cryptoParams.getNonceGenerator().generateNonce(blockSize);
            this.encryptCipher.init(generateNonce, 0, blockSize);
            System.arraycopy(generateNonce, 0, bArr3, i, blockSize);
            i4 = i2;
            i5 = blockSize + i;
            bArr2 = bArr;
        } else {
            bArr2 = bArr;
            i4 = i2;
            i5 = i;
        }
        System.arraycopy(bArr2, i4, bArr3, i5, i3);
        int i8 = i5 + i3;
        if (!this.encryptThenMAC) {
            byte[] calculateMac = this.writeMac.calculateMac(j, s, bArr, i2, i3);
            System.arraycopy(calculateMac, 0, bArr3, i8, calculateMac.length);
            i8 += calculateMac.length;
        }
        byte b = (byte) (i6 - 1);
        int i9 = 0;
        while (i9 < i6) {
            bArr3[i8] = b;
            i9++;
            i8++;
        }
        this.encryptCipher.doFinal(bArr3, i5, i8 - i5, bArr3, i5);
        if (this.encryptThenMAC) {
            byte[] calculateMac2 = this.writeMac.calculateMac(j, s, bArr3, i, i8 - i);
            System.arraycopy(calculateMac2, 0, bArr3, i8, calculateMac2.length);
            i8 += calculateMac2.length;
        }
        if (i8 == bArr3.length) {
            return new TlsEncodeResult(bArr3, 0, bArr3.length, s);
        }
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public int getCiphertextDecodeLimit(int i) {
        return getCiphertextLength(this.decryptCipher.getBlockSize(), this.readMac.getSize(), 256, i);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public int getCiphertextEncodeLimit(int i, int i2) {
        int blockSize = this.encryptCipher.getBlockSize();
        return getCiphertextLength(blockSize, this.writeMac.getSize(), this.useExtraPadding ? 256 : blockSize, i);
    }

    protected int getCiphertextLength(int i, int i2, int i3, int i4) {
        if (this.useExplicitIV) {
            i4 += i;
        }
        int i5 = i4 + i3;
        if (this.encryptThenMAC) {
            return (i5 - (i5 % i)) + i2;
        }
        int i6 = i5 + i2;
        return i6 - (i6 % i);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public int getPlaintextLimit(int i) {
        int i2;
        int blockSize = this.encryptCipher.getBlockSize();
        int size = this.writeMac.getSize();
        if (this.encryptThenMAC) {
            i2 = i - size;
            size = i2 % blockSize;
        } else {
            i2 = i - (i % blockSize);
        }
        int i3 = (i2 - size) - 1;
        return this.useExplicitIV ? i3 - blockSize : i3;
    }

    protected int lowestBitSet(int i) {
        if (i == 0) {
            return 32;
        }
        int i2 = 0;
        while ((i & 1) == 0) {
            i2++;
            i >>= 1;
        }
        return i2;
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
