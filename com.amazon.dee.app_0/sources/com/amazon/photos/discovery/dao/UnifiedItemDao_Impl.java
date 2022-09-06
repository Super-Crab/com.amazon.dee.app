package com.amazon.photos.discovery.dao;

import android.database.Cursor;
import androidx.collection.LongSparseArray;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.paging.LimitOffsetDataSource;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.handsfree.protocols.sierracontentprovider.SierraContentProviderContract;
import com.amazon.photos.discovery.internal.util.DiscoveryTypeConverters;
import com.amazon.photos.discovery.internal.worker.DedupeWorkerKt;
import com.amazon.photos.discovery.model.CloudItem;
import com.amazon.photos.discovery.model.ItemType;
import com.amazon.photos.discovery.model.LocalItem;
import com.amazon.photos.discovery.model.UnifiedItem;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
/* loaded from: classes13.dex */
public final class UnifiedItemDao_Impl implements UnifiedItemDao {
    private final RoomDatabase __db;
    private final DiscoveryTypeConverters __discoveryTypeConverters = new DiscoveryTypeConverters();

    public UnifiedItemDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipcloudItemAscomAmazonPhotosDiscoveryModelCloudItem(LongSparseArray<ArrayList<CloudItem>> longSparseArray) {
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

    /* JADX INFO: Access modifiers changed from: private */
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
    public void __fetchRelationshiplocalItemAscomAmazonPhotosDiscoveryModelLocalItem(androidx.collection.LongSparseArray<java.util.ArrayList<com.amazon.photos.discovery.model.LocalItem>> r54) {
        /*
            Method dump skipped, instructions count: 555
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.discovery.dao.UnifiedItemDao_Impl.__fetchRelationshiplocalItemAscomAmazonPhotosDiscoveryModelLocalItem(androidx.collection.LongSparseArray):void");
    }

    @Override // com.amazon.photos.discovery.dao.UnifiedItemDao
    public DataSource.Factory<Integer, UnifiedItem> getAllItems() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT u.id, u.date_taken, u.date_uploaded, u.type, u.dedupe_stage FROM unified_item u LEFT JOIN (SELECT * FROM local_item) l ON l.unified_id = u.id LEFT JOIN (SELECT * FROM cloud_item) c ON c.unified_id = u.id ORDER BY u.id DESC", 0);
        return new DataSource.Factory<Integer, UnifiedItem>() { // from class: com.amazon.photos.discovery.dao.UnifiedItemDao_Impl.1
            @Override // androidx.paging.DataSource.Factory
            public DataSource<Integer, UnifiedItem> create() {
                return new LimitOffsetDataSource<UnifiedItem>(UnifiedItemDao_Impl.this.__db, acquire, true, "local_item", "cloud_item", "unified_item") { // from class: com.amazon.photos.discovery.dao.UnifiedItemDao_Impl.1.1
                    @Override // androidx.room.paging.LimitOffsetDataSource
                    protected List<UnifiedItem> convertRows(Cursor cursor) {
                        int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursor, "id");
                        int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursor, "date_taken");
                        int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursor, "date_uploaded");
                        int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursor, "type");
                        int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursor, DedupeWorkerKt.PARAM_DEDUPE_STAGE_ID);
                        LongSparseArray longSparseArray = new LongSparseArray();
                        LongSparseArray longSparseArray2 = new LongSparseArray();
                        while (cursor.moveToNext()) {
                            long j = cursor.getLong(columnIndexOrThrow);
                            if (((ArrayList) longSparseArray.get(j)) == null) {
                                longSparseArray.put(j, new ArrayList());
                            }
                            long j2 = cursor.getLong(columnIndexOrThrow);
                            if (((ArrayList) longSparseArray2.get(j2)) == null) {
                                longSparseArray2.put(j2, new ArrayList());
                            }
                        }
                        cursor.moveToPosition(-1);
                        UnifiedItemDao_Impl.this.__fetchRelationshiplocalItemAscomAmazonPhotosDiscoveryModelLocalItem(longSparseArray);
                        UnifiedItemDao_Impl.this.__fetchRelationshipcloudItemAscomAmazonPhotosDiscoveryModelCloudItem(longSparseArray2);
                        ArrayList arrayList = new ArrayList(cursor.getCount());
                        while (cursor.moveToNext()) {
                            long j3 = cursor.getLong(columnIndexOrThrow);
                            long j4 = cursor.getLong(columnIndexOrThrow2);
                            long j5 = cursor.getLong(columnIndexOrThrow3);
                            ItemType itemType = UnifiedItemDao_Impl.this.__discoveryTypeConverters.toItemType(cursor.getInt(columnIndexOrThrow4));
                            int i = cursor.getInt(columnIndexOrThrow5);
                            ArrayList arrayList2 = (ArrayList) longSparseArray.get(cursor.getLong(columnIndexOrThrow));
                            if (arrayList2 == null) {
                                arrayList2 = new ArrayList();
                            }
                            ArrayList arrayList3 = arrayList2;
                            ArrayList arrayList4 = (ArrayList) longSparseArray2.get(cursor.getLong(columnIndexOrThrow));
                            if (arrayList4 == null) {
                                arrayList4 = new ArrayList();
                            }
                            arrayList.add(new UnifiedItem(j3, itemType, j4, j5, i, arrayList3, arrayList4));
                        }
                        return arrayList;
                    }
                };
            }
        };
    }

    @Override // com.amazon.photos.discovery.dao.UnifiedItemDao
    public List<UnifiedItem> getAllUnifiedItemsByFolder(long j, int i, Set<? extends ItemType> set) {
        StringBuilder outline114 = GeneratedOutlineSupport1.outline114("SELECT u.id, u.date_taken, u.date_uploaded, u.type, u.dedupe_stage FROM unified_item u JOIN local_item l on u.id = l.unified_id AND l.size > 0 AND l.parent_id = ", WebConstants.UriConstants.QUESTIONMARK_KEY, " AND l.type IN (");
        int size = set.size();
        StringUtil.appendPlaceholders(outline114, size);
        outline114.append(") LEFT JOIN cloud_item c on u.id = c.unified_id WHERE c.unified_id IS NULL AND u.dedupe_stage = ");
        outline114.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
        outline114.append(" ORDER BY u.id DESC");
        int i2 = 2;
        int i3 = size + 2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(outline114.toString(), i3);
        acquire.bindLong(1, j);
        for (ItemType itemType : set) {
            acquire.bindLong(i2, this.__discoveryTypeConverters.fromItemType(itemType));
            i2++;
        }
        acquire.bindLong(i3, i);
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
                long j2 = query.getLong(columnIndexOrThrow);
                if (longSparseArray.get(j2) == null) {
                    longSparseArray.put(j2, new ArrayList<>());
                }
                long j3 = query.getLong(columnIndexOrThrow);
                if (longSparseArray2.get(j3) == null) {
                    longSparseArray2.put(j3, new ArrayList<>());
                }
            }
            query.moveToPosition(-1);
            __fetchRelationshiplocalItemAscomAmazonPhotosDiscoveryModelLocalItem(longSparseArray);
            __fetchRelationshipcloudItemAscomAmazonPhotosDiscoveryModelCloudItem(longSparseArray2);
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                long j4 = query.getLong(columnIndexOrThrow);
                long j5 = query.getLong(columnIndexOrThrow2);
                long j6 = query.getLong(columnIndexOrThrow3);
                ItemType itemType2 = this.__discoveryTypeConverters.toItemType(query.getInt(columnIndexOrThrow4));
                int i4 = query.getInt(columnIndexOrThrow5);
                ArrayList<LocalItem> arrayList2 = longSparseArray.get(query.getLong(columnIndexOrThrow));
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>();
                }
                ArrayList<LocalItem> arrayList3 = arrayList2;
                ArrayList<CloudItem> arrayList4 = longSparseArray2.get(query.getLong(columnIndexOrThrow));
                if (arrayList4 == null) {
                    arrayList4 = new ArrayList<>();
                }
                arrayList.add(new UnifiedItem(j4, itemType2, j5, j6, i4, arrayList3, arrayList4));
            }
            this.__db.setTransactionSuccessful();
            query.close();
            acquire.release();
            return arrayList;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.discovery.dao.UnifiedItemDao
    public List<UnifiedItem> getAllUnifiedItemsInFoldersByBatch(long j, List<Long> list, int i, int i2, Set<? extends ItemType> set) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT u.id, u.date_taken, u.date_uploaded, u.type, u.dedupe_stage FROM unified_item u JOIN local_item l on u.id = l.unified_id AND l.size > 0 AND l.parent_id IN (");
        int size = list.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(") AND l.type IN (");
        int size2 = set.size();
        StringUtil.appendPlaceholders(newStringBuilder, size2);
        newStringBuilder.append(") LEFT JOIN cloud_item c on u.id = c.unified_id WHERE c.unified_id IS NULL AND u.id > ");
        newStringBuilder.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
        newStringBuilder.append(" AND u.dedupe_stage = ");
        newStringBuilder.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
        int i3 = size + 3 + size2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(GeneratedOutlineSupport1.outline92(newStringBuilder, " ORDER BY u.id ASC LIMIT ", WebConstants.UriConstants.QUESTIONMARK_KEY, " "), i3);
        int i4 = 1;
        for (Long l : list) {
            if (l == null) {
                acquire.bindNull(i4);
            } else {
                acquire.bindLong(i4, l.longValue());
            }
            i4++;
        }
        int i5 = size + 1;
        int i6 = i5;
        for (ItemType itemType : set) {
            acquire.bindLong(i6, this.__discoveryTypeConverters.fromItemType(itemType));
            i6++;
        }
        acquire.bindLong(i5 + size2, j);
        acquire.bindLong(size + 2 + size2, i);
        acquire.bindLong(i3, i2);
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
                long j2 = query.getLong(columnIndexOrThrow);
                if (longSparseArray.get(j2) == null) {
                    longSparseArray.put(j2, new ArrayList<>());
                }
                long j3 = query.getLong(columnIndexOrThrow);
                if (longSparseArray2.get(j3) == null) {
                    longSparseArray2.put(j3, new ArrayList<>());
                }
            }
            query.moveToPosition(-1);
            __fetchRelationshiplocalItemAscomAmazonPhotosDiscoveryModelLocalItem(longSparseArray);
            __fetchRelationshipcloudItemAscomAmazonPhotosDiscoveryModelCloudItem(longSparseArray2);
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                long j4 = query.getLong(columnIndexOrThrow);
                long j5 = query.getLong(columnIndexOrThrow2);
                long j6 = query.getLong(columnIndexOrThrow3);
                ItemType itemType2 = this.__discoveryTypeConverters.toItemType(query.getInt(columnIndexOrThrow4));
                int i7 = query.getInt(columnIndexOrThrow5);
                ArrayList<LocalItem> arrayList2 = longSparseArray.get(query.getLong(columnIndexOrThrow));
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>();
                }
                ArrayList<LocalItem> arrayList3 = arrayList2;
                ArrayList<CloudItem> arrayList4 = longSparseArray2.get(query.getLong(columnIndexOrThrow));
                if (arrayList4 == null) {
                    arrayList4 = new ArrayList<>();
                }
                arrayList.add(new UnifiedItem(j4, itemType2, j5, j6, i7, arrayList3, arrayList4));
            }
            this.__db.setTransactionSuccessful();
            query.close();
            acquire.release();
            return arrayList;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.discovery.dao.UnifiedItemDao
    public DataSource.Factory<Integer, UnifiedItem> getAllValidItems() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT u.id, u.date_taken, u.date_uploaded, u.type, u.dedupe_stage FROM unified_item u LEFT JOIN (SELECT * FROM local_item WHERE size > 0) l ON l.unified_id = u.id LEFT JOIN (SELECT * FROM cloud_item) c ON c.unified_id = u.id ORDER BY u.id DESC", 0);
        return new DataSource.Factory<Integer, UnifiedItem>() { // from class: com.amazon.photos.discovery.dao.UnifiedItemDao_Impl.2
            @Override // androidx.paging.DataSource.Factory
            public DataSource<Integer, UnifiedItem> create() {
                return new LimitOffsetDataSource<UnifiedItem>(UnifiedItemDao_Impl.this.__db, acquire, true, "local_item", "cloud_item", "unified_item") { // from class: com.amazon.photos.discovery.dao.UnifiedItemDao_Impl.2.1
                    @Override // androidx.room.paging.LimitOffsetDataSource
                    protected List<UnifiedItem> convertRows(Cursor cursor) {
                        int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursor, "id");
                        int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursor, "date_taken");
                        int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursor, "date_uploaded");
                        int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursor, "type");
                        int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursor, DedupeWorkerKt.PARAM_DEDUPE_STAGE_ID);
                        LongSparseArray longSparseArray = new LongSparseArray();
                        LongSparseArray longSparseArray2 = new LongSparseArray();
                        while (cursor.moveToNext()) {
                            long j = cursor.getLong(columnIndexOrThrow);
                            if (((ArrayList) longSparseArray.get(j)) == null) {
                                longSparseArray.put(j, new ArrayList());
                            }
                            long j2 = cursor.getLong(columnIndexOrThrow);
                            if (((ArrayList) longSparseArray2.get(j2)) == null) {
                                longSparseArray2.put(j2, new ArrayList());
                            }
                        }
                        cursor.moveToPosition(-1);
                        UnifiedItemDao_Impl.this.__fetchRelationshiplocalItemAscomAmazonPhotosDiscoveryModelLocalItem(longSparseArray);
                        UnifiedItemDao_Impl.this.__fetchRelationshipcloudItemAscomAmazonPhotosDiscoveryModelCloudItem(longSparseArray2);
                        ArrayList arrayList = new ArrayList(cursor.getCount());
                        while (cursor.moveToNext()) {
                            long j3 = cursor.getLong(columnIndexOrThrow);
                            long j4 = cursor.getLong(columnIndexOrThrow2);
                            long j5 = cursor.getLong(columnIndexOrThrow3);
                            ItemType itemType = UnifiedItemDao_Impl.this.__discoveryTypeConverters.toItemType(cursor.getInt(columnIndexOrThrow4));
                            int i = cursor.getInt(columnIndexOrThrow5);
                            ArrayList arrayList2 = (ArrayList) longSparseArray.get(cursor.getLong(columnIndexOrThrow));
                            if (arrayList2 == null) {
                                arrayList2 = new ArrayList();
                            }
                            ArrayList arrayList3 = arrayList2;
                            ArrayList arrayList4 = (ArrayList) longSparseArray2.get(cursor.getLong(columnIndexOrThrow));
                            if (arrayList4 == null) {
                                arrayList4 = new ArrayList();
                            }
                            arrayList.add(new UnifiedItem(j3, itemType, j4, j5, i, arrayList3, arrayList4));
                        }
                        return arrayList;
                    }
                };
            }
        };
    }

    @Override // com.amazon.photos.discovery.dao.UnifiedItemDao
    public LiveData<Integer> getItemCount() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(id) FROM unified_item", 0);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"unified_item"}, false, new Callable<Integer>() { // from class: com.amazon.photos.discovery.dao.UnifiedItemDao_Impl.3
            protected void finalize() {
                acquire.release();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            /* renamed from: call */
            public Integer mo4301call() throws Exception {
                Integer num = null;
                Cursor query = DBUtil.query(UnifiedItemDao_Impl.this.__db, acquire, false, null);
                try {
                    if (query.moveToFirst() && !query.isNull(0)) {
                        num = Integer.valueOf(query.getInt(0));
                    }
                    return num;
                } finally {
                    query.close();
                }
            }
        });
    }

    @Override // com.amazon.photos.discovery.dao.UnifiedItemDao
    public List<UnifiedItem> getUnifiedItemsByIdsAndFolders(List<Long> list, List<Long> list2, int i, Set<? extends ItemType> set) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT u.id, u.date_taken, u.date_uploaded, u.type, u.dedupe_stage FROM unified_item u JOIN local_item l on u.id = l.unified_id AND l.size > 0 AND l.parent_id IN (");
        int size = list.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(") AND l.type IN (");
        int size2 = set.size();
        StringUtil.appendPlaceholders(newStringBuilder, size2);
        newStringBuilder.append(") LEFT JOIN cloud_item c on u.id = c.unified_id WHERE c.unified_id IS NULL AND u.dedupe_stage = ");
        newStringBuilder.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
        newStringBuilder.append(" AND u.id IN (");
        int size3 = list2.size();
        StringUtil.appendPlaceholders(newStringBuilder, size3);
        newStringBuilder.append(") ORDER BY u.id DESC");
        int i2 = size + 1;
        int i3 = i2 + size2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size3 + i3);
        int i4 = 1;
        for (Long l : list) {
            if (l == null) {
                acquire.bindNull(i4);
            } else {
                acquire.bindLong(i4, l.longValue());
            }
            i4++;
        }
        for (ItemType itemType : set) {
            acquire.bindLong(i2, this.__discoveryTypeConverters.fromItemType(itemType));
            i2++;
        }
        acquire.bindLong(i3, i);
        int i5 = size + 2 + size2;
        for (Long l2 : list2) {
            if (l2 == null) {
                acquire.bindNull(i5);
            } else {
                acquire.bindLong(i5, l2.longValue());
            }
            i5++;
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
                ItemType itemType2 = this.__discoveryTypeConverters.toItemType(query.getInt(columnIndexOrThrow4));
                int i6 = query.getInt(columnIndexOrThrow5);
                ArrayList<LocalItem> arrayList2 = longSparseArray.get(query.getLong(columnIndexOrThrow));
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>();
                }
                ArrayList<LocalItem> arrayList3 = arrayList2;
                ArrayList<CloudItem> arrayList4 = longSparseArray2.get(query.getLong(columnIndexOrThrow));
                if (arrayList4 == null) {
                    arrayList4 = new ArrayList<>();
                }
                arrayList.add(new UnifiedItem(j3, itemType2, j4, j5, i6, arrayList3, arrayList4));
            }
            this.__db.setTransactionSuccessful();
            query.close();
            acquire.release();
            return arrayList;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.discovery.dao.UnifiedItemDao
    public LiveData<Integer> getValidItemCount() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(DISTINCT unified_id) FROM local_item WHERE size > 0", 0);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"local_item"}, false, new Callable<Integer>() { // from class: com.amazon.photos.discovery.dao.UnifiedItemDao_Impl.4
            protected void finalize() {
                acquire.release();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            /* renamed from: call */
            public Integer mo4302call() throws Exception {
                Integer num = null;
                Cursor query = DBUtil.query(UnifiedItemDao_Impl.this.__db, acquire, false, null);
                try {
                    if (query.moveToFirst() && !query.isNull(0)) {
                        num = Integer.valueOf(query.getInt(0));
                    }
                    return num;
                } finally {
                    query.close();
                }
            }
        });
    }
}
