package com.amazon.alexa.device.setup.echo.bouncycastle.asn1;

import com.amazon.alexa.device.setup.echo.bouncycastle.util.Arrays;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.math.BigInteger;
/* loaded from: classes.dex */
public class DERInteger extends ASN1Primitive {
    byte[] bytes;

    public DERInteger(int i) {
        this.bytes = BigInteger.valueOf(i).toByteArray();
    }

    public static ASN1Integer getInstance(Object obj) {
        if (obj != null && !(obj instanceof ASN1Integer)) {
            if (obj instanceof DERInteger) {
                return new ASN1Integer(((DERInteger) obj).getValue());
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline45(obj, GeneratedOutlineSupport1.outline107("illegal object in getInstance: ")));
        }
        return (ASN1Integer) obj;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive
    boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERInteger)) {
            return false;
        }
        return Arrays.areEqual(this.bytes, ((DERInteger) aSN1Primitive).bytes);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.writeEncoded(2, this.bytes);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive
    public int encodedLength() {
        return StreamUtil.calculateBodyLength(this.bytes.length) + 1 + this.bytes.length;
    }

    public BigInteger getPositiveValue() {
        return new BigInteger(1, this.bytes);
    }

    public BigInteger getValue() {
        return new BigInteger(this.bytes);
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive, com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.bytes;
            if (i != bArr.length) {
                i2 ^= (bArr[i] & 255) << (i % 4);
                i++;
            } else {
                return i2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        return false;
    }

    public String toString() {
        return getValue().toString();
    }

    public DERInteger(BigInteger bigInteger) {
        this.bytes = bigInteger.toByteArray();
    }

    public DERInteger(byte[] bArr) {
        this.bytes = bArr;
    }

    public static ASN1Integer getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive object = aSN1TaggedObject.getObject();
        if (!z && !(object instanceof DERInteger)) {
            return new ASN1Integer(ASN1OctetString.getInstance(aSN1TaggedObject.getObject()).getOctets());
        }
        return getInstance(object);
    }
}
