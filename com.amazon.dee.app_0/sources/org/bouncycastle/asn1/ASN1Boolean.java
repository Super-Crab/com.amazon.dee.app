package org.bouncycastle.asn1;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
/* loaded from: classes4.dex */
public class ASN1Boolean extends ASN1Primitive {
    private static final byte FALSE_VALUE = 0;
    private static final byte TRUE_VALUE = -1;
    private final byte value;
    public static final ASN1Boolean FALSE = new ASN1Boolean((byte) 0);
    public static final ASN1Boolean TRUE = new ASN1Boolean((byte) -1);

    private ASN1Boolean(byte b) {
        this.value = b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1Boolean fromOctetString(byte[] bArr) {
        if (bArr.length == 1) {
            byte b = bArr[0];
            return b != -1 ? b != 0 ? new ASN1Boolean(b) : FALSE : TRUE;
        }
        throw new IllegalArgumentException("BOOLEAN value should have 1 byte in it");
    }

    public static ASN1Boolean getInstance(int i) {
        return i != 0 ? TRUE : FALSE;
    }

    public static ASN1Boolean getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1Boolean)) {
            return (ASN1Boolean) obj;
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline45(obj, GeneratedOutlineSupport1.outline107("illegal object in getInstance: ")));
        }
        try {
            return (ASN1Boolean) ASN1Primitive.fromByteArray((byte[]) obj);
        } catch (IOException e) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("failed to construct boolean from byte[]: ")));
        }
    }

    public static ASN1Boolean getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive object = aSN1TaggedObject.getObject();
        return (z || (object instanceof ASN1Boolean)) ? getInstance(object) : fromOctetString(ASN1OctetString.getInstance(object).getOctets());
    }

    public static ASN1Boolean getInstance(boolean z) {
        return z ? TRUE : FALSE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        return (aSN1Primitive instanceof ASN1Boolean) && isTrue() == ((ASN1Boolean) aSN1Primitive).isTrue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException {
        aSN1OutputStream.writeEncoded(z, 1, this.value);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int encodedLength() {
        return 3;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        return isTrue() ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        return false;
    }

    public boolean isTrue() {
        return this.value != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive toDERObject() {
        return isTrue() ? TRUE : FALSE;
    }

    public String toString() {
        return isTrue() ? "TRUE" : "FALSE";
    }
}
