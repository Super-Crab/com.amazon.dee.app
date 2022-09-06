package org.bouncycastle.its.asn1;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class BitmapSspRange extends ASN1Object {
    private final byte[] sspBitmask;
    private final byte[] sspValue;

    private BitmapSspRange(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 2) {
            this.sspValue = Utils.octetStringFixed(GeneratedOutlineSupport1.outline193(aSN1Sequence, 0));
            this.sspBitmask = Utils.octetStringFixed(GeneratedOutlineSupport1.outline193(aSN1Sequence, 1));
            return;
        }
        throw new IllegalArgumentException("expected sequence with sspValue and sspBitmask");
    }

    public static BitmapSspRange getInstance(Object obj) {
        if (obj instanceof BitmapSspRange) {
            return (BitmapSspRange) obj;
        }
        if (obj == null) {
            return null;
        }
        return new BitmapSspRange(ASN1Sequence.getInstance(obj));
    }

    public byte[] getSspBitmask() {
        return Arrays.clone(this.sspBitmask);
    }

    public byte[] getSspValue() {
        return Arrays.clone(this.sspValue);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new DEROctetString(this.sspValue));
        aSN1EncodableVector.add(new DEROctetString(this.sspBitmask));
        return new DERSequence(aSN1EncodableVector);
    }
}
