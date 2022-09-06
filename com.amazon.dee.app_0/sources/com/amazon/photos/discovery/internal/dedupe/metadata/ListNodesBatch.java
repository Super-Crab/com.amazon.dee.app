package com.amazon.photos.discovery.internal.dedupe.metadata;

import com.amazon.clouddrive.extended.model.BulkOperationType;
import com.amazon.photos.discovery.model.LocalItem;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ListNodesBatch.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u0007J\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010 \u001a\u00020\u000eJ\u0006\u0010!\u001a\u00020\"J\u0006\u0010#\u001a\u00020\"J\u0006\u0010$\u001a\u00020%R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR*\u0010\f\u001a\u001e\u0012\u0004\u0012\u00020\u000e\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00050\u000fj\b\u0012\u0004\u0012\u00020\u0005`\u00100\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\tR\u001e\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\t¨\u0006&"}, d2 = {"Lcom/amazon/photos/discovery/internal/dedupe/metadata/ListNodesBatch;", "", "()V", "_localItems", "Ljava/util/LinkedList;", "Lcom/amazon/photos/discovery/model/LocalItem;", "expectedResultCount", "", "getExpectedResultCount", "()J", "setExpectedResultCount", "(J)V", "fileNameMap", "Ljava/util/LinkedHashMap;", "", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "localItems", "", "getLocalItems", "()Ljava/util/List;", "<set-?>", "rangeEnd", "getRangeEnd", "rangeStart", "getRangeStart", BulkOperationType.add, "", "item", "itemRangeStart", "itemRangeEnd", "getLocalItemsByName", "fileName", "isEmpty", "", "isNotEmpty", "size", "", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ListNodesBatch {
    private long expectedResultCount;
    private final LinkedHashMap<String, ArrayList<LocalItem>> fileNameMap = new LinkedHashMap<>();
    private final LinkedList<LocalItem> _localItems = new LinkedList<>();
    @NotNull
    private final List<LocalItem> localItems = this._localItems;
    private long rangeStart = Long.MAX_VALUE;
    private long rangeEnd = Long.MIN_VALUE;

    public final void add(@NotNull LocalItem item, long j, long j2) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        String filePath = item.getFilePath();
        if (filePath != null) {
            String fileName = new File(filePath).getName();
            if (!this.fileNameMap.containsKey(fileName)) {
                LinkedHashMap<String, ArrayList<LocalItem>> linkedHashMap = this.fileNameMap;
                Intrinsics.checkExpressionValueIsNotNull(fileName, "fileName");
                linkedHashMap.put(fileName, new ArrayList<>());
            }
            this._localItems.add(item);
            ArrayList<LocalItem> arrayList = this.fileNameMap.get(fileName);
            if (arrayList != null) {
                arrayList.add(item);
            }
            if (j < this.rangeStart) {
                this.rangeStart = j;
            }
            if (j2 <= this.rangeEnd) {
                return;
            }
            this.rangeEnd = j2;
        }
    }

    public final long getExpectedResultCount() {
        return this.expectedResultCount;
    }

    @NotNull
    public final List<LocalItem> getLocalItems() {
        return this.localItems;
    }

    @NotNull
    public final List<LocalItem> getLocalItemsByName(@NotNull String fileName) {
        List<LocalItem> emptyList;
        Intrinsics.checkParameterIsNotNull(fileName, "fileName");
        ArrayList<LocalItem> arrayList = this.fileNameMap.get(fileName);
        if (arrayList != null) {
            return arrayList;
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    public final long getRangeEnd() {
        return this.rangeEnd;
    }

    public final long getRangeStart() {
        return this.rangeStart;
    }

    public final boolean isEmpty() {
        return this.localItems.isEmpty();
    }

    public final boolean isNotEmpty() {
        return !this.localItems.isEmpty();
    }

    public final void setExpectedResultCount(long j) {
        this.expectedResultCount = j;
    }

    public final int size() {
        return this.localItems.size();
    }
}
