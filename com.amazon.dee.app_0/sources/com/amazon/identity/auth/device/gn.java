package com.amazon.identity.auth.device;

import android.annotation.TargetApi;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class gn {
    @TargetApi(11)
    public static synchronized SQLiteDatabase a(SQLiteOpenHelper sQLiteOpenHelper) {
        SQLiteDatabase writableDatabase;
        synchronized (gn.class) {
            try {
                writableDatabase = sQLiteOpenHelper.getWritableDatabase();
                io.dm("LocalDataStorageUtils");
            } catch (SQLiteException e) {
                io.e("LocalDataStorageUtils", "Failed to open MAP writable db", e);
                int i = Build.VERSION.SDK_INT;
                if (e instanceof SQLiteDatabaseLockedException) {
                    sQLiteOpenHelper.close();
                    try {
                        SQLiteDatabase writableDatabase2 = sQLiteOpenHelper.getWritableDatabase();
                        io.i("LocalDataStorageUtils", "Successfully open MAP writable db after closing it and retry");
                        mq.incrementCounterAndRecord("OpenMAPDBOnRetrySuccess", new String[0]);
                        return writableDatabase2;
                    } catch (SQLiteDatabaseLockedException e2) {
                        io.e("LocalDataStorageUtils", "Failed open MAP writable db after closing it and retry", e);
                        mq.incrementCounterAndRecord("OpenMAPDBOnRetryFailed", new String[0]);
                        throw e2;
                    }
                }
                throw e;
            }
        }
        return writableDatabase;
    }
}
