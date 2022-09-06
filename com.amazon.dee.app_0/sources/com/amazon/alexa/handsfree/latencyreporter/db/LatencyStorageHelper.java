package com.amazon.alexa.handsfree.latencyreporter.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.amazon.alexa.handsfree.latencyreporter.Latency;
import com.amazon.alexa.handsfree.latencyreporter.TimestampType;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import java.util.NoSuchElementException;
/* loaded from: classes8.dex */
public class LatencyStorageHelper {
    private static final Object STATIC_LOCK = new Object();
    private static final String TAG = "LatencyStorageHelper";
    private final Context mContext;
    private final LatencyDbOpenHelper mLatencyDbOpenHelper;
    private final MetricsBuilderProvider mMetricsBuilderProvider;

    public LatencyStorageHelper(@NonNull Context context) {
        this(context, new LatencyDbOpenHelper(context), MetricsBuilderProvider.getInstance(context));
    }

    synchronized long insertRow(@NonNull ContentValues contentValues) {
        MetricsBuilder metricsBuilder;
        SQLiteDatabase writableDatabase;
        MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
        try {
            writableDatabase = this.mLatencyDbOpenHelper.getWritableDatabase();
            newBuilder.withPercentileMetricSuccess(TAG, LatencyDbMetricType.DATABASE_OPEN.getValue());
            newBuilder.emit(this.mContext);
        } catch (SQLiteException unused) {
            metricsBuilder.withPercentileMetricFailure(TAG, LatencyDbMetricType.DATABASE_OPEN.getValue());
            metricsBuilder.emit(this.mContext);
            return -1L;
        }
        return writableDatabase.insert("timestamp", null, contentValues);
    }

