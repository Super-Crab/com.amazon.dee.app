package org.bouncycastle.asn1.x509;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes4.dex */
public class AuthorityInformationAccess extends ASN1Object {
    private AccessDescription[] descriptions;

    public AuthorityInformationAccess(ASN1ObjectIdentifier aSN1ObjectIdentifier, GeneralName generalName) {
        this(new AccessDescription(aSN1ObjectIdentifier, generalName));
    }

    private AuthorityInformationAccess(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() >= 1) {
            this.descriptions = new AccessDescription[aSN1Sequence.size()];
            for (int i = 0; i != aSN1Sequence.size(); i++) {
                this.descriptions[i] = AccessDescription.getInstance(aSN1Sequence.getObjectAt(i));
            }
            return;
        }
        throw new IllegalArgumentException("sequence may not be empty");
    }

    public AuthorityInformationAccess(AccessDescription accessDescription) {
        this.descriptions = new AccessDescription[]{accessDescription};
    }

    public AuthorityInformationAccess(AccessDescription[] accessDescriptionArr) {
        this.descriptions = copy(accessDescriptionArr);
    }

    private static AccessDescription[] copy(AccessDescription[] accessDescriptionArr) {
        AccessDescription[] accessDescriptionArr2 = new AccessDescription[accessDescriptionArr.length];
        System.arraycopy(accessDescriptionArr, 0, accessDescriptionArr2, 0, accessDescriptionArr.length);
        return accessDescriptionArr2;
    }

    public static AuthorityInformationAccess fromExtensions(Extensions extensions) {
        return getInstance(Extensions.getExtensionParsedValue(extensions, Extension.authorityInfoAccess));
    }

    public static AuthorityInformationAccess getInstance(Object obj) {
        if (obj instanceof AuthorityInformationAccess) {
            return (AuthorityInformationAccess) obj;
        }
        if (obj == null) {
            return null;
        }
        return new AuthorityInformationAccess(ASN1Sequence.getInstance(obj));
    }

    public AccessDescription[] getAccessDescriptions() {
        return copy(this.descriptions);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(this.descriptions);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AuthorityInformationAccess: Oid(");
        outline107.append(this.descriptions[0].getAccessMethod().getId());
        outline107.append(")");
        return outline107.toString();
    }
}
