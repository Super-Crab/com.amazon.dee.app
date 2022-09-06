package com.amazon.dee.app.elements;

import com.amazon.alexa.elements.api.BridgeStatusService;
import com.amazon.alexa.eventing.EventArgs;
import com.amazon.alexa.eventing.EventHandler;
import com.amazon.alexa.eventing.Listen;
import com.amazon.dee.app.services.logging.Log;
import com.dee.app.metrics.MetricComponentName;
import com.dee.app.metrics.MetricDescriptor;
import com.dee.app.metrics.MetricName;
import com.dee.app.metrics.MetricsConstants;
import com.dee.app.metrics.MetricsServiceV2;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.ReactContext;
import java.util.Timer;
import java.util.TimerTask;
/* loaded from: classes12.dex */
public class ReactBridgeMetrics {
    private static final String TAG = Log.tag(ReactBridgeMetrics.class);
    private final BridgeStatusService bridgeStatusService;
    ReactInstanceManager.ReactInstanceEventListener eventListener = null;
    private final MetricsServiceV2 metricsServiceV2;

    public ReactBridgeMetrics(MetricsServiceV2 metricsServiceV2, BridgeStatusService bridgeStatusService) {
        this.metricsServiceV2 = metricsServiceV2;
        this.bridgeStatusService = bridgeStatusService;
    }

    public /* synthetic */ void lambda$startTimers$0$ReactBridgeMetrics(long j, MetricDescriptor metricDescriptor, EventArgs eventArgs) {
        long currentTimeMillis = System.currentTimeMillis() - j;
        Log.w(TAG, "[MEASURE] RN FullyInitialized %d", Long.valueOf(currentTimeMillis));
        this.metricsServiceV2.recordTime(metricDescriptor, currentTimeMillis);
    }

    public /* synthetic */ void lambda$startTimers$1$ReactBridgeMetrics(long j, Timer timer, MetricDescriptor metricDescriptor, ReactInstanceManager reactInstanceManager, ReactContext reactContext) {
        long currentTimeMillis = System.currentTimeMillis() - j;
        Log.w(TAG, "[MEASURE] RN InstanceCreated %s - %d", reactContext.getLifecycleState().name(), Long.valueOf(currentTimeMillis));
        timer.cancel();
        this.metricsServiceV2.recordTime(metricDescriptor, currentTimeMillis);
        reactInstanceManager.removeReactInstanceEventListener(this.eventListener);
    }

    public ReactInstanceManager.ReactInstanceEventListener startTimers(final long j, final ReactInstanceManager reactInstanceManager) {
        MetricComponentName build = new MetricComponentName.Builder().withCategoryId("load").build();
        final MetricDescriptor build2 = new MetricDescriptor.Builder(new MetricName.Builder("load").withSource(MetricsConstants.Source.NATIVE_ELEMENTS).withModule(MetricsConstants.Module.CORE).build(), build).build();
        final MetricDescriptor build3 = new MetricDescriptor.Builder(new MetricName.Builder(MetricsConstants.Id.BUNDLE_INITIALIZE).withSource(MetricsConstants.Source.NATIVE_ELEMENTS).withModule(MetricsConstants.Module.CORE).build(), build).build();
        Listen.once(this.bridgeStatusService.onReadyChange(), new EventHandler() { // from class: com.amazon.dee.app.elements.-$$Lambda$ReactBridgeMetrics$J0tdqRIOy90Z6dojrRSiXP7teyU
            @Override // com.amazon.alexa.eventing.EventHandler
            public final void onEvent(EventArgs eventArgs) {
                ReactBridgeMetrics.this.lambda$startTimers$0$ReactBridgeMetrics(j, build3, eventArgs);
            }
        });
        final MetricDescriptor build4 = new MetricDescriptor.Builder(new MetricName.Builder(MetricsConstants.Id.LOAD_FAILURE).withSource(MetricsConstants.Source.NATIVE_ELEMENTS).withModule(MetricsConstants.Module.CORE).build(), build).build();
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() { // from class: com.amazon.dee.app.elements.ReactBridgeMetrics.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                ReactBridgeMetrics.this.metricsServiceV2.recordCount(build4, 1.0d);
                Log.e(ReactBridgeMetrics.TAG, "React bridge failed to load.");
                cancel();
            }
        }, 10000L);
        this.eventListener = new ReactInstanceManager.ReactInstanceEventListener() { // from class: com.amazon.dee.app.elements.-$$Lambda$ReactBridgeMetrics$63SztbjrecWuSzv6THWojo7AGgs
            @Override // com.facebook.react.ReactInstanceManager.ReactInstanceEventListener
            public final void onReactContextInitialized(ReactContext reactContext) {
                ReactBridgeMetrics.this.lambda$startTimers$1$ReactBridgeMetrics(j, timer, build2, reactInstanceManager, reactContext);
            }
        };
        return this.eventListener;
    }
}
