package com.amazon.deecomms.common.database;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.util.SharedPreferencesUtils;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteDatabaseHook;
/* loaded from: classes12.dex */
class SQLCipherMigrationHook implements SQLiteDatabaseHook {
    static final String CIPHER_MIGRATE = "PRAGMA cipher_migrate;";
    static final float CURRENT_SQL_CIPHER_VERSION = 4.0f;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, SQLCipherMigrationHook.class);
    private Context context;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SQLCipherMigrationHook(@NonNull Context context) {
        this.context = context;
    }

    private void executeCipherUpgradeQuery(@NonNull SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.rawExecSQL(CIPHER_MIGRATE);
    }

    private boolean isCipherUpgradeCompleted() {
        return SharedPreferencesUtils.getFloatValue(this.context, Constants.LAST_SQLCIPHER_UPGRADED_VERSION, 3.0f) == CURRENT_SQL_CIPHER_VERSION;
    }

    private void persistCipherUpgradeStatus() {
        SharedPreferencesUtils.persistCacheValues(this.context, Constants.LAST_SQLCIPHER_UPGRADED_VERSION, Float.valueOf((float) CURRENT_SQL_CIPHER_VERSION));
    }

    private void recordCipherUpgradeMetric() {
        CounterMetric generateOperational = CounterMetric.generateOperational(MetricKeys.DEBUG_SQLCIPHER_UPGRADE);
        generateOperational.getMetadata().put("errorSource", "Cipher Upgrade");
        generateOperational.getMetadata().put("EventValue", 29);
        MetricsHelper.recordSingleOccurrence(generateOperational);
    }

    @Override // net.sqlcipher.database.SQLiteDatabaseHook
    public void postKey(@NonNull SQLiteDatabase sQLiteDatabase) {
        if (!isCipherUpgradeCompleted()) {
            LOG.i("Executing SQL Cipher migration query");
            recordCipherUpgradeMetric();
            executeCipherUpgradeQuery(sQLiteDatabase);
            persistCipherUpgradeStatus();
            return;
        }
        LOG.i("SQL Cipher migration already complete");
    }

    @Override // net.sqlcipher.database.SQLiteDatabaseHook
    public void preKey(@NonNull SQLiteDatabase sQLiteDatabase) {
    }
}
