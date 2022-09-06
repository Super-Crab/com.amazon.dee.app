package com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1EncodableVector;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Integer;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Sequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1TaggedObject;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERBitString;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERInteger;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERSequence;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class IssuerSerial extends ASN1Object {
    GeneralNames issuer;
    DERBitString issuerUID;
    ASN1Integer serial;

    public IssuerSerial(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 2 && aSN1Sequence.size() != 3) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Bad sequence size: ");
            outline107.append(aSN1Sequence.size());
            throw new IllegalArgumentException(outline107.toString());
        }
        this.issuer = GeneralNames.getInstance(aSN1Sequence.getObjectAt(0));
        this.serial = DERInteger.getInstance(aSN1Sequence.getObjectAt(1));
        if (aSN1Sequence.size() != 3) {
            return;
        }
        this.issuerUID = DERBitString.getInstance(aSN1Sequence.getObjectAt(2));
    }

    public static IssuerSerial getInstance(Object obj) {
        if (obj != null && !(obj instanceof IssuerSerial)) {
            if (obj instanceof ASN1Sequence) {
                return new IssuerSerial((ASN1Sequence) obj);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline45(obj, GeneratedOutlineSupport1.outline107("illegal object in getInstance: ")));
        }
        return (IssuerSerial) obj;
    }

    public GeneralNames getIssuer() {
        return this.issuer;
    }

    public DERBitString getIssuerUID() {
        return this.issuerUID;
    }

    public ASN1Integer getSerial() {
        return this.serial;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object, com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.issuer);
        aSN1EncodableVector.add(this.serial);
        DERBitString dERBitString = this.issuerUID;
        if (dERBitString != null) {
            aSN1EncodableVector.add(dERBitString);
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public static IssuerSerial getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public IssuerSerial(GeneralNames generalNames, ASN1Integer aSN1Integer) {
        this.issuer = generalNames;
        this.serial = aSN1Integer;
    }
}
