package com.amazon.deecomms.calling.ui.ep;

import com.amazon.deecomms.core.CapabilitiesManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class OutgoingEnhancedProcessingVideoCallFragment_MembersInjector implements MembersInjector<OutgoingEnhancedProcessingVideoCallFragment> {
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;

    public OutgoingEnhancedProcessingVideoCallFragment_MembersInjector(Provider<CapabilitiesManager> provider) {
        this.capabilitiesManagerProvider = provider;
    }

    public static MembersInjector<OutgoingEnhancedProcessingVideoCallFragment> create(Provider<CapabilitiesManager> provider) {
        return new OutgoingEnhancedProcessingVideoCallFragment_MembersInjector(provider);
    }

    public static void injectCapabilitiesManager(OutgoingEnhancedProcessingVideoCallFragment outgoingEnhancedProcessingVideoCallFragment, CapabilitiesManager capabilitiesManager) {
        outgoingEnhancedProcessingVideoCallFragment.capabilitiesManager = capabilitiesManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(OutgoingEnhancedProcessingVideoCallFragment outgoingEnhancedProcessingVideoCallFragment) {
        outgoingEnhancedProcessingVideoCallFragment.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
    }
}
