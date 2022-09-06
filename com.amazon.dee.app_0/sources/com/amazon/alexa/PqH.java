package com.amazon.alexa;

import android.os.ConditionVariable;
/* compiled from: SoundAuthority.java */
/* loaded from: classes.dex */
public class PqH implements Runnable {
    public final /* synthetic */ jcJ zZm;

    public PqH(jcJ jcj) {
        this.zZm = jcj;
    }

    @Override // java.lang.Runnable
    public void run() {
        dcs dcsVar;
        dcs dcsVar2;
        ConditionVariable conditionVariable;
        dcsVar = this.zZm.zQM;
        dcsVar.zZm();
        dcsVar2 = this.zZm.BIo;
        dcsVar2.zZm();
        conditionVariable = this.zZm.jiA;
        conditionVariable.open();
    }
}
