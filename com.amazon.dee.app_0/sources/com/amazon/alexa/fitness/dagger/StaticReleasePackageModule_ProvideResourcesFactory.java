package com.amazon.alexa.fitness.dagger;

import android.content.Context;
import android.content.res.Resources;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideResourcesFactory implements Factory<Resources> {
    private final Provider<Context> applicationContextProvider;
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideResourcesFactory(StaticReleasePackageModule staticReleasePackageModule, Provider<Context> provider) {
        this.module = staticReleasePackageModule;
        this.applicationContextProvider = provider;
    }

    public static StaticReleasePackageModule_ProvideResourcesFactory create(StaticReleasePackageModule staticReleasePackageModule, Provider<Context> provider) {
        return new StaticReleasePackageModule_ProvideResourcesFactory(staticReleasePackageModule, provider);
    }

    public static Resources provideInstance(StaticReleasePackageModule staticReleasePackageModule, Provider<Context> provider) {
        return proxyProvideResources(staticReleasePackageModule, provider.mo10268get());
    }

    public static Resources proxyProvideResources(StaticReleasePackageModule staticReleasePackageModule, Context context) {
        return (Resources) Preconditions.checkNotNull(staticReleasePackageModule.provideResources(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Resources mo10268get() {
        return provideInstance(this.module, this.applicationContextProvider);
    }
}
