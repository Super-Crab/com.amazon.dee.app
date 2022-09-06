package com.amazon.identity.auth.device;

import com.amazon.identity.kcpsdk.common.ParseError;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ll implements km {
    private static final String TAG = "com.amazon.identity.auth.device.ll";
    private ko uj;
    private lw uk;
    private kb ul;
    private StringBuilder um;

    public ll(lw lwVar, ko koVar, boolean z) {
        if (koVar != null) {
            this.uj = koVar;
        } else {
            this.uj = new kh();
        }
        this.uk = lwVar;
        this.um = new StringBuilder();
        if (z) {
            this.ul = new kb(this.uk);
            this.uk = this.ul;
        }
    }

    private boolean h(Object obj) {
        if (obj == null) {
            return true;
        }
        return obj.getClass().getMethod("getError", new Class[0]).invoke(obj, new Object[0]) != null;
    }

    @Override // com.amazon.identity.auth.device.km
    public void a(me meVar) {
        this.uk.c(meVar);
    }

    @Override // com.amazon.identity.auth.device.km
    public void cE() {
        this.uk.hh();
        if (this.uk.hi() != ParseError.ParseErrorNoError) {
            io.dm(TAG);
            this.uk.hi();
            this.uj.l();
            return;
        }
        kb kbVar = this.ul;
        if (kbVar != null && !kbVar.he()) {
            this.uj.onAuthenticationFailed();
            return;
        }
        Object hf = this.uk.hf();
        if (h(hf)) {
            io.dm(TAG);
        }
        this.uj.a(hf);
    }

    @Override // com.amazon.identity.auth.device.km
    public void cF() {
        this.uj.onAuthenticationFailed();
    }

    @Override // com.amazon.identity.auth.device.km
    public void onNetworkError() {
        this.uj.k();
    }

    @Override // com.amazon.identity.auth.device.km
    public void a(byte[] bArr, int i) {
        if (this.uk.hd()) {
            if (this.um.length() < 1024) {
                this.um.append(new String(bArr, 0, this.um.length() + i > 1024 ? 1024 - this.um.length() : i));
            }
            this.uk.b(bArr, i);
        }
    }
}
