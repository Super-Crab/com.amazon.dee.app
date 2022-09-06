package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.utils.TimeProvider;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: ExoAudioPlayerFactory.java */
@Singleton
/* loaded from: classes.dex */
public class TLe {
    public final TimeProvider BIo;
    public final uXm JTe;
    public final Lazy<onD> Qle;
    public final Lazy<dAN> jiA;
    public final gSO zQM;
    public final Context zZm;
    public final Lazy<shl> zyO;

    @Inject
    public TLe(Context context, TimeProvider timeProvider, gSO gso, Lazy<shl> lazy, Lazy<dAN> lazy2, Lazy<onD> lazy3, uXm uxm) {
        this.zZm = context;
        this.BIo = timeProvider;
        this.zQM = gso;
        this.zyO = lazy;
        this.jiA = lazy2;
        this.Qle = lazy3;
        this.JTe = uxm;
    }

    public VIX zZm() {
        return new VIX(this.zZm, this.BIo, this.zQM, "audio-player", this.zyO, this.jiA, this.Qle, this.JTe);
    }
}
