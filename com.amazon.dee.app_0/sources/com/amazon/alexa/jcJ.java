package com.amazon.alexa;

import android.os.ConditionVariable;
import com.amazon.alexa.utils.concurrent.ExecutorFactory;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
/* compiled from: SoundAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class jcJ {
    public static final String zZm = "jcJ";
    public final dcs BIo;
    public final ConditionVariable jiA;
    public final dcs zQM;
    public final ExecutorService zyO;

    /* compiled from: SoundAuthority.java */
    /* loaded from: classes.dex */
    private class zZm implements Callable<Integer> {
        public final boolean BIo;
        public final boolean zQM;
        public final KQt zZm;

        public zZm(KQt kQt, boolean z, boolean z2) {
            this.zZm = kQt;
            this.zQM = z;
            this.BIo = z2;
        }

        @Override // java.util.concurrent.Callable
        public Integer call() throws Exception {
            if (this.zZm.zZm()) {
                return Integer.valueOf(jcJ.zZm(jcJ.this, this.zZm.BIo, this.zQM, this.BIo));
            }
            return -1;
        }
    }

    @Inject
    public jcJ(dcs dcsVar, @Named("ScoSoundWrapper") dcs dcsVar2) {
        ExecutorService newSingleThreadCachedThreadPool = ExecutorFactory.newSingleThreadCachedThreadPool("sound_loading_executor");
        this.jiA = new ConditionVariable(false);
        this.BIo = dcsVar;
        this.zQM = dcsVar2;
        this.zyO = newSingleThreadCachedThreadPool;
        this.zyO.submit(new PqH(this));
    }

    public ojv zZm(gOC goc, boolean z) {
        dcs dcsVar;
        if (z) {
            dcsVar = this.zQM;
        } else {
            dcsVar = this.BIo;
        }
        KQt zZm2 = dcsVar.zZm(goc);
        if (zZm2.Qle && zZm2.jiA) {
            return new sSx(zZm(goc, z, false));
        }
        FutureTask futureTask = new FutureTask(new zZm(zZm2, z, false));
        this.zyO.submit(futureTask);
        String str = "Waiting for sound to load before playing: " + goc;
        return new ffe(futureTask);
    }

    public static /* synthetic */ int zZm(jcJ jcj, gOC goc, boolean z, boolean z2) {
        if (z) {
            return jcj.zQM.zZm(goc, z2);
        }
        return jcj.BIo.zZm(goc, z2);
    }

    public final int zZm(gOC goc, boolean z, boolean z2) {
        if (z) {
            return this.zQM.zZm(goc, z2);
        }
        return this.BIo.zZm(goc, z2);
    }
}
