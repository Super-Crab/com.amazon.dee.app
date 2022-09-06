package com.amazon.alexa.fitness.dagger;

import android.content.Context;
import com.amazon.alexa.api.AlexaServicesConnection;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideAlexaServicesConnectionFactory implements Factory<AlexaServicesConnection> {
    private final Provider<Context> contextProvider;
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideAlexaServicesConnectionFactory(StaticReleasePackageModule staticReleasePackageModule, Provider<Context> provider) {
        this.module = staticReleasePackageModule;
        this.contextProvider = provider;
    }

    public static StaticReleasePackageModule_ProvideAlexaServicesConnectionFactory create(StaticReleasePackageModule staticReleasePackageModule, Provider<Context> provider) {
        return new StaticReleasePackageModule_ProvideAlexaServicesConnectionFactory(staticReleasePackageModule, provider);
    }

    public static AlexaServicesConnection provideInstance(StaticReleasePackageModule staticReleasePackageModule, Provider<Context> provider) {
        return proxyProvideAlexaServicesConnection(staticReleasePackageModule, provider.mo10268get());
    }

    public static AlexaServicesConnection proxyProvideAlexaServicesConnection(StaticReleasePackageModule staticReleasePackageModule, Context context) {
        return (AlexaServicesConnection) Preconditions.checkNotNull(staticReleasePackageModule.provideAlexaServicesConnection(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaServicesConnection mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
