package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.utils.PackageNameProvider;
import com.amazon.alexa.wakeword.pryon.WakeWordModelContentProviderHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: WakeWordModule_ProvideWakeWordModelContentProviderHelperFactory.java */
/* loaded from: classes.dex */
public final class eGt implements Factory<WakeWordModelContentProviderHelper> {
    public final Provider<Context> BIo;
    public final Provider<PackageNameProvider> zQM;
    public final iPU zZm;

    public eGt(iPU ipu, Provider<Context> provider, Provider<PackageNameProvider> provider2) {
        this.zZm = ipu;
        this.BIo = provider;
        this.zQM = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (WakeWordModelContentProviderHelper) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get(), this.zQM.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
