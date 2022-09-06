package com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1EncodableVector;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Integer;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1OctetString;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Sequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1TaggedObject;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERSequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERTaggedObject;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class PasswordRecipientInfo extends ASN1Object {
    private final ASN1OctetString encryptedKey;
    private AlgorithmIdentifier keyDerivationAlgorithm;
    private final AlgorithmIdentifier keyEncryptionAlgorithm;
    private final ASN1Integer version;

    public PasswordRecipientInfo(AlgorithmIdentifier algorithmIdentifier, ASN1OctetString aSN1OctetString) {
        this.version = new ASN1Integer(0);
        this.keyEncryptionAlgorithm = algorithmIdentifier;
        this.encryptedKey = aSN1OctetString;
    }

    public static PasswordRecipientInfo getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public ASN1OctetString getEncryptedKey() {
        return this.encryptedKey;
    }

    public AlgorithmIdentifier getKeyDerivationAlgorithm() {
        return this.keyDerivationAlgorithm;
    }

    public AlgorithmIdentifier getKeyEncryptionAlgorithm() {
        return this.keyEncryptionAlgorithm;
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object, com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.version);
        AlgorithmIdentifier algorithmIdentifier = this.keyDerivationAlgorithm;
        if (algorithmIdentifier != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, algorithmIdentifier));
        }
        aSN1EncodableVector.add(this.keyEncryptionAlgorithm);
        aSN1EncodableVector.add(this.encryptedKey);
        return new DERSequence(aSN1EncodableVector);
    }

    public static PasswordRecipientInfo getInstance(Object obj) {
        if (obj != null && !(obj instanceof PasswordRecipientInfo)) {
            if (obj instanceof ASN1Sequence) {
                return new PasswordRecipientInfo((ASN1Sequence) obj);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline45(obj, GeneratedOutlineSupport1.outline107("Invalid PasswordRecipientInfo: ")));
        }
        return (PasswordRecipientInfo) obj;
    }

    public PasswordRecipientInfo(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, ASN1OctetString aSN1OctetString) {
        this.version = new ASN1Integer(0);
        this.keyDerivationAlgorithm = algorithmIdentifier;
        this.keyEncryptionAlgorithm = algorithmIdentifier2;
        this.encryptedKey = aSN1OctetString;
    }

    public PasswordRecipientInfo(ASN1Sequence aSN1Sequence) {
        this.version = (ASN1Integer) aSN1Sequence.getObjectAt(0);
        if (aSN1Sequence.getObjectAt(1) instanceof ASN1TaggedObject) {
            this.keyDerivationAlgorithm = AlgorithmIdentifier.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(1), false);
            this.keyEncryptionAlgorithm = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(2));
            this.encryptedKey = (ASN1OctetString) aSN1Sequence.getObjectAt(3);
            return;
        }
        this.keyEncryptionAlgorithm = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
        this.encryptedKey = (ASN1OctetString) aSN1Sequence.getObjectAt(2);
    }
}
