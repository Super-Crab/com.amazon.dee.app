package com.amazon.photos.discovery.dedupe;

import com.amazon.photos.discovery.dao.DedupeDao;
import com.amazon.photos.discovery.model.UnifiedItem;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DeduplicationRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0012\u001a\u00020\bHÆ\u0003J-\u0010\u0013\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\bHÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/amazon/photos/discovery/dedupe/DeduplicationRequest;", "", "unifiedItems", "", "Lcom/amazon/photos/discovery/model/UnifiedItem;", "dedupeDao", "Lcom/amazon/photos/discovery/dao/DedupeDao;", "stageId", "", "(Ljava/util/Collection;Lcom/amazon/photos/discovery/dao/DedupeDao;I)V", "getDedupeDao", "()Lcom/amazon/photos/discovery/dao/DedupeDao;", "getStageId", "()I", "getUnifiedItems", "()Ljava/util/Collection;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DeduplicationRequest {
    @NotNull
    private final DedupeDao dedupeDao;
    private final int stageId;
    @NotNull
    private final Collection<UnifiedItem> unifiedItems;

    public DeduplicationRequest(@NotNull Collection<UnifiedItem> unifiedItems, @NotNull DedupeDao dedupeDao, int i) {
        Intrinsics.checkParameterIsNotNull(unifiedItems, "unifiedItems");
        Intrinsics.checkParameterIsNotNull(dedupeDao, "dedupeDao");
        this.unifiedItems = unifiedItems;
        this.dedupeDao = dedupeDao;
        this.stageId = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DeduplicationRequest copy$default(DeduplicationRequest deduplicationRequest, Collection collection, DedupeDao dedupeDao, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            collection = deduplicationRequest.unifiedItems;
        }
        if ((i2 & 2) != 0) {
            dedupeDao = deduplicationRequest.dedupeDao;
        }
        if ((i2 & 4) != 0) {
            i = deduplicationRequest.stageId;
        }
        return deduplicationRequest.copy(collection, dedupeDao, i);
    }

    @NotNull
    public final Collection<UnifiedItem> component1() {
        return this.unifiedItems;
    }

    @NotNull
    public final DedupeDao component2() {
        return this.dedupeDao;
    }

    public final int component3() {
        return this.stageId;
    }

    @NotNull
    public final DeduplicationRequest copy(@NotNull Collection<UnifiedItem> unifiedItems, @NotNull DedupeDao dedupeDao, int i) {
        Intrinsics.checkParameterIsNotNull(unifiedItems, "unifiedItems");
        Intrinsics.checkParameterIsNotNull(dedupeDao, "dedupeDao");
        return new DeduplicationRequest(unifiedItems, dedupeDao, i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof DeduplicationRequest)) {
                return false;
            }
            DeduplicationRequest deduplicationRequest = (DeduplicationRequest) obj;
            return Intrinsics.areEqual(this.unifiedItems, deduplicationRequest.unifiedItems) && Intrinsics.areEqual(this.dedupeDao, deduplicationRequest.dedupeDao) && this.stageId == deduplicationRequest.stageId;
        }
        return true;
    }

    @NotNull
    public final DedupeDao getDedupeDao() {
        return this.dedupeDao;
    }

    public final int getStageId() {
        return this.stageId;
    }

    @NotNull
    public final Collection<UnifiedItem> getUnifiedItems() {
        return this.unifiedItems;
    }

    public int hashCode() {
        Collection<UnifiedItem> collection = this.unifiedItems;
        int i = 0;
        int hashCode = (collection != null ? collection.hashCode() : 0) * 31;
        DedupeDao dedupeDao = this.dedupeDao;
        if (dedupeDao != null) {
            i = dedupeDao.hashCode();
        }
        return ((hashCode + i) * 31) + this.stageId;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DeduplicationRequest(unifiedItems=");
        outline107.append(this.unifiedItems);
        outline107.append(", dedupeDao=");
        outline107.append(this.dedupeDao);
        outline107.append(", stageId=");
        return GeneratedOutlineSupport1.outline86(outline107, this.stageId, ")");
    }
}
