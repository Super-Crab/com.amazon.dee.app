package org.bouncycastle.asn1.cmp;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
/* loaded from: classes4.dex */
public class POPODecKeyChallContent extends ASN1Object {
    private ASN1Sequence content;

    private POPODecKeyChallContent(ASN1Sequence aSN1Sequence) {
        this.content = aSN1Sequence;
    }

    public static POPODecKeyChallContent getInstance(Object obj) {
        if (obj instanceof POPODecKeyChallContent) {
            return (POPODecKeyChallContent) obj;
        }
        if (obj == null) {
            return null;
        }
        return new POPODecKeyChallContent(ASN1Sequence.getInstance(obj));
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.content;
    }

    public Challenge[] toChallengeArray() {
        Challenge[] challengeArr = new Challenge[this.content.size()];
        for (int i = 0; i != challengeArr.length; i++) {
            challengeArr[i] = Challenge.getInstance(this.content.getObjectAt(i));
        }
        return challengeArr;
    }
}
