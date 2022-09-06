package com.amazon.deecomms.calling.ui.ep.audio;

import com.amazon.deecomms.core.CapabilitiesManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class IncomingEnhancedProcessingAudioCallFragment_MembersInjector implements MembersInjector<IncomingEnhancedProcessingAudioCallFragment> {
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;

    public IncomingEnhancedProcessingAudioCallFragment_MembersInjector(Provider<CapabilitiesManager> provider) {
        this.capabilitiesManagerProvider = provider;
    }

    public static MembersInjector<IncomingEnhancedProcessingAudioCallFragment> create(Provider<CapabilitiesManager> provider) {
        return new IncomingEnhancedProcessingAudioCallFragment_MembersInjector(provider);
    }

    public static void injectCapabilitiesManager(IncomingEnhancedProcessingAudioCallFragment incomingEnhancedProcessingAudioCallFragment, CapabilitiesManager capabilitiesManager) {
        incomingEnhancedProcessingAudioCallFragment.capabilitiesManager = capabilitiesManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(IncomingEnhancedProcessingAudioCallFragment incomingEnhancedProcessingAudioCallFragment) {
        incomingEnhancedProcessingAudioCallFragment.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
    }
}
