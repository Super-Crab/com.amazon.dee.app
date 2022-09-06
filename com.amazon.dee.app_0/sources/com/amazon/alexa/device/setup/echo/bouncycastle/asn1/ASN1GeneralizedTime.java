package com.amazon.alexa.device.setup.echo.bouncycastle.asn1;

import java.util.Date;
/* loaded from: classes.dex */
public class ASN1GeneralizedTime extends DERGeneralizedTime {
    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1GeneralizedTime(byte[] bArr) {
        super(bArr);
    }

    public ASN1GeneralizedTime(Date date) {
        super(date);
    }

    public ASN1GeneralizedTime(String str) {
        super(str);
    }
}
