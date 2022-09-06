package com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1EncodableVector;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Sequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1TaggedObject;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERBitString;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERSequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class OriginatorPublicKey extends ASN1Object {
    private final AlgorithmIdentifier algorithm;
    private final DERBitString publicKey;

    public OriginatorPublicKey(AlgorithmIdentifier algorithmIdentifier, byte[] bArr) {
        this.algorithm = algorithmIdentifier;
        this.publicKey = new DERBitString(bArr);
    }

    public static OriginatorPublicKey getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public AlgorithmIdentifier getAlgorithm() {
        return this.algorithm;
    }

    public DERBitString getPublicKey() {
        return this.publicKey;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object, com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.algorithm);
        aSN1EncodableVector.add(this.publicKey);
        return new DERSequence(aSN1EncodableVector);
    }

    public static OriginatorPublicKey getInstance(Object obj) {
        if (obj != null && !(obj instanceof OriginatorPublicKey)) {
            if (obj instanceof ASN1Sequence) {
                return new OriginatorPublicKey((ASN1Sequence) obj);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline45(obj, GeneratedOutlineSupport1.outline107("Invalid OriginatorPublicKey: ")));
        }
        return (OriginatorPublicKey) obj;
    }

    public OriginatorPublicKey(ASN1Sequence aSN1Sequence) {
        this.algorithm = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        this.publicKey = (DERBitString) aSN1Sequence.getObjectAt(1);
    }
}
