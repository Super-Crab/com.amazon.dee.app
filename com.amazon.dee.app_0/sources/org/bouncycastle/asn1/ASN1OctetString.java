package org.bouncycastle.asn1;

import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttTopic;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes4.dex */
public abstract class ASN1OctetString extends ASN1Primitive implements ASN1OctetStringParser {
    byte[] string;

    public ASN1OctetString(byte[] bArr) {
        if (bArr != null) {
            this.string = bArr;
            return;
        }
        throw new NullPointerException("'string' cannot be null");
    }

    public static ASN1OctetString getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1OctetString)) {
            return (ASN1OctetString) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return getInstance(ASN1Primitive.fromByteArray((byte[]) obj));
            } catch (IOException e) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("failed to construct OCTET STRING from byte[]: ")));
            }
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
            if (aSN1Primitive instanceof ASN1OctetString) {
                return (ASN1OctetString) aSN1Primitive;
            }
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline45(obj, GeneratedOutlineSupport1.outline107("illegal object in getInstance: ")));
    }

    public static ASN1OctetString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        if (z) {
            if (!aSN1TaggedObject.isExplicit()) {
                throw new IllegalArgumentException("object implicit - explicit expected.");
            }
            return getInstance(aSN1TaggedObject.getObject());
        }
        ASN1Primitive object = aSN1TaggedObject.getObject();
        if (aSN1TaggedObject.isExplicit()) {
            ASN1OctetString aSN1OctetString = getInstance(object);
            return aSN1TaggedObject instanceof BERTaggedObject ? new BEROctetString(new ASN1OctetString[]{aSN1OctetString}) : (ASN1OctetString) new BEROctetString(new ASN1OctetString[]{aSN1OctetString}).toDLObject();
        } else if (object instanceof ASN1OctetString) {
            ASN1OctetString aSN1OctetString2 = (ASN1OctetString) object;
            return aSN1TaggedObject instanceof BERTaggedObject ? aSN1OctetString2 : (ASN1OctetString) aSN1OctetString2.toDLObject();
        } else if (object instanceof ASN1Sequence) {
            ASN1Sequence aSN1Sequence = (ASN1Sequence) object;
            return aSN1TaggedObject instanceof BERTaggedObject ? BEROctetString.fromSequence(aSN1Sequence) : (ASN1OctetString) BEROctetString.fromSequence(aSN1Sequence).toDLObject();
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unknown object in getInstance: ");
            outline107.append(aSN1TaggedObject.getClass().getName());
            throw new IllegalArgumentException(outline107.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1OctetString)) {
            return false;
        }
        return Arrays.areEqual(this.string, ((ASN1OctetString) aSN1Primitive).string);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public abstract void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException;

    @Override // org.bouncycastle.asn1.InMemoryRepresentable
    public ASN1Primitive getLoadedObject() {
        return toASN1Primitive();
    }

    @Override // org.bouncycastle.asn1.ASN1OctetStringParser
    public InputStream getOctetStream() {
        return new ByteArrayInputStream(this.string);
    }

    public byte[] getOctets() {
        return this.string;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        return Arrays.hashCode(getOctets());
    }

    public ASN1OctetStringParser parser() {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive toDERObject() {
        return new DEROctetString(this.string);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive toDLObject() {
        return new DEROctetString(this.string);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(MqttTopic.MULTI_LEVEL_WILDCARD);
        outline107.append(Strings.fromByteArray(Hex.encode(this.string)));
        return outline107.toString();
    }
}
