package com.amazon.alexa.device.setup.echo.bouncycastle.asn1.pkcs;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1EncodableVector;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Integer;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1OctetString;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Sequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DEROctetString;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERSequence;
import java.math.BigInteger;
/* loaded from: classes.dex */
public class RC2CBCParameter extends ASN1Object {
    ASN1OctetString iv;
    ASN1Integer version;

    public RC2CBCParameter(byte[] bArr) {
        this.version = null;
        this.iv = new DEROctetString(bArr);
    }

    public static RC2CBCParameter getInstance(Object obj) {
        if (obj instanceof RC2CBCParameter) {
            return (RC2CBCParameter) obj;
        }
        if (obj == null) {
            return null;
        }
        return new RC2CBCParameter(ASN1Sequence.getInstance(obj));
    }

    public byte[] getIV() {
        return this.iv.getOctets();
    }

    public BigInteger getRC2ParameterVersion() {
        ASN1Integer aSN1Integer = this.version;
        if (aSN1Integer == null) {
            return null;
        }
        return aSN1Integer.getValue();
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object, com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1Integer aSN1Integer = this.version;
        if (aSN1Integer != null) {
            aSN1EncodableVector.add(aSN1Integer);
        }
        aSN1EncodableVector.add(this.iv);
        return new DERSequence(aSN1EncodableVector);
    }

    public RC2CBCParameter(int i, byte[] bArr) {
        this.version = new ASN1Integer(i);
        this.iv = new DEROctetString(bArr);
    }

    private RC2CBCParameter(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 1) {
            this.version = null;
            this.iv = (ASN1OctetString) aSN1Sequence.getObjectAt(0);
            return;
        }
        this.version = (ASN1Integer) aSN1Sequence.getObjectAt(0);
        this.iv = (ASN1OctetString) aSN1Sequence.getObjectAt(1);
    }
}
