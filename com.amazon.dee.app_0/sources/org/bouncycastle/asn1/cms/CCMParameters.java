package org.bouncycastle.asn1.cms;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class CCMParameters extends ASN1Object {
    private int icvLen;
    private byte[] nonce;

    private CCMParameters(ASN1Sequence aSN1Sequence) {
        this.nonce = GeneratedOutlineSupport1.outline193(aSN1Sequence, 0);
        this.icvLen = aSN1Sequence.size() == 2 ? ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1)).intValueExact() : 12;
    }

    public CCMParameters(byte[] bArr, int i) {
        this.nonce = Arrays.clone(bArr);
        this.icvLen = i;
    }

    public static CCMParameters getInstance(Object obj) {
        if (obj instanceof CCMParameters) {
            return (CCMParameters) obj;
        }
        if (obj == null) {
            return null;
        }
        return new CCMParameters(ASN1Sequence.getInstance(obj));
    }

    public int getIcvLen() {
        return this.icvLen;
    }

    public byte[] getNonce() {
        return Arrays.clone(this.nonce);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(new DEROctetString(this.nonce));
        int i = this.icvLen;
        if (i != 12) {
            aSN1EncodableVector.add(new ASN1Integer(i));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
