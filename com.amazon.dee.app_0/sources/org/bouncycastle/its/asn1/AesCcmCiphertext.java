package org.bouncycastle.its.asn1;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes4.dex */
public class AesCcmCiphertext extends ASN1Object {
    private final byte[] nonce;
    private final SequenceOfOctetString opaque;

    private AesCcmCiphertext(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 2) {
            this.nonce = Utils.octetStringFixed(GeneratedOutlineSupport1.outline193(aSN1Sequence, 0), 12);
            this.opaque = SequenceOfOctetString.getInstance(aSN1Sequence.getObjectAt(1));
            return;
        }
        throw new IllegalArgumentException("sequence not length 2");
    }

    public static AesCcmCiphertext getInstance(Object obj) {
        if (obj instanceof AesCcmCiphertext) {
            return (AesCcmCiphertext) obj;
        }
        if (obj == null) {
            return null;
        }
        return new AesCcmCiphertext(ASN1Sequence.getInstance(obj));
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new DEROctetString(this.nonce));
        aSN1EncodableVector.add(this.opaque);
        return new DERSequence(aSN1EncodableVector);
    }
}
