package com.amazon.alexa;

import android.content.Context;
import androidx.annotation.Nullable;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: IOComponentModule_ProvidesIOComponentsStateProviderFactory.java */
/* loaded from: classes.dex */
public final class elM implements Factory<PRf> {
    public final Provider<Context> BIo;
    public final Provider<Efr> zQM;
    public final FLJ zZm;

    public elM(FLJ flj, Provider<Context> provider, Provider<Efr> provider2) {
        this.zZm = flj;
        this.BIo = provider;
        this.zQM = provider2;
    }

    @Override // javax.inject.Provider
    @Nullable
    /* renamed from: get */
    public Object mo10268get() {
        return this.zZm.zZm(this.BIo.mo10268get(), this.zQM.mo10268get());
    }
}
