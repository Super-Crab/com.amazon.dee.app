package com.amazon.alexa.fitness.dagger;

import com.amazon.alexa.fitness.sdk.AfxMessageProcessor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideAfxMessageProcessorFactory implements Factory<AfxMessageProcessor> {
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideAfxMessageProcessorFactory(StaticReleasePackageModule staticReleasePackageModule) {
        this.module = staticReleasePackageModule;
    }

    public static StaticReleasePackageModule_ProvideAfxMessageProcessorFactory create(StaticReleasePackageModule staticReleasePackageModule) {
        return new StaticReleasePackageModule_ProvideAfxMessageProcessorFactory(staticReleasePackageModule);
    }

    public static AfxMessageProcessor provideInstance(StaticReleasePackageModule staticReleasePackageModule) {
        return proxyProvideAfxMessageProcessor(staticReleasePackageModule);
    }

    public static AfxMessageProcessor proxyProvideAfxMessageProcessor(StaticReleasePackageModule staticReleasePackageModule) {
        return (AfxMessageProcessor) Preconditions.checkNotNull(staticReleasePackageModule.provideAfxMessageProcessor(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AfxMessageProcessor mo10268get() {
        return provideInstance(this.module);
    }
}
