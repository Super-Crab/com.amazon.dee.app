package com.amazon.alexa.fitness.sdk.database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.fitness.metrics.Metrics;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.google.android.gms.location.FusedLocationProviderClient;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public final class SampleDao_Impl implements SampleDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<EchoBudSampleEntity> __insertionAdapterOfEchoBudSampleEntity;
    private final EntityInsertionAdapter<LocationSampleEntity> __insertionAdapterOfLocationSampleEntity;
    private final EntityInsertionAdapter<MeasurementSampleEntity> __insertionAdapterOfMeasurementSampleEntity;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAllEchoBudSamples;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAllLocationSamples;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAllMeasurementSamples;

    public SampleDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfEchoBudSampleEntity = new EntityInsertionAdapter<EchoBudSampleEntity>(roomDatabase) { // from class: com.amazon.alexa.fitness.sdk.database.SampleDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `EchoBudSamplesTable` (`id`,`sessionId`,`sensorId`,`collectedAt`,`receivedAt`,`activity`,`steps`,`cadence`) VALUES (?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, EchoBudSampleEntity echoBudSampleEntity) {
                Long l = echoBudSampleEntity.id;
                if (l == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindLong(1, l.longValue());
                }
                String str = echoBudSampleEntity.sessionId;
                if (str == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, str);
                }
                String str2 = echoBudSampleEntity.sensorId;
                if (str2 == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, str2);
                }
                Long l2 = echoBudSampleEntity.collectedAt;
                if (l2 == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindLong(4, l2.longValue());
                }
                Long l3 = echoBudSampleEntity.receivedAt;
                if (l3 == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindLong(5, l3.longValue());
                }
                String str3 = echoBudSampleEntity.activity;
                if (str3 == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, str3);
                }
                Integer num = echoBudSampleEntity.steps;
                if (num == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindLong(7, num.intValue());
                }
                Integer num2 = echoBudSampleEntity.cadence;
                if (num2 == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindLong(8, num2.intValue());
                }
            }
        };
        this.__insertionAdapterOfMeasurementSampleEntity = new EntityInsertionAdapter<MeasurementSampleEntity>(roomDatabase) { // from class: com.amazon.alexa.fitness.sdk.database.SampleDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `MeasurementSamplesTable` (`id`,`sessionId`,`algorithmId`,`receivedAt`,`value`,`type`,`units`,`aggregation`) VALUES (?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, MeasurementSampleEntity measurementSampleEntity) {
                Long l = measurementSampleEntity.id;
                if (l == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindLong(1, l.longValue());
                }
                String str = measurementSampleEntity.sessionId;
                if (str == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, str);
                }
                String str2 = measurementSampleEntity.algorithmId;
                if (str2 == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, str2);
                }
                Long l2 = measurementSampleEntity.receivedAt;
                if (l2 == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindLong(4, l2.longValue());
                }
                String str3 = measurementSampleEntity.value;
                if (str3 == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, str3);
                }
                String str4 = measurementSampleEntity.type;
                if (str4 == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, str4);
                }
                String str5 = measurementSampleEntity.units;
                if (str5 == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, str5);
                }
                String str6 = measurementSampleEntity.aggregation;
                if (str6 == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, str6);
                }
            }
        };
        this.__insertionAdapterOfLocationSampleEntity = new EntityInsertionAdapter<LocationSampleEntity>(roomDatabase) { // from class: com.amazon.alexa.fitness.sdk.database.SampleDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `LocationSamplesTable` (`id`,`sessionId`,`sourceId`,`collectedAt`,`receivedAt`,`latitude`,`longitude`,`altitude`,`horizontalAccuracy`,`verticalAccuracy`,`heading`,`speed`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, LocationSampleEntity locationSampleEntity) {
                Long l = locationSampleEntity.id;
                if (l == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindLong(1, l.longValue());
                }
                String str = locationSampleEntity.sessionId;
                if (str == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, str);
                }
                String str2 = locationSampleEntity.sourceId;
                if (str2 == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, str2);
                }
                Long l2 = locationSampleEntity.collectedAt;
                if (l2 == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindLong(4, l2.longValue());
                }
                Long l3 = locationSampleEntity.receivedAt;
                if (l3 == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindLong(5, l3.longValue());
                }
                Double d = locationSampleEntity.latitude;
                if (d == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindDouble(6, d.doubleValue());
                }
                Double d2 = locationSampleEntity.longitude;
                if (d2 == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindDouble(7, d2.doubleValue());
                }
                Double d3 = locationSampleEntity.altitude;
                if (d3 == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindDouble(8, d3.doubleValue());
                }
                Float f = locationSampleEntity.horizontalAccuracy;
                if (f == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindDouble(9, f.floatValue());
                }
                Float f2 = locationSampleEntity.verticalAccuracy;
                if (f2 == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindDouble(10, f2.floatValue());
                }
                Float f3 = locationSampleEntity.heading;
                if (f3 == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindDouble(11, f3.floatValue());
                }
                Float f4 = locationSampleEntity.speed;
                if (f4 == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindDouble(12, f4.floatValue());
                }
            }
        };
        this.__preparedStmtOfDeleteAllEchoBudSamples = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.alexa.fitness.sdk.database.SampleDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM EchoBudSamplesTable where sessionId=?";
            }
        };
        this.__preparedStmtOfDeleteAllMeasurementSamples = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.alexa.fitness.sdk.database.SampleDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM MeasurementSamplesTable where sessionId=?";
            }
        };
        this.__preparedStmtOfDeleteAllLocationSamples = new SharedSQLiteStatement(roomDatabase) { // from class: com.amazon.alexa.fitness.sdk.database.SampleDao_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM LocationSamplesTable where sessionId=?";
            }
        };
    }

    @Override // com.amazon.alexa.fitness.sdk.database.SampleDao
    public void deleteAllEchoBudSamples(String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteAllEchoBudSamples.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAllEchoBudSamples.release(acquire);
        }
    }

    @Override // com.amazon.alexa.fitness.sdk.database.SampleDao
    public void deleteAllLocationSamples(String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteAllLocationSamples.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAllLocationSamples.release(acquire);
        }
    }

    @Override // com.amazon.alexa.fitness.sdk.database.SampleDao
    public void deleteAllMeasurementSamples(String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteAllMeasurementSamples.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAllMeasurementSamples.release(acquire);
        }
    }

    @Override // com.amazon.alexa.fitness.sdk.database.SampleDao
    public List<EchoBudSampleEntity> getAllEchoBudSamples(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM EchoBudSamplesTable where sessionId = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sensorId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "collectedAt");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "receivedAt");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, Metrics.STEPS);
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "cadence");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EchoBudSampleEntity echoBudSampleEntity = new EchoBudSampleEntity(query.getString(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.isNull(columnIndexOrThrow4) ? null : Long.valueOf(query.getLong(columnIndexOrThrow4)), query.isNull(columnIndexOrThrow5) ? null : Long.valueOf(query.getLong(columnIndexOrThrow5)), query.getString(columnIndexOrThrow6), query.isNull(columnIndexOrThrow7) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow7)), query.isNull(columnIndexOrThrow8) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow8)));
                if (query.isNull(columnIndexOrThrow)) {
                    echoBudSampleEntity.id = null;
                } else {
                    echoBudSampleEntity.id = Long.valueOf(query.getLong(columnIndexOrThrow));
                }
                arrayList.add(echoBudSampleEntity);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.alexa.fitness.sdk.database.SampleDao
    public List<LocationSampleEntity> getAllLocationSamples(String str) {
        int i;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM LocationSamplesTable where sessionId = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "sourceId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "collectedAt");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "receivedAt");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "altitude");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "horizontalAccuracy");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, FusedLocationProviderClient.KEY_VERTICAL_ACCURACY);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "heading");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "speed");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                LocationSampleEntity locationSampleEntity = new LocationSampleEntity(query.getString(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.isNull(columnIndexOrThrow4) ? null : Long.valueOf(query.getLong(columnIndexOrThrow4)), query.isNull(columnIndexOrThrow5) ? null : Long.valueOf(query.getLong(columnIndexOrThrow5)), query.isNull(columnIndexOrThrow6) ? null : Double.valueOf(query.getDouble(columnIndexOrThrow6)), query.isNull(columnIndexOrThrow7) ? null : Double.valueOf(query.getDouble(columnIndexOrThrow7)), query.isNull(columnIndexOrThrow8) ? null : Double.valueOf(query.getDouble(columnIndexOrThrow8)), query.isNull(columnIndexOrThrow9) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow9)), query.isNull(columnIndexOrThrow10) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow10)), query.isNull(columnIndexOrThrow11) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow11)), query.isNull(columnIndexOrThrow12) ? null : Float.valueOf(query.getFloat(columnIndexOrThrow12)));
                if (query.isNull(columnIndexOrThrow)) {
                    i = columnIndexOrThrow2;
                    locationSampleEntity.id = null;
                } else {
                    i = columnIndexOrThrow2;
                    locationSampleEntity.id = Long.valueOf(query.getLong(columnIndexOrThrow));
                }
                arrayList.add(locationSampleEntity);
                columnIndexOrThrow2 = i;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.alexa.fitness.sdk.database.SampleDao
    public List<MeasurementSampleEntity> getAllMeasurementSamples(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM MeasurementSamplesTable where sessionId = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "algorithmId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "receivedAt");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "value");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "type");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "units");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "aggregation");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                MeasurementSampleEntity measurementSampleEntity = new MeasurementSampleEntity(query.getString(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.isNull(columnIndexOrThrow4) ? null : Long.valueOf(query.getLong(columnIndexOrThrow4)), query.getString(columnIndexOrThrow5), query.getString(columnIndexOrThrow6), query.getString(columnIndexOrThrow7), query.getString(columnIndexOrThrow8));
                if (query.isNull(columnIndexOrThrow)) {
                    measurementSampleEntity.id = null;
                } else {
                    measurementSampleEntity.id = Long.valueOf(query.getLong(columnIndexOrThrow));
                }
                arrayList.add(measurementSampleEntity);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.amazon.alexa.fitness.sdk.database.SampleDao
    public void insertEchobudSample(List<EchoBudSampleEntity> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfEchoBudSampleEntity.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.alexa.fitness.sdk.database.SampleDao
    public void insertLocationSample(List<LocationSampleEntity> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfLocationSampleEntity.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.alexa.fitness.sdk.database.SampleDao
    public void insertMeasurementSample(List<MeasurementSampleEntity> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfMeasurementSampleEntity.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.alexa.fitness.sdk.database.SampleDao
    public List<MeasurementSampleEntity> getAllMeasurementSamples(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM MeasurementSamplesTable  where sessionId = ? AND type = ?", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str2 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str2);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "algorithmId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "receivedAt");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "value");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "type");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "units");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "aggregation");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                MeasurementSampleEntity measurementSampleEntity = new MeasurementSampleEntity(query.getString(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.isNull(columnIndexOrThrow4) ? null : Long.valueOf(query.getLong(columnIndexOrThrow4)), query.getString(columnIndexOrThrow5), query.getString(columnIndexOrThrow6), query.getString(columnIndexOrThrow7), query.getString(columnIndexOrThrow8));
                if (query.isNull(columnIndexOrThrow)) {
                    measurementSampleEntity.id = null;
                } else {
                    measurementSampleEntity.id = Long.valueOf(query.getLong(columnIndexOrThrow));
                }
                arrayList.add(measurementSampleEntity);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }
}
