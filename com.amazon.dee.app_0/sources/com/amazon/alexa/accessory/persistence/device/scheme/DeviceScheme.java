package com.amazon.alexa.accessory.persistence.device.scheme;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.internal.util.DeviceDatabaseUtils;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.internal.util.RedactionUtil;
import com.amazon.alexa.accessory.persistence.DatabaseScheme;
import com.amazon.alexa.accessory.persistence.device.v2.DeviceGroupContract;
import com.amazon.alexa.accessory.repositories.device.v2.Device;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Action;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public final class DeviceScheme implements DatabaseScheme {
    private static final long TIMEOUT_IN_SECONDS = 60;
    private final int version;

    public DeviceScheme(int i) {
        this.version = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: createDeviceGroupsIndex */
    public void lambda$createIndexCompletable$1$DeviceScheme(SQLiteDatabase sQLiteDatabase, String str, int i) {
        if (i != 1) {
            if (i == 2) {
                StringBuilder outline116 = GeneratedOutlineSupport1.outline116("CREATE UNIQUE INDEX IF NOT EXISTS index_identifier_transport ON ", str, " (", "identifier", ",");
                outline116.append("transport");
                outline116.append(")");
                String sb = outline116.toString();
                Logger.v(sb);
                sQLiteDatabase.execSQL(sb);
                Logger.d("Unique index created for %s V%d", str, Integer.valueOf(i));
            } else if (i == 3) {
                StringBuilder outline1162 = GeneratedOutlineSupport1.outline116("CREATE UNIQUE INDEX IF NOT EXISTS index_identifier_transport ON ", str, " (", "identifier", ",");
                outline1162.append("transport");
                outline1162.append(")");
                String sb2 = outline1162.toString();
                Logger.v(sb2);
                sQLiteDatabase.execSQL(sb2);
                Logger.d("Unique index created for %s V%d", str, Integer.valueOf(i));
            } else {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Version not supported: ", i));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: createDeviceGroupsTable */
    public void lambda$createTableCompletable$0$DeviceScheme(SQLiteDatabase sQLiteDatabase, String str, int i) {
        if (i != 1) {
            if (i == 2) {
                StringBuilder outline116 = GeneratedOutlineSupport1.outline116("CREATE TABLE IF NOT EXISTS ", str, " (", "_id", " INTEGER PRIMARY KEY,");
                GeneratedOutlineSupport1.outline181(outline116, "identifier", " TEXT NOT NULL,", "transport", " TEXT NOT NULL,");
                GeneratedOutlineSupport1.outline181(outline116, "condition", " TEXT NOT NULL,", "device_id", " TEXT,");
                GeneratedOutlineSupport1.outline181(outline116, "name", " TEXT,", "serial_number", " TEXT,");
                outline116.append("type");
                outline116.append(" TEXT,");
                outline116.append("color");
                outline116.append(" TEXT)");
                String sb = outline116.toString();
                Logger.v(sb);
                sQLiteDatabase.execSQL(sb);
                Logger.d("Device Group DB table %s V%d created", str, Integer.valueOf(i));
            } else if (i == 3) {
                StringBuilder outline1162 = GeneratedOutlineSupport1.outline116("CREATE TABLE IF NOT EXISTS ", str, " (", "_id", " INTEGER PRIMARY KEY,");
                GeneratedOutlineSupport1.outline181(outline1162, "identifier", " TEXT NOT NULL,", "transport", " TEXT NOT NULL,");
                GeneratedOutlineSupport1.outline181(outline1162, "device_id", " TEXT,", "name", " TEXT,");
                GeneratedOutlineSupport1.outline181(outline1162, "serial_number", " TEXT,", "type", " TEXT,");
                GeneratedOutlineSupport1.outline181(outline1162, "color", " TEXT,", DeviceGroupContract.DeviceGroupV3Columns.COLUMN_STANDBY_TIMEOUT, " INTEGER, ");
                outline1162.append(DeviceGroupContract.DeviceGroupV3Columns.COLUMN_LAST_KNOWN_STANDBY_REASON);
                outline1162.append(" TEXT)");
                String sb2 = outline1162.toString();
                Logger.v(sb2);
                sQLiteDatabase.execSQL(sb2);
                Logger.d("Device Group DB table %s V%d created", str, Integer.valueOf(i));
            } else {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Version not supported: ", i));
            }
        }
    }

    private Completable createIndexCompletable(final SQLiteDatabase sQLiteDatabase, final int i) {
        boolean z = i >= 2;
        Preconditions.precondition(z, "Cannot create DB index for version " + i);
        final String tableNameForVersion = getTableNameForVersion(i);
        return Completable.fromAction(new Action() { // from class: com.amazon.alexa.accessory.persistence.device.scheme.-$$Lambda$DeviceScheme$KGWd8FhS-ZDT-vqvZShj9Uyf9n4
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                DeviceScheme.this.lambda$createIndexCompletable$1$DeviceScheme(sQLiteDatabase, tableNameForVersion, i);
            }
        });
    }

    private Completable createTableCompletable(final SQLiteDatabase sQLiteDatabase, final int i) {
        boolean z = i >= 2;
        Preconditions.precondition(z, "Cannot create DB table for version " + i);
        final String tableNameForVersion = getTableNameForVersion(i);
        return Completable.fromAction(new Action() { // from class: com.amazon.alexa.accessory.persistence.device.scheme.-$$Lambda$DeviceScheme$9i0UBZxY4IYbM9DI2lHR7qdHJpo
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                DeviceScheme.this.lambda$createTableCompletable$0$DeviceScheme(sQLiteDatabase, tableNameForVersion, i);
            }
        });
    }

    private String getTableNameForVersion(int i) {
        if (i != 1) {
            if (i == 2) {
                return DeviceGroupContract.Tables.DEVICE_GROUPS;
            }
            if (i == 3) {
                return DeviceGroupContract.Tables.DEVICE_GROUPS_V3;
            }
            return null;
        }
        return "devices";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: migrateDeviceGroupsTable */
    public void lambda$migrateTableContentsCompletable$3$DeviceScheme(SQLiteDatabase sQLiteDatabase) {
        Cursor query = sQLiteDatabase.query(DeviceGroupContract.Tables.DEVICE_GROUPS, null, null, null, null, null, null);
        try {
            if (query.getCount() > 0) {
                Logger.d("Migrating DB table contents from V2 to V3 with %d rows", Integer.valueOf(query.getCount()));
                while (query.moveToNext()) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("_id", Long.valueOf(query.getLong(DeviceGroupContract.DeviceGroupColumns.COLUMN_INDEX_ID)));
                    contentValues.put("identifier", query.getString(DeviceGroupContract.DeviceGroupColumns.COLUMN_INDEX_IDENTIFIER));
                    contentValues.put("transport", query.getString(DeviceGroupContract.DeviceGroupColumns.COLUMN_INDEX_TRANSPORT));
                    contentValues.put("device_id", query.getString(DeviceGroupContract.DeviceGroupColumns.COLUMN_INDEX_DEVICE_ID));
                    contentValues.put("name", query.getString(DeviceGroupContract.DeviceGroupColumns.COLUMN_INDEX_NAME));
                    contentValues.put("serial_number", query.getString(DeviceGroupContract.DeviceGroupColumns.COLUMN_INDEX_SERIAL_NUMBER));
                    contentValues.put("type", query.getString(DeviceGroupContract.DeviceGroupColumns.COLUMN_INDEX_TYPE));
                    contentValues.put("color", query.getString(DeviceGroupContract.DeviceGroupColumns.COLUMN_INDEX_COLOR));
                    contentValues.put(DeviceGroupContract.DeviceGroupV3Columns.COLUMN_STANDBY_TIMEOUT, Long.valueOf(DeviceGroup.Condition.ACTIVE.getSpecialTimestamp()));
                    contentValues.put(DeviceGroupContract.DeviceGroupV3Columns.COLUMN_LAST_KNOWN_STANDBY_REASON, DeviceGroup.StandbyReason.UNKNOWN.name());
                    try {
                        long insertOrThrow = sQLiteDatabase.insertOrThrow(DeviceGroupContract.Tables.DEVICE_GROUPS_V3, null, contentValues);
                        if (insertOrThrow == -1) {
                            Logger.d("ERROR: Insert returns -1 inserting %s", DeviceDatabaseUtils.contentValuesToDeviceGroup(contentValues));
                            Logger.e("Insert returns -1 inserting %s", RedactionUtil.redact(DeviceDatabaseUtils.contentValuesToDeviceGroup(contentValues)));
                        } else {
                            Logger.d("DB Row %d migrated", Long.valueOf(insertOrThrow));
                        }
                    } catch (Exception e) {
                        Logger.d("ERROR: Could not insert %s while migrating device groups from v2 to v3", e, DeviceDatabaseUtils.contentValuesToDeviceGroup(contentValues));
                        Logger.e("Could not insert %s while migrating device groups from v2 to v3", e, RedactionUtil.redact(DeviceDatabaseUtils.contentValuesToDeviceGroup(contentValues)));
                        throw new RuntimeException(e);
                    }
                }
                Logger.d("All DB rows migrated from %s to %s", DeviceGroupContract.Tables.DEVICE_GROUPS, DeviceGroupContract.Tables.DEVICE_GROUPS_V3);
            } else {
                Logger.d("DB table V2 is empty, nothing to migrate");
            }
            query.close();
            sQLiteDatabase.execSQL("DROP TABLE device_groups");
            Logger.d("Dropped old DB Table %s", DeviceGroupContract.Tables.DEVICE_GROUPS);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: migrateDevicesTable */
    public void lambda$migrateTableContentsCompletable$2$DeviceScheme(SQLiteDatabase sQLiteDatabase) {
        Cursor query = sQLiteDatabase.query("devices", null, null, null, null, null, null);
        try {
            if (query.getCount() > 0) {
                Logger.d("Migrating DB table contents from V1 to V2 with %d rows", Integer.valueOf(query.getCount()));
                while (query.moveToNext()) {
                    DeviceGroup.Builder identifier = new DeviceGroup.Builder().id(query.getLong(0)).transport(query.getInt(5) == 0 ? AccessoryTransport.Type.GATT : AccessoryTransport.Type.RFCOMM).identifier(query.getString(4));
                    String string = query.getString(1);
                    String string2 = query.getString(2);
                    String string3 = query.getString(3);
                    if (string != null && string2 != null && string3 != null) {
                        identifier.devices(Collections.singletonList(new Device.Builder().name(string).serialNumber(string2).type(string3).deviceId(0).color(0).build()));
                    }
                    DeviceGroup build = identifier.build();
                    try {
                        if (sQLiteDatabase.insertOrThrow(DeviceGroupContract.Tables.DEVICE_GROUPS, null, DeviceDatabaseUtils.deviceGroupToContentValues(build)) == -1) {
                            Logger.d("ERROR: Insert returns -1 inserting %s", build);
                            Logger.e("Insert returns -1 inserting %s", RedactionUtil.redact(build));
                        }
                    } catch (Exception e) {
                        Logger.d("ERROR: Could not insert %s while migrating devices", e, build);
                        Logger.e("Could not insert %s while migrating devices", e, RedactionUtil.redact(build));
                        throw new RuntimeException(e);
                    }
                }
                Logger.d("All DB rows migrated from %s to %s", "devices", DeviceGroupContract.Tables.DEVICE_GROUPS);
            } else {
                Logger.d("DB table V1 is empty. Nothing to migrate");
            }
            query.close();
            sQLiteDatabase.execSQL("DROP TABLE devices");
            Logger.d("Dropped old table %s", "devices");
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    private Completable migrateTableContentsCompletable(final SQLiteDatabase sQLiteDatabase, int i) {
        if (i != 2) {
            if (i != 3) {
                return Completable.error(new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Cannot migrate table contents to version ", i)));
            }
            return Completable.fromAction(new Action() { // from class: com.amazon.alexa.accessory.persistence.device.scheme.-$$Lambda$DeviceScheme$GRfBxPbz29YRQb4a7i8Ac-L7PK4
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    DeviceScheme.this.lambda$migrateTableContentsCompletable$3$DeviceScheme(sQLiteDatabase);
                }
            });
        }
        return Completable.fromAction(new Action() { // from class: com.amazon.alexa.accessory.persistence.device.scheme.-$$Lambda$DeviceScheme$-ckODktRtI1DSVcWpbuUdW9Db6I
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                DeviceScheme.this.lambda$migrateTableContentsCompletable$2$DeviceScheme(sQLiteDatabase);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.persistence.DatabaseScheme
    public int getVersion() {
        return this.version;
    }

    @Override // com.amazon.alexa.accessory.persistence.DatabaseScheme
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.beginTransaction();
        if (createTableCompletable(sQLiteDatabase, getVersion()).andThen(createIndexCompletable(sQLiteDatabase, getVersion())).blockingAwait(60L, TimeUnit.SECONDS)) {
            sQLiteDatabase.setTransactionSuccessful();
            sQLiteDatabase.endTransaction();
            return;
        }
        sQLiteDatabase.endTransaction();
        throw new RuntimeException("onCreate could not complete.");
    }

    @Override // com.amazon.alexa.accessory.persistence.DatabaseScheme
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        throw new UnsupportedOperationException(GeneratedOutlineSupport1.outline53("onDowngrade: unsupported downgrade from ", i, " to ", i2));
    }

    @Override // com.amazon.alexa.accessory.persistence.DatabaseScheme
    public void onOpen(SQLiteDatabase sQLiteDatabase) {
    }

    @Override // com.amazon.alexa.accessory.persistence.DatabaseScheme
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        while (true) {
            i++;
            if (i > i2) {
                break;
            }
            arrayList.add(createTableCompletable(sQLiteDatabase, i));
            arrayList.add(createIndexCompletable(sQLiteDatabase, i));
            arrayList.add(migrateTableContentsCompletable(sQLiteDatabase, i));
        }
        sQLiteDatabase.beginTransaction();
        if (Completable.concat(arrayList).blockingAwait(60L, TimeUnit.SECONDS)) {
            sQLiteDatabase.setTransactionSuccessful();
            sQLiteDatabase.endTransaction();
            return;
        }
        sQLiteDatabase.endTransaction();
        throw new RuntimeException("onUpgrade could not complete.");
    }

    public DeviceScheme() {
        this(3);
    }
}
