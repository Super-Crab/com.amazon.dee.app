package com.amazonaws.mobileconnectors.remoteconfiguration.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazonaws.mobileconnectors.remoteconfiguration.Configuration;
import com.amazonaws.mobileconnectors.remoteconfiguration.exceptions.ConfigurationNotFoundException;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.gear.Checks;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfiguration;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfigurationImpl;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes13.dex */
public class ConfigurationDb {
    private static final int COL_ENTITY_TAG = 2;
    private static final int COL_ORIGIN = 3;
    private static final int COL_TIMESTAMP = 1;
    private static final int COL_VALUE = 0;
    private static final int DATABASE_VERSION = 2;
    private static final String DB_NAME = "ConfigurationCache.db";
    private static final String SQL_CREATE_CONFIGURATION_TABLE = "CREATE TABLE configuration (_id INTEGER PRIMARY KEY, json TEXT, origin INTEGER NOT NULL CHECK (origin IN (1,2,3)), timestamp INTEGER, entity_tag TEXT);";
    private static final String TAG = "ConfigurationDb";
    private DBHelper dbHelper;
    private SQLiteDatabase mDatabase;
    private AtomicInteger openCounter = new AtomicInteger(0);
    private static final String[] PROJECTION_CONFIGURATION = {"json", "timestamp"};
    private static final String[] PROJECTION_REMOTE_CONFIGURATION = {"json", "timestamp", "entity_tag", "origin"};
    private static final Map<String, ConfigurationDb> instanceMap = new HashMap();

    /* loaded from: classes13.dex */
    private static class ConfigurationTable implements BaseColumns {
        private static final String COLUMN_ENTITY_TAG = "entity_tag";
        private static final String COLUMN_JSON = "json";
        private static final String COLUMN_ORIGIN = "origin";
        private static final String COLUMN_TIMESTAMP = "timestamp";
        private static final String TABLE_NAME = "configuration";

        private ConfigurationTable() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public static class DBHelper extends SQLiteOpenHelper {
        private final File mDbFile;

        /* JADX INFO: Access modifiers changed from: private */
        public static String getDbFileNameFor(String str) {
            return GeneratedOutlineSupport1.outline75(str, "_", ConfigurationDb.DB_NAME);
        }

        private void setupDb(SQLiteDatabase sQLiteDatabase) {
            boolean z = false;
            try {
                ArrayList<String> arrayList = new ArrayList();
                arrayList.add(ConfigurationDb.SQL_CREATE_CONFIGURATION_TABLE);
                sQLiteDatabase.beginTransaction();
                for (String str : arrayList) {
                    sQLiteDatabase.execSQL(str);
                    String unused = ConfigurationDb.TAG;
                    String str2 = "Executed sql, \n" + str;
                }
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
                z = true;
                Log.i(ConfigurationDb.TAG, "Created tables for version 2");
            } catch (Throwable th) {
                if (!z) {
                    boolean delete = this.mDbFile.delete();
                    String str3 = ConfigurationDb.TAG;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed creating tables in SyncDB. Attempt to delete SyncDB ");
                    outline107.append(delete ? "succeeded" : "failed");
                    Log.e(str3, outline107.toString());
                }
                throw th;
            }
        }

        public File getDbFile() {
            return this.mDbFile;
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            Log.i(ConfigurationDb.TAG, "Creating Configuration DB version 2");
            setupDb(sQLiteDatabase);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            throw new DowngradeException();
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            super.onOpen(sQLiteDatabase);
            sQLiteDatabase.execSQL("PRAGMA foreign_keys=ON;");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            if (i == 1 && i2 == 2) {
                String str = ConfigurationDb.TAG;
                Log.i(str, "Upgrading database schema from " + i + " to " + i2);
                sQLiteDatabase.execSQL("DROP INDEX e_tag_idx_configuration;");
                sQLiteDatabase.execSQL("DROP INDEX configuration_type_idx_configuration;");
                sQLiteDatabase.execSQL("ALTER TABLE configuration RENAME TO configuration_old;");
                sQLiteDatabase.execSQL(ConfigurationDb.SQL_CREATE_CONFIGURATION_TABLE);
                sQLiteDatabase.execSQL("INSERT INTO configuration(_id, json, origin, entity_tag, timestamp) SELECT _id, value, CASE WHEN e_tag IS NULL THEN 1 ELSE 2 END AS origin, e_tag, last_load_time FROM configuration_old WHERE configuration_type = 1;");
                sQLiteDatabase.execSQL("DROP TABLE configuration_old;");
                String unused = ConfigurationDb.TAG;
                return;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline53("Illegal upgrade: ", i, " to ", i2));
        }

        private DBHelper(Context context, String str) {
            super(context, getDbFileNameFor(str), (SQLiteDatabase.CursorFactory) null, 2);
            this.mDbFile = context.getDatabasePath(getDbFileNameFor(str));
        }
    }

