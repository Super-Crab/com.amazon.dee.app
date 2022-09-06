package org.bouncycastle.eac;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1ParsingException;
import org.bouncycastle.asn1.eac.CVCertificateRequest;
import org.bouncycastle.asn1.eac.PublicKeyDataObject;
import org.bouncycastle.eac.operator.EACSignatureVerifier;
/* loaded from: classes4.dex */
public class EACCertificateRequestHolder {
    private CVCertificateRequest request;

    public EACCertificateRequestHolder(CVCertificateRequest cVCertificateRequest) {
        this.request = cVCertificateRequest;
    }

    public EACCertificateRequestHolder(byte[] bArr) throws IOException {
        this(parseBytes(bArr));
    }

    private static CVCertificateRequest parseBytes(byte[] bArr) throws IOException {
        try {
            return CVCertificateRequest.getInstance(bArr);
        } catch (ClassCastException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("malformed data: ");
            outline107.append(e.getMessage());
            throw new EACIOException(outline107.toString(), e);
        } catch (IllegalArgumentException e2) {
            throw new EACIOException(GeneratedOutlineSupport1.outline43(e2, GeneratedOutlineSupport1.outline107("malformed data: ")), e2);
        } catch (ASN1ParsingException e3) {
            if (e3.getCause() instanceof IOException) {
                throw ((IOException) e3.getCause());
            }
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("malformed data: ");
            outline1072.append(e3.getMessage());
            throw new EACIOException(outline1072.toString(), e3);
        }
    }

    public PublicKeyDataObject getPublicKeyDataObject() {
        return this.request.getPublicKey();
    }

    public boolean isInnerSignatureValid(EACSignatureVerifier eACSignatureVerifier) throws EACException {
        try {
            OutputStream outputStream = eACSignatureVerifier.getOutputStream();
            outputStream.write(this.request.getCertificateBody().getEncoded("DER"));
            outputStream.close();
            return eACSignatureVerifier.verify(this.request.getInnerSignature());
        } catch (Exception e) {
            throw new EACException(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("unable to process signature: ")), e);
        }
    }

    public CVCertificateRequest toASN1Structure() {
        return this.request;
    }
}
