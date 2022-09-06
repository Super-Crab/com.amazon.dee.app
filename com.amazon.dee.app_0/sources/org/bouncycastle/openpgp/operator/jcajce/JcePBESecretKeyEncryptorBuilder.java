package org.bouncycastle.openpgp.operator.jcajce;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Provider;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.operator.PBESecretKeyEncryptor;
import org.bouncycastle.openpgp.operator.PGPDigestCalculator;
/* loaded from: classes5.dex */
public class JcePBESecretKeyEncryptorBuilder {
    private int encAlgorithm;
    private OperatorHelper helper;
    private SecureRandom random;
    private int s2kCount;
    private PGPDigestCalculator s2kDigestCalculator;

    public JcePBESecretKeyEncryptorBuilder(int i) {
        this(i, new SHA1PGPDigestCalculator());
    }

    public JcePBESecretKeyEncryptorBuilder(int i, int i2) {
        this(i, new SHA1PGPDigestCalculator(), i2);
    }

    public JcePBESecretKeyEncryptorBuilder(int i, PGPDigestCalculator pGPDigestCalculator) {
        this(i, pGPDigestCalculator, 96);
    }

    public JcePBESecretKeyEncryptorBuilder(int i, PGPDigestCalculator pGPDigestCalculator, int i2) {
        this.helper = new OperatorHelper(new DefaultJcaJceHelper());
        this.s2kCount = 96;
        this.encAlgorithm = i;
        this.s2kDigestCalculator = pGPDigestCalculator;
        if (i2 < 0 || i2 > 255) {
            throw new IllegalArgumentException("s2KCount value outside of range 0 to 255.");
        }
        this.s2kCount = i2;
    }

    public PBESecretKeyEncryptor build(char[] cArr) {
        if (this.random == null) {
            this.random = new SecureRandom();
        }
        return new PBESecretKeyEncryptor(this.encAlgorithm, this.s2kDigestCalculator, this.s2kCount, this.random, cArr) { // from class: org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyEncryptorBuilder.1
            private Cipher c;
            private byte[] iv;

            @Override // org.bouncycastle.openpgp.operator.PBESecretKeyEncryptor
            public byte[] encryptKeyData(byte[] bArr, byte[] bArr2, int i, int i2) throws PGPException {
                try {
                    OperatorHelper operatorHelper = JcePBESecretKeyEncryptorBuilder.this.helper;
                    this.c = operatorHelper.createCipher(PGPUtil.getSymmetricCipherName(this.encAlgorithm) + "/CFB/NoPadding");
                    this.c.init(1, JcaJcePGPUtil.makeSymmetricKey(this.encAlgorithm, bArr), this.random);
                    this.iv = this.c.getIV();
                    return this.c.doFinal(bArr2, i, i2);
                } catch (InvalidKeyException e) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("invalid key: ");
                    outline107.append(e.getMessage());
                    throw new PGPException(outline107.toString(), e);
                } catch (BadPaddingException e2) {
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("bad padding: ");
                    outline1072.append(e2.getMessage());
                    throw new PGPException(outline1072.toString(), e2);
                } catch (IllegalBlockSizeException e3) {
                    StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("illegal block size: ");
                    outline1073.append(e3.getMessage());
                    throw new PGPException(outline1073.toString(), e3);
                }
            }

            @Override // org.bouncycastle.openpgp.operator.PBESecretKeyEncryptor
            public byte[] encryptKeyData(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, int i2) throws PGPException {
                try {
                    OperatorHelper operatorHelper = JcePBESecretKeyEncryptorBuilder.this.helper;
                    this.c = operatorHelper.createCipher(PGPUtil.getSymmetricCipherName(this.encAlgorithm) + "/CFB/NoPadding");
                    this.c.init(1, JcaJcePGPUtil.makeSymmetricKey(this.encAlgorithm, bArr), new IvParameterSpec(bArr2));
                    this.iv = bArr2;
                    return this.c.doFinal(bArr3, i, i2);
                } catch (InvalidAlgorithmParameterException e) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("invalid iv: ");
                    outline107.append(e.getMessage());
                    throw new PGPException(outline107.toString(), e);
                } catch (InvalidKeyException e2) {
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("invalid key: ");
                    outline1072.append(e2.getMessage());
                    throw new PGPException(outline1072.toString(), e2);
                } catch (BadPaddingException e3) {
                    StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("bad padding: ");
                    outline1073.append(e3.getMessage());
                    throw new PGPException(outline1073.toString(), e3);
                } catch (IllegalBlockSizeException e4) {
                    StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("illegal block size: ");
                    outline1074.append(e4.getMessage());
                    throw new PGPException(outline1074.toString(), e4);
                }
            }

            @Override // org.bouncycastle.openpgp.operator.PBESecretKeyEncryptor
            public byte[] getCipherIV() {
                return this.iv;
            }
        };
    }

    public JcePBESecretKeyEncryptorBuilder setProvider(String str) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(str));
        return this;
    }

    public JcePBESecretKeyEncryptorBuilder setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JcePBESecretKeyEncryptorBuilder setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }
}
