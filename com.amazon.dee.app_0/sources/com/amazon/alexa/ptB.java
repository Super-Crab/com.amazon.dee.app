package com.amazon.alexa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.VisibleForTesting;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: AlexaEventDatabase.java */
@Singleton
/* loaded from: classes.dex */
public class ptB extends SQLiteOpenHelper {
    @VisibleForTesting
    public static final String BIo = String.format(Locale.US, "CREATE TABLE %s (%s TEXT PRIMARY KEY,%s TEXT,%s TEXT)", "alexaEvents", "requestId", "eventHeader", "eventPayload");
    public static final String zZm = "ptB";

    @Inject
    public ptB(Context context) {
        super(context, "AlexaEvents.db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.beginTransaction();
        try {
            sQLiteDatabase.execSQL(BIo);
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        String str = "Upgrading from version " + i + " to version:" + i2;
        sQLiteDatabase.beginTransaction();
        try {
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }
}
