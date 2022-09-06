package com.amazon.alexa;

import android.content.Context;
import android.content.Intent;
/* compiled from: UserInterfaceManager.java */
/* loaded from: classes.dex */
public class RNp implements Runnable {
    public final /* synthetic */ vYS BIo;
    public final /* synthetic */ Intent zZm;

    public RNp(vYS vys, Intent intent) {
        this.BIo = vys;
        this.zZm = intent;
    }

    @Override // java.lang.Runnable
    public void run() {
        Context context;
        context = this.BIo.BIo;
        context.startActivity(this.zZm);
    }
}
