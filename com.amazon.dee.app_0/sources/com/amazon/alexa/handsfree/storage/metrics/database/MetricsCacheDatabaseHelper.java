package com.amazon.alexa.handsfree.storage.metrics.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.storage.metrics.database.MetricsCacheDatabaseContract;
import com.google.android.exoplayer2.upstream.cache.CacheDataSink;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
@AhfScope
/* loaded from: classes8.dex */
public class MetricsCacheDatabaseHelper extends SQLiteOpenHelper {
    @VisibleForTesting
    static final String DATABASE_NAME = "metricsCache.db";
    private static final int DATABASE_VERSION = 2;
    private static final int MAX_DB_SIZE = 5242880;
    private static final String TAG = "MetricsCacheDBHlpr";
    private final Context mContext;
    private static final String CREATE_QUERY = "CREATE TABLE  %s ( %s  INTEGER PRIMARY KEY AUTOINCREMENT , %s TEXT NOT NULL )";
    private static final String SQL_CREATE_TABLE_CACHED_METRICS = String.format(CREATE_QUERY, MetricsCacheDatabaseContract.CachedMetricsTable.TABLE_NAME, "_id", MetricsCacheDatabaseContract.CachedMetricsTable.COLUMN_METRIC_LIST_JSON);

    @Inject
    public MetricsCacheDatabaseHelper(@NonNull Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 2);
        this.mContext = context;
    }

    private ContentValues getContentValuesFromMetricList(@NonNull String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MetricsCacheDatabaseContract.CachedMetricsTable.COLUMN_METRIC_LIST_JSON, str);
        return contentValues;
    }

    @WorkerThread
    public synchronized void clearDatabase() {
        SQLiteDatabase writableDatabase;
        try {
            writableDatabase = getWritableDatabase();
        } catch (SQLException e) {
            Log.e(TAG, "Error while getting writable database: " + e.getMessage());
        }
        try {
            clearDatabase(writableDatabase);
            writableDatabase.close();
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

    @NonNull
    @WorkerThread
    public synchronized List<String> getCachedMetricGroups() {
        try {
            SQLiteDatabase readableDatabase = getReadableDatabase();
            try {
                Cursor query = readableDatabase.query(MetricsCacheDatabaseContract.CachedMetricsTable.TABLE_NAME, null, null, null, null, null, null);
                if (query != null && query.getCount() > 0) {
                    ArrayList arrayList = new ArrayList();
                    while (query.moveToNext()) {
                        arrayList.add(query.getString(query.getColumnIndex(MetricsCacheDatabaseContract.CachedMetricsTable.COLUMN_METRIC_LIST_JSON)));
                    }
                    query.close();
                    readableDatabase.close();
                    return arrayList;
                }
                List<String> emptyList = Collections.emptyList();
                if (query != null) {
                    query.close();
                }
                readableDatabase.close();
                return emptyList;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (readableDatabase != null) {
                        try {
                            readableDatabase.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        } catch (SQLException e) {
            Log.e(TAG, "Error while getting readable database: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onConfigure(SQLiteDatabase sQLiteDatabase) {
        Log.d(TAG, "onConfigure(...)");
        sQLiteDatabase.setMaximumSize(CacheDataSink.DEFAULT_FRAGMENT_SIZE);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(@NonNull SQLiteDatabase sQLiteDatabase) {
        Log.d(TAG, "onCreate(...)");
        sQLiteDatabase.beginTransaction();
        try {
            sQLiteDatabase.execSQL(SQL_CREATE_TABLE_CACHED_METRICS);
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(@NonNull SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Log.d(TAG, "onUpgrade(...)");
        File databasePath = this.mContext.getDatabasePath(DATABASE_NAME);
        if (i != 1 || databasePath.length() <= CacheDataSink.DEFAULT_FRAGMENT_SIZE) {
            return;
        }
        clearDatabase(sQLiteDatabase);
    }

    @WorkerThread
    public synchronized void putMetricGroupIntoCache(@NonNull String str) {
        SQLiteDatabase writableDatabase;
        try {
            writableDatabase = getWritableDatabase();
        } catch (SQLException e) {
            Log.e(TAG, "Error while getting writable database: " + e.getMessage());
        }
        try {
            Log.d(TAG, "insertMetricGroup(...)");
            writableDatabase.insert(MetricsCacheDatabaseContract.CachedMetricsTable.TABLE_NAME, null, getContentValuesFromMetricList(str));
            writableDatabase.close();
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

    private synchronized void clearDatabase(SQLiteDatabase sQLiteDatabase) {
        Log.d(TAG, "clearDatabase(...)");
        sQLiteDatabase.delete(MetricsCacheDatabaseContract.CachedMetricsTable.TABLE_NAME, null, null);
    }
}
