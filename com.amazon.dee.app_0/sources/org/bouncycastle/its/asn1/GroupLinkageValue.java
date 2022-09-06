package org.bouncycastle.its.asn1;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes4.dex */
public class GroupLinkageValue extends ASN1Object {
    private byte[] jValue;
    private byte[] value;

    private GroupLinkageValue(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 2) {
            this.jValue = Utils.octetStringFixed(GeneratedOutlineSupport1.outline193(aSN1Sequence, 0), 4);
            this.value = Utils.octetStringFixed(GeneratedOutlineSupport1.outline193(aSN1Sequence, 1), 9);
            return;
        }
        throw new IllegalArgumentException("sequence not length 2");
    }

    public static GroupLinkageValue getInstance(Object obj) {
        if (obj instanceof GroupLinkageValue) {
            return (GroupLinkageValue) obj;
        }
        if (obj == null) {
            return null;
        }
        return new GroupLinkageValue(ASN1Sequence.getInstance(obj));
    }

    public byte[] getJValue() {
        return this.jValue;
    }

    public byte[] getValue() {
        return this.value;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new DEROctetString(this.jValue));
        aSN1EncodableVector.add(new DEROctetString(this.value));
        return new DERSequence(aSN1EncodableVector);
    }
}
