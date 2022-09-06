package com.amazon.alexa;

import java.util.concurrent.Callable;
import javax.inject.Inject;
/* compiled from: UpdateVolumeFromSystemCallable.java */
/* loaded from: classes.dex */
public class Kfo implements Callable<Void> {
    public final QMz zZm;

    @Inject
    public Kfo(QMz qMz) {
        this.zZm = qMz;
    }

    @Override // java.util.concurrent.Callable
    public Void call() throws Exception {
        QMz qMz = this.zZm;
        int zyO = qMz.zyO();
        boolean zQM = qMz.zQM();
        boolean z = true;
        boolean z2 = qMz.JTe != zyO;
        if (qMz.jiA == zQM) {
            z = false;
        }
        if (z2) {
            qMz.BIo(qMz.BIo(zyO), zQM);
        }
        if (z) {
            qMz.zZm(qMz.BIo(zyO), zQM);
        }
        qMz.JTe = zyO;
        qMz.jiA = zQM;
        return null;
    }
}
