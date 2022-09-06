package com.amazon.deecomms.core;

import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.protocols.features.FeatureFilter;
import com.amazon.deecomms.conversation.CommsDeviceSupport;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ApplicationModule_ProvidesFeatureFilterFactory implements Factory<FeatureFilter> {
    private final Provider<CommsDeviceSupport> commsDeviceSupportProvider;
    private final Provider<DeviceInformation> deviceInformationProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesFeatureFilterFactory(ApplicationModule applicationModule, Provider<CommsDeviceSupport> provider, Provider<DeviceInformation> provider2) {
        this.module = applicationModule;
        this.commsDeviceSupportProvider = provider;
        this.deviceInformationProvider = provider2;
    }

    public static ApplicationModule_ProvidesFeatureFilterFactory create(ApplicationModule applicationModule, Provider<CommsDeviceSupport> provider, Provider<DeviceInformation> provider2) {
        return new ApplicationModule_ProvidesFeatureFilterFactory(applicationModule, provider, provider2);
    }

    public static FeatureFilter provideInstance(ApplicationModule applicationModule, Provider<CommsDeviceSupport> provider, Provider<DeviceInformation> provider2) {
        return (FeatureFilter) Preconditions.checkNotNull(applicationModule.providesFeatureFilter(provider.mo10268get(), provider2.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static FeatureFilter proxyProvidesFeatureFilter(ApplicationModule applicationModule, CommsDeviceSupport commsDeviceSupport, DeviceInformation deviceInformation) {
        return (FeatureFilter) Preconditions.checkNotNull(applicationModule.providesFeatureFilter(commsDeviceSupport, deviceInformation), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureFilter mo10268get() {
        return provideInstance(this.module, this.commsDeviceSupportProvider, this.deviceInformationProvider);
    }
}
