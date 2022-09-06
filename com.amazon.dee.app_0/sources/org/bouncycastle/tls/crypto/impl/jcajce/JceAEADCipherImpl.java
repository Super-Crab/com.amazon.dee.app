package org.bouncycastle.tls.crypto.impl.jcajce;

import com.google.android.gms.stats.CodePackage;
import java.io.IOException;
import java.security.AccessController;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.PrivilegedAction;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.cms.GCMParameters;
import org.bouncycastle.jcajce.spec.AEADParameterSpec;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl;
/* loaded from: classes5.dex */
public class JceAEADCipherImpl implements TlsAEADCipherImpl {
    private static final int BUF_SIZE = 32768;
    private static final boolean canDoAEAD = checkForAEAD();
    private final String algorithm;
    private final String algorithmParamsName;
    private final Cipher cipher;
    private final int cipherMode;
    private final JcaJceHelper helper;
    private SecretKey key;
    private final int keySize;

    public JceAEADCipherImpl(JcaJceHelper jcaJceHelper, String str, String str2, int i, boolean z) throws GeneralSecurityException {
        this.helper = jcaJceHelper;
        this.cipher = jcaJceHelper.createCipher(str);
        this.algorithm = str2;
        this.keySize = i;
        this.cipherMode = z ? 1 : 2;
        this.algorithmParamsName = getAlgParamsName(jcaJceHelper, str);
    }

    private static boolean checkForAEAD() {
        return ((Boolean) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.bouncycastle.tls.crypto.impl.jcajce.JceAEADCipherImpl.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                try {
                    boolean z = true;
                    if (Cipher.class.getMethod("updateAAD", byte[].class) == null) {
                        z = false;
                    }
                    return Boolean.valueOf(z);
                } catch (Exception unused) {
                    return Boolean.FALSE;
                }
            }
        })).booleanValue();
    }

    private static String getAlgParamsName(JcaJceHelper jcaJceHelper, String str) {
        String str2 = "CCM";
        try {
            if (!str.contains(str2)) {
                str2 = CodePackage.GCM;
            }
            jcaJceHelper.createAlgorithmParameters(str2);
            return str2;
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl
    public int doFinal(byte[] bArr, int i, int i2, byte[] bArr2, byte[] bArr3, int i3) throws IOException {
        int length = bArr2.length;
        if (length <= 0 || 1 == this.cipherMode) {
            try {
                int updateCipher = updateCipher(bArr, i, i2, bArr3, i3);
                if (length > 0) {
                    updateCipher += updateCipher(bArr2, 0, length, bArr3, i3 + updateCipher);
                }
                return updateCipher + this.cipher.doFinal(bArr3, i3 + updateCipher);
            } catch (GeneralSecurityException e) {
                throw Exceptions.illegalStateException("", e);
            }
        }
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl
    public int getOutputSize(int i) {
        return this.cipher.getOutputSize(i);
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl
    public void init(byte[] bArr, int i, byte[] bArr2) {
        try {
            if (!canDoAEAD || this.algorithmParamsName == null) {
                this.cipher.init(this.cipherMode, this.key, new AEADParameterSpec(bArr, i * 8, bArr2));
                return;
            }
            AlgorithmParameters createAlgorithmParameters = this.helper.createAlgorithmParameters(this.algorithmParamsName);
            createAlgorithmParameters.init(new GCMParameters(bArr, i).getEncoded());
            this.cipher.init(this.cipherMode, this.key, createAlgorithmParameters);
            if (bArr2 == null || bArr2.length <= 0) {
                return;
            }
            this.cipher.updateAAD(bArr2);
        } catch (Exception e) {
            throw Exceptions.illegalStateException(e.getMessage(), e);
        }
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl
    public void setKey(byte[] bArr, int i, int i2) {
        if (this.keySize == i2) {
            this.key = new SecretKeySpec(bArr, i, i2, this.algorithm);
            return;
        }
        throw new IllegalStateException();
    }

    protected int updateCipher(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws GeneralSecurityException {
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            int min = Math.min(32768, i2 - i4);
            i5 += this.cipher.update(bArr, i + i4, min, bArr2, i3 + i5);
            i4 += min;
        }
        return i5;
    }
}
