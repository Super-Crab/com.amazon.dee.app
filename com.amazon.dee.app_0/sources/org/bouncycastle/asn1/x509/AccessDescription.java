package org.bouncycastle.asn1.x509;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes4.dex */
public class AccessDescription extends ASN1Object {
    public static final ASN1ObjectIdentifier id_ad_caIssuers = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.48.2");
    public static final ASN1ObjectIdentifier id_ad_ocsp = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.48.1");
    GeneralName accessLocation;
    ASN1ObjectIdentifier accessMethod;

    public AccessDescription(ASN1ObjectIdentifier aSN1ObjectIdentifier, GeneralName generalName) {
        this.accessMethod = null;
        this.accessLocation = null;
        this.accessMethod = aSN1ObjectIdentifier;
        this.accessLocation = generalName;
    }

    private AccessDescription(ASN1Sequence aSN1Sequence) {
        this.accessMethod = null;
        this.accessLocation = null;
        if (aSN1Sequence.size() == 2) {
            this.accessMethod = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
            this.accessLocation = GeneralName.getInstance(aSN1Sequence.getObjectAt(1));
            return;
        }
        throw new IllegalArgumentException("wrong number of elements in sequence");
    }

    public static AccessDescription getInstance(Object obj) {
        if (obj instanceof AccessDescription) {
            return (AccessDescription) obj;
        }
        if (obj == null) {
            return null;
        }
        return new AccessDescription(ASN1Sequence.getInstance(obj));
    }

    public GeneralName getAccessLocation() {
        return this.accessLocation;
    }

    public ASN1ObjectIdentifier getAccessMethod() {
        return this.accessMethod;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(this.accessMethod);
        aSN1EncodableVector.add(this.accessLocation);
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AccessDescription: Oid(");
        outline107.append(this.accessMethod.getId());
        outline107.append(")");
        return outline107.toString();
    }
}