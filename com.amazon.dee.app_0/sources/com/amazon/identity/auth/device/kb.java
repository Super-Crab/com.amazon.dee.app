package com.amazon.identity.auth.device;

import com.amazon.identity.kcpsdk.common.ParseError;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class kb implements lw {
    private ka sc = new ka();
    private lw sd;

    public kb(lw lwVar) {
        this.sd = lwVar;
    }

    @Override // com.amazon.identity.auth.device.lw
    public ParseError b(byte[] bArr, int i) {
        lw lwVar = this.sd;
        if (lwVar != null && lwVar.hd()) {
            this.sd.b(bArr, i);
        }
        if (this.sc.hd()) {
            this.sc.b(bArr, i);
        }
        return hi();
    }

    @Override // com.amazon.identity.auth.device.lw
    public void c(me meVar) {
        lw lwVar = this.sd;
        if (lwVar != null) {
            lwVar.c(meVar);
        }
        this.sc.c(meVar);
    }

    @Override // com.amazon.identity.auth.device.lw
    public boolean hd() {
        lw lwVar = this.sd;
        return (lwVar != null && lwVar.hd()) || this.sc.hd();
    }

    public boolean he() {
        return this.sc.he();
    }

    @Override // com.amazon.identity.auth.device.lw
    public Object hf() {
        lw lwVar = this.sd;
        if (lwVar == null) {
            return null;
        }
        return lwVar.hf();
    }

    @Override // com.amazon.identity.auth.device.lw
    public ParseError hh() {
        lw lwVar = this.sd;
        if (lwVar != null) {
            lwVar.hh();
        }
        this.sc.hh();
        return hi();
    }

    @Override // com.amazon.identity.auth.device.lw
    public ParseError hi() {
        lw lwVar = this.sd;
        if (lwVar != null && lwVar.hi() != ParseError.ParseErrorNoError) {
            return this.sd.hi();
        }
        return this.sc.hi();
    }
}
