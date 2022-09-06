package com.amazon.alexa.alertsca;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.VisibleForTesting;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes6.dex */
public class AlertsDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "AlertRecords.db";
    public static final int DATABASE_VERSION = 4;
    private static final String SQL_ALTER_ALERT_TABLE = "ALTER TABLE alertsTable ADD COLUMN assetInfoExists TEXT;";
    private static final String SQL_ALTER_ASSET_INFO_TABLE_TO_V2 = "ALTER TABLE assetInfoTable ADD COLUMN label TEXT;";
    @VisibleForTesting
    static final String SQL_CREATE_ALERTS_TABLE = "CREATE TABLE alertsTable (token TEXT PRIMARY KEY,type TEXT,scheduledTime TEXT,assetInfoExists TEXT)";
    @VisibleForTesting
    static final String SQL_CREATE_ASSET_INFO_TABLE_V1 = "CREATE TABLE assetInfoTable (token TEXT PRIMARY KEY,backgroundAlertAsset TEXT,loopCount TEXT,loopPauseInMilliseconds TEXT,foregroundAlertAsset TEXT)";
    @VisibleForTesting
    static final String SQL_CREATE_ASSET_INFO_TABLE_V2 = "CREATE TABLE assetInfoTable (token TEXT PRIMARY KEY,backgroundAlertAsset TEXT,loopCount TEXT,loopPauseInMilliseconds TEXT,label TEXT,foregroundAlertAsset TEXT)";
    @VisibleForTesting
    static final String SQL_DELETE_ALERTS_TABLE = "DROP TABLE IF EXISTS alertsTable";
    @VisibleForTesting
    static final String SQL_DELETE_ASSET_INFO_TABLE = "DROP TABLE IF EXISTS assetInfoTable";
    private static final String TAG = AlertsDatabaseHelper.class.getSimpleName();

    @Inject
    public AlertsDatabaseHelper(Context context) {
        super(context.getApplicationContext(), DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 4);
    }

    @VisibleForTesting
    boolean isColumnPresent(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        boolean z = true;
        try {
            Cursor query = sQLiteDatabase.query(str, new String[]{str2}, null, null, null, null, null);
            if (query.getColumnIndex(str2) == -1) {
                z = false;
            }
            query.close();
            return z;
        } catch (SQLException unused) {
            return false;
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.beginTransaction();
        try {
            sQLiteDatabase.execSQL(SQL_CREATE_ALERTS_TABLE);
            sQLiteDatabase.execSQL(SQL_CREATE_ASSET_INFO_TABLE_V2);
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002d A[Catch: all -> 0x0037, TryCatch #0 {all -> 0x0037, blocks: (B:8:0x000e, B:14:0x0030, B:11:0x0023, B:13:0x002d, B:10:0x0020, B:9:0x0016), top: B:20:0x0006 }] */
    @Override // android.database.sqlite.SQLiteOpenHelper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onUpgrade(android.database.sqlite.SQLiteDatabase r2, int r3, int r4) {
        /*
            r1 = this;
            r2.beginTransaction()
            r4 = 1
            java.lang.String r0 = "ALTER TABLE assetInfoTable ADD COLUMN label TEXT;"
            if (r3 == r4) goto L16
            r4 = 2
            if (r3 == r4) goto L20
            r4 = 3
            if (r3 == r4) goto L23
            java.lang.String r3 = com.amazon.alexa.alertsca.AlertsDatabaseHelper.TAG     // Catch: java.lang.Throwable -> L37
            java.lang.String r4 = "Not a proper version"
            android.util.Log.w(r3, r4)     // Catch: java.lang.Throwable -> L37
            goto L30
        L16:
            java.lang.String r3 = "ALTER TABLE alertsTable ADD COLUMN assetInfoExists TEXT;"
            r2.execSQL(r3)     // Catch: java.lang.Throwable -> L37
            java.lang.String r3 = "CREATE TABLE assetInfoTable (token TEXT PRIMARY KEY,backgroundAlertAsset TEXT,loopCount TEXT,loopPauseInMilliseconds TEXT,foregroundAlertAsset TEXT)"
            r2.execSQL(r3)     // Catch: java.lang.Throwable -> L37
        L20:
            r2.execSQL(r0)     // Catch: java.lang.Throwable -> L37
        L23:
            java.lang.String r3 = "assetInfoTable"
            java.lang.String r4 = "label"
            boolean r3 = r1.isColumnPresent(r2, r3, r4)     // Catch: java.lang.Throwable -> L37
            if (r3 != 0) goto L30
            r2.execSQL(r0)     // Catch: java.lang.Throwable -> L37
        L30:
            r2.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L37
            r2.endTransaction()
            return
        L37:
            r3 = move-exception
            r2.endTransaction()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.alertsca.AlertsDatabaseHelper.onUpgrade(android.database.sqlite.SQLiteDatabase, int, int):void");
    }
}
