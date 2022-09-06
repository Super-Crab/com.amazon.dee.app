package com.amazon.photos.discovery.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.paging.LimitOffsetDataSource;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import com.amazon.alexa.handsfree.protocols.sierracontentprovider.SierraContentProviderContract;
import com.amazon.photos.discovery.internal.util.DiscoveryTypeConverters;
import com.amazon.photos.discovery.model.ItemType;
import com.amazon.photos.discovery.model.LocalItem;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
/* loaded from: classes13.dex */
public final class LocalItemDao_Impl implements LocalItemDao {
    private final RoomDatabase __db;
    private final DiscoveryTypeConverters __discoveryTypeConverters = new DiscoveryTypeConverters();

    public LocalItemDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
    }

    @Override // com.amazon.photos.discovery.dao.LocalItemDao
    public DataSource.Factory<Integer, LocalItem> getAllItems() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM local_item ORDER BY id DESC", 0);
        return new DataSource.Factory<Integer, LocalItem>() { // from class: com.amazon.photos.discovery.dao.LocalItemDao_Impl.1
            @Override // androidx.paging.DataSource.Factory
            public DataSource<Integer, LocalItem> create() {
                return new LimitOffsetDataSource<LocalItem>(LocalItemDao_Impl.this.__db, acquire, false, "local_item") { // from class: com.amazon.photos.discovery.dao.LocalItemDao_Impl.1.1
                    @Override // androidx.room.paging.LimitOffsetDataSource
                    protected List<LocalItem> convertRows(Cursor cursor) {
                        Cursor cursor2 = cursor;
                        int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursor2, "id");
                        int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursor2, "unified_id");
                        int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursor2, "type");
                        int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursor2, "file_path");
                        int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursor2, "duration");
                        int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursor2, "width");
                        int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursor2, "height");
                        int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursor2, "size");
                        int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursor2, "date_added");
                        int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursor2, "date_taken");
                        int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursor2, "date_modified");
                        int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursor2, "start_processing");
                        int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursor2, "end_processing");
                        int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursor2, SierraContentProviderContract.MD5_VALUE);
                        int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursor2, "visual_digest");
                        int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursor2, "parent_id");
                        ArrayList arrayList = new ArrayList(cursor.getCount());
                        while (cursor.moveToNext()) {
                            int i = columnIndexOrThrow;
                            int i2 = columnIndexOrThrow2;
                            arrayList.add(new LocalItem(cursor2.getLong(columnIndexOrThrow), cursor2.getLong(columnIndexOrThrow2), LocalItemDao_Impl.this.__discoveryTypeConverters.toItemType(cursor2.getInt(columnIndexOrThrow3)), cursor2.getString(columnIndexOrThrow4), cursor2.isNull(columnIndexOrThrow5) ? null : Long.valueOf(cursor2.getLong(columnIndexOrThrow5)), cursor2.isNull(columnIndexOrThrow6) ? null : Long.valueOf(cursor2.getLong(columnIndexOrThrow6)), cursor2.isNull(columnIndexOrThrow7) ? null : Long.valueOf(cursor2.getLong(columnIndexOrThrow7)), cursor2.getLong(columnIndexOrThrow8), cursor2.getLong(columnIndexOrThrow9), cursor2.getLong(columnIndexOrThrow10), cursor2.getLong(columnIndexOrThrow11), cursor2.getLong(columnIndexOrThrow12), cursor2.getLong(columnIndexOrThrow13), cursor2.getString(columnIndexOrThrow14), cursor2.getString(columnIndexOrThrow15), cursor2.getLong(columnIndexOrThrow16)));
                            cursor2 = cursor;
                            columnIndexOrThrow = i;
                            columnIndexOrThrow2 = i2;
                        }
                        return arrayList;
                    }
                };
            }
        };
    }

    @Override // com.amazon.photos.discovery.dao.LocalItemDao
    public DataSource.Factory<Integer, LocalItem> getAllValidItems() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM local_item WHERE size > 0 ORDER BY id DESC", 0);
        return new DataSource.Factory<Integer, LocalItem>() { // from class: com.amazon.photos.discovery.dao.LocalItemDao_Impl.2
            @Override // androidx.paging.DataSource.Factory
            public DataSource<Integer, LocalItem> create() {
                return new LimitOffsetDataSource<LocalItem>(LocalItemDao_Impl.this.__db, acquire, false, "local_item") { // from class: com.amazon.photos.discovery.dao.LocalItemDao_Impl.2.1
                    @Override // androidx.room.paging.LimitOffsetDataSource
                    protected List<LocalItem> convertRows(Cursor cursor) {
                        Cursor cursor2 = cursor;
                        int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursor2, "id");
                        int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursor2, "unified_id");
                        int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursor2, "type");
                        int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursor2, "file_path");
                        int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursor2, "duration");
                        int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursor2, "width");
                        int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursor2, "height");
                        int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursor2, "size");
                        int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursor2, "date_added");
                        int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursor2, "date_taken");
                        int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursor2, "date_modified");
                        int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursor2, "start_processing");
                        int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursor2, "end_processing");
                        int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursor2, SierraContentProviderContract.MD5_VALUE);
                        int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursor2, "visual_digest");
                        int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursor2, "parent_id");
                        ArrayList arrayList = new ArrayList(cursor.getCount());
                        while (cursor.moveToNext()) {
                            int i = columnIndexOrThrow;
                            int i2 = columnIndexOrThrow2;
                            arrayList.add(new LocalItem(cursor2.getLong(columnIndexOrThrow), cursor2.getLong(columnIndexOrThrow2), LocalItemDao_Impl.this.__discoveryTypeConverters.toItemType(cursor2.getInt(columnIndexOrThrow3)), cursor2.getString(columnIndexOrThrow4), cursor2.isNull(columnIndexOrThrow5) ? null : Long.valueOf(cursor2.getLong(columnIndexOrThrow5)), cursor2.isNull(columnIndexOrThrow6) ? null : Long.valueOf(cursor2.getLong(columnIndexOrThrow6)), cursor2.isNull(columnIndexOrThrow7) ? null : Long.valueOf(cursor2.getLong(columnIndexOrThrow7)), cursor2.getLong(columnIndexOrThrow8), cursor2.getLong(columnIndexOrThrow9), cursor2.getLong(columnIndexOrThrow10), cursor2.getLong(columnIndexOrThrow11), cursor2.getLong(columnIndexOrThrow12), cursor2.getLong(columnIndexOrThrow13), cursor2.getString(columnIndexOrThrow14), cursor2.getString(columnIndexOrThrow15), cursor2.getLong(columnIndexOrThrow16)));
                            cursor2 = cursor;
                            columnIndexOrThrow = i;
                            columnIndexOrThrow2 = i2;
                        }
                        return arrayList;
                    }
                };
            }
        };
    }

    @Override // com.amazon.photos.discovery.dao.LocalItemDao
    public LiveData<Integer> getItemCount() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(id) FROM local_item", 0);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"local_item"}, false, new Callable<Integer>() { // from class: com.amazon.photos.discovery.dao.LocalItemDao_Impl.3
            protected void finalize() {
                acquire.release();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            /* renamed from: call */
            public Integer mo4299call() throws Exception {
                Integer num = null;
                Cursor query = DBUtil.query(LocalItemDao_Impl.this.__db, acquire, false, null);
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

    @Override // com.amazon.photos.discovery.dao.LocalItemDao
    public List<LocalItem> getLocalItemByIds(Collection<Long> collection) {
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
                    long j3 = query.getLong(columnIndexOrThrow8);
                    long j4 = query.getLong(columnIndexOrThrow9);
                    long j5 = query.getLong(columnIndexOrThrow10);
                    long j6 = query.getLong(columnIndexOrThrow11);
                    long j7 = query.getLong(columnIndexOrThrow12);
                    int i4 = i2;
                    long j8 = query.getLong(i4);
                    int i5 = columnIndexOrThrow14;
                    String string2 = query.getString(i5);
                    i2 = i4;
                    int i6 = columnIndexOrThrow15;
                    String string3 = query.getString(i6);
                    columnIndexOrThrow15 = i6;
                    int i7 = columnIndexOrThrow16;
                    columnIndexOrThrow16 = i7;
                    arrayList.add(new LocalItem(j, j2, itemType, string, valueOf, valueOf2, valueOf3, j3, j4, j5, j6, j7, j8, string2, string3, query.getLong(i7)));
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

    @Override // com.amazon.photos.discovery.dao.LocalItemDao
    public LiveData<Integer> getValidItemCount() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(id) FROM local_item WHERE size > 0", 0);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"local_item"}, false, new Callable<Integer>() { // from class: com.amazon.photos.discovery.dao.LocalItemDao_Impl.4
            protected void finalize() {
                acquire.release();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            /* renamed from: call */
            public Integer mo4300call() throws Exception {
                Integer num = null;
                Cursor query = DBUtil.query(LocalItemDao_Impl.this.__db, acquire, false, null);
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
