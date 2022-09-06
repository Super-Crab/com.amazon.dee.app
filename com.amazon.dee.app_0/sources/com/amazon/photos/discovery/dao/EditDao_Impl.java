package com.amazon.photos.discovery.dao;

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
import com.amazon.photos.discovery.internal.model.MutableCloudItem;
import com.amazon.photos.discovery.internal.model.MutableUnifiedEntry;
import com.amazon.photos.discovery.internal.util.DiscoveryTypeConverters;
import com.amazon.photos.discovery.internal.worker.DedupeWorkerKt;
import com.amazon.photos.discovery.model.CloudItem;
import com.amazon.photos.discovery.model.ItemType;
import com.amazon.photos.discovery.model.LocalItem;
import com.amazon.photos.discovery.model.UnifiedItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
/* loaded from: classes13.dex */
public final class EditDao_Impl extends EditDao {
    private final RoomDatabase __db;
    private final DiscoveryTypeConverters __discoveryTypeConverters = new DiscoveryTypeConverters();
    private final EntityInsertionAdapter<MutableCloudItem> __insertionAdapterOfMutableCloudItem;
    private final EntityInsertionAdapter<MutableUnifiedEntry> __insertionAdapterOfMutableUnifiedEntry;
    private final SharedSQLiteStatement __preparedStmtOfUpdateLocalDigest$AndroidPhotosDiscovery_release;
    private final SharedSQLiteStatement __preparedStmtOfUpdateLocalMd5$AndroidPhotosDiscovery_release;
    private final SharedSQLiteStatement __preparedStmtOfUpdateLocalVisualDigest$AndroidPhotosDiscovery_release;
    private final EntityDeletionOrUpdateAdapter<MutableCloudItem> __updateAdapterOfMutableCloudItem;
    private final EntityDeletionOrUpdateAdapter<MutableUnifiedEntry> __updateAdapterOfMutableUnifiedEntry;

