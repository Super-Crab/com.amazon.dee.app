package org.bouncycastle.asn1.esf;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes4.dex */
public class OcspResponsesID extends ASN1Object {
    private OcspIdentifier ocspIdentifier;
    private OtherHash ocspRepHash;

    private OcspResponsesID(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 1 || aSN1Sequence.size() > 2) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline100(aSN1Sequence, GeneratedOutlineSupport1.outline107("Bad sequence size: ")));
        }
        this.ocspIdentifier = OcspIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        if (aSN1Sequence.size() <= 1) {
            return;
        }
        this.ocspRepHash = OtherHash.getInstance(aSN1Sequence.getObjectAt(1));
    }

    public OcspResponsesID(OcspIdentifier ocspIdentifier) {
        this(ocspIdentifier, null);
    }

    public OcspResponsesID(OcspIdentifier ocspIdentifier, OtherHash otherHash) {
        this.ocspIdentifier = ocspIdentifier;
        this.ocspRepHash = otherHash;
    }

    public static OcspResponsesID getInstance(Object obj) {
        if (obj instanceof OcspResponsesID) {
            return (OcspResponsesID) obj;
        }
        if (obj == null) {
            return null;
        }
        return new OcspResponsesID(ASN1Sequence.getInstance(obj));
    }

    public OcspIdentifier getOcspIdentifier() {
        return this.ocspIdentifier;
    }

    public OtherHash getOcspRepHash() {
        return this.ocspRepHash;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(this.ocspIdentifier);
        OtherHash otherHash = this.ocspRepHash;
        if (otherHash != null) {
            aSN1EncodableVector.add(otherHash);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
