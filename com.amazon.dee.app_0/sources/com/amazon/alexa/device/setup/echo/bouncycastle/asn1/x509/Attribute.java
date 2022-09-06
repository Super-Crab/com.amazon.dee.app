package com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1EncodableVector;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Sequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Set;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERObjectIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERSequence;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class Attribute extends ASN1Object {
    private final ASN1ObjectIdentifier attrType;
    private final ASN1Set attrValues;

    private Attribute(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 2) {
            this.attrType = DERObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
            this.attrValues = ASN1Set.getInstance(aSN1Sequence.getObjectAt(1));
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Bad sequence size: ");
        outline107.append(aSN1Sequence.size());
        throw new IllegalArgumentException(outline107.toString());
    }

    public static Attribute getInstance(Object obj) {
        if (obj instanceof Attribute) {
            return (Attribute) obj;
        }
        if (obj == null) {
            return null;
        }
        return new Attribute(ASN1Sequence.getInstance(obj));
    }

    public ASN1ObjectIdentifier getAttrType() {
        return new ASN1ObjectIdentifier(this.attrType.getId());
    }

    public ASN1Set getAttrValues() {
        return this.attrValues;
    }

    public ASN1Encodable[] getAttributeValues() {
        return this.attrValues.toArray();
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object, com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.attrType);
        aSN1EncodableVector.add(this.attrValues);
        return new DERSequence(aSN1EncodableVector);
    }

    public Attribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Set aSN1Set) {
        this.attrType = aSN1ObjectIdentifier;
        this.attrValues = aSN1Set;
    }
}
