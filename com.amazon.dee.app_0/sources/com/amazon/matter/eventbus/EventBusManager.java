package com.amazon.matter.eventbus;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.matter.handler.RequestHandler;
import com.amazon.matter.handler.RequestHandlerProvisionManager;
import com.amazon.matter.metrics.MatterMetricsService;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes9.dex */
public class EventBusManager implements MatterEventHandler {
    private static final String TAG = "EventBusManager";
    private final Context context;
    private final EventBus eventBus;
    private final MatterEventSubscriber matterEventSubscriber = new MatterEventSubscriber(this);
    private MatterMetricsService metricsService;
    private RequestHandlerProvisionManager requestHandlerProvisionManager;

    public EventBusManager(EventBus eventBus, Context context) {
        this.eventBus = eventBus;
        this.context = context;
    }

    @Override // com.amazon.matter.eventbus.MatterEventHandler
    public void handleMatterRequest(@NonNull Message message) {
        if (this.metricsService == null) {
            this.metricsService = new MatterMetricsService((Mobilytics) GeneratedOutlineSupport1.outline20(Mobilytics.class));
        }
        if (!this.matterEventSubscriber.supportsMessage(message)) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Skip message with type: ");
            outline107.append(message.getEventType());
            Log.w(str, outline107.toString());
            this.metricsService.recordMetric("matterRequest.Error.INVALID_REQUEST");
            return;
        }
        if (this.requestHandlerProvisionManager == null) {
            this.requestHandlerProvisionManager = new RequestHandlerProvisionManager(this.eventBus, this.context, this.metricsService);
        }
        MatterEventType eventTypeFromString = MatterEventType.getEventTypeFromString(message.getEventType());
        RequestHandler requestHandler = this.requestHandlerProvisionManager.getRequestHandler(eventTypeFromString);
        if (requestHandler != null) {
            requestHandler.processMessage(message);
            return;
        }
        String str2 = TAG;
        Log.e(str2, "No registered handler for event: " + eventTypeFromString);
    }

    public void startListening() {
        Log.i(TAG, "Subscribing to matter eventBus");
        this.eventBus.subscribe(this.matterEventSubscriber);
    }

    public void stopListening() {
        Log.i(TAG, "Unsubscribing from matter eventBus");
        this.eventBus.unsubscribe(this.matterEventSubscriber);
    }

    protected EventBusManager(Context context, EventBus eventBus, MatterMetricsService matterMetricsService, RequestHandlerProvisionManager requestHandlerProvisionManager) {
        this.context = context;
        this.eventBus = eventBus;
        this.metricsService = matterMetricsService;
        this.requestHandlerProvisionManager = requestHandlerProvisionManager;
    }
}
