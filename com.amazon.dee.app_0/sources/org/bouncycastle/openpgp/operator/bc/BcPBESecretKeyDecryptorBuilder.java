package org.bouncycastle.openpgp.operator.bc;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.operator.PBESecretKeyDecryptor;
import org.bouncycastle.openpgp.operator.PGPDigestCalculatorProvider;
/* loaded from: classes5.dex */
public class BcPBESecretKeyDecryptorBuilder {
    private PGPDigestCalculatorProvider calculatorProvider;

    public BcPBESecretKeyDecryptorBuilder(PGPDigestCalculatorProvider pGPDigestCalculatorProvider) {
        this.calculatorProvider = pGPDigestCalculatorProvider;
    }

    public PBESecretKeyDecryptor build(char[] cArr) {
        return new PBESecretKeyDecryptor(cArr, this.calculatorProvider) { // from class: org.bouncycastle.openpgp.operator.bc.BcPBESecretKeyDecryptorBuilder.1
            @Override // org.bouncycastle.openpgp.operator.PBESecretKeyDecryptor
            public byte[] recoverKeyData(int i, byte[] bArr, byte[] bArr2, byte[] bArr3, int i2, int i3) throws PGPException {
                try {
                    BufferedBlockCipher createSymmetricKeyWrapper = BcUtil.createSymmetricKeyWrapper(false, BcImplProvider.createBlockCipher(i), bArr, bArr2);
                    byte[] bArr4 = new byte[i3];
                    createSymmetricKeyWrapper.doFinal(bArr4, createSymmetricKeyWrapper.processBytes(bArr3, i2, i3, bArr4, 0));
                    return bArr4;
                } catch (InvalidCipherTextException e) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("decryption failed: ");
                    outline107.append(e.getMessage());
                    throw new PGPException(outline107.toString(), e);
                }
            }
        };
    }
}
