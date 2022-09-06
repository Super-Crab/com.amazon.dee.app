package com.amazon.alexa.handsfree.uservoicerecognition.edgesv.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
/* loaded from: classes8.dex */
public class EnrollmentStateCache {
    private static final String ENROLLMENT_SHARED_PREFERENCES_FILE_NAME = "com.amazon.alexa.handsfree.uservoicerecognition.Enrollment";
    private static final String KEY_ENROLLMENT_STATE = "enrollment_state";
    private static final String KEY_LAST_UPDATED_TIME = "last_updated_time";
    private static final String TAG = "EnrollmentStateCache";
    private final SharedPreferences mSharedPreferences;
    private final TimeProvider mTimeProvider;

    @VisibleForTesting
    /* loaded from: classes8.dex */
    static class TimeProvider {
        TimeProvider() {
        }

        @VisibleForTesting
        long getElapsedRealTime() {
            return SystemClock.elapsedRealtime();
        }
    }

    public EnrollmentStateCache(Context context) {
        this(context.getSharedPreferences(ENROLLMENT_SHARED_PREFERENCES_FILE_NAME, 0), new TimeProvider());
    }

    private long getLastUpdatedTime() {
        return this.mSharedPreferences.getLong(KEY_LAST_UPDATED_TIME, 0L);
    }

    public boolean getEnrollmentState() {
        return this.mSharedPreferences.getBoolean(KEY_ENROLLMENT_STATE, false);
    }

    public boolean isCacheExpired(long j) {
        long lastUpdatedTime = getLastUpdatedTime();
        if (lastUpdatedTime == 0) {
            return true;
        }
        long elapsedRealTime = this.mTimeProvider.getElapsedRealTime();
        return elapsedRealTime < lastUpdatedTime || elapsedRealTime - lastUpdatedTime > j;
    }

    public void resetCache() {
        this.mSharedPreferences.edit().clear().apply();
    }

    public void setEnrollmentState(boolean z) {
        this.mSharedPreferences.edit().putBoolean(KEY_ENROLLMENT_STATE, z).putLong(KEY_LAST_UPDATED_TIME, this.mTimeProvider.getElapsedRealTime()).apply();
    }

    @VisibleForTesting
    EnrollmentStateCache(SharedPreferences sharedPreferences, TimeProvider timeProvider) {
        this.mSharedPreferences = sharedPreferences;
        this.mTimeProvider = timeProvider;
    }
}
