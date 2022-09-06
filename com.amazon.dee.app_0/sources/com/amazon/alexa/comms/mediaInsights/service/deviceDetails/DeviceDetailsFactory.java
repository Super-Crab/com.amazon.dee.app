package com.amazon.alexa.comms.mediaInsights.service.deviceDetails;

import lombok.NonNull;
/* loaded from: classes6.dex */
public class DeviceDetailsFactory {
    @NonNull
    private NetworkDetailsFactory networkDetailsFactory;

    public DeviceDetailsFactory(@NonNull NetworkDetailsFactory networkDetailsFactory) {
        if (networkDetailsFactory != null) {
            this.networkDetailsFactory = networkDetailsFactory;
            return;
        }
        throw new IllegalArgumentException("networkDetailsFactory is null");
    }

    public DeviceDetails create() {
        return new NetworkDetailsDecorator(new StaticDeviceDetailsDecorator(new DeviceDetailsImpl()), this.networkDetailsFactory);
    }
}
