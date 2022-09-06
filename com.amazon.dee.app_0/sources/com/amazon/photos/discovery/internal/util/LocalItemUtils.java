package com.amazon.photos.discovery.internal.util;

import com.amazon.photos.discovery.internal.model.MutableLocalItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: LocalItemUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0016\n\u0002\u0010\t\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007J\u001c\u0010\t\u001a\u00020\n2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u000b2\u0006\u0010\f\u001a\u00020\rJ\"\u0010\t\u001a\u00020\n2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000b¨\u0006\u000f"}, d2 = {"Lcom/amazon/photos/discovery/internal/util/LocalItemUtils;", "", "()V", "getFilePaths", "", "", "localItems", "", "Lcom/amazon/photos/discovery/internal/model/MutableLocalItem;", "updateUnifiedIds", "", "", "dbUnifiedIds", "", "", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class LocalItemUtils {
    public static final LocalItemUtils INSTANCE = new LocalItemUtils();

    private LocalItemUtils() {
    }

    @NotNull
    public final Set<String> getFilePaths(@NotNull Collection<MutableLocalItem> localItems) {
        int collectionSizeOrDefault;
        Set<String> set;
        Intrinsics.checkParameterIsNotNull(localItems, "localItems");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(localItems, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (MutableLocalItem mutableLocalItem : localItems) {
            arrayList.add(mutableLocalItem.getFilePath());
        }
        set = CollectionsKt___CollectionsKt.toSet(arrayList);
        return set;
    }

    public final void updateUnifiedIds(@NotNull List<MutableLocalItem> localItems, @NotNull List<Long> dbUnifiedIds) {
        long[] longArray;
        Intrinsics.checkParameterIsNotNull(localItems, "localItems");
        Intrinsics.checkParameterIsNotNull(dbUnifiedIds, "dbUnifiedIds");
        longArray = CollectionsKt___CollectionsKt.toLongArray(dbUnifiedIds);
        updateUnifiedIds(localItems, longArray);
    }

    public final void updateUnifiedIds(@NotNull List<MutableLocalItem> localItems, @NotNull long[] dbUnifiedIds) {
        Intrinsics.checkParameterIsNotNull(localItems, "localItems");
        Intrinsics.checkParameterIsNotNull(dbUnifiedIds, "dbUnifiedIds");
        int length = dbUnifiedIds.length;
        for (int i = 0; i < length; i++) {
            localItems.get(i).setUnifiedId(dbUnifiedIds[i]);
        }
    }
}
