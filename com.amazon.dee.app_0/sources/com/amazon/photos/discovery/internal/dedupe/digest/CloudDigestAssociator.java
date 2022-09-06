package com.amazon.photos.discovery.internal.dedupe.digest;

import com.amazon.alexa.handsfree.protocols.sierracontentprovider.SierraContentProviderContract;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.cds.bulk.BulkGetNodesByDigestResponse;
import com.amazon.clouddrive.cdasdk.cds.bulk.NodeMatch;
import com.amazon.clouddrive.cdasdk.cds.common.NodeInfo;
import com.amazon.clouddrive.cdasdk.cds.node.ListNodeResponse;
import com.amazon.photos.discovery.dao.DedupeDao;
import com.amazon.photos.discovery.internal.dedupe.filter.DedupeFilter;
import com.amazon.photos.discovery.internal.model.MutableCloudItem;
import com.amazon.photos.discovery.internal.server.MetricsServiceApi;
import com.amazon.photos.discovery.internal.util.NodeUtils;
import com.amazon.photos.discovery.metrics.DiscoveryMetrics;
import com.amazon.photos.discovery.model.LocalItem;
import com.amazon.photos.discovery.model.UnifiedItem;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: CloudDigestAssociator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ(\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014H\u0002J\"\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00120\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u000f\u001a\u00020\u0010J&\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00120\u00172\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001d0\u0014H\u0002J\u001c\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010#\u001a\u00020\u001d2\b\u0010$\u001a\u0004\u0018\u00010\u001dH\u0002J\u0012\u0010%\u001a\u0004\u0018\u00010\u001d2\u0006\u0010&\u001a\u00020\u001aH\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/amazon/photos/discovery/internal/dedupe/digest/CloudDigestAssociator;", "", "serviceApi", "Lcom/amazon/photos/discovery/internal/server/MetricsServiceApi;", "logger", "Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "nodeUtils", "Lcom/amazon/photos/discovery/internal/util/NodeUtils;", "dedupeFilter", "Lcom/amazon/photos/discovery/internal/dedupe/filter/DedupeFilter;", "(Lcom/amazon/photos/discovery/internal/server/MetricsServiceApi;Lcom/amazon/clouddrive/android/core/interfaces/Logger;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/amazon/photos/discovery/internal/util/NodeUtils;Lcom/amazon/photos/discovery/internal/dedupe/filter/DedupeFilter;)V", "associate", "", "dedupeDao", "Lcom/amazon/photos/discovery/dao/DedupeDao;", "unifiedItemId", "", "nodes", "", "Lcom/amazon/clouddrive/cdasdk/cds/common/NodeInfo;", "associateMatching", "", "unifiedItems", "", "Lcom/amazon/photos/discovery/model/UnifiedItem;", "fetchAndPersistNodes", SierraContentProviderContract.MD5_VALUE, "", "fetchBulkResponse", "Lcom/amazon/clouddrive/cdasdk/cds/bulk/BulkGetNodesByDigestResponse;", "md5Values", "fetchListNodeResults", "Lcom/amazon/clouddrive/cdasdk/cds/node/ListNodeResponse;", "groupMD5", "startToken", "processUnifiedItem", "unifiedItem", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class CloudDigestAssociator {
    private final DedupeFilter dedupeFilter;
    private final Logger logger;
    private final Metrics metrics;
    private final NodeUtils nodeUtils;
    private final MetricsServiceApi serviceApi;

    public CloudDigestAssociator(@NotNull MetricsServiceApi serviceApi, @NotNull Logger logger, @NotNull Metrics metrics, @NotNull NodeUtils nodeUtils, @NotNull DedupeFilter dedupeFilter) {
        Intrinsics.checkParameterIsNotNull(serviceApi, "serviceApi");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(nodeUtils, "nodeUtils");
        Intrinsics.checkParameterIsNotNull(dedupeFilter, "dedupeFilter");
        this.serviceApi = serviceApi;
        this.logger = logger;
        this.metrics = metrics;
        this.nodeUtils = nodeUtils;
        this.dedupeFilter = dedupeFilter;
    }

    private final void associate(DedupeDao dedupeDao, long j, List<? extends NodeInfo> list) {
        int collectionSizeOrDefault;
        if (list == null || list.isEmpty()) {
            return;
        }
        Logger logger = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Inserting and associating ");
        outline107.append(list.size());
        outline107.append(" cloud items to unified id ");
        outline107.append(j);
        logger.d("CloudDigestAssociator", outline107.toString());
        try {
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            for (NodeInfo nodeInfo : list) {
                String id = nodeInfo.getId();
                Intrinsics.checkExpressionValueIsNotNull(id, "it.id");
                arrayList.add(new MutableCloudItem(0L, j, id, this.nodeUtils.getNodeCreatedDate(nodeInfo), this.nodeUtils.getNodeContentDate(nodeInfo), this.nodeUtils.getNodeMD5(nodeInfo), this.nodeUtils.getNodeVisualDigest(nodeInfo)));
            }
            dedupeDao.insertCloudOrIgnore(arrayList);
        } catch (Exception e) {
            this.logger.e("CloudDigestAssociator", "Failed to insert cloud nodes into the db", e);
            this.metrics.recordSimpleErrorEvent("CloudDigestAssociator", DiscoveryMetrics.CloudNodeInsertionFailure, e);
        }
    }

    private final Set<Long> fetchAndPersistNodes(String str, long j, DedupeDao dedupeDao) {
        HashSet hashSet = new HashSet();
        String str2 = null;
        do {
            ListNodeResponse fetchListNodeResults = fetchListNodeResults(str, str2);
            if (fetchListNodeResults != null) {
                hashSet.add(Long.valueOf(j));
                associate(dedupeDao, j, fetchListNodeResults.getData());
                str2 = fetchListNodeResults.getNextToken();
                continue;
            }
        } while (str2 != null);
        return hashSet;
    }

    private final BulkGetNodesByDigestResponse fetchBulkResponse(List<String> list) {
        if (list.isEmpty()) {
            return null;
        }
        try {
            return this.serviceApi.bulkGetNodesByDigestRequest(list);
        } catch (Exception e) {
            this.metrics.recordSimpleErrorEvent("CloudDigestAssociator", DiscoveryMetrics.DigestBulkGetNodesFailure, e);
            this.logger.e("CloudDigestAssociator", "Unable to make Bulk GetNodesByDigest query", e);
            return null;
        }
    }

    private final ListNodeResponse fetchListNodeResults(String str, String str2) {
        try {
            MetricsServiceApi metricsServiceApi = this.serviceApi;
            return metricsServiceApi.listNodes(CloudDigestAssociatorKt.MD5_FILTER_PREFIX + str, str2);
        } catch (Exception e) {
            this.metrics.recordSimpleErrorEvent("CloudDigestAssociator", DiscoveryMetrics.DigestListNodesFailure, e);
            this.logger.e("CloudDigestAssociator", "Unable to make CDS listNodes query", e);
            return null;
        }
    }

    private final String processUnifiedItem(UnifiedItem unifiedItem) {
        boolean z;
        boolean z2;
        int collectionSizeOrDefault;
        List distinct;
        if (!unifiedItem.getLocalItems().isEmpty()) {
            List<LocalItem> localItems = unifiedItem.getLocalItems();
            if (!(localItems instanceof Collection) || !localItems.isEmpty()) {
                for (LocalItem localItem : localItems) {
                    if (localItem.getMd5() == null) {
                        z = true;
                        continue;
                    } else {
                        z = false;
                        continue;
                    }
                    if (z) {
                        z2 = true;
                        break;
                    }
                }
            }
            z2 = false;
            if (z2 || (!unifiedItem.getCloudItems().isEmpty())) {
                return null;
            }
            List<LocalItem> localItems2 = unifiedItem.getLocalItems();
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(localItems2, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            for (LocalItem localItem2 : localItems2) {
                arrayList.add(localItem2.getMd5());
            }
            distinct = CollectionsKt___CollectionsKt.distinct(arrayList);
            if (distinct.size() != 1) {
                this.logger.e("CloudDigestAssociator", "Was given a group (" + unifiedItem + ".id) with more than one MD5");
                this.metrics.recordSimpleEvent("CloudDigestAssociator", DiscoveryMetrics.MismatchedGroupMD5s, new MetricRecordingType[0]);
            }
            String str = (String) CollectionsKt.first((List<? extends Object>) distinct);
            if (str != null) {
                return str;
            }
            this.logger.e("CloudDigestAssociator", "Was given a group (" + unifiedItem + ".id) with a null MD5, ignoring");
            return null;
        }
        return null;
    }

    @NotNull
    public final Set<Long> associateMatching(@NotNull Collection<UnifiedItem> unifiedItems, @NotNull DedupeDao dedupeDao) {
        List<List<String>> chunked;
        Map<String, NodeMatch> digestToNodeMap;
        List<? extends NodeInfo> listOf;
        Set<Long> emptySet;
        Intrinsics.checkParameterIsNotNull(unifiedItems, "unifiedItems");
        Intrinsics.checkParameterIsNotNull(dedupeDao, "dedupeDao");
        Logger logger = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Processing a request of ");
        outline107.append(unifiedItems.size());
        outline107.append(" unified items");
        logger.d("CloudDigestAssociator", outline107.toString());
        HashMap hashMap = new HashMap();
        Collection<UnifiedItem> filter = this.dedupeFilter.filter("CloudDigestAssociator", unifiedItems);
        if (filter.isEmpty()) {
            emptySet = SetsKt__SetsKt.emptySet();
            return emptySet;
        }
        for (UnifiedItem unifiedItem : filter) {
            String processUnifiedItem = processUnifiedItem(unifiedItem);
            if (processUnifiedItem != null) {
                Long l = (Long) hashMap.put(processUnifiedItem, Long.valueOf(unifiedItem.getId()));
            }
        }
        HashSet hashSet = new HashSet();
        HashMap hashMap2 = new HashMap();
        Set keySet = hashMap.keySet();
        Intrinsics.checkExpressionValueIsNotNull(keySet, "md5ToUnifiedIdMap.keys");
        chunked = CollectionsKt___CollectionsKt.chunked(keySet, 10);
        for (List<String> list : chunked) {
            BulkGetNodesByDigestResponse fetchBulkResponse = fetchBulkResponse(list);
            if (fetchBulkResponse != null && (digestToNodeMap = fetchBulkResponse.getDigestToNodeMap()) != null) {
                for (Map.Entry<String, NodeMatch> entry : digestToNodeMap.entrySet()) {
                    Long l2 = (Long) hashMap.get(entry.getKey());
                    if (l2 != null) {
                        long longValue = l2.longValue();
                        NodeMatch value = entry.getValue();
                        Intrinsics.checkExpressionValueIsNotNull(value, "digestMapEntry.value");
                        if (value.isMultipleHits()) {
                            hashMap2.put(entry.getKey(), Long.valueOf(longValue));
                        } else {
                            NodeMatch value2 = entry.getValue();
                            Intrinsics.checkExpressionValueIsNotNull(value2, "digestMapEntry.value");
                            listOf = CollectionsKt__CollectionsJVMKt.listOf(value2.getNodeInfo());
                            associate(dedupeDao, longValue, listOf);
                            hashSet.add(Long.valueOf(longValue));
                        }
                    }
                }
            }
        }
        Logger logger2 = this.logger;
        logger2.i("CloudDigestAssociator", hashMap2.size() + " unified items had multiple node matches");
        for (Map.Entry entry2 : hashMap2.entrySet()) {
            fetchAndPersistNodes((String) entry2.getKey(), ((Number) entry2.getValue()).longValue(), dedupeDao);
            hashSet.add(entry2.getValue());
        }
        this.serviceApi.recordAndResetCallMetrics("CloudDigestAssociator");
        return hashSet;
    }
}
