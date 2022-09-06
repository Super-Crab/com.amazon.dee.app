package com.amazon.photos.discovery.internal.dedupe.metadata;

import com.amazon.clouddrive.cdasdk.cds.search.AggregationMatch;
import com.amazon.photos.discovery.internal.util.DateUtils;
import com.sun.mail.imap.IMAPStore;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DayAggregations.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0013\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\u0018\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0002J\u0016\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000bJ\u0010\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u0015H\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\f\u0010\u000eR\u001e\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001b"}, d2 = {"Lcom/amazon/photos/discovery/internal/dedupe/metadata/DayAggregations;", "", "response", "Lcom/amazon/clouddrive/cdasdk/cds/search/AggregationResponse;", "(Lcom/amazon/clouddrive/cdasdk/cds/search/AggregationResponse;)V", "aggregations", "", "Lcom/amazon/clouddrive/cdasdk/cds/search/AggregationMatch;", "(Ljava/util/Collection;)V", "Ljava/util/HashMap;", "", "", "isEmpty", "", "()Z", "<set-?>", "totalCount", "getTotalCount", "()J", "getCountForRange", "start", "Ljava/util/Date;", "end", "utcStart", "utcEnd", "getCountOnDay", IMAPStore.ID_DATE, "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DayAggregations {
    private final HashMap<String, Long> aggregations;
    private long totalCount;

    public DayAggregations(@NotNull Collection<? extends AggregationMatch> aggregations) {
        Intrinsics.checkParameterIsNotNull(aggregations, "aggregations");
        this.aggregations = new HashMap<>();
        for (AggregationMatch aggregationMatch : aggregations) {
            String value = aggregationMatch.getValue();
            Intrinsics.checkExpressionValueIsNotNull(value, "aggregation.value");
            String substring = value.substring(0, 10);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            HashMap<String, Long> hashMap = this.aggregations;
            Long count = aggregationMatch.getCount();
            Intrinsics.checkExpressionValueIsNotNull(count, "aggregation.count");
            hashMap.put(substring, count);
            long j = this.totalCount;
            Long count2 = aggregationMatch.getCount();
            Intrinsics.checkExpressionValueIsNotNull(count2, "aggregation.count");
            this.totalCount = count2.longValue() + j;
        }
    }

    private final long getCountOnDay(Date date) {
        Long l = this.aggregations.get(DateUtils.INSTANCE.getDayString(date));
        if (l != null) {
            return l.longValue();
        }
        return 0L;
    }

    public final long getCountForRange(long j, long j2) {
        return getCountForRange(new Date(j), new Date(j2));
    }

    public final long getTotalCount() {
        return this.totalCount;
    }

    public final boolean isEmpty() {
        return this.totalCount == 0;
    }

    private final long getCountForRange(Date date, Date date2) {
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setTime(date);
        long j = 0;
        while (date.before(date2)) {
            j += getCountOnDay(date);
            calendar.add(5, 1);
            date = calendar.getTime();
            Intrinsics.checkExpressionValueIsNotNull(date, "calendar.time");
        }
        return j;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public DayAggregations(@org.jetbrains.annotations.NotNull com.amazon.clouddrive.cdasdk.cds.search.AggregationResponse r2) {
        /*
            r1 = this;
            java.lang.String r0 = "response"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            java.util.List r2 = r2.getAggregations()
            java.lang.String r0 = "response.aggregations"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r0)
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.discovery.internal.dedupe.metadata.DayAggregations.<init>(com.amazon.clouddrive.cdasdk.cds.search.AggregationResponse):void");
    }
}
