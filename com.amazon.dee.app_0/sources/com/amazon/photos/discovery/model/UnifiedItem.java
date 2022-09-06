package com.amazon.photos.discovery.model;

import androidx.room.ColumnInfo;
import androidx.room.DatabaseView;
import androidx.room.Relation;
import com.amazon.photos.discovery.internal.model.MutableCloudItem;
import com.amazon.photos.discovery.internal.model.MutableLocalItem;
import com.amazon.photos.discovery.internal.worker.DedupeWorkerKt;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UnifiedItem.kt */
@DatabaseView("SELECT u.id, u.date_taken, u.date_uploaded, u.type, u.dedupe_stage FROM unified_item u LEFT JOIN (SELECT * FROM local_item) l ON l.unified_id = u.id LEFT JOIN (SELECT * FROM cloud_item) c ON c.unified_id = u.id ORDER BY u.id DESC")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000b¢\u0006\u0002\u0010\u000fJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\tHÆ\u0003J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000bHÆ\u0003J[\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000bHÆ\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020\tHÖ\u0001J\t\u0010'\u001a\u00020(HÖ\u0001R\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0016\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011¨\u0006)"}, d2 = {"Lcom/amazon/photos/discovery/model/UnifiedItem;", "", "id", "", "itemType", "Lcom/amazon/photos/discovery/model/ItemType;", "dateTaken", "dateUploaded", "dedupeStage", "", "localItems", "", "Lcom/amazon/photos/discovery/model/LocalItem;", "cloudItems", "Lcom/amazon/photos/discovery/model/CloudItem;", "(JLcom/amazon/photos/discovery/model/ItemType;JJILjava/util/List;Ljava/util/List;)V", "getCloudItems", "()Ljava/util/List;", "getDateTaken", "()J", "getDateUploaded", "getDedupeStage", "()I", "getId", "getItemType", "()Lcom/amazon/photos/discovery/model/ItemType;", "getLocalItems", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UnifiedItem {
    @Relation(entity = MutableCloudItem.class, entityColumn = "unified_id", parentColumn = "id")
    @NotNull
    private final List<CloudItem> cloudItems;
    @ColumnInfo(name = "date_taken")
    private final long dateTaken;
    @ColumnInfo(name = "date_uploaded")
    private final long dateUploaded;
    @ColumnInfo(name = DedupeWorkerKt.PARAM_DEDUPE_STAGE_ID)
    private final int dedupeStage;
    @ColumnInfo(name = "id")
    private final long id;
    @ColumnInfo(name = "type")
    @NotNull
    private final ItemType itemType;
    @Relation(entity = MutableLocalItem.class, entityColumn = "unified_id", parentColumn = "id")
    @NotNull
    private final List<LocalItem> localItems;

    public UnifiedItem(long j, @NotNull ItemType itemType, long j2, long j3, int i, @NotNull List<LocalItem> localItems, @NotNull List<CloudItem> cloudItems) {
        Intrinsics.checkParameterIsNotNull(itemType, "itemType");
        Intrinsics.checkParameterIsNotNull(localItems, "localItems");
        Intrinsics.checkParameterIsNotNull(cloudItems, "cloudItems");
        this.id = j;
        this.itemType = itemType;
        this.dateTaken = j2;
        this.dateUploaded = j3;
        this.dedupeStage = i;
        this.localItems = localItems;
        this.cloudItems = cloudItems;
    }

    public final long component1() {
        return this.id;
    }

    @NotNull
    public final ItemType component2() {
        return this.itemType;
    }

    public final long component3() {
        return this.dateTaken;
    }

    public final long component4() {
        return this.dateUploaded;
    }

    public final int component5() {
        return this.dedupeStage;
    }

    @NotNull
    public final List<LocalItem> component6() {
        return this.localItems;
    }

    @NotNull
    public final List<CloudItem> component7() {
        return this.cloudItems;
    }

    @NotNull
    public final UnifiedItem copy(long j, @NotNull ItemType itemType, long j2, long j3, int i, @NotNull List<LocalItem> localItems, @NotNull List<CloudItem> cloudItems) {
        Intrinsics.checkParameterIsNotNull(itemType, "itemType");
        Intrinsics.checkParameterIsNotNull(localItems, "localItems");
        Intrinsics.checkParameterIsNotNull(cloudItems, "cloudItems");
        return new UnifiedItem(j, itemType, j2, j3, i, localItems, cloudItems);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof UnifiedItem)) {
                return false;
            }
            UnifiedItem unifiedItem = (UnifiedItem) obj;
            return this.id == unifiedItem.id && Intrinsics.areEqual(this.itemType, unifiedItem.itemType) && this.dateTaken == unifiedItem.dateTaken && this.dateUploaded == unifiedItem.dateUploaded && this.dedupeStage == unifiedItem.dedupeStage && Intrinsics.areEqual(this.localItems, unifiedItem.localItems) && Intrinsics.areEqual(this.cloudItems, unifiedItem.cloudItems);
        }
        return true;
    }

    @NotNull
    public final List<CloudItem> getCloudItems() {
        return this.cloudItems;
    }

    public final long getDateTaken() {
        return this.dateTaken;
    }

    public final long getDateUploaded() {
        return this.dateUploaded;
    }

    public final int getDedupeStage() {
        return this.dedupeStage;
    }

    public final long getId() {
        return this.id;
    }

    @NotNull
    public final ItemType getItemType() {
        return this.itemType;
    }

    @NotNull
    public final List<LocalItem> getLocalItems() {
        return this.localItems;
    }

    public int hashCode() {
        long j = this.id;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        ItemType itemType = this.itemType;
        int i2 = 0;
        int hashCode = itemType != null ? itemType.hashCode() : 0;
        long j2 = this.dateTaken;
        long j3 = this.dateUploaded;
        int i3 = (((((((i + hashCode) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) ((j3 >>> 32) ^ j3))) * 31) + this.dedupeStage) * 31;
        List<LocalItem> list = this.localItems;
        int hashCode2 = (i3 + (list != null ? list.hashCode() : 0)) * 31;
        List<CloudItem> list2 = this.cloudItems;
        if (list2 != null) {
            i2 = list2.hashCode();
        }
        return hashCode2 + i2;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UnifiedItem(id=");
        outline107.append(this.id);
        outline107.append(", itemType=");
        outline107.append(this.itemType);
        outline107.append(", dateTaken=");
        outline107.append(this.dateTaken);
        outline107.append(", dateUploaded=");
        outline107.append(this.dateUploaded);
        outline107.append(", dedupeStage=");
        outline107.append(this.dedupeStage);
        outline107.append(", localItems=");
        outline107.append(this.localItems);
        outline107.append(", cloudItems=");
        return GeneratedOutlineSupport1.outline95(outline107, this.cloudItems, ")");
    }
}