    @WorkerThread
    public void insertTimestamp(@NonNull Latency latency, @Nullable String str, @NonNull TimestampType timestampType, long j) {
        LatencyDbOpenHelper latencyDbOpenHelper;
        synchronized (STATIC_LOCK) {
            String str2 = str == null ? "" : str;
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put(TimestampTable.COLUMN_TIMESTAMP_NAME, latency.name());
                contentValues.put(TimestampTable.COLUMN_TIMESTAMP_IDENTIFIER, str2);
                contentValues.put(TimestampTable.COLUMN_TIMESTAMP_TYPE, timestampType.name());
                contentValues.put("timestamp", Long.valueOf(j));
                MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
                Cursor queryMatchedCursor = queryMatchedCursor(latency, str, timestampType);
                if (queryMatchedCursor != null && queryMatchedCursor.getCount() != 0) {
                    try {
                        queryMatchedCursor.moveToNext();
                        String str3 = TAG;
                        Log.d(str3, "insertTimestamp, timestamp updated for latency: " + latency);
                        int i = queryMatchedCursor.getInt(queryMatchedCursor.getColumnIndex("_id"));
                        newBuilder.withPercentileMetricSuccess(TAG, LatencyDbMetricType.DATA_PARSE.getValue());
                        if (updateRow(i, contentValues) != 1) {
                            newBuilder.withPercentileMetricFailure(TAG, LatencyDbMetricType.DATA_INSERT.getValue());
                        } else {
                            newBuilder.withPercentileMetricSuccess(TAG, LatencyDbMetricType.DATA_INSERT.getValue());
                            newBuilder.withPerformanceMetric(TAG, String.format(LatencyDbMetricType.DATA_OVERRIDE.getValue(), latency.getMetricName()));
                        }
                        newBuilder.emit(this.mContext);
                        queryMatchedCursor.close();
                        latencyDbOpenHelper = this.mLatencyDbOpenHelper;
                    } catch (Exception e) {
                        Log.e(TAG, "insertTimestamp: data parse failed", e, new Object[0]);
                        newBuilder.withPercentileMetricFailure(TAG, LatencyDbMetricType.DATA_PARSE.getValue());
                        newBuilder.emit(this.mContext);
                        queryMatchedCursor.close();
                        latencyDbOpenHelper = this.mLatencyDbOpenHelper;
                    }
                    latencyDbOpenHelper.close();
                    return;
                }
                String str4 = TAG;
                Log.d(str4, "insertTimestamp, timestamp inserted for latency: " + latency);
                if (insertRow(contentValues) == -1) {
                    newBuilder.withPercentileMetricFailure(TAG, LatencyDbMetricType.DATA_INSERT.getValue());
                } else {
                    newBuilder.withPercentileMetricSuccess(TAG, LatencyDbMetricType.DATA_INSERT.getValue());
                }
                newBuilder.emit(this.mContext);
                if (queryMatchedCursor != null) {
                    queryMatchedCursor.close();
                    this.mLatencyDbOpenHelper.close();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @WorkerThread
    public long queryAndRemovePairedTimestamp(@NonNull Latency latency, @Nullable String str, @NonNull TimestampType timestampType) throws NoSuchElementException {
        long j;
        synchronized (STATIC_LOCK) {
            Cursor queryMatchedCursor = queryMatchedCursor(latency, str, timestampType == TimestampType.START ? TimestampType.END : TimestampType.START);
            MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
            if (queryMatchedCursor != null && queryMatchedCursor.getCount() != 0) {
                Latency.TimestampReceiveOrder timestampReceiveOrder = latency.getTimestampReceiveOrder();
                if ((timestampReceiveOrder == Latency.TimestampReceiveOrder.START_BEFORE_END && timestampType == TimestampType.START) || (timestampReceiveOrder == Latency.TimestampReceiveOrder.END_BEFORE_START && timestampType == TimestampType.END)) {
                    Log.i(TAG, "queryAndRemovePairedTimestamp: paired timestamp has not been received yet.");
                    queryMatchedCursor.moveToNext();
                    removeRow(queryMatchedCursor);
                    throw new NoSuchElementException();
                }
                try {
                    queryMatchedCursor.moveToNext();
                    j = queryMatchedCursor.getLong(queryMatchedCursor.getColumnIndex("timestamp"));
                    newBuilder.withPercentileMetricSuccess(TAG, LatencyDbMetricType.DATA_PARSE.getValue());
                    removeRow(queryMatchedCursor);
                    queryMatchedCursor.close();
                    newBuilder.emit(this.mContext);
                    this.mLatencyDbOpenHelper.close();
                } catch (Exception e) {
                    Log.e(TAG, "insertTimestamp: data parse failed", e, new Object[0]);
                    newBuilder.withPercentileMetricFailure(TAG, LatencyDbMetricType.DATA_PARSE.getValue());
                    throw new NoSuchElementException();
                }
            }
            String str2 = TAG;
            Log.i(str2, "queryAndRemovePairedTimestamp: No paired start timestamp for latency: " + latency);
            if (queryMatchedCursor != null) {
                queryMatchedCursor.close();
                this.mLatencyDbOpenHelper.close();
            }
            throw new NoSuchElementException();
        }
        return j;
    }

    @Nullable
    @VisibleForTesting
    Cursor queryAscendingTimestampCursor(@NonNull Latency latency) {
        MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
        try {
            SQLiteDatabase readableDatabase = this.mLatencyDbOpenHelper.getReadableDatabase();
            newBuilder.withPercentileMetricSuccess(TAG, LatencyDbMetricType.DATABASE_OPEN.getValue());
            newBuilder.emit(this.mContext);
            return readableDatabase.query("timestamp", null, "timestamp_name='" + latency.name() + "'", null, null, null, "timestamp ASC");
        } catch (SQLiteException unused) {
            newBuilder.withPercentileMetricFailure(TAG, LatencyDbMetricType.DATABASE_OPEN.getValue());
            newBuilder.emit(this.mContext);
            return null;
        }
    }

    @Nullable
    @VisibleForTesting
    Cursor queryMatchedCursor(@NonNull Latency latency, @Nullable String str, @NonNull TimestampType timestampType) {
        MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
        try {
            SQLiteDatabase readableDatabase = this.mLatencyDbOpenHelper.getReadableDatabase();
            newBuilder.withPercentileMetricSuccess(TAG, LatencyDbMetricType.DATABASE_OPEN.getValue());
            newBuilder.emit(this.mContext);
            if (str == null) {
                str = "";
            }
            return readableDatabase.query("timestamp", null, "timestamp_name='" + latency.name() + "' AND " + TimestampTable.COLUMN_TIMESTAMP_IDENTIFIER + "='" + str + "' AND " + TimestampTable.COLUMN_TIMESTAMP_TYPE + "='" + timestampType.name() + "'", null, null, null, null);
        } catch (SQLiteException unused) {
            newBuilder.withPercentileMetricFailure(TAG, LatencyDbMetricType.DATABASE_OPEN.getValue());
            newBuilder.emit(this.mContext);
            return null;
        }
    }

    @WorkerThread
    public void removeExpiredTimestamp(@NonNull Latency latency, long j) {
        synchronized (STATIC_LOCK) {
            Cursor queryAscendingTimestampCursor = queryAscendingTimestampCursor(latency);
            MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
            if (queryAscendingTimestampCursor != null && queryAscendingTimestampCursor.getCount() != 0) {
                while (true) {
                    try {
                        if (!queryAscendingTimestampCursor.moveToNext()) {
                            break;
                        }
                        long j2 = queryAscendingTimestampCursor.getLong(queryAscendingTimestampCursor.getColumnIndex("timestamp"));
                        newBuilder.withPercentileMetricSuccess(TAG, LatencyDbMetricType.DATA_PARSE.getValue());
                        if (j - j2 < latency.getTimeout()) {
                            Log.d(TAG, "removeExpiredTimestamp: all expired start time removed.");
                            break;
                        }
                        String str = TAG;
                        Log.d(str, "removeExpiredTimestamp: expired start timestamp removed for " + latency);
                        if (removeRow(queryAscendingTimestampCursor) == 1) {
                            newBuilder.withPerformanceMetric(TAG, String.format(LatencyDbMetricType.DATA_EXPIRE_REMOVED.getValue(), latency.getMetricName()));
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "insertTimestamp: data parse failed", e, new Object[0]);
                        newBuilder.withPercentileMetricFailure(TAG, LatencyDbMetricType.DATA_PARSE.getValue());
                        newBuilder.emit(this.mContext);
                        throw new NoSuchElementException();
                    }
                }
                newBuilder.emit(this.mContext);
                queryAscendingTimestampCursor.close();
                this.mLatencyDbOpenHelper.close();
                return;
            }
            Log.d(TAG, "removeExpiredTimestamp: no record, skip.");
            if (queryAscendingTimestampCursor != null) {
                queryAscendingTimestampCursor.close();
                this.mLatencyDbOpenHelper.close();
            }
        }
    }

    @VisibleForTesting
    int removeRow(@NonNull Cursor cursor) {
        MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
        try {
            SQLiteDatabase writableDatabase = this.mLatencyDbOpenHelper.getWritableDatabase();
            newBuilder.withPercentileMetricSuccess(TAG, LatencyDbMetricType.DATABASE_OPEN.getValue());
            int i = cursor.getInt(cursor.getColumnIndex("_id"));
            int delete = writableDatabase.delete("timestamp", "_id=" + i, null);
            if (delete != 1) {
                newBuilder.withPercentileMetricFailure(TAG, LatencyDbMetricType.DATA_DELETE.getValue());
            } else {
                newBuilder.withPercentileMetricSuccess(TAG, LatencyDbMetricType.DATA_DELETE.getValue());
            }
            newBuilder.emit(this.mContext);
            return delete;
        } catch (SQLiteException unused) {
            newBuilder.withPercentileMetricFailure(TAG, LatencyDbMetricType.DATABASE_OPEN.getValue());
            newBuilder.emit(this.mContext);
            return 0;
        }
    }

    int updateRow(int i, @NonNull ContentValues contentValues) {
        MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
        try {
            SQLiteDatabase writableDatabase = this.mLatencyDbOpenHelper.getWritableDatabase();
            newBuilder.withPercentileMetricSuccess(TAG, LatencyDbMetricType.DATABASE_OPEN.getValue());
            newBuilder.emit(this.mContext);
            return writableDatabase.update("timestamp", contentValues, "_id=" + i, null);
        } catch (SQLiteException unused) {
            newBuilder.withPercentileMetricFailure(TAG, LatencyDbMetricType.DATABASE_OPEN.getValue());
            newBuilder.emit(this.mContext);
            return -1;
        }
    }

    @VisibleForTesting
    LatencyStorageHelper(@NonNull Context context, @NonNull LatencyDbOpenHelper latencyDbOpenHelper, @NonNull MetricsBuilderProvider metricsBuilderProvider) {
        this.mContext = context;
        this.mLatencyDbOpenHelper = latencyDbOpenHelper;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
    }
}
