package org.bouncycastle.its.asn1;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes4.dex */
public class PsidSspRange extends ASN1Object {
    private ASN1Integer psid;
    private SspRange sspRange;

    public static PsidSspRange getInstance(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof PsidSspRange) {
            return (PsidSspRange) obj;
        }
        ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(obj);
        PsidSspRange psidSspRange = new PsidSspRange();
        if (aSN1Sequence.size() < 1 || aSN1Sequence.size() > 2) {
            throw new IllegalStateException("expected sequences with one or optionally two items");
        }
        if (aSN1Sequence.size() == 1) {
            psidSspRange.psid = (ASN1Integer) aSN1Sequence.getObjectAt(0);
        }
        if (aSN1Sequence.size() == 2) {
            psidSspRange.sspRange = SspRange.getInstance(aSN1Sequence.getObjectAt(1));
        }
        return psidSspRange;
    }

    public ASN1Integer getPsid() {
        return this.psid;
    }

    public SspRange getSspRange() {
        return this.sspRange;
    }

    public void setPsid(ASN1Integer aSN1Integer) {
        this.psid = aSN1Integer;
    }

    public void setSspRange(SspRange sspRange) {
        this.sspRange = sspRange;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.psid);
        SspRange sspRange = this.sspRange;
        if (sspRange != null) {
            aSN1EncodableVector.add(sspRange);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
