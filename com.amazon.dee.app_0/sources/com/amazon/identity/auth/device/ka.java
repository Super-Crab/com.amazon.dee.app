package com.amazon.identity.auth.device;

import com.amazon.identity.kcpsdk.common.ParseError;
import com.amazon.identity.kcpsdk.common.WebResponseParser;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ka<T> extends WebResponseParser<T> {
    private static final String TAG = "com.amazon.identity.auth.device.ka";
    private final mk rW;
    private final WebResponseParser<T> rX;
    private ParseError rY;
    private ParseError rZ;
    private boolean sa;
    private boolean sb;

    public ka() {
        super("com.amazon.identity.kcpsdk.auth.AuthenticatedResponseHandler");
        this.rW = new mk();
        this.rX = null;
        ParseError parseError = ParseError.ParseErrorNoError;
        this.rY = parseError;
        this.rZ = parseError;
        this.sa = true;
    }

    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    public void a(byte[] bArr, long j) {
        WebResponseParser<T> webResponseParser;
        if (this.rZ == ParseError.ParseErrorNoError && this.sb) {
            this.rW.c(bArr, j);
        }
        if (this.rY != ParseError.ParseErrorNoError || (webResponseParser = this.rX) == null || !webResponseParser.hd()) {
            return;
        }
        this.rY = this.rX.b(bArr, j);
    }

    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    public boolean b(me meVar) {
        WebResponseParser<T> webResponseParser = this.rX;
        if (webResponseParser != null) {
            webResponseParser.c(meVar);
        }
        long iG = meVar.iG();
        String eK = meVar.eK("content-type");
        this.sb = iG == 500 || (iG == 200 && (eK == null || "text/xml".equals(eK)));
        if (this.sb) {
            io.a(TAG, "AuthenticatedResponseHandler: beginParse: Response could be an authentication error. Will try to parse as an authentication error. Status Code: %d Content-Type: %s", Long.valueOf(iG), eK);
        }
        return true;
    }

    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    public boolean hd() {
        WebResponseParser<T> webResponseParser = this.rX;
        return (webResponseParser != null && webResponseParser.hd()) || this.sb;
    }

    public boolean he() {
        return this.sa;
    }

    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    public T hf() {
        WebResponseParser<T> webResponseParser = this.rX;
        if (webResponseParser == null) {
            return null;
        }
        return webResponseParser.hf();
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0052, code lost:
        if (((r0 == null || !r0.getTagName().equals("Errors") || (r0 = com.amazon.identity.auth.device.ml.a(r0, "Error")) == null || (r0 = com.amazon.identity.auth.device.ml.a(r0, com.amazon.identity.auth.device.datastore.DatabaseHelper.authorizationCode)) == null) ? false : "DEVICE_MASTER_SERVICE_ERROR".equals(r0.getTextContent())) != false) goto L11;
     */
    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void hg() {
        /*
            r4 = this;
            boolean r0 = r4.sb
            if (r0 == 0) goto L5f
            com.amazon.identity.kcpsdk.common.ParseError r0 = r4.rZ
            com.amazon.identity.kcpsdk.common.ParseError r1 = com.amazon.identity.kcpsdk.common.ParseError.ParseErrorNoError
            r2 = 0
            if (r0 != r1) goto L5c
            com.amazon.identity.auth.device.mk r0 = r4.rW
            org.w3c.dom.Document r0 = r0.iI()
            if (r0 == 0) goto L5c
            com.amazon.identity.auth.device.lu r1 = com.amazon.identity.auth.device.lv.d(r0)
            if (r1 == 0) goto L21
            com.amazon.identity.kcpsdk.common.FIRSErrorType r1 = r1.iu()
            com.amazon.identity.kcpsdk.common.FIRSErrorType r3 = com.amazon.identity.kcpsdk.common.FIRSErrorType.FIRSErrorTypeInternalError
            if (r1 == r3) goto L54
        L21:
            org.w3c.dom.Element r0 = r0.getDocumentElement()
            if (r0 == 0) goto L51
            java.lang.String r1 = r0.getTagName()
            java.lang.String r3 = "Errors"
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L34
            goto L51
        L34:
            java.lang.String r1 = "Error"
            org.w3c.dom.Element r0 = com.amazon.identity.auth.device.ml.a(r0, r1)
            if (r0 != 0) goto L3d
            goto L51
        L3d:
            java.lang.String r1 = "Code"
            org.w3c.dom.Element r0 = com.amazon.identity.auth.device.ml.a(r0, r1)
            if (r0 != 0) goto L46
            goto L51
        L46:
            java.lang.String r0 = r0.getTextContent()
            java.lang.String r1 = "DEVICE_MASTER_SERVICE_ERROR"
            boolean r0 = r1.equals(r0)
            goto L52
        L51:
            r0 = r2
        L52:
            if (r0 == 0) goto L5c
        L54:
            java.lang.String r0 = com.amazon.identity.auth.device.ka.TAG
            java.lang.String r1 = "AuthenticatedResponseHandler: endParse: Server returned an authentication error. You can ignore any malformed response errors from the inner parser."
            com.amazon.identity.auth.device.io.i(r0, r1)
            goto L5d
        L5c:
            r2 = 1
        L5d:
            r4.sa = r2
        L5f:
            com.amazon.identity.kcpsdk.common.ParseError r0 = r4.rY
            com.amazon.identity.kcpsdk.common.ParseError r1 = com.amazon.identity.kcpsdk.common.ParseError.ParseErrorNoError
            if (r0 != r1) goto L77
            com.amazon.identity.kcpsdk.common.WebResponseParser<T> r0 = r4.rX
            if (r0 == 0) goto L77
            boolean r0 = r0.hd()
            if (r0 == 0) goto L77
            com.amazon.identity.kcpsdk.common.WebResponseParser<T> r0 = r4.rX
            com.amazon.identity.kcpsdk.common.ParseError r0 = r0.hh()
            r4.rY = r0
        L77:
            boolean r0 = r4.sa
            if (r0 == 0) goto L7e
            com.amazon.identity.kcpsdk.common.ParseError r0 = r4.rY
            goto L80
        L7e:
            com.amazon.identity.kcpsdk.common.ParseError r0 = r4.rZ
        L80:
            r4.b(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.ka.hg():void");
    }
}
