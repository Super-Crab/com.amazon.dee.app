package com.amazon.dee.app.dependencies;

import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.core.decoupling.AlexaCommsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CommsModule_ProvideCommsManagerFactory implements Factory<CommsManager> {
    private final Provider<AlexaCommsService> commsServiceProvider;
    private final CommsModule module;

    public CommsModule_ProvideCommsManagerFactory(CommsModule commsModule, Provider<AlexaCommsService> provider) {
        this.module = commsModule;
        this.commsServiceProvider = provider;
    }

    public static CommsModule_ProvideCommsManagerFactory create(CommsModule commsModule, Provider<AlexaCommsService> provider) {
        return new CommsModule_ProvideCommsManagerFactory(commsModule, provider);
    }

    public static CommsManager provideInstance(CommsModule commsModule, Provider<AlexaCommsService> provider) {
        return proxyProvideCommsManager(commsModule, provider.mo10268get());
    }

    public static CommsManager proxyProvideCommsManager(CommsModule commsModule, AlexaCommsService alexaCommsService) {
        return (CommsManager) Preconditions.checkNotNull(commsModule.provideCommsManager(alexaCommsService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsManager mo10268get() {
        return provideInstance(this.module, this.commsServiceProvider);
    }
}
