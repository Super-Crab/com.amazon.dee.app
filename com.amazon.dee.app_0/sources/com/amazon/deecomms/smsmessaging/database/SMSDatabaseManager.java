package com.amazon.deecomms.smsmessaging.database;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.util.EncryptionUtils;
import java.util.Date;
import net.sqlcipher.SQLException;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteException;
import net.sqlcipher.database.SQLiteOpenHelper;
/* loaded from: classes12.dex */
public final class SMSDatabaseManager extends SQLiteOpenHelper {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, SMSDatabaseManager.class);
    private static SMSDatabaseManager sInstance;
    private final Context mContext;
    private SQLiteDatabase mDb;
    private final EncryptionUtils mEncryptionUtils;
    private Date mKeyExpiryDate;

    private SMSDatabaseManager(@NonNull Context context) {
        super(context, SMSMessageContract.DATABASE_NAME, null, 1);
        SQLiteDatabase.loadLibs(context);
        this.mEncryptionUtils = new EncryptionUtils(context);
        this.mContext = context;
    }

    private synchronized void cacheKeyExpiryDate() {
        this.mKeyExpiryDate = this.mEncryptionUtils.getKeyExpiryDate(SMSMessageContract.COMMS_SMS_DATABASE_KEY_ALIAS);
    }

    private synchronized SQLiteDatabase getDatabaseOnKeyExpiry() {
        SQLiteDatabase writableDatabase;
        writableDatabase = super.getWritableDatabase(getKey());
        this.mEncryptionUtils.generateKey(SMSMessageContract.COMMS_SMS_DATABASE_KEY_ALIAS);
        cacheKeyExpiryDate();
        writableDatabase.changePassword(getKey());
        return writableDatabase;
    }

    public static synchronized SMSDatabaseManager getInstance(@NonNull Context context) {
        SMSDatabaseManager sMSDatabaseManager;
        synchronized (SMSDatabaseManager.class) {
            if (sInstance == null) {
                sInstance = new SMSDatabaseManager(context);
            }
            sMSDatabaseManager = sInstance;
        }
        return sMSDatabaseManager;
    }

    private char[] getKey() {
        return this.mEncryptionUtils.getKey(SMSMessageContract.COMMS_SMS_DATABASE_KEY_ALIAS);
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

    private SQLiteDatabase recreateDatabase() {
        LOG.i("Recreating Comms SMS Database");
        SQLiteDatabase sQLiteDatabase = this.mDb;
        if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
            LOG.i("Closing database before recreating database");
            this.mDb.close();
        }
        this.mContext.deleteDatabase(SMSMessageContract.DATABASE_NAME);
        this.mEncryptionUtils.generateKey(SMSMessageContract.COMMS_SMS_DATABASE_KEY_ALIAS);
        char[] key = getKey();
        CounterMetric generateOperational = CounterMetric.generateOperational(SMSMessageContract.DEBUG_DATABASE_RECREATE);
        generateOperational.getMetadata().put("errorSource", key);
        generateOperational.getMetadata().put("EventValue", 1);
        MetricsHelper.recordSingleOccurrence(generateOperational);
        return super.getWritableDatabase(key);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:46:0x00db -> B:53:0x00e2). Please submit an issue!!! */
    public synchronized SQLiteDatabase getWritableDatabase() {
        if (hasDatabaseKeyExpired()) {
            LOG.i("Database key has expired. Getting Database on Key expiry...");
            CounterMetric generateOperational = CounterMetric.generateOperational(SMSMessageContract.DEBUG_DATABASE_KEY_EXPIRED);
            generateOperational.getMetadata().put("errorSource", this.mKeyExpiryDate);
            generateOperational.getMetadata().put("EventValue", 1);
            MetricsHelper.recordSingleOccurrence(generateOperational);
            this.mDb = getDatabaseOnKeyExpiry();
        }
        SQLiteDatabase sQLiteDatabase = this.mDb;
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen() || this.mDb.isReadOnly()) {
            try {
                LOG.i("DB is not ready to be written...");
                if (!this.mEncryptionUtils.isKeyValid(SMSMessageContract.COMMS_SMS_DATABASE_KEY_ALIAS)) {
                    LOG.i("Will try recreate database");
                    CounterMetric generateOperational2 = CounterMetric.generateOperational(SMSMessageContract.DEBUG_DATABASE_GET_WRITABLE_DB_FAIL);
                    generateOperational2.getMetadata().put("errorSource", "Invalid Key");
                    generateOperational2.getMetadata().put("EventValue", 1);
                    MetricsHelper.recordSingleOccurrence(generateOperational2);
                    this.mDb = recreateDatabase();
                } else {
                    LOG.i("Use super.getWritableDatabase()");
                    char[] key = getKey();
                    try {
                        this.mDb = super.getWritableDatabase(getKey());
                    } catch (SQLiteException e) {
                        LOG.e("Exception while trying to get Writable Database. Recreating DB...", e);
                        CounterMetric generateOperational3 = CounterMetric.generateOperational(SMSMessageContract.DEBUG_DATABASE_GET_WRITABLE_DB_FAIL);
                        generateOperational3.getMetadata().put("errorSource", e.getMessage());
                        generateOperational3.getMetadata().put("source", key);
                        generateOperational3.getMetadata().put("EventValue", 1);
                        MetricsHelper.recordSingleOccurrence(generateOperational3);
                        this.mDb = recreateDatabase();
                    }
                }
            } catch (Exception e2) {
                LOG.e("getWritableDatabase failed.", e2);
            }
        }
        return this.mDb;
    }

    @Override // net.sqlcipher.database.SQLiteOpenHelper
    public void onCreate(@NonNull SQLiteDatabase sQLiteDatabase) {
        LOG.i("Create Comms SMS Database.");
        try {
            sQLiteDatabase.execSQL(SMSMessageContract.CREATE_READ_TABLE);
            CounterMetric generateOperational = CounterMetric.generateOperational(SMSMessageContract.DEBUG_DATABASE_CREATE);
            generateOperational.getMetadata().put("EventValue", 1);
            MetricsHelper.recordSingleOccurrence(generateOperational);
        } catch (SQLException e) {
            LOG.e("CREATE_READ_TABLE query Exception.", e);
            CounterMetric generateOperational2 = CounterMetric.generateOperational(SMSMessageContract.DEBUG_DATABASE_CREATE_FAIL);
            generateOperational2.getMetadata().put("errorSource", e.getMessage());
            generateOperational2.getMetadata().put("EventValue", 1);
            MetricsHelper.recordSingleOccurrence(generateOperational2);
        }
    }

    @Override // net.sqlcipher.database.SQLiteOpenHelper
    public void onUpgrade(@NonNull SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
