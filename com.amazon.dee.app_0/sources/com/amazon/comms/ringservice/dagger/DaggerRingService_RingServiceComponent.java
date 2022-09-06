package com.amazon.comms.ringservice.dagger;

import com.amazon.comms.ringservice.DeviceCallingServiceImpl;
import com.amazon.comms.ringservice.DeviceCallingServiceImpl_Factory;
import com.amazon.comms.ringservice.dagger.RingService;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class DaggerRingService_RingServiceComponent implements RingService.RingServiceComponent {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Provider<DeviceCallingServiceImpl> deviceCallingServiceImplProvider;

    /* loaded from: classes12.dex */
    public static final class Builder {
        public RingService.RingServiceComponent build() {
            return new DaggerRingService_RingServiceComponent(this);
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static RingService.RingServiceComponent create() {
        return builder().build();
    }

    private void initialize(Builder builder) {
        this.deviceCallingServiceImplProvider = DoubleCheck.provider(DeviceCallingServiceImpl_Factory.create());
    }

    @Override // com.amazon.comms.ringservice.dagger.RingService.RingServiceComponent
    public DeviceCallingServiceImpl deviceCallingService() {
        return this.deviceCallingServiceImplProvider.mo10268get();
    }

    private DaggerRingService_RingServiceComponent(Builder builder) {
        initialize(builder);
    }
}
