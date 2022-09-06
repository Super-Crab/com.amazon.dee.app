package com.amazon.alexa.voiceui;

import android.content.Context;
import com.amazon.alexa.api.AlexaServicesConnection;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class AlexaServicesModule_ProvidesAlexaServicesConnectionFactory implements Factory<AlexaServicesConnection> {
    private final Provider<Context> contextProvider;
    private final AlexaServicesModule module;

    public AlexaServicesModule_ProvidesAlexaServicesConnectionFactory(AlexaServicesModule alexaServicesModule, Provider<Context> provider) {
        this.module = alexaServicesModule;
        this.contextProvider = provider;
    }

    public static AlexaServicesModule_ProvidesAlexaServicesConnectionFactory create(AlexaServicesModule alexaServicesModule, Provider<Context> provider) {
        return new AlexaServicesModule_ProvidesAlexaServicesConnectionFactory(alexaServicesModule, provider);
    }

    public static AlexaServicesConnection provideInstance(AlexaServicesModule alexaServicesModule, Provider<Context> provider) {
        return proxyProvidesAlexaServicesConnection(alexaServicesModule, provider.mo10268get());
    }

    public static AlexaServicesConnection proxyProvidesAlexaServicesConnection(AlexaServicesModule alexaServicesModule, Context context) {
        return (AlexaServicesConnection) Preconditions.checkNotNull(alexaServicesModule.providesAlexaServicesConnection(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaServicesConnection mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
