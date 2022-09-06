package com.amazon.alexa;

import java.util.concurrent.FutureTask;
import javax.inject.Inject;
/* compiled from: Downchannel.java */
/* loaded from: classes.dex */
public class fau extends FutureTask<Void> {
    public final jQK zZm;

    @Inject
    public fau(jQK jqk) {
        super(jqk);
        this.zZm = jqk;
    }

    @Override // java.util.concurrent.FutureTask, java.util.concurrent.Future
    public boolean cancel(boolean z) {
        super.cancel(z);
        this.zZm.zZm();
        return true;
    }
}
