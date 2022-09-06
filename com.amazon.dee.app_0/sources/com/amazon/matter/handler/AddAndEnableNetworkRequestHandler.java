package com.amazon.matter.handler;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.matter.commission.WifiNetworkManager;
import com.amazon.matter.data.AddAndEnableNetworkRequest;
import com.amazon.matter.data.MatterError;
import com.amazon.matter.data.MatterErrorType;
import com.amazon.matter.eventbus.EventBusHelper;
import com.amazon.matter.eventbus.MatterEventType;
import com.amazon.matter.metrics.MatterMetricsService;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
/* loaded from: classes9.dex */
public class AddAndEnableNetworkRequestHandler implements RequestHandler {
    private static final Gson GSON = new Gson();
    private final EventBusHelper eventBusHelper;
    private final MatterMetricsService metricsService;
    private final WifiNetworkManager wifiNetworkManager;

    public AddAndEnableNetworkRequestHandler(WifiNetworkManager wifiNetworkManager, EventBusHelper eventBusHelper, MatterMetricsService matterMetricsService) {
        this.wifiNetworkManager = wifiNetworkManager;
        this.eventBusHelper = eventBusHelper;
        this.metricsService = matterMetricsService;
    }

    @Override // com.amazon.matter.handler.RequestHandler
    public void processMessage(Message message) {
        this.metricsService.recordRequestMetric(MatterEventType.ADD_AND_ENABLE_NETWORK_REQUEST);
        MobilyticsMetricsTimer createAndStartTimer = this.metricsService.createAndStartTimer(MatterEventType.ADD_AND_ENABLE_NETWORK_REQUEST);
        try {
            AddAndEnableNetworkRequest addAndEnableNetworkRequest = (AddAndEnableNetworkRequest) GSON.fromJson(message.getPayloadAsString(), (Class<Object>) AddAndEnableNetworkRequest.class);
            this.wifiNetworkManager.addAndEnableNetwork(addAndEnableNetworkRequest.getNodeId(), addAndEnableNetworkRequest.getSsid(), addAndEnableNetworkRequest.getPassword(), createAndStartTimer);
        } catch (JsonSyntaxException unused) {
            this.metricsService.recordErrorMetric(MatterEventType.ADD_AND_ENABLE_NETWORK_REQUEST, MatterErrorType.INVALID_REQUEST);
            this.eventBusHelper.sendMessageToEventBus(MatterEventType.ADD_AND_ENABLE_NETWORK_RESPONSE_ERROR, GSON.toJson(new MatterError(MatterErrorType.INVALID_REQUEST, "")));
            this.metricsService.recordEventTime(createAndStartTimer);
        }
    }
}
