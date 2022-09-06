package com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1EncodableVector;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1OctetString;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Sequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1TaggedObject;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERGeneralizedTime;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DEROctetString;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERSequence;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class RecipientKeyIdentifier extends ASN1Object {
    private DERGeneralizedTime date;
    private OtherKeyAttribute other;
    private final ASN1OctetString subjectKeyIdentifier;

    public RecipientKeyIdentifier(ASN1OctetString aSN1OctetString, DERGeneralizedTime dERGeneralizedTime, OtherKeyAttribute otherKeyAttribute) {
        this.subjectKeyIdentifier = aSN1OctetString;
        this.date = dERGeneralizedTime;
        this.other = otherKeyAttribute;
    }

    public static RecipientKeyIdentifier getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public DERGeneralizedTime getDate() {
        return this.date;
    }

    public OtherKeyAttribute getOtherKeyAttribute() {
        return this.other;
    }

    public ASN1OctetString getSubjectKeyIdentifier() {
        return this.subjectKeyIdentifier;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object, com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.subjectKeyIdentifier);
        DERGeneralizedTime dERGeneralizedTime = this.date;
        if (dERGeneralizedTime != null) {
            aSN1EncodableVector.add(dERGeneralizedTime);
        }
        OtherKeyAttribute otherKeyAttribute = this.other;
        if (otherKeyAttribute != null) {
            aSN1EncodableVector.add(otherKeyAttribute);
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public static RecipientKeyIdentifier getInstance(Object obj) {
        if (obj != null && !(obj instanceof RecipientKeyIdentifier)) {
            if (obj instanceof ASN1Sequence) {
                return new RecipientKeyIdentifier((ASN1Sequence) obj);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline45(obj, GeneratedOutlineSupport1.outline107("Invalid RecipientKeyIdentifier: ")));
        }
        return (RecipientKeyIdentifier) obj;
    }

    public RecipientKeyIdentifier(byte[] bArr, DERGeneralizedTime dERGeneralizedTime, OtherKeyAttribute otherKeyAttribute) {
        this.subjectKeyIdentifier = new DEROctetString(bArr);
        this.date = dERGeneralizedTime;
        this.other = otherKeyAttribute;
    }

    public RecipientKeyIdentifier(byte[] bArr) {
        this(bArr, (DERGeneralizedTime) null, (OtherKeyAttribute) null);
    }

    public RecipientKeyIdentifier(ASN1Sequence aSN1Sequence) {
        this.subjectKeyIdentifier = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(0));
        int size = aSN1Sequence.size();
        if (size != 1) {
            if (size != 2) {
                if (size == 3) {
                    this.date = (DERGeneralizedTime) aSN1Sequence.getObjectAt(1);
                    this.other = OtherKeyAttribute.getInstance(aSN1Sequence.getObjectAt(2));
                    return;
                }
                throw new IllegalArgumentException("Invalid RecipientKeyIdentifier");
            } else if (aSN1Sequence.getObjectAt(1) instanceof DERGeneralizedTime) {
                this.date = (DERGeneralizedTime) aSN1Sequence.getObjectAt(1);
            } else {
                this.other = OtherKeyAttribute.getInstance(aSN1Sequence.getObjectAt(2));
            }
        }
    }
}
