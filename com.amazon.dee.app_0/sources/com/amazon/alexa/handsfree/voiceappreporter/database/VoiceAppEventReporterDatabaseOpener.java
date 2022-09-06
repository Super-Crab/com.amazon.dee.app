package com.amazon.alexa.handsfree.voiceappreporter.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.voiceappreporter.VoiceAppEventReporterContract;
/* loaded from: classes8.dex */
final class VoiceAppEventReporterDatabaseOpener extends SQLiteOpenHelper {
    private static final String CREATE_DB_STRING = "CREATE TABLE %s (%s INTEGER PRIMARY KEY CHECK(TYPEOF(%s) = 'integer'),%s TEXT NOT NULL, %s BLOB, %s TEXT,%s INTEGER NOT NULL CHECK(TYPEOF(%s) = 'integer'));";
    private static final int DATABASE_VERSION = 1;
    private static VoiceAppEventReporterDatabaseOpener sInstance;

    private VoiceAppEventReporterDatabaseOpener(@NonNull Context context) {
        super(context, VoiceAppEventReporterContract.VoiceAppEventsTable.DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    private void createVoiceAppEventTable(@NonNull SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format(CREATE_DB_STRING, VoiceAppEventReporterContract.VoiceAppEventsTable.TABLE_NAME, "_id", "_id", VoiceAppEventReporterContract.VoiceAppEventsTable.COLUMN_EVENT_TYPE, VoiceAppEventReporterContract.VoiceAppEventsTable.COLUMN_STACK_TRACE, VoiceAppEventReporterContract.VoiceAppEventsTable.COLUMN_THREAD_DUMP, VoiceAppEventReporterContract.VoiceAppEventsTable.COLUMN_EVENT_DATE, VoiceAppEventReporterContract.VoiceAppEventsTable.COLUMN_EVENT_DATE));
    }

    @NonNull
    public static synchronized VoiceAppEventReporterDatabaseOpener getInstance(@NonNull Context context) {
        VoiceAppEventReporterDatabaseOpener voiceAppEventReporterDatabaseOpener;
        synchronized (VoiceAppEventReporterDatabaseOpener.class) {
            if (sInstance == null) {
                sInstance = new VoiceAppEventReporterDatabaseOpener(context);
            }
            voiceAppEventReporterDatabaseOpener = sInstance;
        }
        return voiceAppEventReporterDatabaseOpener;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(@NonNull SQLiteDatabase sQLiteDatabase) {
        createVoiceAppEventTable(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(@NonNull SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
