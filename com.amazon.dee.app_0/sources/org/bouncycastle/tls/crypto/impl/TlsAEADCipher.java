package org.bouncycastle.tls.crypto.impl;

import java.io.IOException;
import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.SecurityParameters;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.TlsCipher;
import org.bouncycastle.tls.crypto.TlsCryptoParameters;
import org.bouncycastle.tls.crypto.TlsCryptoUtils;
import org.bouncycastle.tls.crypto.TlsDecodeResult;
import org.bouncycastle.tls.crypto.TlsEncodeResult;
import org.bouncycastle.tls.crypto.TlsSecret;
/* loaded from: classes5.dex */
public class TlsAEADCipher implements TlsCipher {
    public static final int AEAD_CCM = 1;
    public static final int AEAD_CHACHA20_POLY1305 = 2;
    public static final int AEAD_GCM = 3;
    private static final int NONCE_RFC5288 = 1;
    private static final int NONCE_RFC7905 = 2;
    protected final TlsCryptoParameters cryptoParams;
    protected final TlsAEADCipherImpl decryptCipher;
    protected final byte[] decryptNonce;
    protected final TlsAEADCipherImpl encryptCipher;
    protected final byte[] encryptNonce;
    protected final int fixed_iv_length;
    protected final boolean isTLSv13;
    protected final int keySize;
    protected final int macSize;
    protected final int nonceMode;
    protected final int record_iv_length;

