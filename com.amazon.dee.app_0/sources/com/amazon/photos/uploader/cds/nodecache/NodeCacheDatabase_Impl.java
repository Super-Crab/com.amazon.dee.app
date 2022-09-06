package com.amazon.photos.uploader.cds.nodecache;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.HashSet;
/* loaded from: classes13.dex */
public final class NodeCacheDatabase_Impl extends NodeCacheDatabase {
    private volatile ParentIdDao _parentIdDao;

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `parent_id`");
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
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "parent_id");
    }

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.mo201create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate(1) { // from class: com.amazon.photos.uploader.cds.nodecache.NodeCacheDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `parent_id` (`path` TEXT NOT NULL, `node_id` TEXT NOT NULL, PRIMARY KEY(`path`))");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'd77fac74c8da1dfb270e4757228854d8')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `parent_id`");
                if (((RoomDatabase) NodeCacheDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) NodeCacheDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) NodeCacheDatabase_Impl.this).mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (((RoomDatabase) NodeCacheDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) NodeCacheDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) NodeCacheDatabase_Impl.this).mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                ((RoomDatabase) NodeCacheDatabase_Impl.this).mDatabase = supportSQLiteDatabase;
                NodeCacheDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                if (((RoomDatabase) NodeCacheDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) NodeCacheDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) NodeCacheDatabase_Impl.this).mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
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
                HashMap hashMap = new HashMap(2);
                hashMap.put(RouteParameter.PATH, new TableInfo.Column(RouteParameter.PATH, Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 1, null, 1));
                TableInfo tableInfo = new TableInfo("parent_id", hashMap, GeneratedOutlineSupport1.outline135(hashMap, "node_id", new TableInfo.Column("node_id", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1), 0), new HashSet(0));
                TableInfo read = TableInfo.read(supportSQLiteDatabase, "parent_id");
                if (!tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(false, GeneratedOutlineSupport1.outline60("parent_id(com.amazon.photos.uploader.cds.nodecache.ParentId).\n Expected:\n", tableInfo, "\n Found:\n", read));
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "d77fac74c8da1dfb270e4757228854d8", "c9f431c28025ae67961115aa50d13b0b")).build());
    }

    @Override // com.amazon.photos.uploader.cds.nodecache.NodeCacheDatabase
    public ParentIdDao parentIdDao() {
        ParentIdDao parentIdDao;
        if (this._parentIdDao != null) {
            return this._parentIdDao;
        }
        synchronized (this) {
            if (this._parentIdDao == null) {
                this._parentIdDao = new ParentIdDao_Impl(this);
            }
            parentIdDao = this._parentIdDao;
        }
        return parentIdDao;
    }
}
