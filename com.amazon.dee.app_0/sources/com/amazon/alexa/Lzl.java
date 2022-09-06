package com.amazon.alexa;

import java.io.IOException;
/* compiled from: DialogTurnData.java */
/* loaded from: classes.dex */
public abstract class Lzl {
    public zZm zZm;

    /* compiled from: DialogTurnData.java */
    /* loaded from: classes.dex */
    protected enum zZm {
        UNVERIFIED,
        VERIFYING,
        VERIFIED,
        FINISHED
    }

    public synchronized void BIo() {
        if (this.zZm != zZm.FINISHED) {
            yPL();
            this.zZm = zZm.FINISHED;
        }
    }

    public abstract boolean JTe();

    public abstract void LPk();

    public abstract String Qle();

    public abstract cIy jiA();

    public abstract void yPL();

    public abstract void zQM();

    public abstract Gcr zZm();

    public abstract void zZm(int i) throws IOException;

    public abstract void zZm(cIy ciy);

    public abstract cIy zyO();
}
