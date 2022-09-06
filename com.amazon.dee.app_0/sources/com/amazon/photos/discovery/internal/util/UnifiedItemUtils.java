package com.amazon.photos.discovery.internal.util;

import com.amazon.photos.discovery.model.UnifiedItem;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: UnifiedItemUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004¨\u0006\b"}, d2 = {"Lcom/amazon/photos/discovery/internal/util/UnifiedItemUtils;", "", "()V", "getUnifiedIds", "", "", "unifiedItems", "Lcom/amazon/photos/discovery/model/UnifiedItem;", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UnifiedItemUtils {
    public static final UnifiedItemUtils INSTANCE = new UnifiedItemUtils();

    private UnifiedItemUtils() {
    }

    @NotNull
    public final List<Long> getUnifiedIds(@NotNull List<UnifiedItem> unifiedItems) {
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(unifiedItems, "unifiedItems");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(unifiedItems, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (UnifiedItem unifiedItem : unifiedItems) {
            arrayList.add(Long.valueOf(unifiedItem.getId()));
        }
        return arrayList;
    }
}
