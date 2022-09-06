package com.amazon.alexa.handsfree.storage.initialization;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
/* loaded from: classes8.dex */
public class AppInitializationStatusStore {
    @VisibleForTesting
    static final String APP_VERSION_KEY = "com.amazon.alexa.handsfree.metrics.app_version";
    @VisibleForTesting
    static final String FIRST_STARTUP_METRIC_COUNT_KEY = "com.amazon.alexa.handsfree.metrics.first_startup_metric_count";
    @VisibleForTesting
    static final String INITIALIZATION_PREFERENCE_KEY = "com.amazon.alexa.handsfree.metrics.initialization";
    @VisibleForTesting
    static final String LATEST_FRO_EMISSION_TIME_KEY = "com.amazon.alexa.handsfree.metrics.last_fro_emission_time";
    @VisibleForTesting
    static final String SHARED_PREFERENCES_FILE = "com.amazon.alexa.handsfree.metrics.initialization.AppInitializationStatusStore";

    public synchronized int getAppVersionCode(@NonNull Context context) {
        return getSharedPreferences(context).getInt(APP_VERSION_KEY, 0);
    }

    public synchronized int getFirstStartupMetricCount(@NonNull Context context) {
        return getSharedPreferences(context).getInt(FIRST_STARTUP_METRIC_COUNT_KEY, 0);
    }

    public synchronized long getFirstStartupMetricEmissionTime(@NonNull Context context) {
        return getSharedPreferences(context).getLong(LATEST_FRO_EMISSION_TIME_KEY, 0L);
    }

    @VisibleForTesting
    SharedPreferences getSharedPreferences(@NonNull Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_FILE, 0);
    }

    public synchronized boolean isInitialized(@NonNull Context context) {
        return getSharedPreferences(context).getBoolean(INITIALIZATION_PREFERENCE_KEY, false);
    }

    public synchronized void setAppVersionCode(@NonNull Context context, int i) {
        getSharedPreferences(context).edit().putInt(APP_VERSION_KEY, i).apply();
    }

    public synchronized void setFirstStartupMetricCount(@NonNull Context context, int i) {
        getSharedPreferences(context).edit().putInt(FIRST_STARTUP_METRIC_COUNT_KEY, i).apply();
    }

    public synchronized void setFirstStartupMetricEmissionTime(@NonNull Context context, long j) {
        getSharedPreferences(context).edit().putLong(LATEST_FRO_EMISSION_TIME_KEY, j).apply();
    }

    public synchronized void setInitialized(@NonNull Context context, boolean z) {
        getSharedPreferences(context).edit().putBoolean(INITIALIZATION_PREFERENCE_KEY, z).apply();
    }
}
