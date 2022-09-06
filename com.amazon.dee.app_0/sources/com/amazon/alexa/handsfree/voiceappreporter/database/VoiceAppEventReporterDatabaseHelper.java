package com.amazon.alexa.handsfree.voiceappreporter.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.protocols.voiceappreporter.VoiceAppEventReporterContract;
import com.amazon.alexa.handsfree.voiceappreporter.VoiceAppEvent;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import javax.inject.Inject;
@AhfScope
@WorkerThread
/* loaded from: classes8.dex */
public class VoiceAppEventReporterDatabaseHelper {
    private static final String LIMIT_QUERY_EVENTS = "1";
    private static final Object LOCK = new Object();
    private static final String TAG = "VoiceAppEventReporterDatabaseHelper";
    private boolean hasCursorErrorOccurred;
    private final VoiceAppEventReporterDatabaseOpener mVoiceAppEventReporterDatabaseOpener;

    @Inject
    public VoiceAppEventReporterDatabaseHelper(@NonNull Context context) {
        this(VoiceAppEventReporterDatabaseOpener.getInstance(context));
    }

    private void deleteVoiceEvent(@NonNull SQLiteDatabase sQLiteDatabase, long j) {
        sQLiteDatabase.delete(VoiceAppEventReporterContract.VoiceAppEventsTable.TABLE_NAME, "_id = ?", new String[]{Long.toString(j)});
    }

    @Nullable
    private Throwable deserializeThrowableFromByteArray(@NonNull byte[] bArr) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bArr));
            Throwable th = (Throwable) objectInputStream.readObject();
            objectInputStream.close();
            return th;
        } catch (Exception e) {
            Log.e(TAG, "Error when deserializing exception: ", e, new Object[0]);
            return null;
        }
    }

    @NonNull
    private VoiceAppEvent getVoiceAppEventFromCursor(@NonNull Cursor cursor) {
        String string = cursor.getString(cursor.getColumnIndex(VoiceAppEventReporterContract.VoiceAppEventsTable.COLUMN_EVENT_TYPE));
        return new VoiceAppEvent(string, cursor.getInt(cursor.getColumnIndex(VoiceAppEventReporterContract.VoiceAppEventsTable.COLUMN_EVENT_DATE)), VoiceAppEventReporterContract.VoiceAppEventsTable.EVENT_TYPE_APP_CRASH.equals(string) ? deserializeThrowableFromByteArray(cursor.getBlob(cursor.getColumnIndex(VoiceAppEventReporterContract.VoiceAppEventsTable.COLUMN_STACK_TRACE))) : null);
    }

    private boolean isValidEventType(@Nullable String str) {
        return str != null && (str.equals(VoiceAppEventReporterContract.VoiceAppEventsTable.EVENT_TYPE_APP_CRASH) || str.equals(VoiceAppEventReporterContract.VoiceAppEventsTable.EVENT_TYPE_APP_START));
    }

    private boolean isValidVoiceEvent(@NonNull ContentValues contentValues) {
        String asString = contentValues.getAsString(VoiceAppEventReporterContract.VoiceAppEventsTable.COLUMN_EVENT_TYPE);
        Long asLong = contentValues.getAsLong(VoiceAppEventReporterContract.VoiceAppEventsTable.COLUMN_EVENT_DATE);
        if (isValidEventType(asString) && asLong != null) {
            if (!asString.equals(VoiceAppEventReporterContract.VoiceAppEventsTable.EVENT_TYPE_APP_CRASH) || contentValues.getAsByteArray(VoiceAppEventReporterContract.VoiceAppEventsTable.COLUMN_STACK_TRACE) != null) {
                return true;
            }
            Log.e(TAG, String.format("Voice app crashes require %s", VoiceAppEventReporterContract.VoiceAppEventsTable.COLUMN_STACK_TRACE));
            return false;
        }
        Log.e(TAG, String.format("Voice App Events require a valid %s and %s", VoiceAppEventReporterContract.VoiceAppEventsTable.COLUMN_EVENT_TYPE, VoiceAppEventReporterContract.VoiceAppEventsTable.COLUMN_EVENT_DATE));
        return false;
    }

    @NonNull
    private Cursor queryVoiceEvent(@NonNull SQLiteDatabase sQLiteDatabase) {
        return sQLiteDatabase.query(VoiceAppEventReporterContract.VoiceAppEventsTable.TABLE_NAME, null, null, null, null, null, null, "1");
    }

    public boolean getHasCursorErrorOccurred() {
        return this.hasCursorErrorOccurred;
    }

    public long insertVoiceEvent(@NonNull ContentValues contentValues) {
        if (!isValidVoiceEvent(contentValues)) {
            return 0L;
        }
        SQLiteDatabase writableDatabase = this.mVoiceAppEventReporterDatabaseOpener.getWritableDatabase();
        try {
            long insert = writableDatabase.insert(VoiceAppEventReporterContract.VoiceAppEventsTable.TABLE_NAME, null, contentValues);
            writableDatabase.close();
            return insert;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (writableDatabase != null) {
                    try {
                        writableDatabase.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Nullable
    public VoiceAppEvent popVoiceEvent() {
        this.hasCursorErrorOccurred = false;
        synchronized (LOCK) {
            SQLiteDatabase writableDatabase = this.mVoiceAppEventReporterDatabaseOpener.getWritableDatabase();
            try {
                Cursor queryVoiceEvent = queryVoiceEvent(writableDatabase);
                if (queryVoiceEvent != null) {
                    try {
                        if (!queryVoiceEvent.isClosed()) {
                            if (queryVoiceEvent.getCount() == 0) {
                                queryVoiceEvent.close();
                                writableDatabase.close();
                                return null;
                            }
                            queryVoiceEvent.moveToFirst();
                            VoiceAppEvent voiceAppEventFromCursor = getVoiceAppEventFromCursor(queryVoiceEvent);
                            deleteVoiceEvent(writableDatabase, queryVoiceEvent.getLong(queryVoiceEvent.getColumnIndex("_id")));
                            queryVoiceEvent.close();
                            writableDatabase.close();
                            return voiceAppEventFromCursor;
                        }
                    } finally {
                    }
                }
                Log.d(TAG, "Cursor was null or closed, returning null");
                this.hasCursorErrorOccurred = true;
                if (queryVoiceEvent != null) {
                    queryVoiceEvent.close();
                }
                writableDatabase.close();
                return null;
            } catch (IllegalStateException e) {
                Log.e(TAG, "Error when accessing cursor", e, new Object[0]);
                this.hasCursorErrorOccurred = true;
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
                return null;
            }
        }
    }

    @VisibleForTesting
    VoiceAppEventReporterDatabaseHelper(@NonNull VoiceAppEventReporterDatabaseOpener voiceAppEventReporterDatabaseOpener) {
        this.mVoiceAppEventReporterDatabaseOpener = voiceAppEventReporterDatabaseOpener;
    }
}
