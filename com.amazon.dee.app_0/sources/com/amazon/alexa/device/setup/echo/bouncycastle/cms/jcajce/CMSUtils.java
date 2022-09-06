package com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1OctetString;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.Certificate;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.TBSCertificateStructure;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.X509Extension;
import java.security.Provider;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class CMSUtils {
    CMSUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static EnvelopedDataHelper createContentHelper(Provider provider) {
        if (provider != null) {
            return new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        }
        return new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static IssuerAndSerialNumber getIssuerAndSerialNumber(X509Certificate x509Certificate) throws CertificateEncodingException {
        return new IssuerAndSerialNumber(Certificate.getInstance(x509Certificate.getEncoded()).getIssuer(), x509Certificate.getSerialNumber());
    }

    static byte[] getSubjectKeyId(X509Certificate x509Certificate) {
        byte[] extensionValue = x509Certificate.getExtensionValue(X509Extension.subjectKeyIdentifier.getId());
        if (extensionValue != null) {
            return ASN1OctetString.getInstance(ASN1OctetString.getInstance(extensionValue).getOctets()).getOctets();
        }
        return null;
    }

    static TBSCertificateStructure getTBSCertificateStructure(X509Certificate x509Certificate) throws CertificateEncodingException {
        return TBSCertificateStructure.getInstance(x509Certificate.getTBSCertificate());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static EnvelopedDataHelper createContentHelper(String str) {
        if (str != null) {
            return new EnvelopedDataHelper(new NamedJcaJceExtHelper(str));
        }
        return new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
    }
}
