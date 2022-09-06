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
public class KeyAgreeRecipientInfo extends ASN1Object {
    private final AlgorithmIdentifier keyEncryptionAlgorithm;
    private final OriginatorIdentifierOrKey originator;
    private final ASN1Sequence recipientEncryptedKeys;
    private ASN1OctetString ukm;
    private final ASN1Integer version;

    public KeyAgreeRecipientInfo(OriginatorIdentifierOrKey originatorIdentifierOrKey, ASN1OctetString aSN1OctetString, AlgorithmIdentifier algorithmIdentifier, ASN1Sequence aSN1Sequence) {
        this.version = new ASN1Integer(3);
        this.originator = originatorIdentifierOrKey;
        this.ukm = aSN1OctetString;
        this.keyEncryptionAlgorithm = algorithmIdentifier;
        this.recipientEncryptedKeys = aSN1Sequence;
    }

    public static KeyAgreeRecipientInfo getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public AlgorithmIdentifier getKeyEncryptionAlgorithm() {
        return this.keyEncryptionAlgorithm;
    }

    public OriginatorIdentifierOrKey getOriginator() {
        return this.originator;
    }

    public ASN1Sequence getRecipientEncryptedKeys() {
        return this.recipientEncryptedKeys;
    }

    public ASN1OctetString getUserKeyingMaterial() {
        return this.ukm;
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object, com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.version);
        aSN1EncodableVector.add(new DERTaggedObject(true, 0, this.originator));
        ASN1OctetString aSN1OctetString = this.ukm;
        if (aSN1OctetString != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 1, aSN1OctetString));
        }
        aSN1EncodableVector.add(this.keyEncryptionAlgorithm);
        aSN1EncodableVector.add(this.recipientEncryptedKeys);
        return new DERSequence(aSN1EncodableVector);
    }

    public static KeyAgreeRecipientInfo getInstance(Object obj) {
        if (obj != null && !(obj instanceof KeyAgreeRecipientInfo)) {
            if (obj instanceof ASN1Sequence) {
                return new KeyAgreeRecipientInfo((ASN1Sequence) obj);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline45(obj, GeneratedOutlineSupport1.outline107("Illegal object in KeyAgreeRecipientInfo: ")));
        }
        return (KeyAgreeRecipientInfo) obj;
    }

    public KeyAgreeRecipientInfo(ASN1Sequence aSN1Sequence) {
        this.version = (ASN1Integer) aSN1Sequence.getObjectAt(0);
        this.originator = OriginatorIdentifierOrKey.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(1), true);
        int i = 2;
        if (aSN1Sequence.getObjectAt(2) instanceof ASN1TaggedObject) {
            this.ukm = ASN1OctetString.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(2), true);
            i = 3;
        }
        this.keyEncryptionAlgorithm = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(i));
        this.recipientEncryptedKeys = (ASN1Sequence) aSN1Sequence.getObjectAt(i + 1);
    }
}
