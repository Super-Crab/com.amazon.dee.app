package com.amazon.alexa.location.phase3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.dee.app.cachemanager.Encryptor;
import com.dee.app.cachemanager.MarshmallowPlusAESEncryptor;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes9.dex */
public class LocationDataStoreService {
    public static final String COLUMN_NAME_KEY = "key";
    public static final String COLUMN_NAME_VALUE = "value";
    public static final int DB_VERSION = 3;
    private static final String ENCRYPTION_KEY_ALIAS = "locationDataStore";
    private static final String TAG = "LocationDataStoreService";
    private final Encryptor encryptor;
    private final LazyComponent<Mobilytics> mobilytics;
    private final SQLiteDatabase sqLiteDatabase;
    public static final String LAST_TRIGGER_TABLE = "lastTriggerTable";
    public static final String EVENT_QUEUE_TABLE = "eventQueue";
    public static final String ALEXA_GEOFENCE_STATES_TABLE = "alexaGeofenceStates";
    public static final String LAST_WIFI_STATUS_TABLE = "lastWifiStatusTable";
    public static final String LOCATION_OF_INTEREST_TABLE = "locationsOfInterest";
    public static final String NATIVE_LOCATION_INPUT_TABLE = "nativeLocationInput";
    public static final String LAST_MOTION_STATUS_TABLE = "lastMotionStatus";
    public static final String LOCATION_SETTINGS_TABLE = "locationSettings";
    @VisibleForTesting
    public static final String[] ALL_TABLES = {LAST_TRIGGER_TABLE, EVENT_QUEUE_TABLE, ALEXA_GEOFENCE_STATES_TABLE, LAST_WIFI_STATUS_TABLE, LOCATION_OF_INTEREST_TABLE, NATIVE_LOCATION_INPUT_TABLE, LAST_MOTION_STATUS_TABLE, LOCATION_SETTINGS_TABLE};

    /* loaded from: classes9.dex */
    private static class DbOpenHelper extends SQLiteOpenHelper {
        private static final String SQL_CREATE_TABLE_TEMPLATE = "CREATE TABLE IF NOT EXISTS %s (key TEXT PRIMARY KEY, value BLOB)";
        private static final String SQL_DROP_TABLE_TEMPLATE = "DROP TABLE IF EXISTS %s";
        private final String[] tables;

        DbOpenHelper(Context context, String[] strArr) {
            super(context, LocationDataStoreService.TAG, (SQLiteDatabase.CursorFactory) null, 3);
            this.tables = strArr;
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            String[] strArr = this.tables;
            int length = strArr.length;
            for (int i = 0; i < length; i++) {
                sQLiteDatabase.execSQL(String.format(SQL_CREATE_TABLE_TEMPLATE, strArr[i]));
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            if (i < 2) {
                String[] strArr = this.tables;
                int length = strArr.length;
                for (int i3 = 0; i3 < length; i3++) {
                    sQLiteDatabase.execSQL(String.format(SQL_DROP_TABLE_TEMPLATE, strArr[i3]));
                }
            }
            onCreate(sQLiteDatabase);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface TableName {
    }

    public LocationDataStoreService(Context context, LazyComponent<Mobilytics> lazyComponent) {
        this(context, ALL_TABLES, getEncryptor(context, ENCRYPTION_KEY_ALIAS), lazyComponent);
    }

    private static Encryptor getEncryptor(Context context, String str) {
        int i = Build.VERSION.SDK_INT;
        return new MarshmallowPlusAESEncryptor(context, str);
    }

    public <T> LocationDataStore<T> getDataStore(String str, Class<T> cls) {
        return new LocationDataStore<>(this.sqLiteDatabase, str, cls, this.encryptor, this.mobilytics);
    }

    @VisibleForTesting
    LocationDataStoreService(Context context, String[] strArr, Encryptor encryptor, LazyComponent<Mobilytics> lazyComponent) {
        this.encryptor = encryptor;
        this.mobilytics = lazyComponent;
        this.sqLiteDatabase = new DbOpenHelper(context, strArr).getWritableDatabase();
    }
}
