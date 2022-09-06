package com.amazon.alexa.mobilytics.dependencies;

import com.amazon.alexa.mobilytics.internal.InstallationIdProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class MobilyticsModule_ProvideInstallationIdFactory implements Factory<String> {
    private final Provider<InstallationIdProvider> installationIdProvider;
    private final MobilyticsModule module;

    public MobilyticsModule_ProvideInstallationIdFactory(MobilyticsModule mobilyticsModule, Provider<InstallationIdProvider> provider) {
        this.module = mobilyticsModule;
        this.installationIdProvider = provider;
    }

    public static MobilyticsModule_ProvideInstallationIdFactory create(MobilyticsModule mobilyticsModule, Provider<InstallationIdProvider> provider) {
        return new MobilyticsModule_ProvideInstallationIdFactory(mobilyticsModule, provider);
    }

    public static String provideInstance(MobilyticsModule mobilyticsModule, Provider<InstallationIdProvider> provider) {
        return proxyProvideInstallationId(mobilyticsModule, provider.mo10268get());
    }

    public static String proxyProvideInstallationId(MobilyticsModule mobilyticsModule, InstallationIdProvider installationIdProvider) {
        return (String) Preconditions.checkNotNull(mobilyticsModule.provideInstallationId(installationIdProvider), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public String mo10268get() {
        return provideInstance(this.module, this.installationIdProvider);
    }
}
