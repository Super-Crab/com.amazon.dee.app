package com.amazon.alexa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import java.util.Locale;
/* compiled from: ClientMetricDatabase.java */
/* loaded from: classes.dex */
public class pOk extends SQLiteOpenHelper {
    public static volatile pOk BIo = null;
    public static final String zZm = "pOk";
    @VisibleForTesting
    public static final String zQM = String.format(Locale.US, "CREATE TABLE IF NOT EXISTS %s (%s TEXT PRIMARY KEY,%s TEXT,%s TEXT,%s TEXT,%s TEXT,%s TEXT,%s TEXT,%s TEXT,%s DATETIME)", "clientMetrics", "eventId", JsonFields.EVENT_NAME, "sourcePackageName", "clientPackageName", AlexaMetricsConstants.EventConstants.USER_SPEECH_INVOCATION_TYPE, AccessoryMetricsConstants.DIALOG_TURN_ID, "softwareVersion", "apiCallId", JsonFields.EVENT_TIMESTAMP);
    @VisibleForTesting
    public static final String zyO = String.format(Locale.US, "CREATE TABLE IF NOT EXISTS %s (%s TEXT PRIMARY KEY,%s TEXT,%s TEXT,%s TEXT,%s TEXT,%s TEXT,%s TEXT,%s TEXT,%s DATETIME)", "textClientMetrics", "eventId", JsonFields.EVENT_NAME, "sourcePackageName", "clientPackageName", AlexaMetricsConstants.EventConstants.USER_SPEECH_INVOCATION_TYPE, AccessoryMetricsConstants.DIALOG_TURN_ID, "softwareVersion", "apiCallId", JsonFields.EVENT_TIMESTAMP);
    @VisibleForTesting
    public static final String jiA = String.format(Locale.US, "CREATE TABLE IF NOT EXISTS %s (%s TEXT PRIMARY KEY,%s TEXT,%s TEXT,%s TEXT,%s TEXT,%s INT,%s DATETIME)", "genericClientMetrics", "eventId", JsonFields.EVENT_NAME, "sourcePackageName", "clientPackageName", "softwareVersion", "latency", JsonFields.EVENT_TIMESTAMP);
    @VisibleForTesting
    public static final String Qle = String.format(Locale.US, "ALTER TABLE %s ADD %s TEXT", "clientMetrics", AccessoryMetricsConstants.DIALOG_TURN_ID);
    @VisibleForTesting
    public static final String JTe = String.format(Locale.US, "ALTER TABLE %s ADD %s TEXT", "clientMetrics", "softwareVersion");
    @VisibleForTesting
    public static final String LPk = String.format(Locale.US, "ALTER TABLE %s ADD %s TEXT", "genericClientMetrics", "apiCallId");
    @VisibleForTesting
    public static final String yPL = String.format(Locale.US, "ALTER TABLE %s ADD %s TEXT", "clientMetrics", "apiCallId");

    public pOk(Context context) {
        super(context, "ClientMetrics.db", (SQLiteDatabase.CursorFactory) null, 6);
    }

    public static pOk zZm(Context context) {
        if (BIo == null) {
            synchronized (pOk.class) {
                if (BIo == null) {
                    BIo = new pOk(context);
                }
            }
        }
        return BIo;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.beginTransaction();
        try {
            sQLiteDatabase.execSQL(zQM);
            sQLiteDatabase.execSQL(jiA);
            sQLiteDatabase.execSQL(zyO);
            sQLiteDatabase.execSQL(LPk);
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0028, code lost:
        if (r4 != 5) goto L12;
     */
    @Override // android.database.sqlite.SQLiteOpenHelper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onUpgrade(android.database.sqlite.SQLiteDatabase r3, int r4, int r5) {
        /*
            r2 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Upgrading from version "
            r0.append(r1)
            r0.append(r4)
            java.lang.String r1 = " to version: "
            r0.append(r1)
            r0.append(r5)
            r0.toString()
            r3.beginTransaction()
            r5 = 1
            if (r4 == r5) goto L2b
            r5 = 2
            if (r4 == r5) goto L30
            r5 = 3
            if (r4 == r5) goto L35
            r5 = 4
            if (r4 == r5) goto L3a
            r5 = 5
            if (r4 == r5) goto L44
            goto L49
        L2b:
            java.lang.String r4 = com.amazon.alexa.pOk.Qle     // Catch: java.lang.Throwable -> L50
            r3.execSQL(r4)     // Catch: java.lang.Throwable -> L50
        L30:
            java.lang.String r4 = com.amazon.alexa.pOk.JTe     // Catch: java.lang.Throwable -> L50
            r3.execSQL(r4)     // Catch: java.lang.Throwable -> L50
        L35:
            java.lang.String r4 = com.amazon.alexa.pOk.jiA     // Catch: java.lang.Throwable -> L50
            r3.execSQL(r4)     // Catch: java.lang.Throwable -> L50
        L3a:
            java.lang.String r4 = com.amazon.alexa.pOk.LPk     // Catch: java.lang.Throwable -> L50
            r3.execSQL(r4)     // Catch: java.lang.Throwable -> L50
            java.lang.String r4 = com.amazon.alexa.pOk.yPL     // Catch: java.lang.Throwable -> L50
            r3.execSQL(r4)     // Catch: java.lang.Throwable -> L50
        L44:
            java.lang.String r4 = com.amazon.alexa.pOk.zyO     // Catch: java.lang.Throwable -> L50
            r3.execSQL(r4)     // Catch: java.lang.Throwable -> L50
        L49:
            r3.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L50
            r3.endTransaction()
            return
        L50:
            r4 = move-exception
            r3.endTransaction()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.pOk.onUpgrade(android.database.sqlite.SQLiteDatabase, int, int):void");
    }
}
