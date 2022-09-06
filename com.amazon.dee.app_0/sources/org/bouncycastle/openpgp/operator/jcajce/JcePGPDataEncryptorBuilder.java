package org.bouncycastle.openpgp.operator.jcajce;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Provider;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.jcajce.io.CipherOutputStream;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.operator.PGPDataEncryptor;
import org.bouncycastle.openpgp.operator.PGPDataEncryptorBuilder;
import org.bouncycastle.openpgp.operator.PGPDigestCalculator;
/* loaded from: classes5.dex */
public class JcePGPDataEncryptorBuilder implements PGPDataEncryptorBuilder {
    private int encAlgorithm;
    private OperatorHelper helper = new OperatorHelper(new DefaultJcaJceHelper());
    private SecureRandom random;
    private boolean withIntegrityPacket;

    /* loaded from: classes5.dex */
    private class MyPGPDataEncryptor implements PGPDataEncryptor {
        private final Cipher c;

        MyPGPDataEncryptor(byte[] bArr) throws PGPException {
            this.c = JcePGPDataEncryptorBuilder.this.helper.createStreamCipher(JcePGPDataEncryptorBuilder.this.encAlgorithm, JcePGPDataEncryptorBuilder.this.withIntegrityPacket);
            try {
                if (JcePGPDataEncryptorBuilder.this.withIntegrityPacket) {
                    this.c.init(1, JcaJcePGPUtil.makeSymmetricKey(JcePGPDataEncryptorBuilder.this.encAlgorithm, bArr), new IvParameterSpec(new byte[this.c.getBlockSize()]));
                } else {
                    this.c.init(1, JcaJcePGPUtil.makeSymmetricKey(JcePGPDataEncryptorBuilder.this.encAlgorithm, bArr));
                }
            } catch (InvalidAlgorithmParameterException e) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("imvalid algorithm parameter: ");
                outline107.append(e.getMessage());
                throw new PGPException(outline107.toString(), e);
            } catch (InvalidKeyException e2) {
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("invalid key: ");
                outline1072.append(e2.getMessage());
                throw new PGPException(outline1072.toString(), e2);
            }
        }

        @Override // org.bouncycastle.openpgp.operator.PGPDataEncryptor
        public int getBlockSize() {
            return this.c.getBlockSize();
        }

        @Override // org.bouncycastle.openpgp.operator.PGPDataEncryptor
        public PGPDigestCalculator getIntegrityCalculator() {
            if (JcePGPDataEncryptorBuilder.this.withIntegrityPacket) {
                return new SHA1PGPDigestCalculator();
            }
            return null;
        }

        @Override // org.bouncycastle.openpgp.operator.PGPDataEncryptor
        public OutputStream getOutputStream(OutputStream outputStream) {
            return new CipherOutputStream(outputStream, this.c);
        }
    }

    public JcePGPDataEncryptorBuilder(int i) {
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

    public JcePGPDataEncryptorBuilder setProvider(String str) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(str));
        return this;
    }

    public JcePGPDataEncryptorBuilder setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JcePGPDataEncryptorBuilder setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }

    public JcePGPDataEncryptorBuilder setWithIntegrityPacket(boolean z) {
        this.withIntegrityPacket = z;
        return this;
    }
}
