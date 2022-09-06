package com.amazon.dee.app.dependencies;

import com.amazon.deecomms.conversation.CommsDeviceSupport;
import com.amazon.deecomms.core.decoupling.AlexaCommsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CommsModule_ProvideCommsDeviceSupportFactory implements Factory<CommsDeviceSupport> {
    private final Provider<AlexaCommsService> commsServiceProvider;
    private final CommsModule module;

    public CommsModule_ProvideCommsDeviceSupportFactory(CommsModule commsModule, Provider<AlexaCommsService> provider) {
        this.module = commsModule;
        this.commsServiceProvider = provider;
    }

    public static CommsModule_ProvideCommsDeviceSupportFactory create(CommsModule commsModule, Provider<AlexaCommsService> provider) {
        return new CommsModule_ProvideCommsDeviceSupportFactory(commsModule, provider);
    }

    public static CommsDeviceSupport provideInstance(CommsModule commsModule, Provider<AlexaCommsService> provider) {
        return proxyProvideCommsDeviceSupport(commsModule, provider.mo10268get());
    }

    public static CommsDeviceSupport proxyProvideCommsDeviceSupport(CommsModule commsModule, AlexaCommsService alexaCommsService) {
        return (CommsDeviceSupport) Preconditions.checkNotNull(commsModule.provideCommsDeviceSupport(alexaCommsService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsDeviceSupport mo10268get() {
        return provideInstance(this.module, this.commsServiceProvider);
    }
}
