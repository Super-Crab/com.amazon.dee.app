package com.amazon.deecomms.calling.ui.ep;

import com.amazon.deecomms.core.CapabilitiesManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ActiveEnhancedProcessingVideoCallFragment_MembersInjector implements MembersInjector<ActiveEnhancedProcessingVideoCallFragment> {
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;

    public ActiveEnhancedProcessingVideoCallFragment_MembersInjector(Provider<CapabilitiesManager> provider) {
        this.capabilitiesManagerProvider = provider;
    }

    public static MembersInjector<ActiveEnhancedProcessingVideoCallFragment> create(Provider<CapabilitiesManager> provider) {
        return new ActiveEnhancedProcessingVideoCallFragment_MembersInjector(provider);
    }

    public static void injectCapabilitiesManager(ActiveEnhancedProcessingVideoCallFragment activeEnhancedProcessingVideoCallFragment, CapabilitiesManager capabilitiesManager) {
        activeEnhancedProcessingVideoCallFragment.capabilitiesManager = capabilitiesManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ActiveEnhancedProcessingVideoCallFragment activeEnhancedProcessingVideoCallFragment) {
        activeEnhancedProcessingVideoCallFragment.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
    }
}
