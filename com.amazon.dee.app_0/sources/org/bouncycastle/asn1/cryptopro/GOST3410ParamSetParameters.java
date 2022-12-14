package org.bouncycastle.asn1.cryptopro;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes4.dex */
public class GOST3410ParamSetParameters extends ASN1Object {
    ASN1Integer a;
    int keySize;
    ASN1Integer p;
    ASN1Integer q;

    public GOST3410ParamSetParameters(int i, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.keySize = i;
        this.p = new ASN1Integer(bigInteger);
        this.q = new ASN1Integer(bigInteger2);
        this.a = new ASN1Integer(bigInteger3);
    }

    public GOST3410ParamSetParameters(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.keySize = ((ASN1Integer) objects.nextElement()).intValueExact();
        this.p = (ASN1Integer) objects.nextElement();
        this.q = (ASN1Integer) objects.nextElement();
        this.a = (ASN1Integer) objects.nextElement();
    }

    public static GOST3410ParamSetParameters getInstance(Object obj) {
        if (obj == null || (obj instanceof GOST3410ParamSetParameters)) {
            return (GOST3410ParamSetParameters) obj;
        }
        if (!(obj instanceof ASN1Sequence)) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline45(obj, GeneratedOutlineSupport1.outline107("Invalid GOST3410Parameter: ")));
        }
        return new GOST3410ParamSetParameters((ASN1Sequence) obj);
    }

    public static GOST3410ParamSetParameters getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public BigInteger getA() {
        return this.a.getPositiveValue();
    }

    public int getKeySize() {
        return this.keySize;
    }

    public int getLKeySize() {
        return this.keySize;
    }

    public BigInteger getP() {
        return this.p.getPositiveValue();
    }

    public BigInteger getQ() {
        return this.q.getPositiveValue();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(4);
        aSN1EncodableVector.add(new ASN1Integer(this.keySize));
        aSN1EncodableVector.add(this.p);
        aSN1EncodableVector.add(this.q);
        aSN1EncodableVector.add(this.a);
        return new DERSequence(aSN1EncodableVector);
    }
}
