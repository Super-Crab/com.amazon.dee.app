package com.amazon.deecomms.calling.ui.ep.audio;

import com.amazon.deecomms.core.CapabilitiesManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ActiveEnhancedProcessingAudioCallFragment_MembersInjector implements MembersInjector<ActiveEnhancedProcessingAudioCallFragment> {
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;

    public ActiveEnhancedProcessingAudioCallFragment_MembersInjector(Provider<CapabilitiesManager> provider) {
        this.capabilitiesManagerProvider = provider;
    }

    public static MembersInjector<ActiveEnhancedProcessingAudioCallFragment> create(Provider<CapabilitiesManager> provider) {
        return new ActiveEnhancedProcessingAudioCallFragment_MembersInjector(provider);
    }

    public static void injectCapabilitiesManager(ActiveEnhancedProcessingAudioCallFragment activeEnhancedProcessingAudioCallFragment, CapabilitiesManager capabilitiesManager) {
        activeEnhancedProcessingAudioCallFragment.capabilitiesManager = capabilitiesManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ActiveEnhancedProcessingAudioCallFragment activeEnhancedProcessingAudioCallFragment) {
        activeEnhancedProcessingAudioCallFragment.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
    }
}
