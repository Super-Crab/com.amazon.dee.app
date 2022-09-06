package org.bouncycastle.cmc;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.util.Encodable;
import org.bouncycastle.util.Store;
/* loaded from: classes4.dex */
public class SimplePKIResponse implements Encodable {
    private final CMSSignedData certificateResponse;

    public SimplePKIResponse(ContentInfo contentInfo) throws CMCException {
        try {
            this.certificateResponse = new CMSSignedData(contentInfo);
            if (this.certificateResponse.getSignerInfos().size() != 0) {
                throw new CMCException("malformed response: SignerInfo structures found");
            }
            if (this.certificateResponse.getSignedContent() != null) {
                throw new CMCException("malformed response: Signed Content found");
            }
        } catch (CMSException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("malformed response: ");
            outline107.append(e.getMessage());
            throw new CMCException(outline107.toString(), e);
        }
    }

    public SimplePKIResponse(byte[] bArr) throws CMCException {
        this(parseBytes(bArr));
    }

    private static ContentInfo parseBytes(byte[] bArr) throws CMCException {
        try {
            return ContentInfo.getInstance(ASN1Primitive.fromByteArray(bArr));
        } catch (Exception e) {
            throw new CMCException(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("malformed data: ")), e);
        }
    }

    public Store<X509CRLHolder> getCRLs() {
        return this.certificateResponse.getCRLs();
    }

    public Store<X509CertificateHolder> getCertificates() {
        return this.certificateResponse.getCertificates();
    }

    @Override // org.bouncycastle.util.Encodable
    public byte[] getEncoded() throws IOException {
        return this.certificateResponse.getEncoded();
    }
}
