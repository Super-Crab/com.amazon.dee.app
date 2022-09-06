package com.amazon.photos.discovery.dao;

import android.database.Cursor;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import com.amazon.alexa.handsfree.protocols.sierracontentprovider.SierraContentProviderContract;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.photos.discovery.internal.util.DiscoveryTypeConverters;
import com.amazon.photos.discovery.model.LocalFolder;
import com.amazon.photos.discovery.model.LocalFolderCover;
import com.amazon.photos.discovery.model.LocalFolderStats;
import com.amazon.photos.discovery.model.LocalItem;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes13.dex */
public final class LocalFolderDao_Impl implements LocalFolderDao {
    private final RoomDatabase __db;
    private final DiscoveryTypeConverters __discoveryTypeConverters = new DiscoveryTypeConverters();

    public LocalFolderDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
    }

    @Override // com.amazon.photos.discovery.dao.LocalFolderDao
    public List<LocalFolder> getAllFolders() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM local_folder", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "name");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, RouteParameter.PATH);
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "folder_type");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(new LocalFolder(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), this.__discoveryTypeConverters.toFolderType(query.getString(columnIndexOrThrow4))));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.discovery.dao.LocalFolderDao
    public List<LocalFolder> getFoldersById(List<Long> list) {
        StringBuilder outline114 = GeneratedOutlineSupport1.outline114("SELECT ", "*", " FROM local_folder where id in (");
        int size = list.size();
        StringUtil.appendPlaceholders(outline114, size);
        outline114.append(")");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(outline114.toString(), size + 0);
        int i = 1;
        for (Long l : list) {
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
                arrayList.add(new LocalFolder(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), this.__discoveryTypeConverters.toFolderType(query.getString(columnIndexOrThrow4))));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.discovery.dao.LocalFolderDao
    public List<LocalFolder> getFoldersByPath(List<String> list) {
        StringBuilder outline114 = GeneratedOutlineSupport1.outline114("SELECT ", "*", " FROM local_folder where path in (");
        int size = list.size();
        StringUtil.appendPlaceholders(outline114, size);
        outline114.append(")");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(outline114.toString(), size + 0);
        int i = 1;
        for (String str : list) {
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
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "name");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, RouteParameter.PATH);
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "folder_type");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(new LocalFolder(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), this.__discoveryTypeConverters.toFolderType(query.getString(columnIndexOrThrow4))));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.discovery.dao.LocalFolderDao
    public List<LocalFolder> getFoldersByType(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM local_folder WHERE folder_type = ?", 1);
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
                arrayList.add(new LocalFolder(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), this.__discoveryTypeConverters.toFolderType(query.getString(columnIndexOrThrow4))));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.discovery.dao.LocalFolderDao
    public List<LocalFolderCover> getLocalFolderCovers() {
        RoomSQLiteQuery roomSQLiteQuery;
        int i;
        long j;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        LocalItem localItem;
        int i8;
        long j2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select parent_id as folderId, max(date_taken), * from local_item group by parent_id", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "folderId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "unified_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "type");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "file_path");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "duration");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "width");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "height");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "size");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "date_added");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "date_taken");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "date_modified");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "start_processing");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "end_processing");
                try {
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, SierraContentProviderContract.MD5_VALUE);
                    int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "visual_digest");
                    int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "parent_id");
                    int i9 = columnIndexOrThrow14;
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        ArrayList arrayList2 = arrayList;
                        long j3 = query.getLong(columnIndexOrThrow);
                        try {
                            if (!query.isNull(columnIndexOrThrow2) || !query.isNull(columnIndexOrThrow3) || !query.isNull(columnIndexOrThrow4) || !query.isNull(columnIndexOrThrow5) || !query.isNull(columnIndexOrThrow6) || !query.isNull(columnIndexOrThrow7) || !query.isNull(columnIndexOrThrow8) || !query.isNull(columnIndexOrThrow9) || !query.isNull(columnIndexOrThrow10) || !query.isNull(columnIndexOrThrow11) || !query.isNull(columnIndexOrThrow12) || !query.isNull(columnIndexOrThrow13)) {
                                i = columnIndexOrThrow;
                                j = j3;
                                i2 = columnIndexOrThrow15;
                                i3 = columnIndexOrThrow17;
                                i4 = i9;
                            } else {
                                i = columnIndexOrThrow;
                                i4 = i9;
                                if (query.isNull(i4)) {
                                    j = j3;
                                    i2 = columnIndexOrThrow15;
                                    if (query.isNull(i2)) {
                                        int i10 = columnIndexOrThrow16;
                                        if (query.isNull(i10)) {
                                            columnIndexOrThrow16 = i10;
                                            i3 = columnIndexOrThrow17;
                                            if (query.isNull(i3)) {
                                                i7 = columnIndexOrThrow2;
                                                i6 = columnIndexOrThrow3;
                                                i5 = columnIndexOrThrow4;
                                                i8 = columnIndexOrThrow16;
                                                localItem = null;
                                                int i11 = i4;
                                                int i12 = i2;
                                                int i13 = i3;
                                                arrayList = arrayList2;
                                                arrayList.add(new LocalFolderCover(j, localItem));
                                                columnIndexOrThrow3 = i6;
                                                columnIndexOrThrow4 = i5;
                                                columnIndexOrThrow = i;
                                                columnIndexOrThrow17 = i13;
                                                i9 = i11;
                                                columnIndexOrThrow16 = i8;
                                                columnIndexOrThrow2 = i7;
                                                columnIndexOrThrow15 = i12;
                                            }
                                        } else {
                                            columnIndexOrThrow16 = i10;
                                        }
                                    }
                                } else {
                                    j = j3;
                                    i2 = columnIndexOrThrow15;
                                }
                                i3 = columnIndexOrThrow17;
                            }
                            i8 = columnIndexOrThrow16;
                            localItem = new LocalItem(j2, query.getLong(columnIndexOrThrow3), this.__discoveryTypeConverters.toItemType(query.getInt(columnIndexOrThrow4)), query.getString(columnIndexOrThrow5), query.isNull(columnIndexOrThrow6) ? null : Long.valueOf(query.getLong(columnIndexOrThrow6)), query.isNull(columnIndexOrThrow7) ? null : Long.valueOf(query.getLong(columnIndexOrThrow7)), query.isNull(columnIndexOrThrow8) ? null : Long.valueOf(query.getLong(columnIndexOrThrow8)), query.getLong(columnIndexOrThrow9), query.getLong(columnIndexOrThrow10), query.getLong(columnIndexOrThrow11), query.getLong(columnIndexOrThrow12), query.getLong(columnIndexOrThrow13), query.getLong(i4), query.getString(i2), query.getString(i8), query.getLong(i3));
                            int i112 = i4;
                            int i122 = i2;
                            int i132 = i3;
                            arrayList = arrayList2;
                            arrayList.add(new LocalFolderCover(j, localItem));
                            columnIndexOrThrow3 = i6;
                            columnIndexOrThrow4 = i5;
                            columnIndexOrThrow = i;
                            columnIndexOrThrow17 = i132;
                            i9 = i112;
                            columnIndexOrThrow16 = i8;
                            columnIndexOrThrow2 = i7;
                            columnIndexOrThrow15 = i122;
                        } catch (Throwable th) {
                            th = th;
                            query.close();
                            roomSQLiteQuery.release();
                            throw th;
                        }
                        j2 = query.getLong(columnIndexOrThrow2);
                        i7 = columnIndexOrThrow2;
                        i6 = columnIndexOrThrow3;
                        i5 = columnIndexOrThrow4;
                    }
                    query.close();
                    roomSQLiteQuery.release();
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.amazon.photos.discovery.dao.LocalFolderDao
    public List<LocalFolderStats> getLocalFolderStats() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT parent_id as folderId, count(*) as itemCount FROM local_item GROUP BY parent_id", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "folderId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "itemCount");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(new LocalFolderStats(query.getLong(columnIndexOrThrow), query.getLong(columnIndexOrThrow2)));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }
}
