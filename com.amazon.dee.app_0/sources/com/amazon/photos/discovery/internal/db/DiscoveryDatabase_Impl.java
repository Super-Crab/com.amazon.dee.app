package com.amazon.photos.discovery.internal.db;

import android.os.Build;
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
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.deecomms.common.Constants;
import com.amazon.photos.discovery.dao.EditDao;
import com.amazon.photos.discovery.dao.EditDao_Impl;
import com.amazon.photos.discovery.dao.LocalFolderDao;
import com.amazon.photos.discovery.dao.LocalFolderDao_Impl;
import com.amazon.photos.discovery.dao.LocalItemDao;
import com.amazon.photos.discovery.dao.LocalItemDao_Impl;
import com.amazon.photos.discovery.dao.UnifiedItemDao;
import com.amazon.photos.discovery.dao.UnifiedItemDao_Impl;
import com.amazon.photos.discovery.internal.dao.WorkerDao;
import com.amazon.photos.discovery.internal.dao.WorkerDao_Impl;
import com.amazon.photos.discovery.internal.worker.DedupeWorkerKt;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
/* loaded from: classes13.dex */
public final class DiscoveryDatabase_Impl extends DiscoveryDatabase {
    private volatile EditDao _editDao;
    private volatile LocalFolderDao _localFolderDao;
    private volatile LocalItemDao _localItemDao;
    private volatile UnifiedItemDao _unifiedItemDao;
    private volatile WorkerDao _workerDao;

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        int i = Build.VERSION.SDK_INT;
        if (1 == 0) {
            try {
                writableDatabase.execSQL("PRAGMA foreign_keys = FALSE");
            } finally {
                super.endTransaction();
                if (1 == 0) {
                    writableDatabase.execSQL("PRAGMA foreign_keys = TRUE");
                }
                if (!GeneratedOutlineSupport1.outline188(writableDatabase, "PRAGMA wal_checkpoint(FULL)")) {
                    writableDatabase.execSQL("VACUUM");
                }
            }
        }
        super.beginTransaction();
        if (1 != 0) {
            writableDatabase.execSQL("PRAGMA defer_foreign_keys = TRUE");
        }
        writableDatabase.execSQL("DELETE FROM `local_item`");
        writableDatabase.execSQL("DELETE FROM `cloud_item`");
        writableDatabase.execSQL("DELETE FROM `unified_item`");
        writableDatabase.execSQL("DELETE FROM `local_folder`");
        super.setTransactionSuccessful();
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "local_item", "cloud_item", "unified_item", "local_folder");
    }

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.mo201create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate(2) { // from class: com.amazon.photos.discovery.internal.db.DiscoveryDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `local_item` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `unified_id` INTEGER NOT NULL, `type` INTEGER NOT NULL, `file_path` TEXT NOT NULL, `duration` INTEGER, `width` INTEGER, `height` INTEGER, `size` INTEGER, `date_added` INTEGER NOT NULL, `date_taken` INTEGER NOT NULL, `date_modified` INTEGER NOT NULL, `start_processing` INTEGER NOT NULL, `end_processing` INTEGER NOT NULL, `md5` TEXT, `visual_digest` TEXT, `parent_id` INTEGER NOT NULL, FOREIGN KEY(`parent_id`) REFERENCES `local_folder`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
                supportSQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_local_item_id` ON `local_item` (`id`)");
                supportSQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_local_item_file_path` ON `local_item` (`file_path`)");
                supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_local_item_parent_id` ON `local_item` (`parent_id`)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `cloud_item` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `unified_id` INTEGER NOT NULL, `node_id` TEXT NOT NULL, `date_uploaded` INTEGER NOT NULL, `date_taken` INTEGER NOT NULL, `md5` TEXT, `visual_digest` TEXT)");
                supportSQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_cloud_item_id` ON `cloud_item` (`id`)");
                supportSQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_cloud_item_node_id` ON `cloud_item` (`node_id`)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `unified_item` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `type` INTEGER, `date_taken` INTEGER NOT NULL, `date_uploaded` INTEGER NOT NULL, `dedupe_stage` INTEGER NOT NULL, `synched` INTEGER NOT NULL)");
                supportSQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_unified_item_id` ON `unified_item` (`id`)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `local_folder` (`id` INTEGER NOT NULL, `name` TEXT NOT NULL, `path` TEXT NOT NULL, `folder_type` TEXT NOT NULL, PRIMARY KEY(`id`))");
                supportSQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_local_folder_path` ON `local_folder` (`path`)");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'ee3b60cb430c3fd24f9c85e216d12605')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `local_item`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `cloud_item`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `unified_item`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `local_folder`");
                if (((RoomDatabase) DiscoveryDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) DiscoveryDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) DiscoveryDatabase_Impl.this).mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (((RoomDatabase) DiscoveryDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) DiscoveryDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) DiscoveryDatabase_Impl.this).mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                ((RoomDatabase) DiscoveryDatabase_Impl.this).mDatabase = supportSQLiteDatabase;
                supportSQLiteDatabase.execSQL("PRAGMA foreign_keys = ON");
                DiscoveryDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                if (((RoomDatabase) DiscoveryDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) DiscoveryDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) DiscoveryDatabase_Impl.this).mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
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
                HashMap hashMap = new HashMap(16);
                hashMap.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                hashMap.put("unified_id", new TableInfo.Column("unified_id", "INTEGER", true, 0, null, 1));
                hashMap.put("type", new TableInfo.Column("type", "INTEGER", true, 0, null, 1));
                hashMap.put("file_path", new TableInfo.Column("file_path", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1));
                hashMap.put("duration", new TableInfo.Column("duration", "INTEGER", false, 0, null, 1));
                hashMap.put("width", new TableInfo.Column("width", "INTEGER", false, 0, null, 1));
                hashMap.put("height", new TableInfo.Column("height", "INTEGER", false, 0, null, 1));
                hashMap.put("size", new TableInfo.Column("size", "INTEGER", false, 0, null, 1));
                hashMap.put("date_added", new TableInfo.Column("date_added", "INTEGER", true, 0, null, 1));
                hashMap.put("date_taken", new TableInfo.Column("date_taken", "INTEGER", true, 0, null, 1));
                hashMap.put("date_modified", new TableInfo.Column("date_modified", "INTEGER", true, 0, null, 1));
                hashMap.put("start_processing", new TableInfo.Column("start_processing", "INTEGER", true, 0, null, 1));
                hashMap.put("end_processing", new TableInfo.Column("end_processing", "INTEGER", true, 0, null, 1));
                hashMap.put(SierraContentProviderContract.MD5_VALUE, new TableInfo.Column(SierraContentProviderContract.MD5_VALUE, Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap.put("visual_digest", new TableInfo.Column("visual_digest", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                HashSet outline135 = GeneratedOutlineSupport1.outline135(hashMap, "parent_id", new TableInfo.Column("parent_id", "INTEGER", true, 0, null, 1), 1);
                outline135.add(new TableInfo.ForeignKey("local_folder", "CASCADE", "NO ACTION", Arrays.asList("parent_id"), Arrays.asList("id")));
                HashSet hashSet = new HashSet(3);
                hashSet.add(new TableInfo.Index("index_local_item_id", true, Arrays.asList("id")));
                hashSet.add(new TableInfo.Index("index_local_item_file_path", true, Arrays.asList("file_path")));
                hashSet.add(new TableInfo.Index("index_local_item_parent_id", false, Arrays.asList("parent_id")));
                TableInfo tableInfo = new TableInfo("local_item", hashMap, outline135, hashSet);
                TableInfo read = TableInfo.read(supportSQLiteDatabase, "local_item");
                if (!tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(false, GeneratedOutlineSupport1.outline60("local_item(com.amazon.photos.discovery.internal.model.MutableLocalItem).\n Expected:\n", tableInfo, "\n Found:\n", read));
                }
                HashMap hashMap2 = new HashMap(7);
                hashMap2.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                hashMap2.put("unified_id", new TableInfo.Column("unified_id", "INTEGER", true, 0, null, 1));
                hashMap2.put("node_id", new TableInfo.Column("node_id", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1));
                hashMap2.put("date_uploaded", new TableInfo.Column("date_uploaded", "INTEGER", true, 0, null, 1));
                hashMap2.put("date_taken", new TableInfo.Column("date_taken", "INTEGER", true, 0, null, 1));
                hashMap2.put(SierraContentProviderContract.MD5_VALUE, new TableInfo.Column(SierraContentProviderContract.MD5_VALUE, Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                HashSet outline1352 = GeneratedOutlineSupport1.outline135(hashMap2, "visual_digest", new TableInfo.Column("visual_digest", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1), 0);
                HashSet hashSet2 = new HashSet(2);
                hashSet2.add(new TableInfo.Index("index_cloud_item_id", true, Arrays.asList("id")));
                hashSet2.add(new TableInfo.Index("index_cloud_item_node_id", true, Arrays.asList("node_id")));
                TableInfo tableInfo2 = new TableInfo("cloud_item", hashMap2, outline1352, hashSet2);
                TableInfo read2 = TableInfo.read(supportSQLiteDatabase, "cloud_item");
                if (!tableInfo2.equals(read2)) {
                    return new RoomOpenHelper.ValidationResult(false, GeneratedOutlineSupport1.outline60("cloud_item(com.amazon.photos.discovery.internal.model.MutableCloudItem).\n Expected:\n", tableInfo2, "\n Found:\n", read2));
                }
                HashMap hashMap3 = new HashMap(6);
                hashMap3.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                hashMap3.put("type", new TableInfo.Column("type", "INTEGER", false, 0, null, 1));
                hashMap3.put("date_taken", new TableInfo.Column("date_taken", "INTEGER", true, 0, null, 1));
                hashMap3.put("date_uploaded", new TableInfo.Column("date_uploaded", "INTEGER", true, 0, null, 1));
                hashMap3.put(DedupeWorkerKt.PARAM_DEDUPE_STAGE_ID, new TableInfo.Column(DedupeWorkerKt.PARAM_DEDUPE_STAGE_ID, "INTEGER", true, 0, null, 1));
                HashSet outline1353 = GeneratedOutlineSupport1.outline135(hashMap3, "synched", new TableInfo.Column("synched", "INTEGER", true, 0, null, 1), 0);
                HashSet hashSet3 = new HashSet(1);
                hashSet3.add(new TableInfo.Index("index_unified_item_id", true, Arrays.asList("id")));
                TableInfo tableInfo3 = new TableInfo("unified_item", hashMap3, outline1353, hashSet3);
                TableInfo read3 = TableInfo.read(supportSQLiteDatabase, "unified_item");
                if (!tableInfo3.equals(read3)) {
                    return new RoomOpenHelper.ValidationResult(false, GeneratedOutlineSupport1.outline60("unified_item(com.amazon.photos.discovery.internal.model.MutableUnifiedEntry).\n Expected:\n", tableInfo3, "\n Found:\n", read3));
                }
                HashMap hashMap4 = new HashMap(4);
                hashMap4.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                hashMap4.put("name", new TableInfo.Column("name", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1));
                hashMap4.put(RouteParameter.PATH, new TableInfo.Column(RouteParameter.PATH, Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1));
                HashSet outline1354 = GeneratedOutlineSupport1.outline135(hashMap4, "folder_type", new TableInfo.Column("folder_type", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1), 0);
                HashSet hashSet4 = new HashSet(1);
                hashSet4.add(new TableInfo.Index("index_local_folder_path", true, Arrays.asList(RouteParameter.PATH)));
                TableInfo tableInfo4 = new TableInfo("local_folder", hashMap4, outline1354, hashSet4);
                TableInfo read4 = TableInfo.read(supportSQLiteDatabase, "local_folder");
                if (!tableInfo4.equals(read4)) {
                    return new RoomOpenHelper.ValidationResult(false, GeneratedOutlineSupport1.outline60("local_folder(com.amazon.photos.discovery.internal.model.MutableLocalFolder).\n Expected:\n", tableInfo4, "\n Found:\n", read4));
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "ee3b60cb430c3fd24f9c85e216d12605", "27db7b412c96e709376ac6c42aec374b")).build());
    }

    @Override // com.amazon.photos.discovery.internal.db.DiscoveryDatabase
    public UnifiedItemDao discoveryItemDao() {
        UnifiedItemDao unifiedItemDao;
        if (this._unifiedItemDao != null) {
            return this._unifiedItemDao;
        }
        synchronized (this) {
            if (this._unifiedItemDao == null) {
                this._unifiedItemDao = new UnifiedItemDao_Impl(this);
            }
            unifiedItemDao = this._unifiedItemDao;
        }
        return unifiedItemDao;
    }

    @Override // com.amazon.photos.discovery.internal.db.DiscoveryDatabase
    public EditDao editDao() {
        EditDao editDao;
        if (this._editDao != null) {
            return this._editDao;
        }
        synchronized (this) {
            if (this._editDao == null) {
                this._editDao = new EditDao_Impl(this);
            }
            editDao = this._editDao;
        }
        return editDao;
    }

    @Override // com.amazon.photos.discovery.internal.db.DiscoveryDatabase
    public LocalFolderDao localFolderDao() {
        LocalFolderDao localFolderDao;
        if (this._localFolderDao != null) {
            return this._localFolderDao;
        }
        synchronized (this) {
            if (this._localFolderDao == null) {
                this._localFolderDao = new LocalFolderDao_Impl(this);
            }
            localFolderDao = this._localFolderDao;
        }
        return localFolderDao;
    }

    @Override // com.amazon.photos.discovery.internal.db.DiscoveryDatabase
    public LocalItemDao localItemDao() {
        LocalItemDao localItemDao;
        if (this._localItemDao != null) {
            return this._localItemDao;
        }
        synchronized (this) {
            if (this._localItemDao == null) {
                this._localItemDao = new LocalItemDao_Impl(this);
            }
            localItemDao = this._localItemDao;
        }
        return localItemDao;
    }

    @Override // com.amazon.photos.discovery.internal.db.DiscoveryDatabase
    public WorkerDao workerDao() {
        WorkerDao workerDao;
        if (this._workerDao != null) {
            return this._workerDao;
        }
        synchronized (this) {
            if (this._workerDao == null) {
                this._workerDao = new WorkerDao_Impl(this);
            }
            workerDao = this._workerDao;
        }
        return workerDao;
    }
}
