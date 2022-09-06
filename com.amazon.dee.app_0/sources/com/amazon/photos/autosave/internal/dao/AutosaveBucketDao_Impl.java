package com.amazon.photos.autosave.internal.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.amazon.photos.autosave.model.AutosaveBucket;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes13.dex */
public final class AutosaveBucketDao_Impl extends AutosaveBucketDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<AutosaveBucket> __deletionAdapterOfAutosaveBucket;
    private final EntityInsertionAdapter<AutosaveBucket> __insertionAdapterOfAutosaveBucket;
    private final SharedSQLiteStatement __preparedStmtOfDeleteByBucketPath;
    private final SharedSQLiteStatement __preparedStmtOfDeleteById;
    private final EntityDeletionOrUpdateAdapter<AutosaveBucket> __updateAdapterOfAutosaveBucket;

    public AutosaveBucketDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfAutosaveBucket = new EntityInsertionAdapter<AutosaveBucket>(roomDatabase) { // from class: com.amazon.photos.autosave.internal.dao.AutosaveBucketDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR IGNORE INTO `autosave_bucket` (`id`,`bucket_path`,`folder_id`) VALUES (nullif(?, 0),?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, AutosaveBucket autosaveBucket) {
                supportSQLiteStatement.bindLong(1, autosaveBucket.getId());
                if (autosaveBucket.getBucketPath() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, autosaveBucket.getBucketPath());
                }
                supportSQLiteStatement.bindLong(3, autosaveBucket.getFolderId());
            }
        };
        this.__deletionAdapterOfAutosaveBucket = new EntityDeletionOrUpdateAdapter<AutosaveBucket>(roomDatabase) { // from class: com.amazon.photos.autosave.internal.dao.AutosaveBucketDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `autosave_bucket` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, AutosaveBucket autosaveBucket) {
                supportSQLiteStatement.bindLong(1, autosaveBucket.getId());
            }
        };
        this.__updateAdapterOfAutosaveBucket = new EntityDeletionOrUpdateAdapter<AutosaveBucket>(roomDatabase) { // from class: com.amazon.photos.autosave.internal.dao.AutosaveBucketDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `autosave_bucket` SET `id` = ?,`bucket_path` = ?,`folder_id` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, AutosaveBucket autosaveBucket) {
                supportSQLiteStatement.bindLong(1, autosaveBucket.getId());
                if (autosaveBucket.getBucketPath() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, autosaveBucket.getBucketPath());
                }
                supportSQLiteStatement.bindLong(3, autosaveBucket.getFolderId());
                supportSQLiteStatement.bindLong(4, autosaveBucket.getId());
            }
        };
        this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.autosave.internal.dao.AutosaveBucketDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM autosave_bucket WHERE id = ?";
            }
        };
        this.__preparedStmtOfDeleteByBucketPath = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.photos.autosave.internal.dao.AutosaveBucketDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM autosave_bucket WHERE bucket_path = ?";
            }
        };
    }

    @Override // com.amazon.photos.autosave.internal.dao.AutosaveBucketDao
    public void deleteBucket(AutosaveBucket autosaveBucket) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfAutosaveBucket.handle(autosaveBucket);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.autosave.internal.dao.AutosaveBucketDao
    public int deleteByBucketPath(String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteByBucketPath.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.beginTransaction();
        try {
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            return executeUpdateDelete;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteByBucketPath.release(acquire);
        }
    }

    @Override // com.amazon.photos.autosave.internal.dao.AutosaveBucketDao
    public void deleteById(long j) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteById.acquire();
        acquire.bindLong(1, j);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteById.release(acquire);
        }
    }

    @Override // com.amazon.photos.autosave.internal.dao.AutosaveBucketDao
    public List<AutosaveBucket> getAllBuckets() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM autosave_bucket", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "bucket_path");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "folder_id");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(new AutosaveBucket(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2), query.getLong(columnIndexOrThrow3)));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.autosave.internal.dao.AutosaveBucketDao
    public AutosaveBucket getBucketById(long j) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM autosave_bucket WHERE id = ?", 1);
        acquire.bindLong(1, j);
        this.__db.assertNotSuspendingTransaction();
        AutosaveBucket autosaveBucket = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "bucket_path");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "folder_id");
            if (query.moveToFirst()) {
                autosaveBucket = new AutosaveBucket(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2), query.getLong(columnIndexOrThrow3));
            }
            return autosaveBucket;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.autosave.internal.dao.AutosaveBucketDao
    public AutosaveBucket getBucketByPath(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM autosave_bucket WHERE bucket_path = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            return query.moveToFirst() ? new AutosaveBucket(query.getLong(CursorUtil.getColumnIndexOrThrow(query, "id")), query.getString(CursorUtil.getColumnIndexOrThrow(query, "bucket_path")), query.getLong(CursorUtil.getColumnIndexOrThrow(query, "folder_id"))) : null;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.photos.autosave.internal.dao.AutosaveBucketDao
    public long insertOrIgnoreItem(AutosaveBucket autosaveBucket) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long insertAndReturnId = this.__insertionAdapterOfAutosaveBucket.insertAndReturnId(autosaveBucket);
            this.__db.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.photos.autosave.internal.dao.AutosaveBucketDao
    public void updateItem(AutosaveBucket autosaveBucket) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfAutosaveBucket.handle(autosaveBucket);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }
}
