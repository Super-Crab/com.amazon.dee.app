package com.amazon.photos.discovery.internal.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import com.amazon.alexa.accessory.frames.contacts.ContactsModuleConstants;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.photos.discovery.internal.model.ItemTypeCount;
import com.amazon.photos.discovery.internal.model.MutableCloudItem;
import com.amazon.photos.discovery.internal.model.MutableLocalFolder;
import com.amazon.photos.discovery.internal.model.MutableLocalItem;
import com.amazon.photos.discovery.internal.model.MutableUnifiedEntry;
import com.amazon.photos.discovery.model.ItemType;
import com.amazon.photos.discovery.model.UnifiedItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: WorkerDao.kt */
@Dao
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0016\n\u0002\b\t\b!\u0018\u0000 Q2\u00020\u0001:\u0001QB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H'J!\u0010\u0007\u001a\u00020\u00042\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\t\"\u00020\nH'¢\u0006\u0002\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H'J\b\u0010\r\u001a\u00020\u0004H'J\b\u0010\u000e\u001a\u00020\u0004H'J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H'J+\u0010\u0010\u001a\u00020\u00042!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00040\u0012H\u0017J\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u001aH'J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH'J\b\u0010\u001f\u001a\u00020\u001cH'J\b\u0010 \u001a\u00020\u001cH'J\u0010\u0010!\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020\u001cH'J\b\u0010#\u001a\u00020\u001cH'J\u0010\u0010$\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020&H'J\u000e\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0017H'J\u000e\u0010(\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0017H'J\u0012\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0016\u0010+\u001a\b\u0012\u0004\u0012\u00020*0\u00172\u0006\u0010,\u001a\u00020\u001eH'J\u001c\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00060\u00172\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u001aH'J\u001c\u0010.\u001a\b\u0012\u0004\u0012\u00020*0\u00172\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u001aH'J\u0010\u0010/\u001a\u00020\u001c2\u0006\u00100\u001a\u00020\u0006H'J\u0016\u00101\u001a\b\u0012\u0004\u0012\u00020\n0\u00172\u0006\u00100\u001a\u00020\u0006H'J\u0016\u00102\u001a\b\u0012\u0004\u0012\u00020\n0\u00172\u0006\u00103\u001a\u00020\u0006H'J\u001e\u00104\u001a\b\u0012\u0004\u0012\u00020\n0\u00172\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u00105\u001a\u00020\u0006H'J\u001c\u00106\u001a\b\u0012\u0004\u0012\u00020\n0\u00172\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u001aH'J\u0010\u00107\u001a\u00020\u001c2\u0006\u00108\u001a\u00020\u0006H'J\u001c\u00109\u001a\b\u0012\u0004\u0012\u00020\n0\u00172\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001aH'J\b\u0010;\u001a\u00020\u001cH'J\u000e\u0010<\u001a\b\u0012\u0004\u0012\u00020=0\u0017H'J\u000e\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H'J\u000e\u0010?\u001a\b\u0012\u0004\u0012\u00020\n0\u0017H'J\u000e\u0010@\u001a\b\u0012\u0004\u0012\u00020A0\u0017H'J\u001e\u0010B\u001a\b\u0012\u0004\u0012\u00020C0\u00172\u0006\u0010\"\u001a\u00020\u001c2\u0006\u00105\u001a\u00020\u001cH'J\b\u0010D\u001a\u00020\u001cH'J\u0010\u0010E\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020\u001cH'J\u001c\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00060\u00172\f\u0010G\u001a\b\u0012\u0004\u0012\u00020*0\u001aH\u0017J\u0018\u0010H\u001a\u0004\u0018\u00010I2\f\u0010G\u001a\b\u0012\u0004\u0012\u00020*0\u001aH'J\u0016\u0010J\u001a\u00020I2\f\u0010K\u001a\b\u0012\u0004\u0012\u00020\n0\u001aH'J\u0016\u0010L\u001a\u00020I2\f\u0010M\u001a\b\u0012\u0004\u0012\u00020A0\u001aH'J\u0016\u0010N\u001a\u00020\u001c2\f\u0010K\u001a\b\u0012\u0004\u0012\u00020\n0\u001aH'J\u001e\u0010O\u001a\u00020\u00042\f\u0010P\u001a\b\u0012\u0004\u0012\u00020\u00060\u001a2\u0006\u0010\"\u001a\u00020\u001cH'¨\u0006R"}, d2 = {"Lcom/amazon/photos/discovery/internal/dao/WorkerDao;", "", "()V", "deleteCloudItemsByUnifiedId", "", "id", "", "deleteLocal", EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS, "", "Lcom/amazon/photos/discovery/internal/model/MutableLocalItem;", "([Lcom/amazon/photos/discovery/internal/model/MutableLocalItem;)V", "deleteLocalFolderById", "deleteOrphanedCloudItems", "deleteOrphanedUnifiedItems", "deleteUnifiedById", "executeTransaction", "transaction", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "workerDao", "getCloudById", "", "Lcom/amazon/photos/discovery/internal/model/MutableCloudItem;", ContactsModuleConstants.CONTACT_IDS, "", "getCountOfLocalItemsInDirectory", "", "directoryPath", "", "getCountOfLocalItemsWithInvalidDateTaken", "getCountOfLocalItemsWithInvalidFileSize", "getCountOfLocalItemsWithMissingMd5Values", "dedupeStage", "getCountOfUnifiedItemsWithMappedCloudItems", "getCountOfZeroDurationLocalItemByType", "itemType", "Lcom/amazon/photos/discovery/model/ItemType;", "getDistinctDedupeStages", "getDuplicatedLocalItems", "getFolderById", "Lcom/amazon/photos/discovery/internal/model/MutableLocalFolder;", "getFolderByPath", RouteParameter.PATH, "getFolderIdsByIds", "getFoldersByIds", "getItemCountByFolderId", "folderId", "getItemsByFolderId", "getLocalAddedOnOrAfter", "dateAdded", "getLocalAfterId", MetricsUtil.LegacyMetricTypes.LIMIT, "getLocalById", "getLocalCountForUnifiedId", "unifiedId", "getLocalInGivenPaths", "filePaths", "getLocalItemCount", "getLocalItemTypeCounts", "Lcom/amazon/photos/discovery/internal/model/ItemTypeCount;", "getOrphanedCloudItems", "getOrphanedLocalItems", "getOrphanedUnifiedEntries", "Lcom/amazon/photos/discovery/internal/model/MutableUnifiedEntry;", "getUnifiedByDedupeStage", "Lcom/amazon/photos/discovery/model/UnifiedItem;", "getUnifiedItemCount", "getUnifiedItemCountOnDedupeStage", "insertFoldersOrIgnore", "folders", "insertFoldersOrIgnoreInternal", "", "insertLocalOrIgnore", "localItems", "insertUnified", "entries", "updateLocal", "updateUnifiedEntryDedupeStage", "unifiedIds", "Companion", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public abstract class WorkerDao {
    public static final int BATCH_SIZE = 200;
    public static final Companion Companion = new Companion(null);

    /* compiled from: WorkerDao.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/discovery/internal/dao/WorkerDao$Companion;", "", "()V", "BATCH_SIZE", "", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Query("DELETE FROM cloud_item WHERE unified_id = :id")
    public abstract void deleteCloudItemsByUnifiedId(long j);

    @Delete
    public abstract void deleteLocal(@NotNull MutableLocalItem... mutableLocalItemArr);

    @Query("DELETE FROM local_folder WHERE id = :id")
    public abstract void deleteLocalFolderById(long j);

    @Query("DELETE FROM cloud_item WHERE unified_id NOT IN (SELECT id FROM unified_item)")
    public abstract void deleteOrphanedCloudItems();

    @Query("DELETE FROM unified_item WHERE id NOT IN (SELECT unified_id FROM local_item)")
    public abstract void deleteOrphanedUnifiedItems();

    @Query("DELETE FROM unified_item WHERE id = :id")
    public abstract void deleteUnifiedById(long j);

    @Transaction
    public void executeTransaction(@NotNull Function1<? super WorkerDao, Unit> transaction) {
        Intrinsics.checkParameterIsNotNull(transaction, "transaction");
        transaction.mo12165invoke(this);
    }

    @Query("SELECT * FROM cloud_item WHERE id IN (:ids) ORDER BY date_taken ASC")
    @NotNull
    public abstract List<MutableCloudItem> getCloudById(@NotNull Collection<Long> collection);

    @Query("SELECT count(*) FROM local_item WHERE local_item.file_path LIKE :directoryPath")
    public abstract int getCountOfLocalItemsInDirectory(@NotNull String str);

    @Query("SELECT count(*) FROM local_item WHERE local_item.date_taken <= 0")
    public abstract int getCountOfLocalItemsWithInvalidDateTaken();

    @Query("SELECT count(*) FROM local_item WHERE size <= 0")
    public abstract int getCountOfLocalItemsWithInvalidFileSize();

    @Query("SELECT count(*) FROM (SELECT u.dedupe_stage FROM unified_item u LEFT JOIN (SELECT unified_id, md5 FROM local_item) l ON l.unified_id = u.id WHERE u.dedupe_stage = :dedupeStage AND l.md5 IS NULL)")
    public abstract int getCountOfLocalItemsWithMissingMd5Values(int i);

    @Query("SELECT count(*) FROM unified_item u LEFT JOIN (SELECT * FROM cloud_item) c ON c.unified_id = u.id WHERE c.unified_id IS NOT NULL")
    public abstract int getCountOfUnifiedItemsWithMappedCloudItems();

    @Query("SELECT count(*) FROM local_item WHERE type = :itemType AND (duration <= 0 OR duration IS NULL)")
    public abstract int getCountOfZeroDurationLocalItemByType(@NotNull ItemType itemType);

    @Query("SELECT DISTINCT dedupe_stage FROM unified_item")
    @NotNull
    public abstract List<Integer> getDistinctDedupeStages();

    @Query("SELECT file_path FROM local_item WHERE file_path IS NOT NULL GROUP BY file_path HAVING COUNT(id) > 1")
    @NotNull
    public abstract List<String> getDuplicatedLocalItems();

    @Query("SELECT * FROM local_folder where id = :id")
    @Nullable
    public abstract MutableLocalFolder getFolderById(long j);

    @Query("SELECT * FROM local_folder where path = :path")
    @NotNull
    public abstract List<MutableLocalFolder> getFolderByPath(@NotNull String str);

    @Query("SELECT id FROM local_folder where id IN (:ids)")
    @NotNull
    public abstract List<Long> getFolderIdsByIds(@NotNull Collection<Long> collection);

    @Query("SELECT * FROM local_folder where id IN (:ids)")
    @NotNull
    public abstract List<MutableLocalFolder> getFoldersByIds(@NotNull Collection<Long> collection);

    @Query("SELECT count(*) FROM local_item where parent_id = :folderId")
    public abstract int getItemCountByFolderId(long j);

    @Query("SELECT * FROM local_item where parent_id = :folderId")
    @NotNull
    public abstract List<MutableLocalItem> getItemsByFolderId(long j);

    @Query("SELECT * FROM local_item WHERE date_added >= :dateAdded ORDER BY date_added ASC")
    @NotNull
    public abstract List<MutableLocalItem> getLocalAddedOnOrAfter(long j);

    @Query("SELECT * FROM local_item WHERE id > :id ORDER BY id ASC LIMIT :limit")
    @NotNull
    public abstract List<MutableLocalItem> getLocalAfterId(long j, long j2);

    @Query("SELECT * FROM local_item WHERE id IN (:ids) ORDER BY date_added ASC")
    @NotNull
    public abstract List<MutableLocalItem> getLocalById(@NotNull Collection<Long> collection);

    @Query("SELECT count(*) FROM local_item WHERE id = :unifiedId")
    public abstract int getLocalCountForUnifiedId(long j);

    @Query("SELECT * FROM local_item WHERE file_path IN (:filePaths)")
    @NotNull
    public abstract List<MutableLocalItem> getLocalInGivenPaths(@NotNull Collection<String> collection);

    @Query("SELECT count(*) FROM local_item")
    public abstract int getLocalItemCount();

    @Query("SELECT DISTINCT type as itemType, count(*) as count from local_item GROUP BY type")
    @NotNull
    public abstract List<ItemTypeCount> getLocalItemTypeCounts();

    @Query("SELECT * FROM cloud_item WHERE unified_id NOT IN (SELECT id FROM unified_item)")
    @NotNull
    public abstract List<MutableCloudItem> getOrphanedCloudItems();

    @Query("SELECT * FROM local_item WHERE unified_id NOT IN (SELECT id FROM unified_item)")
    @NotNull
    public abstract List<MutableLocalItem> getOrphanedLocalItems();

    @Query("SELECT * FROM unified_item WHERE id NOT IN (SELECT unified_id FROM local_item)")
    @NotNull
    public abstract List<MutableUnifiedEntry> getOrphanedUnifiedEntries();

    @Query("SELECT u.id, u.date_taken, u.date_uploaded, u.type, u.dedupe_stage FROM unified_item u LEFT JOIN (SELECT * FROM local_item) l ON l.unified_id = u.id LEFT JOIN (SELECT * FROM cloud_item) c ON c.unified_id = u.id WHERE u.dedupe_stage = :dedupeStage ORDER BY c.unified_id ASC, u.date_taken DESC LIMIT :limit")
    @Transaction
    @NotNull
    public abstract List<UnifiedItem> getUnifiedByDedupeStage(int i, int i2);

    @Query("SELECT count(*) FROM unified_item")
    public abstract int getUnifiedItemCount();

    @Query("SELECT count(*) FROM unified_item u WHERE u.dedupe_stage = :dedupeStage")
    public abstract int getUnifiedItemCountOnDedupeStage(int i);

    @Transaction
    @NotNull
    public List<Long> insertFoldersOrIgnore(@NotNull Collection<MutableLocalFolder> folders) {
        Iterable withIndex;
        List<Long> list;
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(folders, "folders");
        ArrayList arrayList = new ArrayList();
        withIndex = CollectionsKt___CollectionsKt.withIndex(folders);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : withIndex) {
            Integer valueOf = Integer.valueOf(((IndexedValue) obj).getIndex() / 200);
            Object obj2 = linkedHashMap.get(valueOf);
            if (obj2 == null) {
                obj2 = new ArrayList();
                linkedHashMap.put(valueOf, obj2);
            }
            ((List) obj2).add(obj);
        }
        ArrayList<List> arrayList2 = new ArrayList(linkedHashMap.size());
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            Iterable<IndexedValue> iterable = (Iterable) entry.getValue();
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10);
            ArrayList arrayList3 = new ArrayList(collectionSizeOrDefault);
            for (IndexedValue indexedValue : iterable) {
                arrayList3.add((MutableLocalFolder) indexedValue.getValue());
            }
            arrayList2.add(arrayList3);
        }
        for (List list2 : arrayList2) {
            long[] insertFoldersOrIgnoreInternal = insertFoldersOrIgnoreInternal(list2);
            if (insertFoldersOrIgnoreInternal != null) {
                list = ArraysKt___ArraysKt.toList(insertFoldersOrIgnoreInternal);
                arrayList.addAll(list);
            }
        }
        return arrayList;
    }

    @Insert(onConflict = 5)
    @Nullable
    public abstract long[] insertFoldersOrIgnoreInternal(@NotNull Collection<MutableLocalFolder> collection);

    @Insert(onConflict = 5)
    @NotNull
    public abstract long[] insertLocalOrIgnore(@NotNull Collection<MutableLocalItem> collection);

    @Insert
    @NotNull
    public abstract long[] insertUnified(@NotNull Collection<MutableUnifiedEntry> collection);

    @Update
    public abstract int updateLocal(@NotNull Collection<MutableLocalItem> collection);

    @Query("UPDATE unified_item SET dedupe_stage = :dedupeStage WHERE id IN (:unifiedIds)")
    @Transaction
    public abstract void updateUnifiedEntryDedupeStage(@NotNull Collection<Long> collection, int i);
}
