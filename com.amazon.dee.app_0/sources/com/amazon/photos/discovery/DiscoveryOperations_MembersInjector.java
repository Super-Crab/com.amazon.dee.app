package com.amazon.photos.discovery;

import androidx.work.WorkManager;
import com.amazon.clouddrive.android.core.interfaces.SystemUtil;
import com.amazon.photos.discovery.internal.observers.ContentChangeNotifier;
import com.amazon.photos.discovery.internal.util.WorkerHelper;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DiscoveryOperations_MembersInjector implements MembersInjector<DiscoveryOperations> {
    private final Provider<ContentChangeNotifier> contentChangeNotifierProvider;
    private final Provider<SystemUtil> systemUtilProvider;
    private final Provider<WorkManager> workManagerProvider;
    private final Provider<WorkerHelper> workerHelperProvider;

    public DiscoveryOperations_MembersInjector(Provider<WorkManager> provider, Provider<WorkerHelper> provider2, Provider<SystemUtil> provider3, Provider<ContentChangeNotifier> provider4) {
        this.workManagerProvider = provider;
        this.workerHelperProvider = provider2;
        this.systemUtilProvider = provider3;
        this.contentChangeNotifierProvider = provider4;
    }

    public static MembersInjector<DiscoveryOperations> create(Provider<WorkManager> provider, Provider<WorkerHelper> provider2, Provider<SystemUtil> provider3, Provider<ContentChangeNotifier> provider4) {
        return new DiscoveryOperations_MembersInjector(provider, provider2, provider3, provider4);
    }

    public static void injectContentChangeNotifier(DiscoveryOperations discoveryOperations, ContentChangeNotifier contentChangeNotifier) {
        discoveryOperations.contentChangeNotifier = contentChangeNotifier;
    }

    public static void injectSystemUtil(DiscoveryOperations discoveryOperations, SystemUtil systemUtil) {
        discoveryOperations.systemUtil = systemUtil;
    }

    public static void injectWorkManager(DiscoveryOperations discoveryOperations, WorkManager workManager) {
        discoveryOperations.workManager = workManager;
    }

    public static void injectWorkerHelper(DiscoveryOperations discoveryOperations, WorkerHelper workerHelper) {
        discoveryOperations.workerHelper = workerHelper;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DiscoveryOperations discoveryOperations) {
        injectWorkManager(discoveryOperations, this.workManagerProvider.mo10268get());
        injectWorkerHelper(discoveryOperations, this.workerHelperProvider.mo10268get());
        injectSystemUtil(discoveryOperations, this.systemUtilProvider.mo10268get());
        injectContentChangeNotifier(discoveryOperations, this.contentChangeNotifierProvider.mo10268get());
    }
}
