package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.content.Context;
import android.net.wifi.WifiManager;
import com.amazon.whisperjoin.deviceprovisioningservice.service.power.DevicePowerStatusProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.util.Clock;
import com.amazon.whisperjoin.deviceprovisioningservice.util.JobInfoProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.util.LocationPermissionsHelper;
import com.amazon.whisperjoin.deviceprovisioningservice.wifi.CurrentWifiNetworkProvider;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes13.dex */
public class UtilModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public Clock providesClock() {
        return new Clock.SystemClockImpl();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public CurrentWifiNetworkProvider providesCurrentWifiNetworkProvider(WifiManager wifiManager) {
        return new CurrentWifiNetworkProvider(wifiManager);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public DevicePowerStatusProvider providesDevicePowerStatsProvider(Context context) {
        return new DevicePowerStatusProvider(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public JobInfoProvider providesJobInfoProvider() {
        return new JobInfoProvider();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public LocationPermissionsHelper providesLocationPermissionsHelper(Context context) {
        return new LocationPermissionsHelper(context);
    }
}
