package com.amazon.matter.handler;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.matter.data.MatterError;
import com.amazon.matter.data.MatterErrorType;
import com.amazon.matter.data.PairDeviceRequest;
import com.amazon.matter.discovery.DiscoveryService;
import com.amazon.matter.eventbus.EventBusHelper;
import com.amazon.matter.eventbus.MatterEventType;
import com.amazon.matter.metrics.MatterMetricsService;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
/* loaded from: classes9.dex */
public class PairDeviceRequestHandler implements RequestHandler {
    private static final Gson GSON = new Gson();
    private final DiscoveryService discoveryService;
    private final EventBusHelper eventBusHelper;
    private final MatterMetricsService metricsService;

    public PairDeviceRequestHandler(DiscoveryService discoveryService, EventBusHelper eventBusHelper, MatterMetricsService matterMetricsService) {
        this.discoveryService = discoveryService;
        this.eventBusHelper = eventBusHelper;
        this.metricsService = matterMetricsService;
    }

    @Override // com.amazon.matter.handler.RequestHandler
    public void processMessage(Message message) {
        this.metricsService.recordRequestMetric(MatterEventType.PAIR_DEVICE_REQUEST);
        MobilyticsMetricsTimer createAndStartTimer = this.metricsService.createAndStartTimer(MatterEventType.PAIR_DEVICE_REQUEST);
        try {
            PairDeviceRequest pairDeviceRequest = (PairDeviceRequest) GSON.fromJson(message.getPayloadAsString(), (Class<Object>) PairDeviceRequest.class);
            this.discoveryService.discoverAndPairDevice((short) pairDeviceRequest.getSetupPayload().discriminator, (short) pairDeviceRequest.getSetupPayload().vendorId, (short) pairDeviceRequest.getSetupPayload().productId, pairDeviceRequest.getFabricId(), pairDeviceRequest.getNodeId(), pairDeviceRequest.getSetupPayload().setupPinCode, createAndStartTimer);
        } catch (JsonSyntaxException unused) {
            this.metricsService.recordErrorMetric(MatterEventType.PAIR_DEVICE_REQUEST, MatterErrorType.INVALID_REQUEST);
            this.eventBusHelper.sendMessageToEventBus(MatterEventType.PAIR_DEVICE_RESPONSE_ERROR, GSON.toJson(new MatterError(MatterErrorType.INVALID_REQUEST, "")));
            this.metricsService.recordEventTime(createAndStartTimer);
        }
    }
}
