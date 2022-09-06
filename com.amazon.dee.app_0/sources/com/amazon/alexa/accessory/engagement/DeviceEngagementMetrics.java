package com.amazon.alexa.accessory.engagement;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.engagement.SharedPreferencesLongRepository;
import com.amazon.alexa.accessory.internal.monitor.NetworkStatusMonitor;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.MetricsSupplier;
import java.util.concurrent.Executors;
import net.danlew.android.joda.JodaTimeAndroid;
/* loaded from: classes.dex */
public final class DeviceEngagementMetrics {
    private static final String TAG = "DeviceEngagementMetrics:";

    private DeviceEngagementMetrics() {
        throw new UnsupportedOperationException("No instances of this static utility class.");
    }

    public static DeviceEngagementMetricsTriggers triggersFrom(@NonNull Context context, @NonNull NetworkStatusMonitor networkStatusMonitor, @NonNull SessionSupplier sessionSupplier, @NonNull MetricsSupplier metricsSupplier) {
        Preconditions.notNull(context, "context");
        Preconditions.notNull(networkStatusMonitor, "networkStatusMonitor");
        Preconditions.notNull(sessionSupplier, "sessionSupplier");
        Preconditions.notNull(metricsSupplier, "metricsSupplier");
        try {
            JodaTimeAndroid.init(context);
        } catch (Exception e) {
            Logger.e("%s Unable to initialize JodaTimeAndroid", e, TAG);
        }
        AndroidAndMAPAttributes androidAndMAPAttributes = new AndroidAndMAPAttributes(context);
        DeviceEngagementMetricsTriggers deviceEngagementMetricsTriggers = new DeviceEngagementMetricsTriggers(networkStatusMonitor, sessionSupplier, new BackgroundDemReporter(Executors.newSingleThreadExecutor(), new PlatformAndUserReportingFilter(androidAndMAPAttributes, new DebounceReportingFilter(new DcmDemReporter(new DcmMetricsFactorySupplier(androidAndMAPAttributes, context), androidAndMAPAttributes, $$Lambda$qyOpNvB9udiUdow0Yz0GeX9SisY.INSTANCE), $$Lambda$qyOpNvB9udiUdow0Yz0GeX9SisY.INSTANCE, new SharedPreferencesLongRepository.Factory(context)))));
        networkStatusMonitor.addObserver(deviceEngagementMetricsTriggers);
        sessionSupplier.addSessionListener(deviceEngagementMetricsTriggers);
        metricsSupplier.registerObserver(deviceEngagementMetricsTriggers);
        return deviceEngagementMetricsTriggers;
    }
}