    ConfigurationDb(Context context, String str, DBHelper dBHelper) {
        this.dbHelper = dBHelper;
    }

    private synchronized void closeDatabase() {
        if (this.openCounter.decrementAndGet() == 0 && this.mDatabase != null) {
            this.mDatabase.close();
        }
    }

    private Configuration configurationFromCursor(Cursor cursor) {
        if (cursor != null && cursor.getString(0) != null) {
            return new ConfigurationImpl(cursor.getString(0), cursor.isNull(1) ? null : new Date(cursor.getLong(1)));
        }
        throw new ConfigurationNotFoundException("Configuration not found");
    }

    private ContentValues getContentValues(String str, Integer num, Long l, String str2) {
        ContentValues contentValues = new ContentValues();
        if (str != null) {
            contentValues.put("json", str);
        } else {
            contentValues.putNull("json");
        }
        if (num != null) {
            contentValues.put("origin", num);
        } else {
            contentValues.putNull("origin");
        }
        if (l != null) {
            contentValues.put("timestamp", l);
        } else {
            contentValues.putNull("timestamp");
        }
        if (str2 != null) {
            contentValues.put("entity_tag", str2);
        } else {
            contentValues.putNull("entity_tag");
        }
        return contentValues;
    }

    public static ConfigurationDb getOrCreateInstance(Context context, String str) {
        ConfigurationDb configurationDb;
        String replace = str.replace("/", "");
        synchronized (instanceMap) {
            configurationDb = instanceMap.get(replace);
            if (configurationDb == null) {
                DBHelper dBHelper = new DBHelper(context, replace);
                try {
                    dBHelper.getWritableDatabase();
                } catch (DowngradeException unused) {
                    context.deleteDatabase(DBHelper.getDbFileNameFor(replace));
                    dBHelper = new DBHelper(context, replace);
                }
                ConfigurationDb configurationDb2 = new ConfigurationDb(context, replace, dBHelper);
                instanceMap.put(replace, configurationDb2);
                configurationDb = configurationDb2;
            }
        }
        return configurationDb;
    }

    private boolean hasConfiguration(SQLiteDatabase sQLiteDatabase) {
        Cursor query = sQLiteDatabase.query(PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, new String[]{"_id"}, null, null, null, null, null, "1");
        if (query != null) {
            boolean moveToFirst = query.moveToFirst();
            query.close();
            return moveToFirst;
        }
        return false;
    }

    private synchronized SQLiteDatabase openDatabase() {
        if (this.openCounter.incrementAndGet() == 1) {
            this.mDatabase = this.dbHelper.getWritableDatabase();
        }
        return this.mDatabase;
    }

    private RemoteConfiguration remoteConfigurationFromCursor(Cursor cursor, String str) {
        if (cursor == null) {
            return null;
        }
        return new RemoteConfigurationImpl(configurationFromCursor(cursor), str, cursor.getInt(3), cursor.getString(2), false);
    }

    private void saveConfigurationPrivate(SQLiteDatabase sQLiteDatabase, String str, int i, Long l, String str2) {
        ContentValues contentValues = getContentValues(str, Integer.valueOf(i), l, str2);
        if (hasConfiguration(sQLiteDatabase)) {
            int update = sQLiteDatabase.update(PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, contentValues, null, null);
            if (update == 1) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Updated 1 row in configuration table,\n");
                outline107.append(contentValues.toString());
                outline107.toString();
                return;
            }
            throw new IllegalStateException("Updated " + update + " rows while was intending to update one and only one row in " + PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY);
        }
        sQLiteDatabase.insertOrThrow(PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, null, contentValues);
        String str3 = "Inserted 1 row into configuration table,\n" + contentValues.toString();
    }

