package com.amazon.alexa.handsfree.storage.dependencies;

import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentProtocol;
import com.amazon.alexa.handsfree.storage.metrics.CacheMetricsService;
import com.amazon.alexa.handsfree.storage.metrics.EnableMetricsBroadcastReceiver;
import com.amazon.alexa.handsfree.storage.metrics.ProcessMetricsCacheService;
import dagger.Subcomponent;
@Subcomponent
/* loaded from: classes8.dex */
public interface FalcoStorageComponent extends AhfComponentProtocol {
    void inject(CacheMetricsService cacheMetricsService);

    void inject(EnableMetricsBroadcastReceiver enableMetricsBroadcastReceiver);

    void inject(ProcessMetricsCacheService processMetricsCacheService);
}
