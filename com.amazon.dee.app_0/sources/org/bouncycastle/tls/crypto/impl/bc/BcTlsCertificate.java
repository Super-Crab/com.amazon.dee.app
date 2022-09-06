package org.bouncycastle.tls.crypto.impl.bc;

import java.io.IOException;
import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.DHPublicKeyParameters;
import org.bouncycastle.crypto.params.DSAPublicKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.params.Ed448PublicKeyParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.crypto.TlsCertificate;
import org.bouncycastle.tls.crypto.TlsVerifier;
import org.bouncycastle.tls.crypto.impl.RSAUtil;
import org.bouncycastle.util.Arrays;
/* loaded from: classes5.dex */
public class BcTlsCertificate implements TlsCertificate {
    protected final Certificate certificate;
    protected final BcTlsCrypto crypto;
    protected DHPublicKeyParameters pubKeyDH;
    protected ECPublicKeyParameters pubKeyEC;
    protected Ed25519PublicKeyParameters pubKeyEd25519;
    protected Ed448PublicKeyParameters pubKeyEd448;
    protected RSAKeyParameters pubKeyRSA;

    public BcTlsCertificate(BcTlsCrypto bcTlsCrypto, Certificate certificate) {
        this.pubKeyDH = null;
        this.pubKeyEC = null;
        this.pubKeyEd25519 = null;
        this.pubKeyEd448 = null;
        this.pubKeyRSA = null;
        this.crypto = bcTlsCrypto;
        this.certificate = certificate;
    }

    public BcTlsCertificate(BcTlsCrypto bcTlsCrypto, byte[] bArr) throws IOException {
        this(bcTlsCrypto, parseCertificate(bArr));
    }

    public static BcTlsCertificate convert(BcTlsCrypto bcTlsCrypto, TlsCertificate tlsCertificate) throws IOException {
        return tlsCertificate instanceof BcTlsCertificate ? (BcTlsCertificate) tlsCertificate : new BcTlsCertificate(bcTlsCrypto, tlsCertificate.getEncoded());
    }

