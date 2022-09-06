package com.amazon.alexa.smarthomecameras.dependencies.modules;

import android.content.Context;
import android.util.DisplayMetrics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class PtzModule_ProvideDisplayMetricsFactory implements Factory<DisplayMetrics> {
    private final Provider<Context> contextProvider;
    private final PtzModule module;

    public PtzModule_ProvideDisplayMetricsFactory(PtzModule ptzModule, Provider<Context> provider) {
        this.module = ptzModule;
        this.contextProvider = provider;
    }

    public static PtzModule_ProvideDisplayMetricsFactory create(PtzModule ptzModule, Provider<Context> provider) {
        return new PtzModule_ProvideDisplayMetricsFactory(ptzModule, provider);
    }

    public static DisplayMetrics provideInstance(PtzModule ptzModule, Provider<Context> provider) {
        return proxyProvideDisplayMetrics(ptzModule, provider.mo10268get());
    }

    public static DisplayMetrics proxyProvideDisplayMetrics(PtzModule ptzModule, Context context) {
        return (DisplayMetrics) Preconditions.checkNotNull(ptzModule.provideDisplayMetrics(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DisplayMetrics mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
