package org.bouncycastle.asn1.x509;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
/* loaded from: classes4.dex */
public class CRLNumber extends ASN1Object {
    private BigInteger number;

    public CRLNumber(BigInteger bigInteger) {
        this.number = bigInteger;
    }

    public static CRLNumber getInstance(Object obj) {
        if (obj instanceof CRLNumber) {
            return (CRLNumber) obj;
        }
        if (obj == null) {
            return null;
        }
        return new CRLNumber(ASN1Integer.getInstance(obj).getValue());
    }

    public BigInteger getCRLNumber() {
        return this.number;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new ASN1Integer(this.number);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CRLNumber: ");
        outline107.append(getCRLNumber());
        return outline107.toString();
    }
}
