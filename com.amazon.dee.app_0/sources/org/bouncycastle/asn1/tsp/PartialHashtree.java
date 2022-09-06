package org.bouncycastle.asn1.tsp;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class PartialHashtree extends ASN1Object {
    private ASN1Sequence values;

    private PartialHashtree(ASN1Sequence aSN1Sequence) {
        for (int i = 0; i != aSN1Sequence.size(); i++) {
            if (!(aSN1Sequence.getObjectAt(i) instanceof DEROctetString)) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unknown object in constructor: ");
                outline107.append(aSN1Sequence.getObjectAt(i).getClass().getName());
                throw new IllegalArgumentException(outline107.toString());
            }
        }
        this.values = aSN1Sequence;
    }

    public PartialHashtree(byte[] bArr) {
        this(new byte[][]{bArr});
    }

    public PartialHashtree(byte[][] bArr) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(bArr.length);
        for (int i = 0; i != bArr.length; i++) {
            aSN1EncodableVector.add(new DEROctetString(Arrays.clone(bArr[i])));
        }
        this.values = new DERSequence(aSN1EncodableVector);
    }

    public static PartialHashtree getInstance(Object obj) {
        if (obj instanceof PartialHashtree) {
            return (PartialHashtree) obj;
        }
        if (obj == null) {
            return null;
        }
        return new PartialHashtree(ASN1Sequence.getInstance(obj));
    }

    public byte[][] getValues() {
        byte[][] bArr = new byte[this.values.size()];
        for (int i = 0; i != bArr.length; i++) {
            bArr[i] = GeneratedOutlineSupport1.outline194(this.values, i);
        }
        return bArr;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.values;
    }
}
