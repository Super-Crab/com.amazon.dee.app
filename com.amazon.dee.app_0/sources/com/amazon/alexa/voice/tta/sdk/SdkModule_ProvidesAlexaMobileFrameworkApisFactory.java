package com.amazon.alexa.voice.tta.sdk;

import android.content.Context;
import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class SdkModule_ProvidesAlexaMobileFrameworkApisFactory implements Factory<AlexaMobileFrameworkApis> {
    private final Provider<Context> contextProvider;
    private final SdkModule module;

    public SdkModule_ProvidesAlexaMobileFrameworkApisFactory(SdkModule sdkModule, Provider<Context> provider) {
        this.module = sdkModule;
        this.contextProvider = provider;
    }

    public static SdkModule_ProvidesAlexaMobileFrameworkApisFactory create(SdkModule sdkModule, Provider<Context> provider) {
        return new SdkModule_ProvidesAlexaMobileFrameworkApisFactory(sdkModule, provider);
    }

    public static AlexaMobileFrameworkApis provideInstance(SdkModule sdkModule, Provider<Context> provider) {
        return proxyProvidesAlexaMobileFrameworkApis(sdkModule, provider.mo10268get());
    }

    public static AlexaMobileFrameworkApis proxyProvidesAlexaMobileFrameworkApis(SdkModule sdkModule, Context context) {
        return (AlexaMobileFrameworkApis) Preconditions.checkNotNull(sdkModule.providesAlexaMobileFrameworkApis(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaMobileFrameworkApis mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
