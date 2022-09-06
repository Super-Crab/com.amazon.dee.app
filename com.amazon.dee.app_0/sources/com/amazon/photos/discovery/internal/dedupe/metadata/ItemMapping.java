package com.amazon.photos.discovery.internal.dedupe.metadata;

import androidx.annotation.WorkerThread;
import androidx.exifinterface.media.ExifInterface;
import com.amazon.clouddrive.cdasdk.cds.common.NodeInfo;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.amazon.photos.discovery.internal.util.KotlinExtKt;
import com.amazon.photos.discovery.model.CloudItem;
import com.amazon.photos.discovery.model.LocalItem;
import com.amazon.photos.discovery.model.UnifiedItem;
import com.dee.app.metrics.MetricsConstants;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ItemMapping.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\b\u0013\b\u0001\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u0012J\u0015\u0010\"\u001a\u0004\u0018\u00010\u00052\u0006\u0010#\u001a\u00020\u0010¢\u0006\u0002\u0010$J\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00050&2\u0006\u0010 \u001a\u00020\u0005J\u0010\u0010'\u001a\u0004\u0018\u00010\u00062\u0006\u0010(\u001a\u00020\u0005J\u0014\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00050&2\u0006\u0010 \u001a\u00020\u0005J\u0010\u0010*\u001a\u0004\u0018\u00010\f2\u0006\u0010+\u001a\u00020\u0005J\u0010\u0010,\u001a\u0004\u0018\u00010\u00122\u0006\u0010#\u001a\u00020\u0010J\u0014\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00100&2\u0006\u0010 \u001a\u00020\u0005J\u0014\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00050&2\u0006\u0010#\u001a\u00020\u0010J\u0010\u0010/\u001a\u0004\u0018\u00010\u001b2\u0006\u0010 \u001a\u00020\u0005J\u000e\u00100\u001a\u00020\u001f2\u0006\u00101\u001a\u00020\u001bJ\u0014\u00102\u001a\u00020\u001f2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\bJ?\u00103\u001a\u00020\u001f\"\u0004\b\u0000\u00104\"\u0004\b\u0001\u00105*\u0014\u0012\u0004\u0012\u0002H4\u0012\n\u0012\b\u0012\u0004\u0012\u0002H50\u00140\u00042\u0006\u00106\u001a\u0002H42\u0006\u00107\u001a\u0002H5H\u0002¢\u0006\u0002\u00108R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\f0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\b8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\nR\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00120\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0013\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00140\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00120\b8F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\nR \u0010\u0017\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00140\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00140\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0019\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00140\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001b0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\b8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\n¨\u00069"}, d2 = {"Lcom/amazon/photos/discovery/internal/dedupe/metadata/ItemMapping;", "", "()V", "cloudIdToCloudItem", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lcom/amazon/photos/discovery/model/CloudItem;", "cloudItems", "", "getCloudItems", "()Ljava/util/Collection;", "localIdToLocalItem", "Lcom/amazon/photos/discovery/model/LocalItem;", "localItems", "getLocalItems", "nodeIdToCloudId", "", "nodeIdToNode", "Lcom/amazon/clouddrive/cdasdk/cds/common/NodeInfo;", "nodeIdToUnifiedIds", "", "nodes", "getNodes", "unifiedIdToCloudIds", "unifiedIdToLocalIds", "unifiedIdToNodeIds", "unifiedIdToUnifiedItem", "Lcom/amazon/photos/discovery/model/UnifiedItem;", "unifiedItems", "getUnifiedItems", "associate", "", "unifiedId", "node", "getCloudId", AlexaDeviceBackgroundImageBridge.KEY_NODE_ID, "(Ljava/lang/String;)Ljava/lang/Long;", "getCloudIds", "", "getCloudItem", "cloudId", "getLocalIds", "getLocalItem", "localId", "getNode", "getNodeIds", "getUnifiedIds", "getUnifiedItem", MetricsConstants.Method.CACHE_PUT, "unifiedItem", "putAll", "addToConcurrentMapSet", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "key", "value", "(Ljava/util/concurrent/ConcurrentHashMap;Ljava/lang/Object;Ljava/lang/Object;)V", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
@WorkerThread
/* loaded from: classes13.dex */
public final class ItemMapping {
    private final ConcurrentHashMap<Long, UnifiedItem> unifiedIdToUnifiedItem = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Long, LocalItem> localIdToLocalItem = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Long, CloudItem> cloudIdToCloudItem = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Long, Set<Long>> unifiedIdToLocalIds = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Long, Set<Long>> unifiedIdToCloudIds = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, NodeInfo> nodeIdToNode = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Long> nodeIdToCloudId = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Set<Long>> nodeIdToUnifiedIds = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Long, Set<String>> unifiedIdToNodeIds = new ConcurrentHashMap<>();

    private final <K, V> void addToConcurrentMapSet(@NotNull ConcurrentHashMap<K, Set<V>> concurrentHashMap, K k, V v) {
        Set<V> set = concurrentHashMap.get(k);
        if (set != null) {
            set.add(v);
            return;
        }
        Set<V> newSetFromMap = Collections.newSetFromMap(new ConcurrentHashMap());
        newSetFromMap.add(v);
        concurrentHashMap.put(k, newSetFromMap);
    }

    public final void associate(long j, @NotNull NodeInfo node) {
        Intrinsics.checkParameterIsNotNull(node, "node");
        ConcurrentHashMap<String, NodeInfo> concurrentHashMap = this.nodeIdToNode;
        String id = node.getId();
        Intrinsics.checkExpressionValueIsNotNull(id, "node.id");
        concurrentHashMap.put(id, node);
        ConcurrentHashMap<String, Set<Long>> concurrentHashMap2 = this.nodeIdToUnifiedIds;
        String id2 = node.getId();
        Intrinsics.checkExpressionValueIsNotNull(id2, "node.id");
        addToConcurrentMapSet(concurrentHashMap2, id2, Long.valueOf(j));
        ConcurrentHashMap<Long, Set<String>> concurrentHashMap3 = this.unifiedIdToNodeIds;
        Long valueOf = Long.valueOf(j);
        String id3 = node.getId();
        Intrinsics.checkExpressionValueIsNotNull(id3, "node.id");
        addToConcurrentMapSet(concurrentHashMap3, valueOf, id3);
    }

    @Nullable
    public final Long getCloudId(@NotNull String nodeId) {
        Intrinsics.checkParameterIsNotNull(nodeId, "nodeId");
        return this.nodeIdToCloudId.get(nodeId);
    }

    @NotNull
    public final Set<Long> getCloudIds(long j) {
        Set<Long> emptySet;
        Set<Long> set = this.unifiedIdToCloudIds.get(Long.valueOf(j));
        if (set != null) {
            return set;
        }
        emptySet = SetsKt__SetsKt.emptySet();
        return emptySet;
    }

    @Nullable
    public final CloudItem getCloudItem(long j) {
        return this.cloudIdToCloudItem.get(Long.valueOf(j));
    }

    @NotNull
    public final Collection<CloudItem> getCloudItems() {
        Set set;
        Collection<CloudItem> values = this.cloudIdToCloudItem.values();
        Intrinsics.checkExpressionValueIsNotNull(values, "cloudIdToCloudItem.values");
        set = CollectionsKt___CollectionsKt.toSet(values);
        return set;
    }

    @NotNull
    public final Set<Long> getLocalIds(long j) {
        Set<Long> emptySet;
        Set<Long> set = this.unifiedIdToLocalIds.get(Long.valueOf(j));
        if (set != null) {
            return set;
        }
        emptySet = SetsKt__SetsKt.emptySet();
        return emptySet;
    }

    @Nullable
    public final LocalItem getLocalItem(long j) {
        return this.localIdToLocalItem.get(Long.valueOf(j));
    }

    @NotNull
    public final Collection<LocalItem> getLocalItems() {
        Set set;
        Collection<LocalItem> values = this.localIdToLocalItem.values();
        Intrinsics.checkExpressionValueIsNotNull(values, "localIdToLocalItem.values");
        set = CollectionsKt___CollectionsKt.toSet(values);
        return set;
    }

    @Nullable
    public final NodeInfo getNode(@NotNull String nodeId) {
        Intrinsics.checkParameterIsNotNull(nodeId, "nodeId");
        return this.nodeIdToNode.get(nodeId);
    }

    @NotNull
    public final Set<String> getNodeIds(long j) {
        Set<String> emptySet;
        Set<String> set = this.unifiedIdToNodeIds.get(Long.valueOf(j));
        if (set != null) {
            return set;
        }
        emptySet = SetsKt__SetsKt.emptySet();
        return emptySet;
    }

    @NotNull
    public final Collection<NodeInfo> getNodes() {
        Set set;
        Collection<NodeInfo> values = this.nodeIdToNode.values();
        Intrinsics.checkExpressionValueIsNotNull(values, "nodeIdToNode.values");
        set = CollectionsKt___CollectionsKt.toSet(values);
        return set;
    }

    @NotNull
    public final Set<Long> getUnifiedIds(@NotNull String nodeId) {
        Set<Long> emptySet;
        Intrinsics.checkParameterIsNotNull(nodeId, "nodeId");
        Set<Long> set = this.nodeIdToUnifiedIds.get(nodeId);
        if (set != null) {
            return set;
        }
        emptySet = SetsKt__SetsKt.emptySet();
        return emptySet;
    }

    @Nullable
    public final UnifiedItem getUnifiedItem(long j) {
        return this.unifiedIdToUnifiedItem.get(Long.valueOf(j));
    }

    @NotNull
    public final Collection<UnifiedItem> getUnifiedItems() {
        Set set;
        Collection<UnifiedItem> values = this.unifiedIdToUnifiedItem.values();
        Intrinsics.checkExpressionValueIsNotNull(values, "unifiedIdToUnifiedItem.values");
        set = CollectionsKt___CollectionsKt.toSet(values);
        return set;
    }

    public final void put(@NotNull UnifiedItem unifiedItem) {
        Intrinsics.checkParameterIsNotNull(unifiedItem, "unifiedItem");
        this.unifiedIdToUnifiedItem.put(Long.valueOf(unifiedItem.getId()), unifiedItem);
        ConcurrentHashMap<Long, Set<Long>> concurrentHashMap = this.unifiedIdToLocalIds;
        Long valueOf = Long.valueOf(unifiedItem.getId());
        Set<Long> newSetFromMap = Collections.newSetFromMap(new ConcurrentHashMap());
        Intrinsics.checkExpressionValueIsNotNull(newSetFromMap, "Collections.newSetFromMap(ConcurrentHashMap())");
        concurrentHashMap.put(valueOf, newSetFromMap);
        ConcurrentHashMap<Long, Set<Long>> concurrentHashMap2 = this.unifiedIdToCloudIds;
        Long valueOf2 = Long.valueOf(unifiedItem.getId());
        Set<Long> newSetFromMap2 = Collections.newSetFromMap(new ConcurrentHashMap());
        Intrinsics.checkExpressionValueIsNotNull(newSetFromMap2, "Collections.newSetFromMap(ConcurrentHashMap())");
        concurrentHashMap2.put(valueOf2, newSetFromMap2);
        ConcurrentHashMap<Long, Set<String>> concurrentHashMap3 = this.unifiedIdToNodeIds;
        Long valueOf3 = Long.valueOf(unifiedItem.getId());
        Set<String> newSetFromMap3 = Collections.newSetFromMap(new ConcurrentHashMap());
        Intrinsics.checkExpressionValueIsNotNull(newSetFromMap3, "Collections.newSetFromMap(ConcurrentHashMap())");
        concurrentHashMap3.put(valueOf3, newSetFromMap3);
        Set<Long> set = this.unifiedIdToLocalIds.get(Long.valueOf(unifiedItem.getId()));
        for (LocalItem localItem : unifiedItem.getLocalItems()) {
            this.localIdToLocalItem.put(Long.valueOf(localItem.getId()), localItem);
            if (set != null) {
                set.add(Long.valueOf(localItem.getId()));
            }
        }
        Set<Long> set2 = this.unifiedIdToCloudIds.get(Long.valueOf(unifiedItem.getId()));
        Set<String> set3 = this.unifiedIdToNodeIds.get(Long.valueOf(unifiedItem.getId()));
        for (CloudItem cloudItem : unifiedItem.getCloudItems()) {
            this.cloudIdToCloudItem.put(Long.valueOf(cloudItem.getId()), cloudItem);
            if (set2 != null) {
                set2.add(Long.valueOf(cloudItem.getId()));
            }
            this.nodeIdToCloudId.put(cloudItem.getNodeId(), Long.valueOf(cloudItem.getId()));
            KotlinExtKt.addToMapSet(this.nodeIdToUnifiedIds, cloudItem.getNodeId(), Long.valueOf(unifiedItem.getId()));
            if (set3 != null) {
                set3.add(cloudItem.getNodeId());
            }
        }
    }

    public final void putAll(@NotNull Collection<UnifiedItem> unifiedItems) {
        Intrinsics.checkParameterIsNotNull(unifiedItems, "unifiedItems");
        for (UnifiedItem unifiedItem : unifiedItems) {
            put(unifiedItem);
        }
    }
}
