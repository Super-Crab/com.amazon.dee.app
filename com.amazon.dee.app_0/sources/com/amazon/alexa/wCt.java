package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.utils.PackageNameProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: WakeWordModule_ProvidesStringResourceProviderFactory.java */
/* loaded from: classes.dex */
public final class wCt implements Factory<PackageNameProvider> {
    public final Provider<Context> BIo;
    public final iPU zZm;

    public wCt(iPU ipu, Provider<Context> provider) {
        this.zZm = ipu;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (PackageNameProvider) Preconditions.checkNotNull(this.zZm.zQM(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
