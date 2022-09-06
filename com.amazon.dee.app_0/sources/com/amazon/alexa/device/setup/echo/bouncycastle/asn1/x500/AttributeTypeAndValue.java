package com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x500;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1EncodableVector;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Sequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERSequence;
/* loaded from: classes.dex */
public class AttributeTypeAndValue extends ASN1Object {
    private final ASN1ObjectIdentifier type;
    private final ASN1Encodable value;

    private AttributeTypeAndValue(ASN1Sequence aSN1Sequence) {
        this.type = (ASN1ObjectIdentifier) aSN1Sequence.getObjectAt(0);
        this.value = aSN1Sequence.getObjectAt(1);
    }

    public static AttributeTypeAndValue getInstance(Object obj) {
        if (obj instanceof AttributeTypeAndValue) {
            return (AttributeTypeAndValue) obj;
        }
        if (obj != null) {
            return new AttributeTypeAndValue(ASN1Sequence.getInstance(obj));
        }
        throw new IllegalArgumentException("null value in getInstance()");
    }

    public ASN1ObjectIdentifier getType() {
        return this.type;
    }

    public ASN1Encodable getValue() {
        return this.value;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object, com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.type);
        aSN1EncodableVector.add(this.value);
        return new DERSequence(aSN1EncodableVector);
    }

    public AttributeTypeAndValue(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.type = aSN1ObjectIdentifier;
        this.value = aSN1Encodable;
    }
}
