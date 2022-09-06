package com.amazon.alexa.alertsca.dependencies;

import com.amazon.alexa.api.AlexaServicesConnection;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class ApplicationModule_ProvidesAlexaServicesConnectionFactory implements Factory<AlexaServicesConnection> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesAlexaServicesConnectionFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvidesAlexaServicesConnectionFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesAlexaServicesConnectionFactory(applicationModule);
    }

    public static AlexaServicesConnection provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesAlexaServicesConnection(applicationModule);
    }

    public static AlexaServicesConnection proxyProvidesAlexaServicesConnection(ApplicationModule applicationModule) {
        return (AlexaServicesConnection) Preconditions.checkNotNull(applicationModule.providesAlexaServicesConnection(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaServicesConnection mo10268get() {
        return provideInstance(this.module);
    }
}
