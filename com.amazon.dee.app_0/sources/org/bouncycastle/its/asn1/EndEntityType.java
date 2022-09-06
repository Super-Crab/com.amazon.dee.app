package org.bouncycastle.its.asn1;

import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERBitString;
/* loaded from: classes4.dex */
public class EndEntityType extends ASN1Object {
    public static final int app = 128;
    public static final int enrol = 64;
    private final ASN1BitString type;

    public EndEntityType(int i) {
        if (i == 128 || i == 64) {
            this.type = new DERBitString(i);
            return;
        }
        throw new IllegalArgumentException("value out of range");
    }

    private EndEntityType(DERBitString dERBitString) {
        this.type = dERBitString;
    }

    public static EndEntityType getInstance(Object obj) {
        if (obj instanceof EndEntityType) {
            return (EndEntityType) obj;
        }
        if (obj == null) {
            return null;
        }
        return new EndEntityType(DERBitString.getInstance(obj));
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.type;
    }
}
