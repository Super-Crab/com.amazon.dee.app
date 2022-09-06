package com.amazon.alexa.comms.mediaInsights.service.deviceDetails;

import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;
/* loaded from: classes6.dex */
public class DeviceDetailsImpl implements DeviceDetails {
    private Map<String, String> deviceDetailsMap;

    public DeviceDetailsImpl() {
        this.deviceDetailsMap = new HashMap();
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.deviceDetails.DeviceDetails
    public Map<String, String> asMap() {
        return this.deviceDetailsMap;
    }

    public DeviceDetailsImpl(@NonNull Map<String, String> map) {
        this.deviceDetailsMap = new HashMap();
        if (map != null) {
            this.deviceDetailsMap = map;
            return;
        }
        throw new IllegalArgumentException("deviceDetailsMap is null");
    }
}
