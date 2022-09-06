package com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Choice;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Sequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1TaggedObject;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERTaggedObject;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class AttCertIssuer extends ASN1Object implements ASN1Choice {
    ASN1Primitive choiceObj;
    ASN1Encodable obj;

    public AttCertIssuer(GeneralNames generalNames) {
        this.obj = generalNames;
        this.choiceObj = this.obj.toASN1Primitive();
    }

    public static AttCertIssuer getInstance(Object obj) {
        if (obj != null && !(obj instanceof AttCertIssuer)) {
            if (obj instanceof V2Form) {
                return new AttCertIssuer(V2Form.getInstance(obj));
            }
            if (obj instanceof GeneralNames) {
                return new AttCertIssuer((GeneralNames) obj);
            }
            if (obj instanceof ASN1TaggedObject) {
                return new AttCertIssuer(V2Form.getInstance((ASN1TaggedObject) obj, false));
            }
            if (obj instanceof ASN1Sequence) {
                return new AttCertIssuer(GeneralNames.getInstance(obj));
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline45(obj, GeneratedOutlineSupport1.outline107("unknown object in factory: ")));
        }
        return (AttCertIssuer) obj;
    }

    public ASN1Encodable getIssuer() {
        return this.obj;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object, com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.choiceObj;
    }

    public AttCertIssuer(V2Form v2Form) {
        this.obj = v2Form;
        this.choiceObj = new DERTaggedObject(false, 0, this.obj);
    }

    public static AttCertIssuer getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(aSN1TaggedObject.getObject());
    }
}
