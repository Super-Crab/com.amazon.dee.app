package org.bouncycastle.openpgp.operator.bc;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.OutputStream;
import java.security.SecureRandom;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.io.CipherOutputStream;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.operator.PGPDataEncryptor;
import org.bouncycastle.openpgp.operator.PGPDataEncryptorBuilder;
import org.bouncycastle.openpgp.operator.PGPDigestCalculator;
/* loaded from: classes5.dex */
public class BcPGPDataEncryptorBuilder implements PGPDataEncryptorBuilder {
    private int encAlgorithm;
    private SecureRandom random;
    private boolean withIntegrityPacket;

    /* loaded from: classes5.dex */
    private class MyPGPDataEncryptor implements PGPDataEncryptor {
        private final BufferedBlockCipher c;

        MyPGPDataEncryptor(byte[] bArr) throws PGPException {
            try {
                this.c = BcUtil.createStreamCipher(true, BcImplProvider.createBlockCipher(BcPGPDataEncryptorBuilder.this.encAlgorithm), BcPGPDataEncryptorBuilder.this.withIntegrityPacket, bArr);
            } catch (IllegalArgumentException e) {
                throw new PGPException(GeneratedOutlineSupport1.outline43(e, GeneratedOutlineSupport1.outline107("invalid parameters: ")), e);
            }
        }

        @Override // org.bouncycastle.openpgp.operator.PGPDataEncryptor
        public int getBlockSize() {
            return this.c.getBlockSize();
        }

        @Override // org.bouncycastle.openpgp.operator.PGPDataEncryptor
        public PGPDigestCalculator getIntegrityCalculator() {
            if (BcPGPDataEncryptorBuilder.this.withIntegrityPacket) {
                return new SHA1PGPDigestCalculator();
            }
            return null;
        }

        @Override // org.bouncycastle.openpgp.operator.PGPDataEncryptor
        public OutputStream getOutputStream(OutputStream outputStream) {
            return new CipherOutputStream(outputStream, this.c);
        }
    }

    public BcPGPDataEncryptorBuilder(int i) {
        this.encAlgorithm = i;
        if (i != 0) {
            return;
        }
        throw new IllegalArgumentException("null cipher specified");
    }

    @Override // org.bouncycastle.openpgp.operator.PGPDataEncryptorBuilder
    public PGPDataEncryptor build(byte[] bArr) throws PGPException {
        return new MyPGPDataEncryptor(bArr);
    }

    @Override // org.bouncycastle.openpgp.operator.PGPDataEncryptorBuilder
    public int getAlgorithm() {
        return this.encAlgorithm;
    }

    @Override // org.bouncycastle.openpgp.operator.PGPDataEncryptorBuilder
    public SecureRandom getSecureRandom() {
        if (this.random == null) {
            this.random = new SecureRandom();
        }
        return this.random;
    }

    public BcPGPDataEncryptorBuilder setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }

    public BcPGPDataEncryptorBuilder setWithIntegrityPacket(boolean z) {
        this.withIntegrityPacket = z;
        return this;
    }
}
