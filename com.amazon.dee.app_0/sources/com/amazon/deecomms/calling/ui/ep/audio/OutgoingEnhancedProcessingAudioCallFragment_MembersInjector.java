package com.amazon.deecomms.calling.ui.ep.audio;

import com.amazon.deecomms.core.CapabilitiesManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class OutgoingEnhancedProcessingAudioCallFragment_MembersInjector implements MembersInjector<OutgoingEnhancedProcessingAudioCallFragment> {
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;

    public OutgoingEnhancedProcessingAudioCallFragment_MembersInjector(Provider<CapabilitiesManager> provider) {
        this.capabilitiesManagerProvider = provider;
    }

    public static MembersInjector<OutgoingEnhancedProcessingAudioCallFragment> create(Provider<CapabilitiesManager> provider) {
        return new OutgoingEnhancedProcessingAudioCallFragment_MembersInjector(provider);
    }

    public static void injectCapabilitiesManager(OutgoingEnhancedProcessingAudioCallFragment outgoingEnhancedProcessingAudioCallFragment, CapabilitiesManager capabilitiesManager) {
        outgoingEnhancedProcessingAudioCallFragment.capabilitiesManager = capabilitiesManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(OutgoingEnhancedProcessingAudioCallFragment outgoingEnhancedProcessingAudioCallFragment) {
        outgoingEnhancedProcessingAudioCallFragment.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
    }
}
