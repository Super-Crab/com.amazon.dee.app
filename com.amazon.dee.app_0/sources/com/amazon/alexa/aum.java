package com.amazon.alexa;

import com.amazon.alexa.uTP;
/* compiled from: PersistedDataLoader.java */
/* loaded from: classes.dex */
public class aum implements Runnable {
    public final /* synthetic */ uTP BIo;
    public final /* synthetic */ uTP.zZm zZm;

    public aum(uTP utp, uTP.zZm zzm) {
        this.BIo = utp;
        this.zZm = zzm;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.BIo.BIo(this.zZm);
    }
}
