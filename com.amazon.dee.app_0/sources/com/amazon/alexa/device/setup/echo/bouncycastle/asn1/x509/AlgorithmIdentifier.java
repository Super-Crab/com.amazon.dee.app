package com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1EncodableVector;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Sequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1SequenceParser;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1TaggedObject;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERNull;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERObjectIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERSequence;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class AlgorithmIdentifier extends ASN1Object {
    private final ASN1ObjectIdentifier objectId;
    private ASN1Encodable parameters;
    private boolean parametersDefined;

    public AlgorithmIdentifier(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.parametersDefined = false;
        this.objectId = aSN1ObjectIdentifier;
    }

    public static AlgorithmIdentifier getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public ASN1ObjectIdentifier getAlgorithm() {
        return new ASN1ObjectIdentifier(this.objectId.getId());
    }

    public ASN1ObjectIdentifier getObjectId() {
        return this.objectId;
    }

    public ASN1Encodable getParameters() {
        return this.parameters;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object, com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.objectId);
        if (this.parametersDefined) {
            ASN1Encodable aSN1Encodable = this.parameters;
            if (aSN1Encodable != null) {
                aSN1EncodableVector.add(aSN1Encodable);
            } else {
                aSN1EncodableVector.add(DERNull.INSTANCE);
            }
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public static AlgorithmIdentifier getInstance(Object obj) {
        if (obj != null && !(obj instanceof AlgorithmIdentifier)) {
            if (obj instanceof ASN1ObjectIdentifier) {
                return new AlgorithmIdentifier((ASN1ObjectIdentifier) obj);
            }
            if (obj instanceof String) {
                return new AlgorithmIdentifier((String) obj);
            }
            if (!(obj instanceof ASN1Sequence) && !(obj instanceof ASN1SequenceParser)) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline45(obj, GeneratedOutlineSupport1.outline107("unknown object in factory: ")));
            }
            return new AlgorithmIdentifier(ASN1Sequence.getInstance(obj));
        }
        return (AlgorithmIdentifier) obj;
    }

    public AlgorithmIdentifier(String str) {
        this.parametersDefined = false;
        this.objectId = new ASN1ObjectIdentifier(str);
    }

    public AlgorithmIdentifier(DERObjectIdentifier dERObjectIdentifier) {
        this.parametersDefined = false;
        this.objectId = new ASN1ObjectIdentifier(dERObjectIdentifier.getId());
    }

    public AlgorithmIdentifier(DERObjectIdentifier dERObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.parametersDefined = false;
        this.parametersDefined = true;
        this.objectId = new ASN1ObjectIdentifier(dERObjectIdentifier.getId());
        this.parameters = aSN1Encodable;
    }

    public AlgorithmIdentifier(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.parametersDefined = false;
        this.parametersDefined = true;
        this.objectId = aSN1ObjectIdentifier;
        this.parameters = aSN1Encodable;
    }

    public AlgorithmIdentifier(ASN1Sequence aSN1Sequence) {
        this.parametersDefined = false;
        if (aSN1Sequence.size() >= 1 && aSN1Sequence.size() <= 2) {
            this.objectId = DERObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
            if (aSN1Sequence.size() == 2) {
                this.parametersDefined = true;
                this.parameters = aSN1Sequence.getObjectAt(1);
                return;
            }
            this.parameters = null;
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Bad sequence size: ");
        outline107.append(aSN1Sequence.size());
        throw new IllegalArgumentException(outline107.toString());
    }
}
