package com.amazon.photos.discovery.internal.dedupe.metadata;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.cds.common.NodeInfo;
import com.amazon.photos.discovery.internal.util.NodeUtils;
import com.amazon.photos.discovery.metrics.DiscoveryMetrics;
import com.amazon.photos.discovery.model.ItemType;
import com.amazon.photos.discovery.model.LocalItem;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ItemDedupeLogic.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010J\u0018\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\"\u0010\u0014\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0015*\u0012\u0012\u0006\u0012\u0004\u0018\u0001H\u0015\u0012\u0006\u0012\u0004\u0018\u0001H\u00150\u0016H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/amazon/photos/discovery/internal/dedupe/metadata/ItemDedupeLogic;", "", "dateMatcher", "Lcom/amazon/photos/discovery/internal/dedupe/metadata/DateMatcher;", "nodeUtils", "Lcom/amazon/photos/discovery/internal/util/NodeUtils;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "(Lcom/amazon/photos/discovery/internal/dedupe/metadata/DateMatcher;Lcom/amazon/photos/discovery/internal/util/NodeUtils;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "logMissingResolutionMetric", "", "localItem", "Lcom/amazon/photos/discovery/model/LocalItem;", "metadataDedupe", "", "node", "Lcom/amazon/clouddrive/cdasdk/cds/common/NodeInfo;", "metadataDedupeImage", "metadataDedupeVideo", "shouldDedupeOnResolution", "bothValuesNonNull", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/Pair;", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ItemDedupeLogic {
    private final DateMatcher dateMatcher;
    private final Metrics metrics;
    private final NodeUtils nodeUtils;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ItemType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;

        static {
            $EnumSwitchMapping$0[ItemType.PHOTO.ordinal()] = 1;
            $EnumSwitchMapping$0[ItemType.VIDEO.ordinal()] = 2;
            $EnumSwitchMapping$1 = new int[ItemType.values().length];
            $EnumSwitchMapping$1[ItemType.PHOTO.ordinal()] = 1;
            $EnumSwitchMapping$1[ItemType.VIDEO.ordinal()] = 2;
            $EnumSwitchMapping$2 = new int[ItemType.values().length];
            $EnumSwitchMapping$2[ItemType.PHOTO.ordinal()] = 1;
            $EnumSwitchMapping$2[ItemType.VIDEO.ordinal()] = 2;
            $EnumSwitchMapping$3 = new int[ItemType.values().length];
            $EnumSwitchMapping$3[ItemType.PHOTO.ordinal()] = 1;
            $EnumSwitchMapping$3[ItemType.VIDEO.ordinal()] = 2;
        }
    }

    public ItemDedupeLogic(@NotNull DateMatcher dateMatcher, @NotNull NodeUtils nodeUtils, @NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(dateMatcher, "dateMatcher");
        Intrinsics.checkParameterIsNotNull(nodeUtils, "nodeUtils");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        this.dateMatcher = dateMatcher;
        this.nodeUtils = nodeUtils;
        this.metrics = metrics;
    }

    private final <T> boolean bothValuesNonNull(@NotNull Pair<? extends T, ? extends T> pair) {
        boolean z;
        List<T> list = TuplesKt.toList(pair);
        if (!(list instanceof Collection) || !list.isEmpty()) {
            for (T t : list) {
                if (t != null) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (!z) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    private final void logMissingResolutionMetric(LocalItem localItem) {
        DiscoveryMetrics discoveryMetrics;
        boolean z = (localItem.getWidth() == null || localItem.getHeight() == null) ? false : true;
        if (z) {
            int i = WhenMappings.$EnumSwitchMapping$1[localItem.getType().ordinal()];
            if (i == 1) {
                discoveryMetrics = DiscoveryMetrics.MddCloudImageResolutionMissing;
            } else if (i != 2) {
                throw new NoWhenBranchMatchedException();
            } else {
                discoveryMetrics = DiscoveryMetrics.MddCloudVideoResolutionMissing;
            }
        } else if (z) {
            throw new NoWhenBranchMatchedException();
        } else {
            int i2 = WhenMappings.$EnumSwitchMapping$2[localItem.getType().ordinal()];
            if (i2 == 1) {
                discoveryMetrics = DiscoveryMetrics.MddLocalImageResolutionMissing;
            } else if (i2 != 2) {
                throw new NoWhenBranchMatchedException();
            } else {
                discoveryMetrics = DiscoveryMetrics.MddLocalVideoResolutionMissing;
            }
        }
        this.metrics.recordSimpleEvent("ItemDedupeLogic", discoveryMetrics, MetricRecordingType.STANDARD);
    }

    private final boolean shouldDedupeOnResolution(LocalItem localItem, NodeInfo nodeInfo) {
        boolean z = (localItem.getWidth() == null || localItem.getHeight() == null) ? false : true;
        int i = WhenMappings.$EnumSwitchMapping$3[localItem.getType().ordinal()];
        if (i != 1) {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            if (!z || !bothValuesNonNull(this.nodeUtils.getNodeVideoResolution(nodeInfo))) {
                return false;
            }
        } else if (!z || !bothValuesNonNull(this.nodeUtils.getNodeImageResolution(nodeInfo))) {
            return false;
        }
        return true;
    }

    public final boolean metadataDedupe(@NotNull LocalItem localItem, @NotNull NodeInfo node) {
        Intrinsics.checkParameterIsNotNull(localItem, "localItem");
        Intrinsics.checkParameterIsNotNull(node, "node");
        int i = WhenMappings.$EnumSwitchMapping$0[localItem.getType().ordinal()];
        if (i != 1) {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            return metadataDedupeVideo(localItem, node);
        }
        return metadataDedupeImage(localItem, node);
    }

    public final boolean metadataDedupeImage(@NotNull LocalItem localItem, @NotNull NodeInfo node) {
        Intrinsics.checkParameterIsNotNull(localItem, "localItem");
        Intrinsics.checkParameterIsNotNull(node, "node");
        if (shouldDedupeOnResolution(localItem, node)) {
            Pair<Integer, Integer> nodeImageResolution = this.nodeUtils.getNodeImageResolution(node);
            Long width = localItem.getWidth();
            Integer first = nodeImageResolution.getFirst();
            Long l = null;
            if (!Intrinsics.areEqual(width, first != null ? Long.valueOf(first.intValue()) : null)) {
                return false;
            }
            Long height = localItem.getHeight();
            Integer second = nodeImageResolution.getSecond();
            if (second != null) {
                l = Long.valueOf(second.intValue());
            }
            if (!Intrinsics.areEqual(height, l)) {
                return false;
            }
        } else {
            logMissingResolutionMetric(localItem);
        }
        return this.dateMatcher.isMatch(node, localItem);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x005a, code lost:
        if ((!kotlin.jvm.internal.Intrinsics.areEqual(r3, r5)) != false) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean metadataDedupeVideo(@org.jetbrains.annotations.NotNull com.amazon.photos.discovery.model.LocalItem r9, @org.jetbrains.annotations.NotNull com.amazon.clouddrive.cdasdk.cds.common.NodeInfo r10) {
        /*
            r8 = this;
            java.lang.String r0 = "localItem"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0)
            java.lang.String r0 = "node"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r10, r0)
            com.amazon.photos.discovery.internal.dedupe.metadata.DateMatcher r0 = r8.dateMatcher
            boolean r0 = r0.isMatch(r10, r9)
            r1 = 0
            if (r0 != 0) goto L14
            return r1
        L14:
            boolean r0 = r8.shouldDedupeOnResolution(r9, r10)
            r2 = 1
            if (r0 == 0) goto L5d
            com.amazon.photos.discovery.internal.util.NodeUtils r0 = r8.nodeUtils
            kotlin.Pair r0 = r0.getNodeVideoResolution(r10)
            java.lang.Long r3 = r9.getWidth()
            java.lang.Object r4 = r0.getFirst()
            java.lang.Integer r4 = (java.lang.Integer) r4
            r5 = 0
            if (r4 == 0) goto L38
            int r4 = r4.intValue()
            long r6 = (long) r4
            java.lang.Long r4 = java.lang.Long.valueOf(r6)
            goto L39
        L38:
            r4 = r5
        L39:
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r4)
            r3 = r3 ^ r2
            if (r3 != 0) goto L5c
            java.lang.Long r3 = r9.getHeight()
            java.lang.Object r0 = r0.getSecond()
            java.lang.Integer r0 = (java.lang.Integer) r0
            if (r0 == 0) goto L55
            int r0 = r0.intValue()
            long r4 = (long) r0
            java.lang.Long r5 = java.lang.Long.valueOf(r4)
        L55:
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r5)
            r0 = r0 ^ r2
            if (r0 == 0) goto L60
        L5c:
            return r1
        L5d:
            r8.logMissingResolutionMetric(r9)
        L60:
            com.amazon.photos.discovery.internal.util.NodeUtils r0 = r8.nodeUtils
            java.lang.Double r10 = r0.getNodeVideoDuration(r10)
            java.lang.Long r0 = r9.getDuration()
            if (r0 == 0) goto L84
            if (r10 != 0) goto L6f
            goto L84
        L6f:
            java.lang.Long r9 = r9.getDuration()
            double r3 = r10.doubleValue()
            long r3 = (long) r3
            if (r9 != 0) goto L7b
            goto L84
        L7b:
            long r9 = r9.longValue()
            int r9 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r9 != 0) goto L84
            r1 = r2
        L84:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.discovery.internal.dedupe.metadata.ItemDedupeLogic.metadataDedupeVideo(com.amazon.photos.discovery.model.LocalItem, com.amazon.clouddrive.cdasdk.cds.common.NodeInfo):boolean");
    }
}
