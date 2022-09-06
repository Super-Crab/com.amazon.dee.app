package com.amazon.photos.discovery.internal.model;

import androidx.room.DatabaseView;
import androidx.room.Embedded;
import androidx.room.Ignore;
import androidx.room.Relation;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: MutableUnifiedItem.kt */
@DatabaseView("SELECT * FROM unified_item u LEFT JOIN (SELECT * FROM local_item) l ON l.unified_id = u.id LEFT JOIN (SELECT * FROM cloud_item) c ON c.unified_id = u.id ORDER BY u.id DESC")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0081\b\u0018\u00002\u00020\u0001B\u000f\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006¢\u0006\u0002\u0010\nJ\b\u0010\u0010\u001a\u00020\u0000H\u0016J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\u0006HÆ\u0003J3\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0096\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001d"}, d2 = {"Lcom/amazon/photos/discovery/internal/model/MutableUnifiedItem;", "", "unifiedEntry", "Lcom/amazon/photos/discovery/internal/model/MutableUnifiedEntry;", "(Lcom/amazon/photos/discovery/internal/model/MutableUnifiedEntry;)V", "localItems", "", "Lcom/amazon/photos/discovery/internal/model/MutableLocalItem;", "cloudItems", "Lcom/amazon/photos/discovery/internal/model/MutableCloudItem;", "(Lcom/amazon/photos/discovery/internal/model/MutableUnifiedEntry;Ljava/util/List;Ljava/util/List;)V", "getCloudItems", "()Ljava/util/List;", "getLocalItems", "getUnifiedEntry", "()Lcom/amazon/photos/discovery/internal/model/MutableUnifiedEntry;", "clone", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MutableUnifiedItem implements Cloneable {
    @Relation(entity = MutableCloudItem.class, entityColumn = "unified_id", parentColumn = "id")
    @NotNull
    private final List<MutableCloudItem> cloudItems;
    @Relation(entity = MutableLocalItem.class, entityColumn = "unified_id", parentColumn = "id")
    @NotNull
    private final List<MutableLocalItem> localItems;
    @Embedded
    @NotNull
    private final MutableUnifiedEntry unifiedEntry;

    public MutableUnifiedItem(@NotNull MutableUnifiedEntry unifiedEntry, @NotNull List<MutableLocalItem> localItems, @NotNull List<MutableCloudItem> cloudItems) {
        Intrinsics.checkParameterIsNotNull(unifiedEntry, "unifiedEntry");
        Intrinsics.checkParameterIsNotNull(localItems, "localItems");
        Intrinsics.checkParameterIsNotNull(cloudItems, "cloudItems");
        this.unifiedEntry = unifiedEntry;
        this.localItems = localItems;
        this.cloudItems = cloudItems;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MutableUnifiedItem copy$default(MutableUnifiedItem mutableUnifiedItem, MutableUnifiedEntry mutableUnifiedEntry, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            mutableUnifiedEntry = mutableUnifiedItem.unifiedEntry;
        }
        if ((i & 2) != 0) {
            list = mutableUnifiedItem.localItems;
        }
        if ((i & 4) != 0) {
            list2 = mutableUnifiedItem.cloudItems;
        }
        return mutableUnifiedItem.copy(mutableUnifiedEntry, list, list2);
    }

    @NotNull
    public final MutableUnifiedEntry component1() {
        return this.unifiedEntry;
    }

    @NotNull
    public final List<MutableLocalItem> component2() {
        return this.localItems;
    }

    @NotNull
    public final List<MutableCloudItem> component3() {
        return this.cloudItems;
    }

    @NotNull
    public final MutableUnifiedItem copy(@NotNull MutableUnifiedEntry unifiedEntry, @NotNull List<MutableLocalItem> localItems, @NotNull List<MutableCloudItem> cloudItems) {
        Intrinsics.checkParameterIsNotNull(unifiedEntry, "unifiedEntry");
        Intrinsics.checkParameterIsNotNull(localItems, "localItems");
        Intrinsics.checkParameterIsNotNull(cloudItems, "cloudItems");
        return new MutableUnifiedItem(unifiedEntry, localItems, cloudItems);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(MutableUnifiedItem.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj != null) {
            MutableUnifiedItem mutableUnifiedItem = (MutableUnifiedItem) obj;
            return !(Intrinsics.areEqual(this.unifiedEntry, mutableUnifiedItem.unifiedEntry) ^ true) && !(Intrinsics.areEqual(this.localItems, mutableUnifiedItem.localItems) ^ true) && !(Intrinsics.areEqual(this.cloudItems, mutableUnifiedItem.cloudItems) ^ true);
        }
        throw new TypeCastException("null cannot be cast to non-null type com.amazon.photos.discovery.internal.model.MutableUnifiedItem");
    }

    @NotNull
    public final List<MutableCloudItem> getCloudItems() {
        return this.cloudItems;
    }

    @NotNull
    public final List<MutableLocalItem> getLocalItems() {
        return this.localItems;
    }

    @NotNull
    public final MutableUnifiedEntry getUnifiedEntry() {
        return this.unifiedEntry;
    }

    public int hashCode() {
        return this.unifiedEntry.hashCode();
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MutableUnifiedItem(unifiedEntry=");
        outline107.append(this.unifiedEntry);
        outline107.append(", localItems=");
        outline107.append(this.localItems);
        outline107.append(", cloudItems=");
        return GeneratedOutlineSupport1.outline95(outline107, this.cloudItems, ")");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Ignore
    public MutableUnifiedItem(@NotNull MutableUnifiedEntry unifiedEntry) {
        this(unifiedEntry, new ArrayList(), new ArrayList());
        Intrinsics.checkParameterIsNotNull(unifiedEntry, "unifiedEntry");
    }

    @NotNull
    /* renamed from: clone */
    public MutableUnifiedItem m4341clone() {
        int collectionSizeOrDefault;
        int collectionSizeOrDefault2;
        MutableUnifiedEntry m4340clone = this.unifiedEntry.m4340clone();
        List<MutableLocalItem> list = this.localItems;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (MutableLocalItem mutableLocalItem : list) {
            arrayList.add(mutableLocalItem.m4339clone());
        }
        List<MutableCloudItem> list2 = this.cloudItems;
        collectionSizeOrDefault2 = CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10);
        ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault2);
        for (MutableCloudItem mutableCloudItem : list2) {
            arrayList2.add(mutableCloudItem.m4338clone());
        }
        return new MutableUnifiedItem(m4340clone, arrayList, arrayList2);
    }
}
