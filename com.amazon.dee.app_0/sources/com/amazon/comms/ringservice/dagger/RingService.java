package com.amazon.comms.ringservice.dagger;

import com.amazon.comms.calling.service.DeviceCallingService;
import com.amazon.comms.calling.sipclient.CallQualityMetrics;
import com.amazon.comms.ringservice.CallQualityStats;
import com.amazon.comms.ringservice.DeviceCallingServiceImpl;
import dagger.Component;
import javax.inject.Singleton;
/* loaded from: classes12.dex */
public enum RingService {
    INSTANCE;
    
    private DeviceCallingService deviceCallingService;

    @Component
    @Singleton
    /* loaded from: classes12.dex */
    public interface RingServiceComponent {
        DeviceCallingServiceImpl deviceCallingService();
    }

    public void clearCallQualityMetrics(String str) {
        CallQualityStats.getInstance().clearCallQualityMetrics(str);
    }

    public CallQualityMetrics getCallQualityMetrics(String str) {
        return CallQualityStats.getInstance().getCallQualityMetrics(str);
    }

    public synchronized DeviceCallingService getDeviceCallingService() {
        if (this.deviceCallingService == null) {
            this.deviceCallingService = DaggerRingService_RingServiceComponent.create().deviceCallingService();
        }
        return this.deviceCallingService;
    }
}
