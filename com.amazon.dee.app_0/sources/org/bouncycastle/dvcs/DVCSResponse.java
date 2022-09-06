package org.bouncycastle.dvcs;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.SignedData;
import org.bouncycastle.asn1.dvcs.DVCSObjectIdentifiers;
import org.bouncycastle.cms.CMSSignedData;
/* loaded from: classes4.dex */
public class DVCSResponse extends DVCSMessage {
    private org.bouncycastle.asn1.dvcs.DVCSResponse asn1;

    public DVCSResponse(ContentInfo contentInfo) throws DVCSConstructionException {
        super(contentInfo);
        if (DVCSObjectIdentifiers.id_ct_DVCSResponseData.equals((ASN1Primitive) contentInfo.getContentType())) {
            try {
                this.asn1 = contentInfo.getContent().toASN1Primitive() instanceof ASN1Sequence ? org.bouncycastle.asn1.dvcs.DVCSResponse.getInstance(contentInfo.getContent()) : org.bouncycastle.asn1.dvcs.DVCSResponse.getInstance(ASN1OctetString.getInstance(contentInfo.getContent()).getOctets());
                return;
            } catch (Exception e) {
                throw new DVCSConstructionException(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("Unable to parse content: ")), e);
            }
        }
        throw new DVCSConstructionException("ContentInfo not a DVCS Response");
    }

    public DVCSResponse(CMSSignedData cMSSignedData) throws DVCSConstructionException {
        this(SignedData.getInstance(cMSSignedData.toASN1Structure().getContent()).getEncapContentInfo());
    }

    @Override // org.bouncycastle.dvcs.DVCSMessage
    public ASN1Encodable getContent() {
        return this.asn1;
    }
}
