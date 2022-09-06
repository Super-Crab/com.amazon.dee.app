package org.bouncycastle.its.asn1;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes4.dex */
public class LinkageData extends ASN1Object {
    private final GroupLinkageValue groupLinkageValue;
    private final IValue iCert;
    private final LinkageValue linkageValue;

    private LinkageData(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 2 || aSN1Sequence.size() == 3) {
            this.iCert = IValue.getInstance(aSN1Sequence.getObjectAt(2));
            this.linkageValue = LinkageValue.getInstance(aSN1Sequence.getObjectAt(2));
            this.groupLinkageValue = GroupLinkageValue.getInstance(aSN1Sequence.getObjectAt(2));
            return;
        }
        throw new IllegalArgumentException("sequence must be size 2 or 3");
    }

    public static LinkageData getInstance(Object obj) {
        if (obj instanceof LinkageData) {
            return (LinkageData) obj;
        }
        if (obj == null) {
            return null;
        }
        return new LinkageData(ASN1Sequence.getInstance(obj));
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(new ASN1EncodableVector());
    }
}
