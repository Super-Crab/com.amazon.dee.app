package com.amazon.whisperjoin.deviceprovisioningservice.di.components;

import android.app.job.JobScheduler;
import android.content.Context;
import android.net.wifi.WifiManager;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ArcusModule;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ContextModule;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.MapAuthModule;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.UtilModule;
import com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.service.power.DevicePowerStatusProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.util.Clock;
import com.amazon.whisperjoin.deviceprovisioningservice.util.JobInfoProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.util.LocationPermissionsHelper;
import com.amazon.whisperjoin.deviceprovisioningservice.util.SharedPreferencesProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.wifi.CurrentWifiNetworkProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.BluetoothSupportProvider;
import com.google.gson.Gson;
import dagger.Component;
import javax.inject.Singleton;
@Component(modules = {ContextModule.class, MapAuthModule.class, UtilModule.class, ArcusModule.class})
@Singleton
/* loaded from: classes13.dex */
public interface BaseComponent {
    BluetoothSupportProvider getBluetoothSupportProvider();

    Clock getClock();

    Context getContext();

    CurrentWifiNetworkProvider getCurrentWifiNetworkProvider();

    DevicePowerStatusProvider getDevicePowerStatusProvider();

    FFSArcusSyncCoordinator getFFSArcusSyncCoordinator();

    Gson getGson();

    JobInfoProvider getJobInfoProvider();

    JobScheduler getJobScheduler();

    LocationPermissionsHelper getLocationPermissionHelper();

    MapAuthenticationProvider getMapAuthProvider();

    SharedPreferencesProvider getSharedPreferencesProvider();

    WifiManager getWifiManager();
}
