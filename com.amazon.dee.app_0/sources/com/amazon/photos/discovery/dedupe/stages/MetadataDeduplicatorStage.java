package com.amazon.photos.discovery.dedupe.stages;

import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.clouddrive.android.core.interfaces.ClientMetric;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.CloudDriveException;
import com.amazon.clouddrive.cdasdk.cds.common.NodeInfo;
import com.amazon.clouddrive.cdasdk.cds.node.ListNodeResponse;
import com.amazon.photos.discovery.dedupe.DeduplicationRequest;
import com.amazon.photos.discovery.dedupe.Deduplicator;
import com.amazon.photos.discovery.dedupe.DeduplicatorResult;
import com.amazon.photos.discovery.dedupe.NoRetryDedupeException;
import com.amazon.photos.discovery.dedupe.RetryDedupeException;
import com.amazon.photos.discovery.internal.ConstantsKt;
import com.amazon.photos.discovery.internal.Injectable;
import com.amazon.photos.discovery.internal.dagger.component.DiscoveryComponent;
import com.amazon.photos.discovery.internal.dedupe.filter.DedupeFilter;
import com.amazon.photos.discovery.internal.dedupe.filter.FilterEvents;
import com.amazon.photos.discovery.internal.dedupe.metadata.DateMatcher;
import com.amazon.photos.discovery.internal.dedupe.metadata.DayAggregations;
import com.amazon.photos.discovery.internal.dedupe.metadata.ItemBatcher;
import com.amazon.photos.discovery.internal.dedupe.metadata.ItemDedupeLogic;
import com.amazon.photos.discovery.internal.dedupe.metadata.ItemMapping;
import com.amazon.photos.discovery.internal.dedupe.metadata.ItemMappingUtils;
import com.amazon.photos.discovery.internal.dedupe.metadata.ListNodesBatch;
import com.amazon.photos.discovery.internal.server.MetricsServiceApi;
import com.amazon.photos.discovery.internal.server.ServiceApi;
import com.amazon.photos.discovery.internal.util.AbortableCountDownLatch;
import com.amazon.photos.discovery.internal.util.DateUtils;
import com.amazon.photos.discovery.internal.util.KotlinExtKt$catchDb$1;
import com.amazon.photos.discovery.internal.util.NodeUtils;
import com.amazon.photos.discovery.metrics.DiscoveryMetrics;
import com.amazon.photos.discovery.metrics.DiscoveryMetricsKt;
import com.amazon.photos.discovery.model.LocalItem;
import com.amazon.photos.discovery.model.UnifiedItem;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import javax.inject.Inject;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetadataDeduplicatorStage.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Ð\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\u0010\t\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 b2\u00020\u00012\u00020\u0002:\u0001bB\u0011\b\u0007\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J \u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020FH\u0002J&\u0010G\u001a\u00020@2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020J0I2\u0006\u0010A\u001a\u00020B2\u0006\u0010E\u001a\u00020FH\u0002J\u0010\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020NH\u0016J\u0010\u0010O\u001a\u00020@2\u0006\u0010P\u001a\u00020QH\u0016J\u0014\u0010R\u001a\u00020S2\n\u0010T\u001a\u00060Uj\u0002`VH\u0002J\u0016\u0010W\u001a\u00020F2\f\u0010X\u001a\b\u0012\u0004\u0012\u00020Z0YH\u0002J\u0016\u0010[\u001a\u00020F2\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020B0IH\u0002J\u0010\u0010]\u001a\u00020D2\u0006\u0010A\u001a\u00020BH\u0002J\u0014\u0010^\u001a\u0004\u0018\u00010D2\b\u0010_\u001a\u0004\u0018\u00010`H\u0002J\u0010\u0010a\u001a\u00020@2\u0006\u0010A\u001a\u00020BH\u0002R\u001e\u0010\u0006\u001a\u00020\u00078\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u00020\u000f8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0014\u001a\u00020\u00158\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001dR\u001e\u0010 \u001a\u00020!8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R2\u0010&\u001a&\u0012\f\u0012\n )*\u0004\u0018\u00010(0( )*\u0012\u0012\f\u0012\n )*\u0004\u0018\u00010(0(\u0018\u00010*0'X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010+\u001a\u00020,8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001e\u00101\u001a\u0002028\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u001e\u00107\u001a\u0002088\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u000e\u0010=\u001a\u00020>X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006c"}, d2 = {"Lcom/amazon/photos/discovery/dedupe/stages/MetadataDeduplicatorStage;", "Lcom/amazon/photos/discovery/dedupe/Deduplicator;", "Lcom/amazon/photos/discovery/internal/Injectable;", "threadPoolExecutor", "Ljava/util/concurrent/ThreadPoolExecutor;", "(Ljava/util/concurrent/ThreadPoolExecutor;)V", "dateMatcher", "Lcom/amazon/photos/discovery/internal/dedupe/metadata/DateMatcher;", "getDateMatcher$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/internal/dedupe/metadata/DateMatcher;", "setDateMatcher$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/internal/dedupe/metadata/DateMatcher;)V", "dayAggregations", "Lcom/amazon/photos/discovery/internal/dedupe/metadata/DayAggregations;", "dedupeFilter", "Lcom/amazon/photos/discovery/internal/dedupe/filter/DedupeFilter;", "getDedupeFilter$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/internal/dedupe/filter/DedupeFilter;", "setDedupeFilter$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/internal/dedupe/filter/DedupeFilter;)V", "filterEvents", "Lcom/amazon/photos/discovery/internal/dedupe/filter/FilterEvents;", "getFilterEvents$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/internal/dedupe/filter/FilterEvents;", "setFilterEvents$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/internal/dedupe/filter/FilterEvents;)V", "itemDedupeLogic", "Lcom/amazon/photos/discovery/internal/dedupe/metadata/ItemDedupeLogic;", "getItemDedupeLogic", "()Lcom/amazon/photos/discovery/internal/dedupe/metadata/ItemDedupeLogic;", "itemDedupeLogic$delegate", "Lkotlin/Lazy;", "itemMappingUtils", "Lcom/amazon/photos/discovery/internal/dedupe/metadata/ItemMappingUtils;", "getItemMappingUtils$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/internal/dedupe/metadata/ItemMappingUtils;", "setItemMappingUtils$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/internal/dedupe/metadata/ItemMappingUtils;)V", "localItemsDeduped", "", "", "kotlin.jvm.PlatformType", "", "logger", "Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "getLogger$AndroidPhotosDiscovery_release", "()Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "setLogger$AndroidPhotosDiscovery_release", "(Lcom/amazon/clouddrive/android/core/interfaces/Logger;)V", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "getMetrics$AndroidPhotosDiscovery_release", "()Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "setMetrics$AndroidPhotosDiscovery_release", "(Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "nodeUtils", "Lcom/amazon/photos/discovery/internal/util/NodeUtils;", "getNodeUtils$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/internal/util/NodeUtils;", "setNodeUtils$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/internal/util/NodeUtils;)V", "serviceApi", "Lcom/amazon/photos/discovery/internal/server/MetricsServiceApi;", "dedupeBatch", "", "batch", "Lcom/amazon/photos/discovery/internal/dedupe/metadata/ListNodesBatch;", DiscoveryMetricsKt.METRICS_SERVICE_API_LIST_NODES, "Lcom/amazon/clouddrive/cdasdk/cds/node/ListNodeResponse;", "matchMapping", "Lcom/amazon/photos/discovery/internal/dedupe/metadata/ItemMapping;", "dedupeNodes", "nodes", "", "Lcom/amazon/clouddrive/cdasdk/cds/common/NodeInfo;", "deduplicate", "Lcom/amazon/photos/discovery/dedupe/DeduplicatorResult;", "request", "Lcom/amazon/photos/discovery/dedupe/DeduplicationRequest;", "inject", JsonFields.COMPONENT, "Lcom/amazon/photos/discovery/internal/dagger/component/DiscoveryComponent;", "isInvalidParameterResult", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "performCloudContentToUnifiedIdMatching", "localItems", "", "Lcom/amazon/photos/discovery/model/LocalItem;", "processBatches", "batches", "queryForBatch", "queryForNextPage", "nextToken", "", "verboseBatchProcessingLog", "Companion", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MetadataDeduplicatorStage implements Deduplicator, Injectable {
    public static final Companion Companion = new Companion(null);
    private static final int DEFAULT_THREADS = Runtime.getRuntime().availableProcessors();
    @Inject
    @NotNull
    public DateMatcher dateMatcher;
    private DayAggregations dayAggregations;
    @Inject
    @NotNull
    public DedupeFilter dedupeFilter;
    @Inject
    @NotNull
    public FilterEvents filterEvents;
    private final Lazy itemDedupeLogic$delegate;
    @Inject
    @NotNull
    public ItemMappingUtils itemMappingUtils;
    private final Set<Long> localItemsDeduped;
    @Inject
    @NotNull
    public Logger logger;
    @Inject
    @NotNull
    public Metrics metrics;
    @Inject
    @NotNull
    public NodeUtils nodeUtils;
    private MetricsServiceApi serviceApi;
    private final ThreadPoolExecutor threadPoolExecutor;

    /* compiled from: MetadataDeduplicatorStage.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/photos/discovery/dedupe/stages/MetadataDeduplicatorStage$Companion;", "", "()V", "DEFAULT_THREADS", "", "createItemMapping", "Lcom/amazon/photos/discovery/internal/dedupe/metadata/ItemMapping;", "unifiedItems", "", "Lcom/amazon/photos/discovery/model/UnifiedItem;", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final ItemMapping createItemMapping(Collection<UnifiedItem> collection) {
            ItemMapping itemMapping = new ItemMapping();
            itemMapping.putAll(collection);
            return itemMapping;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @JvmOverloads
    public MetadataDeduplicatorStage() {
        this(null, 1, null);
    }

    @JvmOverloads
    public MetadataDeduplicatorStage(@NotNull ThreadPoolExecutor threadPoolExecutor) {
        Intrinsics.checkParameterIsNotNull(threadPoolExecutor, "threadPoolExecutor");
        this.threadPoolExecutor = threadPoolExecutor;
        this.itemDedupeLogic$delegate = LazyKt.lazy(new MetadataDeduplicatorStage$itemDedupeLogic$2(this));
        this.localItemsDeduped = Collections.newSetFromMap(new ConcurrentHashMap());
    }

    public static final /* synthetic */ DayAggregations access$getDayAggregations$p(MetadataDeduplicatorStage metadataDeduplicatorStage) {
        DayAggregations dayAggregations = metadataDeduplicatorStage.dayAggregations;
        if (dayAggregations == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dayAggregations");
        }
        return dayAggregations;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void dedupeBatch(ListNodesBatch listNodesBatch, ListNodeResponse listNodeResponse, ItemMapping itemMapping) throws CloudDriveException, InterruptedException, IOException {
        Logger logger = this.logger;
        if (logger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.US;
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.US");
        Object[] objArr = {Integer.valueOf(listNodesBatch.getLocalItems().size()), Integer.valueOf(listNodeResponse.getData().size())};
        String format = String.format(locale, "Deduping batch of %d local items against %d nodes.", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(locale, format, *args)");
        logger.v(ConstantsKt.MDD_STAGE, format);
        while (listNodeResponse != null && listNodeResponse.getData() != null) {
            List<NodeInfo> data = listNodeResponse.getData();
            Intrinsics.checkExpressionValueIsNotNull(data, "currentListNodes.data");
            dedupeNodes(data, listNodesBatch, itemMapping);
            listNodeResponse = queryForNextPage(listNodeResponse.getNextToken());
        }
    }

    private final void dedupeNodes(List<? extends NodeInfo> list, ListNodesBatch listNodesBatch, ItemMapping itemMapping) {
        for (NodeInfo nodeInfo : list) {
            String name = nodeInfo.getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "node.name");
            for (LocalItem localItem : listNodesBatch.getLocalItemsByName(name)) {
                if (getItemDedupeLogic().metadataDedupe(localItem, nodeInfo)) {
                    itemMapping.associate(localItem.getUnifiedId(), nodeInfo);
                    this.localItemsDeduped.add(Long.valueOf(localItem.getId()));
                }
            }
        }
    }

    private final ItemDedupeLogic getItemDedupeLogic() {
        return (ItemDedupeLogic) this.itemDedupeLogic$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isInvalidParameterResult(Exception exc) {
        return (exc instanceof CloudDriveException) && ((CloudDriveException) exc).getCode() == 400;
    }

    private final ItemMapping performCloudContentToUnifiedIdMatching(Collection<LocalItem> collection) throws CloudDriveException, InterruptedException {
        DayAggregations dayAggregations = this.dayAggregations;
        if (dayAggregations == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dayAggregations");
        }
        return processBatches(new ItemBatcher(dayAggregations).createBatches(collection));
    }

    private final ItemMapping processBatches(List<ListNodesBatch> list) throws InterruptedException, CloudDriveException {
        final ItemMapping itemMapping = new ItemMapping();
        final AbortableCountDownLatch abortableCountDownLatch = new AbortableCountDownLatch(list.size());
        final MetadataDeduplicatorStage$processBatches$1 metadataDeduplicatorStage$processBatches$1 = new MetadataDeduplicatorStage$processBatches$1(this, abortableCountDownLatch);
        for (final ListNodesBatch listNodesBatch : list) {
            verboseBatchProcessingLog(listNodesBatch);
            this.threadPoolExecutor.execute(new Runnable() { // from class: com.amazon.photos.discovery.dedupe.stages.MetadataDeduplicatorStage$processBatches$2
                @Override // java.lang.Runnable
                public final void run() {
                    ListNodeResponse queryForBatch;
                    try {
                        if (abortableCountDownLatch.isAborted()) {
                            return;
                        }
                        try {
                            try {
                                queryForBatch = MetadataDeduplicatorStage.this.queryForBatch(listNodesBatch);
                                MetadataDeduplicatorStage.this.dedupeBatch(listNodesBatch, queryForBatch, itemMapping);
                            } catch (CloudDriveException e) {
                                metadataDeduplicatorStage$processBatches$1.invoke2((Exception) e);
                            }
                        } catch (IOException e2) {
                            metadataDeduplicatorStage$processBatches$1.invoke2((Exception) e2);
                        } catch (InterruptedException e3) {
                            metadataDeduplicatorStage$processBatches$1.invoke2((Exception) e3);
                        }
                    } finally {
                        abortableCountDownLatch.countDown();
                    }
                }
            });
        }
        try {
            abortableCountDownLatch.await();
            return itemMapping;
        } catch (InterruptedException e) {
            Throwable abortReason = abortableCountDownLatch.getAbortReason();
            if (abortReason instanceof CloudDriveException) {
                throw abortReason;
            }
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListNodeResponse queryForBatch(ListNodesBatch listNodesBatch) throws InterruptedException, CloudDriveException, IOException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("status:AVAILABLE AND kind:FILE");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {DateUtils.INSTANCE.getUTCString(listNodesBatch.getRangeStart()), DateUtils.INSTANCE.getUTCString(listNodesBatch.getRangeEnd())};
        String format = String.format(" AND contentProperties.contentDate:[\"%s\" TO \"%s\"]\n", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        outline107.append(format);
        String sb = outline107.toString();
        MetricsServiceApi metricsServiceApi = this.serviceApi;
        if (metricsServiceApi == null) {
            Intrinsics.throwUninitializedPropertyAccessException("serviceApi");
        }
        ListNodeResponse listNodes = metricsServiceApi.listNodes(sb, null);
        ClientMetric withTagName = new ClientMetric().addCounter(DiscoveryMetrics.MddQueryBatchSize, listNodesBatch.size()).addCounter(DiscoveryMetrics.MddResponseNodes, listNodes.getData().size()).withTagName(ConstantsKt.MDD_STAGE);
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        metrics.recordCustomMetric(ConstantsKt.MDD_STAGE, withTagName, MetricRecordingType.STANDARD);
        return listNodes;
    }

    private final ListNodeResponse queryForNextPage(String str) throws InterruptedException, CloudDriveException, IOException {
        if (str != null) {
            if (!(str.length() == 0)) {
                MetricsServiceApi metricsServiceApi = this.serviceApi;
                if (metricsServiceApi == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("serviceApi");
                }
                ListNodeResponse listNodes = metricsServiceApi.listNodes(null, str);
                ClientMetric withTagName = new ClientMetric().addCounter(DiscoveryMetrics.MddResponseNodes, listNodes.getData().size()).withTagName(ConstantsKt.MDD_STAGE);
                Metrics metrics = this.metrics;
                if (metrics == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("metrics");
                }
                metrics.recordCustomMetric(ConstantsKt.MDD_STAGE, withTagName, MetricRecordingType.STANDARD);
                return listNodes;
            }
        }
        return null;
    }

    private final void verboseBatchProcessingLog(ListNodesBatch listNodesBatch) {
        Logger logger = this.logger;
        if (logger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.US;
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.US");
        Object[] objArr = {Integer.valueOf(listNodesBatch.getLocalItems().size()), Long.valueOf(listNodesBatch.getExpectedResultCount()), DateUtils.INSTANCE.getDayString(listNodesBatch.getRangeStart()), DateUtils.INSTANCE.getDayString(listNodesBatch.getRangeEnd())};
        String format = String.format(locale, "Daily aggregated number of local items: %d  Expected count: %d  Range: %s - %s", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(locale, format, *args)");
        logger.v(ConstantsKt.MDD_STAGE, format);
    }

    @Override // com.amazon.photos.discovery.dedupe.Deduplicator
    @NotNull
    public DeduplicatorResult deduplicate(@NotNull DeduplicationRequest request) throws InterruptedException, RetryDedupeException, NoRetryDedupeException {
        Collection<UnifiedItem> filter;
        Unit unit;
        Intrinsics.checkParameterIsNotNull(request, "request");
        this.localItemsDeduped.clear();
        MetadataDeduplicatorStage$deduplicate$1 metadataDeduplicatorStage$deduplicate$1 = new MetadataDeduplicatorStage$deduplicate$1(this);
        try {
            if (this.dayAggregations == null) {
                MetricsServiceApi metricsServiceApi = this.serviceApi;
                if (metricsServiceApi == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("serviceApi");
                }
                this.dayAggregations = metricsServiceApi.aggregationsByDay();
                FilterEvents filterEvents = this.filterEvents;
                if (filterEvents == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("filterEvents");
                }
                DayAggregations dayAggregations = this.dayAggregations;
                if (dayAggregations == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("dayAggregations");
                }
                filterEvents.onDayAggregationsRetrieved(dayAggregations);
            }
            DedupeFilter dedupeFilter = this.dedupeFilter;
            if (dedupeFilter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dedupeFilter");
            }
            filter = dedupeFilter.filter(ConstantsKt.MDD_STAGE, request.getUnifiedItems());
        } catch (CloudDriveException e) {
            metadataDeduplicatorStage$deduplicate$1.invoke2((Exception) e);
        } catch (IOException e2) {
            metadataDeduplicatorStage$deduplicate$1.invoke2((Exception) e2);
        }
        if (!filter.isEmpty()) {
            ItemMapping createItemMapping = Companion.createItemMapping(filter);
            ItemMapping performCloudContentToUnifiedIdMatching = performCloudContentToUnifiedIdMatching(createItemMapping.getLocalItems());
            Metrics metrics = this.metrics;
            if (metrics == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            try {
                request.getDedupeDao().executeTransaction(new MetadataDeduplicatorStage$deduplicate$$inlined$catchDb$lambda$1(this, request, performCloudContentToUnifiedIdMatching, createItemMapping));
                unit = Unit.INSTANCE;
            } catch (Exception e3) {
                metrics.recordSimpleErrorEvent(DiscoveryMetricsKt.DB_ERROR_COMPONENT, new KotlinExtKt$catchDb$1(DiscoveryMetricsKt.DB_ERROR_PERSIST_MDD), e3);
                unit = null;
            }
            if (unit == null) {
                throw new RetryDedupeException("Failure to persist Metadata mappings");
            }
            MetricsServiceApi metricsServiceApi2 = this.serviceApi;
            if (metricsServiceApi2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("serviceApi");
            }
            metricsServiceApi2.recordAndResetCallMetrics(ConstantsKt.MDD_STAGE);
            return new DeduplicatorResult(this.localItemsDeduped.size(), ConstantsKt.MDD_STAGE);
        }
        return new DeduplicatorResult(0, ConstantsKt.MDD_STAGE);
    }

    @NotNull
    public final DateMatcher getDateMatcher$AndroidPhotosDiscovery_release() {
        DateMatcher dateMatcher = this.dateMatcher;
        if (dateMatcher == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dateMatcher");
        }
        return dateMatcher;
    }

    @NotNull
    public final DedupeFilter getDedupeFilter$AndroidPhotosDiscovery_release() {
        DedupeFilter dedupeFilter = this.dedupeFilter;
        if (dedupeFilter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dedupeFilter");
        }
        return dedupeFilter;
    }

    @NotNull
    public final FilterEvents getFilterEvents$AndroidPhotosDiscovery_release() {
        FilterEvents filterEvents = this.filterEvents;
        if (filterEvents == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filterEvents");
        }
        return filterEvents;
    }

    @NotNull
    public final ItemMappingUtils getItemMappingUtils$AndroidPhotosDiscovery_release() {
        ItemMappingUtils itemMappingUtils = this.itemMappingUtils;
        if (itemMappingUtils == null) {
            Intrinsics.throwUninitializedPropertyAccessException("itemMappingUtils");
        }
        return itemMappingUtils;
    }

    @NotNull
    public final Logger getLogger$AndroidPhotosDiscovery_release() {
        Logger logger = this.logger;
        if (logger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        return logger;
    }

    @NotNull
    public final Metrics getMetrics$AndroidPhotosDiscovery_release() {
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        return metrics;
    }

    @NotNull
    public final NodeUtils getNodeUtils$AndroidPhotosDiscovery_release() {
        NodeUtils nodeUtils = this.nodeUtils;
        if (nodeUtils == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nodeUtils");
        }
        return nodeUtils;
    }

    @Override // com.amazon.photos.discovery.internal.Injectable
    public void inject(@NotNull DiscoveryComponent component) {
        Intrinsics.checkParameterIsNotNull(component, "component");
        component.inject(this);
        ServiceApi serviceApi = component.getServiceApi();
        Intrinsics.checkExpressionValueIsNotNull(serviceApi, "component.serviceApi");
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        this.serviceApi = new MetricsServiceApi(serviceApi, metrics);
    }

    public final void setDateMatcher$AndroidPhotosDiscovery_release(@NotNull DateMatcher dateMatcher) {
        Intrinsics.checkParameterIsNotNull(dateMatcher, "<set-?>");
        this.dateMatcher = dateMatcher;
    }

    public final void setDedupeFilter$AndroidPhotosDiscovery_release(@NotNull DedupeFilter dedupeFilter) {
        Intrinsics.checkParameterIsNotNull(dedupeFilter, "<set-?>");
        this.dedupeFilter = dedupeFilter;
    }

    public final void setFilterEvents$AndroidPhotosDiscovery_release(@NotNull FilterEvents filterEvents) {
        Intrinsics.checkParameterIsNotNull(filterEvents, "<set-?>");
        this.filterEvents = filterEvents;
    }

    public final void setItemMappingUtils$AndroidPhotosDiscovery_release(@NotNull ItemMappingUtils itemMappingUtils) {
        Intrinsics.checkParameterIsNotNull(itemMappingUtils, "<set-?>");
        this.itemMappingUtils = itemMappingUtils;
    }

    public final void setLogger$AndroidPhotosDiscovery_release(@NotNull Logger logger) {
        Intrinsics.checkParameterIsNotNull(logger, "<set-?>");
        this.logger = logger;
    }

    public final void setMetrics$AndroidPhotosDiscovery_release(@NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(metrics, "<set-?>");
        this.metrics = metrics;
    }

    public final void setNodeUtils$AndroidPhotosDiscovery_release(@NotNull NodeUtils nodeUtils) {
        Intrinsics.checkParameterIsNotNull(nodeUtils, "<set-?>");
        this.nodeUtils = nodeUtils;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ MetadataDeduplicatorStage(java.util.concurrent.ThreadPoolExecutor r8, int r9, kotlin.jvm.internal.DefaultConstructorMarker r10) {
        /*
            r7 = this;
            r9 = r9 & 1
            if (r9 == 0) goto L16
            java.util.concurrent.ThreadPoolExecutor r8 = new java.util.concurrent.ThreadPoolExecutor
            int r2 = com.amazon.photos.discovery.dedupe.stages.MetadataDeduplicatorStage.DEFAULT_THREADS
            r3 = 10
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.MINUTES
            java.util.concurrent.LinkedBlockingQueue r6 = new java.util.concurrent.LinkedBlockingQueue
            r6.<init>()
            r0 = r8
            r1 = r2
            r0.<init>(r1, r2, r3, r5, r6)
        L16:
            r7.<init>(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.discovery.dedupe.stages.MetadataDeduplicatorStage.<init>(java.util.concurrent.ThreadPoolExecutor, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
}
