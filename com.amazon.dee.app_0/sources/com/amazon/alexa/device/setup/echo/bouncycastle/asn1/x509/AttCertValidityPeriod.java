package com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1EncodableVector;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Sequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERGeneralizedTime;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERSequence;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class AttCertValidityPeriod extends ASN1Object {
    DERGeneralizedTime notAfterTime;
    DERGeneralizedTime notBeforeTime;

    private AttCertValidityPeriod(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 2) {
            this.notBeforeTime = DERGeneralizedTime.getInstance(aSN1Sequence.getObjectAt(0));
            this.notAfterTime = DERGeneralizedTime.getInstance(aSN1Sequence.getObjectAt(1));
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Bad sequence size: ");
        outline107.append(aSN1Sequence.size());
        throw new IllegalArgumentException(outline107.toString());
    }

    public static AttCertValidityPeriod getInstance(Object obj) {
        if (obj instanceof AttCertValidityPeriod) {
            return (AttCertValidityPeriod) obj;
        }
        if (obj == null) {
            return null;
        }
        return new AttCertValidityPeriod(ASN1Sequence.getInstance(obj));
    }

    public DERGeneralizedTime getNotAfterTime() {
        return this.notAfterTime;
    }

    public DERGeneralizedTime getNotBeforeTime() {
        return this.notBeforeTime;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object, com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.notBeforeTime);
        aSN1EncodableVector.add(this.notAfterTime);
        return new DERSequence(aSN1EncodableVector);
    }

    public AttCertValidityPeriod(DERGeneralizedTime dERGeneralizedTime, DERGeneralizedTime dERGeneralizedTime2) {
        this.notBeforeTime = dERGeneralizedTime;
        this.notAfterTime = dERGeneralizedTime2;
    }
}