    synchronized void deleteConfiguration() {
        SQLiteDatabase sQLiteDatabase;
        Throwable th;
        try {
            sQLiteDatabase = openDatabase();
            try {
                sQLiteDatabase.beginTransaction();
                sQLiteDatabase.delete(PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, null, null);
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
                closeDatabase();
            } catch (Throwable th2) {
                th = th2;
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.endTransaction();
                }
                closeDatabase();
                throw th;
            }
        } catch (Throwable th3) {
            sQLiteDatabase = null;
            th = th3;
        }
    }

    public synchronized Configuration readConfiguration() throws ConfigurationNotFoundException {
        SQLiteDatabase sQLiteDatabase;
        Configuration configurationFromCursor;
        Cursor cursor = null;
        try {
            try {
                sQLiteDatabase = openDatabase();
                try {
                    sQLiteDatabase.beginTransaction();
                    Cursor query = sQLiteDatabase.query(PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, PROJECTION_CONFIGURATION, null, null, null, null, null);
                    sQLiteDatabase.setTransactionSuccessful();
                    if (query != null && query.moveToFirst()) {
                        configurationFromCursor = configurationFromCursor(query);
                        query.close();
                        sQLiteDatabase.endTransaction();
                        closeDatabase();
                    } else {
                        throw new ConfigurationNotFoundException("Configuration not found");
                    }
                } catch (Throwable th) {
                    th = th;
                    if (0 != 0) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.endTransaction();
                    }
                    closeDatabase();
                    throw th;
                }
            } catch (Throwable th2) {
                throw th2;
            }
        } catch (Throwable th3) {
            th = th3;
            sQLiteDatabase = null;
        }
        return configurationFromCursor;
    }

    public synchronized RemoteConfiguration readRemoteConfiguration(String str) {
        SQLiteDatabase sQLiteDatabase;
        Cursor query;
        Cursor cursor = null;
        try {
            try {
                sQLiteDatabase = openDatabase();
                try {
                    sQLiteDatabase.beginTransaction();
                    query = sQLiteDatabase.query(PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, PROJECTION_REMOTE_CONFIGURATION, null, null, null, null, null);
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
                sQLiteDatabase = null;
            }
            try {
                sQLiteDatabase.setTransactionSuccessful();
                if (query != null && query.moveToFirst()) {
                    RemoteConfiguration remoteConfigurationFromCursor = remoteConfigurationFromCursor(query, str);
                    query.close();
                    sQLiteDatabase.endTransaction();
                    closeDatabase();
                    return remoteConfigurationFromCursor;
                }
                if (query != null) {
                    query.close();
                }
                sQLiteDatabase.endTransaction();
                closeDatabase();
                return null;
            } catch (Throwable th3) {
                th = th3;
                cursor = query;
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.endTransaction();
                }
                closeDatabase();
                throw th;
            }
        } catch (Throwable th4) {
            throw th4;
        }
    }

    public void saveConfiguration(RemoteConfiguration remoteConfiguration) {
        if (remoteConfiguration != null) {
            if (remoteConfiguration.getConfiguration() != null) {
                if (remoteConfiguration.getConfiguration().getTimestamp() != null) {
                    saveConfiguration(remoteConfiguration.getConfiguration().getAsJsonString(), remoteConfiguration.getOrigin(), Long.valueOf(remoteConfiguration.getConfiguration().getTimestamp().getTime()), remoteConfiguration.getEntityTag());
                    return;
                }
                throw new NullPointerException("The Configuration's timestamp may not be null");
            }
            throw new NullPointerException("The contained Configuration may not be null");
        }
        throw new NullPointerException("The RemoteConfiguration may not be null");
    }

    private synchronized void saveConfiguration(String str, int i, Long l, String str2) {
        Checks.checkNotNull(str, "configuration cannot be null");
        Checks.checkNotNull(l, "timestamp cannot be null for non-default configuration");
        SQLiteDatabase openDatabase = openDatabase();
        openDatabase.beginTransaction();
        saveConfigurationPrivate(openDatabase, str, i, l, str2);
        openDatabase.setTransactionSuccessful();
        openDatabase.endTransaction();
        closeDatabase();
    }
}
