package com.amazon.deecomms.smsmessaging.database;

import android.content.ContentValues;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import net.sqlcipher.SQLException;
import net.sqlcipher.database.SQLiteDatabase;
/* loaded from: classes12.dex */
public class SMSDatabaseUtils {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, SMSDatabaseUtils.class);
    SMSDatabaseManager mSMSDatabaseManager;

    public SMSDatabaseUtils(@NonNull SMSDatabaseManager sMSDatabaseManager) {
        this.mSMSDatabaseManager = sMSDatabaseManager;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x005e, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized java.util.Set<java.lang.String> getAllMessageIds(@androidx.annotation.NonNull java.lang.String r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            java.util.HashSet r0 = new java.util.HashSet     // Catch: java.lang.Throwable -> L73
            r0.<init>()     // Catch: java.lang.Throwable -> L73
            java.lang.String r1 = "SELECT * FROM "
            java.lang.String r6 = com.android.tools.r8.GeneratedOutlineSupport.outline0(r1, r6)     // Catch: java.lang.Throwable -> L73
            r1 = 0
            com.amazon.deecomms.smsmessaging.database.SMSDatabaseManager r2 = r5.mSMSDatabaseManager     // Catch: java.lang.Throwable -> L66
            net.sqlcipher.database.SQLiteDatabase r2 = r2.getWritableDatabase()     // Catch: java.lang.Throwable -> L66
            net.sqlcipher.Cursor r1 = r2.rawQuery(r6, r1)     // Catch: java.lang.Throwable -> L5a
            if (r1 == 0) goto L5c
            int r6 = r1.getCount()     // Catch: java.lang.Throwable -> L5a
            if (r6 != 0) goto L20
            goto L5c
        L20:
            boolean r6 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L5a
            if (r6 == 0) goto L52
        L26:
            java.lang.String r6 = "messageId"
            int r6 = r1.getColumnIndex(r6)     // Catch: java.lang.Throwable -> L5a
            java.lang.String r6 = r1.getString(r6)     // Catch: java.lang.Throwable -> L5a
            java.lang.String r3 = "messageType"
            int r3 = r1.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L5a
            java.lang.String r3 = r1.getString(r3)     // Catch: java.lang.Throwable -> L5a
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5a
            r4.<init>()     // Catch: java.lang.Throwable -> L5a
            r4.append(r3)     // Catch: java.lang.Throwable -> L5a
            r4.append(r6)     // Catch: java.lang.Throwable -> L5a
            java.lang.String r6 = r4.toString()     // Catch: java.lang.Throwable -> L5a
            r0.add(r6)     // Catch: java.lang.Throwable -> L5a
            boolean r6 = r1.moveToNext()     // Catch: java.lang.Throwable -> L5a
            if (r6 != 0) goto L26
        L52:
            r1.close()     // Catch: java.lang.Throwable -> L73
            r2.close()     // Catch: java.lang.Throwable -> L73
            monitor-exit(r5)
            return r0
        L5a:
            r6 = move-exception
            goto L68
        L5c:
            if (r1 == 0) goto L61
            r1.close()     // Catch: java.lang.Throwable -> L73
        L61:
            r2.close()     // Catch: java.lang.Throwable -> L73
            monitor-exit(r5)
            return r0
        L66:
            r6 = move-exception
            r2 = r1
        L68:
            if (r1 == 0) goto L6d
            r1.close()     // Catch: java.lang.Throwable -> L73
        L6d:
            if (r2 == 0) goto L72
            r2.close()     // Catch: java.lang.Throwable -> L73
        L72:
            throw r6     // Catch: java.lang.Throwable -> L73
        L73:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.deecomms.smsmessaging.database.SMSDatabaseUtils.getAllMessageIds(java.lang.String):java.util.Set");
    }

    public synchronized long insertMessage(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        long insertWithOnConflict;
        SQLiteDatabase writableDatabase = this.mSMSDatabaseManager.getWritableDatabase();
        writableDatabase.beginTransaction();
        ContentValues contentValues = new ContentValues();
        contentValues.put("messageId", str3);
        contentValues.put("messageType", str2);
        insertWithOnConflict = writableDatabase.insertWithOnConflict(str, null, contentValues, 5);
        writableDatabase.setTransactionSuccessful();
        writableDatabase.endTransaction();
        writableDatabase.close();
        return insertWithOnConflict;
    }

    public synchronized void pruningData() {
        try {
            this.mSMSDatabaseManager.getWritableDatabase().execSQL(SMSMessageContract.PRUNE_READ_TABLE);
        } catch (SQLException e) {
            LOG.e("PRUNE_READ_TABLE query SQLException.", e);
            CounterMetric generateOperational = CounterMetric.generateOperational(SMSMessageContract.DEBUG_DATABASE_PRUNE_FAIL);
            generateOperational.getMetadata().put("errorSource", e.getMessage());
            generateOperational.getMetadata().put("EventValue", 1);
            MetricsHelper.recordSingleOccurrence(generateOperational);
        }
    }
}
