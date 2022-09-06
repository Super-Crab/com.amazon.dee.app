package com.amazon.alexa;

import com.amazon.alexa.wakeword.pryon.WakeWordModelContentProviderHelper;
import java.util.List;
/* compiled from: WakeWordAuthority.java */
/* loaded from: classes.dex */
public class Rqp implements Runnable {
    public final /* synthetic */ bjR BIo;
    public final /* synthetic */ WakeWordModelContentProviderHelper zZm;

    public Rqp(bjR bjr, WakeWordModelContentProviderHelper wakeWordModelContentProviderHelper) {
        this.BIo = bjr;
        this.zZm = wakeWordModelContentProviderHelper;
    }

    @Override // java.lang.Runnable
    public void run() {
        List<String> readWakeWords = this.zZm.readWakeWords();
        if (readWakeWords == null) {
            readWakeWords = bjR.BIo;
        }
        this.BIo.zZm(readWakeWords, false);
    }
}