    public EditDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfMutableCloudItem = new EntityInsertionAdapter<MutableCloudItem>(roomDatabase) { // from class: com.amazon.photos.discovery.dao.EditDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR IGNORE INTO `cloud_item` (`id`,`unified_id`,`node_id`,`date_uploaded`,`date_taken`,`md5`,`visual_digest`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, MutableCloudItem mutableCloudItem) {
                supportSQLiteStatement.bindLong(1, mutableCloudItem.getId());
                supportSQLiteStatement.bindLong(2, mutableCloudItem.getUnifiedId());
                if (mutableCloudItem.getNodeId() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, mutableCloudItem.getNodeId());
                }
                supportSQLiteStatement.bindLong(4, mutableCloudItem.getDateUploaded());
                supportSQLiteStatement.bindLong(5, mutableCloudItem.getDateTaken());
                if (mutableCloudItem.getMd5() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, mutableCloudItem.getMd5());
                }
                if (mutableCloudItem.getVisualDigest() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, mutableCloudItem.getVisualDigest());
                }
            }
        };
        this.__insertionAdapterOfMutableUnifiedEntry = new EntityInsertionAdapter<MutableUnifiedEntry>(roomDatabase) { // from class: com.amazon.photos.discovery.dao.EditDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `unified_item` (`id`,`type`,`date_taken`,`date_uploaded`,`dedupe_stage`,`synched`) VALUES (nullif(?, 0),?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, MutableUnifiedEntry mutableUnifiedEntry) {
                supportSQLiteStatement.bindLong(1, mutableUnifiedEntry.getId());
                supportSQLiteStatement.bindLong(2, EditDao_Impl.this.__discoveryTypeConverters.fromItemType(mutableUnifiedEntry.getItemType()));
                supportSQLiteStatement.bindLong(3, mutableUnifiedEntry.getDateTaken());
                supportSQLiteStatement.bindLong(4, mutableUnifiedEntry.getDateUploaded());
                supportSQLiteStatement.bindLong(5, mutableUnifiedEntry.getDedupeStage());
                supportSQLiteStatement.bindLong(6, mutableUnifiedEntry.getSynched() ? 1L : 0L);
            }
        };
        this.__updateAdapterOfMutableUnifiedEntry = new EntityDeletionOrUpdateAdapter<MutableUnifiedEntry>(roomDatabase) { // from class: com.amazon.photos.discovery.dao.EditDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `unified_item` SET `id` = ?,`type` = ?,`date_taken` = ?,`date_uploaded` = ?,`dedupe_stage` = ?,`synched` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, MutableUnifiedEntry mutableUnifiedEntry) {
                supportSQLiteStatement.bindLong(1, mutableUnifiedEntry.getId());
                supportSQLiteStatement.bindLong(2, EditDao_Impl.this.__discoveryTypeConverters.fromItemType(mutableUnifiedEntry.getItemType()));
                supportSQLiteStatement.bindLong(3, mutableUnifiedEntry.getDateTaken());
                supportSQLiteStatement.bindLong(4, mutableUnifiedEntry.getDateUploaded());
                supportSQLiteStatement.bindLong(5, mutableUnifiedEntry.getDedupeStage());
                supportSQLiteStatement.bindLong(6, mutableUnifiedEntry.getSynched() ? 1L : 0L);
                supportSQLiteStatement.bindLong(7, mutableUnifiedEntry.getId());
            }
        };
        this.__updateAdapterOfMutableCloudItem = new EntityDeletionOrUpdateAdapter<MutableCloudItem>(roomDatabase) { // from class: com.amazon.photos.discovery.dao.EditDao_Impl.4
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `cloud_item` SET `id` = ?,`unified_id` = ?,`node_id` = ?,`date_uploaded` = ?,`date_taken` = ?,`md5` = ?,`visual_digest` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, MutableCloudItem mutableCloudItem) {
                supportSQLiteStatement.bindLong(1, mutableCloudItem.getId());
                supportSQLiteStatement.bindLong(2, mutableCloudItem.getUnifiedId());
                if (mutableCloudItem.getNodeId() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, mutableCloudItem.getNodeId());
                }
                supportSQLiteStatement.bindLong(4, mutableCloudItem.getDateUploaded());
                supportSQLiteStatement.bindLong(5, mutableCloudItem.getDateTaken());
                if (mutableCloudItem.getMd5() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, mutableCloudItem.getMd5());
                }
                if (mutableCloudItem.getVisualDigest() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, mutableCloudItem.getVisualDigest());
                }
                supportSQLiteStatement.bindLong(8, mutableCloudItem.getId());
            }
        };
        this.__preparedStmtOfUpdateLocalMd5$AndroidPhotosDiscovery_release = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.discovery.dao.EditDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE local_item SET md5 = ? WHERE id IS ?";
            }
        };
        this.__preparedStmtOfUpdateLocalVisualDigest$AndroidPhotosDiscovery_release = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.discovery.dao.EditDao_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE local_item SET visual_digest = ? WHERE id IS ?";
            }
        };
        this.__preparedStmtOfUpdateLocalDigest$AndroidPhotosDiscovery_release = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.discovery.dao.EditDao_Impl.7
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE local_item SET md5 = ?, visual_digest = ? WHERE id IS ?";
            }
        };
    }

    private void __fetchRelationshipcloudItemAscomAmazonPhotosDiscoveryInternalModelMutableCloudItem(LongSparseArray<ArrayList<MutableCloudItem>> longSparseArray) {
        if (longSparseArray.isEmpty()) {
            return;
        }
        if (longSparseArray.size() > 999) {
            LongSparseArray<ArrayList<MutableCloudItem>> longSparseArray2 = new LongSparseArray<>(999);
            int size = longSparseArray.size();
            LongSparseArray<ArrayList<MutableCloudItem>> longSparseArray3 = longSparseArray2;
            int i = 0;
            int i2 = 0;
            while (i < size) {
                longSparseArray3.put(longSparseArray.keyAt(i), longSparseArray.valueAt(i));
                i++;
                i2++;
                if (i2 == 999) {
                    __fetchRelationshipcloudItemAscomAmazonPhotosDiscoveryInternalModelMutableCloudItem(longSparseArray3);
                    longSparseArray3 = new LongSparseArray<>(999);
                    i2 = 0;
                }
            }
            if (i2 <= 0) {
                return;
            }
            __fetchRelationshipcloudItemAscomAmazonPhotosDiscoveryInternalModelMutableCloudItem(longSparseArray3);
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
                ArrayList<MutableCloudItem> arrayList = longSparseArray.get(query.getLong(columnIndex));
                if (arrayList != null) {
                    long j = 0;
                    long j2 = columnIndex2 == -1 ? 0L : query.getLong(columnIndex2);
                    long j3 = columnIndex3 == -1 ? 0L : query.getLong(columnIndex3);
                    String string = columnIndex4 == -1 ? null : query.getString(columnIndex4);
                    long j4 = columnIndex5 == -1 ? 0L : query.getLong(columnIndex5);
                    if (columnIndex6 != -1) {
                        j = query.getLong(columnIndex6);
                    }
                    arrayList.add(new MutableCloudItem(j2, j3, string, j4, j, columnIndex7 == -1 ? null : query.getString(columnIndex7), columnIndex8 == -1 ? null : query.getString(columnIndex8)));
                }
            }
        } finally {
            query.close();
        }
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

    /* JADX WARN: Removed duplicated region for block: B:57:0x016d A[Catch: all -> 0x0231, TryCatch #0 {all -> 0x0231, blocks: (B:20:0x007e, B:25:0x0089, B:26:0x00ef, B:28:0x00f5, B:91:0x0213, B:90:0x020e, B:87:0x01ff, B:84:0x01ec, B:81:0x01d9, B:78:0x01c6, B:75:0x01b5, B:72:0x01a6, B:69:0x0199, B:63:0x0183, B:66:0x018a, B:57:0x016d, B:60:0x0174, B:51:0x0157, B:54:0x015e, B:45:0x0141, B:48:0x0148, B:42:0x0136, B:39:0x0124, B:36:0x0118, B:33:0x010d), top: B:100:0x007e }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0183 A[Catch: all -> 0x0231, TryCatch #0 {all -> 0x0231, blocks: (B:20:0x007e, B:25:0x0089, B:26:0x00ef, B:28:0x00f5, B:91:0x0213, B:90:0x020e, B:87:0x01ff, B:84:0x01ec, B:81:0x01d9, B:78:0x01c6, B:75:0x01b5, B:72:0x01a6, B:69:0x0199, B:63:0x0183, B:66:0x018a, B:57:0x016d, B:60:0x0174, B:51:0x0157, B:54:0x015e, B:45:0x0141, B:48:0x0148, B:42:0x0136, B:39:0x0124, B:36:0x0118, B:33:0x010d), top: B:100:0x007e }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0199 A[Catch: all -> 0x0231, TryCatch #0 {all -> 0x0231, blocks: (B:20:0x007e, B:25:0x0089, B:26:0x00ef, B:28:0x00f5, B:91:0x0213, B:90:0x020e, B:87:0x01ff, B:84:0x01ec, B:81:0x01d9, B:78:0x01c6, B:75:0x01b5, B:72:0x01a6, B:69:0x0199, B:63:0x0183, B:66:0x018a, B:57:0x016d, B:60:0x0174, B:51:0x0157, B:54:0x015e, B:45:0x0141, B:48:0x0148, B:42:0x0136, B:39:0x0124, B:36:0x0118, B:33:0x010d), top: B:100:0x007e }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01a6 A[Catch: all -> 0x0231, TryCatch #0 {all -> 0x0231, blocks: (B:20:0x007e, B:25:0x0089, B:26:0x00ef, B:28:0x00f5, B:91:0x0213, B:90:0x020e, B:87:0x01ff, B:84:0x01ec, B:81:0x01d9, B:78:0x01c6, B:75:0x01b5, B:72:0x01a6, B:69:0x0199, B:63:0x0183, B:66:0x018a, B:57:0x016d, B:60:0x0174, B:51:0x0157, B:54:0x015e, B:45:0x0141, B:48:0x0148, B:42:0x0136, B:39:0x0124, B:36:0x0118, B:33:0x010d), top: B:100:0x007e }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01b5 A[Catch: all -> 0x0231, TryCatch #0 {all -> 0x0231, blocks: (B:20:0x007e, B:25:0x0089, B:26:0x00ef, B:28:0x00f5, B:91:0x0213, B:90:0x020e, B:87:0x01ff, B:84:0x01ec, B:81:0x01d9, B:78:0x01c6, B:75:0x01b5, B:72:0x01a6, B:69:0x0199, B:63:0x0183, B:66:0x018a, B:57:0x016d, B:60:0x0174, B:51:0x0157, B:54:0x015e, B:45:0x0141, B:48:0x0148, B:42:0x0136, B:39:0x0124, B:36:0x0118, B:33:0x010d), top: B:100:0x007e }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01c6 A[Catch: all -> 0x0231, TryCatch #0 {all -> 0x0231, blocks: (B:20:0x007e, B:25:0x0089, B:26:0x00ef, B:28:0x00f5, B:91:0x0213, B:90:0x020e, B:87:0x01ff, B:84:0x01ec, B:81:0x01d9, B:78:0x01c6, B:75:0x01b5, B:72:0x01a6, B:69:0x0199, B:63:0x0183, B:66:0x018a, B:57:0x016d, B:60:0x0174, B:51:0x0157, B:54:0x015e, B:45:0x0141, B:48:0x0148, B:42:0x0136, B:39:0x0124, B:36:0x0118, B:33:0x010d), top: B:100:0x007e }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01d9 A[Catch: all -> 0x0231, TryCatch #0 {all -> 0x0231, blocks: (B:20:0x007e, B:25:0x0089, B:26:0x00ef, B:28:0x00f5, B:91:0x0213, B:90:0x020e, B:87:0x01ff, B:84:0x01ec, B:81:0x01d9, B:78:0x01c6, B:75:0x01b5, B:72:0x01a6, B:69:0x0199, B:63:0x0183, B:66:0x018a, B:57:0x016d, B:60:0x0174, B:51:0x0157, B:54:0x015e, B:45:0x0141, B:48:0x0148, B:42:0x0136, B:39:0x0124, B:36:0x0118, B:33:0x010d), top: B:100:0x007e }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01ec A[Catch: all -> 0x0231, TryCatch #0 {all -> 0x0231, blocks: (B:20:0x007e, B:25:0x0089, B:26:0x00ef, B:28:0x00f5, B:91:0x0213, B:90:0x020e, B:87:0x01ff, B:84:0x01ec, B:81:0x01d9, B:78:0x01c6, B:75:0x01b5, B:72:0x01a6, B:69:0x0199, B:63:0x0183, B:66:0x018a, B:57:0x016d, B:60:0x0174, B:51:0x0157, B:54:0x015e, B:45:0x0141, B:48:0x0148, B:42:0x0136, B:39:0x0124, B:36:0x0118, B:33:0x010d), top: B:100:0x007e }] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01ff A[Catch: all -> 0x0231, TryCatch #0 {all -> 0x0231, blocks: (B:20:0x007e, B:25:0x0089, B:26:0x00ef, B:28:0x00f5, B:91:0x0213, B:90:0x020e, B:87:0x01ff, B:84:0x01ec, B:81:0x01d9, B:78:0x01c6, B:75:0x01b5, B:72:0x01a6, B:69:0x0199, B:63:0x0183, B:66:0x018a, B:57:0x016d, B:60:0x0174, B:51:0x0157, B:54:0x015e, B:45:0x0141, B:48:0x0148, B:42:0x0136, B:39:0x0124, B:36:0x0118, B:33:0x010d), top: B:100:0x007e }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x020e A[Catch: all -> 0x0231, TryCatch #0 {all -> 0x0231, blocks: (B:20:0x007e, B:25:0x0089, B:26:0x00ef, B:28:0x00f5, B:91:0x0213, B:90:0x020e, B:87:0x01ff, B:84:0x01ec, B:81:0x01d9, B:78:0x01c6, B:75:0x01b5, B:72:0x01a6, B:69:0x0199, B:63:0x0183, B:66:0x018a, B:57:0x016d, B:60:0x0174, B:51:0x0157, B:54:0x015e, B:45:0x0141, B:48:0x0148, B:42:0x0136, B:39:0x0124, B:36:0x0118, B:33:0x010d), top: B:100:0x007e }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void __fetchRelationshiplocalItemAscomAmazonPhotosDiscoveryInternalModelMutableLocalItem(androidx.collection.LongSparseArray<java.util.ArrayList<com.amazon.photos.discovery.internal.model.MutableLocalItem>> r53) {
        /*
            Method dump skipped, instructions count: 566
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.discovery.dao.EditDao_Impl.__fetchRelationshiplocalItemAscomAmazonPhotosDiscoveryInternalModelMutableLocalItem(androidx.collection.LongSparseArray):void");
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
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.discovery.dao.EditDao_Impl.__fetchRelationshiplocalItemAscomAmazonPhotosDiscoveryModelLocalItem(androidx.collection.LongSparseArray):void");
    }

    @Override // com.amazon.photos.discovery.dao.EditDao
    public void deleteCloudByCloudIds(Collection<Long> collection) {
        this.__db.assertNotSuspendingTransaction();
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("DELETE FROM cloud_item WHERE id IN (");
        StringUtil.appendPlaceholders(newStringBuilder, collection.size());
        newStringBuilder.append(")");
        SupportSQLiteStatement compileStatement = this.__db.compileStatement(newStringBuilder.toString());
        int i = 1;
        for (Long l : collection) {
            if (l == null) {
                compileStatement.bindNull(i);
            } else {
                compileStatement.bindLong(i, l.longValue());
            }
            i++;
        }
        this.__db.beginTransaction();
        try {
            compileStatement.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.discovery.dao.EditDao
    public void deleteUnifiedByUnifiedIds(Collection<Long> collection) {
        this.__db.assertNotSuspendingTransaction();
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("DELETE FROM unified_item WHERE id IN (");
        StringUtil.appendPlaceholders(newStringBuilder, collection.size());
        newStringBuilder.append(")");
        SupportSQLiteStatement compileStatement = this.__db.compileStatement(newStringBuilder.toString());
        int i = 1;
        for (Long l : collection) {
            if (l == null) {
                compileStatement.bindNull(i);
            } else {
                compileStatement.bindLong(i, l.longValue());
            }
            i++;
        }
        this.__db.beginTransaction();
        try {
            compileStatement.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.discovery.dao.EditDao
    public void executeTransaction(Function1<? super EditDao, Unit> function1) {
        this.__db.beginTransaction();
        try {
            super.executeTransaction(function1);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0128 A[Catch: all -> 0x015b, TryCatch #2 {all -> 0x0163, blocks: (B:11:0x004e, B:51:0x014f, B:12:0x0055, B:13:0x0083, B:15:0x0089, B:17:0x0095, B:18:0x009d, B:20:0x00a9, B:21:0x00b2, B:22:0x00c5, B:24:0x00cb, B:26:0x00d1, B:28:0x00d7, B:30:0x00dd, B:32:0x00e3, B:34:0x00e9, B:43:0x011c, B:45:0x0128, B:46:0x012d, B:48:0x0139, B:49:0x013e, B:38:0x00f2, B:42:0x0114, B:50:0x014a), top: B:60:0x004e }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0139 A[Catch: all -> 0x015b, TryCatch #2 {all -> 0x0163, blocks: (B:11:0x004e, B:51:0x014f, B:12:0x0055, B:13:0x0083, B:15:0x0089, B:17:0x0095, B:18:0x009d, B:20:0x00a9, B:21:0x00b2, B:22:0x00c5, B:24:0x00cb, B:26:0x00d1, B:28:0x00d7, B:30:0x00dd, B:32:0x00e3, B:34:0x00e9, B:43:0x011c, B:45:0x0128, B:46:0x012d, B:48:0x0139, B:49:0x013e, B:38:0x00f2, B:42:0x0114, B:50:0x014a), top: B:60:0x004e }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x013e A[SYNTHETIC] */
    @Override // com.amazon.photos.discovery.dao.EditDao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<com.amazon.photos.discovery.internal.model.MutableUnifiedItem> getMutableUnifiedByUnifiedIds$AndroidPhotosDiscovery_release(java.util.Collection<java.lang.Long> r26) {
        /*
            Method dump skipped, instructions count: 362
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.discovery.dao.EditDao_Impl.getMutableUnifiedByUnifiedIds$AndroidPhotosDiscovery_release(java.util.Collection):java.util.List");
    }

    @Override // com.amazon.photos.discovery.dao.EditDao
    public List<UnifiedItem> getUnifiedByCloudIds(Collection<Long> collection) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT u.id, u.date_taken, u.date_uploaded, u.type, u.dedupe_stage FROM unified_item u LEFT JOIN (SELECT * FROM local_item) l ON l.unified_id = u.id LEFT JOIN (SELECT * FROM cloud_item) c ON c.unified_id = u.id WHERE c.id IN (");
        int size = collection.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(") ORDER BY u.id ASC");
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
                int i2 = query.getInt(columnIndexOrThrow5);
                ArrayList<LocalItem> arrayList2 = longSparseArray.get(query.getLong(columnIndexOrThrow));
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>();
                }
                ArrayList<LocalItem> arrayList3 = arrayList2;
                ArrayList<CloudItem> arrayList4 = longSparseArray2.get(query.getLong(columnIndexOrThrow));
                if (arrayList4 == null) {
                    arrayList4 = new ArrayList<>();
                }
                arrayList.add(new UnifiedItem(j3, itemType, j4, j5, i2, arrayList3, arrayList4));
            }
            this.__db.setTransactionSuccessful();
            query.close();
            acquire.release();
            return arrayList;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.discovery.dao.EditDao
    public List<UnifiedItem> getUnifiedByLocalIds(Collection<Long> collection) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT u.id, u.date_taken, u.date_uploaded, u.type, u.dedupe_stage FROM unified_item u LEFT JOIN (SELECT * FROM local_item) l ON l.unified_id = u.id LEFT JOIN (SELECT * FROM cloud_item) c ON c.unified_id = u.id WHERE l.id IN (");
        int size = collection.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(") ORDER BY u.id ASC");
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
                int i2 = query.getInt(columnIndexOrThrow5);
                ArrayList<LocalItem> arrayList2 = longSparseArray.get(query.getLong(columnIndexOrThrow));
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>();
                }
                ArrayList<LocalItem> arrayList3 = arrayList2;
                ArrayList<CloudItem> arrayList4 = longSparseArray2.get(query.getLong(columnIndexOrThrow));
                if (arrayList4 == null) {
                    arrayList4 = new ArrayList<>();
                }
                arrayList.add(new UnifiedItem(j3, itemType, j4, j5, i2, arrayList3, arrayList4));
            }
            this.__db.setTransactionSuccessful();
            query.close();
            acquire.release();
            return arrayList;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.discovery.dao.EditDao
    public List<UnifiedItem> getUnifiedByMd5s(Collection<String> collection) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT u.id, u.date_taken, u.date_uploaded, u.type, u.dedupe_stage FROM unified_item u LEFT JOIN (SELECT * FROM local_item) l ON l.unified_id = u.id LEFT JOIN (SELECT * FROM cloud_item) c ON c.unified_id = u.id WHERE c.md5 IN (");
        int size = collection.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(") OR l.md5 IN (");
        int size2 = collection.size();
        StringUtil.appendPlaceholders(newStringBuilder, size2);
        newStringBuilder.append(") ORDER BY u.id ASC");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 0 + size2);
        int i = 1;
        for (String str : collection) {
            if (str == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindString(i, str);
            }
            i++;
        }
        int i2 = size + 1;
        for (String str2 : collection) {
            if (str2 == null) {
                acquire.bindNull(i2);
            } else {
                acquire.bindString(i2, str2);
            }
            i2++;
        }
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

    @Override // com.amazon.photos.discovery.dao.EditDao
    public List<UnifiedItem> getUnifiedByNodeIds(Collection<String> collection) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT u.id, u.date_taken, u.date_uploaded, u.type, u.dedupe_stage FROM unified_item u LEFT JOIN (SELECT * FROM local_item) l ON l.unified_id = u.id LEFT JOIN (SELECT * FROM cloud_item) c ON c.unified_id = u.id WHERE c.node_id IN (");
        int size = collection.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(") ORDER BY u.id ASC");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 0);
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
                int i2 = query.getInt(columnIndexOrThrow5);
                ArrayList<LocalItem> arrayList2 = longSparseArray.get(query.getLong(columnIndexOrThrow));
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>();
                }
                ArrayList<LocalItem> arrayList3 = arrayList2;
                ArrayList<CloudItem> arrayList4 = longSparseArray2.get(query.getLong(columnIndexOrThrow));
                if (arrayList4 == null) {
                    arrayList4 = new ArrayList<>();
                }
                arrayList.add(new UnifiedItem(j3, itemType, j4, j5, i2, arrayList3, arrayList4));
            }
            this.__db.setTransactionSuccessful();
            query.close();
            acquire.release();
            return arrayList;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.discovery.dao.EditDao
    public List<UnifiedItem> getUnifiedByUnifiedIds(Collection<Long> collection) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT u.id, u.date_taken, u.date_uploaded, u.type, u.dedupe_stage FROM unified_item u LEFT JOIN (SELECT * FROM local_item) l ON l.unified_id = u.id LEFT JOIN (SELECT * FROM cloud_item) c ON c.unified_id = u.id WHERE u.id IN (");
        int size = collection.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(") ORDER BY u.id ASC");
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
                int i2 = query.getInt(columnIndexOrThrow5);
                ArrayList<LocalItem> arrayList2 = longSparseArray.get(query.getLong(columnIndexOrThrow));
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>();
                }
                ArrayList<LocalItem> arrayList3 = arrayList2;
                ArrayList<CloudItem> arrayList4 = longSparseArray2.get(query.getLong(columnIndexOrThrow));
                if (arrayList4 == null) {
                    arrayList4 = new ArrayList<>();
                }
                arrayList.add(new UnifiedItem(j3, itemType, j4, j5, i2, arrayList3, arrayList4));
            }
            this.__db.setTransactionSuccessful();
            query.close();
            acquire.release();
            return arrayList;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.discovery.dao.EditDao
    public List<UnifiedItem> getUnifiedByVisualDigests(Collection<String> collection) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT u.id, u.date_taken, u.date_uploaded, u.type, u.dedupe_stage FROM unified_item u LEFT JOIN (SELECT * FROM local_item) l ON l.unified_id = u.id LEFT JOIN (SELECT * FROM cloud_item) c ON c.unified_id = u.id WHERE c.visual_digest IN (");
        int size = collection.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(") OR l.visual_digest IN (");
        int size2 = collection.size();
        StringUtil.appendPlaceholders(newStringBuilder, size2);
        newStringBuilder.append(") ORDER BY u.id ASC");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 0 + size2);
        int i = 1;
        for (String str : collection) {
            if (str == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindString(i, str);
            }
            i++;
        }
        int i2 = size + 1;
        for (String str2 : collection) {
            if (str2 == null) {
                acquire.bindNull(i2);
            } else {
                acquire.bindString(i2, str2);
            }
            i2++;
        }
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

    @Override // com.amazon.photos.discovery.dao.EditDao
    public long[] insertCloudOrIgnore(List<MutableCloudItem> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long[] insertAndReturnIdsArray = this.__insertionAdapterOfMutableCloudItem.insertAndReturnIdsArray(list);
            this.__db.setTransactionSuccessful();
            return insertAndReturnIdsArray;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.discovery.dao.EditDao
    public long[] insertUnified$AndroidPhotosDiscovery_release(List<MutableUnifiedEntry> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long[] insertAndReturnIdsArray = this.__insertionAdapterOfMutableUnifiedEntry.insertAndReturnIdsArray(list);
            this.__db.setTransactionSuccessful();
            return insertAndReturnIdsArray;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.discovery.dao.EditDao
    public void updateCloudUnifiedId(Collection<Long> collection, long j) {
        this.__db.assertNotSuspendingTransaction();
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("UPDATE cloud_item SET unified_id = ");
        newStringBuilder.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
        newStringBuilder.append(" WHERE id IN (");
        StringUtil.appendPlaceholders(newStringBuilder, collection.size());
        newStringBuilder.append(")");
        SupportSQLiteStatement compileStatement = this.__db.compileStatement(newStringBuilder.toString());
        compileStatement.bindLong(1, j);
        int i = 2;
        for (Long l : collection) {
            if (l == null) {
                compileStatement.bindNull(i);
            } else {
                compileStatement.bindLong(i, l.longValue());
            }
            i++;
        }
        this.__db.beginTransaction();
        try {
            compileStatement.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.discovery.dao.EditDao
    public void updateLocalDigest$AndroidPhotosDiscovery_release(long j, String str, String str2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateLocalDigest$AndroidPhotosDiscovery_release.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str2 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str2);
        }
        acquire.bindLong(3, j);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateLocalDigest$AndroidPhotosDiscovery_release.release(acquire);
        }
    }

    @Override // com.amazon.photos.discovery.dao.EditDao
    public void updateLocalMd5$AndroidPhotosDiscovery_release(long j, String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateLocalMd5$AndroidPhotosDiscovery_release.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateLocalMd5$AndroidPhotosDiscovery_release.release(acquire);
        }
    }

    @Override // com.amazon.photos.discovery.dao.EditDao
    public void updateLocalUnifiedId$AndroidPhotosDiscovery_release(Collection<Long> collection, long j) {
        this.__db.assertNotSuspendingTransaction();
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("UPDATE local_item SET unified_id = ");
        newStringBuilder.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
        newStringBuilder.append(" WHERE id IN (");
        StringUtil.appendPlaceholders(newStringBuilder, collection.size());
        newStringBuilder.append(")");
        SupportSQLiteStatement compileStatement = this.__db.compileStatement(newStringBuilder.toString());
        compileStatement.bindLong(1, j);
        int i = 2;
        for (Long l : collection) {
            if (l == null) {
                compileStatement.bindNull(i);
            } else {
                compileStatement.bindLong(i, l.longValue());
            }
            i++;
        }
        this.__db.beginTransaction();
        try {
            compileStatement.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.discovery.dao.EditDao
    public void updateLocalVisualDigest$AndroidPhotosDiscovery_release(long j, String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateLocalVisualDigest$AndroidPhotosDiscovery_release.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateLocalVisualDigest$AndroidPhotosDiscovery_release.release(acquire);
        }
    }

    @Override // com.amazon.photos.discovery.dao.EditDao
    public void updateMutableCloudItem(MutableCloudItem mutableCloudItem) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfMutableCloudItem.handle(mutableCloudItem);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.discovery.dao.EditDao
    public void updateMutableCloudItems(Collection<MutableCloudItem> collection) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfMutableCloudItem.handleMultiple(collection);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.discovery.dao.EditDao
    public void updateMutableUnifiedEntries$AndroidPhotosDiscovery_release(List<MutableUnifiedEntry> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfMutableUnifiedEntry.handleMultiple(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.discovery.dao.EditDao
    public void updateMutableUnifiedEntry$AndroidPhotosDiscovery_release(MutableUnifiedEntry mutableUnifiedEntry) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfMutableUnifiedEntry.handle(mutableUnifiedEntry);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.discovery.dao.EditDao
    public List<UnifiedItem> getUnifiedByCloudIds(Collection<Long> collection, Set<Integer> set) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT u.id, u.date_taken, u.date_uploaded, u.type, u.dedupe_stage FROM unified_item u LEFT JOIN (SELECT * FROM local_item) l ON l.unified_id = u.id LEFT JOIN (SELECT * FROM cloud_item) c ON c.unified_id = u.id WHERE c.id IN (");
        int size = collection.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(") AND u.dedupe_stage IN (");
        int size2 = set.size();
        StringUtil.appendPlaceholders(newStringBuilder, size2);
        newStringBuilder.append(") ORDER BY u.id ASC");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 0 + size2);
        int i = 1;
        for (Long l : collection) {
            if (l == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindLong(i, l.longValue());
            }
            i++;
        }
        int i2 = size + 1;
        for (Integer num : set) {
            if (num == null) {
                acquire.bindNull(i2);
            } else {
                acquire.bindLong(i2, num.intValue());
            }
            i2++;
        }
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

    @Override // com.amazon.photos.discovery.dao.EditDao
    public List<UnifiedItem> getUnifiedByLocalIds(Collection<Long> collection, Set<Integer> set) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT u.id, u.date_taken, u.date_uploaded, u.type, u.dedupe_stage FROM unified_item u LEFT JOIN (SELECT * FROM local_item) l ON l.unified_id = u.id LEFT JOIN (SELECT * FROM cloud_item) c ON c.unified_id = u.id WHERE l.id IN (");
        int size = collection.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(") AND u.dedupe_stage IN (");
        int size2 = set.size();
        StringUtil.appendPlaceholders(newStringBuilder, size2);
        newStringBuilder.append(") ORDER BY u.id ASC");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 0 + size2);
        int i = 1;
        for (Long l : collection) {
            if (l == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindLong(i, l.longValue());
            }
            i++;
        }
        int i2 = size + 1;
        for (Integer num : set) {
            if (num == null) {
                acquire.bindNull(i2);
            } else {
                acquire.bindLong(i2, num.intValue());
            }
            i2++;
        }
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

    @Override // com.amazon.photos.discovery.dao.EditDao
    public List<UnifiedItem> getUnifiedByNodeIds(Collection<String> collection, Set<Integer> set) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT u.id, u.date_taken, u.date_uploaded, u.type, u.dedupe_stage FROM unified_item u LEFT JOIN (SELECT * FROM local_item) l ON l.unified_id = u.id LEFT JOIN (SELECT * FROM cloud_item) c ON c.unified_id = u.id WHERE c.node_id IN (");
        int size = collection.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(") AND u.dedupe_stage IN (");
        int size2 = set.size();
        StringUtil.appendPlaceholders(newStringBuilder, size2);
        newStringBuilder.append(") ORDER BY u.id ASC");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 0 + size2);
        int i = 1;
        for (String str : collection) {
            if (str == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindString(i, str);
            }
            i++;
        }
        int i2 = size + 1;
        for (Integer num : set) {
            if (num == null) {
                acquire.bindNull(i2);
            } else {
                acquire.bindLong(i2, num.intValue());
            }
            i2++;
        }
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

    @Override // com.amazon.photos.discovery.dao.EditDao
    public List<UnifiedItem> getUnifiedByUnifiedIds(Collection<Long> collection, Set<Integer> set) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT u.id, u.date_taken, u.date_uploaded, u.type, u.dedupe_stage FROM unified_item u LEFT JOIN (SELECT * FROM local_item) l ON l.unified_id = u.id LEFT JOIN (SELECT * FROM cloud_item) c ON c.unified_id = u.id WHERE u.id IN (");
        int size = collection.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(") AND u.dedupe_stage IN (");
        int size2 = set.size();
        StringUtil.appendPlaceholders(newStringBuilder, size2);
        newStringBuilder.append(") ORDER BY u.id ASC");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 0 + size2);
        int i = 1;
        for (Long l : collection) {
            if (l == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindLong(i, l.longValue());
            }
            i++;
        }
        int i2 = size + 1;
        for (Integer num : set) {
            if (num == null) {
                acquire.bindNull(i2);
            } else {
                acquire.bindLong(i2, num.intValue());
            }
            i2++;
        }
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

    @Override // com.amazon.photos.discovery.dao.EditDao
    public List<UnifiedItem> getUnifiedByMd5s(Collection<String> collection, Set<Integer> set) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT u.id, u.date_taken, u.date_uploaded, u.type, u.dedupe_stage FROM unified_item u LEFT JOIN (SELECT * FROM local_item) l ON l.unified_id = u.id LEFT JOIN (SELECT * FROM cloud_item) c ON c.unified_id = u.id WHERE u.dedupe_stage IN (");
        int size = set.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(") AND (c.md5 IN (");
        int size2 = collection.size();
        StringUtil.appendPlaceholders(newStringBuilder, size2);
        newStringBuilder.append(") OR l.md5 IN (");
        int size3 = collection.size();
        StringUtil.appendPlaceholders(newStringBuilder, size3);
        newStringBuilder.append(")) ORDER BY u.id ASC");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 0 + size2 + size3);
        int i = 1;
        for (Integer num : set) {
            if (num == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindLong(i, num.intValue());
            }
            i++;
        }
        int i2 = size + 1;
        int i3 = i2;
        for (String str : collection) {
            if (str == null) {
                acquire.bindNull(i3);
            } else {
                acquire.bindString(i3, str);
            }
            i3++;
        }
        int i4 = i2 + size2;
        for (String str2 : collection) {
            if (str2 == null) {
                acquire.bindNull(i4);
            } else {
                acquire.bindString(i4, str2);
            }
            i4++;
        }
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
                int i5 = query.getInt(columnIndexOrThrow5);
                ArrayList<LocalItem> arrayList2 = longSparseArray.get(query.getLong(columnIndexOrThrow));
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>();
                }
                ArrayList<LocalItem> arrayList3 = arrayList2;
                ArrayList<CloudItem> arrayList4 = longSparseArray2.get(query.getLong(columnIndexOrThrow));
                if (arrayList4 == null) {
                    arrayList4 = new ArrayList<>();
                }
                arrayList.add(new UnifiedItem(j3, itemType, j4, j5, i5, arrayList3, arrayList4));
            }
            this.__db.setTransactionSuccessful();
            query.close();
            acquire.release();
            return arrayList;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.discovery.dao.EditDao
    public List<UnifiedItem> getUnifiedByVisualDigests(Collection<String> collection, Set<Integer> set) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT u.id, u.date_taken, u.date_uploaded, u.type, u.dedupe_stage FROM unified_item u LEFT JOIN (SELECT * FROM local_item) l ON l.unified_id = u.id LEFT JOIN (SELECT * FROM cloud_item) c ON c.unified_id = u.id WHERE u.dedupe_stage IN (");
        int size = set.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(") AND (c.visual_digest IN (");
        int size2 = collection.size();
        StringUtil.appendPlaceholders(newStringBuilder, size2);
        newStringBuilder.append(") OR l.visual_digest IN (");
        int size3 = collection.size();
        StringUtil.appendPlaceholders(newStringBuilder, size3);
        newStringBuilder.append(")) ORDER BY u.id ASC");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 0 + size2 + size3);
        int i = 1;
        for (Integer num : set) {
            if (num == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindLong(i, num.intValue());
            }
            i++;
        }
        int i2 = size + 1;
        int i3 = i2;
        for (String str : collection) {
            if (str == null) {
                acquire.bindNull(i3);
            } else {
                acquire.bindString(i3, str);
            }
            i3++;
        }
        int i4 = i2 + size2;
        for (String str2 : collection) {
            if (str2 == null) {
                acquire.bindNull(i4);
            } else {
                acquire.bindString(i4, str2);
            }
            i4++;
        }
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
                int i5 = query.getInt(columnIndexOrThrow5);
                ArrayList<LocalItem> arrayList2 = longSparseArray.get(query.getLong(columnIndexOrThrow));
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>();
                }
                ArrayList<LocalItem> arrayList3 = arrayList2;
                ArrayList<CloudItem> arrayList4 = longSparseArray2.get(query.getLong(columnIndexOrThrow));
                if (arrayList4 == null) {
                    arrayList4 = new ArrayList<>();
                }
                arrayList.add(new UnifiedItem(j3, itemType, j4, j5, i5, arrayList3, arrayList4));
            }
            this.__db.setTransactionSuccessful();
            query.close();
            acquire.release();
            return arrayList;
        } finally {
            this.__db.endTransaction();
        }
    }
}
