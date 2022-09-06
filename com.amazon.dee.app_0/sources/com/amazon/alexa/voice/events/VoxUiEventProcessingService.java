package com.amazon.alexa.voice.events;

import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import com.amazon.alexa.voice.ui.onedesign.util.Logger;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class VoxUiEventProcessingService {
    private final List<VoxUiEventProcessor> eventProcessors;

    public VoxUiEventProcessingService(VoxMetricEventProcessingService voxMetricEventProcessingService) {
        this(new VoxUiEventToMetricEventProcessor(voxMetricEventProcessingService));
    }

    private synchronized void processInternally(VoxUiEvent voxUiEvent) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Logger.verbose("VoxUiEventProcessingService event received:" + voxUiEvent.getName());
        synchronized (this.eventProcessors) {
            for (VoxUiEventProcessor voxUiEventProcessor : this.eventProcessors) {
                voxUiEventProcessor.process(voxUiEvent);
            }
        }
        Logger.verbose("VoxUiEventProcessingService.process() takes time(ms) : " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    public void process(VoxUiEvent voxUiEvent) {
        processInternally(voxUiEvent);
    }

    public void registerEventProcessor(VoxUiEventProcessor voxUiEventProcessor) {
        synchronized (this.eventProcessors) {
            this.eventProcessors.add(voxUiEventProcessor);
        }
    }

    public void unregisterEventProcessor(VoxUiEventProcessor voxUiEventProcessor) {
        synchronized (this.eventProcessors) {
            this.eventProcessors.remove(voxUiEventProcessor);
        }
    }

    @VisibleForTesting
    VoxUiEventProcessingService(VoxUiEventProcessor... voxUiEventProcessorArr) {
        this.eventProcessors = new ArrayList();
        for (VoxUiEventProcessor voxUiEventProcessor : voxUiEventProcessorArr) {
            this.eventProcessors.add(voxUiEventProcessor);
        }
    }
}
