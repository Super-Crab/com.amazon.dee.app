package com.amazon.identity.auth.device;

import com.amazon.identity.kcpsdk.common.WebResponseParser;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class lj extends WebResponseParser<li> {
    private final mk rW;
    private li uc;

    public lj() {
        super("com.amazon.identity.kcpsdk.auth.RenameDeviceResponseParser");
        this.rW = new mk();
    }

    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    public void a(byte[] bArr, long j) {
        this.rW.c(bArr, j);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0084  */
    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void hg() {
        /*
            r8 = this;
            com.amazon.identity.auth.device.mk r0 = r8.rW
            org.w3c.dom.Document r0 = r0.iI()
            if (r0 != 0) goto Le
            com.amazon.identity.kcpsdk.common.ParseError r0 = com.amazon.identity.kcpsdk.common.ParseError.ParseErrorMalformedBody
            r8.b(r0)
            return
        Le:
            org.w3c.dom.Element r0 = r0.getDocumentElement()
            java.lang.String r1 = "Received a null response from the server"
            r2 = 7
            r3 = 0
            if (r0 == 0) goto L81
            java.lang.String r4 = r0.getTagName()
            java.lang.String r5 = "response"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L26
            goto L81
        L26:
            java.lang.String r4 = "error_code"
            org.w3c.dom.Element r4 = com.amazon.identity.auth.device.ml.a(r0, r4)
            java.lang.String r5 = "text"
            org.w3c.dom.Element r0 = com.amazon.identity.auth.device.ml.a(r0, r5)
            java.lang.String r4 = com.amazon.identity.auth.device.ml.f(r4)
            java.lang.String r0 = com.amazon.identity.auth.device.ml.f(r0)
            if (r4 != 0) goto L3e
            goto L81
        L3e:
            java.lang.Integer r4 = com.amazon.identity.auth.device.je.dA(r4)
            if (r4 != 0) goto L4e
            com.amazon.identity.auth.device.li r0 = new com.amazon.identity.auth.device.li
            com.amazon.identity.auth.device.api.MAPError$CommonError r4 = com.amazon.identity.auth.device.api.MAPError.CommonError.INVALID_RESPONSE
            java.lang.String r5 = "No response code found."
            r0.<init>(r4, r1, r2, r5)
            goto L82
        L4e:
            int r5 = r4.intValue()
            if (r5 == 0) goto L74
            r4 = 2
            if (r5 == r4) goto L6c
            r4 = 4
            if (r5 == r4) goto L64
            com.amazon.identity.auth.device.api.MAPError$CommonError r4 = com.amazon.identity.auth.device.api.MAPError.CommonError.INVALID_RESPONSE
            java.lang.String r5 = r4.getErrorMessage()
            r6 = r5
            r5 = r4
            r4 = r2
            goto L7a
        L64:
            r4 = 5
            com.amazon.identity.auth.device.api.MAPError$AccountError r5 = com.amazon.identity.auth.device.api.MAPError.AccountError.DUPLICATE_DEVICE_NAME
            java.lang.String r6 = r5.getErrorMessage()
            goto L7a
        L6c:
            r4 = 3
            com.amazon.identity.auth.device.api.MAPError$CommonError r5 = com.amazon.identity.auth.device.api.MAPError.CommonError.BAD_REQUEST
            java.lang.String r6 = r5.getErrorMessage()
            goto L7a
        L74:
            int r4 = r4.intValue()
            r5 = r3
            r6 = r5
        L7a:
            com.amazon.identity.auth.device.li r7 = new com.amazon.identity.auth.device.li
            r7.<init>(r5, r6, r4, r0)
            r0 = r7
            goto L82
        L81:
            r0 = r3
        L82:
            if (r0 != 0) goto L8b
            com.amazon.identity.auth.device.li r0 = new com.amazon.identity.auth.device.li
            com.amazon.identity.auth.device.api.MAPError$CommonError r4 = com.amazon.identity.auth.device.api.MAPError.CommonError.INVALID_RESPONSE
            r0.<init>(r4, r1, r2, r3)
        L8b:
            r8.uc = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.lj.hg():void");
    }

    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    /* renamed from: id */
    public li hf() {
        return this.uc;
    }
}
