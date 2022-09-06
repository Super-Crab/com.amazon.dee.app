package com.amazon.alexa.handsfree.settings;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.settings.metrics.MetricType;
import java.util.Locale;
import javax.inject.Inject;
@AhfScope
/* loaded from: classes8.dex */
public class SettingsChangeRecorder {
    @VisibleForTesting
    static final String SET_HANDS_FREE_STATE_NAME = "setHandsFreeState";
    static final String SHARED_PREFS_FILE = "com.amazon.alexa.handsfree.wakewordsettings";
    static final String SHARED_PREFS_WAKEWORD_KEY = "ahf_disable_source";
    private static final String TAG = "SettingsChangeRecorder";
    private Context mContext;
    private MetricsBuilderProvider mMetricsBuilderProvider;
    private SharedPreferences mSharedPreferences;

    @Inject
    public SettingsChangeRecorder(Context context, MetricsBuilderProvider metricsBuilderProvider) {
        this.mContext = context;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mSharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, 0);
    }

    public synchronized void disableWakeWordSharedPreferences(String str) {
        String str2 = TAG;
        Log.d(str2, "Calling disable with " + str);
        if (!this.mSharedPreferences.contains(SHARED_PREFS_WAKEWORD_KEY)) {
            this.mSharedPreferences.edit().putString(SHARED_PREFS_WAKEWORD_KEY, str).apply();
            MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
            if (str != null) {
                newBuilder.withPerformanceMetric(TAG, String.format(Locale.US, MetricType.HANDS_FREE_NOT_ENABLED_WITH_CALLER.getValue(), SET_HANDS_FREE_STATE_NAME, str));
            } else {
                newBuilder.withPerformanceMetric(TAG, String.format(Locale.US, MetricType.HANDS_FREE_NOT_ENABLED.getValue(), SET_HANDS_FREE_STATE_NAME));
            }
            newBuilder.emit(this.mContext);
        }
    }

    public synchronized void enableWakeWordSharedPreferences(String str) {
        String str2 = TAG;
        Log.d(str2, "Calling enable with " + str);
        this.mSharedPreferences.edit().remove(SHARED_PREFS_WAKEWORD_KEY).apply();
        MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
        if (str != null) {
            newBuilder.withPerformanceMetric(TAG, String.format(Locale.US, MetricType.HANDS_FREE_ENABLED_WITH_CALLER.getValue(), SET_HANDS_FREE_STATE_NAME, str));
        } else {
            newBuilder.withPerformanceMetric(TAG, String.format(Locale.US, MetricType.HANDS_FREE_ENABLED.getValue(), SET_HANDS_FREE_STATE_NAME));
        }
        newBuilder.emit(this.mContext);
    }
}
