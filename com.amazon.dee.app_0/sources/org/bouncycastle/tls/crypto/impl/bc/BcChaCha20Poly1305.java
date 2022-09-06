package org.bouncycastle.tls.crypto.impl.bc;

import java.io.IOException;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.crypto.engines.ChaCha7539Engine;
import org.bouncycastle.crypto.macs.Poly1305;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;
/* loaded from: classes5.dex */
public class BcChaCha20Poly1305 implements TlsAEADCipherImpl {
    private static final byte[] ZEROES = new byte[15];
    protected int additionalDataLength;
    protected final boolean isEncrypting;
    protected final ChaCha7539Engine cipher = new ChaCha7539Engine();
    protected final Poly1305 mac = new Poly1305();

    public BcChaCha20Poly1305(boolean z) {
        this.isEncrypting = z;
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl
    public int doFinal(byte[] bArr, int i, int i2, byte[] bArr2, byte[] bArr3, int i3) throws IOException {
        int length = bArr2.length;
        if (this.isEncrypting) {
            int i4 = i2 + length;
            int processBytes = this.cipher.processBytes(bArr, i, i2, bArr3, i3);
            if (length > 0) {
                processBytes = this.cipher.processBytes(bArr2, 0, length, bArr3, i3 + processBytes) + processBytes;
            }
            if (i4 != processBytes) {
                throw new IllegalStateException();
            }
            updateMAC(bArr3, i3, i4);
            byte[] bArr4 = new byte[16];
            Pack.longToLittleEndian(this.additionalDataLength & BodyPartID.bodyIdMax, bArr4, 0);
            Pack.longToLittleEndian(i4 & BodyPartID.bodyIdMax, bArr4, 8);
            this.mac.update(bArr4, 0, 16);
            this.mac.doFinal(bArr3, i3 + i4);
            return i4 + 16;
        } else if (length > 0) {
            throw new TlsFatalAlert((short) 80);
        } else {
            int i5 = i2 - 16;
            updateMAC(bArr, i, i5);
            byte[] bArr5 = new byte[16];
            Pack.longToLittleEndian(this.additionalDataLength & BodyPartID.bodyIdMax, bArr5, 0);
            Pack.longToLittleEndian(i5 & BodyPartID.bodyIdMax, bArr5, 8);
            this.mac.update(bArr5, 0, 16);
            this.mac.doFinal(bArr5, 0);
            if (!TlsUtils.constantTimeAreEqual(16, bArr5, 0, bArr, i + i5)) {
                throw new TlsFatalAlert((short) 20);
            }
            if (i5 != this.cipher.processBytes(bArr, i, i5, bArr3, i3)) {
                throw new IllegalStateException();
            }
            return i5;
        }
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl
    public int getOutputSize(int i) {
        return this.isEncrypting ? i + 16 : i - 16;
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl
    public void init(byte[] bArr, int i, byte[] bArr2) throws IOException {
        if (bArr != null && bArr.length == 12 && i == 16) {
            this.cipher.init(this.isEncrypting, new ParametersWithIV(null, bArr));
            initMAC();
            if (bArr2 == null) {
                this.additionalDataLength = 0;
                return;
            }
            this.additionalDataLength = bArr2.length;
            updateMAC(bArr2, 0, bArr2.length);
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    protected void initMAC() {
        byte[] bArr = new byte[64];
        this.cipher.processBytes(bArr, 0, 64, bArr, 0);
        this.mac.init(new KeyParameter(bArr, 0, 32));
        Arrays.fill(bArr, (byte) 0);
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl
    public void setKey(byte[] bArr, int i, int i2) throws IOException {
        this.cipher.init(this.isEncrypting, new ParametersWithIV(new KeyParameter(bArr, i, i2), ZEROES, 0, 12));
    }

    protected void updateMAC(byte[] bArr, int i, int i2) {
        this.mac.update(bArr, i, i2);
        int i3 = i2 % 16;
        if (i3 != 0) {
            this.mac.update(ZEROES, 0, 16 - i3);
        }
    }
}
