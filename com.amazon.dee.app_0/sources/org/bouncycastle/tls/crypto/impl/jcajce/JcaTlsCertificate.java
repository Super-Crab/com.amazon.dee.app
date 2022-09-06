package org.bouncycastle.tls.crypto.impl.jcajce;

import com.amazon.whispercloak.KeyUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPublicKey;
import javax.crypto.interfaces.DHPublicKey;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jcajce.spec.EdDSAParameterSpec;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.TlsCertificate;
import org.bouncycastle.tls.crypto.TlsCryptoException;
import org.bouncycastle.tls.crypto.TlsVerifier;
/* loaded from: classes5.dex */
public class JcaTlsCertificate implements TlsCertificate {
    protected static final int KU_CRL_SIGN = 6;
    protected static final int KU_DATA_ENCIPHERMENT = 3;
    protected static final int KU_DECIPHER_ONLY = 8;
    protected static final int KU_DIGITAL_SIGNATURE = 0;
    protected static final int KU_ENCIPHER_ONLY = 7;
    protected static final int KU_KEY_AGREEMENT = 4;
    protected static final int KU_KEY_CERT_SIGN = 5;
    protected static final int KU_KEY_ENCIPHERMENT = 2;
    protected static final int KU_NON_REPUDIATION = 1;
    protected final X509Certificate certificate;
    protected final JcaTlsCrypto crypto;
    protected DHPublicKey pubKeyDH;
    protected ECPublicKey pubKeyEC;
    protected PublicKey pubKeyRSA;

    public JcaTlsCertificate(JcaTlsCrypto jcaTlsCrypto, X509Certificate x509Certificate) {
        this.pubKeyDH = null;
        this.pubKeyEC = null;
        this.pubKeyRSA = null;
        this.crypto = jcaTlsCrypto;
        this.certificate = x509Certificate;
    }

    public JcaTlsCertificate(JcaTlsCrypto jcaTlsCrypto, byte[] bArr) throws IOException {
        this(jcaTlsCrypto, parseCertificate(jcaTlsCrypto.getHelper(), bArr));
    }

    public static JcaTlsCertificate convert(JcaTlsCrypto jcaTlsCrypto, TlsCertificate tlsCertificate) throws IOException {
        return tlsCertificate instanceof JcaTlsCertificate ? (JcaTlsCertificate) tlsCertificate : new JcaTlsCertificate(jcaTlsCrypto, tlsCertificate.getEncoded());
    }

