package com.amazon.photos.discovery.internal.dagger.module;

import androidx.work.WorkManager;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.android.core.interfaces.SystemUtil;
import com.amazon.clouddrive.cdasdk.util.MD5Fingerprint;
import com.amazon.photos.discovery.Discovery;
import com.amazon.photos.discovery.DiscoveryConfiguration;
import com.amazon.photos.discovery.dedupe.DedupeStage;
import com.amazon.photos.discovery.internal.OpenForTesting;
import com.amazon.photos.discovery.internal.dagger.PerAccount;
import com.amazon.photos.discovery.internal.dedupe.digest.CloudDigestAssociator;
import com.amazon.photos.discovery.internal.dedupe.digest.LocalDigestAssociator;
import com.amazon.photos.discovery.internal.dedupe.filter.DedupeFilter;
import com.amazon.photos.discovery.internal.dedupe.metadata.DateMatcher;
import com.amazon.photos.discovery.internal.dedupe.metadata.ItemMappingUtils;
import com.amazon.photos.discovery.internal.observers.ContentChangeNotifier;
import com.amazon.photos.discovery.internal.server.DefaultServiceApi;
import com.amazon.photos.discovery.internal.server.MetricsServiceApi;
import com.amazon.photos.discovery.internal.server.ServiceApi;
import com.amazon.photos.discovery.internal.util.FileUtils;
import com.amazon.photos.discovery.internal.util.MediaStoreUtil;
import com.amazon.photos.discovery.internal.util.NodeUtils;
import com.amazon.photos.discovery.internal.util.SystemUtilsImpl;
import com.amazon.photos.discovery.internal.util.WorkerHelper;
import dagger.Module;
import dagger.Provides;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: DiscoveryModule.kt */
@OpenForTesting
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J0\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\r\u0010\u0013\u001a\u00020\u0014H\u0001¢\u0006\u0002\b\u0015J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0014\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u0019H\u0007J\u000e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001dH\u0007J\b\u0010\u001e\u001a\u00020\u0003H\u0007J\b\u0010\u001f\u001a\u00020\u0005H\u0007J\b\u0010 \u001a\u00020!H\u0007J\b\u0010\"\u001a\u00020#H\u0007J \u0010$\u001a\u00020%2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0010\u0010&\u001a\u00020'2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\b\u0010(\u001a\u00020\fH\u0007J\b\u0010)\u001a\u00020*H\u0007J\u0018\u0010+\u001a\u00020,2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\b\u0010-\u001a\u00020\u000eH\u0007J\u0014\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a0\u0019H\u0007J\b\u0010/\u001a\u00020\u0010H\u0007J\b\u00100\u001a\u00020\u001aH\u0007J\b\u00101\u001a\u00020\nH\u0007J\b\u00102\u001a\u000203H\u0007J\b\u00104\u001a\u000205H\u0007J \u00106\u001a\u0002072\u0006\u00108\u001a\u0002032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/amazon/photos/discovery/internal/dagger/module/DiscoveryModule;", "", "discovery", "Lcom/amazon/photos/discovery/Discovery;", "discoveryConfiguration", "Lcom/amazon/photos/discovery/DiscoveryConfiguration;", "(Lcom/amazon/photos/discovery/Discovery;Lcom/amazon/photos/discovery/DiscoveryConfiguration;)V", "provideCloudDigestAssociator", "Lcom/amazon/photos/discovery/internal/dedupe/digest/CloudDigestAssociator;", "serviceApi", "Lcom/amazon/photos/discovery/internal/server/ServiceApi;", "logger", "Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "nodeUtils", "Lcom/amazon/photos/discovery/internal/util/NodeUtils;", "dedupeFilter", "Lcom/amazon/photos/discovery/internal/dedupe/filter/DedupeFilter;", "provideContentChangeNotifier", "Lcom/amazon/photos/discovery/internal/observers/ContentChangeNotifier;", "provideContentChangeNotifier$AndroidPhotosDiscovery_release", "provideDateMatcher", "Lcom/amazon/photos/discovery/internal/dedupe/metadata/DateMatcher;", "provideDedupeIdMap", "", "", "Lcom/amazon/photos/discovery/dedupe/DedupeStage;", "provideDedupeStages", "", "provideDiscovery", "provideDiscoveryConfiguration", "provideFileUtils", "Lcom/amazon/photos/discovery/internal/util/FileUtils;", "provideHashedDirectedId", "", "provideItemMappingUtils", "Lcom/amazon/photos/discovery/internal/dedupe/metadata/ItemMappingUtils;", "provideLocalDigestAssociator", "Lcom/amazon/photos/discovery/internal/dedupe/digest/LocalDigestAssociator;", "provideLogger", "provideMd5Fingerprint", "Lcom/amazon/clouddrive/cdasdk/util/MD5Fingerprint;", "provideMediaStoreUtil", "Lcom/amazon/photos/discovery/internal/util/MediaStoreUtil;", "provideMetrics", "provideNextDedupeStageIdMap", "provideNodeUtils", "provideScanBatchSize", "provideServiceApi", "provideSystemUtil", "Lcom/amazon/clouddrive/android/core/interfaces/SystemUtil;", "provideWorkManager", "Landroidx/work/WorkManager;", "provideWorkerHelper", "Lcom/amazon/photos/discovery/internal/util/WorkerHelper;", "systemUtil", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
@Module
/* loaded from: classes13.dex */
public final class DiscoveryModule {
    private final Discovery discovery;
    private final DiscoveryConfiguration discoveryConfiguration;

