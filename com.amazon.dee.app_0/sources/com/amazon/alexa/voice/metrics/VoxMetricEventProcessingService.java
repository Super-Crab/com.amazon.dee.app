package com.amazon.alexa.voice.metrics;

import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.metrics.service.MetricsService;
import com.amazon.alexa.voice.ui.onedesign.util.Logger;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class VoxMetricEventProcessingService {
    private final List<VoxMetricEventProcessor> eventProcessors = new ArrayList();

    public VoxMetricEventProcessingService(MetricsService metricsService) {
        this.eventProcessors.add(new VoxScrimShownLatencyMetricsReporter(metricsService));
        this.eventProcessors.add(new VoxSingleEventMetricReporter(metricsService));
        this.eventProcessors.add(new VoxFtueMetricsReporter(metricsService));
        this.eventProcessors.add(new VoxLocaleMetricsReporter(metricsService));
    }

    private synchronized void processInternally(VoxMetricEvent voxMetricEvent) {
        Logger.verbose("VoxMetricEventProcessingService event received:" + voxMetricEvent.getName());
        for (VoxMetricEventProcessor voxMetricEventProcessor : this.eventProcessors) {
            voxMetricEventProcessor.process(voxMetricEvent);
        }
    }

    public void process(VoxMetricEvent voxMetricEvent) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        processInternally(voxMetricEvent);
        Logger.verbose("VoxMetricEventProcessingService.process() takes time(ms) : " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    @VisibleForTesting
    VoxMetricEventProcessingService(VoxMetricEventProcessor... voxMetricEventProcessorArr) {
        for (VoxMetricEventProcessor voxMetricEventProcessor : voxMetricEventProcessorArr) {
            this.eventProcessors.add(voxMetricEventProcessor);
        }
    }
}
