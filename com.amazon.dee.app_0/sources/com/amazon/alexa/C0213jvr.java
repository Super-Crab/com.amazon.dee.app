package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.utils.TimeProvider;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: ExoAudioPlayerFactory_Factory.java */
/* renamed from: com.amazon.alexa.jvr  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0213jvr implements Factory<TLe> {
    public final Provider<TimeProvider> BIo;
    public final Provider<uXm> JTe;
    public final Provider<onD> Qle;
    public final Provider<dAN> jiA;
    public final Provider<gSO> zQM;
    public final Provider<Context> zZm;
    public final Provider<shl> zyO;

    public C0213jvr(Provider<Context> provider, Provider<TimeProvider> provider2, Provider<gSO> provider3, Provider<shl> provider4, Provider<dAN> provider5, Provider<onD> provider6, Provider<uXm> provider7) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
        this.Qle = provider6;
        this.JTe = provider7;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new TLe(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), DoubleCheck.lazy(this.zyO), DoubleCheck.lazy(this.jiA), DoubleCheck.lazy(this.Qle), this.JTe.mo10268get());
    }
}
