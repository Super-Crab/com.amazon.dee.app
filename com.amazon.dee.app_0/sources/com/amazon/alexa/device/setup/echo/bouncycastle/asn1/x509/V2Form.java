package com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1EncodableVector;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Sequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1TaggedObject;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERSequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERTaggedObject;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class V2Form extends ASN1Object {
    IssuerSerial baseCertificateID;
    GeneralNames issuerName;
    ObjectDigestInfo objectDigestInfo;

    public V2Form(GeneralNames generalNames) {
        this.issuerName = generalNames;
    }

    public static V2Form getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public IssuerSerial getBaseCertificateID() {
        return this.baseCertificateID;
    }

    public GeneralNames getIssuerName() {
        return this.issuerName;
    }

    public ObjectDigestInfo getObjectDigestInfo() {
        return this.objectDigestInfo;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object, com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        GeneralNames generalNames = this.issuerName;
        if (generalNames != null) {
            aSN1EncodableVector.add(generalNames);
        }
        IssuerSerial issuerSerial = this.baseCertificateID;
        if (issuerSerial != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, issuerSerial));
        }
        ObjectDigestInfo objectDigestInfo = this.objectDigestInfo;
        if (objectDigestInfo != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, objectDigestInfo));
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public static V2Form getInstance(Object obj) {
        if (obj != null && !(obj instanceof V2Form)) {
            if (obj instanceof ASN1Sequence) {
                return new V2Form((ASN1Sequence) obj);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline45(obj, GeneratedOutlineSupport1.outline107("unknown object in factory: ")));
        }
        return (V2Form) obj;
    }

    public V2Form(ASN1Sequence aSN1Sequence) {
        int i;
        if (aSN1Sequence.size() <= 3) {
            if (!(aSN1Sequence.getObjectAt(0) instanceof ASN1TaggedObject)) {
                this.issuerName = GeneralNames.getInstance(aSN1Sequence.getObjectAt(0));
                i = 1;
            } else {
                i = 0;
            }
            while (i != aSN1Sequence.size()) {
                ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(i));
                if (aSN1TaggedObject.getTagNo() == 0) {
                    this.baseCertificateID = IssuerSerial.getInstance(aSN1TaggedObject, false);
                } else if (aSN1TaggedObject.getTagNo() == 1) {
                    this.objectDigestInfo = ObjectDigestInfo.getInstance(aSN1TaggedObject, false);
                } else {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Bad tag number: ");
                    outline107.append(aSN1TaggedObject.getTagNo());
                    throw new IllegalArgumentException(outline107.toString());
                }
                i++;
            }
            return;
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Bad sequence size: ");
        outline1072.append(aSN1Sequence.size());
        throw new IllegalArgumentException(outline1072.toString());
    }
}