    public static Certificate parseCertificate(byte[] bArr) throws IOException {
        try {
            return Certificate.getInstance(bArr);
        } catch (IllegalArgumentException e) {
            throw new TlsFatalAlert((short) 42, (Throwable) e);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public TlsVerifier createVerifier(short s) throws IOException {
        validateKeyUsage(128);
        switch (s) {
            case 1:
                validateRSA_PKCS1();
                return new BcTlsRSAVerifier(this.crypto, getPubKeyRSA());
            case 2:
                return new BcTlsDSAVerifier(this.crypto, getPubKeyDSS());
            case 3:
                return new BcTlsECDSAVerifier(this.crypto, getPubKeyEC());
            case 4:
            case 5:
            case 6:
                validateRSA_PSS_RSAE();
                return new BcTlsRSAPSSVerifier(this.crypto, getPubKeyRSA(), s);
            case 7:
                return new BcTlsEd25519Verifier(this.crypto, getPubKeyEd25519());
            case 8:
                return new BcTlsEd448Verifier(this.crypto, getPubKeyEd448());
            case 9:
            case 10:
            case 11:
                validateRSA_PSS_PSS(s);
                return new BcTlsRSAPSSVerifier(this.crypto, getPubKeyRSA(), s);
            default:
                throw new TlsFatalAlert((short) 46);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public byte[] getEncoded() throws IOException {
        return this.certificate.getEncoded("DER");
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public byte[] getExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws IOException {
        Extension extension;
        Extensions extensions = this.certificate.getTBSCertificate().getExtensions();
        if (extensions == null || (extension = extensions.getExtension(aSN1ObjectIdentifier)) == null) {
            return null;
        }
        return Arrays.clone(extension.getExtnValue().getOctets());
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public short getLegacySignatureAlgorithm() throws IOException {
        AsymmetricKeyParameter publicKey = getPublicKey();
        if (!publicKey.isPrivate()) {
            if (!supportsKeyUsage(128)) {
                return (short) -1;
            }
            if (publicKey instanceof RSAKeyParameters) {
                return (short) 1;
            }
            if (publicKey instanceof DSAPublicKeyParameters) {
                return (short) 2;
            }
            return publicKey instanceof ECPublicKeyParameters ? (short) 3 : (short) -1;
        }
        throw new TlsFatalAlert((short) 80);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DHPublicKeyParameters getPubKeyDH() throws IOException {
        try {
            return (DHPublicKeyParameters) getPublicKey();
        } catch (RuntimeException e) {
            throw new TlsFatalAlert((short) 46, (Throwable) e);
        }
    }

    public DSAPublicKeyParameters getPubKeyDSS() throws IOException {
        try {
            return (DSAPublicKeyParameters) getPublicKey();
        } catch (ClassCastException e) {
            throw new TlsFatalAlert((short) 46, (Throwable) e);
        }
    }

    public ECPublicKeyParameters getPubKeyEC() throws IOException {
        try {
            return (ECPublicKeyParameters) getPublicKey();
        } catch (ClassCastException e) {
            throw new TlsFatalAlert((short) 46, (Throwable) e);
        }
    }

    public Ed25519PublicKeyParameters getPubKeyEd25519() throws IOException {
        try {
            return (Ed25519PublicKeyParameters) getPublicKey();
        } catch (ClassCastException e) {
            throw new TlsFatalAlert((short) 46, (Throwable) e);
        }
    }

    public Ed448PublicKeyParameters getPubKeyEd448() throws IOException {
        try {
            return (Ed448PublicKeyParameters) getPublicKey();
        } catch (ClassCastException e) {
            throw new TlsFatalAlert((short) 46, (Throwable) e);
        }
    }

    public RSAKeyParameters getPubKeyRSA() throws IOException {
        try {
            return (RSAKeyParameters) getPublicKey();
        } catch (ClassCastException e) {
            throw new TlsFatalAlert((short) 46, (Throwable) e);
        }
    }

    protected AsymmetricKeyParameter getPublicKey() throws IOException {
        try {
            return PublicKeyFactory.createKey(this.certificate.getSubjectPublicKeyInfo());
        } catch (RuntimeException e) {
            throw new TlsFatalAlert((short) 43, (Throwable) e);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public BigInteger getSerialNumber() {
        return this.certificate.getSerialNumber().getValue();
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public String getSigAlgOID() {
        return this.certificate.getSignatureAlgorithm().getAlgorithm().getId();
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public ASN1Encodable getSigAlgParams() {
        return this.certificate.getSignatureAlgorithm().getParameters();
    }

    protected boolean supportsKeyUsage(int i) {
        KeyUsage fromExtensions;
        Extensions extensions = this.certificate.getTBSCertificate().getExtensions();
        return extensions == null || (fromExtensions = KeyUsage.fromExtensions(extensions)) == null || ((fromExtensions.getBytes()[0] & 255) & i) == i;
    }

    protected boolean supportsRSA_PKCS1() {
        return RSAUtil.supportsPKCS1(this.certificate.getSubjectPublicKeyInfo().getAlgorithm());
    }

    protected boolean supportsRSA_PSS_PSS(short s) {
        return RSAUtil.supportsPSS_PSS(s, this.certificate.getSubjectPublicKeyInfo().getAlgorithm());
    }

    protected boolean supportsRSA_PSS_RSAE() {
        return RSAUtil.supportsPSS_RSAE(this.certificate.getSubjectPublicKeyInfo().getAlgorithm());
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public boolean supportsSignatureAlgorithm(short s) throws IOException {
        return supportsSignatureAlgorithm(s, 128);
    }

    protected boolean supportsSignatureAlgorithm(short s, int i) throws IOException {
        if (!supportsKeyUsage(i)) {
            return false;
        }
        AsymmetricKeyParameter publicKey = getPublicKey();
        switch (s) {
            case 1:
                return supportsRSA_PKCS1() && (publicKey instanceof RSAKeyParameters);
            case 2:
                return publicKey instanceof DSAPublicKeyParameters;
            case 3:
                return publicKey instanceof ECPublicKeyParameters;
            case 4:
            case 5:
            case 6:
                return supportsRSA_PSS_RSAE() && (publicKey instanceof RSAKeyParameters);
            case 7:
                return publicKey instanceof Ed25519PublicKeyParameters;
            case 8:
                return publicKey instanceof Ed448PublicKeyParameters;
            case 9:
            case 10:
            case 11:
                return supportsRSA_PSS_PSS(s) && (publicKey instanceof RSAKeyParameters);
            default:
                return false;
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public boolean supportsSignatureAlgorithmCA(short s) throws IOException {
        return supportsSignatureAlgorithm(s, 4);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCertificate
    public TlsCertificate useInRole(int i, int i2) throws IOException {
        if (i2 == 7 || i2 == 9) {
            validateKeyUsage(8);
            this.pubKeyDH = getPubKeyDH();
            return this;
        } else if (i2 == 16 || i2 == 18) {
            validateKeyUsage(8);
            this.pubKeyEC = getPubKeyEC();
            return this;
        } else if (i != 0 || (i2 != 1 && i2 != 15)) {
            throw new TlsFatalAlert((short) 46);
        } else {
            validateKeyUsage(32);
            this.pubKeyRSA = getPubKeyRSA();
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void validateKeyUsage(int i) throws IOException {
        if (supportsKeyUsage(i)) {
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
