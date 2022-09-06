package com.amazon.photos.uploader.internal;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.amazon.alexa.handsfree.protocols.sierracontentprovider.SierraContentProviderContract;
import com.amazon.deecomms.common.Constants;
import com.amazon.photos.uploader.dao.EventDao;
import com.amazon.photos.uploader.dao.EventDao_Impl;
import com.amazon.photos.uploader.internal.dao.LiveRequestDao;
import com.amazon.photos.uploader.internal.dao.LiveRequestDao_Impl;
import com.amazon.photos.uploader.internal.dao.SnapshotRequestDao;
import com.amazon.photos.uploader.internal.dao.SnapshotRequestDao_Impl;
import com.amazon.photos.uploader.internal.dao.UploadRequestDao;
import com.amazon.photos.uploader.internal.dao.UploadRequestDao_Impl;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.DefaultDeliveryClient;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
/* loaded from: classes13.dex */
public final class UploaderDatabase_Impl extends UploaderDatabase {
    private volatile EventDao _eventDao;
    private volatile LiveRequestDao _liveRequestDao;
    private volatile SnapshotRequestDao _snapshotRequestDao;
    private volatile UploadRequestDao _uploadRequestDao;

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `upload_request`");
            writableDatabase.execSQL("DELETE FROM `events`");
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
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "upload_request", DefaultDeliveryClient.EVENTS_DIRECTORY);
    }

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.mo201create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate(3) { // from class: com.amazon.photos.uploader.internal.UploaderDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `upload_request` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `file_path` TEXT NOT NULL, `upload_path` TEXT, `content_date` TEXT, `md5` TEXT, `visual_digest` TEXT, `suppress_duplication` INTEGER NOT NULL, `rename_on_name_conflict` INTEGER NOT NULL, `upload_category` TEXT NOT NULL, `state` TEXT NOT NULL, `queue` TEXT NOT NULL, `current_progress` INTEGER NOT NULL, `max_progress` INTEGER NOT NULL, `error_code` TEXT, `error_category` TEXT, `blocker` TEXT, `total_attempt_count` INTEGER NOT NULL, `attempt_count` INTEGER NOT NULL, `max_attempts_exceeded` INTEGER NOT NULL, `creation_time_millis` INTEGER NOT NULL, `file_size` INTEGER NOT NULL, `priority` INTEGER NOT NULL, `add_to_family_vault` INTEGER NOT NULL, `app_data` TEXT, `parent_id` TEXT, `content_uri` TEXT NOT NULL)");
                supportSQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_upload_request_file_path` ON `upload_request` (`file_path`)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `events` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `description` TEXT NOT NULL, `event_time_millis` INTEGER NOT NULL)");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '003bbfa0e68bf1d4226f89858ecbccac')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `upload_request`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `events`");
                if (((RoomDatabase) UploaderDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) UploaderDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) UploaderDatabase_Impl.this).mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (((RoomDatabase) UploaderDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) UploaderDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) UploaderDatabase_Impl.this).mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                ((RoomDatabase) UploaderDatabase_Impl.this).mDatabase = supportSQLiteDatabase;
                UploaderDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                if (((RoomDatabase) UploaderDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) UploaderDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) UploaderDatabase_Impl.this).mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
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
                HashMap hashMap = new HashMap(26);
                hashMap.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                hashMap.put("file_path", new TableInfo.Column("file_path", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1));
                hashMap.put("upload_path", new TableInfo.Column("upload_path", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap.put("content_date", new TableInfo.Column("content_date", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap.put(SierraContentProviderContract.MD5_VALUE, new TableInfo.Column(SierraContentProviderContract.MD5_VALUE, Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap.put("visual_digest", new TableInfo.Column("visual_digest", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap.put("suppress_duplication", new TableInfo.Column("suppress_duplication", "INTEGER", true, 0, null, 1));
                hashMap.put("rename_on_name_conflict", new TableInfo.Column("rename_on_name_conflict", "INTEGER", true, 0, null, 1));
                hashMap.put("upload_category", new TableInfo.Column("upload_category", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1));
                hashMap.put("state", new TableInfo.Column("state", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1));
                hashMap.put("queue", new TableInfo.Column("queue", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1));
                hashMap.put("current_progress", new TableInfo.Column("current_progress", "INTEGER", true, 0, null, 1));
                hashMap.put("max_progress", new TableInfo.Column("max_progress", "INTEGER", true, 0, null, 1));
                hashMap.put("error_code", new TableInfo.Column("error_code", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap.put("error_category", new TableInfo.Column("error_category", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap.put("blocker", new TableInfo.Column("blocker", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap.put("total_attempt_count", new TableInfo.Column("total_attempt_count", "INTEGER", true, 0, null, 1));
                hashMap.put("attempt_count", new TableInfo.Column("attempt_count", "INTEGER", true, 0, null, 1));
                hashMap.put("max_attempts_exceeded", new TableInfo.Column("max_attempts_exceeded", "INTEGER", true, 0, null, 1));
                hashMap.put("creation_time_millis", new TableInfo.Column("creation_time_millis", "INTEGER", true, 0, null, 1));
                hashMap.put("file_size", new TableInfo.Column("file_size", "INTEGER", true, 0, null, 1));
                hashMap.put("priority", new TableInfo.Column("priority", "INTEGER", true, 0, null, 1));
                hashMap.put("add_to_family_vault", new TableInfo.Column("add_to_family_vault", "INTEGER", true, 0, null, 1));
                hashMap.put("app_data", new TableInfo.Column("app_data", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap.put("parent_id", new TableInfo.Column("parent_id", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                HashSet outline135 = GeneratedOutlineSupport1.outline135(hashMap, "content_uri", new TableInfo.Column("content_uri", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1), 0);
                HashSet hashSet = new HashSet(1);
                hashSet.add(new TableInfo.Index("index_upload_request_file_path", true, Arrays.asList("file_path")));
                TableInfo tableInfo = new TableInfo("upload_request", hashMap, outline135, hashSet);
                TableInfo read = TableInfo.read(supportSQLiteDatabase, "upload_request");
                if (!tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(false, GeneratedOutlineSupport1.outline60("upload_request(com.amazon.photos.uploader.UploadRequest).\n Expected:\n", tableInfo, "\n Found:\n", read));
                }
                HashMap hashMap2 = new HashMap(3);
                hashMap2.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                hashMap2.put("description", new TableInfo.Column("description", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1));
                TableInfo tableInfo2 = new TableInfo(DefaultDeliveryClient.EVENTS_DIRECTORY, hashMap2, GeneratedOutlineSupport1.outline135(hashMap2, "event_time_millis", new TableInfo.Column("event_time_millis", "INTEGER", true, 0, null, 1), 0), new HashSet(0));
                TableInfo read2 = TableInfo.read(supportSQLiteDatabase, DefaultDeliveryClient.EVENTS_DIRECTORY);
                if (!tableInfo2.equals(read2)) {
                    return new RoomOpenHelper.ValidationResult(false, GeneratedOutlineSupport1.outline60("events(com.amazon.photos.uploader.Event).\n Expected:\n", tableInfo2, "\n Found:\n", read2));
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "003bbfa0e68bf1d4226f89858ecbccac", "6debaf60866fd44326d8af75566b3cf7")).build());
    }

    @Override // com.amazon.photos.uploader.internal.UploaderDatabase
    public EventDao eventDao() {
        EventDao eventDao;
        if (this._eventDao != null) {
            return this._eventDao;
        }
        synchronized (this) {
            if (this._eventDao == null) {
                this._eventDao = new EventDao_Impl(this);
            }
            eventDao = this._eventDao;
        }
        return eventDao;
    }

    @Override // com.amazon.photos.uploader.internal.UploaderDatabase
    public LiveRequestDao liveRequestDao() {
        LiveRequestDao liveRequestDao;
        if (this._liveRequestDao != null) {
            return this._liveRequestDao;
        }
        synchronized (this) {
            if (this._liveRequestDao == null) {
                this._liveRequestDao = new LiveRequestDao_Impl(this);
            }
            liveRequestDao = this._liveRequestDao;
        }
        return liveRequestDao;
    }

    @Override // com.amazon.photos.uploader.internal.UploaderDatabase
    public UploadRequestDao requestDao() {
        UploadRequestDao uploadRequestDao;
        if (this._uploadRequestDao != null) {
            return this._uploadRequestDao;
        }
        synchronized (this) {
            if (this._uploadRequestDao == null) {
                this._uploadRequestDao = new UploadRequestDao_Impl(this);
            }
            uploadRequestDao = this._uploadRequestDao;
        }
        return uploadRequestDao;
    }

    @Override // com.amazon.photos.uploader.internal.UploaderDatabase
    public SnapshotRequestDao snapshotRequestDao() {
        SnapshotRequestDao snapshotRequestDao;
        if (this._snapshotRequestDao != null) {
            return this._snapshotRequestDao;
        }
        synchronized (this) {
            if (this._snapshotRequestDao == null) {
                this._snapshotRequestDao = new SnapshotRequestDao_Impl(this);
            }
            snapshotRequestDao = this._snapshotRequestDao;
        }
        return snapshotRequestDao;
    }
}
