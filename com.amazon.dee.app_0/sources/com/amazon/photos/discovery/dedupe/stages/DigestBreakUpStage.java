package com.amazon.photos.discovery.dedupe.stages;

import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.discovery.dao.DedupeDao;
import com.amazon.photos.discovery.dedupe.DeduplicationRequest;
import com.amazon.photos.discovery.dedupe.Deduplicator;
import com.amazon.photos.discovery.dedupe.DeduplicatorResult;
import com.amazon.photos.discovery.dedupe.RetryDedupeException;
import com.amazon.photos.discovery.internal.ConstantsKt;
import com.amazon.photos.discovery.internal.Injectable;
import com.amazon.photos.discovery.internal.dagger.component.DiscoveryComponent;
import com.amazon.photos.discovery.internal.model.MutableUnifiedEntry;
import com.amazon.photos.discovery.metrics.DiscoveryMetrics;
import com.amazon.photos.discovery.metrics.DiscoveryMetricsKt;
import com.amazon.photos.discovery.model.CloudItem;
import com.amazon.photos.discovery.model.LocalItem;
import com.amazon.photos.discovery.model.UnifiedItem;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt___SetsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DigestBreakUpStage.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u0001\u001cB\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016R\u001e\u0010\u0004\u001a\u00020\u00058\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001e\u0010\n\u001a\u00020\u000b8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u001d"}, d2 = {"Lcom/amazon/photos/discovery/dedupe/stages/DigestBreakUpStage;", "Lcom/amazon/photos/discovery/dedupe/Deduplicator;", "Lcom/amazon/photos/discovery/internal/Injectable;", "()V", "logger", "Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "getLogger$AndroidPhotosDiscovery_release", "()Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "setLogger$AndroidPhotosDiscovery_release", "(Lcom/amazon/clouddrive/android/core/interfaces/Logger;)V", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "getMetrics$AndroidPhotosDiscovery_release", "()Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "setMetrics$AndroidPhotosDiscovery_release", "(Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "breakUpIfNeeded", "", "request", "Lcom/amazon/photos/discovery/dedupe/DeduplicationRequest;", "unifiedItem", "Lcom/amazon/photos/discovery/model/UnifiedItem;", "deduplicate", "Lcom/amazon/photos/discovery/dedupe/DeduplicatorResult;", "inject", "", JsonFields.COMPONENT, "Lcom/amazon/photos/discovery/internal/dagger/component/DiscoveryComponent;", "SplitIntoNewGroupTransaction", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DigestBreakUpStage implements Deduplicator, Injectable {
    @Inject
    @NotNull
    public Logger logger;
    @Inject
    @NotNull
    public Metrics metrics;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DigestBreakUpStage.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B1\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\t¢\u0006\u0002\u0010\rJ\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0011\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0002H\u0096\u0002R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/photos/discovery/dedupe/stages/DigestBreakUpStage$SplitIntoNewGroupTransaction;", "Lkotlin/Function1;", "Lcom/amazon/photos/discovery/dao/DedupeDao;", "", "request", "Lcom/amazon/photos/discovery/dedupe/DeduplicationRequest;", "originalUnifiedItem", "Lcom/amazon/photos/discovery/model/UnifiedItem;", "localItems", "", "Lcom/amazon/photos/discovery/model/LocalItem;", "cloudItems", "Lcom/amazon/photos/discovery/model/CloudItem;", "(Lcom/amazon/photos/discovery/dedupe/stages/DigestBreakUpStage;Lcom/amazon/photos/discovery/dedupe/DeduplicationRequest;Lcom/amazon/photos/discovery/model/UnifiedItem;Ljava/util/List;Ljava/util/List;)V", "forkNewUnifiedEntry", "Lcom/amazon/photos/discovery/internal/model/MutableUnifiedEntry;", "synced", "", "invoke", "dedupeDao", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final class SplitIntoNewGroupTransaction implements Function1<DedupeDao, Unit> {
        private final List<CloudItem> cloudItems;
        private final List<LocalItem> localItems;
        private final UnifiedItem originalUnifiedItem;
        private final DeduplicationRequest request;
        final /* synthetic */ DigestBreakUpStage this$0;

        public SplitIntoNewGroupTransaction(@NotNull DigestBreakUpStage digestBreakUpStage, @NotNull DeduplicationRequest request, @NotNull UnifiedItem originalUnifiedItem, @NotNull List<LocalItem> localItems, List<CloudItem> cloudItems) {
            Intrinsics.checkParameterIsNotNull(request, "request");
            Intrinsics.checkParameterIsNotNull(originalUnifiedItem, "originalUnifiedItem");
            Intrinsics.checkParameterIsNotNull(localItems, "localItems");
            Intrinsics.checkParameterIsNotNull(cloudItems, "cloudItems");
            this.this$0 = digestBreakUpStage;
            this.request = request;
            this.originalUnifiedItem = originalUnifiedItem;
            this.localItems = localItems;
            this.cloudItems = cloudItems;
        }

        private final MutableUnifiedEntry forkNewUnifiedEntry(DeduplicationRequest deduplicationRequest, boolean z) {
            List<MutableUnifiedEntry> listOf;
            long first;
            long first2;
            MutableUnifiedEntry mutableUnifiedEntry = new MutableUnifiedEntry(0L, this.originalUnifiedItem.getItemType(), this.originalUnifiedItem.getDateTaken(), this.originalUnifiedItem.getDateUploaded(), deduplicationRequest.getStageId(), z);
            DedupeDao dedupeDao = deduplicationRequest.getDedupeDao();
            listOf = CollectionsKt__CollectionsJVMKt.listOf(mutableUnifiedEntry);
            long[] insertUnified = dedupeDao.insertUnified(listOf);
            if (insertUnified != null) {
                if (insertUnified.length == 0) {
                    return null;
                }
                first = ArraysKt___ArraysKt.first(insertUnified);
                if (first < 0) {
                    return null;
                }
                first2 = ArraysKt___ArraysKt.first(insertUnified);
                mutableUnifiedEntry.setId(first2);
                return mutableUnifiedEntry;
            }
            return null;
        }

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: invoke */
        public /* bridge */ /* synthetic */ Unit mo12165invoke(DedupeDao dedupeDao) {
            invoke2(dedupeDao);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public void invoke2(@NotNull DedupeDao dedupeDao) {
            int collectionSizeOrDefault;
            int collectionSizeOrDefault2;
            List listOf;
            Intrinsics.checkParameterIsNotNull(dedupeDao, "dedupeDao");
            List<LocalItem> list = this.localItems;
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
            ArrayList<Number> arrayList = new ArrayList(collectionSizeOrDefault);
            for (LocalItem localItem : list) {
                arrayList.add(Long.valueOf(localItem.getId()));
            }
            List<CloudItem> list2 = this.cloudItems;
            collectionSizeOrDefault2 = CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10);
            ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault2);
            for (CloudItem cloudItem : list2) {
                arrayList2.add(Long.valueOf(cloudItem.getId()));
            }
            if (this.localItems.isEmpty()) {
                this.this$0.getLogger$AndroidPhotosDiscovery_release().d(ConstantsKt.DIGEST_BREAK_UP_STAGE, "Orphaning a cloud-only group");
                this.this$0.getMetrics$AndroidPhotosDiscovery_release().recordSimpleEvent(ConstantsKt.DIGEST_BREAK_UP_STAGE, DiscoveryMetrics.OphaningCloudOnlyGroup, new MetricRecordingType[0]);
                dedupeDao.updateCloudUnifiedId(arrayList2, -1L);
            } else if (((LocalItem) CollectionsKt.first((List<? extends Object>) this.localItems)).getMd5() == null) {
                this.this$0.getLogger$AndroidPhotosDiscovery_release().d(ConstantsKt.DIGEST_BREAK_UP_STAGE, "Fixing a null MD5 group");
                this.this$0.getMetrics$AndroidPhotosDiscovery_release().recordSimpleEvent(ConstantsKt.DIGEST_BREAK_UP_STAGE, DiscoveryMetrics.BreakupNullMd5Items, new MetricRecordingType[0]);
                dedupeDao.updateCloudUnifiedId(arrayList2, -1L);
                for (Number number : arrayList) {
                    long longValue = number.longValue();
                    MutableUnifiedEntry forkNewUnifiedEntry = forkNewUnifiedEntry(this.request, false);
                    if (forkNewUnifiedEntry != null) {
                        listOf = CollectionsKt__CollectionsJVMKt.listOf(Long.valueOf(longValue));
                        dedupeDao.updateLocalUnifiedId(listOf, forkNewUnifiedEntry.getId());
                    } else {
                        Logger logger$AndroidPhotosDiscovery_release = this.this$0.getLogger$AndroidPhotosDiscovery_release();
                        logger$AndroidPhotosDiscovery_release.e(ConstantsKt.DIGEST_BREAK_UP_STAGE, "Failed to split a null MD5 local item from its group: " + longValue);
                    }
                }
            } else {
                MutableUnifiedEntry forkNewUnifiedEntry2 = forkNewUnifiedEntry(this.request, !arrayList2.isEmpty());
                if (forkNewUnifiedEntry2 != null) {
                    dedupeDao.updateCloudUnifiedId(arrayList2, forkNewUnifiedEntry2.getId());
                    dedupeDao.updateLocalUnifiedId(arrayList, forkNewUnifiedEntry2.getId());
                    return;
                }
                Logger logger$AndroidPhotosDiscovery_release2 = this.this$0.getLogger$AndroidPhotosDiscovery_release();
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to break out items into a new group, original unified id: ");
                outline107.append(this.originalUnifiedItem.getId());
                logger$AndroidPhotosDiscovery_release2.e(ConstantsKt.DIGEST_BREAK_UP_STAGE, outline107.toString());
            }
        }
    }

    private final boolean breakUpIfNeeded(DeduplicationRequest deduplicationRequest, UnifiedItem unifiedItem) {
        Set plus;
        List<String> sortedWith;
        int collectionSizeOrDefault;
        Unit unit;
        List<LocalItem> localItems = unifiedItem.getLocalItems();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : localItems) {
            String md5 = ((LocalItem) obj).getMd5();
            Object obj2 = linkedHashMap.get(md5);
            if (obj2 == null) {
                obj2 = new ArrayList();
                linkedHashMap.put(md5, obj2);
            }
            ((List) obj2).add(obj);
        }
        List<CloudItem> cloudItems = unifiedItem.getCloudItems();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Object obj3 : cloudItems) {
            String md52 = ((CloudItem) obj3).getMd5();
            Object obj4 = linkedHashMap2.get(md52);
            if (obj4 == null) {
                obj4 = new ArrayList();
                linkedHashMap2.put(md52, obj4);
            }
            ((List) obj4).add(obj3);
        }
        plus = SetsKt___SetsKt.plus((Set) linkedHashMap.keySet(), (Iterable) linkedHashMap2.keySet());
        sortedWith = CollectionsKt___CollectionsKt.sortedWith(plus, new Comparator<T>() { // from class: com.amazon.photos.discovery.dedupe.stages.DigestBreakUpStage$breakUpIfNeeded$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int compareValues;
                compareValues = ComparisonsKt__ComparisonsKt.compareValues((String) t, (String) t2);
                return compareValues;
            }
        });
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(sortedWith, 10);
        ArrayList<Pair> arrayList = new ArrayList(collectionSizeOrDefault);
        for (String str : sortedWith) {
            List list = (List) linkedHashMap.get(str);
            if (list == null) {
                list = CollectionsKt__CollectionsKt.emptyList();
            }
            List list2 = (List) linkedHashMap2.get(str);
            if (list2 == null) {
                list2 = CollectionsKt__CollectionsKt.emptyList();
            }
            arrayList.add(new Pair(list, list2));
        }
        if (arrayList.isEmpty()) {
            Logger logger = this.logger;
            if (logger == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            logger.d(ConstantsKt.DIGEST_BREAK_UP_STAGE, "Was given an orphaned unified item to evaluate");
            return false;
        }
        if (arrayList.size() == 1) {
            if ((linkedHashMap.isEmpty() ^ true ? (String) CollectionsKt.first(linkedHashMap.keySet()) : (String) CollectionsKt.first(linkedHashMap2.keySet())) != null) {
                return false;
            }
        }
        for (Pair pair : arrayList) {
            Logger logger2 = this.logger;
            if (logger2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Breaking out a new group of ");
            outline107.append(((List) pair.getFirst()).size());
            outline107.append(" local items and ");
            outline107.append(((List) pair.getSecond()).size());
            outline107.append(" cloud items");
            logger2.d(ConstantsKt.DIGEST_BREAK_UP_STAGE, outline107.toString());
            SplitIntoNewGroupTransaction splitIntoNewGroupTransaction = new SplitIntoNewGroupTransaction(this, deduplicationRequest, unifiedItem, (List) pair.getFirst(), (List) pair.getSecond());
            Metrics metrics = this.metrics;
            if (metrics == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metrics");
            }
            try {
                deduplicationRequest.getDedupeDao().executeTransaction(splitIntoNewGroupTransaction);
                unit = Unit.INSTANCE;
                continue;
            } catch (Exception e) {
                GeneratedOutlineSupport1.outline155(DiscoveryMetricsKt.DB_ERROR_PERSIST_BREAKUPS, metrics, DiscoveryMetricsKt.DB_ERROR_COMPONENT, e);
                unit = null;
                continue;
            }
            if (unit == null) {
                throw new RetryDedupeException("Failed to persist Digest Breakup");
            }
        }
        return true;
    }

    @Override // com.amazon.photos.discovery.dedupe.Deduplicator
    @NotNull
    public DeduplicatorResult deduplicate(@NotNull DeduplicationRequest request) {
        Intrinsics.checkParameterIsNotNull(request, "request");
        Logger logger = this.logger;
        if (logger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Processing a request of ");
        outline107.append(request.getUnifiedItems().size());
        outline107.append(" unified items");
        logger.d(ConstantsKt.DIGEST_BREAK_UP_STAGE, outline107.toString());
        int i = 0;
        for (UnifiedItem unifiedItem : request.getUnifiedItems()) {
            if (breakUpIfNeeded(request, unifiedItem)) {
                i++;
            }
        }
        return new DeduplicatorResult(i, ConstantsKt.DIGEST_BREAK_UP_STAGE);
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

    @Override // com.amazon.photos.discovery.internal.Injectable
    public void inject(@NotNull DiscoveryComponent component) {
        Intrinsics.checkParameterIsNotNull(component, "component");
        component.inject(this);
    }

    public final void setLogger$AndroidPhotosDiscovery_release(@NotNull Logger logger) {
        Intrinsics.checkParameterIsNotNull(logger, "<set-?>");
        this.logger = logger;
    }

    public final void setMetrics$AndroidPhotosDiscovery_release(@NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(metrics, "<set-?>");
        this.metrics = metrics;
    }
}
