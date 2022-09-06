package com.amazon.photos.discovery.dao;

import androidx.annotation.AnyThread;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.photos.discovery.model.ItemType;
import com.amazon.photos.discovery.model.UnifiedItem;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt__SetsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: UnifiedItemDao.kt */
@Dao
@AnyThread
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H'J.\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00042\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH'JD\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u00072\u0006\u0010\u000f\u001a\u00020\t2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\u00072\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH'J\u0014\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H'J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u0014H'JB\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u00072\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\u00072\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u00072\u0006\u0010\n\u001a\u00020\u00042\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH'J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u0014H'¨\u0006\u0018"}, d2 = {"Lcom/amazon/photos/discovery/dao/UnifiedItemDao;", "", "getAllItems", "Landroidx/paging/DataSource$Factory;", "", "Lcom/amazon/photos/discovery/model/UnifiedItem;", "getAllUnifiedItemsByFolder", "", "folderId", "", "dedupeStage", "itemTypes", "", "Lcom/amazon/photos/discovery/model/ItemType;", "getAllUnifiedItemsInFoldersByBatch", "startPoint", "folderIds", MetricsUtil.LegacyMetricTypes.LIMIT, "getAllValidItems", "getItemCount", "Landroidx/lifecycle/LiveData;", "getUnifiedItemsByIdsAndFolders", "unifiedItemIds", "getValidItemCount", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface UnifiedItemDao {

    /* compiled from: UnifiedItemDao.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class DefaultImpls {
        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ List getAllUnifiedItemsByFolder$default(UnifiedItemDao unifiedItemDao, long j, int i, Set set, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 4) != 0) {
                    set = SetsKt__SetsKt.setOf((Object[]) new ItemType[]{ItemType.PHOTO, ItemType.VIDEO});
                }
                return unifiedItemDao.getAllUnifiedItemsByFolder(j, i, set);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getAllUnifiedItemsByFolder");
        }

        public static /* synthetic */ List getAllUnifiedItemsInFoldersByBatch$default(UnifiedItemDao unifiedItemDao, long j, List list, int i, int i2, Set set, int i3, Object obj) {
            Set of;
            if (obj == null) {
                Set set2 = set;
                if ((i3 & 16) != 0) {
                    of = SetsKt__SetsKt.setOf((Object[]) new ItemType[]{ItemType.PHOTO, ItemType.VIDEO});
                    set2 = of;
                }
                return unifiedItemDao.getAllUnifiedItemsInFoldersByBatch(j, list, i, i2, set2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getAllUnifiedItemsInFoldersByBatch");
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ List getUnifiedItemsByIdsAndFolders$default(UnifiedItemDao unifiedItemDao, List list, List list2, int i, Set set, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 8) != 0) {
                    set = SetsKt__SetsKt.setOf((Object[]) new ItemType[]{ItemType.PHOTO, ItemType.VIDEO});
                }
                return unifiedItemDao.getUnifiedItemsByIdsAndFolders(list, list2, i, set);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getUnifiedItemsByIdsAndFolders");
        }
    }

    @Query("SELECT u.id, u.date_taken, u.date_uploaded, u.type, u.dedupe_stage FROM unified_item u LEFT JOIN (SELECT * FROM local_item) l ON l.unified_id = u.id LEFT JOIN (SELECT * FROM cloud_item) c ON c.unified_id = u.id ORDER BY u.id DESC")
    @Transaction
    @NotNull
    DataSource.Factory<Integer, UnifiedItem> getAllItems();

    @Query("SELECT u.id, u.date_taken, u.date_uploaded, u.type, u.dedupe_stage FROM unified_item u JOIN local_item l on u.id = l.unified_id AND l.size > 0 AND l.parent_id = :folderId AND l.type IN (:itemTypes) LEFT JOIN cloud_item c on u.id = c.unified_id WHERE c.unified_id IS NULL AND u.dedupe_stage = :dedupeStage ORDER BY u.id DESC")
    @Transaction
    @NotNull
    List<UnifiedItem> getAllUnifiedItemsByFolder(long j, int i, @NotNull Set<? extends ItemType> set);

    @Query("SELECT u.id, u.date_taken, u.date_uploaded, u.type, u.dedupe_stage FROM unified_item u JOIN local_item l on u.id = l.unified_id AND l.size > 0 AND l.parent_id IN (:folderIds) AND l.type IN (:itemTypes) LEFT JOIN cloud_item c on u.id = c.unified_id WHERE c.unified_id IS NULL AND u.id > :startPoint AND u.dedupe_stage = :dedupeStage ORDER BY u.id ASC LIMIT :limit ")
    @Transaction
    @NotNull
    List<UnifiedItem> getAllUnifiedItemsInFoldersByBatch(long j, @NotNull List<Long> list, int i, int i2, @NotNull Set<? extends ItemType> set);

    @Query("SELECT u.id, u.date_taken, u.date_uploaded, u.type, u.dedupe_stage FROM unified_item u LEFT JOIN (SELECT * FROM local_item WHERE size > 0) l ON l.unified_id = u.id LEFT JOIN (SELECT * FROM cloud_item) c ON c.unified_id = u.id ORDER BY u.id DESC")
    @Transaction
    @NotNull
    DataSource.Factory<Integer, UnifiedItem> getAllValidItems();

    @Query("SELECT COUNT(id) FROM unified_item")
    @NotNull
    LiveData<Integer> getItemCount();

    @Query("SELECT u.id, u.date_taken, u.date_uploaded, u.type, u.dedupe_stage FROM unified_item u JOIN local_item l on u.id = l.unified_id AND l.size > 0 AND l.parent_id IN (:folderIds) AND l.type IN (:itemTypes) LEFT JOIN cloud_item c on u.id = c.unified_id WHERE c.unified_id IS NULL AND u.dedupe_stage = :dedupeStage AND u.id IN (:unifiedItemIds) ORDER BY u.id DESC")
    @Transaction
    @NotNull
    List<UnifiedItem> getUnifiedItemsByIdsAndFolders(@NotNull List<Long> list, @NotNull List<Long> list2, int i, @NotNull Set<? extends ItemType> set);

    @Query("SELECT COUNT(DISTINCT unified_id) FROM local_item WHERE size > 0")
    @NotNull
    LiveData<Integer> getValidItemCount();
}