    public TlsAEADCipher(TlsCryptoParameters tlsCryptoParameters, TlsAEADCipherImpl tlsAEADCipherImpl, TlsAEADCipherImpl tlsAEADCipherImpl2, int i, int i2, int i3) throws IOException {
        int i4;
        ProtocolVersion negotiatedVersion = tlsCryptoParameters.getSecurityParametersHandshake().getNegotiatedVersion();
        if (TlsImplUtils.isTLSv12(negotiatedVersion)) {
            this.isTLSv13 = TlsImplUtils.isTLSv13(negotiatedVersion);
            this.nonceMode = getNonceMode(this.isTLSv13, i3);
            int i5 = this.nonceMode;
            if (i5 == 1) {
                this.fixed_iv_length = 4;
                this.record_iv_length = 8;
            } else if (i5 != 2) {
                throw new TlsFatalAlert((short) 80);
            } else {
                this.fixed_iv_length = 12;
                this.record_iv_length = 0;
            }
            this.cryptoParams = tlsCryptoParameters;
            this.keySize = i;
            this.macSize = i2;
            this.decryptCipher = tlsAEADCipherImpl2;
            this.encryptCipher = tlsAEADCipherImpl;
            int i6 = this.fixed_iv_length;
            this.decryptNonce = new byte[i6];
            this.encryptNonce = new byte[i6];
            if (this.isTLSv13) {
                rekeyDecoder();
                rekeyEncoder();
                return;
            }
            int i7 = (i6 * 2) + (i * 2);
            byte[] calculateKeyBlock = TlsImplUtils.calculateKeyBlock(tlsCryptoParameters, i7);
            if (tlsCryptoParameters.isServer()) {
                tlsAEADCipherImpl2.setKey(calculateKeyBlock, 0, i);
                int i8 = i + 0;
                tlsAEADCipherImpl.setKey(calculateKeyBlock, i8, i);
                int i9 = i8 + i;
                System.arraycopy(calculateKeyBlock, i9, this.decryptNonce, 0, this.fixed_iv_length);
                int i10 = this.fixed_iv_length;
                i4 = i9 + i10;
                System.arraycopy(calculateKeyBlock, i4, this.encryptNonce, 0, i10);
            } else {
                tlsAEADCipherImpl.setKey(calculateKeyBlock, 0, i);
                int i11 = i + 0;
                tlsAEADCipherImpl2.setKey(calculateKeyBlock, i11, i);
                int i12 = i11 + i;
                System.arraycopy(calculateKeyBlock, i12, this.encryptNonce, 0, this.fixed_iv_length);
                int i13 = this.fixed_iv_length;
                i4 = i12 + i13;
                System.arraycopy(calculateKeyBlock, i4, this.decryptNonce, 0, i13);
            }
            if (i7 != i4 + this.fixed_iv_length) {
                throw new TlsFatalAlert((short) 80);
            }
            byte[] bArr = new byte[this.fixed_iv_length + this.record_iv_length];
            bArr[0] = (byte) (~this.encryptNonce[0]);
            bArr[1] = (byte) (~this.decryptNonce[1]);
            tlsAEADCipherImpl.init(bArr, i2, null);
            tlsAEADCipherImpl2.init(bArr, i2, null);
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    private static int getNonceMode(boolean z, int i) throws IOException {
        if (i != 1) {
            if (i == 2) {
                return 2;
            }
            if (i != 3) {
                throw new TlsFatalAlert((short) 80);
            }
        }
        return z ? 2 : 1;
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public TlsDecodeResult decodeCiphertext(long j, short s, ProtocolVersion protocolVersion, byte[] bArr, int i, int i2) throws IOException {
        short s2;
        byte b;
        if (getPlaintextLimit(i2) >= 0) {
            byte[] bArr2 = this.decryptNonce;
            byte[] bArr3 = new byte[bArr2.length + this.record_iv_length];
            int i3 = this.nonceMode;
            int i4 = 0;
            if (i3 == 1) {
                System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
                int length = bArr3.length;
                int i5 = this.record_iv_length;
                System.arraycopy(bArr, i, bArr3, length - i5, i5);
            } else if (i3 != 2) {
                throw new TlsFatalAlert((short) 80);
            } else {
                TlsUtils.writeUint64(j, bArr3, bArr3.length - 8);
                while (true) {
                    byte[] bArr4 = this.decryptNonce;
                    if (i4 >= bArr4.length) {
                        break;
                    }
                    bArr3[i4] = (byte) (bArr4[i4] ^ bArr3[i4]);
                    i4++;
                }
            }
            int i6 = this.record_iv_length;
            int i7 = i + i6;
            int i8 = i2 - i6;
            int outputSize = this.decryptCipher.getOutputSize(i8);
            try {
                this.decryptCipher.init(bArr3, this.macSize, getAdditionalData(j, s, protocolVersion, i2, outputSize));
                if (this.decryptCipher.doFinal(bArr, i7, i8, TlsUtils.EMPTY_BYTES, bArr, i7) != outputSize) {
                    throw new TlsFatalAlert((short) 80);
                }
                if (this.isTLSv13) {
                    do {
                        outputSize--;
                        if (outputSize < 0) {
                            throw new TlsFatalAlert((short) 10);
                        }
                        b = bArr[i7 + outputSize];
                    } while (b == 0);
                    s2 = (short) (b & 255);
                } else {
                    s2 = s;
                }
                return new TlsDecodeResult(bArr, i7, outputSize, s2);
            } catch (Exception e) {
                throw new TlsFatalAlert((short) 20, (Throwable) e);
            }
        }
        throw new TlsFatalAlert((short) 50);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public TlsEncodeResult encodePlaintext(long j, short s, ProtocolVersion protocolVersion, int i, byte[] bArr, int i2, int i3) throws IOException {
        int i4 = i;
        byte[] bArr2 = this.encryptNonce;
        byte[] bArr3 = new byte[bArr2.length + this.record_iv_length];
        int i5 = this.nonceMode;
        if (i5 == 1) {
            System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
            TlsUtils.writeUint64(j, bArr3, this.encryptNonce.length);
        } else if (i5 != 2) {
            throw new TlsFatalAlert((short) 80);
        } else {
            TlsUtils.writeUint64(j, bArr3, bArr3.length - 8);
            int i6 = 0;
            while (true) {
                byte[] bArr4 = this.encryptNonce;
                if (i6 >= bArr4.length) {
                    break;
                }
                bArr3[i6] = (byte) (bArr4[i6] ^ bArr3[i6]);
                i6++;
            }
        }
        int outputSize = this.encryptCipher.getOutputSize(i3 + (this.isTLSv13 ? 1 : 0));
        int i7 = this.record_iv_length;
        int i8 = i7 + outputSize;
        byte[] bArr5 = new byte[i4 + i8];
        if (i7 != 0) {
            System.arraycopy(bArr3, bArr3.length - i7, bArr5, i4, i7);
            i4 += this.record_iv_length;
        }
        short s2 = this.isTLSv13 ? (short) 23 : s;
        try {
            this.encryptCipher.init(bArr3, this.macSize, getAdditionalData(j, s2, protocolVersion, i8, i3));
            short s3 = s2;
            if (i4 + this.encryptCipher.doFinal(bArr, i2, i3, this.isTLSv13 ? new byte[]{(byte) s} : TlsUtils.EMPTY_BYTES, bArr5, i4) != bArr5.length) {
                throw new TlsFatalAlert((short) 80);
            }
            return new TlsEncodeResult(bArr5, 0, bArr5.length, s3);
        } catch (Exception e) {
            throw new TlsFatalAlert((short) 80, (Throwable) e);
        }
    }

    protected byte[] getAdditionalData(long j, short s, ProtocolVersion protocolVersion, int i, int i2) throws IOException {
        if (this.isTLSv13) {
            byte[] bArr = new byte[5];
            TlsUtils.writeUint8(s, bArr, 0);
            TlsUtils.writeVersion(protocolVersion, bArr, 1);
            TlsUtils.writeUint16(i, bArr, 3);
            return bArr;
        }
        byte[] bArr2 = new byte[13];
        TlsUtils.writeUint64(j, bArr2, 0);
        TlsUtils.writeUint8(s, bArr2, 8);
        TlsUtils.writeVersion(protocolVersion, bArr2, 9);
        TlsUtils.writeUint16(i2, bArr2, 11);
        return bArr2;
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public int getCiphertextDecodeLimit(int i) {
        return i + this.macSize + this.record_iv_length + (this.isTLSv13 ? 1 : 0);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public int getCiphertextEncodeLimit(int i, int i2) {
        if (this.isTLSv13) {
            i = Math.min(i2, i + 0) + 1;
        }
        return i + this.macSize + this.record_iv_length;
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public int getPlaintextLimit(int i) {
        return ((i - this.macSize) - this.record_iv_length) - (this.isTLSv13 ? 1 : 0);
    }

    protected void rekeyCipher(TlsAEADCipherImpl tlsAEADCipherImpl, byte[] bArr, boolean z) throws IOException {
        if (this.isTLSv13) {
            SecurityParameters securityParametersHandshake = this.cryptoParams.getSecurityParametersHandshake();
            TlsSecret trafficSecretServer = z ? securityParametersHandshake.getTrafficSecretServer() : securityParametersHandshake.getTrafficSecretClient();
            if (trafficSecretServer == null) {
                throw new TlsFatalAlert((short) 80);
            }
            setup13Cipher(tlsAEADCipherImpl, bArr, trafficSecretServer, securityParametersHandshake.getPRFHashAlgorithm());
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public void rekeyDecoder() throws IOException {
        rekeyCipher(this.decryptCipher, this.decryptNonce, !this.cryptoParams.isServer());
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public void rekeyEncoder() throws IOException {
        rekeyCipher(this.encryptCipher, this.encryptNonce, this.cryptoParams.isServer());
    }

    protected void setup13Cipher(TlsAEADCipherImpl tlsAEADCipherImpl, byte[] bArr, TlsSecret tlsSecret, short s) throws IOException {
        byte[] extract = TlsCryptoUtils.hkdfExpandLabel(tlsSecret, s, "key", TlsUtils.EMPTY_BYTES, this.keySize).extract();
        byte[] extract2 = TlsCryptoUtils.hkdfExpandLabel(tlsSecret, s, "iv", TlsUtils.EMPTY_BYTES, this.fixed_iv_length).extract();
        tlsAEADCipherImpl.setKey(extract, 0, this.keySize);
        System.arraycopy(extract2, 0, bArr, 0, this.fixed_iv_length);
        extract2[0] = (byte) (extract2[0] ^ 128);
        tlsAEADCipherImpl.init(extract2, this.macSize, null);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public boolean usesOpaqueRecordType() {
        return this.isTLSv13;
    }
}
