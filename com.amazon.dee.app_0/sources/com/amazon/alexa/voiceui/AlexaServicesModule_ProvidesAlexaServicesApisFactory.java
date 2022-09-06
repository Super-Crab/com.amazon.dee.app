package com.amazon.alexa.voiceui;

import com.amazon.alexa.api.AlexaServicesConnection;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class AlexaServicesModule_ProvidesAlexaServicesApisFactory implements Factory<AlexaServicesApis> {
    private final Provider<AlexaServicesConnection> alexaServicesConnectionProvider;
    private final AlexaServicesModule module;

    public AlexaServicesModule_ProvidesAlexaServicesApisFactory(AlexaServicesModule alexaServicesModule, Provider<AlexaServicesConnection> provider) {
        this.module = alexaServicesModule;
        this.alexaServicesConnectionProvider = provider;
    }

    public static AlexaServicesModule_ProvidesAlexaServicesApisFactory create(AlexaServicesModule alexaServicesModule, Provider<AlexaServicesConnection> provider) {
        return new AlexaServicesModule_ProvidesAlexaServicesApisFactory(alexaServicesModule, provider);
    }

    public static AlexaServicesApis provideInstance(AlexaServicesModule alexaServicesModule, Provider<AlexaServicesConnection> provider) {
        return proxyProvidesAlexaServicesApis(alexaServicesModule, provider.mo10268get());
    }

    public static AlexaServicesApis proxyProvidesAlexaServicesApis(AlexaServicesModule alexaServicesModule, AlexaServicesConnection alexaServicesConnection) {
        return (AlexaServicesApis) Preconditions.checkNotNull(alexaServicesModule.providesAlexaServicesApis(alexaServicesConnection), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaServicesApis mo10268get() {
        return provideInstance(this.module, this.alexaServicesConnectionProvider);
    }
}
