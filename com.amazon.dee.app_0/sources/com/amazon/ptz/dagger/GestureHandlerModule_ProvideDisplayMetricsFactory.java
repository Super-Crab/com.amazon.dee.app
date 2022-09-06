package com.amazon.ptz.dagger;

import android.content.Context;
import android.util.DisplayMetrics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class GestureHandlerModule_ProvideDisplayMetricsFactory implements Factory<DisplayMetrics> {
    private final Provider<Context> contextProvider;
    private final GestureHandlerModule module;

    public GestureHandlerModule_ProvideDisplayMetricsFactory(GestureHandlerModule gestureHandlerModule, Provider<Context> provider) {
        this.module = gestureHandlerModule;
        this.contextProvider = provider;
    }

    public static GestureHandlerModule_ProvideDisplayMetricsFactory create(GestureHandlerModule gestureHandlerModule, Provider<Context> provider) {
        return new GestureHandlerModule_ProvideDisplayMetricsFactory(gestureHandlerModule, provider);
    }

    public static DisplayMetrics provideInstance(GestureHandlerModule gestureHandlerModule, Provider<Context> provider) {
        return proxyProvideDisplayMetrics(gestureHandlerModule, provider.mo10268get());
    }

    public static DisplayMetrics proxyProvideDisplayMetrics(GestureHandlerModule gestureHandlerModule, Context context) {
        return (DisplayMetrics) Preconditions.checkNotNull(gestureHandlerModule.provideDisplayMetrics(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DisplayMetrics mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
