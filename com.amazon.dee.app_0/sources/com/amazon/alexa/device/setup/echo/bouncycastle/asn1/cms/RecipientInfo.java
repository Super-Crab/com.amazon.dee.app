package com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Choice;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Integer;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Sequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1TaggedObject;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERTaggedObject;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class RecipientInfo extends ASN1Object implements ASN1Choice {
    ASN1Encodable info;

    public RecipientInfo(KeyTransRecipientInfo keyTransRecipientInfo) {
        this.info = keyTransRecipientInfo;
    }

    public static RecipientInfo getInstance(Object obj) {
        if (obj != null && !(obj instanceof RecipientInfo)) {
            if (obj instanceof ASN1Sequence) {
                return new RecipientInfo((ASN1Sequence) obj);
            }
            if (obj instanceof ASN1TaggedObject) {
                return new RecipientInfo((ASN1TaggedObject) obj);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline45(obj, GeneratedOutlineSupport1.outline107("unknown object in factory: ")));
        }
        return (RecipientInfo) obj;
    }

    private KEKRecipientInfo getKEKInfo(ASN1TaggedObject aSN1TaggedObject) {
        if (aSN1TaggedObject.isExplicit()) {
            return KEKRecipientInfo.getInstance(aSN1TaggedObject, true);
        }
        return KEKRecipientInfo.getInstance(aSN1TaggedObject, false);
    }

    public ASN1Encodable getInfo() {
        ASN1Encodable aSN1Encodable = this.info;
        if (aSN1Encodable instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Encodable;
            int tagNo = aSN1TaggedObject.getTagNo();
            if (tagNo == 1) {
                return KeyAgreeRecipientInfo.getInstance(aSN1TaggedObject, false);
            }
            if (tagNo == 2) {
                return getKEKInfo(aSN1TaggedObject);
            }
            if (tagNo == 3) {
                return PasswordRecipientInfo.getInstance(aSN1TaggedObject, false);
            }
            if (tagNo == 4) {
                return OtherRecipientInfo.getInstance(aSN1TaggedObject, false);
            }
            throw new IllegalStateException("unknown tag");
        }
        return KeyTransRecipientInfo.getInstance(aSN1Encodable);
    }

    public ASN1Integer getVersion() {
        ASN1Encodable aSN1Encodable = this.info;
        if (aSN1Encodable instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Encodable;
            int tagNo = aSN1TaggedObject.getTagNo();
            if (tagNo == 1) {
                return KeyAgreeRecipientInfo.getInstance(aSN1TaggedObject, false).getVersion();
            }
            if (tagNo == 2) {
                return getKEKInfo(aSN1TaggedObject).getVersion();
            }
            if (tagNo == 3) {
                return PasswordRecipientInfo.getInstance(aSN1TaggedObject, false).getVersion();
            }
            if (tagNo == 4) {
                return new ASN1Integer(0);
            }
            throw new IllegalStateException("unknown tag");
        }
        return KeyTransRecipientInfo.getInstance(aSN1Encodable).getVersion();
    }

    public boolean isTagged() {
        return this.info instanceof ASN1TaggedObject;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object, com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.info.toASN1Primitive();
    }

    public RecipientInfo(KeyAgreeRecipientInfo keyAgreeRecipientInfo) {
        this.info = new DERTaggedObject(false, 1, keyAgreeRecipientInfo);
    }

    public RecipientInfo(KEKRecipientInfo kEKRecipientInfo) {
        this.info = new DERTaggedObject(false, 2, kEKRecipientInfo);
    }

    public RecipientInfo(PasswordRecipientInfo passwordRecipientInfo) {
        this.info = new DERTaggedObject(false, 3, passwordRecipientInfo);
    }

    public RecipientInfo(OtherRecipientInfo otherRecipientInfo) {
        this.info = new DERTaggedObject(false, 4, otherRecipientInfo);
    }

    public RecipientInfo(ASN1Primitive aSN1Primitive) {
        this.info = aSN1Primitive;
    }
}
