package com.amazon.deecomms.alexa;

import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.core.CapabilitiesManager;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CommsAudioInteractionScheduler_Factory implements Factory<CommsAudioInteractionScheduler> {
    private final Provider<CallManager> callManagerProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<CommsAudioInteraction> commsAudioInteractionProvider;

    public CommsAudioInteractionScheduler_Factory(Provider<CommsAudioInteraction> provider, Provider<CallManager> provider2, Provider<CapabilitiesManager> provider3) {
        this.commsAudioInteractionProvider = provider;
        this.callManagerProvider = provider2;
        this.capabilitiesManagerProvider = provider3;
    }

    public static CommsAudioInteractionScheduler_Factory create(Provider<CommsAudioInteraction> provider, Provider<CallManager> provider2, Provider<CapabilitiesManager> provider3) {
        return new CommsAudioInteractionScheduler_Factory(provider, provider2, provider3);
    }

    public static CommsAudioInteractionScheduler newCommsAudioInteractionScheduler(CommsAudioInteraction commsAudioInteraction, CallManager callManager, CapabilitiesManager capabilitiesManager) {
        return new CommsAudioInteractionScheduler(commsAudioInteraction, callManager, capabilitiesManager);
    }

    public static CommsAudioInteractionScheduler provideInstance(Provider<CommsAudioInteraction> provider, Provider<CallManager> provider2, Provider<CapabilitiesManager> provider3) {
        return new CommsAudioInteractionScheduler(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsAudioInteractionScheduler mo10268get() {
        return provideInstance(this.commsAudioInteractionProvider, this.callManagerProvider, this.capabilitiesManagerProvider);
    }
}
