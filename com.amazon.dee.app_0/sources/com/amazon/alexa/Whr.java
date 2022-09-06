package com.amazon.alexa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.VisibleForTesting;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: ExternalComponentStateDatabase.java */
@Singleton
/* loaded from: classes.dex */
public class Whr extends SQLiteOpenHelper {
    @VisibleForTesting
    public static final String zZm = String.format("CREATE TABLE %s (%s TEXT,%s TEXT,%s TEXT,%s TEXT,%s TEXT,%s DATETIME DEFAULT CURRENT_TIMESTAMP,PRIMARY KEY(%s, %s, %s))", "externalComponentState", "namespace", "name", "payload", CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME, "packageVersionCode", "dateUpdated", "namespace", "name", CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME);
    @VisibleForTesting
    public static final String BIo = String.format("CREATE TABLE %s (%s TEXT,%s TEXT,%s DATETIME DEFAULT CURRENT_TIMESTAMP,PRIMARY KEY(%s))", "externalComponentStateCachingPreference", "namespace", "isCachingEnabled", "dateUpdated", "namespace");

    @Inject
    public Whr(Context context) {
        super(context, "ExternalComponentState.db", (SQLiteDatabase.CursorFactory) null, 2);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.beginTransaction();
        sQLiteDatabase.execSQL(zZm);
        sQLiteDatabase.execSQL(BIo);
        sQLiteDatabase.setTransactionSuccessful();
        sQLiteDatabase.endTransaction();
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
