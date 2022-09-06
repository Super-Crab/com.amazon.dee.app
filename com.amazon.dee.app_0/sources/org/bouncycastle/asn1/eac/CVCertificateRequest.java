package org.bouncycastle.asn1.eac;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1ApplicationSpecific;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ParsingException;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERApplicationSpecific;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class CVCertificateRequest extends ASN1Object {
    private static final int bodyValid = 1;
    private static final int signValid = 2;
    private CertificateBody certificateBody;
    private byte[] innerSignature = null;
    private final ASN1ApplicationSpecific original;
    private byte[] outerSignature;

    private CVCertificateRequest(ASN1ApplicationSpecific aSN1ApplicationSpecific) throws IOException {
        this.outerSignature = null;
        this.original = aSN1ApplicationSpecific;
        if (!aSN1ApplicationSpecific.isConstructed() || aSN1ApplicationSpecific.getApplicationTag() != 7) {
            initCertBody(aSN1ApplicationSpecific);
            return;
        }
        ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(aSN1ApplicationSpecific.getObject(16));
        initCertBody(ASN1ApplicationSpecific.getInstance(aSN1Sequence.getObjectAt(0)));
        this.outerSignature = ASN1ApplicationSpecific.getInstance(aSN1Sequence.getObjectAt(aSN1Sequence.size() - 1)).getContents();
    }

    public static CVCertificateRequest getInstance(Object obj) {
        if (obj instanceof CVCertificateRequest) {
            return (CVCertificateRequest) obj;
        }
        if (obj == null) {
            return null;
        }
        try {
            return new CVCertificateRequest(ASN1ApplicationSpecific.getInstance(obj));
        } catch (IOException e) {
            throw new ASN1ParsingException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("unable to parse data: ")), e);
        }
    }

    private void initCertBody(ASN1ApplicationSpecific aSN1ApplicationSpecific) throws IOException {
        if (aSN1ApplicationSpecific.getApplicationTag() != 33) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("not a CARDHOLDER_CERTIFICATE in request:");
            outline107.append(aSN1ApplicationSpecific.getApplicationTag());
            throw new IOException(outline107.toString());
        }
        boolean z = false;
        Enumeration objects = ASN1Sequence.getInstance(aSN1ApplicationSpecific.getObject(16)).getObjects();
        while (objects.hasMoreElements()) {
            ASN1ApplicationSpecific aSN1ApplicationSpecific2 = ASN1ApplicationSpecific.getInstance(objects.nextElement());
            int applicationTag = aSN1ApplicationSpecific2.getApplicationTag();
            if (applicationTag == 55) {
                this.innerSignature = aSN1ApplicationSpecific2.getContents();
                z |= true;
            } else if (applicationTag != 78) {
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Invalid tag, not an CV Certificate Request element:");
                outline1072.append(aSN1ApplicationSpecific2.getApplicationTag());
                throw new IOException(outline1072.toString());
            } else {
                this.certificateBody = CertificateBody.getInstance(aSN1ApplicationSpecific2);
                z |= true;
            }
        }
        if (z && true) {
            return;
        }
        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Invalid CARDHOLDER_CERTIFICATE in request:");
        outline1073.append(aSN1ApplicationSpecific.getApplicationTag());
        throw new IOException(outline1073.toString());
    }

    public CertificateBody getCertificateBody() {
        return this.certificateBody;
    }

    public byte[] getInnerSignature() {
        return Arrays.clone(this.innerSignature);
    }

    public byte[] getOuterSignature() {
        return Arrays.clone(this.outerSignature);
    }

    public PublicKeyDataObject getPublicKey() {
        return this.certificateBody.getPublicKey();
    }

    public boolean hasOuterSignature() {
        return this.outerSignature != null;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1ApplicationSpecific aSN1ApplicationSpecific = this.original;
        if (aSN1ApplicationSpecific != null) {
            return aSN1ApplicationSpecific;
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(this.certificateBody);
        try {
            aSN1EncodableVector.add(new DERApplicationSpecific(false, 55, (ASN1Encodable) new DEROctetString(this.innerSignature)));
            return new DERApplicationSpecific(33, aSN1EncodableVector);
        } catch (IOException unused) {
            throw new IllegalStateException("unable to convert signature!");
        }
    }
}
