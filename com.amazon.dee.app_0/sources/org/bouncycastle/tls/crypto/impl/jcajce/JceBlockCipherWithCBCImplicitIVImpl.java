package org.bouncycastle.tls.crypto.impl.jcajce;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.impl.TlsBlockCipherImpl;
/* loaded from: classes5.dex */
public class JceBlockCipherWithCBCImplicitIVImpl implements TlsBlockCipherImpl {
    private static final int BUF_SIZE = 32768;
    private final String algorithm;
    private final Cipher cipher;
    private final boolean isEncrypting;
    private SecretKey key;
    private byte[] nextIV;

    public JceBlockCipherWithCBCImplicitIVImpl(Cipher cipher, String str, boolean z) throws GeneralSecurityException {
        this.cipher = cipher;
        this.algorithm = str;
        this.isEncrypting = z;
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsBlockCipherImpl
    public int doFinal(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        try {
            this.cipher.init(this.isEncrypting ? 1 : 2, this.key, new IvParameterSpec(this.nextIV));
            this.nextIV = null;
            if (!this.isEncrypting) {
                int i4 = i + i2;
                this.nextIV = TlsUtils.copyOfRangeExact(bArr, i4 - this.cipher.getBlockSize(), i4);
            }
            int i5 = i;
            int i6 = i2;
            int i7 = 0;
            while (i6 > 32768) {
                i7 += this.cipher.update(bArr, i5, 32768, bArr2, i3 + i7);
                i5 += 32768;
                i6 -= 32768;
            }
            int update = i7 + this.cipher.update(bArr, i5, i6, bArr2, i3 + i7);
            int doFinal = update + this.cipher.doFinal(bArr2, i3 + update);
            if (this.isEncrypting) {
                int i8 = i3 + doFinal;
                this.nextIV = TlsUtils.copyOfRangeExact(bArr2, i8 - this.cipher.getBlockSize(), i8);
            }
            return doFinal;
        } catch (GeneralSecurityException e) {
            throw Exceptions.illegalStateException(e.getMessage(), e);
        }
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsBlockCipherImpl
    public int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsBlockCipherImpl
    public void init(byte[] bArr, int i, int i2) {
        if (this.nextIV == null) {
            this.nextIV = TlsUtils.copyOfRangeExact(bArr, i, i2 + i);
            return;
        }
        throw new IllegalStateException("unexpected reinitialization of an implicit-IV cipher");
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsBlockCipherImpl
    public void setKey(byte[] bArr, int i, int i2) {
        this.key = new SecretKeySpec(bArr, i, i2, this.algorithm);
    }
}
