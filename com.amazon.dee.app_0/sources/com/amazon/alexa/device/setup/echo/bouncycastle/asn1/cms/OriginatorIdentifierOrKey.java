package com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Choice;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1OctetString;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1TaggedObject;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERTaggedObject;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class OriginatorIdentifierOrKey extends ASN1Object implements ASN1Choice {
    private final ASN1Encodable id;

    public OriginatorIdentifierOrKey(IssuerAndSerialNumber issuerAndSerialNumber) {
        this.id = issuerAndSerialNumber;
    }

    public static OriginatorIdentifierOrKey getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        if (z) {
            return getInstance(aSN1TaggedObject.getObject());
        }
        throw new IllegalArgumentException("Can't implicitly tag OriginatorIdentifierOrKey");
    }

    public ASN1Encodable getId() {
        return this.id;
    }

    public IssuerAndSerialNumber getIssuerAndSerialNumber() {
        ASN1Encodable aSN1Encodable = this.id;
        if (aSN1Encodable instanceof IssuerAndSerialNumber) {
            return (IssuerAndSerialNumber) aSN1Encodable;
        }
        return null;
    }

    public OriginatorPublicKey getOriginatorKey() {
        ASN1Encodable aSN1Encodable = this.id;
        if (!(aSN1Encodable instanceof ASN1TaggedObject) || ((ASN1TaggedObject) aSN1Encodable).getTagNo() != 1) {
            return null;
        }
        return OriginatorPublicKey.getInstance((ASN1TaggedObject) this.id, false);
    }

    public SubjectKeyIdentifier getSubjectKeyIdentifier() {
        ASN1Encodable aSN1Encodable = this.id;
        if (!(aSN1Encodable instanceof ASN1TaggedObject) || ((ASN1TaggedObject) aSN1Encodable).getTagNo() != 0) {
            return null;
        }
        return SubjectKeyIdentifier.getInstance((ASN1TaggedObject) this.id, false);
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object, com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.id.toASN1Primitive();
    }

    public OriginatorIdentifierOrKey(ASN1OctetString aSN1OctetString) {
        this(new SubjectKeyIdentifier(aSN1OctetString.getOctets()));
    }

    public static OriginatorIdentifierOrKey getInstance(Object obj) {
        if (obj != null && !(obj instanceof OriginatorIdentifierOrKey)) {
            if (obj instanceof IssuerAndSerialNumber) {
                return new OriginatorIdentifierOrKey((IssuerAndSerialNumber) obj);
            }
            if (obj instanceof SubjectKeyIdentifier) {
                return new OriginatorIdentifierOrKey((SubjectKeyIdentifier) obj);
            }
            if (obj instanceof OriginatorPublicKey) {
                return new OriginatorIdentifierOrKey((OriginatorPublicKey) obj);
            }
            if (obj instanceof ASN1TaggedObject) {
                return new OriginatorIdentifierOrKey((ASN1TaggedObject) obj);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline45(obj, GeneratedOutlineSupport1.outline107("Invalid OriginatorIdentifierOrKey: ")));
        }
        return (OriginatorIdentifierOrKey) obj;
    }

    public OriginatorIdentifierOrKey(SubjectKeyIdentifier subjectKeyIdentifier) {
        this.id = new DERTaggedObject(false, 0, subjectKeyIdentifier);
    }

    public OriginatorIdentifierOrKey(OriginatorPublicKey originatorPublicKey) {
        this.id = new DERTaggedObject(false, 1, originatorPublicKey);
    }

    public OriginatorIdentifierOrKey(ASN1Primitive aSN1Primitive) {
        this.id = aSN1Primitive;
    }
}
