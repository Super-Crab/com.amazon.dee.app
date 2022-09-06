package org.bouncycastle.openpgp.operator.bc;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.SecureRandom;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.operator.PBEKeyEncryptionMethodGenerator;
import org.bouncycastle.openpgp.operator.PGPDigestCalculator;
/* loaded from: classes5.dex */
public class BcPBEKeyEncryptionMethodGenerator extends PBEKeyEncryptionMethodGenerator {
    public BcPBEKeyEncryptionMethodGenerator(char[] cArr) {
        this(cArr, new SHA1PGPDigestCalculator());
    }

    public BcPBEKeyEncryptionMethodGenerator(char[] cArr, int i) {
        super(cArr, new SHA1PGPDigestCalculator(), i);
    }

    public BcPBEKeyEncryptionMethodGenerator(char[] cArr, PGPDigestCalculator pGPDigestCalculator) {
        super(cArr, pGPDigestCalculator);
    }

    public BcPBEKeyEncryptionMethodGenerator(char[] cArr, PGPDigestCalculator pGPDigestCalculator, int i) {
        super(cArr, pGPDigestCalculator, i);
    }

    @Override // org.bouncycastle.openpgp.operator.PBEKeyEncryptionMethodGenerator
    protected byte[] encryptSessionInfo(int i, byte[] bArr, byte[] bArr2) throws PGPException {
        try {
            BlockCipher createBlockCipher = BcImplProvider.createBlockCipher(i);
            BufferedBlockCipher createSymmetricKeyWrapper = BcUtil.createSymmetricKeyWrapper(true, createBlockCipher, bArr, new byte[createBlockCipher.getBlockSize()]);
            byte[] bArr3 = new byte[bArr2.length];
            createSymmetricKeyWrapper.doFinal(bArr3, createSymmetricKeyWrapper.processBytes(bArr2, 0, bArr2.length, bArr3, 0));
            return bArr3;
        } catch (InvalidCipherTextException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("encryption failed: ");
            outline107.append(e.getMessage());
            throw new PGPException(outline107.toString(), e);
        }
    }

    @Override // org.bouncycastle.openpgp.operator.PBEKeyEncryptionMethodGenerator
    public PBEKeyEncryptionMethodGenerator setSecureRandom(SecureRandom secureRandom) {
        super.setSecureRandom(secureRandom);
        return this;
    }
}
