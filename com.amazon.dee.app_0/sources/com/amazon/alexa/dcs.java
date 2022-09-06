package com.amazon.alexa;

import android.content.Context;
import android.media.SoundPool;
import android.util.SparseArray;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
/* compiled from: SoundWrapper.java */
/* loaded from: classes.dex */
public class dcs implements SoundPool.OnLoadCompleteListener {
    public final Lazy<SoundPool> zQM;
    public final Context zyO;
    public final SparseArray<KQt> zZm = new SparseArray<>();
    public final Map<gOC, KQt> BIo = new HashMap();

    @Inject
    public dcs(Lazy<SoundPool> lazy, Context context) {
        this.zQM = lazy;
        this.zyO = context;
    }

    public void BIo() {
        this.zQM.mo358get().release();
    }

    @Override // android.media.SoundPool.OnLoadCompleteListener
    public void onLoadComplete(SoundPool soundPool, int i, int i2) {
        KQt kQt = this.zZm.get(i);
        if (kQt != null) {
            boolean z = i2 == 0;
            kQt.Qle = true;
            kQt.jiA = z;
            kQt.zyO.countDown();
        }
    }

    public void zZm() {
        gOC[] values;
        this.zQM.mo358get().setOnLoadCompleteListener(this);
        for (gOC goc : gOC.values()) {
            int load = this.zQM.mo358get().load(this.zyO, goc.zZm(), 0);
            KQt kQt = new KQt(goc, load);
            this.zZm.put(load, kQt);
            this.BIo.put(goc, kQt);
        }
    }

    public int zZm(gOC goc, boolean z) {
        return this.zQM.mo358get().play(this.BIo.get(goc).zQM, 1.0f, 1.0f, 0, z ? -1 : 0, 1.0001f);
    }

    public KQt zZm(gOC goc) {
        return this.BIo.get(goc);
    }
}
