package com.amazon.dee.app.dependencies;

import com.amazon.deecomms.api.CommsDelegateBase;
import com.amazon.deecomms.core.decoupling.AlexaCommsServiceWrapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CommsModule_ProvideCommsDelegateBaseFactory implements Factory<CommsDelegateBase> {
    private final Provider<AlexaCommsServiceWrapper> alexaCommsServiceWrapperProvider;
    private final CommsModule module;

    public CommsModule_ProvideCommsDelegateBaseFactory(CommsModule commsModule, Provider<AlexaCommsServiceWrapper> provider) {
        this.module = commsModule;
        this.alexaCommsServiceWrapperProvider = provider;
    }

    public static CommsModule_ProvideCommsDelegateBaseFactory create(CommsModule commsModule, Provider<AlexaCommsServiceWrapper> provider) {
        return new CommsModule_ProvideCommsDelegateBaseFactory(commsModule, provider);
    }

    public static CommsDelegateBase provideInstance(CommsModule commsModule, Provider<AlexaCommsServiceWrapper> provider) {
        return proxyProvideCommsDelegateBase(commsModule, provider.mo10268get());
    }

    public static CommsDelegateBase proxyProvideCommsDelegateBase(CommsModule commsModule, AlexaCommsServiceWrapper alexaCommsServiceWrapper) {
        return (CommsDelegateBase) Preconditions.checkNotNull(commsModule.provideCommsDelegateBase(alexaCommsServiceWrapper), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsDelegateBase mo10268get() {
        return provideInstance(this.module, this.alexaCommsServiceWrapperProvider);
    }
}
