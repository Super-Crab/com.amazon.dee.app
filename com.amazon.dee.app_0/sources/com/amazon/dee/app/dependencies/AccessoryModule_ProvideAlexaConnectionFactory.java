package com.amazon.dee.app.dependencies;

import com.amazon.alexa.accessory.avsclient.AlexaConnection;
import com.amazon.alexa.api.AlexaServicesConnection;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class AccessoryModule_ProvideAlexaConnectionFactory implements Factory<AlexaConnection> {
    private final Provider<AlexaServicesConnection> connectionProvider;
    private final AccessoryModule module;

    public AccessoryModule_ProvideAlexaConnectionFactory(AccessoryModule accessoryModule, Provider<AlexaServicesConnection> provider) {
        this.module = accessoryModule;
        this.connectionProvider = provider;
    }

    public static AccessoryModule_ProvideAlexaConnectionFactory create(AccessoryModule accessoryModule, Provider<AlexaServicesConnection> provider) {
        return new AccessoryModule_ProvideAlexaConnectionFactory(accessoryModule, provider);
    }

    public static AlexaConnection provideInstance(AccessoryModule accessoryModule, Provider<AlexaServicesConnection> provider) {
        return proxyProvideAlexaConnection(accessoryModule, provider.mo10268get());
    }

    public static AlexaConnection proxyProvideAlexaConnection(AccessoryModule accessoryModule, AlexaServicesConnection alexaServicesConnection) {
        return (AlexaConnection) Preconditions.checkNotNull(accessoryModule.provideAlexaConnection(alexaServicesConnection), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaConnection mo10268get() {
        return provideInstance(this.module, this.connectionProvider);
    }
}
