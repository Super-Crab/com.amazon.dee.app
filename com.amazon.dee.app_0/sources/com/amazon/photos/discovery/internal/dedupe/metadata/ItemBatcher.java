package com.amazon.photos.discovery.internal.dedupe.metadata;

import androidx.annotation.WorkerThread;
import com.amazon.photos.discovery.internal.util.DateUtils;
import com.amazon.photos.discovery.model.LocalItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ItemBatcher.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010*\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0007\b\u0001\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002J\u0016\u0010\u000f\u001a\u00020\n2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0011H\u0002J\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\n0\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u0015J(\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/amazon/photos/discovery/internal/dedupe/metadata/ItemBatcher;", "", "dayAggregations", "Lcom/amazon/photos/discovery/internal/dedupe/metadata/DayAggregations;", "(Lcom/amazon/photos/discovery/internal/dedupe/metadata/DayAggregations;)V", "addItemToBatch", "", "item", "Lcom/amazon/photos/discovery/model/LocalItem;", "listNodesBatch", "Lcom/amazon/photos/discovery/internal/dedupe/metadata/ListNodesBatch;", "computeEnd", "", "utcTime", "computeStart", "createBatch", "localItemItr", "", "createBatches", "", "localItems", "", "sameDayRanges", "startA", "endA", "startB", "endB", "Companion", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
@WorkerThread
/* loaded from: classes13.dex */
public final class ItemBatcher {
    public static final Companion Companion = new Companion(null);
    private static final int MAX_BATCH_RESULTS = 200;
    private static final long TIMEZONE_MAX_NEG_OFFSET;
    private static final long TIMEZONE_MAX_POS_OFFSET;
    private final DayAggregations dayAggregations;

    /* compiled from: ItemBatcher.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/photos/discovery/internal/dedupe/metadata/ItemBatcher$Companion;", "", "()V", "MAX_BATCH_RESULTS", "", "TIMEZONE_MAX_NEG_OFFSET", "", "TIMEZONE_MAX_POS_OFFSET", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        long j = 2000;
        TIMEZONE_MAX_POS_OFFSET = TimeUnit.HOURS.toMillis(14L) + j;
        TIMEZONE_MAX_NEG_OFFSET = TimeUnit.HOURS.toMillis(12L) + j;
    }

    public ItemBatcher(@NotNull DayAggregations dayAggregations) {
        Intrinsics.checkParameterIsNotNull(dayAggregations, "dayAggregations");
        this.dayAggregations = dayAggregations;
    }

    private final boolean addItemToBatch(LocalItem localItem, ListNodesBatch listNodesBatch) {
        if (localItem.getDateTaken() == 0) {
            return true;
        }
        long computeStart = computeStart(localItem.getDateTaken());
        long computeEnd = computeEnd(localItem.getDateTaken());
        long countForRange = this.dayAggregations.getCountForRange(computeStart, computeEnd);
        if (countForRange == 0) {
            return true;
        }
        if (listNodesBatch.getLocalItems().isEmpty()) {
            listNodesBatch.add(localItem, computeStart, computeEnd);
            listNodesBatch.setExpectedResultCount(countForRange);
            return true;
        }
        long rangeStart = listNodesBatch.getRangeStart();
        long rangeEnd = listNodesBatch.getRangeEnd();
        long j = computeStart < rangeStart ? computeStart : rangeStart;
        long j2 = computeEnd > rangeEnd ? computeEnd : rangeEnd;
        long j3 = j2;
        long j4 = j;
        if (sameDayRanges(j, j2, rangeStart, rangeEnd)) {
            listNodesBatch.add(localItem, rangeStart, rangeEnd);
        } else {
            long countForRange2 = this.dayAggregations.getCountForRange(j4, j3);
            if (countForRange2 != listNodesBatch.getExpectedResultCount() && countForRange2 > 200) {
                return false;
            }
            listNodesBatch.add(localItem, computeStart, computeEnd);
            listNodesBatch.setExpectedResultCount(countForRange2);
        }
        return true;
    }

    private final long computeEnd(long j) {
        return j + TIMEZONE_MAX_POS_OFFSET;
    }

    private final long computeStart(long j) {
        return j - TIMEZONE_MAX_NEG_OFFSET;
    }

    private final ListNodesBatch createBatch(ListIterator<LocalItem> listIterator) {
        ListNodesBatch listNodesBatch = new ListNodesBatch();
        boolean z = false;
        while (listIterator.hasNext() && !z) {
            z = !addItemToBatch(listIterator.next(), listNodesBatch);
            if (z) {
                listIterator.previous();
            }
        }
        return listNodesBatch;
    }

    private final boolean sameDayRanges(long j, long j2, long j3, long j4) {
        return Intrinsics.areEqual(DateUtils.INSTANCE.getDayString(j), DateUtils.INSTANCE.getDayString(j3)) && Intrinsics.areEqual(DateUtils.INSTANCE.getDayString(j2), DateUtils.INSTANCE.getDayString(j4));
    }

    @NotNull
    public final List<ListNodesBatch> createBatches(@NotNull Collection<LocalItem> localItems) {
        Intrinsics.checkParameterIsNotNull(localItems, "localItems");
        ArrayList arrayList = new ArrayList();
        ListIterator<LocalItem> listIterator = new ArrayList(localItems).listIterator();
        Intrinsics.checkExpressionValueIsNotNull(listIterator, "ArrayList(localItems).listIterator()");
        while (listIterator.hasNext()) {
            ListNodesBatch createBatch = createBatch(listIterator);
            if (createBatch.isNotEmpty()) {
                arrayList.add(createBatch);
            }
        }
        return arrayList;
    }
}
