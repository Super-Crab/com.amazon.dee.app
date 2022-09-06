package com.amazon.alexa.device.setup.echo.bouncycastle.asn1;

import java.math.BigInteger;
/* loaded from: classes.dex */
public class ASN1Integer extends DERInteger {
    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Integer(byte[] bArr) {
        super(bArr);
    }

    public ASN1Integer(BigInteger bigInteger) {
        super(bigInteger);
    }

    public ASN1Integer(int i) {
        super(i);
    }
}
