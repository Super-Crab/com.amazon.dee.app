package com.amazon.alexa.fitness.sdk.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.fitness.metrics.Metrics;
import com.amazon.alexa.fitness.util.ConstantsKt;
import com.amazon.deecomms.common.Constants;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.location.FusedLocationProviderClient;
import java.util.HashMap;
import java.util.HashSet;
/* loaded from: classes8.dex */
public final class FitnessDatabase_Impl extends FitnessDatabase {
    private volatile SampleDao _sampleDao;

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `EchoBudSamplesTable`");
            writableDatabase.execSQL("DELETE FROM `MeasurementSamplesTable`");
            writableDatabase.execSQL("DELETE FROM `LocationSamplesTable`");
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
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), ConstantsKt.ECHOBUD_SAMPLES_TABLE_NAME, ConstantsKt.MEASUREMENT_SAMPLES_TABLE_NAME, ConstantsKt.LOCATION_SAMPLES_TABLE_NAME);
    }

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.mo201create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate(2) { // from class: com.amazon.alexa.fitness.sdk.database.FitnessDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `EchoBudSamplesTable` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `sessionId` TEXT NOT NULL, `sensorId` TEXT NOT NULL, `collectedAt` INTEGER NOT NULL, `receivedAt` INTEGER NOT NULL, `activity` TEXT NOT NULL, `steps` INTEGER NOT NULL, `cadence` INTEGER NOT NULL)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `MeasurementSamplesTable` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `sessionId` TEXT NOT NULL, `algorithmId` TEXT NOT NULL, `receivedAt` INTEGER NOT NULL, `value` TEXT NOT NULL, `type` TEXT NOT NULL, `units` TEXT NOT NULL, `aggregation` TEXT NOT NULL)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `LocationSamplesTable` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `sessionId` TEXT NOT NULL, `sourceId` TEXT NOT NULL, `collectedAt` INTEGER NOT NULL, `receivedAt` INTEGER NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `altitude` REAL NOT NULL, `horizontalAccuracy` REAL NOT NULL, `verticalAccuracy` REAL NOT NULL, `heading` REAL NOT NULL, `speed` REAL NOT NULL)");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'b31714aec5c247161203dd1f7eb06468')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `EchoBudSamplesTable`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `MeasurementSamplesTable`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `LocationSamplesTable`");
                if (((RoomDatabase) FitnessDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) FitnessDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) FitnessDatabase_Impl.this).mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (((RoomDatabase) FitnessDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) FitnessDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) FitnessDatabase_Impl.this).mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                ((RoomDatabase) FitnessDatabase_Impl.this).mDatabase = supportSQLiteDatabase;
                FitnessDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                if (((RoomDatabase) FitnessDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) FitnessDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) FitnessDatabase_Impl.this).mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
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
                HashMap hashMap = new HashMap(8);
                hashMap.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, 1));
                hashMap.put(AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, new TableInfo.Column(AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1));
                hashMap.put("sensorId", new TableInfo.Column("sensorId", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1));
                hashMap.put("collectedAt", new TableInfo.Column("collectedAt", "INTEGER", true, 0, null, 1));
                hashMap.put("receivedAt", new TableInfo.Column("receivedAt", "INTEGER", true, 0, null, 1));
                hashMap.put(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, new TableInfo.Column(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1));
                hashMap.put(Metrics.STEPS, new TableInfo.Column(Metrics.STEPS, "INTEGER", true, 0, null, 1));
                TableInfo tableInfo = new TableInfo(ConstantsKt.ECHOBUD_SAMPLES_TABLE_NAME, hashMap, GeneratedOutlineSupport1.outline135(hashMap, "cadence", new TableInfo.Column("cadence", "INTEGER", true, 0, null, 1), 0), new HashSet(0));
                TableInfo read = TableInfo.read(supportSQLiteDatabase, ConstantsKt.ECHOBUD_SAMPLES_TABLE_NAME);
                if (!tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(false, GeneratedOutlineSupport1.outline60("EchoBudSamplesTable(com.amazon.alexa.fitness.sdk.database.EchoBudSampleEntity).\n Expected:\n", tableInfo, "\n Found:\n", read));
                }
                HashMap hashMap2 = new HashMap(8);
                hashMap2.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                hashMap2.put(AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, new TableInfo.Column(AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1));
                hashMap2.put("algorithmId", new TableInfo.Column("algorithmId", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1));
                hashMap2.put("receivedAt", new TableInfo.Column("receivedAt", "INTEGER", true, 0, null, 1));
                hashMap2.put("value", new TableInfo.Column("value", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1));
                hashMap2.put("type", new TableInfo.Column("type", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1));
                hashMap2.put("units", new TableInfo.Column("units", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1));
                TableInfo tableInfo2 = new TableInfo(ConstantsKt.MEASUREMENT_SAMPLES_TABLE_NAME, hashMap2, GeneratedOutlineSupport1.outline135(hashMap2, "aggregation", new TableInfo.Column("aggregation", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1), 0), new HashSet(0));
                TableInfo read2 = TableInfo.read(supportSQLiteDatabase, ConstantsKt.MEASUREMENT_SAMPLES_TABLE_NAME);
                if (!tableInfo2.equals(read2)) {
                    return new RoomOpenHelper.ValidationResult(false, GeneratedOutlineSupport1.outline60("MeasurementSamplesTable(com.amazon.alexa.fitness.sdk.database.MeasurementSampleEntity).\n Expected:\n", tableInfo2, "\n Found:\n", read2));
                }
                HashMap hashMap3 = new HashMap(12);
                hashMap3.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, 1));
                hashMap3.put(AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, new TableInfo.Column(AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1));
                hashMap3.put("sourceId", new TableInfo.Column("sourceId", Constants.Calling.MEDIA_STREAM_TYPE_TEXT, true, 0, null, 1));
                hashMap3.put("collectedAt", new TableInfo.Column("collectedAt", "INTEGER", true, 0, null, 1));
                hashMap3.put("receivedAt", new TableInfo.Column("receivedAt", "INTEGER", true, 0, null, 1));
                hashMap3.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, 1));
                hashMap3.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, 1));
                hashMap3.put("altitude", new TableInfo.Column("altitude", "REAL", true, 0, null, 1));
                hashMap3.put("horizontalAccuracy", new TableInfo.Column("horizontalAccuracy", "REAL", true, 0, null, 1));
                hashMap3.put(FusedLocationProviderClient.KEY_VERTICAL_ACCURACY, new TableInfo.Column(FusedLocationProviderClient.KEY_VERTICAL_ACCURACY, "REAL", true, 0, null, 1));
                hashMap3.put("heading", new TableInfo.Column("heading", "REAL", true, 0, null, 1));
                TableInfo tableInfo3 = new TableInfo(ConstantsKt.LOCATION_SAMPLES_TABLE_NAME, hashMap3, GeneratedOutlineSupport1.outline135(hashMap3, "speed", new TableInfo.Column("speed", "REAL", true, 0, null, 1), 0), new HashSet(0));
                TableInfo read3 = TableInfo.read(supportSQLiteDatabase, ConstantsKt.LOCATION_SAMPLES_TABLE_NAME);
                if (!tableInfo3.equals(read3)) {
                    return new RoomOpenHelper.ValidationResult(false, GeneratedOutlineSupport1.outline60("LocationSamplesTable(com.amazon.alexa.fitness.sdk.database.LocationSampleEntity).\n Expected:\n", tableInfo3, "\n Found:\n", read3));
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "b31714aec5c247161203dd1f7eb06468", "ca08a181c503fd99ec5e7121a3dd6a72")).build());
    }

    @Override // com.amazon.alexa.fitness.sdk.database.FitnessDatabase
    public SampleDao sampleDao() {
        SampleDao sampleDao;
        if (this._sampleDao != null) {
            return this._sampleDao;
        }
        synchronized (this) {
            if (this._sampleDao == null) {
                this._sampleDao = new SampleDao_Impl(this);
            }
            sampleDao = this._sampleDao;
        }
        return sampleDao;
    }
}
