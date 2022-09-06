package com.amazon.identity.auth.device;

import com.amazon.identity.kcpsdk.common.ParseError;
import com.amazon.identity.kcpsdk.common.WebResponseParser;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class lo implements lw {
    protected WebResponseParser uo;

    public lo(WebResponseParser webResponseParser) {
        this.uo = webResponseParser;
    }

    @Override // com.amazon.identity.auth.device.lw
    public ParseError b(byte[] bArr, int i) {
        WebResponseParser webResponseParser = this.uo;
        if (webResponseParser != null) {
            return webResponseParser.b(bArr, i);
        }
        return null;
    }

    @Override // com.amazon.identity.auth.device.lw
    public void c(me meVar) {
        WebResponseParser webResponseParser = this.uo;
        if (webResponseParser != null) {
            webResponseParser.c(meVar);
        }
    }

    @Override // com.amazon.identity.auth.device.lw
    public boolean hd() {
        WebResponseParser webResponseParser = this.uo;
        if (webResponseParser != null) {
            return webResponseParser.hd();
        }
        return false;
    }

    @Override // com.amazon.identity.auth.device.lw
    public Object hf() {
        WebResponseParser webResponseParser = this.uo;
        if (webResponseParser != null) {
            return webResponseParser.hf();
        }
        return null;
    }

    @Override // com.amazon.identity.auth.device.lw
    public ParseError hh() {
        WebResponseParser webResponseParser = this.uo;
        if (webResponseParser != null) {
            return webResponseParser.hh();
        }
        return null;
    }

    @Override // com.amazon.identity.auth.device.lw
    public ParseError hi() {
        WebResponseParser webResponseParser = this.uo;
        if (webResponseParser != null) {
            return webResponseParser.hi();
        }
        return null;
    }
}
