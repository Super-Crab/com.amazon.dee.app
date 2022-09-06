package com.amazon.photos.discovery.internal.util;

import com.amazon.photos.discovery.internal.model.MutableCloudItem;
import com.amazon.photos.discovery.internal.model.MutableLocalItem;
import com.amazon.photos.discovery.internal.model.MutableUnifiedEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: UnifiedEntryUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\t\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006J\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bJ\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J \u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000fH\u0002J\u0018\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\tH\u0002J\u0018\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0007H\u0002¨\u0006\u0018"}, d2 = {"Lcom/amazon/photos/discovery/internal/util/UnifiedEntryUtils;", "", "()V", "create", "Lcom/amazon/photos/discovery/internal/model/MutableUnifiedEntry;", "localItems", "", "Lcom/amazon/photos/discovery/internal/model/MutableLocalItem;", "cloudItems", "Lcom/amazon/photos/discovery/internal/model/MutableCloudItem;", "createUnifiedEntries", "", "shouldUpdateDate", "", "unifiedEntryDate", "", "otherDate", "shouldUpdateId", "unifiedEntry", "otherUnifiedId", "otherDateTaken", "update", "cloudItem", "localItem", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UnifiedEntryUtils {
    public static final UnifiedEntryUtils INSTANCE = new UnifiedEntryUtils();

    private UnifiedEntryUtils() {
    }

    private final boolean shouldUpdateDate(long j, long j2) {
        return j2 != 0 && (j == 0 || j2 < j);
    }

    private final boolean shouldUpdateId(MutableUnifiedEntry mutableUnifiedEntry, long j, long j2) {
        return (mutableUnifiedEntry.getId() < 1 && j > 0) || (j > 0 && (shouldUpdateDate(mutableUnifiedEntry.getDateTaken(), j2) || (j2 == 0 && mutableUnifiedEntry.getDateTaken() == 0 && j < mutableUnifiedEntry.getId())));
    }

    private final MutableUnifiedEntry update(MutableUnifiedEntry mutableUnifiedEntry, MutableLocalItem mutableLocalItem) {
        if (shouldUpdateId(mutableUnifiedEntry, mutableLocalItem.getUnifiedId(), mutableLocalItem.getDateTaken())) {
            mutableUnifiedEntry.setId(mutableLocalItem.getUnifiedId());
        }
        if (shouldUpdateDate(mutableUnifiedEntry.getDateTaken(), mutableLocalItem.getDateTaken())) {
            mutableUnifiedEntry.setDateTaken(mutableLocalItem.getDateTaken());
        }
        return mutableUnifiedEntry;
    }

    @NotNull
    public final MutableUnifiedEntry create(@NotNull Collection<MutableLocalItem> localItems, @NotNull Collection<MutableCloudItem> cloudItems) {
        List<Cloneable> plus;
        Intrinsics.checkParameterIsNotNull(localItems, "localItems");
        Intrinsics.checkParameterIsNotNull(cloudItems, "cloudItems");
        MutableUnifiedEntry mutableUnifiedEntry = new MutableUnifiedEntry();
        plus = CollectionsKt___CollectionsKt.plus((Collection) localItems, (Iterable) cloudItems);
        for (Cloneable cloneable : plus) {
            if (cloneable instanceof MutableLocalItem) {
                INSTANCE.update(mutableUnifiedEntry, (MutableLocalItem) cloneable);
            } else if (cloneable instanceof MutableCloudItem) {
                INSTANCE.update(mutableUnifiedEntry, (MutableCloudItem) cloneable);
            }
        }
        return mutableUnifiedEntry;
    }

    @NotNull
    public final List<MutableUnifiedEntry> createUnifiedEntries(@NotNull List<MutableLocalItem> localItems) {
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(localItems, "localItems");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(localItems, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (MutableLocalItem mutableLocalItem : localItems) {
            MutableUnifiedEntry mutableUnifiedEntry = new MutableUnifiedEntry();
            mutableUnifiedEntry.setDateTaken(mutableLocalItem.getDateTaken());
            mutableUnifiedEntry.setItemType(mutableLocalItem.getItemType());
            arrayList.add(mutableUnifiedEntry);
        }
        return arrayList;
    }

    private final MutableUnifiedEntry update(MutableUnifiedEntry mutableUnifiedEntry, MutableCloudItem mutableCloudItem) {
        if (shouldUpdateDate(mutableUnifiedEntry.getDateUploaded(), mutableCloudItem.getDateUploaded())) {
            mutableUnifiedEntry.setDateUploaded(mutableCloudItem.getDateUploaded());
        }
        return mutableUnifiedEntry;
    }
}
