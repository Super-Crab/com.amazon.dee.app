package com.amazon.alexa.device.setup.echo.bouncycastle.asn1;

import java.util.Date;
/* loaded from: classes.dex */
public class ASN1UTCTime extends DERUTCTime {
    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1UTCTime(byte[] bArr) {
        super(bArr);
    }

    public ASN1UTCTime(Date date) {
        super(date);
    }

    public ASN1UTCTime(String str) {
        super(str);
    }
}
