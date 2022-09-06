package com.amazon.alexa;

import com.amazon.alexa.BOa;
import java.io.EOFException;
/* compiled from: ValidationCallbacksWrapper.java */
/* loaded from: classes.dex */
public class Xjy implements Runnable {
    public final /* synthetic */ Rbd BIo;
    public final /* synthetic */ Throwable zZm;

    public Xjy(Rbd rbd, Throwable th) {
        this.BIo = rbd;
        this.zZm = th;
    }

    @Override // java.lang.Runnable
    public void run() {
        Sjd sjd;
        Sjd sjd2;
        Sjd sjd3;
        Throwable th = this.zZm;
        if (th == null) {
            IllegalStateException illegalStateException = new IllegalStateException("wake word not found");
            sjd3 = this.BIo.callbacks;
            ((BOa.BIo) sjd3).zZm(illegalStateException);
        } else if (th instanceof EOFException) {
            IllegalStateException illegalStateException2 = new IllegalStateException("audio input stream closed early");
            sjd2 = this.BIo.callbacks;
            ((BOa.BIo) sjd2).zQM(illegalStateException2);
        } else {
            IllegalStateException illegalStateException3 = new IllegalStateException("failed to detect a wake word", th);
            sjd = this.BIo.callbacks;
            ((BOa.BIo) sjd).zZm(illegalStateException3);
        }
    }
}
