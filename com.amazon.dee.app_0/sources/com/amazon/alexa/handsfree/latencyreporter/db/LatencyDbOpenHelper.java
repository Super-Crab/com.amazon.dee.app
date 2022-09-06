package com.amazon.alexa.handsfree.latencyreporter.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes8.dex */
public class LatencyDbOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "timestamp.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = LatencyDbOpenHelper.class.getSimpleName();
    private final Context mContext;
    private final Initializer mInitializer;
    private final MetricsBuilderProvider mMetricsBuilderProvider;

    public LatencyDbOpenHelper(@NonNull Context context) {
        this(context, InitializerProvider.getInitializer(), MetricsBuilderProvider.getInstance(context));
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(@NonNull SQLiteDatabase sQLiteDatabase) {
        Log.d(TAG, "onCreate: creating database");
        this.mInitializer.initialize(this.mContext);
        MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
        sQLiteDatabase.beginTransaction();
        try {
            try {
                sQLiteDatabase.execSQL(TimestampTable.TABLE_CREATE);
                sQLiteDatabase.setTransactionSuccessful();
                newBuilder.withPercentileMetricSuccess(TAG, LatencyDbMetricType.DATABASE_CREATION.getValue());
            } catch (SQLException e) {
                Log.e(TAG, "onCreate: database creation failed!", e, new Object[0]);
                newBuilder.withPercentileMetricFailure(TAG, LatencyDbMetricType.DATABASE_CREATION.getValue());
            }
        } finally {
            sQLiteDatabase.endTransaction();
            newBuilder.emit(this.mContext);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(@NonNull SQLiteDatabase sQLiteDatabase, int i, int i2) {
        String str = TAG;
        Log.d(str, "Upgrading database from version " + i + " to " + i2);
        this.mInitializer.initialize(this.mContext);
        if (i2 != i) {
            MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
            sQLiteDatabase.beginTransaction();
            try {
                try {
                    sQLiteDatabase.execSQL(TimestampTable.TABLE_DELETE);
                    sQLiteDatabase.setTransactionSuccessful();
                    newBuilder.withPercentileMetricSuccess(TAG, LatencyDbMetricType.DATABASE_UPDATE.getValue());
                } catch (SQLException e) {
                    Log.e(TAG, "onUpgrade: database deletion failed!", e, new Object[0]);
                    newBuilder.withPercentileMetricFailure(TAG, LatencyDbMetricType.DATABASE_UPDATE.getValue());
                }
                onCreate(sQLiteDatabase);
            } finally {
                sQLiteDatabase.endTransaction();
                newBuilder.emit(this.mContext);
            }
        }
    }

    @VisibleForTesting
    LatencyDbOpenHelper(@NonNull Context context, @NonNull Initializer initializer, @NonNull MetricsBuilderProvider metricsBuilderProvider) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
        this.mContext = context;
        this.mInitializer = initializer;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
    }
}
