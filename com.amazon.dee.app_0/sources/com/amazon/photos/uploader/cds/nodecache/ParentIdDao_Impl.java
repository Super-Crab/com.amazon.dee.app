package com.amazon.photos.uploader.cds.nodecache;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
/* loaded from: classes13.dex */
public final class ParentIdDao_Impl implements ParentIdDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<ParentId> __insertionAdapterOfParentId;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

    public ParentIdDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfParentId = new EntityInsertionAdapter<ParentId>(roomDatabase) { // from class: com.amazon.photos.uploader.cds.nodecache.ParentIdDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `parent_id` (`path`,`node_id`) VALUES (?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, ParentId parentId) {
                if (parentId.getPath() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, parentId.getPath());
                }
                if (parentId.getNodeId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, parentId.getNodeId());
                }
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.uploader.cds.nodecache.ParentIdDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM parent_id";
            }
        };
    }

    @Override // com.amazon.photos.uploader.cds.nodecache.ParentIdDao
    public void deleteAll() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteAll.acquire();
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAll.release(acquire);
        }
    }

    @Override // com.amazon.photos.uploader.cds.nodecache.ParentIdDao
    public String get(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT node_id FROM parent_id WHERE path = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        String str2 = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            if (query.moveToFirst()) {
                str2 = query.getString(0);
            }
            return str2;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.uploader.cds.nodecache.ParentIdDao
    public long insert(ParentId parentId) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long insertAndReturnId = this.__insertionAdapterOfParentId.insertAndReturnId(parentId);
            this.__db.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.__db.endTransaction();
        }
    }
}
