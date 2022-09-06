package com.amazon.photos.discovery.internal.dedupe.metadata;

import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.cds.common.NodeInfo;
import com.amazon.photos.discovery.dao.DedupeDao;
import com.amazon.photos.discovery.internal.model.MutableCloudItem;
import com.amazon.photos.discovery.internal.util.NodeUtils;
import com.amazon.photos.discovery.metrics.DiscoveryMetrics;
import com.amazon.photos.discovery.model.CloudItem;
import com.amazon.photos.discovery.model.LocalItem;
import com.amazon.photos.discovery.model.UnifiedItem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
/* compiled from: ItemMappingUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ$\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002J&\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0002J\u0018\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u001e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001cR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/amazon/photos/discovery/internal/dedupe/metadata/ItemMappingUtils;", "", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "logger", "Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "nodeUtils", "Lcom/amazon/photos/discovery/internal/util/NodeUtils;", "(Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/amazon/clouddrive/android/core/interfaces/Logger;Lcom/amazon/photos/discovery/internal/util/NodeUtils;)V", "getIgnoredNodeIds", "", "", "persistedCloudIds", "", "nodes", "", "Lcom/amazon/clouddrive/cdasdk/cds/common/NodeInfo;", "getUnifiedIdsToMerge", "", "", "localItem", "Lcom/amazon/photos/discovery/model/LocalItem;", "cloudContentMapping", "Lcom/amazon/photos/discovery/internal/dedupe/metadata/ItemMapping;", "localContentMapping", "insertNodes", "matchMapping", "dedupeDao", "Lcom/amazon/photos/discovery/dao/DedupeDao;", "matchAndPersistMappings", "", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ItemMappingUtils {
    private final Logger logger;
    private final Metrics metrics;
    private final NodeUtils nodeUtils;

    public ItemMappingUtils(@NotNull Metrics metrics, @NotNull Logger logger, @NotNull NodeUtils nodeUtils) {
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(nodeUtils, "nodeUtils");
        this.metrics = metrics;
        this.logger = logger;
        this.nodeUtils = nodeUtils;
    }

    private final List<String> getIgnoredNodeIds(long[] jArr, Collection<? extends NodeInfo> collection) {
        ArrayList arrayList = new ArrayList();
        Iterator<? extends NodeInfo> it2 = collection.iterator();
        for (long j : jArr) {
            NodeInfo next = it2.next();
            if (j < 0) {
                arrayList.add(next.getId());
            }
        }
        return arrayList;
    }

    private final Set<Long> getUnifiedIdsToMerge(LocalItem localItem, ItemMapping itemMapping, ItemMapping itemMapping2) {
        Set<Long> emptySet;
        Set<String> nodeIds = itemMapping.getNodeIds(localItem.getUnifiedId());
        if (nodeIds.isEmpty()) {
            emptySet = SetsKt__SetsKt.emptySet();
            return emptySet;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (String str : nodeIds) {
            Long cloudId = itemMapping2.getCloudId(str);
            CloudItem cloudItem = cloudId != null ? itemMapping2.getCloudItem(cloudId.longValue()) : null;
            if (cloudItem != null && cloudItem.getUnifiedId() != localItem.getUnifiedId()) {
                linkedHashSet.add(Long.valueOf(cloudItem.getUnifiedId()));
            }
            for (Long l : itemMapping.getUnifiedIds(str)) {
                long longValue = l.longValue();
                if (longValue != localItem.getUnifiedId()) {
                    linkedHashSet.add(Long.valueOf(longValue));
                }
            }
            if (!linkedHashSet.isEmpty()) {
                linkedHashSet.add(Long.valueOf(localItem.getUnifiedId()));
            }
        }
        return linkedHashSet;
    }

    private final long[] insertNodes(ItemMapping itemMapping, DedupeDao dedupeDao) {
        Collection<NodeInfo> nodes = itemMapping.getNodes();
        ArrayList arrayList = new ArrayList(nodes.size());
        for (NodeInfo nodeInfo : nodes) {
            String id = nodeInfo.getId();
            Intrinsics.checkExpressionValueIsNotNull(id, "node.id");
            MutableCloudItem mutableCloudItem = new MutableCloudItem(id);
            mutableCloudItem.setDateTaken(this.nodeUtils.getNodeContentDate(nodeInfo));
            mutableCloudItem.setDateUploaded(this.nodeUtils.getNodeCreatedDate(nodeInfo));
            mutableCloudItem.setMd5(this.nodeUtils.getNodeMD5(nodeInfo));
            mutableCloudItem.setVisualDigest(this.nodeUtils.getNodeVisualDigest(nodeInfo));
            String nodeId = nodeInfo.getId();
            Intrinsics.checkExpressionValueIsNotNull(nodeId, "nodeId");
            Long l = (Long) CollectionsKt.firstOrNull(itemMapping.getUnifiedIds(nodeId));
            if (l == null) {
                this.logger.e("ItemMappingUtils", "Unified id not present when attempting to save cloud item");
                this.metrics.recordSimpleEvent("ItemMappingUtils", DiscoveryMetrics.MddMissingUnifiedIdError, MetricRecordingType.STANDARD);
            } else {
                mutableCloudItem.setUnifiedId(l.longValue());
                arrayList.add(mutableCloudItem);
            }
        }
        if (arrayList.isEmpty()) {
            this.logger.v("ItemMappingUtils", "No new cloud items to insert into the DB.");
            return new long[0];
        }
        Logger logger = this.logger;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.US;
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.US");
        Object[] objArr = {Integer.valueOf(arrayList.size())};
        String format = String.format(locale, "Inserting %d cloud items into the DB.", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(locale, format, *args)");
        logger.v("ItemMappingUtils", format);
        return dedupeDao.insertCloudOrIgnore(arrayList);
    }

    public final void matchAndPersistMappings(@NotNull ItemMapping cloudContentMapping, @NotNull ItemMapping localContentMapping, @NotNull DedupeDao dedupeDao) {
        Intrinsics.checkParameterIsNotNull(cloudContentMapping, "cloudContentMapping");
        Intrinsics.checkParameterIsNotNull(localContentMapping, "localContentMapping");
        Intrinsics.checkParameterIsNotNull(dedupeDao, "dedupeDao");
        for (UnifiedItem unifiedItem : dedupeDao.getUnifiedByNodeIds(getIgnoredNodeIds(insertNodes(cloudContentMapping, dedupeDao), cloudContentMapping.getNodes()))) {
            localContentMapping.put(unifiedItem);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (LocalItem localItem : localContentMapping.getLocalItems()) {
            Set<Long> unifiedIdsToMerge = getUnifiedIdsToMerge(localItem, cloudContentMapping, localContentMapping);
            if (!unifiedIdsToMerge.isEmpty()) {
                linkedHashSet.add(unifiedIdsToMerge);
            }
        }
        Iterator it2 = linkedHashSet.iterator();
        while (it2.hasNext()) {
            Set unifiedIds = (Set) it2.next();
            Intrinsics.checkExpressionValueIsNotNull(unifiedIds, "unifiedIds");
            dedupeDao.combineByUnifiedIds(unifiedIds);
        }
    }
}
