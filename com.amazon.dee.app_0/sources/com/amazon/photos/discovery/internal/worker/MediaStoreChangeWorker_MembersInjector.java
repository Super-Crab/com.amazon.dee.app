package com.amazon.photos.discovery.internal.worker;

import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.discovery.Discovery;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class MediaStoreChangeWorker_MembersInjector implements MembersInjector<MediaStoreChangeWorker> {
    private final Provider<Discovery> discoveryProvider;
    private final Provider<Logger> loggerProvider;
    private final Provider<Metrics> metricsProvider;

    public MediaStoreChangeWorker_MembersInjector(Provider<Discovery> provider, Provider<Logger> provider2, Provider<Metrics> provider3) {
        this.discoveryProvider = provider;
        this.loggerProvider = provider2;
        this.metricsProvider = provider3;
    }

    public static MembersInjector<MediaStoreChangeWorker> create(Provider<Discovery> provider, Provider<Logger> provider2, Provider<Metrics> provider3) {
        return new MediaStoreChangeWorker_MembersInjector(provider, provider2, provider3);
    }

    public static void injectDiscovery(MediaStoreChangeWorker mediaStoreChangeWorker, Discovery discovery) {
        mediaStoreChangeWorker.discovery = discovery;
    }

    public static void injectLogger(MediaStoreChangeWorker mediaStoreChangeWorker, Logger logger) {
        mediaStoreChangeWorker.logger = logger;
    }

    public static void injectMetrics(MediaStoreChangeWorker mediaStoreChangeWorker, Metrics metrics) {
        mediaStoreChangeWorker.metrics = metrics;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(MediaStoreChangeWorker mediaStoreChangeWorker) {
        injectDiscovery(mediaStoreChangeWorker, this.discoveryProvider.mo10268get());
        injectLogger(mediaStoreChangeWorker, this.loggerProvider.mo10268get());
        injectMetrics(mediaStoreChangeWorker, this.metricsProvider.mo10268get());
    }
}
