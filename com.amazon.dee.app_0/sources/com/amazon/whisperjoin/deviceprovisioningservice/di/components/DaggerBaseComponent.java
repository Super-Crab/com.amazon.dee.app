package com.amazon.whisperjoin.deviceprovisioningservice.di.components;

import android.app.job.JobScheduler;
import android.content.Context;
import android.net.wifi.WifiManager;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusClient;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ArcusModule;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ArcusModule_ProvidesDefaultArcuConfigurationFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ArcusModule_ProvidesFFSArcusSyncClientFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ArcusModule_ProvidesFFSArcusSyncCoordinatorFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ArcusModule_ProvidesRemoteConfigurationManagerFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ContextModule;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ContextModule_ProvidesBluetoothSupportProviderFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ContextModule_ProvidesContextFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ContextModule_ProvidesGsonFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ContextModule_ProvidesJobSchedulerFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ContextModule_ProvidesSharedPreferencesProviderFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ContextModule_ProvidesWifiManagerFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.MapAuthModule;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.MapAuthModule_ProvidesMapProviderFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.UtilModule;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.UtilModule_ProvidesClockFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.UtilModule_ProvidesCurrentWifiNetworkProviderFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.UtilModule_ProvidesDevicePowerStatsProviderFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.UtilModule_ProvidesJobInfoProviderFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.UtilModule_ProvidesLocationPermissionsHelperFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.service.power.DevicePowerStatusProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.util.Clock;
import com.amazon.whisperjoin.deviceprovisioningservice.util.JobInfoProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.util.LocationPermissionsHelper;
import com.amazon.whisperjoin.deviceprovisioningservice.util.SharedPreferencesProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.wifi.CurrentWifiNetworkProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.BluetoothSupportProvider;
import com.amazonaws.mobileconnectors.remoteconfiguration.RemoteConfigurationManager;
import com.google.gson.Gson;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import org.json.JSONObject;
/* loaded from: classes13.dex */
public final class DaggerBaseComponent implements BaseComponent {
    private MapAuthModule mapAuthModule;
    private Provider<BluetoothSupportProvider> providesBluetoothSupportProvider;
    private UtilModule_ProvidesClockFactory providesClockProvider;
    private Provider<Context> providesContextProvider;
    private Provider<JSONObject> providesDefaultArcuConfigurationProvider;
    private Provider<FFSArcusClient> providesFFSArcusSyncClientProvider;
    private Provider<FFSArcusSyncCoordinator> providesFFSArcusSyncCoordinatorProvider;
    private Provider<Gson> providesGsonProvider;
    private UtilModule_ProvidesJobInfoProviderFactory providesJobInfoProvider;
    private Provider<JobScheduler> providesJobSchedulerProvider;
    private Provider<RemoteConfigurationManager> providesRemoteConfigurationManagerProvider;
    private Provider<SharedPreferencesProvider> providesSharedPreferencesProvider;
    private Provider<WifiManager> providesWifiManagerProvider;
    private UtilModule utilModule;

    /* loaded from: classes13.dex */
    public static final class Builder {
        private ArcusModule arcusModule;
        private ContextModule contextModule;
        private MapAuthModule mapAuthModule;
        private UtilModule utilModule;

        public Builder arcusModule(ArcusModule arcusModule) {
            this.arcusModule = (ArcusModule) Preconditions.checkNotNull(arcusModule);
            return this;
        }

        public BaseComponent build() {
            Preconditions.checkBuilderRequirement(this.contextModule, ContextModule.class);
            if (this.mapAuthModule == null) {
                this.mapAuthModule = new MapAuthModule();
            }
            if (this.arcusModule == null) {
                this.arcusModule = new ArcusModule();
            }
            if (this.utilModule == null) {
                this.utilModule = new UtilModule();
            }
            return new DaggerBaseComponent(this);
        }

        public Builder contextModule(ContextModule contextModule) {
            this.contextModule = (ContextModule) Preconditions.checkNotNull(contextModule);
            return this;
        }

        public Builder mapAuthModule(MapAuthModule mapAuthModule) {
            this.mapAuthModule = (MapAuthModule) Preconditions.checkNotNull(mapAuthModule);
            return this;
        }

