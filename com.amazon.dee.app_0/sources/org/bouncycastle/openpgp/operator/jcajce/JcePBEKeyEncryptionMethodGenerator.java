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
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.operator.PBEKeyEncryptionMethodGenerator;
import org.bouncycastle.openpgp.operator.PGPDigestCalculator;
/* loaded from: classes5.dex */
public class JcePBEKeyEncryptionMethodGenerator extends PBEKeyEncryptionMethodGenerator {
    private OperatorHelper helper;

    public JcePBEKeyEncryptionMethodGenerator(char[] cArr) {
        this(cArr, new SHA1PGPDigestCalculator());
    }

    public JcePBEKeyEncryptionMethodGenerator(char[] cArr, int i) {
        super(cArr, new SHA1PGPDigestCalculator(), i);
        this.helper = new OperatorHelper(new DefaultJcaJceHelper());
    }

    public JcePBEKeyEncryptionMethodGenerator(char[] cArr, PGPDigestCalculator pGPDigestCalculator) {
        super(cArr, pGPDigestCalculator);
        this.helper = new OperatorHelper(new DefaultJcaJceHelper());
    }

    public JcePBEKeyEncryptionMethodGenerator(char[] cArr, PGPDigestCalculator pGPDigestCalculator, int i) {
        super(cArr, pGPDigestCalculator, i);
        this.helper = new OperatorHelper(new DefaultJcaJceHelper());
    }

    @Override // org.bouncycastle.openpgp.operator.PBEKeyEncryptionMethodGenerator
    protected byte[] encryptSessionInfo(int i, byte[] bArr, byte[] bArr2) throws PGPException {
        try {
            String symmetricCipherName = PGPUtil.getSymmetricCipherName(i);
            OperatorHelper operatorHelper = this.helper;
            Cipher createCipher = operatorHelper.createCipher(symmetricCipherName + "/CFB/NoPadding");
            createCipher.init(1, new SecretKeySpec(bArr, PGPUtil.getSymmetricCipherName(i)), new IvParameterSpec(new byte[createCipher.getBlockSize()]));
            return createCipher.doFinal(bArr2, 0, bArr2.length);
        } catch (InvalidAlgorithmParameterException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("IV invalid: ");
            outline107.append(e.getMessage());
            throw new PGPException(outline107.toString(), e);
        } catch (InvalidKeyException e2) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("key invalid: ");
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

    public JcePBEKeyEncryptionMethodGenerator setProvider(String str) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(str));
        return this;
    }

    public JcePBEKeyEncryptionMethodGenerator setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    @Override // org.bouncycastle.openpgp.operator.PBEKeyEncryptionMethodGenerator
    public PBEKeyEncryptionMethodGenerator setSecureRandom(SecureRandom secureRandom) {
        super.setSecureRandom(secureRandom);
        return this;
    }
}
