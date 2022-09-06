package com.amazon.dee.app.dependencies;

import com.amazon.alexa.crashreporting.CrashReportingService;
import com.facebook.react.ReactPackage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ElementsModule_ProvideBugsnagPackageFactory implements Factory<ReactPackage> {
    private final Provider<CrashReportingService> crashReportingServiceProvider;
    private final ElementsModule module;

    public ElementsModule_ProvideBugsnagPackageFactory(ElementsModule elementsModule, Provider<CrashReportingService> provider) {
        this.module = elementsModule;
        this.crashReportingServiceProvider = provider;
    }

    public static ElementsModule_ProvideBugsnagPackageFactory create(ElementsModule elementsModule, Provider<CrashReportingService> provider) {
        return new ElementsModule_ProvideBugsnagPackageFactory(elementsModule, provider);
    }

    public static ReactPackage provideInstance(ElementsModule elementsModule, Provider<CrashReportingService> provider) {
        return proxyProvideBugsnagPackage(elementsModule, provider.mo10268get());
    }

    public static ReactPackage proxyProvideBugsnagPackage(ElementsModule elementsModule, CrashReportingService crashReportingService) {
        return (ReactPackage) Preconditions.checkNotNull(elementsModule.provideBugsnagPackage(crashReportingService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ReactPackage mo10268get() {
        return provideInstance(this.module, this.crashReportingServiceProvider);
    }
}