        public Builder utilModule(UtilModule utilModule) {
            this.utilModule = (UtilModule) Preconditions.checkNotNull(utilModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.providesContextProvider = DoubleCheck.provider(ContextModule_ProvidesContextFactory.create(builder.contextModule));
        this.providesJobSchedulerProvider = DoubleCheck.provider(ContextModule_ProvidesJobSchedulerFactory.create(builder.contextModule, this.providesContextProvider));
        this.providesSharedPreferencesProvider = DoubleCheck.provider(ContextModule_ProvidesSharedPreferencesProviderFactory.create(builder.contextModule, this.providesContextProvider));
        this.providesGsonProvider = DoubleCheck.provider(ContextModule_ProvidesGsonFactory.create(builder.contextModule));
        this.providesJobInfoProvider = UtilModule_ProvidesJobInfoProviderFactory.create(builder.utilModule);
        this.providesClockProvider = UtilModule_ProvidesClockFactory.create(builder.utilModule);
        this.providesDefaultArcuConfigurationProvider = DoubleCheck.provider(ArcusModule_ProvidesDefaultArcuConfigurationFactory.create(builder.arcusModule));
        this.providesRemoteConfigurationManagerProvider = DoubleCheck.provider(ArcusModule_ProvidesRemoteConfigurationManagerFactory.create(builder.arcusModule, this.providesContextProvider, this.providesDefaultArcuConfigurationProvider));
        this.providesFFSArcusSyncClientProvider = DoubleCheck.provider(ArcusModule_ProvidesFFSArcusSyncClientFactory.create(builder.arcusModule, this.providesRemoteConfigurationManagerProvider));
        this.providesFFSArcusSyncCoordinatorProvider = DoubleCheck.provider(ArcusModule_ProvidesFFSArcusSyncCoordinatorFactory.create(builder.arcusModule, this.providesJobSchedulerProvider, this.providesJobInfoProvider, this.providesContextProvider, this.providesSharedPreferencesProvider, this.providesClockProvider, this.providesFFSArcusSyncClientProvider));
        this.providesBluetoothSupportProvider = DoubleCheck.provider(ContextModule_ProvidesBluetoothSupportProviderFactory.create(builder.contextModule, this.providesContextProvider));
        this.providesWifiManagerProvider = DoubleCheck.provider(ContextModule_ProvidesWifiManagerFactory.create(builder.contextModule, this.providesContextProvider));
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.BaseComponent
    public BluetoothSupportProvider getBluetoothSupportProvider() {
        return this.providesBluetoothSupportProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.BaseComponent
    public Clock getClock() {
        return UtilModule_ProvidesClockFactory.proxyProvidesClock(this.utilModule);
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.BaseComponent
    public Context getContext() {
        return this.providesContextProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.BaseComponent
    public CurrentWifiNetworkProvider getCurrentWifiNetworkProvider() {
        return UtilModule_ProvidesCurrentWifiNetworkProviderFactory.proxyProvidesCurrentWifiNetworkProvider(this.utilModule, this.providesWifiManagerProvider.mo10268get());
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.BaseComponent
    public DevicePowerStatusProvider getDevicePowerStatusProvider() {
        return UtilModule_ProvidesDevicePowerStatsProviderFactory.proxyProvidesDevicePowerStatsProvider(this.utilModule, this.providesContextProvider.mo10268get());
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.BaseComponent
    public FFSArcusSyncCoordinator getFFSArcusSyncCoordinator() {
        return this.providesFFSArcusSyncCoordinatorProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.BaseComponent
    public Gson getGson() {
        return this.providesGsonProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.BaseComponent
    public JobInfoProvider getJobInfoProvider() {
        return UtilModule_ProvidesJobInfoProviderFactory.proxyProvidesJobInfoProvider(this.utilModule);
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.BaseComponent
    public JobScheduler getJobScheduler() {
        return this.providesJobSchedulerProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.BaseComponent
    public LocationPermissionsHelper getLocationPermissionHelper() {
        return UtilModule_ProvidesLocationPermissionsHelperFactory.proxyProvidesLocationPermissionsHelper(this.utilModule, this.providesContextProvider.mo10268get());
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.BaseComponent
    public MapAuthenticationProvider getMapAuthProvider() {
        return MapAuthModule_ProvidesMapProviderFactory.proxyProvidesMapProvider(this.mapAuthModule, this.providesContextProvider.mo10268get());
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.BaseComponent
    public SharedPreferencesProvider getSharedPreferencesProvider() {
        return this.providesSharedPreferencesProvider.mo10268get();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.BaseComponent
    public WifiManager getWifiManager() {
        return this.providesWifiManagerProvider.mo10268get();
    }

    private DaggerBaseComponent(Builder builder) {
        this.mapAuthModule = builder.mapAuthModule;
        this.utilModule = builder.utilModule;
        initialize(builder);
    }
}
