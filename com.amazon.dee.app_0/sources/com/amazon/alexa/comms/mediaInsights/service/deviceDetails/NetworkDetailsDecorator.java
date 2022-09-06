package com.amazon.alexa.comms.mediaInsights.service.deviceDetails;

import java.util.Map;
import lombok.NonNull;
/* loaded from: classes6.dex */
public class NetworkDetailsDecorator implements DeviceDetails {
    @NonNull
    private DeviceDetails deviceDetails;
    @NonNull
    private NetworkDetailsFactory networkDetailsfactory;

    public NetworkDetailsDecorator(@NonNull DeviceDetails deviceDetails, @NonNull NetworkDetailsFactory networkDetailsFactory) {
        if (deviceDetails != null) {
            if (networkDetailsFactory == null) {
                throw new IllegalArgumentException("networkDetailsfactory is null");
            }
            this.deviceDetails = deviceDetails;
            this.networkDetailsfactory = networkDetailsFactory;
            return;
        }
        throw new IllegalArgumentException("deviceDetails is null");
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.deviceDetails.DeviceDetails
    public Map<String, String> asMap() {
        Map<String, String> asMap = this.deviceDetails.asMap();
        asMap.putAll(this.networkDetailsfactory.create());
        return asMap;
    }
}