    public static X509Certificate parseCertificate(JcaJceHelper jcaJceHelper, byte[] bArr) throws IOException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Certificate.getInstance(bArr).getEncoded("DER"));
            X509Certificate x509Certificate = (X509Certificate) jcaJceHelper.createCertificateFactory(KeyUtils.X509_CERITIFATE_FACTORY).generateCertificate(byteArrayInputStream);
            if (byteArrayInputStream.available() == 0) {
                return x509Certificate;
            }
            throw new IOException("Extra data detected in stream");
        } catch (GeneralSecurityException e) {
            throw new TlsCryptoException("unable to decode certificate", e);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public TlsVerifier createVerifier(short s) throws IOException {
        validateKeyUsageBit(0);
        switch (s) {
            case 1:
                validateRSA_PKCS1();
                return new JcaTlsRSAVerifier(this.crypto, getPubKeyRSA());
            case 2:
                return new JcaTlsDSAVerifier(this.crypto, getPubKeyDSS());
            case 3:
                return new JcaTlsECDSAVerifier(this.crypto, getPubKeyEC());
            case 4:
            case 5:
            case 6:
                validateRSA_PSS_RSAE();
                return new JcaTlsRSAPSSVerifier(this.crypto, getPubKeyRSA(), s);
            case 7:
                return new JcaTlsEd25519Verifier(this.crypto, getPubKeyEd25519());
            case 8:
                return new JcaTlsEd448Verifier(this.crypto, getPubKeyEd448());
            case 9:
            case 10:
            case 11:
                validateRSA_PSS_PSS(s);
                return new JcaTlsRSAPSSVerifier(this.crypto, getPubKeyRSA(), s);
            default:
                throw new TlsFatalAlert((short) 46);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public byte[] getEncoded() throws IOException {
        try {
            return this.certificate.getEncoded();
        } catch (CertificateEncodingException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unable to encode certificate: ");
            outline107.append(e.getMessage());
            throw new TlsCryptoException(outline107.toString(), e);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public byte[] getExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws IOException {
        byte[] extensionValue = this.certificate.getExtensionValue(aSN1ObjectIdentifier.getId());
        if (extensionValue == null) {
            return null;
        }
        return ((ASN1OctetString) ASN1Primitive.fromByteArray(extensionValue)).getOctets();
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public short getLegacySignatureAlgorithm() throws IOException {
        PublicKey publicKey = getPublicKey();
        if (!supportsKeyUsageBit(0)) {
            return (short) -1;
        }
        if (publicKey instanceof RSAPublicKey) {
            return (short) 1;
        }
        if (publicKey instanceof DSAPublicKey) {
            return (short) 2;
        }
        return publicKey instanceof ECPublicKey ? (short) 3 : (short) -1;
    }

    DHPublicKey getPubKeyDH() throws IOException {
        try {
            return (DHPublicKey) getPublicKey();
        } catch (ClassCastException e) {
            throw new TlsFatalAlert((short) 46, (Throwable) e);
        }
    }

    DSAPublicKey getPubKeyDSS() throws IOException {
        try {
            return (DSAPublicKey) getPublicKey();
        } catch (ClassCastException e) {
            throw new TlsFatalAlert((short) 46, (Throwable) e);
        }
    }

    ECPublicKey getPubKeyEC() throws IOException {
        try {
            return (ECPublicKey) getPublicKey();
        } catch (ClassCastException e) {
            throw new TlsFatalAlert((short) 46, (Throwable) e);
        }
    }

    PublicKey getPubKeyEd25519() throws IOException {
        PublicKey publicKey = getPublicKey();
        if (EdDSAParameterSpec.Ed25519.equals(publicKey.getAlgorithm())) {
            return publicKey;
        }
        throw new TlsFatalAlert((short) 46);
    }

    PublicKey getPubKeyEd448() throws IOException {
        PublicKey publicKey = getPublicKey();
        if (EdDSAParameterSpec.Ed448.equals(publicKey.getAlgorithm())) {
            return publicKey;
        }
        throw new TlsFatalAlert((short) 46);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PublicKey getPubKeyRSA() throws IOException {
        return getPublicKey();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PublicKey getPublicKey() throws IOException {
        try {
            return this.certificate.getPublicKey();
        } catch (RuntimeException e) {
            throw new TlsFatalAlert((short) 42, (Throwable) e);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public BigInteger getSerialNumber() {
        return this.certificate.getSerialNumber();
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public String getSigAlgOID() {
        return this.certificate.getSigAlgOID();
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public ASN1Encodable getSigAlgParams() throws IOException {
        byte[] sigAlgParams = this.certificate.getSigAlgParams();
        if (sigAlgParams == null) {
            return null;
        }
        return TlsUtils.readDERObject(sigAlgParams);
    }

    protected SubjectPublicKeyInfo getSubjectPublicKeyInfo() throws IOException {
        return SubjectPublicKeyInfo.getInstance(getPublicKey().getEncoded());
    }

    public X509Certificate getX509Certificate() {
        return this.certificate;
    }

    protected boolean implSupportsSignatureAlgorithm(short s) throws IOException {
        String algorithm;
        String str;
        PublicKey publicKey = getPublicKey();
        switch (s) {
            case 1:
                return supportsRSA_PKCS1() && (publicKey instanceof RSAPublicKey);
            case 2:
                return publicKey instanceof DSAPublicKey;
            case 3:
                return publicKey instanceof ECPublicKey;
            case 4:
            case 5:
            case 6:
                return supportsRSA_PSS_RSAE() && (publicKey instanceof RSAPublicKey);
            case 7:
                algorithm = publicKey.getAlgorithm();
                str = EdDSAParameterSpec.Ed25519;
                break;
            case 8:
                algorithm = publicKey.getAlgorithm();
                str = EdDSAParameterSpec.Ed448;
                break;
            case 9:
            case 10:
            case 11:
                return supportsRSA_PSS_PSS(s) && (publicKey instanceof RSAPublicKey);
            default:
                return false;
        }
        return str.equals(algorithm);
    }

    protected boolean supportsKeyUsageBit(int i) {
        boolean[] keyUsage = this.certificate.getKeyUsage();
        return keyUsage == null || (keyUsage.length > i && keyUsage[i]);
    }

    protected boolean supportsRSA_PKCS1() throws IOException {
        return org.bouncycastle.tls.crypto.impl.RSAUtil.supportsPKCS1(getSubjectPublicKeyInfo().getAlgorithm());
    }

    protected boolean supportsRSA_PSS_PSS(short s) throws IOException {
        return org.bouncycastle.tls.crypto.impl.RSAUtil.supportsPSS_PSS(s, getSubjectPublicKeyInfo().getAlgorithm());
    }

    protected boolean supportsRSA_PSS_RSAE() throws IOException {
        return org.bouncycastle.tls.crypto.impl.RSAUtil.supportsPSS_RSAE(getSubjectPublicKeyInfo().getAlgorithm());
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public boolean supportsSignatureAlgorithm(short s) throws IOException {
        if (!supportsKeyUsageBit(0)) {
            return false;
        }
        return implSupportsSignatureAlgorithm(s);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public boolean supportsSignatureAlgorithmCA(short s) throws IOException {
        return implSupportsSignatureAlgorithm(s);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public TlsCertificate useInRole(int i, int i2) throws IOException {
        if (i2 == 7 || i2 == 9) {
            validateKeyUsageBit(4);
            this.pubKeyDH = getPubKeyDH();
            return this;
        } else if (i2 == 16 || i2 == 18) {
            validateKeyUsageBit(4);
            this.pubKeyEC = getPubKeyEC();
            return this;
        } else if (i != 0 || (i2 != 1 && i2 != 15)) {
            throw new TlsFatalAlert((short) 46);
        } else {
            validateKeyUsageBit(2);
            this.pubKeyRSA = getPubKeyRSA();
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void validateKeyUsageBit(int i) throws IOException {
        if (supportsKeyUsageBit(i)) {
            return;
        }
        throw new TlsFatalAlert((short) 46);
    }

    protected void validateRSA_PKCS1() throws IOException {
        if (supportsRSA_PKCS1()) {
            return;
        }
        throw new TlsFatalAlert((short) 46);
    }

    protected void validateRSA_PSS_PSS(short s) throws IOException {
        if (supportsRSA_PSS_PSS(s)) {
            return;
        }
        throw new TlsFatalAlert((short) 46);
    }

    protected void validateRSA_PSS_RSAE() throws IOException {
        if (supportsRSA_PSS_RSAE()) {
            return;
        }
        throw new TlsFatalAlert((short) 46);
    }
}
