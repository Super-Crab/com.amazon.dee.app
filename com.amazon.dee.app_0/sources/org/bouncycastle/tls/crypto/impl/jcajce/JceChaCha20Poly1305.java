package org.bouncycastle.tls.crypto.impl.jcajce;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl;
import org.bouncycastle.util.Pack;
/* loaded from: classes5.dex */
public class JceChaCha20Poly1305 implements TlsAEADCipherImpl {
    private static final int BUF_SIZE = 32768;
    private static final byte[] ZEROES = new byte[15];
    protected byte[] additionalData;
    protected final Cipher cipher;
    protected SecretKey cipherKey;
    protected final int cipherMode;
    protected final Mac mac;

    public JceChaCha20Poly1305(JcaJceHelper jcaJceHelper, boolean z) throws GeneralSecurityException {
        this.cipher = jcaJceHelper.createCipher("ChaCha7539");
        this.mac = jcaJceHelper.createMac("Poly1305");
        this.cipherMode = z ? 1 : 2;
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl
    public int doFinal(byte[] bArr, int i, int i2, byte[] bArr2, byte[] bArr3, int i3) throws IOException {
        try {
            int length = bArr2.length;
            if (this.cipherMode == 1) {
                int i4 = i2 + length;
                byte[] bArr4 = new byte[i4 + 64];
                System.arraycopy(bArr, i, bArr4, 64, i2);
                System.arraycopy(bArr2, 0, bArr4, i2 + 64, length);
                runCipher(bArr4);
                System.arraycopy(bArr4, 64, bArr3, i3, i4);
                initMAC(bArr4);
                updateMAC(this.additionalData, 0, this.additionalData.length);
                updateMAC(bArr4, 64, i4);
                byte[] bArr5 = new byte[16];
                Pack.longToLittleEndian(this.additionalData.length & BodyPartID.bodyIdMax, bArr5, 0);
                Pack.longToLittleEndian(i4 & BodyPartID.bodyIdMax, bArr5, 8);
                this.mac.update(bArr5, 0, 16);
                this.mac.doFinal(bArr3, i3 + i4);
                return i4 + 16;
            } else if (length > 0) {
                throw new TlsFatalAlert((short) 80);
            } else {
                int i5 = i2 - 16;
                byte[] bArr6 = new byte[i5 + 64];
                System.arraycopy(bArr, i, bArr6, 64, i5);
                runCipher(bArr6);
                initMAC(bArr6);
                updateMAC(this.additionalData, 0, this.additionalData.length);
                updateMAC(bArr, i, i5);
                byte[] bArr7 = new byte[16];
                Pack.longToLittleEndian(this.additionalData.length & BodyPartID.bodyIdMax, bArr7, 0);
                Pack.longToLittleEndian(BodyPartID.bodyIdMax & i5, bArr7, 8);
                this.mac.update(bArr7, 0, 16);
                this.mac.doFinal(bArr7, 0);
                if (!TlsUtils.constantTimeAreEqual(16, bArr7, 0, bArr, i + i5)) {
                    throw new TlsFatalAlert((short) 20);
                }
                System.arraycopy(bArr6, 64, bArr3, i3, i5);
                return i5;
            }
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl
    public int getOutputSize(int i) {
        return this.cipherMode == 1 ? i + 16 : i - 16;
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl
    public void init(byte[] bArr, int i, byte[] bArr2) throws IOException {
        if (bArr != null && bArr.length == 12 && i == 16) {
            try {
                this.cipher.init(this.cipherMode, this.cipherKey, new IvParameterSpec(bArr));
                this.additionalData = bArr2;
                return;
            } catch (GeneralSecurityException e) {
                throw new RuntimeException(e);
            }
        }
        throw new TlsFatalAlert((short) 80);
    }

    protected void initMAC(byte[] bArr) throws InvalidKeyException {
        this.mac.init(new SecretKeySpec(bArr, 0, 32, "Poly1305"));
        for (int i = 0; i < 64; i++) {
            bArr[i] = 0;
        }
    }

    protected void runCipher(byte[] bArr) throws GeneralSecurityException {
        int i = 0;
        int i2 = 0;
        while (i < bArr.length) {
            int min = Math.min(32768, bArr.length - i);
            i2 += this.cipher.update(bArr, i, min, bArr, i2);
            i += min;
        }
        if (bArr.length == this.cipher.doFinal(bArr, i2) + i2) {
            return;
        }
        throw new IllegalStateException();
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl
    public void setKey(byte[] bArr, int i, int i2) throws IOException {
        this.cipherKey = new SecretKeySpec(bArr, i, i2, "ChaCha7539");
    }

    protected void updateMAC(byte[] bArr, int i, int i2) {
        this.mac.update(bArr, i, i2);
        int i3 = i2 % 16;
        if (i3 != 0) {
            this.mac.update(ZEROES, 0, 16 - i3);
        }
    }
}
