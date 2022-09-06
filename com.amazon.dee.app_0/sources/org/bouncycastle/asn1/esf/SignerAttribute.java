package org.bouncycastle.asn1.esf;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.Attribute;
import org.bouncycastle.asn1.x509.AttributeCertificate;
/* loaded from: classes4.dex */
public class SignerAttribute extends ASN1Object {
    private Object[] values;

    private SignerAttribute(ASN1Sequence aSN1Sequence) {
        this.values = new Object[aSN1Sequence.size()];
        Enumeration objects = aSN1Sequence.getObjects();
        int i = 0;
        while (objects.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(objects.nextElement());
            if (aSN1TaggedObject.getTagNo() == 0) {
                ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(aSN1TaggedObject, true);
                Attribute[] attributeArr = new Attribute[aSN1Sequence2.size()];
                for (int i2 = 0; i2 != attributeArr.length; i2++) {
                    attributeArr[i2] = Attribute.getInstance(aSN1Sequence2.getObjectAt(i2));
                }
                this.values[i] = attributeArr;
            } else if (aSN1TaggedObject.getTagNo() != 1) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline101(aSN1TaggedObject, GeneratedOutlineSupport1.outline107("illegal tag: ")));
            } else {
                this.values[i] = AttributeCertificate.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, true));
            }
            i++;
        }
    }

    public SignerAttribute(AttributeCertificate attributeCertificate) {
        this.values = new Object[1];
        this.values[0] = attributeCertificate;
    }

    public SignerAttribute(Attribute[] attributeArr) {
        this.values = new Object[1];
        this.values[0] = attributeArr;
    }

    public static SignerAttribute getInstance(Object obj) {
        if (obj instanceof SignerAttribute) {
            return (SignerAttribute) obj;
        }
        if (obj == null) {
            return null;
        }
        return new SignerAttribute(ASN1Sequence.getInstance(obj));
    }

    public Object[] getValues() {
        Object[] objArr = this.values;
        Object[] objArr2 = new Object[objArr.length];
        System.arraycopy(objArr, 0, objArr2, 0, objArr2.length);
        return objArr2;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(this.values.length);
        int i = 0;
        while (true) {
            Object[] objArr = this.values;
            if (i != objArr.length) {
                aSN1EncodableVector.add(objArr[i] instanceof Attribute[] ? new DERTaggedObject(0, new DERSequence((Attribute[]) objArr[i])) : new DERTaggedObject(1, (AttributeCertificate) objArr[i]));
                i++;
            } else {
                return new DERSequence(aSN1EncodableVector);
            }
        }
    }
}
