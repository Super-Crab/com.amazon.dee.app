package com.amazon.alexa;

import com.amazon.alexa.wakeword.RecordingTracker;
import com.amazon.alexa.wakeword.precondition.InternalWakeWordPrecondition;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Set;
import javax.inject.Provider;
/* compiled from: WakeWordModule_ProvideWakeWordPreconditionsSetFactory.java */
/* loaded from: classes.dex */
public final class vpY implements Factory<Set<InternalWakeWordPrecondition>> {
    public final Provider<jxu> BIo;
    public final Provider<FLQ> Qle;
    public final Provider<RecordingTracker> jiA;
    public final Provider<Snr> zQM;
    public final iPU zZm;
    public final Provider<GSf> zyO;

    public vpY(iPU ipu, Provider<jxu> provider, Provider<Snr> provider2, Provider<GSf> provider3, Provider<RecordingTracker> provider4, Provider<FLQ> provider5) {
        this.zZm = ipu;
        this.BIo = provider;
        this.zQM = provider2;
        this.zyO = provider3;
        this.jiA = provider4;
        this.Qle = provider5;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (Set) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
