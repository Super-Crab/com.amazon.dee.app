package com.amazon.alexa.alertsca.dependencies;

import com.amazon.alexa.device.api.DeviceInformation;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Module;
import dagger.Provides;
import javax.annotation.Nullable;
import javax.inject.Singleton;
@Module
/* loaded from: classes6.dex */
public class DeviceInformationModule {
    @Provides
    @Singleton
    @Nullable
    public DeviceInformation provideDeviceInformation() {
        return (DeviceInformation) GeneratedOutlineSupport1.outline21(DeviceInformation.class);
    }
}
