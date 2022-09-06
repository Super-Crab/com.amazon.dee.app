package org.bouncycastle.tls.crypto.impl.jcajce;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.tls.crypto.impl.TlsBlockCipherImpl;
/* loaded from: classes5.dex */
public class JceBlockCipherImpl implements TlsBlockCipherImpl {
    private static final int BUF_SIZE = 32768;
    private final String algorithm;
    private final Cipher cipher;
    private final int cipherMode;
    private SecretKey key;
    private final int keySize;

    public JceBlockCipherImpl(Cipher cipher, String str, int i, boolean z) throws GeneralSecurityException {
        this.cipher = cipher;
        this.algorithm = str;
        this.keySize = i;
        this.cipherMode = z ? 1 : 2;
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsBlockCipherImpl
    public int doFinal(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int i4 = 0;
        int i5 = i;
        int i6 = i2;
        while (i6 > 32768) {
            try {
                i4 += this.cipher.update(bArr, i5, 32768, bArr2, i3 + i4);
                i5 += 32768;
                i6 -= 32768;
            } catch (GeneralSecurityException e) {
                throw Exceptions.illegalStateException(e.getMessage(), e);
            }
        }
        int update = i4 + this.cipher.update(bArr, i5, i6, bArr2, i3 + i4);
        return update + this.cipher.doFinal(bArr2, i3 + update);
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsBlockCipherImpl
    public int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsBlockCipherImpl
    public void init(byte[] bArr, int i, int i2) {
        try {
            this.cipher.init(this.cipherMode, this.key, new IvParameterSpec(bArr, i, i2));
        } catch (GeneralSecurityException e) {
            throw Exceptions.illegalStateException(e.getMessage(), e);
        }
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsBlockCipherImpl
    public void setKey(byte[] bArr, int i, int i2) {
        if (this.keySize == i2) {
            this.key = new SecretKeySpec(bArr, i, i2, this.algorithm);
            return;
        }
        throw new IllegalStateException();
    }
}
