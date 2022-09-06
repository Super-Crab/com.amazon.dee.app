package com.amazon.photos.discovery.internal.dedupe.digest;

import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.discovery.dao.DedupeDao;
import com.amazon.photos.discovery.dedupe.RetryDedupeException;
import com.amazon.photos.discovery.internal.util.KotlinExtKt;
import com.amazon.photos.discovery.metrics.DiscoveryMetricsKt;
import com.amazon.photos.discovery.model.CloudItem;
import com.amazon.photos.discovery.model.LocalItem;
import com.amazon.photos.discovery.model.UnifiedItem;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: LocalDigestAssociator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\"\n\u0002\u0010 \n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0005\u001a\u00060\u0006R\u00020\u00002\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bJ$\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000f0\r*\b\u0012\u0004\u0012\u00020\t0\u0010H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/photos/discovery/internal/dedupe/digest/LocalDigestAssociator;", "", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "(Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "associateMatching", "Lcom/amazon/photos/discovery/internal/dedupe/digest/LocalDigestAssociator$LocalDigestAssociationResult;", "unifiedItems", "", "Lcom/amazon/photos/discovery/model/UnifiedItem;", "dedupeDao", "Lcom/amazon/photos/discovery/dao/DedupeDao;", "toMd5Map", "", "", "", "", "LocalDigestAssociationResult", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class LocalDigestAssociator {
    private final Metrics metrics;

    /* compiled from: LocalDigestAssociator.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0080\u0004\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/amazon/photos/discovery/internal/dedupe/digest/LocalDigestAssociator$LocalDigestAssociationResult;", "", "modifiedUnifiedIds", "", "", "unifiedItemsToProcess", "", "Lcom/amazon/photos/discovery/model/UnifiedItem;", "(Lcom/amazon/photos/discovery/internal/dedupe/digest/LocalDigestAssociator;Ljava/util/Set;Ljava/util/Collection;)V", "getModifiedUnifiedIds", "()Ljava/util/Set;", "getUnifiedItemsToProcess", "()Ljava/util/Collection;", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final class LocalDigestAssociationResult {
        @NotNull
        private final Set<Long> modifiedUnifiedIds;
        final /* synthetic */ LocalDigestAssociator this$0;
        @NotNull
        private final Collection<UnifiedItem> unifiedItemsToProcess;

        public LocalDigestAssociationResult(@NotNull LocalDigestAssociator localDigestAssociator, @NotNull Set<Long> modifiedUnifiedIds, Collection<UnifiedItem> unifiedItemsToProcess) {
            Intrinsics.checkParameterIsNotNull(modifiedUnifiedIds, "modifiedUnifiedIds");
            Intrinsics.checkParameterIsNotNull(unifiedItemsToProcess, "unifiedItemsToProcess");
            this.this$0 = localDigestAssociator;
            this.modifiedUnifiedIds = modifiedUnifiedIds;
            this.unifiedItemsToProcess = unifiedItemsToProcess;
        }

        @NotNull
        public final Set<Long> getModifiedUnifiedIds() {
            return this.modifiedUnifiedIds;
        }

        @NotNull
        public final Collection<UnifiedItem> getUnifiedItemsToProcess() {
            return this.unifiedItemsToProcess;
        }
    }

    public LocalDigestAssociator(@NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        this.metrics = metrics;
    }

    private final Map<String, Set<UnifiedItem>> toMd5Map(@NotNull List<UnifiedItem> list) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (UnifiedItem unifiedItem : list) {
            for (LocalItem localItem : unifiedItem.getLocalItems()) {
                String md5 = localItem.getMd5();
                if (md5 != null) {
                    KotlinExtKt.addToMapSet(linkedHashMap, md5, unifiedItem);
                }
            }
            for (CloudItem cloudItem : unifiedItem.getCloudItems()) {
                String md52 = cloudItem.getMd5();
                if (md52 != null) {
                    KotlinExtKt.addToMapSet(linkedHashMap, md52, unifiedItem);
                }
            }
        }
        return linkedHashMap;
    }

    @NotNull
    public final LocalDigestAssociationResult associateMatching(@NotNull Collection<UnifiedItem> unifiedItems, @NotNull DedupeDao dedupeDao) {
        List<String> distinct;
        Map<String, Set<UnifiedItem>> map;
        Set emptySet;
        Set<UnifiedItem> set;
        Set emptySet2;
        Intrinsics.checkParameterIsNotNull(unifiedItems, "unifiedItems");
        Intrinsics.checkParameterIsNotNull(dedupeDao, "dedupeDao");
        HashSet hashSet = new HashSet();
        ArrayList<LocalItem> arrayList = new ArrayList();
        for (UnifiedItem unifiedItem : unifiedItems) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, unifiedItem.getLocalItems());
        }
        ArrayList arrayList2 = new ArrayList();
        for (LocalItem localItem : arrayList) {
            String md5 = localItem.getMd5();
            if (md5 != null) {
                arrayList2.add(md5);
            }
        }
        distinct = CollectionsKt___CollectionsKt.distinct(arrayList2);
        if (distinct.isEmpty()) {
            emptySet2 = SetsKt__SetsKt.emptySet();
            return new LocalDigestAssociationResult(this, emptySet2, unifiedItems);
        }
        Metrics metrics = this.metrics;
        Unit unit = null;
        try {
            map = toMd5Map(dedupeDao.getUnifiedByMd5s(distinct));
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline155(DiscoveryMetricsKt.DB_ERROR_UNIFIED_MD5, metrics, DiscoveryMetricsKt.DB_ERROR_COMPONENT, e);
            map = null;
        }
        if (map != null) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (LocalItem localItem2 : arrayList) {
                String md52 = localItem2.getMd5();
                if (md52 != null && (set = map.get(md52)) != null) {
                    for (UnifiedItem unifiedItem2 : set) {
                        if (unifiedItem2.getId() != localItem2.getUnifiedId()) {
                            KotlinExtKt.addToMapSet(linkedHashMap, md52, Long.valueOf(unifiedItem2.getId()));
                            KotlinExtKt.addToMapSet(linkedHashMap, md52, Long.valueOf(localItem2.getUnifiedId()));
                        }
                    }
                }
            }
            if (linkedHashMap.isEmpty()) {
                emptySet = SetsKt__SetsKt.emptySet();
                return new LocalDigestAssociationResult(this, emptySet, unifiedItems);
            }
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Metrics metrics2 = this.metrics;
            try {
                dedupeDao.executeTransaction(new LocalDigestAssociator$associateMatching$$inlined$catchDb$lambda$1(dedupeDao, linkedHashMap, hashSet, linkedHashSet));
                unit = Unit.INSTANCE;
            } catch (Exception e2) {
                GeneratedOutlineSupport1.outline155(DiscoveryMetricsKt.DB_ERROR_PERSIST_COMBINED, metrics2, DiscoveryMetricsKt.DB_ERROR_COMPONENT, e2);
            }
            if (unit != null) {
                ArrayList arrayList3 = new ArrayList();
                for (Object obj : unifiedItems) {
                    if (!linkedHashSet.contains(Long.valueOf(((UnifiedItem) obj).getId()))) {
                        arrayList3.add(obj);
                    }
                }
                return new LocalDigestAssociationResult(this, hashSet, arrayList3);
            }
            throw new RetryDedupeException("Failed to Persist items combined by Local Digest Associator");
        }
        throw new RetryDedupeException("Failed to read Unified Items for Local Digest Associator");
    }
}
