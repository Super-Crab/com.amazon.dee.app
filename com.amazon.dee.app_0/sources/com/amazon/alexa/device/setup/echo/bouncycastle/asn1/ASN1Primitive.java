package com.amazon.alexa.device.setup.echo.bouncycastle.asn1;

import java.io.IOException;
/* loaded from: classes.dex */
public abstract class ASN1Primitive extends ASN1Object {
    public static ASN1Primitive fromByteArray(byte[] bArr) throws IOException {
        try {
            return new ASN1InputStream(bArr).readObject();
        } catch (ClassCastException unused) {
            throw new IOException("cannot recognise object in stream");
        }
    }

    abstract boolean asn1Equals(ASN1Primitive aSN1Primitive);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void encode(ASN1OutputStream aSN1OutputStream) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int encodedLength() throws IOException;

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ASN1Encodable) && asn1Equals(((ASN1Encodable) obj).toASN1Primitive());
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object
    public abstract int hashCode();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean isConstructed();

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object, com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Primitive toDERObject() {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Primitive toDLObject() {
        return this;
    }
}
