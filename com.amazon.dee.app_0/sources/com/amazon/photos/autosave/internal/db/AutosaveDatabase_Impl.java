package com.amazon.photos.autosave.internal.db;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.amazon.deecomms.common.Constants;
import com.amazon.photos.autosave.internal.dao.AutosaveBucketDao;
import com.amazon.photos.autosave.internal.dao.AutosaveBucketDao_Impl;
import com.amazon.photos.autosave.internal.dao.AutosaveItemDao;
import com.amazon.photos.autosave.internal.dao.AutosaveItemDao_Impl;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
/* loaded from: classes13.dex */
public final class AutosaveDatabase_Impl extends AutosaveDatabase {
    private volatile AutosaveBucketDao _autosaveBucketDao;
    private volatile AutosaveItemDao _autosaveItemDao;

    @Override // com.amazon.photos.autosave.internal.db.AutosaveDatabase
    public AutosaveBucketDao bucketDao() {
        AutosaveBucketDao autosaveBucketDao;
        if (this._autosaveBucketDao != null) {
            return this._autosaveBucketDao;
        }
        synchronized (this) {
            if (this._autosaveBucketDao == null) {
                this._autosaveBucketDao = new AutosaveBucketDao_Impl(this);
            }
            autosaveBucketDao = this._autosaveBucketDao;
        }
        return autosaveBucketDao;
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `autosave_item`");
            writableDatabase.execSQL("DELETE FROM `autosave_bucket`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            if (!GeneratedOutlineSupport1.outline188(writableDatabase, "PRAGMA wal_checkpoint(FULL)")) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "autosave_item", "autosave_bucket");
    }

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.mo201create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate(1) { // from class: com.amazon.photos.autosave.internal.db.AutosaveDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `autosave_item` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `local_item_id` INTEGER NOT NULL, `unified_id` INTEGER NOT NULL, `folder_id` INTEGER NOT NULL, `file_path` TEXT NOT NULL, `state` TEXT NOT NULL, `media_type` TEXT NOT NULL)");
                supportSQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_autosave_item_local_item_id` ON `autosave_item` (`local_item_id`)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `autosave_bucket` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `bucket_path` TEXT NOT NULL, `folder_id` INTEGER NOT NULL)");
                supportSQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_autosave_bucket_bucket_path` ON `autosave_bucket` (`bucket_path`)");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '9f1ad6a9c8cf7eb4fc19a947e3612500')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `autosave_item`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `autosave_bucket`");
                if (((RoomDatabase) AutosaveDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) AutosaveDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) AutosaveDatabase_Impl.this).mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (((RoomDatabase) AutosaveDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) AutosaveDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) AutosaveDatabase_Impl.this).mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                ((RoomDatabase) AutosaveDatabase_Impl.this).mDatabase = supportSQLiteDatabase;
                AutosaveDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                if (((RoomDatabase) AutosaveDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) AutosaveDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) AutosaveDatabase_Impl.this).mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
                DBUtil.dropFtsSyncTriggers(supportSQLiteDatabase);
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase supportSQLiteDatabase) {
                HashMap hashMap = new HashMap(7);
                hashMap.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                hashMap.put("local_item_id", new TableInfo.Column("local_item_id", "INTEGER", true, 0, null, 1));
                hashMap.put("unified_id", new TableInfo.Column("unified_id", "INTEGER", true, 0, null, 1));
                hashMap.put("folder_id", new TableInfo.Column("folder_id", "INTEGER", true, 0, null, 1));
                hashMap.put("file_path", new TableInfo.Column("file_path", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1));
                hashMap.put("state", new TableInfo.Column("state", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1));
                HashSet outline135 = GeneratedOutlineSupport1.outline135(hashMap, "media_type", new TableInfo.Column("media_type", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1), 0);
                HashSet hashSet = new HashSet(1);
                hashSet.add(new TableInfo.Index("index_autosave_item_local_item_id", true, Arrays.asList("local_item_id")));
                TableInfo tableInfo = new TableInfo("autosave_item", hashMap, outline135, hashSet);
                TableInfo read = TableInfo.read(supportSQLiteDatabase, "autosave_item");
                if (!tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(false, GeneratedOutlineSupport1.outline60("autosave_item(com.amazon.photos.autosave.model.AutosaveItem).\n Expected:\n", tableInfo, "\n Found:\n", read));
                }
                HashMap hashMap2 = new HashMap(3);
                hashMap2.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                hashMap2.put("bucket_path", new TableInfo.Column("bucket_path", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1));
                HashSet outline1352 = GeneratedOutlineSupport1.outline135(hashMap2, "folder_id", new TableInfo.Column("folder_id", "INTEGER", true, 0, null, 1), 0);
                HashSet hashSet2 = new HashSet(1);
                hashSet2.add(new TableInfo.Index("index_autosave_bucket_bucket_path", true, Arrays.asList("bucket_path")));
                TableInfo tableInfo2 = new TableInfo("autosave_bucket", hashMap2, outline1352, hashSet2);
                TableInfo read2 = TableInfo.read(supportSQLiteDatabase, "autosave_bucket");
                if (!tableInfo2.equals(read2)) {
                    return new RoomOpenHelper.ValidationResult(false, GeneratedOutlineSupport1.outline60("autosave_bucket(com.amazon.photos.autosave.model.AutosaveBucket).\n Expected:\n", tableInfo2, "\n Found:\n", read2));
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "9f1ad6a9c8cf7eb4fc19a947e3612500", "d97ba021b9e2b0e1f37ad9973535fbc1")).build());
    }

    @Override // com.amazon.photos.autosave.internal.db.AutosaveDatabase
    public AutosaveItemDao itemDao() {
        AutosaveItemDao autosaveItemDao;
        if (this._autosaveItemDao != null) {
            return this._autosaveItemDao;
        }
        synchronized (this) {
            if (this._autosaveItemDao == null) {
                this._autosaveItemDao = new AutosaveItemDao_Impl(this);
            }
            autosaveItemDao = this._autosaveItemDao;
        }
        return autosaveItemDao;
    }
}
