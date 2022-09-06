package com.amazon.photos.discovery.internal.dedupe.metadata;

import androidx.annotation.WorkerThread;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.cdasdk.cds.common.NodeInfo;
import com.amazon.photos.discovery.internal.util.NodeUtils;
import com.amazon.photos.discovery.model.LocalItem;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DateMatcher.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\bH\u0002J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0018\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u000bH\u0002R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/photos/discovery/internal/dedupe/metadata/DateMatcher;", "", "logger", "Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "nodeUtils", "Lcom/amazon/photos/discovery/internal/util/NodeUtils;", "(Lcom/amazon/clouddrive/android/core/interfaces/Logger;Lcom/amazon/photos/discovery/internal/util/NodeUtils;)V", "lastMatchingTimeZone", "Ljava/util/TimeZone;", "kotlin.jvm.PlatformType", "adjustToTimeZone", "", "utcDate", "timeZone", "isMatch", "", "node", "Lcom/amazon/clouddrive/cdasdk/cds/common/NodeInfo;", "localItem", "Lcom/amazon/photos/discovery/model/LocalItem;", "timeIsWithinWindow", "timeA", "timeB", "Companion", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DateMatcher {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "DateMatcher";
    public static final int TIME_MATCH_WINDOW = 2000;
    private static final List<TimeZone> TIME_ZONES;
    private TimeZone lastMatchingTimeZone;
    private final Logger logger;
    private final NodeUtils nodeUtils;

    /* compiled from: DateMatcher.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/photos/discovery/internal/dedupe/metadata/DateMatcher$Companion;", "", "()V", "TAG", "", "TIME_MATCH_WINDOW", "", "TIME_ZONES", "", "Ljava/util/TimeZone;", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str : TimeZone.getAvailableIDs()) {
            TimeZone timeZone = TimeZone.getTimeZone(str);
            Intrinsics.checkExpressionValueIsNotNull(timeZone, "timeZone");
            if (!linkedHashMap.containsKey(Integer.valueOf(timeZone.getRawOffset()))) {
                linkedHashMap.put(Integer.valueOf(timeZone.getRawOffset()), new ArrayList());
            }
            List<TimeZone> list = (List) linkedHashMap.get(Integer.valueOf(timeZone.getRawOffset()));
            if (list != null) {
                boolean z = true;
                for (TimeZone timeZone2 : list) {
                    z &= !timeZone.hasSameRules(timeZone2);
                }
                if (z) {
                    list.add(timeZone);
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, (List) entry.getValue());
        }
        TIME_ZONES = arrayList;
    }

    public DateMatcher(@NotNull Logger logger, @NotNull NodeUtils nodeUtils) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(nodeUtils, "nodeUtils");
        this.logger = logger;
        this.nodeUtils = nodeUtils;
        this.lastMatchingTimeZone = TimeZone.getDefault();
    }

    private final long adjustToTimeZone(long j, TimeZone timeZone) {
        return j + timeZone.getOffset(j);
    }

    private final boolean timeIsWithinWindow(long j, long j2) {
        return Math.abs(j - j2) < ((long) 2000);
    }

    @WorkerThread
    public final boolean isMatch(@NotNull NodeInfo node, @NotNull LocalItem localItem) {
        Intrinsics.checkParameterIsNotNull(node, "node");
        Intrinsics.checkParameterIsNotNull(localItem, "localItem");
        long nodeContentDate = this.nodeUtils.getNodeContentDate(node);
        if (nodeContentDate == 0) {
            Logger logger = this.logger;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Node with no date: ");
            outline107.append(node.getId());
            logger.w(TAG, outline107.toString());
            return false;
        }
        long dateTaken = localItem.getDateTaken();
        TimeZone lastMatchingTimeZone = this.lastMatchingTimeZone;
        Intrinsics.checkExpressionValueIsNotNull(lastMatchingTimeZone, "lastMatchingTimeZone");
        if (timeIsWithinWindow(nodeContentDate, adjustToTimeZone(dateTaken, lastMatchingTimeZone))) {
            return true;
        }
        for (TimeZone timeZone : TIME_ZONES) {
            if (timeIsWithinWindow(nodeContentDate, adjustToTimeZone(dateTaken, timeZone))) {
                this.lastMatchingTimeZone = timeZone;
                return true;
            }
        }
        return false;
    }
}
