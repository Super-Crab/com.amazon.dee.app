package com.amazon.photos.uploader.cds.multipart;

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
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
/* loaded from: classes13.dex */
public final class MultiPartUploadDatabase_Impl extends MultiPartUploadDatabase {
    private volatile MultipartUploadRequestMetadataDao _multipartUploadRequestMetadataDao;
    private volatile PartInfoDao _partInfoDao;

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `part_info`");
            writableDatabase.execSQL("DELETE FROM `multipart_upload_request_metadata`");
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
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "part_info", "multipart_upload_request_metadata");
    }

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.mo201create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate(1) { // from class: com.amazon.photos.uploader.cds.multipart.MultiPartUploadDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `part_info` (`part_id` INTEGER NOT NULL, `upload_request_id` INTEGER NOT NULL, `part_upload_state` TEXT NOT NULL, `part_enqueue_timestamp` INTEGER NOT NULL, `part_upload_start_timestamp` INTEGER NOT NULL, `part_upload_complete_timestamp` INTEGER NOT NULL, `part_md5` TEXT, `part_size` INTEGER NOT NULL, `part_offset` INTEGER NOT NULL, `service_upload_id` TEXT, `node_id` TEXT, PRIMARY KEY(`part_id`, `upload_request_id`))");
                supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `idx_upload_request_id` ON `part_info` (`upload_request_id`)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `multipart_upload_request_metadata` (`upload_request_id` INTEGER NOT NULL, `node_id` TEXT NOT NULL, `upload_id` TEXT, `part_size` INTEGER, `total_parts` INTEGER, `multipart_start_time` TEXT, `multipart_expiration_time` TEXT, PRIMARY KEY(`upload_request_id`))");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'd9aab36d919c02525c9c865723f2c2b8')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `part_info`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `multipart_upload_request_metadata`");
                if (((RoomDatabase) MultiPartUploadDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) MultiPartUploadDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) MultiPartUploadDatabase_Impl.this).mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (((RoomDatabase) MultiPartUploadDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) MultiPartUploadDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) MultiPartUploadDatabase_Impl.this).mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                ((RoomDatabase) MultiPartUploadDatabase_Impl.this).mDatabase = supportSQLiteDatabase;
                MultiPartUploadDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                if (((RoomDatabase) MultiPartUploadDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) MultiPartUploadDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) MultiPartUploadDatabase_Impl.this).mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
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
                HashMap hashMap = new HashMap(11);
                hashMap.put("part_id", new TableInfo.Column("part_id", "INTEGER", true, 1, null, 1));
                hashMap.put("upload_request_id", new TableInfo.Column("upload_request_id", "INTEGER", true, 2, null, 1));
                hashMap.put("part_upload_state", new TableInfo.Column("part_upload_state", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1));
                hashMap.put("part_enqueue_timestamp", new TableInfo.Column("part_enqueue_timestamp", "INTEGER", true, 0, null, 1));
                hashMap.put("part_upload_start_timestamp", new TableInfo.Column("part_upload_start_timestamp", "INTEGER", true, 0, null, 1));
                hashMap.put("part_upload_complete_timestamp", new TableInfo.Column("part_upload_complete_timestamp", "INTEGER", true, 0, null, 1));
                hashMap.put("part_md5", new TableInfo.Column("part_md5", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap.put("part_size", new TableInfo.Column("part_size", "INTEGER", true, 0, null, 1));
                hashMap.put("part_offset", new TableInfo.Column("part_offset", "INTEGER", true, 0, null, 1));
                hashMap.put("service_upload_id", new TableInfo.Column("service_upload_id", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                HashSet outline135 = GeneratedOutlineSupport1.outline135(hashMap, "node_id", new TableInfo.Column("node_id", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1), 0);
                HashSet hashSet = new HashSet(1);
                hashSet.add(new TableInfo.Index("idx_upload_request_id", false, Arrays.asList("upload_request_id")));
                TableInfo tableInfo = new TableInfo("part_info", hashMap, outline135, hashSet);
                TableInfo read = TableInfo.read(supportSQLiteDatabase, "part_info");
                if (!tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(false, GeneratedOutlineSupport1.outline60("part_info(com.amazon.photos.uploader.cds.multipart.PartInfo).\n Expected:\n", tableInfo, "\n Found:\n", read));
                }
                HashMap hashMap2 = new HashMap(7);
                hashMap2.put("upload_request_id", new TableInfo.Column("upload_request_id", "INTEGER", true, 1, null, 1));
                hashMap2.put("node_id", new TableInfo.Column("node_id", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1));
                hashMap2.put("upload_id", new TableInfo.Column("upload_id", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap2.put("part_size", new TableInfo.Column("part_size", "INTEGER", false, 0, null, 1));
                hashMap2.put("total_parts", new TableInfo.Column("total_parts", "INTEGER", false, 0, null, 1));
                hashMap2.put("multipart_start_time", new TableInfo.Column("multipart_start_time", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                TableInfo tableInfo2 = new TableInfo("multipart_upload_request_metadata", hashMap2, GeneratedOutlineSupport1.outline135(hashMap2, "multipart_expiration_time", new TableInfo.Column("multipart_expiration_time", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1), 0), new HashSet(0));
                TableInfo read2 = TableInfo.read(supportSQLiteDatabase, "multipart_upload_request_metadata");
                if (!tableInfo2.equals(read2)) {
                    return new RoomOpenHelper.ValidationResult(false, GeneratedOutlineSupport1.outline60("multipart_upload_request_metadata(com.amazon.photos.uploader.cds.multipart.MultipartUploadRequestMetadata).\n Expected:\n", tableInfo2, "\n Found:\n", read2));
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "d9aab36d919c02525c9c865723f2c2b8", "b1cd5d43e812f71d5174aaaca1886752")).build());
    }

    @Override // com.amazon.photos.uploader.cds.multipart.MultiPartUploadDatabase
    public MultipartUploadRequestMetadataDao multipartUploadRequestMetadataDao() {
        MultipartUploadRequestMetadataDao multipartUploadRequestMetadataDao;
        if (this._multipartUploadRequestMetadataDao != null) {
            return this._multipartUploadRequestMetadataDao;
        }
        synchronized (this) {
            if (this._multipartUploadRequestMetadataDao == null) {
                this._multipartUploadRequestMetadataDao = new MultipartUploadRequestMetadataDao_Impl(this);
            }
            multipartUploadRequestMetadataDao = this._multipartUploadRequestMetadataDao;
        }
        return multipartUploadRequestMetadataDao;
    }

    @Override // com.amazon.photos.uploader.cds.multipart.MultiPartUploadDatabase
    public PartInfoDao partInfoDao() {
        PartInfoDao partInfoDao;
        if (this._partInfoDao != null) {
            return this._partInfoDao;
        }
        synchronized (this) {
            if (this._partInfoDao == null) {
                this._partInfoDao = new PartInfoDao_Impl(this);
            }
            partInfoDao = this._partInfoDao;
        }
        return partInfoDao;
    }
}
