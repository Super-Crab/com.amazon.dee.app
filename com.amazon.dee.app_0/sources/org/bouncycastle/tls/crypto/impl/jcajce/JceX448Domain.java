package org.bouncycastle.tls.crypto.impl.jcajce;

import com.amazon.whispercloak.KeyUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import org.bouncycastle.asn1.edec.EdECObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jcajce.spec.XDHParameterSpec;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.crypto.TlsAgreement;
import org.bouncycastle.tls.crypto.TlsCryptoException;
import org.bouncycastle.tls.crypto.TlsECDomain;
import org.bouncycastle.util.Arrays;
/* loaded from: classes5.dex */
public class JceX448Domain implements TlsECDomain {
    protected final JcaTlsCrypto crypto;

    public JceX448Domain(JcaTlsCrypto jcaTlsCrypto) {
        this.crypto = jcaTlsCrypto;
    }

    public JceTlsSecret calculateECDHAgreement(PrivateKey privateKey, PublicKey publicKey) throws IOException {
        try {
            byte[] calculateKeyAgreement = this.crypto.calculateKeyAgreement(XDHParameterSpec.X448, privateKey, publicKey, "TlsPremasterSecret");
            if (calculateKeyAgreement == null || calculateKeyAgreement.length != 56) {
                throw new TlsCryptoException("invalid secret calculated");
            }
            if (Arrays.areAllZeroes(calculateKeyAgreement, 0, calculateKeyAgreement.length)) {
                throw new TlsFatalAlert((short) 40);
            }
            return this.crypto.adoptLocalSecret(calculateKeyAgreement);
        } catch (GeneralSecurityException e) {
            throw new TlsCryptoException("cannot calculate secret", e);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsECDomain
    public TlsAgreement createECDH() {
        return new JceX448(this);
    }

    public PublicKey decodePublicKey(byte[] bArr) throws IOException {
        try {
            return this.crypto.getHelper().createKeyFactory(XDHParameterSpec.X448).generatePublic(new X509EncodedKeySpec(new SubjectPublicKeyInfo(new AlgorithmIdentifier(EdECObjectIdentifiers.id_X448), bArr).getEncoded("DER")));
        } catch (Exception e) {
            throw new TlsFatalAlert((short) 47, (Throwable) e);
        }
    }

    public byte[] encodePublicKey(PublicKey publicKey) throws IOException {
        try {
            if (KeyUtils.X509_CERITIFATE_FACTORY.equals(publicKey.getFormat())) {
                return SubjectPublicKeyInfo.getInstance(publicKey.getEncoded()).getPublicKeyData().getOctets();
            }
        } catch (Exception unused) {
        }
        throw new TlsFatalAlert((short) 80);
    }

    public KeyPair generateKeyPair() {
        try {
            KeyPairGenerator createKeyPairGenerator = this.crypto.getHelper().createKeyPairGenerator(XDHParameterSpec.X448);
            createKeyPairGenerator.initialize(448, this.crypto.getSecureRandom());
            return createKeyPairGenerator.generateKeyPair();
        } catch (GeneralSecurityException e) {
            throw Exceptions.illegalStateException(GeneratedOutlineSupport1.outline99(e, GeneratedOutlineSupport1.outline107("unable to create key pair: ")), e);
        }
    }
}
