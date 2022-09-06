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
public class Holder extends ASN1Object {
    public static final int V1_CERTIFICATE_HOLDER = 0;
    public static final int V2_CERTIFICATE_HOLDER = 1;
    IssuerSerial baseCertificateID;
    GeneralNames entityName;
    ObjectDigestInfo objectDigestInfo;
    private int version;

    private Holder(ASN1TaggedObject aSN1TaggedObject) {
        this.version = 1;
        int tagNo = aSN1TaggedObject.getTagNo();
        if (tagNo == 0) {
            this.baseCertificateID = IssuerSerial.getInstance(aSN1TaggedObject, false);
        } else if (tagNo == 1) {
            this.entityName = GeneralNames.getInstance(aSN1TaggedObject, false);
        } else {
            throw new IllegalArgumentException("unknown tag in Holder");
        }
        this.version = 0;
    }

    public static Holder getInstance(Object obj) {
        if (obj instanceof Holder) {
            return (Holder) obj;
        }
        if (obj instanceof ASN1TaggedObject) {
            return new Holder(ASN1TaggedObject.getInstance(obj));
        }
        if (obj == null) {
            return null;
        }
        return new Holder(ASN1Sequence.getInstance(obj));
    }

    public IssuerSerial getBaseCertificateID() {
        return this.baseCertificateID;
    }

    public GeneralNames getEntityName() {
        return this.entityName;
    }

    public ObjectDigestInfo getObjectDigestInfo() {
        return this.objectDigestInfo;
    }

    public int getVersion() {
        return this.version;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object, com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        if (this.version == 1) {
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            IssuerSerial issuerSerial = this.baseCertificateID;
            if (issuerSerial != null) {
                aSN1EncodableVector.add(new DERTaggedObject(false, 0, issuerSerial));
            }
            GeneralNames generalNames = this.entityName;
            if (generalNames != null) {
                aSN1EncodableVector.add(new DERTaggedObject(false, 1, generalNames));
            }
            ObjectDigestInfo objectDigestInfo = this.objectDigestInfo;
            if (objectDigestInfo != null) {
                aSN1EncodableVector.add(new DERTaggedObject(false, 2, objectDigestInfo));
            }
            return new DERSequence(aSN1EncodableVector);
        }
        GeneralNames generalNames2 = this.entityName;
        if (generalNames2 != null) {
            return new DERTaggedObject(false, 1, generalNames2);
        }
        return new DERTaggedObject(false, 0, this.baseCertificateID);
    }

    private Holder(ASN1Sequence aSN1Sequence) {
        this.version = 1;
        if (aSN1Sequence.size() <= 3) {
            for (int i = 0; i != aSN1Sequence.size(); i++) {
                ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(i));
                int tagNo = aSN1TaggedObject.getTagNo();
                if (tagNo == 0) {
                    this.baseCertificateID = IssuerSerial.getInstance(aSN1TaggedObject, false);
                } else if (tagNo == 1) {
                    this.entityName = GeneralNames.getInstance(aSN1TaggedObject, false);
                } else if (tagNo == 2) {
                    this.objectDigestInfo = ObjectDigestInfo.getInstance(aSN1TaggedObject, false);
                } else {
                    throw new IllegalArgumentException("unknown tag in Holder");
                }
            }
            this.version = 1;
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Bad sequence size: ");
        outline107.append(aSN1Sequence.size());
        throw new IllegalArgumentException(outline107.toString());
    }

    public Holder(IssuerSerial issuerSerial) {
        this(issuerSerial, 1);
    }

    public Holder(IssuerSerial issuerSerial, int i) {
        this.version = 1;
        this.baseCertificateID = issuerSerial;
        this.version = i;
    }

    public Holder(GeneralNames generalNames) {
        this(generalNames, 1);
    }

    public Holder(GeneralNames generalNames, int i) {
        this.version = 1;
        this.entityName = generalNames;
        this.version = i;
    }

    public Holder(ObjectDigestInfo objectDigestInfo) {
        this.version = 1;
        this.objectDigestInfo = objectDigestInfo;
    }
}
