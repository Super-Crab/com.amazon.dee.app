package com.amazon.alexa;

import com.amazon.alexa.QYV;
/* compiled from: SpeechRecognizerCapabilityAgent.java */
/* loaded from: classes.dex */
public class TPm implements Runnable {
    public final /* synthetic */ LuX BIo;
    public final /* synthetic */ QYV.Qle zZm;

    public TPm(LuX luX, QYV.Qle qle) {
        this.BIo = luX;
        this.zZm = qle;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.BIo.zZm(this.zZm);
    }
}
