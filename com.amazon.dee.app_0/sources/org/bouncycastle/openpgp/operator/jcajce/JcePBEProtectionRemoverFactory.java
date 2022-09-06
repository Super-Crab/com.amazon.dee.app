package org.bouncycastle.openpgp.operator.jcajce;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Provider;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.operator.PBEProtectionRemoverFactory;
import org.bouncycastle.openpgp.operator.PBESecretKeyDecryptor;
import org.bouncycastle.openpgp.operator.PGPDigestCalculatorProvider;
/* loaded from: classes5.dex */
public class JcePBEProtectionRemoverFactory implements PBEProtectionRemoverFactory {
    private PGPDigestCalculatorProvider calculatorProvider;
    private JcaPGPDigestCalculatorProviderBuilder calculatorProviderBuilder;
    private OperatorHelper helper;
    private final char[] passPhrase;

    public JcePBEProtectionRemoverFactory(char[] cArr) {
        this.helper = new OperatorHelper(new DefaultJcaJceHelper());
        this.passPhrase = cArr;
        this.calculatorProviderBuilder = new JcaPGPDigestCalculatorProviderBuilder();
    }

    public JcePBEProtectionRemoverFactory(char[] cArr, PGPDigestCalculatorProvider pGPDigestCalculatorProvider) {
        this.helper = new OperatorHelper(new DefaultJcaJceHelper());
        this.passPhrase = cArr;
        this.calculatorProvider = pGPDigestCalculatorProvider;
    }

    @Override // org.bouncycastle.openpgp.operator.PBEProtectionRemoverFactory
    public PBESecretKeyDecryptor createDecryptor(String str) throws PGPException {
        if (this.calculatorProvider == null) {
            this.calculatorProvider = this.calculatorProviderBuilder.build();
        }
        return new PBESecretKeyDecryptor(this.passPhrase, this.calculatorProvider) { // from class: org.bouncycastle.openpgp.operator.jcajce.JcePBEProtectionRemoverFactory.1
            @Override // org.bouncycastle.openpgp.operator.PBESecretKeyDecryptor
            public byte[] recoverKeyData(int i, byte[] bArr, byte[] bArr2, byte[] bArr3, int i2, int i3) throws PGPException {
                try {
                    OperatorHelper operatorHelper = JcePBEProtectionRemoverFactory.this.helper;
                    Cipher createCipher = operatorHelper.createCipher(PGPUtil.getSymmetricCipherName(i) + "/CBC/NoPadding");
                    createCipher.init(2, JcaJcePGPUtil.makeSymmetricKey(i, bArr), new IvParameterSpec(bArr2));
                    return createCipher.doFinal(bArr3, i2, i3);
                } catch (InvalidAlgorithmParameterException e) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("invalid parameter: ");
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
        };
    }

    public JcePBEProtectionRemoverFactory setProvider(String str) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(str));
        JcaPGPDigestCalculatorProviderBuilder jcaPGPDigestCalculatorProviderBuilder = this.calculatorProviderBuilder;
        if (jcaPGPDigestCalculatorProviderBuilder != null) {
            jcaPGPDigestCalculatorProviderBuilder.setProvider(str);
        }
        return this;
    }

    public JcePBEProtectionRemoverFactory setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        JcaPGPDigestCalculatorProviderBuilder jcaPGPDigestCalculatorProviderBuilder = this.calculatorProviderBuilder;
        if (jcaPGPDigestCalculatorProviderBuilder != null) {
            jcaPGPDigestCalculatorProviderBuilder.setProvider(provider);
        }
        return this;
    }
}