    public DiscoveryModule(@NotNull Discovery discovery, @NotNull DiscoveryConfiguration discoveryConfiguration) {
        Intrinsics.checkParameterIsNotNull(discovery, "discovery");
        Intrinsics.checkParameterIsNotNull(discoveryConfiguration, "discoveryConfiguration");
        this.discovery = discovery;
        this.discoveryConfiguration = discoveryConfiguration;
    }

    @PerAccount
    @Provides
    @NotNull
    public final CloudDigestAssociator provideCloudDigestAssociator(@NotNull ServiceApi serviceApi, @NotNull Logger logger, @NotNull Metrics metrics, @NotNull NodeUtils nodeUtils, @NotNull DedupeFilter dedupeFilter) {
        Intrinsics.checkParameterIsNotNull(serviceApi, "serviceApi");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(nodeUtils, "nodeUtils");
        Intrinsics.checkParameterIsNotNull(dedupeFilter, "dedupeFilter");
        return new CloudDigestAssociator(new MetricsServiceApi(serviceApi, metrics), logger, metrics, nodeUtils, dedupeFilter);
    }

    @PerAccount
    @Provides
    @NotNull
    public final ContentChangeNotifier provideContentChangeNotifier$AndroidPhotosDiscovery_release() {
        return new ContentChangeNotifier();
    }

    @Provides
    @NotNull
    public final DateMatcher provideDateMatcher(@NotNull Logger logger, @NotNull NodeUtils nodeUtils) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(nodeUtils, "nodeUtils");
        return new DateMatcher(logger, nodeUtils);
    }

    @Provides
    @Named("DedupeIdMap")
    @NotNull
    @PerAccount
    public final Map<Integer, DedupeStage> provideDedupeIdMap() {
        int collectionSizeOrDefault;
        int mapCapacity;
        int coerceAtLeast;
        List<DedupeStage> dedupeStages = this.discoveryConfiguration.getDedupeStages();
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(dedupeStages, 10);
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(collectionSizeOrDefault);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (Object obj : dedupeStages) {
            linkedHashMap.put(Integer.valueOf(((DedupeStage) obj).getStageId()), obj);
        }
        return linkedHashMap;
    }

    @PerAccount
    @Provides
    @NotNull
    public final List<DedupeStage> provideDedupeStages() {
        return this.discoveryConfiguration.getDedupeStages();
    }

    @PerAccount
    @Provides
    @NotNull
    public final Discovery provideDiscovery() {
        return this.discovery;
    }

    @PerAccount
    @Provides
    @NotNull
    public final DiscoveryConfiguration provideDiscoveryConfiguration() {
        return this.discoveryConfiguration;
    }

    @PerAccount
    @Provides
    @NotNull
    public final FileUtils provideFileUtils() {
        return new FileUtils();
    }

    @Provides
    @Named("HashedDirectedId")
    @NotNull
    @PerAccount
    public final String provideHashedDirectedId() {
        return this.discoveryConfiguration.getHashedDirectedId();
    }

    @PerAccount
    @Provides
    @NotNull
    public final ItemMappingUtils provideItemMappingUtils(@NotNull Metrics metrics, @NotNull Logger logger, @NotNull NodeUtils nodeUtils) {
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(nodeUtils, "nodeUtils");
        return new ItemMappingUtils(metrics, logger, nodeUtils);
    }

    @PerAccount
    @Provides
    @NotNull
    public final LocalDigestAssociator provideLocalDigestAssociator(@NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        return new LocalDigestAssociator(metrics);
    }

    @Provides
    @NotNull
    public final Logger provideLogger() {
        return this.discoveryConfiguration.getLogger();
    }

    @PerAccount
    @Provides
    @NotNull
    public final MD5Fingerprint provideMd5Fingerprint() {
        return new MD5Fingerprint();
    }

    @Provides
    @NotNull
    public final MediaStoreUtil provideMediaStoreUtil(@NotNull Logger logger, @NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        return new MediaStoreUtil(logger, metrics);
    }

    @PerAccount
    @Provides
    @NotNull
    public final Metrics provideMetrics() {
        return this.discoveryConfiguration.getMetrics();
    }

    @Provides
    @Named("NextDedupeStageIdMap")
    @NotNull
    @PerAccount
    public final Map<Integer, Integer> provideNextDedupeStageIdMap() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = 0;
        for (DedupeStage dedupeStage : this.discoveryConfiguration.getDedupeStages()) {
            int component2 = dedupeStage.component2();
            linkedHashMap.put(Integer.valueOf(i), Integer.valueOf(component2));
            i = component2;
        }
        return linkedHashMap;
    }

    @Provides
    @NotNull
    public final NodeUtils provideNodeUtils() {
        return NodeUtils.INSTANCE;
    }

    @Provides
    @Named("ScanBatchSize")
    public final int provideScanBatchSize() {
        return this.discoveryConfiguration.getScanBatchSize();
    }

    @PerAccount
    @Provides
    @NotNull
    public final ServiceApi provideServiceApi() {
        return new DefaultServiceApi(this.discoveryConfiguration.getCdClient());
    }

    @Provides
    @NotNull
    public final SystemUtil provideSystemUtil() {
        return new SystemUtilsImpl();
    }

    @Provides
    @NotNull
    public final WorkManager provideWorkManager() {
        WorkManager workManager = WorkManager.getInstance(this.discoveryConfiguration.getApplicationContext());
        Intrinsics.checkExpressionValueIsNotNull(workManager, "WorkManager.getInstance(…ation.applicationContext)");
        return workManager;
    }

    @PerAccount
    @Provides
    @NotNull
    public final WorkerHelper provideWorkerHelper(@NotNull SystemUtil systemUtil, @NotNull Logger logger, @NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(systemUtil, "systemUtil");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        return new WorkerHelper(systemUtil, logger, metrics);
    }
}
