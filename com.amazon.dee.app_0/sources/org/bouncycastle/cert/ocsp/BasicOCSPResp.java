package org.bouncycastle.cert.ocsp;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ocsp.BasicOCSPResponse;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.bouncycastle.asn1.ocsp.SingleResponse;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.operator.ContentVerifier;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.util.Encodable;
/* loaded from: classes4.dex */
public class BasicOCSPResp implements Encodable {
    private ResponseData data;
    private Extensions extensions;
    private BasicOCSPResponse resp;

    public BasicOCSPResp(BasicOCSPResponse basicOCSPResponse) {
        this.resp = basicOCSPResponse;
        this.data = basicOCSPResponse.getTbsResponseData();
        this.extensions = Extensions.getInstance(basicOCSPResponse.getTbsResponseData().getResponseExtensions());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BasicOCSPResp) {
            return this.resp.equals(((BasicOCSPResp) obj).resp);
        }
        return false;
    }

    public X509CertificateHolder[] getCerts() {
        ASN1Sequence certs;
        if (this.resp.getCerts() != null && (certs = this.resp.getCerts()) != null) {
            X509CertificateHolder[] x509CertificateHolderArr = new X509CertificateHolder[certs.size()];
            for (int i = 0; i != x509CertificateHolderArr.length; i++) {
                x509CertificateHolderArr[i] = new X509CertificateHolder(Certificate.getInstance(certs.getObjectAt(i)));
            }
            return x509CertificateHolderArr;
        }
        return OCSPUtils.EMPTY_CERTS;
    }

    public Set getCriticalExtensionOIDs() {
        return OCSPUtils.getCriticalExtensionOIDs(this.extensions);
    }

    @Override // org.bouncycastle.util.Encodable
    public byte[] getEncoded() throws IOException {
        return this.resp.getEncoded();
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

    public Date getProducedAt() {
        return OCSPUtils.extractDate(this.data.getProducedAt());
    }

    public RespID getResponderId() {
        return new RespID(this.data.getResponderID());
    }

    public SingleResp[] getResponses() {
        ASN1Sequence responses = this.data.getResponses();
        SingleResp[] singleRespArr = new SingleResp[responses.size()];
        for (int i = 0; i != singleRespArr.length; i++) {
            singleRespArr[i] = new SingleResp(SingleResponse.getInstance(responses.getObjectAt(i)));
        }
        return singleRespArr;
    }

    public byte[] getSignature() {
        return this.resp.getSignature().getOctets();
    }

    public ASN1ObjectIdentifier getSignatureAlgOID() {
        return this.resp.getSignatureAlgorithm().getAlgorithm();
    }

    public AlgorithmIdentifier getSignatureAlgorithmID() {
        return this.resp.getSignatureAlgorithm();
    }

    public byte[] getTBSResponseData() {
        try {
            return this.resp.getTbsResponseData().getEncoded("DER");
        } catch (IOException unused) {
            return null;
        }
    }

    public int getVersion() {
        return this.data.getVersion().intValueExact() + 1;
    }

    public boolean hasExtensions() {
        return this.extensions != null;
    }

    public int hashCode() {
        return this.resp.hashCode();
    }

    public boolean isSignatureValid(ContentVerifierProvider contentVerifierProvider) throws OCSPException {
        try {
            ContentVerifier contentVerifier = contentVerifierProvider.get(this.resp.getSignatureAlgorithm());
            OutputStream outputStream = contentVerifier.getOutputStream();
            outputStream.write(this.resp.getTbsResponseData().getEncoded("DER"));
            outputStream.close();
            return contentVerifier.verify(getSignature());
        } catch (Exception e) {
            throw new OCSPException(GeneratedOutlineSupport1.outline68("exception processing sig: ", e), e);
        }
    }
}
