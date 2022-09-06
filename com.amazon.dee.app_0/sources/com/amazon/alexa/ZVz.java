package com.amazon.alexa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.VisibleForTesting;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: ExternalCapabilityRegistrationDatabase.java */
@Singleton
/* loaded from: classes.dex */
public class ZVz extends SQLiteOpenHelper {
    @VisibleForTesting
    public static final String BIo = String.format(Locale.US, "CREATE TABLE IF NOT EXISTS %s (%s TEXT PRIMARY KEY,%s TEXT,%s TEXT,%s TEXT,%s TEXT,%s TEXT,%s TEXT)", "externalCapabilitiesRegistration", "capabilityComponentName", "sourcePackageName", "capabilityAgentVersion", "externalCapabilityAgentRegistrationData", "autoUpdatePreference", "registrationStatus", "isFollowing");
    @VisibleForTesting
    public static final String zQM = String.format(Locale.US, "ALTER TABLE %s ADD %s TEXT", "externalCapabilitiesRegistration", "isFollowing");
    public static final String zZm = "com.amazon.alexa.ZVz";

    @Inject
    public ZVz(Context context) {
        super(context, "ExternalCapabilitiesRegistration.db", (SQLiteDatabase.CursorFactory) null, 2);
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
        String str = "Upgrading from version: " + i + " to version: " + i2;
        sQLiteDatabase.beginTransaction();
        if (i == 1) {
            try {
                sQLiteDatabase.execSQL(zQM);
            } finally {
                sQLiteDatabase.endTransaction();
            }
        }
        sQLiteDatabase.setTransactionSuccessful();
    }
}
