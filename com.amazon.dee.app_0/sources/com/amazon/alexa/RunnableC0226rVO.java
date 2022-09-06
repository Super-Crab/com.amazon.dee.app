package com.amazon.alexa;

import android.app.KeyguardManager;
import android.content.Intent;
/* compiled from: UserInterfaceManager.java */
/* renamed from: com.amazon.alexa.rVO  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class RunnableC0226rVO implements Runnable {
    public final /* synthetic */ vYS BIo;
    public final /* synthetic */ Intent zZm;

    public RunnableC0226rVO(vYS vys, Intent intent) {
        this.BIo = vys;
        this.zZm = intent;
    }

    @Override // java.lang.Runnable
    public void run() {
        Msx msx;
        msx = this.BIo.Qle;
        KeyguardManager keyguardManager = msx.zZm;
        if (keyguardManager != null && keyguardManager.isKeyguardLocked()) {
            this.BIo.zQM(this.zZm);
        } else {
            this.BIo.zZm(this.zZm);
        }
    }
}
