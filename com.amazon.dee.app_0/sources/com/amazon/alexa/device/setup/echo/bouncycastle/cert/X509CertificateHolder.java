package com.amazon.alexa.device.setup.echo.bouncycastle.cert;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DEROutputStream;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x500.X500Name;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.Certificate;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.Extension;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.Extensions;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.TBSCertificate;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.ContentVerifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.ContentVerifierProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Set;
/* loaded from: classes.dex */
public class X509CertificateHolder {
    private final Extensions extensions;
    private final Certificate x509Certificate;

    public X509CertificateHolder(byte[] bArr) throws IOException {
        this(parseBytes(bArr));
    }

    private static Certificate parseBytes(byte[] bArr) throws IOException {
        try {
            return Certificate.getInstance(ASN1Primitive.fromByteArray(bArr));
        } catch (ClassCastException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("malformed data: ");
            outline107.append(e.getMessage());
            throw new CertIOException(outline107.toString(), e);
        } catch (IllegalArgumentException e2) {
            throw new CertIOException(GeneratedOutlineSupport1.outline43(e2, GeneratedOutlineSupport1.outline107("malformed data: ")), e2);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof X509CertificateHolder) {
            return this.x509Certificate.equals(((X509CertificateHolder) obj).x509Certificate);
        }
        return false;
    }

    public Set getCriticalExtensionOIDs() {
        return CertUtils.getCriticalExtensionOIDs(this.extensions);
    }

    public byte[] getEncoded() throws IOException {
        return this.x509Certificate.getEncoded();
    }

    public Extension getExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Extensions extensions = this.extensions;
        if (extensions != null) {
            return extensions.getExtension(aSN1ObjectIdentifier);
        }
        return null;
    }

    public List getExtensionOIDs() {
        return CertUtils.getExtensionOIDs(this.extensions);
    }

    public X500Name getIssuer() {
        return X500Name.getInstance(this.x509Certificate.getIssuer());
    }

    public Set getNonCriticalExtensionOIDs() {
        return CertUtils.getNonCriticalExtensionOIDs(this.extensions);
    }

    public Date getNotAfter() {
        return this.x509Certificate.getEndDate().getDate();
    }

    public Date getNotBefore() {
        return this.x509Certificate.getStartDate().getDate();
    }

    public BigInteger getSerialNumber() {
        return this.x509Certificate.getSerialNumber().getValue();
    }

    public byte[] getSignature() {
        return this.x509Certificate.getSignature().getBytes();
    }

    public AlgorithmIdentifier getSignatureAlgorithm() {
        return this.x509Certificate.getSignatureAlgorithm();
    }

    public X500Name getSubject() {
        return X500Name.getInstance(this.x509Certificate.getSubject());
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return this.x509Certificate.getSubjectPublicKeyInfo();
    }

    public int getVersion() {
        return this.x509Certificate.getVersionNumber();
    }

    public int getVersionNumber() {
        return this.x509Certificate.getVersionNumber();
    }

    public boolean hasExtensions() {
        return this.extensions != null;
    }

    public int hashCode() {
        return this.x509Certificate.hashCode();
    }

    public boolean isSignatureValid(ContentVerifierProvider contentVerifierProvider) throws CertException {
        TBSCertificate tBSCertificate = this.x509Certificate.getTBSCertificate();
        if (tBSCertificate.getSignature().equals(this.x509Certificate.getSignatureAlgorithm())) {
            try {
                ContentVerifier contentVerifier = contentVerifierProvider.get(tBSCertificate.getSignature());
                OutputStream outputStream = contentVerifier.getOutputStream();
                new DEROutputStream(outputStream).writeObject(tBSCertificate);
                outputStream.close();
                return contentVerifier.verify(this.x509Certificate.getSignature().getBytes());
            } catch (Exception e) {
                throw new CertException(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("unable to process signature: ")), e);
            }
        }
        throw new CertException("signature invalid - algorithm identifier mismatch");
    }

    public boolean isValidOn(Date date) {
        return !date.before(this.x509Certificate.getStartDate().getDate()) && !date.after(this.x509Certificate.getEndDate().getDate());
    }

    public Certificate toASN1Structure() {
        return this.x509Certificate;
    }

    public X509CertificateHolder(Certificate certificate) {
        this.x509Certificate = certificate;
        this.extensions = certificate.getTBSCertificate().getExtensions();
    }
}
