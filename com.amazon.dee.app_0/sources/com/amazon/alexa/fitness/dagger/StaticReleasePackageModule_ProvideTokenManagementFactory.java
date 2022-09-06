package com.amazon.alexa.fitness.dagger;

import android.content.Context;
import com.amazon.identity.auth.device.api.TokenManagement;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideTokenManagementFactory implements Factory<TokenManagement> {
    private final Provider<Context> applicationContextProvider;
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideTokenManagementFactory(StaticReleasePackageModule staticReleasePackageModule, Provider<Context> provider) {
        this.module = staticReleasePackageModule;
        this.applicationContextProvider = provider;
    }

    public static StaticReleasePackageModule_ProvideTokenManagementFactory create(StaticReleasePackageModule staticReleasePackageModule, Provider<Context> provider) {
        return new StaticReleasePackageModule_ProvideTokenManagementFactory(staticReleasePackageModule, provider);
    }

    public static TokenManagement provideInstance(StaticReleasePackageModule staticReleasePackageModule, Provider<Context> provider) {
        return proxyProvideTokenManagement(staticReleasePackageModule, provider.mo10268get());
    }

    public static TokenManagement proxyProvideTokenManagement(StaticReleasePackageModule staticReleasePackageModule, Context context) {
        return (TokenManagement) Preconditions.checkNotNull(staticReleasePackageModule.provideTokenManagement(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TokenManagement mo10268get() {
        return provideInstance(this.module, this.applicationContextProvider);
    }
}
