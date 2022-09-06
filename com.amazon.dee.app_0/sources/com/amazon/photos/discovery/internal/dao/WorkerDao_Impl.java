package com.amazon.photos.discovery.internal.dao;

import android.database.Cursor;
import androidx.collection.LongSparseArray;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.handsfree.protocols.sierracontentprovider.SierraContentProviderContract;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.photos.discovery.internal.model.ItemTypeCount;
import com.amazon.photos.discovery.internal.model.MutableCloudItem;
import com.amazon.photos.discovery.internal.model.MutableLocalFolder;
import com.amazon.photos.discovery.internal.model.MutableLocalItem;
import com.amazon.photos.discovery.internal.model.MutableUnifiedEntry;
import com.amazon.photos.discovery.internal.util.DiscoveryTypeConverters;
import com.amazon.photos.discovery.internal.worker.DedupeWorkerKt;
import com.amazon.photos.discovery.model.CloudItem;
import com.amazon.photos.discovery.model.ItemType;
import com.amazon.photos.discovery.model.LocalItem;
import com.amazon.photos.discovery.model.UnifiedItem;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
/* loaded from: classes13.dex */
public final class WorkerDao_Impl extends WorkerDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<MutableLocalItem> __deletionAdapterOfMutableLocalItem;
    private final DiscoveryTypeConverters __discoveryTypeConverters = new DiscoveryTypeConverters();
    private final EntityInsertionAdapter<MutableLocalFolder> __insertionAdapterOfMutableLocalFolder;
    private final EntityInsertionAdapter<MutableLocalItem> __insertionAdapterOfMutableLocalItem;
    private final EntityInsertionAdapter<MutableUnifiedEntry> __insertionAdapterOfMutableUnifiedEntry;
    private final SharedSQLiteStatement __preparedStmtOfDeleteCloudItemsByUnifiedId;
    private final SharedSQLiteStatement __preparedStmtOfDeleteLocalFolderById;
    private final SharedSQLiteStatement __preparedStmtOfDeleteOrphanedCloudItems;
    private final SharedSQLiteStatement __preparedStmtOfDeleteOrphanedUnifiedItems;
    private final SharedSQLiteStatement __preparedStmtOfDeleteUnifiedById;
    private final EntityDeletionOrUpdateAdapter<MutableLocalItem> __updateAdapterOfMutableLocalItem;

    public WorkerDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfMutableUnifiedEntry = new EntityInsertionAdapter<MutableUnifiedEntry>(roomDatabase) { // from class: com.amazon.photos.discovery.internal.dao.WorkerDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `unified_item` (`id`,`type`,`date_taken`,`date_uploaded`,`dedupe_stage`,`synched`) VALUES (nullif(?, 0),?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, MutableUnifiedEntry mutableUnifiedEntry) {
                supportSQLiteStatement.bindLong(1, mutableUnifiedEntry.getId());
                supportSQLiteStatement.bindLong(2, WorkerDao_Impl.this.__discoveryTypeConverters.fromItemType(mutableUnifiedEntry.getItemType()));
                supportSQLiteStatement.bindLong(3, mutableUnifiedEntry.getDateTaken());
                supportSQLiteStatement.bindLong(4, mutableUnifiedEntry.getDateUploaded());
                supportSQLiteStatement.bindLong(5, mutableUnifiedEntry.getDedupeStage());
                supportSQLiteStatement.bindLong(6, mutableUnifiedEntry.getSynched() ? 1L : 0L);
            }
        };
        this.__insertionAdapterOfMutableLocalItem = new EntityInsertionAdapter<MutableLocalItem>(roomDatabase) { // from class: com.amazon.photos.discovery.internal.dao.WorkerDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR IGNORE INTO `local_item` (`id`,`unified_id`,`type`,`file_path`,`duration`,`width`,`height`,`size`,`date_added`,`date_taken`,`date_modified`,`start_processing`,`end_processing`,`md5`,`visual_digest`,`parent_id`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, MutableLocalItem mutableLocalItem) {
                supportSQLiteStatement.bindLong(1, mutableLocalItem.getId());
                supportSQLiteStatement.bindLong(2, mutableLocalItem.getUnifiedId());
                supportSQLiteStatement.bindLong(3, WorkerDao_Impl.this.__discoveryTypeConverters.fromItemType(mutableLocalItem.getItemType()));
                if (mutableLocalItem.getFilePath() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, mutableLocalItem.getFilePath());
                }
                if (mutableLocalItem.getDuration() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindLong(5, mutableLocalItem.getDuration().longValue());
                }
                if (mutableLocalItem.getWidth() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindLong(6, mutableLocalItem.getWidth().longValue());
                }
                if (mutableLocalItem.getHeight() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindLong(7, mutableLocalItem.getHeight().longValue());
                }
                if (mutableLocalItem.getSize() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindLong(8, mutableLocalItem.getSize().longValue());
                }
                supportSQLiteStatement.bindLong(9, mutableLocalItem.getDateAdded());
                supportSQLiteStatement.bindLong(10, mutableLocalItem.getDateTaken());
                supportSQLiteStatement.bindLong(11, mutableLocalItem.getDateModified());
                supportSQLiteStatement.bindLong(12, mutableLocalItem.getStartProcessing());
                supportSQLiteStatement.bindLong(13, mutableLocalItem.getEndProcessing());
                if (mutableLocalItem.getMd5() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, mutableLocalItem.getMd5());
                }
                if (mutableLocalItem.getVisualDigest() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, mutableLocalItem.getVisualDigest());
                }
                supportSQLiteStatement.bindLong(16, mutableLocalItem.getParentId());
            }
        };
        this.__insertionAdapterOfMutableLocalFolder = new EntityInsertionAdapter<MutableLocalFolder>(roomDatabase) { // from class: com.amazon.photos.discovery.internal.dao.WorkerDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR IGNORE INTO `local_folder` (`id`,`name`,`path`,`folder_type`) VALUES (?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, MutableLocalFolder mutableLocalFolder) {
                supportSQLiteStatement.bindLong(1, mutableLocalFolder.getId());
                if (mutableLocalFolder.getName() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, mutableLocalFolder.getName());
                }
                if (mutableLocalFolder.getPath() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, mutableLocalFolder.getPath());
                }
                String fromFolderType = WorkerDao_Impl.this.__discoveryTypeConverters.fromFolderType(mutableLocalFolder.getFolderType());
                if (fromFolderType == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, fromFolderType);
                }
            }
        };
        this.__deletionAdapterOfMutableLocalItem = new EntityDeletionOrUpdateAdapter<MutableLocalItem>(roomDatabase) { // from class: com.amazon.photos.discovery.internal.dao.WorkerDao_Impl.4
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `local_item` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, MutableLocalItem mutableLocalItem) {
                supportSQLiteStatement.bindLong(1, mutableLocalItem.getId());
            }
        };
        this.__updateAdapterOfMutableLocalItem = new EntityDeletionOrUpdateAdapter<MutableLocalItem>(roomDatabase) { // from class: com.amazon.photos.discovery.internal.dao.WorkerDao_Impl.5
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `local_item` SET `id` = ?,`unified_id` = ?,`type` = ?,`file_path` = ?,`duration` = ?,`width` = ?,`height` = ?,`size` = ?,`date_added` = ?,`date_taken` = ?,`date_modified` = ?,`start_processing` = ?,`end_processing` = ?,`md5` = ?,`visual_digest` = ?,`parent_id` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, MutableLocalItem mutableLocalItem) {
                supportSQLiteStatement.bindLong(1, mutableLocalItem.getId());
                supportSQLiteStatement.bindLong(2, mutableLocalItem.getUnifiedId());
                supportSQLiteStatement.bindLong(3, WorkerDao_Impl.this.__discoveryTypeConverters.fromItemType(mutableLocalItem.getItemType()));
                if (mutableLocalItem.getFilePath() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, mutableLocalItem.getFilePath());
                }
                if (mutableLocalItem.getDuration() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindLong(5, mutableLocalItem.getDuration().longValue());
                }
                if (mutableLocalItem.getWidth() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindLong(6, mutableLocalItem.getWidth().longValue());
                }
                if (mutableLocalItem.getHeight() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindLong(7, mutableLocalItem.getHeight().longValue());
                }
                if (mutableLocalItem.getSize() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindLong(8, mutableLocalItem.getSize().longValue());
                }
                supportSQLiteStatement.bindLong(9, mutableLocalItem.getDateAdded());
                supportSQLiteStatement.bindLong(10, mutableLocalItem.getDateTaken());
                supportSQLiteStatement.bindLong(11, mutableLocalItem.getDateModified());
                supportSQLiteStatement.bindLong(12, mutableLocalItem.getStartProcessing());
                supportSQLiteStatement.bindLong(13, mutableLocalItem.getEndProcessing());
                if (mutableLocalItem.getMd5() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, mutableLocalItem.getMd5());
                }
                if (mutableLocalItem.getVisualDigest() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, mutableLocalItem.getVisualDigest());
                }
                supportSQLiteStatement.bindLong(16, mutableLocalItem.getParentId());
                supportSQLiteStatement.bindLong(17, mutableLocalItem.getId());
            }
        };
        this.__preparedStmtOfDeleteCloudItemsByUnifiedId = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.discovery.internal.dao.WorkerDao_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM cloud_item WHERE unified_id = ?";
            }
        };
        this.__preparedStmtOfDeleteUnifiedById = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.discovery.internal.dao.WorkerDao_Impl.7
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM unified_item WHERE id = ?";
            }
        };
        this.__preparedStmtOfDeleteOrphanedUnifiedItems = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.discovery.internal.dao.WorkerDao_Impl.8
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM unified_item WHERE id NOT IN (SELECT unified_id FROM local_item)";
            }
        };
        this.__preparedStmtOfDeleteOrphanedCloudItems = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.discovery.internal.dao.WorkerDao_Impl.9
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM cloud_item WHERE unified_id NOT IN (SELECT id FROM unified_item)";
            }
        };
        this.__preparedStmtOfDeleteLocalFolderById = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.discovery.internal.dao.WorkerDao_Impl.10
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM local_folder WHERE id = ?";
            }
        };
    }

    private void __fetchRelationshipcloudItemAscomAmazonPhotosDiscoveryModelCloudItem(LongSparseArray<ArrayList<CloudItem>> longSparseArray) {
        if (longSparseArray.isEmpty()) {
            return;
        }
        if (longSparseArray.size() > 999) {
            LongSparseArray<ArrayList<CloudItem>> longSparseArray2 = new LongSparseArray<>(999);
            int size = longSparseArray.size();
            LongSparseArray<ArrayList<CloudItem>> longSparseArray3 = longSparseArray2;
            int i = 0;
            int i2 = 0;
            while (i < size) {
                longSparseArray3.put(longSparseArray.keyAt(i), longSparseArray.valueAt(i));
                i++;
                i2++;
                if (i2 == 999) {
                    __fetchRelationshipcloudItemAscomAmazonPhotosDiscoveryModelCloudItem(longSparseArray3);
                    longSparseArray3 = new LongSparseArray<>(999);
                    i2 = 0;
                }
            }
            if (i2 <= 0) {
                return;
            }
            __fetchRelationshipcloudItemAscomAmazonPhotosDiscoveryModelCloudItem(longSparseArray3);
            return;
        }
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT `id`,`unified_id`,`node_id`,`date_uploaded`,`date_taken`,`md5`,`visual_digest` FROM `cloud_item` WHERE `unified_id` IN (");
        int size2 = longSparseArray.size();
        StringUtil.appendPlaceholders(newStringBuilder, size2);
        newStringBuilder.append(")");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size2 + 0);
        int i3 = 1;
        for (int i4 = 0; i4 < longSparseArray.size(); i4++) {
            acquire.bindLong(i3, longSparseArray.keyAt(i4));
            i3++;
        }
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(query, "unified_id");
            if (columnIndex == -1) {
                return;
            }
            int columnIndex2 = CursorUtil.getColumnIndex(query, "id");
            int columnIndex3 = CursorUtil.getColumnIndex(query, "unified_id");
            int columnIndex4 = CursorUtil.getColumnIndex(query, "node_id");
            int columnIndex5 = CursorUtil.getColumnIndex(query, "date_uploaded");
            int columnIndex6 = CursorUtil.getColumnIndex(query, "date_taken");
            int columnIndex7 = CursorUtil.getColumnIndex(query, SierraContentProviderContract.MD5_VALUE);
            int columnIndex8 = CursorUtil.getColumnIndex(query, "visual_digest");
            while (query.moveToNext()) {
                ArrayList<CloudItem> arrayList = longSparseArray.get(query.getLong(columnIndex));
                if (arrayList != null) {
                    long j = 0;
                    long j2 = columnIndex2 == -1 ? 0L : query.getLong(columnIndex2);
                    long j3 = columnIndex3 == -1 ? 0L : query.getLong(columnIndex3);
                    String string = columnIndex4 == -1 ? null : query.getString(columnIndex4);
                    long j4 = columnIndex5 == -1 ? 0L : query.getLong(columnIndex5);
                    if (columnIndex6 != -1) {
                        j = query.getLong(columnIndex6);
                    }
                    arrayList.add(new CloudItem(j2, j3, string, j4, j, columnIndex7 == -1 ? null : query.getString(columnIndex7), columnIndex8 == -1 ? null : query.getString(columnIndex8)));
                }
            }
        } finally {
            query.close();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x016d A[Catch: all -> 0x0226, TryCatch #0 {all -> 0x0226, blocks: (B:20:0x007e, B:25:0x0089, B:26:0x00ef, B:28:0x00f5, B:88:0x0208, B:87:0x0203, B:84:0x01f4, B:81:0x01e1, B:78:0x01ce, B:75:0x01bb, B:72:0x01aa, B:69:0x019b, B:66:0x018e, B:63:0x0183, B:57:0x016d, B:60:0x0174, B:51:0x0157, B:54:0x015e, B:45:0x0141, B:48:0x0148, B:42:0x0136, B:39:0x0124, B:36:0x0118, B:33:0x010d), top: B:97:0x007e }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0183 A[Catch: all -> 0x0226, TryCatch #0 {all -> 0x0226, blocks: (B:20:0x007e, B:25:0x0089, B:26:0x00ef, B:28:0x00f5, B:88:0x0208, B:87:0x0203, B:84:0x01f4, B:81:0x01e1, B:78:0x01ce, B:75:0x01bb, B:72:0x01aa, B:69:0x019b, B:66:0x018e, B:63:0x0183, B:57:0x016d, B:60:0x0174, B:51:0x0157, B:54:0x015e, B:45:0x0141, B:48:0x0148, B:42:0x0136, B:39:0x0124, B:36:0x0118, B:33:0x010d), top: B:97:0x007e }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x018e A[Catch: all -> 0x0226, TryCatch #0 {all -> 0x0226, blocks: (B:20:0x007e, B:25:0x0089, B:26:0x00ef, B:28:0x00f5, B:88:0x0208, B:87:0x0203, B:84:0x01f4, B:81:0x01e1, B:78:0x01ce, B:75:0x01bb, B:72:0x01aa, B:69:0x019b, B:66:0x018e, B:63:0x0183, B:57:0x016d, B:60:0x0174, B:51:0x0157, B:54:0x015e, B:45:0x0141, B:48:0x0148, B:42:0x0136, B:39:0x0124, B:36:0x0118, B:33:0x010d), top: B:97:0x007e }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x019b A[Catch: all -> 0x0226, TryCatch #0 {all -> 0x0226, blocks: (B:20:0x007e, B:25:0x0089, B:26:0x00ef, B:28:0x00f5, B:88:0x0208, B:87:0x0203, B:84:0x01f4, B:81:0x01e1, B:78:0x01ce, B:75:0x01bb, B:72:0x01aa, B:69:0x019b, B:66:0x018e, B:63:0x0183, B:57:0x016d, B:60:0x0174, B:51:0x0157, B:54:0x015e, B:45:0x0141, B:48:0x0148, B:42:0x0136, B:39:0x0124, B:36:0x0118, B:33:0x010d), top: B:97:0x007e }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01aa A[Catch: all -> 0x0226, TryCatch #0 {all -> 0x0226, blocks: (B:20:0x007e, B:25:0x0089, B:26:0x00ef, B:28:0x00f5, B:88:0x0208, B:87:0x0203, B:84:0x01f4, B:81:0x01e1, B:78:0x01ce, B:75:0x01bb, B:72:0x01aa, B:69:0x019b, B:66:0x018e, B:63:0x0183, B:57:0x016d, B:60:0x0174, B:51:0x0157, B:54:0x015e, B:45:0x0141, B:48:0x0148, B:42:0x0136, B:39:0x0124, B:36:0x0118, B:33:0x010d), top: B:97:0x007e }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01bb A[Catch: all -> 0x0226, TryCatch #0 {all -> 0x0226, blocks: (B:20:0x007e, B:25:0x0089, B:26:0x00ef, B:28:0x00f5, B:88:0x0208, B:87:0x0203, B:84:0x01f4, B:81:0x01e1, B:78:0x01ce, B:75:0x01bb, B:72:0x01aa, B:69:0x019b, B:66:0x018e, B:63:0x0183, B:57:0x016d, B:60:0x0174, B:51:0x0157, B:54:0x015e, B:45:0x0141, B:48:0x0148, B:42:0x0136, B:39:0x0124, B:36:0x0118, B:33:0x010d), top: B:97:0x007e }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01ce A[Catch: all -> 0x0226, TryCatch #0 {all -> 0x0226, blocks: (B:20:0x007e, B:25:0x0089, B:26:0x00ef, B:28:0x00f5, B:88:0x0208, B:87:0x0203, B:84:0x01f4, B:81:0x01e1, B:78:0x01ce, B:75:0x01bb, B:72:0x01aa, B:69:0x019b, B:66:0x018e, B:63:0x0183, B:57:0x016d, B:60:0x0174, B:51:0x0157, B:54:0x015e, B:45:0x0141, B:48:0x0148, B:42:0x0136, B:39:0x0124, B:36:0x0118, B:33:0x010d), top: B:97:0x007e }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01e1 A[Catch: all -> 0x0226, TryCatch #0 {all -> 0x0226, blocks: (B:20:0x007e, B:25:0x0089, B:26:0x00ef, B:28:0x00f5, B:88:0x0208, B:87:0x0203, B:84:0x01f4, B:81:0x01e1, B:78:0x01ce, B:75:0x01bb, B:72:0x01aa, B:69:0x019b, B:66:0x018e, B:63:0x0183, B:57:0x016d, B:60:0x0174, B:51:0x0157, B:54:0x015e, B:45:0x0141, B:48:0x0148, B:42:0x0136, B:39:0x0124, B:36:0x0118, B:33:0x010d), top: B:97:0x007e }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01f4 A[Catch: all -> 0x0226, TryCatch #0 {all -> 0x0226, blocks: (B:20:0x007e, B:25:0x0089, B:26:0x00ef, B:28:0x00f5, B:88:0x0208, B:87:0x0203, B:84:0x01f4, B:81:0x01e1, B:78:0x01ce, B:75:0x01bb, B:72:0x01aa, B:69:0x019b, B:66:0x018e, B:63:0x0183, B:57:0x016d, B:60:0x0174, B:51:0x0157, B:54:0x015e, B:45:0x0141, B:48:0x0148, B:42:0x0136, B:39:0x0124, B:36:0x0118, B:33:0x010d), top: B:97:0x007e }] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0203 A[Catch: all -> 0x0226, TryCatch #0 {all -> 0x0226, blocks: (B:20:0x007e, B:25:0x0089, B:26:0x00ef, B:28:0x00f5, B:88:0x0208, B:87:0x0203, B:84:0x01f4, B:81:0x01e1, B:78:0x01ce, B:75:0x01bb, B:72:0x01aa, B:69:0x019b, B:66:0x018e, B:63:0x0183, B:57:0x016d, B:60:0x0174, B:51:0x0157, B:54:0x015e, B:45:0x0141, B:48:0x0148, B:42:0x0136, B:39:0x0124, B:36:0x0118, B:33:0x010d), top: B:97:0x007e }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void __fetchRelationshiplocalItemAscomAmazonPhotosDiscoveryModelLocalItem(androidx.collection.LongSparseArray<java.util.ArrayList<com.amazon.photos.discovery.model.LocalItem>> r54) {
        /*
            Method dump skipped, instructions count: 555
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.discovery.internal.dao.WorkerDao_Impl.__fetchRelationshiplocalItemAscomAmazonPhotosDiscoveryModelLocalItem(androidx.collection.LongSparseArray):void");
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public void deleteCloudItemsByUnifiedId(long j) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteCloudItemsByUnifiedId.acquire();
        acquire.bindLong(1, j);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteCloudItemsByUnifiedId.release(acquire);
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public void deleteLocal(MutableLocalItem... mutableLocalItemArr) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfMutableLocalItem.handleMultiple(mutableLocalItemArr);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public void deleteLocalFolderById(long j) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteLocalFolderById.acquire();
        acquire.bindLong(1, j);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteLocalFolderById.release(acquire);
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public void deleteOrphanedCloudItems() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteOrphanedCloudItems.acquire();
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteOrphanedCloudItems.release(acquire);
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public void deleteOrphanedUnifiedItems() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteOrphanedUnifiedItems.acquire();
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteOrphanedUnifiedItems.release(acquire);
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public void deleteUnifiedById(long j) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteUnifiedById.acquire();
        acquire.bindLong(1, j);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteUnifiedById.release(acquire);
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public void executeTransaction(Function1<? super WorkerDao, Unit> function1) {
        this.__db.beginTransaction();
        try {
            super.executeTransaction(function1);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public List<MutableCloudItem> getCloudById(Collection<Long> collection) {
        StringBuilder outline114 = GeneratedOutlineSupport1.outline114("SELECT ", "*", " FROM cloud_item WHERE id IN (");
        int size = collection.size();
        StringUtil.appendPlaceholders(outline114, size);
        outline114.append(") ORDER BY date_taken ASC");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(outline114.toString(), size + 0);
        int i = 1;
        for (Long l : collection) {
            if (l == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindLong(i, l.longValue());
            }
            i++;
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "unified_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "node_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "date_uploaded");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "date_taken");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, SierraContentProviderContract.MD5_VALUE);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "visual_digest");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(new MutableCloudItem(query.getLong(columnIndexOrThrow), query.getLong(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.getLong(columnIndexOrThrow4), query.getLong(columnIndexOrThrow5), query.getString(columnIndexOrThrow6), query.getString(columnIndexOrThrow7)));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public int getCountOfLocalItemsInDirectory(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT count(*) FROM local_item WHERE local_item.file_path LIKE ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        int i = 0;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            if (query.moveToFirst()) {
                i = query.getInt(0);
            }
            return i;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public int getCountOfLocalItemsWithInvalidDateTaken() {
        int i = 0;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT count(*) FROM local_item WHERE local_item.date_taken <= 0", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            if (query.moveToFirst()) {
                i = query.getInt(0);
            }
            return i;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public int getCountOfLocalItemsWithInvalidFileSize() {
        int i = 0;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT count(*) FROM local_item WHERE size <= 0", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            if (query.moveToFirst()) {
                i = query.getInt(0);
            }
            return i;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public int getCountOfLocalItemsWithMissingMd5Values(int i) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT count(*) FROM (SELECT u.dedupe_stage FROM unified_item u LEFT JOIN (SELECT unified_id, md5 FROM local_item) l ON l.unified_id = u.id WHERE u.dedupe_stage = ? AND l.md5 IS NULL)", 1);
        acquire.bindLong(1, i);
        this.__db.assertNotSuspendingTransaction();
        int i2 = 0;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            if (query.moveToFirst()) {
                i2 = query.getInt(0);
            }
            return i2;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public int getCountOfUnifiedItemsWithMappedCloudItems() {
        int i = 0;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT count(*) FROM unified_item u LEFT JOIN (SELECT * FROM cloud_item) c ON c.unified_id = u.id WHERE c.unified_id IS NOT NULL", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            if (query.moveToFirst()) {
                i = query.getInt(0);
            }
            return i;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public int getCountOfZeroDurationLocalItemByType(ItemType itemType) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT count(*) FROM local_item WHERE type = ? AND (duration <= 0 OR duration IS NULL)", 1);
        acquire.bindLong(1, this.__discoveryTypeConverters.fromItemType(itemType));
        this.__db.assertNotSuspendingTransaction();
        int i = 0;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            if (query.moveToFirst()) {
                i = query.getInt(0);
            }
            return i;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public List<Integer> getDistinctDedupeStages() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT DISTINCT dedupe_stage FROM unified_item", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(query.isNull(0) ? null : Integer.valueOf(query.getInt(0)));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public List<String> getDuplicatedLocalItems() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT file_path FROM local_item WHERE file_path IS NOT NULL GROUP BY file_path HAVING COUNT(id) > 1", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(query.getString(0));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public MutableLocalFolder getFolderById(long j) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM local_folder where id = ?", 1);
        acquire.bindLong(1, j);
        this.__db.assertNotSuspendingTransaction();
        MutableLocalFolder mutableLocalFolder = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "name");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, RouteParameter.PATH);
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "folder_type");
            if (query.moveToFirst()) {
                mutableLocalFolder = new MutableLocalFolder(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), this.__discoveryTypeConverters.toFolderType(query.getString(columnIndexOrThrow4)));
            }
            return mutableLocalFolder;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public List<MutableLocalFolder> getFolderByPath(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM local_folder where path = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "name");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, RouteParameter.PATH);
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "folder_type");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(new MutableLocalFolder(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), this.__discoveryTypeConverters.toFolderType(query.getString(columnIndexOrThrow4))));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public List<Long> getFolderIdsByIds(Collection<Long> collection) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT id FROM local_folder where id IN (");
        int size = collection.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(")");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 0);
        int i = 1;
        for (Long l : collection) {
            if (l == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindLong(i, l.longValue());
            }
            i++;
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(query.isNull(0) ? null : Long.valueOf(query.getLong(0)));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public List<MutableLocalFolder> getFoldersByIds(Collection<Long> collection) {
        StringBuilder outline114 = GeneratedOutlineSupport1.outline114("SELECT ", "*", " FROM local_folder where id IN (");
        int size = collection.size();
        StringUtil.appendPlaceholders(outline114, size);
        outline114.append(")");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(outline114.toString(), size + 0);
        int i = 1;
        for (Long l : collection) {
            if (l == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindLong(i, l.longValue());
            }
            i++;
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "name");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, RouteParameter.PATH);
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "folder_type");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(new MutableLocalFolder(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), this.__discoveryTypeConverters.toFolderType(query.getString(columnIndexOrThrow4))));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public int getItemCountByFolderId(long j) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT count(*) FROM local_item where parent_id = ?", 1);
        acquire.bindLong(1, j);
        this.__db.assertNotSuspendingTransaction();
        int i = 0;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            if (query.moveToFirst()) {
                i = query.getInt(0);
            }
            return i;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public List<MutableLocalItem> getItemsByFolderId(long j) {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM local_item where parent_id = ?", 1);
        acquire.bindLong(1, j);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "unified_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "type");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "duration");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "width");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "height");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "size");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "date_added");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "date_taken");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "date_modified");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "start_processing");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "end_processing");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, SierraContentProviderContract.MD5_VALUE);
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "visual_digest");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "parent_id");
                int i = columnIndexOrThrow13;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    long j2 = query.getLong(columnIndexOrThrow);
                    long j3 = query.getLong(columnIndexOrThrow2);
                    int i2 = columnIndexOrThrow;
                    ItemType itemType = this.__discoveryTypeConverters.toItemType(query.getInt(columnIndexOrThrow3));
                    String string = query.getString(columnIndexOrThrow4);
                    Long valueOf = query.isNull(columnIndexOrThrow5) ? null : Long.valueOf(query.getLong(columnIndexOrThrow5));
                    Long valueOf2 = query.isNull(columnIndexOrThrow6) ? null : Long.valueOf(query.getLong(columnIndexOrThrow6));
                    Long valueOf3 = query.isNull(columnIndexOrThrow7) ? null : Long.valueOf(query.getLong(columnIndexOrThrow7));
                    Long valueOf4 = query.isNull(columnIndexOrThrow8) ? null : Long.valueOf(query.getLong(columnIndexOrThrow8));
                    long j4 = query.getLong(columnIndexOrThrow9);
                    long j5 = query.getLong(columnIndexOrThrow10);
                    long j6 = query.getLong(columnIndexOrThrow11);
                    long j7 = query.getLong(columnIndexOrThrow12);
                    int i3 = i;
                    long j8 = query.getLong(i3);
                    int i4 = columnIndexOrThrow14;
                    String string2 = query.getString(i4);
                    i = i3;
                    int i5 = columnIndexOrThrow15;
                    String string3 = query.getString(i5);
                    columnIndexOrThrow15 = i5;
                    int i6 = columnIndexOrThrow16;
                    columnIndexOrThrow16 = i6;
                    arrayList.add(new MutableLocalItem(j2, j3, itemType, string, valueOf, valueOf2, valueOf3, valueOf4, j4, j5, j6, j7, j8, string2, string3, query.getLong(i6)));
                    columnIndexOrThrow14 = i4;
                    columnIndexOrThrow = i2;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public List<MutableLocalItem> getLocalAddedOnOrAfter(long j) {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM local_item WHERE date_added >= ? ORDER BY date_added ASC", 1);
        acquire.bindLong(1, j);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "unified_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "type");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "duration");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "width");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "height");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "size");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "date_added");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "date_taken");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "date_modified");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "start_processing");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "end_processing");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, SierraContentProviderContract.MD5_VALUE);
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "visual_digest");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "parent_id");
                int i = columnIndexOrThrow13;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    long j2 = query.getLong(columnIndexOrThrow);
                    long j3 = query.getLong(columnIndexOrThrow2);
                    int i2 = columnIndexOrThrow;
                    ItemType itemType = this.__discoveryTypeConverters.toItemType(query.getInt(columnIndexOrThrow3));
                    String string = query.getString(columnIndexOrThrow4);
                    Long valueOf = query.isNull(columnIndexOrThrow5) ? null : Long.valueOf(query.getLong(columnIndexOrThrow5));
                    Long valueOf2 = query.isNull(columnIndexOrThrow6) ? null : Long.valueOf(query.getLong(columnIndexOrThrow6));
                    Long valueOf3 = query.isNull(columnIndexOrThrow7) ? null : Long.valueOf(query.getLong(columnIndexOrThrow7));
                    Long valueOf4 = query.isNull(columnIndexOrThrow8) ? null : Long.valueOf(query.getLong(columnIndexOrThrow8));
                    long j4 = query.getLong(columnIndexOrThrow9);
                    long j5 = query.getLong(columnIndexOrThrow10);
                    long j6 = query.getLong(columnIndexOrThrow11);
                    long j7 = query.getLong(columnIndexOrThrow12);
                    int i3 = i;
                    long j8 = query.getLong(i3);
                    int i4 = columnIndexOrThrow14;
                    String string2 = query.getString(i4);
                    i = i3;
                    int i5 = columnIndexOrThrow15;
                    String string3 = query.getString(i5);
                    columnIndexOrThrow15 = i5;
                    int i6 = columnIndexOrThrow16;
                    columnIndexOrThrow16 = i6;
                    arrayList.add(new MutableLocalItem(j2, j3, itemType, string, valueOf, valueOf2, valueOf3, valueOf4, j4, j5, j6, j7, j8, string2, string3, query.getLong(i6)));
                    columnIndexOrThrow14 = i4;
                    columnIndexOrThrow = i2;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public List<MutableLocalItem> getLocalAfterId(long j, long j2) {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM local_item WHERE id > ? ORDER BY id ASC LIMIT ?", 2);
        acquire.bindLong(1, j);
        acquire.bindLong(2, j2);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "unified_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "type");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "duration");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "width");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "height");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "size");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "date_added");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "date_taken");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "date_modified");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "start_processing");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "end_processing");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, SierraContentProviderContract.MD5_VALUE);
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "visual_digest");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "parent_id");
                int i = columnIndexOrThrow13;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    long j3 = query.getLong(columnIndexOrThrow);
                    long j4 = query.getLong(columnIndexOrThrow2);
                    int i2 = columnIndexOrThrow;
                    ItemType itemType = this.__discoveryTypeConverters.toItemType(query.getInt(columnIndexOrThrow3));
                    String string = query.getString(columnIndexOrThrow4);
                    Long valueOf = query.isNull(columnIndexOrThrow5) ? null : Long.valueOf(query.getLong(columnIndexOrThrow5));
                    Long valueOf2 = query.isNull(columnIndexOrThrow6) ? null : Long.valueOf(query.getLong(columnIndexOrThrow6));
                    Long valueOf3 = query.isNull(columnIndexOrThrow7) ? null : Long.valueOf(query.getLong(columnIndexOrThrow7));
                    Long valueOf4 = query.isNull(columnIndexOrThrow8) ? null : Long.valueOf(query.getLong(columnIndexOrThrow8));
                    long j5 = query.getLong(columnIndexOrThrow9);
                    long j6 = query.getLong(columnIndexOrThrow10);
                    long j7 = query.getLong(columnIndexOrThrow11);
                    long j8 = query.getLong(columnIndexOrThrow12);
                    int i3 = i;
                    long j9 = query.getLong(i3);
                    int i4 = columnIndexOrThrow14;
                    String string2 = query.getString(i4);
                    i = i3;
                    int i5 = columnIndexOrThrow15;
                    String string3 = query.getString(i5);
                    columnIndexOrThrow15 = i5;
                    int i6 = columnIndexOrThrow16;
                    columnIndexOrThrow16 = i6;
                    arrayList.add(new MutableLocalItem(j3, j4, itemType, string, valueOf, valueOf2, valueOf3, valueOf4, j5, j6, j7, j8, j9, string2, string3, query.getLong(i6)));
                    columnIndexOrThrow14 = i4;
                    columnIndexOrThrow = i2;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public List<MutableLocalItem> getLocalById(Collection<Long> collection) {
        RoomSQLiteQuery roomSQLiteQuery;
        StringBuilder outline114 = GeneratedOutlineSupport1.outline114("SELECT ", "*", " FROM local_item WHERE id IN (");
        int size = collection.size();
        StringUtil.appendPlaceholders(outline114, size);
        outline114.append(") ORDER BY date_added ASC");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(outline114.toString(), size + 0);
        int i = 1;
        for (Long l : collection) {
            if (l == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindLong(i, l.longValue());
            }
            i++;
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "unified_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "type");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "duration");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "width");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "height");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "size");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "date_added");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "date_taken");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "date_modified");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "start_processing");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "end_processing");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, SierraContentProviderContract.MD5_VALUE);
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "visual_digest");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "parent_id");
                int i2 = columnIndexOrThrow13;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    long j = query.getLong(columnIndexOrThrow);
                    long j2 = query.getLong(columnIndexOrThrow2);
                    int i3 = columnIndexOrThrow;
                    ItemType itemType = this.__discoveryTypeConverters.toItemType(query.getInt(columnIndexOrThrow3));
                    String string = query.getString(columnIndexOrThrow4);
                    Long valueOf = query.isNull(columnIndexOrThrow5) ? null : Long.valueOf(query.getLong(columnIndexOrThrow5));
                    Long valueOf2 = query.isNull(columnIndexOrThrow6) ? null : Long.valueOf(query.getLong(columnIndexOrThrow6));
                    Long valueOf3 = query.isNull(columnIndexOrThrow7) ? null : Long.valueOf(query.getLong(columnIndexOrThrow7));
                    Long valueOf4 = query.isNull(columnIndexOrThrow8) ? null : Long.valueOf(query.getLong(columnIndexOrThrow8));
                    long j3 = query.getLong(columnIndexOrThrow9);
                    long j4 = query.getLong(columnIndexOrThrow10);
                    long j5 = query.getLong(columnIndexOrThrow11);
                    long j6 = query.getLong(columnIndexOrThrow12);
                    int i4 = i2;
                    long j7 = query.getLong(i4);
                    int i5 = columnIndexOrThrow14;
                    String string2 = query.getString(i5);
                    i2 = i4;
                    int i6 = columnIndexOrThrow15;
                    String string3 = query.getString(i6);
                    columnIndexOrThrow15 = i6;
                    int i7 = columnIndexOrThrow16;
                    columnIndexOrThrow16 = i7;
                    arrayList.add(new MutableLocalItem(j, j2, itemType, string, valueOf, valueOf2, valueOf3, valueOf4, j3, j4, j5, j6, j7, string2, string3, query.getLong(i7)));
                    columnIndexOrThrow14 = i5;
                    columnIndexOrThrow = i3;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public int getLocalCountForUnifiedId(long j) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT count(*) FROM local_item WHERE id = ?", 1);
        acquire.bindLong(1, j);
        this.__db.assertNotSuspendingTransaction();
        int i = 0;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            if (query.moveToFirst()) {
                i = query.getInt(0);
            }
            return i;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public List<MutableLocalItem> getLocalInGivenPaths(Collection<String> collection) {
        RoomSQLiteQuery roomSQLiteQuery;
        StringBuilder outline114 = GeneratedOutlineSupport1.outline114("SELECT ", "*", " FROM local_item WHERE file_path IN (");
        int size = collection.size();
        StringUtil.appendPlaceholders(outline114, size);
        outline114.append(")");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(outline114.toString(), size + 0);
        int i = 1;
        for (String str : collection) {
            if (str == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindString(i, str);
            }
            i++;
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "unified_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "type");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "duration");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "width");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "height");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "size");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "date_added");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "date_taken");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "date_modified");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "start_processing");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "end_processing");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, SierraContentProviderContract.MD5_VALUE);
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "visual_digest");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "parent_id");
                int i2 = columnIndexOrThrow13;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    long j = query.getLong(columnIndexOrThrow);
                    long j2 = query.getLong(columnIndexOrThrow2);
                    int i3 = columnIndexOrThrow;
                    ItemType itemType = this.__discoveryTypeConverters.toItemType(query.getInt(columnIndexOrThrow3));
                    String string = query.getString(columnIndexOrThrow4);
                    Long valueOf = query.isNull(columnIndexOrThrow5) ? null : Long.valueOf(query.getLong(columnIndexOrThrow5));
                    Long valueOf2 = query.isNull(columnIndexOrThrow6) ? null : Long.valueOf(query.getLong(columnIndexOrThrow6));
                    Long valueOf3 = query.isNull(columnIndexOrThrow7) ? null : Long.valueOf(query.getLong(columnIndexOrThrow7));
                    Long valueOf4 = query.isNull(columnIndexOrThrow8) ? null : Long.valueOf(query.getLong(columnIndexOrThrow8));
                    long j3 = query.getLong(columnIndexOrThrow9);
                    long j4 = query.getLong(columnIndexOrThrow10);
                    long j5 = query.getLong(columnIndexOrThrow11);
                    long j6 = query.getLong(columnIndexOrThrow12);
                    int i4 = i2;
                    long j7 = query.getLong(i4);
                    int i5 = columnIndexOrThrow14;
                    String string2 = query.getString(i5);
                    i2 = i4;
                    int i6 = columnIndexOrThrow15;
                    String string3 = query.getString(i6);
                    columnIndexOrThrow15 = i6;
                    int i7 = columnIndexOrThrow16;
                    columnIndexOrThrow16 = i7;
                    arrayList.add(new MutableLocalItem(j, j2, itemType, string, valueOf, valueOf2, valueOf3, valueOf4, j3, j4, j5, j6, j7, string2, string3, query.getLong(i7)));
                    columnIndexOrThrow14 = i5;
                    columnIndexOrThrow = i3;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public int getLocalItemCount() {
        int i = 0;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT count(*) FROM local_item", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            if (query.moveToFirst()) {
                i = query.getInt(0);
            }
            return i;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public List<ItemTypeCount> getLocalItemTypeCounts() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT DISTINCT type as itemType, count(*) as count from local_item GROUP BY type", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "itemType");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "count");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(new ItemTypeCount(this.__discoveryTypeConverters.toItemType(query.getInt(columnIndexOrThrow)), query.getInt(columnIndexOrThrow2)));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public List<MutableCloudItem> getOrphanedCloudItems() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM cloud_item WHERE unified_id NOT IN (SELECT id FROM unified_item)", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "unified_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "node_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "date_uploaded");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "date_taken");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, SierraContentProviderContract.MD5_VALUE);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "visual_digest");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(new MutableCloudItem(query.getLong(columnIndexOrThrow), query.getLong(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.getLong(columnIndexOrThrow4), query.getLong(columnIndexOrThrow5), query.getString(columnIndexOrThrow6), query.getString(columnIndexOrThrow7)));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public List<MutableLocalItem> getOrphanedLocalItems() {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM local_item WHERE unified_id NOT IN (SELECT id FROM unified_item)", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "unified_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "type");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "duration");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "width");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "height");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "size");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "date_added");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "date_taken");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "date_modified");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "start_processing");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "end_processing");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, SierraContentProviderContract.MD5_VALUE);
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "visual_digest");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "parent_id");
                int i = columnIndexOrThrow13;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    long j = query.getLong(columnIndexOrThrow);
                    long j2 = query.getLong(columnIndexOrThrow2);
                    int i2 = columnIndexOrThrow;
                    ItemType itemType = this.__discoveryTypeConverters.toItemType(query.getInt(columnIndexOrThrow3));
                    String string = query.getString(columnIndexOrThrow4);
                    Long valueOf = query.isNull(columnIndexOrThrow5) ? null : Long.valueOf(query.getLong(columnIndexOrThrow5));
                    Long valueOf2 = query.isNull(columnIndexOrThrow6) ? null : Long.valueOf(query.getLong(columnIndexOrThrow6));
                    Long valueOf3 = query.isNull(columnIndexOrThrow7) ? null : Long.valueOf(query.getLong(columnIndexOrThrow7));
                    Long valueOf4 = query.isNull(columnIndexOrThrow8) ? null : Long.valueOf(query.getLong(columnIndexOrThrow8));
                    long j3 = query.getLong(columnIndexOrThrow9);
                    long j4 = query.getLong(columnIndexOrThrow10);
                    long j5 = query.getLong(columnIndexOrThrow11);
                    long j6 = query.getLong(columnIndexOrThrow12);
                    int i3 = i;
                    long j7 = query.getLong(i3);
                    int i4 = columnIndexOrThrow14;
                    String string2 = query.getString(i4);
                    i = i3;
                    int i5 = columnIndexOrThrow15;
                    String string3 = query.getString(i5);
                    columnIndexOrThrow15 = i5;
                    int i6 = columnIndexOrThrow16;
                    columnIndexOrThrow16 = i6;
                    arrayList.add(new MutableLocalItem(j, j2, itemType, string, valueOf, valueOf2, valueOf3, valueOf4, j3, j4, j5, j6, j7, string2, string3, query.getLong(i6)));
                    columnIndexOrThrow14 = i4;
                    columnIndexOrThrow = i2;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public List<MutableUnifiedEntry> getOrphanedUnifiedEntries() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM unified_item WHERE id NOT IN (SELECT unified_id FROM local_item)", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "type");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "date_taken");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "date_uploaded");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, DedupeWorkerKt.PARAM_DEDUPE_STAGE_ID);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "synched");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(new MutableUnifiedEntry(query.getLong(columnIndexOrThrow), this.__discoveryTypeConverters.toItemType(query.getInt(columnIndexOrThrow2)), query.getLong(columnIndexOrThrow3), query.getLong(columnIndexOrThrow4), query.getInt(columnIndexOrThrow5), query.getInt(columnIndexOrThrow6) != 0));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public List<UnifiedItem> getUnifiedByDedupeStage(int i, int i2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT u.id, u.date_taken, u.date_uploaded, u.type, u.dedupe_stage FROM unified_item u LEFT JOIN (SELECT * FROM local_item) l ON l.unified_id = u.id LEFT JOIN (SELECT * FROM cloud_item) c ON c.unified_id = u.id WHERE u.dedupe_stage = ? ORDER BY c.unified_id ASC, u.date_taken DESC LIMIT ?", 2);
        acquire.bindLong(1, i);
        acquire.bindLong(2, i2);
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor query = DBUtil.query(this.__db, acquire, true, null);
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "date_taken");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "date_uploaded");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "type");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, DedupeWorkerKt.PARAM_DEDUPE_STAGE_ID);
            LongSparseArray<ArrayList<LocalItem>> longSparseArray = new LongSparseArray<>();
            LongSparseArray<ArrayList<CloudItem>> longSparseArray2 = new LongSparseArray<>();
            while (query.moveToNext()) {
                long j = query.getLong(columnIndexOrThrow);
                if (longSparseArray.get(j) == null) {
                    longSparseArray.put(j, new ArrayList<>());
                }
                long j2 = query.getLong(columnIndexOrThrow);
                if (longSparseArray2.get(j2) == null) {
                    longSparseArray2.put(j2, new ArrayList<>());
                }
            }
            query.moveToPosition(-1);
            __fetchRelationshiplocalItemAscomAmazonPhotosDiscoveryModelLocalItem(longSparseArray);
            __fetchRelationshipcloudItemAscomAmazonPhotosDiscoveryModelCloudItem(longSparseArray2);
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                long j3 = query.getLong(columnIndexOrThrow);
                long j4 = query.getLong(columnIndexOrThrow2);
                long j5 = query.getLong(columnIndexOrThrow3);
                ItemType itemType = this.__discoveryTypeConverters.toItemType(query.getInt(columnIndexOrThrow4));
                int i3 = query.getInt(columnIndexOrThrow5);
                ArrayList<LocalItem> arrayList2 = longSparseArray.get(query.getLong(columnIndexOrThrow));
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>();
                }
                ArrayList<LocalItem> arrayList3 = arrayList2;
                ArrayList<CloudItem> arrayList4 = longSparseArray2.get(query.getLong(columnIndexOrThrow));
                if (arrayList4 == null) {
                    arrayList4 = new ArrayList<>();
                }
                arrayList.add(new UnifiedItem(j3, itemType, j4, j5, i3, arrayList3, arrayList4));
            }
            this.__db.setTransactionSuccessful();
            query.close();
            acquire.release();
            return arrayList;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public int getUnifiedItemCount() {
        int i = 0;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT count(*) FROM unified_item", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            if (query.moveToFirst()) {
                i = query.getInt(0);
            }
            return i;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public int getUnifiedItemCountOnDedupeStage(int i) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT count(*) FROM unified_item u WHERE u.dedupe_stage = ?", 1);
        acquire.bindLong(1, i);
        this.__db.assertNotSuspendingTransaction();
        int i2 = 0;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            if (query.moveToFirst()) {
                i2 = query.getInt(0);
            }
            return i2;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public List<Long> insertFoldersOrIgnore(Collection<MutableLocalFolder> collection) {
        this.__db.beginTransaction();
        try {
            List<Long> insertFoldersOrIgnore = super.insertFoldersOrIgnore(collection);
            this.__db.setTransactionSuccessful();
            return insertFoldersOrIgnore;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public long[] insertFoldersOrIgnoreInternal(Collection<MutableLocalFolder> collection) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long[] insertAndReturnIdsArray = this.__insertionAdapterOfMutableLocalFolder.insertAndReturnIdsArray(collection);
            this.__db.setTransactionSuccessful();
            return insertAndReturnIdsArray;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public long[] insertLocalOrIgnore(Collection<MutableLocalItem> collection) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long[] insertAndReturnIdsArray = this.__insertionAdapterOfMutableLocalItem.insertAndReturnIdsArray(collection);
            this.__db.setTransactionSuccessful();
            return insertAndReturnIdsArray;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public long[] insertUnified(Collection<MutableUnifiedEntry> collection) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long[] insertAndReturnIdsArray = this.__insertionAdapterOfMutableUnifiedEntry.insertAndReturnIdsArray(collection);
            this.__db.setTransactionSuccessful();
            return insertAndReturnIdsArray;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public int updateLocal(Collection<MutableLocalItem> collection) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            int handleMultiple = this.__updateAdapterOfMutableLocalItem.handleMultiple(collection) + 0;
            this.__db.setTransactionSuccessful();
            return handleMultiple;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.discovery.internal.dao.WorkerDao
    public void updateUnifiedEntryDedupeStage(Collection<Long> collection, int i) {
        this.__db.assertNotSuspendingTransaction();
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("UPDATE unified_item SET dedupe_stage = ");
        newStringBuilder.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
        newStringBuilder.append(" WHERE id IN (");
        StringUtil.appendPlaceholders(newStringBuilder, collection.size());
        newStringBuilder.append(")");
        SupportSQLiteStatement compileStatement = this.__db.compileStatement(newStringBuilder.toString());
        compileStatement.bindLong(1, i);
        int i2 = 2;
        for (Long l : collection) {
            if (l == null) {
                compileStatement.bindNull(i2);
            } else {
                compileStatement.bindLong(i2, l.longValue());
            }
            i2++;
        }
        this.__db.beginTransaction();
        try {
            compileStatement.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }
}
