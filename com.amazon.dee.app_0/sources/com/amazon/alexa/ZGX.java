package com.amazon.alexa;

import com.amazon.alexa.client.metrics.core.DeviceInformation;
/* compiled from: MetricsModule.java */
/* loaded from: classes.dex */
public class ZGX implements DeviceInformation.ServiceVersionProvider {
    public ZGX(kbj kbjVar) {
    }

    @Override // com.amazon.alexa.client.metrics.core.DeviceInformation.ServiceVersionProvider
    public String getServiceVersion() {
        return "2.4.1609.0";
    }
}
