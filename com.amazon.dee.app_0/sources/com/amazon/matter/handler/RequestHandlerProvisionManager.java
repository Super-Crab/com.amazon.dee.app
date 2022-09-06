package com.amazon.matter.handler;

import android.content.Context;
import chip.setuppayload.SetupPayloadParser;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.matter.commission.WifiNetworkManager;
import com.amazon.matter.discovery.DiscoveryServiceBleImpl;
import com.amazon.matter.eventbus.EventBusHelper;
import com.amazon.matter.eventbus.MatterEventType;
import com.amazon.matter.metrics.MatterMetricsService;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
/* loaded from: classes9.dex */
public class RequestHandlerProvisionManager {
    private final Map<MatterEventType, RequestHandler> matterEventToHandlerMap;

    public RequestHandlerProvisionManager(EventBus eventBus, Context context, MatterMetricsService matterMetricsService) {
        EventBusHelper eventBusHelper = new EventBusHelper(eventBus);
        DiscoveryServiceBleImpl discoveryServiceBleImpl = new DiscoveryServiceBleImpl(context, eventBusHelper, matterMetricsService);
        WifiNetworkManager wifiNetworkManager = new WifiNetworkManager(context, eventBusHelper, matterMetricsService);
        SetupPayloadParser setupPayloadParser = new SetupPayloadParser();
        this.matterEventToHandlerMap = ImmutableMap.of(MatterEventType.MANUAL_CODE_PARSER_REQUEST, (AddAndEnableNetworkRequestHandler) new ManualCodeParserRequestHandler(eventBusHelper, setupPayloadParser, matterMetricsService), MatterEventType.QR_CODE_PARSER_REQUEST, (AddAndEnableNetworkRequestHandler) new QrCodeParserRequestHandler(eventBusHelper, setupPayloadParser, matterMetricsService), MatterEventType.PAIR_DEVICE_REQUEST, (AddAndEnableNetworkRequestHandler) new PairDeviceRequestHandler(discoveryServiceBleImpl, eventBusHelper, matterMetricsService), MatterEventType.ADD_AND_ENABLE_NETWORK_REQUEST, new AddAndEnableNetworkRequestHandler(wifiNetworkManager, eventBusHelper, matterMetricsService));
    }

    public RequestHandler getRequestHandler(MatterEventType matterEventType) {
        return this.matterEventToHandlerMap.get(matterEventType);
    }
}
