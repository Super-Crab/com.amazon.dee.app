package com.amazon.alexa.device.setup.echo.bouncycastle.cert;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1InputStream;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DEROutputStream;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x500.X500Name;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.CertificateList;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.Extension;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.Extensions;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.GeneralName;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.GeneralNames;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.IssuingDistributionPoint;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.TBSCertList;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.ContentVerifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.ContentVerifierProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
/* loaded from: classes.dex */
public class X509CRLHolder {
    private final Extensions extensions;
    private final boolean isIndirect;
    private final GeneralNames issuerName;
    private final CertificateList x509CRL;

    public X509CRLHolder(byte[] bArr) throws IOException {
        this(parseStream(new ByteArrayInputStream(bArr)));
    }

    private static boolean isIndirectCRL(Extensions extensions) {
        Extension extension;
        return (extensions == null || (extension = extensions.getExtension(Extension.issuingDistributionPoint)) == null || !IssuingDistributionPoint.getInstance(extension.getParsedValue()).isIndirectCRL()) ? false : true;
    }

    private static CertificateList parseStream(InputStream inputStream) throws IOException {
        try {
            return CertificateList.getInstance(new ASN1InputStream(inputStream, true).readObject());
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
        if (obj instanceof X509CRLHolder) {
            return this.x509CRL.equals(((X509CRLHolder) obj).x509CRL);
        }
        return false;
    }

    public Set getCriticalExtensionOIDs() {
        return CertUtils.getCriticalExtensionOIDs(this.extensions);
    }

    public byte[] getEncoded() throws IOException {
        return this.x509CRL.getEncoded();
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
        return X500Name.getInstance(this.x509CRL.getIssuer());
    }

    public Set getNonCriticalExtensionOIDs() {
        return CertUtils.getNonCriticalExtensionOIDs(this.extensions);
    }

    public X509CRLEntryHolder getRevokedCertificate(BigInteger bigInteger) {
        Extension extension;
        GeneralNames generalNames = this.issuerName;
        Enumeration revokedCertificateEnumeration = this.x509CRL.getRevokedCertificateEnumeration();
        while (revokedCertificateEnumeration.hasMoreElements()) {
            TBSCertList.CRLEntry cRLEntry = (TBSCertList.CRLEntry) revokedCertificateEnumeration.nextElement();
            if (cRLEntry.getUserCertificate().getValue().equals(bigInteger)) {
                return new X509CRLEntryHolder(cRLEntry, this.isIndirect, generalNames);
            }
            if (this.isIndirect && cRLEntry.hasExtensions() && (extension = cRLEntry.getExtensions().getExtension(Extension.certificateIssuer)) != null) {
                generalNames = GeneralNames.getInstance(extension.getParsedValue());
            }
        }
        return null;
    }

    public Collection getRevokedCertificates() {
        ArrayList arrayList = new ArrayList(this.x509CRL.getRevokedCertificates().length);
        GeneralNames generalNames = this.issuerName;
        Enumeration revokedCertificateEnumeration = this.x509CRL.getRevokedCertificateEnumeration();
        while (revokedCertificateEnumeration.hasMoreElements()) {
            X509CRLEntryHolder x509CRLEntryHolder = new X509CRLEntryHolder((TBSCertList.CRLEntry) revokedCertificateEnumeration.nextElement(), this.isIndirect, generalNames);
            arrayList.add(x509CRLEntryHolder);
            generalNames = x509CRLEntryHolder.getCertificateIssuer();
        }
        return arrayList;
    }

    public boolean hasExtensions() {
        return this.extensions != null;
    }

    public int hashCode() {
        return this.x509CRL.hashCode();
    }

    public boolean isSignatureValid(ContentVerifierProvider contentVerifierProvider) throws CertException {
        TBSCertList tBSCertList = this.x509CRL.getTBSCertList();
        if (tBSCertList.getSignature().equals(this.x509CRL.getSignatureAlgorithm())) {
            try {
                ContentVerifier contentVerifier = contentVerifierProvider.get(tBSCertList.getSignature());
                OutputStream outputStream = contentVerifier.getOutputStream();
                new DEROutputStream(outputStream).writeObject(tBSCertList);
                outputStream.close();
                return contentVerifier.verify(this.x509CRL.getSignature().getBytes());
            } catch (Exception e) {
                throw new CertException(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("unable to process signature: ")), e);
            }
        }
        throw new CertException("signature invalid - algorithm identifier mismatch");
    }

    public CertificateList toASN1Structure() {
        return this.x509CRL;
    }

    public X509CRLHolder(InputStream inputStream) throws IOException {
        this(parseStream(inputStream));
    }

    public X509CRLHolder(CertificateList certificateList) {
        this.x509CRL = certificateList;
        this.extensions = certificateList.getTBSCertList().getExtensions();
        this.isIndirect = isIndirectCRL(this.extensions);
        this.issuerName = new GeneralNames(new GeneralName(certificateList.getIssuer()));
    }
}
