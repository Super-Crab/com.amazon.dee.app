package com.amazon.dee.app.services.metrics;

import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
import com.amazon.dee.app.services.logging.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsService;
import com.dee.app.metrics.MetricsTimer;
import java.util.Collections;
/* loaded from: classes12.dex */
public final class UserPerceivedLatencyService {
    private static final String FULL_USER_PERCEIVED_COLD_START_LATENCY = "native.cold_start.full.user_perceived.time";
    private static final String USER_PERCEIVED_COLD_START_LATENCY = "native.cold_start.user_perceived.time";
    private static final String TAG = Log.tag(UserPerceivedLatencyService.class);
    @VisibleForTesting
    static long coldStartTimestamp = -1;

    private UserPerceivedLatencyService() {
    }

    public static void cancelColdStartTimer() {
        coldStartTimestamp = -1L;
    }

    private static MetricsTimer createMetricsTimer(String str, long j) {
        return new DefaultMetricsTimer(str, "Application", Collections.emptyMap(), j, false);
    }

    public static void initColdStartTimer() {
        coldStartTimestamp = SystemClock.elapsedRealtime();
    }

    public static void recordColdStartTimer(MetricsService metricsService) {
        if (coldStartTimestamp >= 0) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            long j = elapsedRealtime - coldStartTimestamp;
            GeneratedOutlineSupport1.outline153("native.cold_start.user_perceived.time: ", j);
            metricsService.recordTimer(createMetricsTimer(USER_PERCEIVED_COLD_START_LATENCY, j));
            int i = Build.VERSION.SDK_INT;
            long startElapsedRealtime = elapsedRealtime - Process.getStartElapsedRealtime();
            GeneratedOutlineSupport1.outline153("native.cold_start.full.user_perceived.time: ", startElapsedRealtime);
            metricsService.recordTimer(createMetricsTimer(FULL_USER_PERCEIVED_COLD_START_LATENCY, startElapsedRealtime));
            coldStartTimestamp = -1L;
        }
    }
}
