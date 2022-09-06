package com.amazon.identity.auth.device;

import com.amazon.identity.kcpsdk.auth.DeregisterDeviceErrorType;
import com.amazon.identity.kcpsdk.common.ParseError;
import com.amazon.identity.kcpsdk.common.WebResponseParser;
import org.w3c.dom.Document;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class kl extends WebResponseParser<kk> {
    private final mk rW;
    private kk sp;

    public kl() {
        super("com.amazon.identity.kcpsdk.auth.DeregisterDeviceResponseParser");
        this.rW = new mk();
    }

    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    public void a(byte[] bArr, long j) {
        this.rW.c(bArr, j);
    }

    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    public void hg() {
        Document iI = this.rW.iI();
        if (iI == null) {
            b(ParseError.ParseErrorMalformedBody);
            return;
        }
        kk kkVar = new kk();
        if (lv.d(iI) != null) {
            kkVar.a(new ki(DeregisterDeviceErrorType.DeregisterDeviceErrorTypeFailed));
        }
        this.sp = kkVar;
    }

    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    /* renamed from: hq */
    public kk hf() {
        return this.sp;
    }
}
