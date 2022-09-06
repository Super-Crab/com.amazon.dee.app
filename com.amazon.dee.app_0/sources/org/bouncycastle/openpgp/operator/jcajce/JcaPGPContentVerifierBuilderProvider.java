package org.bouncycastle.openpgp.operator.jcajce;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPublicKey;
import org.bouncycastle.jcajce.io.OutputStreamFactory;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPRuntimeOperationException;
import org.bouncycastle.openpgp.operator.PGPContentVerifier;
import org.bouncycastle.openpgp.operator.PGPContentVerifierBuilder;
import org.bouncycastle.openpgp.operator.PGPContentVerifierBuilderProvider;
import org.bouncycastle.openpgp.operator.PGPDigestCalculator;
/* loaded from: classes5.dex */
public class JcaPGPContentVerifierBuilderProvider implements PGPContentVerifierBuilderProvider {
    private OperatorHelper helper = new OperatorHelper(new DefaultJcaJceHelper());
    private JcaPGPDigestCalculatorProviderBuilder digestCalculatorProviderBuilder = new JcaPGPDigestCalculatorProviderBuilder();
    private JcaPGPKeyConverter keyConverter = new JcaPGPKeyConverter();

    /* loaded from: classes5.dex */
    private class JcaPGPContentVerifierBuilder implements PGPContentVerifierBuilder {
        private int hashAlgorithm;
        private int keyAlgorithm;

        public JcaPGPContentVerifierBuilder(int i, int i2) {
            this.keyAlgorithm = i;
            this.hashAlgorithm = i2;
        }

        @Override // org.bouncycastle.openpgp.operator.PGPContentVerifierBuilder
        public PGPContentVerifier build(final PGPPublicKey pGPPublicKey) throws PGPException {
            final Signature createSignature = JcaPGPContentVerifierBuilderProvider.this.helper.createSignature(this.keyAlgorithm, this.hashAlgorithm);
            final PGPDigestCalculator pGPDigestCalculator = JcaPGPContentVerifierBuilderProvider.this.digestCalculatorProviderBuilder.build().get(this.hashAlgorithm);
            final PublicKey publicKey = JcaPGPContentVerifierBuilderProvider.this.keyConverter.getPublicKey(pGPPublicKey);
            try {
                createSignature.initVerify(publicKey);
                return new PGPContentVerifier() { // from class: org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentVerifierBuilderProvider.JcaPGPContentVerifierBuilder.1
                    @Override // org.bouncycastle.openpgp.operator.PGPContentVerifier
                    public int getHashAlgorithm() {
                        return JcaPGPContentVerifierBuilder.this.hashAlgorithm;
                    }

                    @Override // org.bouncycastle.openpgp.operator.PGPContentVerifier
                    public int getKeyAlgorithm() {
                        return JcaPGPContentVerifierBuilder.this.keyAlgorithm;
                    }

                    @Override // org.bouncycastle.openpgp.operator.PGPContentVerifier
                    public long getKeyID() {
                        return pGPPublicKey.getKeyID();
                    }

                    @Override // org.bouncycastle.openpgp.operator.PGPContentVerifier
                    public OutputStream getOutputStream() {
                        return JcaPGPContentVerifierBuilder.this.keyAlgorithm == 22 ? pGPDigestCalculator.getOutputStream() : OutputStreamFactory.createStream(createSignature);
                    }

                    @Override // org.bouncycastle.openpgp.operator.PGPContentVerifier
                    public boolean verify(byte[] bArr) {
                        int bitLength;
                        try {
                            if ((publicKey instanceof RSAPublicKey) && bArr.length < (bitLength = (((RSAPublicKey) publicKey).getModulus().bitLength() + 7) / 8)) {
                                byte[] bArr2 = new byte[bitLength];
                                System.arraycopy(bArr, 0, bArr2, bArr2.length - bArr.length, bArr.length);
                                return createSignature.verify(bArr2);
                            } else if (JcaPGPContentVerifierBuilder.this.keyAlgorithm != 22) {
                                return createSignature.verify(bArr);
                            } else {
                                createSignature.update(pGPDigestCalculator.getDigest());
                                return createSignature.verify(bArr);
                            }
                        } catch (SignatureException e) {
                            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unable to verify signature: ");
                            outline107.append(e.getMessage());
                            throw new PGPRuntimeOperationException(outline107.toString(), e);
                        }
                    }
                };
            } catch (InvalidKeyException e) {
                throw new PGPException("invalid key.", e);
            }
        }
    }

    @Override // org.bouncycastle.openpgp.operator.PGPContentVerifierBuilderProvider
    public PGPContentVerifierBuilder get(int i, int i2) throws PGPException {
        return new JcaPGPContentVerifierBuilder(i, i2);
    }

    public JcaPGPContentVerifierBuilderProvider setProvider(String str) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(str));
        this.keyConverter.setProvider(str);
        this.digestCalculatorProviderBuilder.setProvider(str);
        return this;
    }

    public JcaPGPContentVerifierBuilderProvider setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        this.keyConverter.setProvider(provider);
        this.digestCalculatorProviderBuilder.setProvider(provider);
        return this;
    }
}
