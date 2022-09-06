package com.amazon.alexa.assetManagementService.util;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.amazon.alexa.assetManagementService.dao.AssetMappingDao;
import com.amazon.alexa.assetManagementService.dao.AssetMappingDao_Impl;
import com.amazon.alexa.assetManagementService.model.constants.LocalDBQuery;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.HashSet;
/* loaded from: classes6.dex */
public final class AssetMappingDatabase_Impl extends AssetMappingDatabase {
    private volatile AssetMappingDao _assetMappingDao;

    @Override // com.amazon.alexa.assetManagementService.util.AssetMappingDatabase
    public AssetMappingDao assetMappingDao() {
        AssetMappingDao assetMappingDao;
        if (this._assetMappingDao != null) {
            return this._assetMappingDao;
        }
        synchronized (this) {
            if (this._assetMappingDao == null) {
                this._assetMappingDao = new AssetMappingDao_Impl(this);
            }
            assetMappingDao = this._assetMappingDao;
        }
        return assetMappingDao;
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `AssetMapping`");
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
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "AssetMapping");
    }

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.mo201create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate(2) { // from class: com.amazon.alexa.assetManagementService.util.AssetMappingDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `AssetMapping` (`bundleId` TEXT NOT NULL, `resourceId` TEXT NOT NULL, `assetURL` TEXT NOT NULL, PRIMARY KEY(`resourceId`, `bundleId`))");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'a7bc87d0b0eb6bfadb056b37cab8cb41')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `AssetMapping`");
                if (((RoomDatabase) AssetMappingDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) AssetMappingDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) AssetMappingDatabase_Impl.this).mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (((RoomDatabase) AssetMappingDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) AssetMappingDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) AssetMappingDatabase_Impl.this).mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                ((RoomDatabase) AssetMappingDatabase_Impl.this).mDatabase = supportSQLiteDatabase;
                AssetMappingDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                if (((RoomDatabase) AssetMappingDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) AssetMappingDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) AssetMappingDatabase_Impl.this).mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
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
                HashMap hashMap = new HashMap(3);
                hashMap.put("bundleId", new TableInfo.Column("bundleId", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 2, null, 1));
                hashMap.put("resourceId", new TableInfo.Column("resourceId", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 1, null, 1));
                TableInfo tableInfo = new TableInfo("AssetMapping", hashMap, GeneratedOutlineSupport1.outline135(hashMap, LocalDBQuery.ASSET_URL_COLUMN, new TableInfo.Column(LocalDBQuery.ASSET_URL_COLUMN, Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1), 0), new HashSet(0));
                TableInfo read = TableInfo.read(supportSQLiteDatabase, "AssetMapping");
                if (!tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(false, GeneratedOutlineSupport1.outline60("AssetMapping(com.amazon.alexa.assetManagementService.entity.AssetMapping).\n Expected:\n", tableInfo, "\n Found:\n", read));
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "a7bc87d0b0eb6bfadb056b37cab8cb41", "1ceaecf1d3586cfd4fead108cdd6d568")).build());
    }
}
