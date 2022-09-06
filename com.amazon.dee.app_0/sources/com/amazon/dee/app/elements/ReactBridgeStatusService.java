package com.amazon.dee.app.elements;

import com.amazon.alexa.elements.api.BridgeStatusService;
import com.amazon.alexa.elements.api.BridgeStatusServiceConstants;
import com.amazon.alexa.elements.api.InitializationPhase;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventing.Event;
import com.amazon.alexa.eventing.EventArgs;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.dee.app.services.metrics.LatencyService;
import com.dee.app.metrics.MetricsService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/* loaded from: classes12.dex */
public class ReactBridgeStatusService implements BridgeStatusService {
    private final EventBus eventBus;
    private final MetricsService metricsService;
    private boolean isFirstLoad = true;
    private boolean isReady = false;
    private InitializationPhase currentInitializationPhase = InitializationPhase.UNKNOWN;
    Event<Boolean> onReadyChange = new Event<>();
    List<Runnable> listeners = new ArrayList();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public ReactBridgeStatusService(MetricsService metricsService, EventBus eventBus) {
        this.metricsService = metricsService;
        this.eventBus = eventBus;
    }

    @Override // com.amazon.alexa.elements.api.BridgeStatusService
    public InitializationPhase getCurrentPhase() {
        this.lock.readLock().lock();
        try {
            return this.currentInitializationPhase;
        } finally {
            this.lock.readLock().unlock();
        }
    }

    @Override // com.amazon.alexa.elements.api.BridgeStatusService
    public boolean getIsReady() {
        return this.isReady;
    }

    @Override // com.amazon.alexa.elements.api.BridgeStatusService
    public Event<Boolean> onReadyChange() {
        return this.onReadyChange;
    }

    @Override // com.amazon.alexa.elements.api.BridgeStatusService
    public synchronized void registerListener(Runnable runnable) {
        if (this.isReady) {
            runnable.run();
        } else {
            this.listeners.add(runnable);
        }
    }

    @Override // com.amazon.alexa.elements.api.BridgeStatusService
    public synchronized void setIsReady(boolean z) {
        this.isReady = z;
        if (this.isFirstLoad && z) {
            this.metricsService.recordEvent(AlexaMetricsConstants.MetricEvents.RN_BRIDGE_READY_ON_CHANGE, AlexaMetricsConstants.MetricsComponents.ELEMENTS, null);
            LatencyService.whenReadyComplete();
            this.isFirstLoad = false;
        }
        this.onReadyChange.fire(EventArgs.of(Boolean.valueOf(z)));
        if (z) {
            for (Runnable runnable : this.listeners) {
                runnable.run();
            }
            this.listeners.clear();
        }
    }

    @Override // com.amazon.alexa.elements.api.BridgeStatusService
    public void setPhaseReady(InitializationPhase initializationPhase) {
        this.lock.writeLock().lock();
        try {
            this.currentInitializationPhase = initializationPhase;
            this.eventBus.publish(new Message.Builder().setEventType(BridgeStatusServiceConstants.INITIALIZATION_PHASE_EVENT_TYPE).setPayload(initializationPhase.toString()).build());
        } finally {
            this.lock.writeLock().unlock();
        }
    }
}
