package com.amazon.deecomms.common.database;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.util.EncryptionUtils;
import com.amazon.deecomms.contacts.database.ContactsDatabaseHelper;
import com.amazon.deecomms.messaging.database.MessagingDbHelper;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Date;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteException;
import net.sqlcipher.database.SQLiteOpenHelper;
/* loaded from: classes12.dex */
public final class CommsDatabaseHelper extends SQLiteOpenHelper {
    private static final String COMMS_DATABASE_KEY_ALIAS = "COMMS_DATABASE_KEY_ALIAS";
    private static final String DATABASE_NAME = "comms.db";
    public static final int DATABASE_VERSION = 29;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsDatabaseHelper.class);
    private static CommsDatabaseHelper sInstance;
    private ICommsDatabase mContactsDatabase;
    private Context mContext;
    private SQLiteDatabase mDb;
    private final EncryptionUtils mEncryptionUtils;
    private Date mKeyExpiryDate;
    private ICommsDatabase mMessagingDatabase;

    private CommsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 29, new SQLCipherMigrationHook(context));
        this.mContactsDatabase = new ContactsDatabaseHelper();
        this.mMessagingDatabase = new MessagingDbHelper();
        SQLiteDatabase.loadLibs(context);
        this.mEncryptionUtils = new EncryptionUtils(context);
        this.mContext = context;
    }

    private synchronized void cacheKeyExpiryDate() {
        this.mKeyExpiryDate = this.mEncryptionUtils.getKeyExpiryDate(COMMS_DATABASE_KEY_ALIAS);
    }

    private synchronized SQLiteDatabase getDatabaseOnKeyExpiry() throws SQLiteException {
        SQLiteDatabase writableDatabase;
        writableDatabase = super.getWritableDatabase(getKey());
        this.mEncryptionUtils.generateKey(COMMS_DATABASE_KEY_ALIAS);
        cacheKeyExpiryDate();
        writableDatabase.changePassword(getKey());
        return writableDatabase;
    }

    public static synchronized CommsDatabaseHelper getInstance(Context context) {
        CommsDatabaseHelper commsDatabaseHelper;
        synchronized (CommsDatabaseHelper.class) {
            if (sInstance == null) {
                sInstance = new CommsDatabaseHelper(context);
            }
            commsDatabaseHelper = sInstance;
        }
        return commsDatabaseHelper;
    }

    private char[] getKey() {
        return this.mEncryptionUtils.getKey(COMMS_DATABASE_KEY_ALIAS);
    }

    private synchronized boolean hasDatabaseKeyExpired() {
        boolean z;
        if (this.mKeyExpiryDate == null) {
            cacheKeyExpiryDate();
        }
        if (this.mKeyExpiryDate != null) {
            if (new Date().after(this.mKeyExpiryDate)) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    private void logDBMetricWithErrorSource(@NonNull String str, @NonNull Object obj) {
        CounterMetric generateOperational = CounterMetric.generateOperational(str);
        generateOperational.getMetadata().put("errorSource", obj);
        generateOperational.getMetadata().put("EventValue", 29);
        MetricsHelper.recordSingleOccurrence(generateOperational);
    }

    private void logDBMetricWithErrorSourceAndSource(@NonNull String str, @NonNull Object obj, @NonNull Object obj2) {
        CounterMetric generateOperational = CounterMetric.generateOperational(str);
        generateOperational.getMetadata().put("errorSource", obj);
        generateOperational.getMetadata().put("source", obj2);
        generateOperational.getMetadata().put("EventValue", 29);
        MetricsHelper.recordSingleOccurrence(generateOperational);
    }

    private SQLiteDatabase recreateDatabase() throws SQLiteException {
        LOG.i("Recreating Comms Database");
        SQLiteDatabase sQLiteDatabase = this.mDb;
        if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
            LOG.i("Closing database before recreating database");
            this.mDb.close();
        }
        this.mContext.deleteDatabase(DATABASE_NAME);
        this.mEncryptionUtils.generateKey(COMMS_DATABASE_KEY_ALIAS);
        char[] key = getKey();
        logDBMetricWithErrorSource(MetricKeys.DEBUG_DATABASE_RECREATE, key);
        return super.getWritableDatabase(key);
    }

    public synchronized SQLiteDatabase getReadableDatabase() {
        return getWritableDatabase();
    }

    public synchronized SQLiteDatabase getWritableDatabase() {
        try {
            if (hasDatabaseKeyExpired()) {
                LOG.i("Database key has expired. Getting Database on Key expiry...");
                logDBMetricWithErrorSource(MetricKeys.DEBUG_DATABASE_KEY_EXPIRED, this.mKeyExpiryDate);
                this.mDb = getDatabaseOnKeyExpiry();
            }
            if (this.mDb == null || !this.mDb.isOpen() || this.mDb.isReadOnly()) {
                LOG.i("DB is not ready to be written...");
                if (!this.mEncryptionUtils.isKeyValid(COMMS_DATABASE_KEY_ALIAS)) {
                    logDBMetricWithErrorSource(MetricKeys.DEBUG_DATABASE_GET_WRITABLE_DB_FAIL, "Invalid Key");
                    this.mDb = recreateDatabase();
                } else {
                    char[] key = getKey();
                    try {
                        this.mDb = super.getWritableDatabase(key);
                    } catch (SQLiteException e) {
                        LOG.e("Exception while trying to get Writable Database. Recreating DB...", e);
                        logDBMetricWithErrorSourceAndSource(MetricKeys.DEBUG_DATABASE_GET_WRITABLE_DB_FAIL, e.getMessage(), key);
                        this.mDb = recreateDatabase();
                    }
                }
            }
        } catch (SQLiteException e2) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("An exception occurred with the SQLite DB  : ");
            outline1.append(e2.getMessage());
            commsLogger.e(outline1.toString());
            logDBMetricWithErrorSource(MetricKeys.DEBUG_DATABASE_EXCEPTION, e2.getMessage());
        }
        return this.mDb;
    }

    @Override // net.sqlcipher.database.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.mContactsDatabase.onCreate(sQLiteDatabase);
        this.mMessagingDatabase.onCreate(sQLiteDatabase);
        CounterMetric generateOperational = CounterMetric.generateOperational(MetricKeys.DEBUG_DATABASE_CREATE);
        generateOperational.getMetadata().put("EventValue", 29);
        MetricsHelper.recordSingleOccurrence(generateOperational);
    }

    @Override // net.sqlcipher.database.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i > i2) {
            LOG.e(GeneratedOutlineSupport1.outline54("onUpgrade() was asked to upgrade from ", i, " to ", i2, " but this was detected as a downgrade. Recreating Database instead."));
            logDBMetricWithErrorSource(MetricKeys.DEBUG_DATABASE_DOWNGRADE_DETECTED, "oldVersion: " + i + ", newVersion: " + i2);
            this.mDb = recreateDatabase();
            return;
        }
        logDBMetricWithErrorSource(MetricKeys.DEBUG_DATABASE_UPGRADE, GeneratedOutlineSupport1.outline53("oldVersion: ", i, ", newVersion: ", i2));
        this.mContactsDatabase.onUpgrade(sQLiteDatabase, i, i2);
        this.mMessagingDatabase.onUpgrade(sQLiteDatabase, i, i2);
    }
}
