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
public class SequenceOfOctetString extends ASN1Object {
    private byte[][] octetStrings;

    private SequenceOfOctetString(ASN1Sequence aSN1Sequence) {
        this.octetStrings = toByteArrays(aSN1Sequence);
    }

    public static SequenceOfOctetString getInstance(Object obj) {
        if (obj instanceof SequenceOfOctetString) {
            return (SequenceOfOctetString) obj;
        }
        if (obj == null) {
            return null;
        }
        return new SequenceOfOctetString(ASN1Sequence.getInstance(obj));
    }

    static byte[][] toByteArrays(ASN1Sequence aSN1Sequence) {
        byte[][] bArr = new byte[aSN1Sequence.size()];
        for (int i = 0; i != aSN1Sequence.size(); i++) {
            bArr[i] = GeneratedOutlineSupport1.outline193(aSN1Sequence, i);
        }
        return bArr;
    }

    public int size() {
        return this.octetStrings.length;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        int i = 0;
        while (true) {
            byte[][] bArr = this.octetStrings;
            if (i != bArr.length) {
                aSN1EncodableVector.add(new DEROctetString(Arrays.clone(bArr[i])));
                i++;
            } else {
                return new DERSequence(aSN1EncodableVector);
            }
        }
    }
}
