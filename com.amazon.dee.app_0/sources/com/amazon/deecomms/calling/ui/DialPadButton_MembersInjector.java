package com.amazon.deecomms.calling.ui;

import com.amazon.deecomms.core.CapabilitiesManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class DialPadButton_MembersInjector implements MembersInjector<DialPadButton> {
    private final Provider<CapabilitiesManager> mCapabilitiesManagerProvider;

    public DialPadButton_MembersInjector(Provider<CapabilitiesManager> provider) {
        this.mCapabilitiesManagerProvider = provider;
    }

    public static MembersInjector<DialPadButton> create(Provider<CapabilitiesManager> provider) {
        return new DialPadButton_MembersInjector(provider);
    }

    public static void injectMCapabilitiesManager(DialPadButton dialPadButton, CapabilitiesManager capabilitiesManager) {
        dialPadButton.mCapabilitiesManager = capabilitiesManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DialPadButton dialPadButton) {
        dialPadButton.mCapabilitiesManager = this.mCapabilitiesManagerProvider.mo10268get();
    }
}
