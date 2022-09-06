package com.amazon.alexa.featureservice.database.roomdb;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.amazon.alexa.featureservice.constants.FeatureConstants;
import com.amazon.alexa.featureservice.dao.FeatureDao;
import com.amazon.alexa.featureservice.dao.FeatureDao_Impl;
import com.amazon.alexa.featureservice.database.dao.FeatureFlagDao;
import com.amazon.alexa.featureservice.database.dao.FeatureFlagDao_Impl;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.HashSet;
/* loaded from: classes7.dex */
public final class FeatureAppDatabase_Impl extends FeatureAppDatabase {
    private volatile FeatureDao _featureDao;
    private volatile FeatureFlagDao _featureFlagDao;

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `Feature`");
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
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "Feature");
    }

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.mo201create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate(1) { // from class: com.amazon.alexa.featureservice.database.roomdb.FeatureAppDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `Feature` (`featureName` TEXT NOT NULL, `treatment` TEXT, `should_record_trigger` INTEGER NOT NULL, `allocation_id` TEXT, PRIMARY KEY(`featureName`))");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '9560419d4e1dacf060420a90e1791747')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `Feature`");
                if (((RoomDatabase) FeatureAppDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) FeatureAppDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) FeatureAppDatabase_Impl.this).mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (((RoomDatabase) FeatureAppDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) FeatureAppDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) FeatureAppDatabase_Impl.this).mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                ((RoomDatabase) FeatureAppDatabase_Impl.this).mDatabase = supportSQLiteDatabase;
                FeatureAppDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                if (((RoomDatabase) FeatureAppDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) FeatureAppDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) FeatureAppDatabase_Impl.this).mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
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
                HashMap hashMap = new HashMap(4);
                hashMap.put("featureName", new TableInfo.Column("featureName", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 1, null, 1));
                hashMap.put(FeatureConstants.Network.TREATMENT, new TableInfo.Column(FeatureConstants.Network.TREATMENT, Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1));
                hashMap.put("should_record_trigger", new TableInfo.Column("should_record_trigger", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo = new TableInfo("Feature", hashMap, GeneratedOutlineSupport1.outline135(hashMap, "allocation_id", new TableInfo.Column("allocation_id", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, false, 0, null, 1), 0), new HashSet(0));
                TableInfo read = TableInfo.read(supportSQLiteDatabase, "Feature");
                if (!tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(false, GeneratedOutlineSupport1.outline60("Feature(com.amazon.alexa.featureservice.database.entity.Feature).\n Expected:\n", tableInfo, "\n Found:\n", read));
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "9560419d4e1dacf060420a90e1791747", "c6378c8182fb636b449a66a6f04f295d")).build());
    }

    @Override // com.amazon.alexa.featureservice.database.roomdb.FeatureAppDatabase
    public FeatureFlagDao featureDao() {
        FeatureFlagDao featureFlagDao;
        if (this._featureFlagDao != null) {
            return this._featureFlagDao;
        }
        synchronized (this) {
            if (this._featureFlagDao == null) {
                this._featureFlagDao = new FeatureFlagDao_Impl(this);
            }
            featureFlagDao = this._featureFlagDao;
        }
        return featureFlagDao;
    }

    @Override // com.amazon.alexa.featureservice.database.roomdb.FeatureAppDatabase
    public FeatureDao userDao() {
        FeatureDao featureDao;
        if (this._featureDao != null) {
            return this._featureDao;
        }
        synchronized (this) {
            if (this._featureDao == null) {
                this._featureDao = new FeatureDao_Impl(this);
            }
            featureDao = this._featureDao;
        }
        return featureDao;
    }
}
