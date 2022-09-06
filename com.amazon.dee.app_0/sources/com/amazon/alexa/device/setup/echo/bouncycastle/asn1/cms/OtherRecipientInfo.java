package com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1EncodableVector;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Sequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1TaggedObject;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERObjectIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERSequence;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class OtherRecipientInfo extends ASN1Object {
    private final ASN1ObjectIdentifier oriType;
    private final ASN1Encodable oriValue;

    public OtherRecipientInfo(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.oriType = aSN1ObjectIdentifier;
        this.oriValue = aSN1Encodable;
    }

    public static OtherRecipientInfo getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public ASN1ObjectIdentifier getType() {
        return this.oriType;
    }

    public ASN1Encodable getValue() {
        return this.oriValue;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object, com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.oriType);
        aSN1EncodableVector.add(this.oriValue);
        return new DERSequence(aSN1EncodableVector);
    }

    public static OtherRecipientInfo getInstance(Object obj) {
        if (obj != null && !(obj instanceof OtherRecipientInfo)) {
            if (obj instanceof ASN1Sequence) {
                return new OtherRecipientInfo((ASN1Sequence) obj);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline45(obj, GeneratedOutlineSupport1.outline107("Invalid OtherRecipientInfo: ")));
        }
        return (OtherRecipientInfo) obj;
    }

    public OtherRecipientInfo(ASN1Sequence aSN1Sequence) {
        this.oriType = DERObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        this.oriValue = aSN1Sequence.getObjectAt(1);
    }
}
