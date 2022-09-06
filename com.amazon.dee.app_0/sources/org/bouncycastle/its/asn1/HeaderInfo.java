package org.bouncycastle.its.asn1;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes4.dex */
public class HeaderInfo extends ASN1Object {
    private HeaderInfo(ASN1Sequence aSN1Sequence) {
    }

    public static HeaderInfo getInstance(Object obj) {
        if (obj instanceof HeaderInfo) {
            return (HeaderInfo) obj;
        }
        if (obj == null) {
            return null;
        }
        return new HeaderInfo(ASN1Sequence.getInstance(obj));
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(new ASN1EncodableVector());
    }
}
