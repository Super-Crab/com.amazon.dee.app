package com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1EncodableVector;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1OctetString;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Sequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1TaggedObject;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERSequence;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class RecipientEncryptedKey extends ASN1Object {
    private final ASN1OctetString encryptedKey;
    private final KeyAgreeRecipientIdentifier identifier;

    private RecipientEncryptedKey(ASN1Sequence aSN1Sequence) {
        this.identifier = KeyAgreeRecipientIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        this.encryptedKey = (ASN1OctetString) aSN1Sequence.getObjectAt(1);
    }

    public static RecipientEncryptedKey getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public ASN1OctetString getEncryptedKey() {
        return this.encryptedKey;
    }

    public KeyAgreeRecipientIdentifier getIdentifier() {
        return this.identifier;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object, com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.identifier);
        aSN1EncodableVector.add(this.encryptedKey);
        return new DERSequence(aSN1EncodableVector);
    }

    public static RecipientEncryptedKey getInstance(Object obj) {
        if (obj != null && !(obj instanceof RecipientEncryptedKey)) {
            if (obj instanceof ASN1Sequence) {
                return new RecipientEncryptedKey((ASN1Sequence) obj);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline45(obj, GeneratedOutlineSupport1.outline107("Invalid RecipientEncryptedKey: ")));
        }
        return (RecipientEncryptedKey) obj;
    }

    public RecipientEncryptedKey(KeyAgreeRecipientIdentifier keyAgreeRecipientIdentifier, ASN1OctetString aSN1OctetString) {
        this.identifier = keyAgreeRecipientIdentifier;
        this.encryptedKey = aSN1OctetString;
    }
}
