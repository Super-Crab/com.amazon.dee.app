package com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1EncodableVector;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Integer;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Sequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1TaggedObject;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERBitString;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERInteger;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERSequence;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class AttributeCertificateInfo extends ASN1Object {
    private final AttCertValidityPeriod attrCertValidityPeriod;
    private final ASN1Sequence attributes;
    private Extensions extensions;
    private final Holder holder;
    private final AttCertIssuer issuer;
    private DERBitString issuerUniqueID;
    private final ASN1Integer serialNumber;
    private final AlgorithmIdentifier signature;
    private final ASN1Integer version;

    private AttributeCertificateInfo(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() >= 7 && aSN1Sequence.size() <= 9) {
            this.version = DERInteger.getInstance(aSN1Sequence.getObjectAt(0));
            this.holder = Holder.getInstance(aSN1Sequence.getObjectAt(1));
            this.issuer = AttCertIssuer.getInstance(aSN1Sequence.getObjectAt(2));
            this.signature = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(3));
            this.serialNumber = DERInteger.getInstance(aSN1Sequence.getObjectAt(4));
            this.attrCertValidityPeriod = AttCertValidityPeriod.getInstance(aSN1Sequence.getObjectAt(5));
            this.attributes = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(6));
            for (int i = 7; i < aSN1Sequence.size(); i++) {
                ASN1Encodable objectAt = aSN1Sequence.getObjectAt(i);
                if (objectAt instanceof DERBitString) {
                    this.issuerUniqueID = DERBitString.getInstance(aSN1Sequence.getObjectAt(i));
                } else if ((objectAt instanceof ASN1Sequence) || (objectAt instanceof Extensions)) {
                    this.extensions = Extensions.getInstance(aSN1Sequence.getObjectAt(i));
                }
            }
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Bad sequence size: ");
        outline107.append(aSN1Sequence.size());
        throw new IllegalArgumentException(outline107.toString());
    }

    public static AttributeCertificateInfo getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public AttCertValidityPeriod getAttrCertValidityPeriod() {
        return this.attrCertValidityPeriod;
    }

    public ASN1Sequence getAttributes() {
        return this.attributes;
    }

    public Extensions getExtensions() {
        return this.extensions;
    }

    public Holder getHolder() {
        return this.holder;
    }

    public AttCertIssuer getIssuer() {
        return this.issuer;
    }

    public DERBitString getIssuerUniqueID() {
        return this.issuerUniqueID;
    }

    public ASN1Integer getSerialNumber() {
        return this.serialNumber;
    }

    public AlgorithmIdentifier getSignature() {
        return this.signature;
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object, com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.version);
        aSN1EncodableVector.add(this.holder);
        aSN1EncodableVector.add(this.issuer);
        aSN1EncodableVector.add(this.signature);
        aSN1EncodableVector.add(this.serialNumber);
        aSN1EncodableVector.add(this.attrCertValidityPeriod);
        aSN1EncodableVector.add(this.attributes);
        DERBitString dERBitString = this.issuerUniqueID;
        if (dERBitString != null) {
            aSN1EncodableVector.add(dERBitString);
        }
        Extensions extensions = this.extensions;
        if (extensions != null) {
            aSN1EncodableVector.add(extensions);
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public static AttributeCertificateInfo getInstance(Object obj) {
        if (obj instanceof AttributeCertificateInfo) {
            return (AttributeCertificateInfo) obj;
        }
        if (obj == null) {
            return null;
        }
        return new AttributeCertificateInfo(ASN1Sequence.getInstance(obj));
    }
}
