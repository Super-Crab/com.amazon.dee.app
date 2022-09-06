package com.amazon.photos.discovery.internal.dagger.component;

import com.amazon.photos.discovery.Discovery;
import com.amazon.photos.discovery.DiscoveryDaos;
import com.amazon.photos.discovery.DiscoveryLiveWorkInfo;
import com.amazon.photos.discovery.DiscoveryOperations;
import com.amazon.photos.discovery.dedupe.stages.DigestBreakUpStage;
import com.amazon.photos.discovery.dedupe.stages.DigestCalculatorStage;
import com.amazon.photos.discovery.dedupe.stages.DigestDeduplicatorStage;
import com.amazon.photos.discovery.dedupe.stages.MetadataDeduplicatorStage;
import com.amazon.photos.discovery.internal.dagger.PerAccount;
import com.amazon.photos.discovery.internal.dagger.module.DataModule;
import com.amazon.photos.discovery.internal.dagger.module.DiscoveryModule;
import com.amazon.photos.discovery.internal.dedupe.digest.CloudDigestAssociator;
import com.amazon.photos.discovery.internal.server.ServiceApi;
import com.amazon.photos.discovery.internal.worker.DedupeWorker;
import com.amazon.photos.discovery.internal.worker.MediaStoreChangeWorker;
import com.amazon.photos.discovery.internal.worker.MonitorWorker;
import com.amazon.photos.discovery.internal.worker.ScanAddedWorker;
import com.amazon.photos.discovery.internal.worker.ScanDeletedWorker;
import dagger.Component;
@PerAccount
@Component(modules = {DiscoveryModule.class, DataModule.class})
/* loaded from: classes13.dex */
public interface DiscoveryComponent {
    Discovery getDiscovery();

    ServiceApi getServiceApi();

    void inject(DiscoveryDaos discoveryDaos);

    void inject(DiscoveryLiveWorkInfo discoveryLiveWorkInfo);

    void inject(DiscoveryOperations discoveryOperations);

    void inject(DigestBreakUpStage digestBreakUpStage);

    void inject(DigestCalculatorStage digestCalculatorStage);

    void inject(DigestDeduplicatorStage digestDeduplicatorStage);

    void inject(MetadataDeduplicatorStage metadataDeduplicatorStage);

    void inject(CloudDigestAssociator cloudDigestAssociator);

    void inject(DedupeWorker dedupeWorker);

    void inject(MediaStoreChangeWorker mediaStoreChangeWorker);

    void inject(MonitorWorker monitorWorker);

    void inject(ScanAddedWorker scanAddedWorker);

    void inject(ScanDeletedWorker scanDeletedWorker);
}
