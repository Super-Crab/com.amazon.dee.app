package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.utils.TimeProvider;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: AudioPlayerModule_ProvidesSpeechAudioPlayerFactory.java */
/* loaded from: classes.dex */
public final class yAJ implements Factory<uxN> {
    public final Provider<Context> BIo;
    public final Provider<uXm> JTe;
    public final Provider<dAN> Qle;
    public final Provider<shl> jiA;
    public final Provider<TimeProvider> zQM;
    public final uuv zZm;
    public final Provider<gSO> zyO;

    public yAJ(uuv uuvVar, Provider<Context> provider, Provider<TimeProvider> provider2, Provider<gSO> provider3, Provider<shl> provider4, Provider<dAN> provider5, Provider<uXm> provider6) {
        this.zZm = uuvVar;
        this.BIo = provider;
        this.zQM = provider2;
        this.zyO = provider3;
        this.jiA = provider4;
        this.Qle = provider5;
        this.JTe = provider6;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (uxN) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), DoubleCheck.lazy(this.jiA), DoubleCheck.lazy(this.Qle), this.JTe.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
