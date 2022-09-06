package org.bouncycastle.cert.ocsp;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.bouncycastle.asn1.ASN1Exception;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ocsp.OCSPRequest;
import org.bouncycastle.asn1.ocsp.Request;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.operator.ContentVerifier;
import org.bouncycastle.operator.ContentVerifierProvider;
/* loaded from: classes4.dex */
public class OCSPReq {
    private static final X509CertificateHolder[] EMPTY_CERTS = new X509CertificateHolder[0];
    private Extensions extensions;
    private OCSPRequest req;

    private OCSPReq(ASN1InputStream aSN1InputStream) throws IOException {
        try {
            this.req = OCSPRequest.getInstance(aSN1InputStream.readObject());
            if (this.req == null) {
                throw new CertIOException("malformed request: no request data found");
            }
            this.extensions = this.req.getTbsRequest().getRequestExtensions();
        } catch (ClassCastException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("malformed request: ");
            outline107.append(e.getMessage());
            throw new CertIOException(outline107.toString(), e);
        } catch (IllegalArgumentException e2) {
            throw new CertIOException(GeneratedOutlineSupport1.outline43(e2, GeneratedOutlineSupport1.outline107("malformed request: ")), e2);
        } catch (ASN1Exception e3) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("malformed request: ");
            outline1072.append(e3.getMessage());
            throw new CertIOException(outline1072.toString(), e3);
        }
    }

    public OCSPReq(OCSPRequest oCSPRequest) {
        this.req = oCSPRequest;
        this.extensions = oCSPRequest.getTbsRequest().getRequestExtensions();
    }

    public OCSPReq(byte[] bArr) throws IOException {
        this(new ASN1InputStream(bArr));
    }

    public X509CertificateHolder[] getCerts() {
        ASN1Sequence certs;
        if (this.req.getOptionalSignature() != null && (certs = this.req.getOptionalSignature().getCerts()) != null) {
            X509CertificateHolder[] x509CertificateHolderArr = new X509CertificateHolder[certs.size()];
            for (int i = 0; i != x509CertificateHolderArr.length; i++) {
                x509CertificateHolderArr[i] = new X509CertificateHolder(Certificate.getInstance(certs.getObjectAt(i)));
            }
            return x509CertificateHolderArr;
        }
        return EMPTY_CERTS;
    }

    public Set getCriticalExtensionOIDs() {
        return OCSPUtils.getCriticalExtensionOIDs(this.extensions);
    }

    public byte[] getEncoded() throws IOException {
        return this.req.getEncoded();
    }

    public Extension getExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Extensions extensions = this.extensions;
        if (extensions != null) {
            return extensions.getExtension(aSN1ObjectIdentifier);
        }
        return null;
    }

    public List getExtensionOIDs() {
        return OCSPUtils.getExtensionOIDs(this.extensions);
    }

    public Set getNonCriticalExtensionOIDs() {
        return OCSPUtils.getNonCriticalExtensionOIDs(this.extensions);
    }

    public Req[] getRequestList() {
        ASN1Sequence requestList = this.req.getTbsRequest().getRequestList();
        Req[] reqArr = new Req[requestList.size()];
        for (int i = 0; i != reqArr.length; i++) {
            reqArr[i] = new Req(Request.getInstance(requestList.getObjectAt(i)));
        }
        return reqArr;
    }

    public GeneralName getRequestorName() {
        return GeneralName.getInstance(this.req.getTbsRequest().getRequestorName());
    }

    public byte[] getSignature() {
        if (!isSigned()) {
            return null;
        }
        return this.req.getOptionalSignature().getSignature().getOctets();
    }

    public ASN1ObjectIdentifier getSignatureAlgOID() {
        if (!isSigned()) {
            return null;
        }
        return this.req.getOptionalSignature().getSignatureAlgorithm().getAlgorithm();
    }

    public int getVersionNumber() {
        return this.req.getTbsRequest().getVersion().intValueExact() + 1;
    }

    public boolean hasExtensions() {
        return this.extensions != null;
    }

    public boolean isSignatureValid(ContentVerifierProvider contentVerifierProvider) throws OCSPException {
        if (isSigned()) {
            try {
                ContentVerifier contentVerifier = contentVerifierProvider.get(this.req.getOptionalSignature().getSignatureAlgorithm());
                contentVerifier.getOutputStream().write(this.req.getTbsRequest().getEncoded("DER"));
                return contentVerifier.verify(getSignature());
            } catch (Exception e) {
                throw new OCSPException(GeneratedOutlineSupport1.outline68("exception processing signature: ", e), e);
            }
        }
        throw new OCSPException("attempt to verify signature on unsigned object");
    }

    public boolean isSigned() {
        return this.req.getOptionalSignature() != null;
    }
}
