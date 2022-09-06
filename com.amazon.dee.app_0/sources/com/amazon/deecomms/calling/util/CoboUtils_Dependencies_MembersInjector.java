package com.amazon.deecomms.calling.util;

import com.amazon.deecomms.calling.util.CoboUtils;
import com.amazon.deecomms.core.CapabilitiesManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CoboUtils_Dependencies_MembersInjector implements MembersInjector<CoboUtils.Dependencies> {
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;

    public CoboUtils_Dependencies_MembersInjector(Provider<CapabilitiesManager> provider) {
        this.capabilitiesManagerProvider = provider;
    }

    public static MembersInjector<CoboUtils.Dependencies> create(Provider<CapabilitiesManager> provider) {
        return new CoboUtils_Dependencies_MembersInjector(provider);
    }

    public static void injectCapabilitiesManager(CoboUtils.Dependencies dependencies, CapabilitiesManager capabilitiesManager) {
        dependencies.capabilitiesManager = capabilitiesManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(CoboUtils.Dependencies dependencies) {
        dependencies.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
    }
}
