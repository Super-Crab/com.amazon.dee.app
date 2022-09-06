package com.amazon.photos.discovery;

import androidx.work.WorkManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DiscoveryLiveWorkInfo_MembersInjector implements MembersInjector<DiscoveryLiveWorkInfo> {
    private final Provider<String> hashedDirectedIdProvider;
    private final Provider<WorkManager> workManagerProvider;

    public DiscoveryLiveWorkInfo_MembersInjector(Provider<String> provider, Provider<WorkManager> provider2) {
        this.hashedDirectedIdProvider = provider;
        this.workManagerProvider = provider2;
    }

    public static MembersInjector<DiscoveryLiveWorkInfo> create(Provider<String> provider, Provider<WorkManager> provider2) {
        return new DiscoveryLiveWorkInfo_MembersInjector(provider, provider2);
    }

    public static void injectHashedDirectedId(DiscoveryLiveWorkInfo discoveryLiveWorkInfo, String str) {
        discoveryLiveWorkInfo.hashedDirectedId = str;
    }

    public static void injectWorkManager(DiscoveryLiveWorkInfo discoveryLiveWorkInfo, WorkManager workManager) {
        discoveryLiveWorkInfo.workManager = workManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DiscoveryLiveWorkInfo discoveryLiveWorkInfo) {
        injectHashedDirectedId(discoveryLiveWorkInfo, this.hashedDirectedIdProvider.mo10268get());
        injectWorkManager(discoveryLiveWorkInfo, this.workManagerProvider.mo10268get());
    }
}
