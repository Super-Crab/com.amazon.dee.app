package com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1EncodableVector;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Sequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERSequence;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class OtherKeyAttribute extends ASN1Object {
    private final ASN1Encodable keyAttr;
    private final ASN1ObjectIdentifier keyAttrId;

    public OtherKeyAttribute(ASN1Sequence aSN1Sequence) {
        this.keyAttrId = (ASN1ObjectIdentifier) aSN1Sequence.getObjectAt(0);
        this.keyAttr = aSN1Sequence.getObjectAt(1);
    }

    public static OtherKeyAttribute getInstance(Object obj) {
        if (obj != null && !(obj instanceof OtherKeyAttribute)) {
            if (obj instanceof ASN1Sequence) {
                return new OtherKeyAttribute((ASN1Sequence) obj);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline45(obj, GeneratedOutlineSupport1.outline107("unknown object in factory: ")));
        }
        return (OtherKeyAttribute) obj;
    }

    public ASN1Encodable getKeyAttr() {
        return this.keyAttr;
    }

    public ASN1ObjectIdentifier getKeyAttrId() {
        return this.keyAttrId;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object, com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.keyAttrId);
        aSN1EncodableVector.add(this.keyAttr);
        return new DERSequence(aSN1EncodableVector);
    }

    public OtherKeyAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.keyAttrId = aSN1ObjectIdentifier;
        this.keyAttr = aSN1Encodable;
    }
}
