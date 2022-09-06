package com.amazon.alexa;

import com.amazon.alexa.pPw;
import javax.annotation.Nullable;
/* compiled from: ExternalCapabilityAgentException.java */
/* loaded from: classes.dex */
public class noF extends Exception {
    public final pPw.BIo zZm;

    public noF(String str, Exception exc, @Nullable pPw.BIo bIo) {
        super(str, exc);
        this.zZm = bIo;
    }

    public noF(String str, @Nullable pPw.BIo bIo) {
        super(str);
        this.zZm = bIo;
    }
}
