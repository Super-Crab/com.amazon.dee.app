package com.amazon.alexa.voice.tta.sdk;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class SdkModule_ProvidesUiEventReporterFactory implements Factory<UiEventReporter> {
    private final Provider<Context> contextProvider;
    private final SdkModule module;

    public SdkModule_ProvidesUiEventReporterFactory(SdkModule sdkModule, Provider<Context> provider) {
        this.module = sdkModule;
        this.contextProvider = provider;
    }

    public static SdkModule_ProvidesUiEventReporterFactory create(SdkModule sdkModule, Provider<Context> provider) {
        return new SdkModule_ProvidesUiEventReporterFactory(sdkModule, provider);
    }

    public static UiEventReporter provideInstance(SdkModule sdkModule, Provider<Context> provider) {
        return proxyProvidesUiEventReporter(sdkModule, provider.mo10268get());
    }

    public static UiEventReporter proxyProvidesUiEventReporter(SdkModule sdkModule, Context context) {
        return (UiEventReporter) Preconditions.checkNotNull(sdkModule.providesUiEventReporter(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UiEventReporter mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
