package com.amazon.whisperjoin.deviceprovisioningservice.service;

import android.content.Context;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.metrics.FFSProvisioningServiceMetricsRecorder;
import com.amazon.whisperjoin.deviceprovisioningservice.service.power.DevicePowerMonitor;
import com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist.WhiteListPolicyCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist.WhiteListPolicyUpdateListener;
import com.amazon.whisperjoin.deviceprovisioningservice.util.LocationPermissionsHelper;
import com.amazon.whisperjoin.deviceprovisioningservice.util.SharedPreferencesProvider;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class FFSProvisioningService_MembersInjector implements MembersInjector<FFSProvisioningService> {
    private final Provider<Context> mContextProvider;
    private final Provider<DevicePowerMonitor> mDevicePowerMonitorProvider;
    private final Provider<FFSArcusSyncCoordinator> mFFSArcusSyncCoordinatorProvider;
    private final Provider<FFSProvisioningServiceMetricsRecorder> mFFSServiceMetricsRecorderProvider;
    private final Provider<LocationPermissionsHelper> mLocationPermissionsHelperProvider;
    private final Provider<ProvisionerClientData> mProvisionerClientDataProvider;
    private final Provider<SharedPreferencesProvider> mSharedPreferencesProvider;
    private final Provider<WhiteListPolicyCoordinator> mWhiteListPolicyCoordinatorProvider;
    private final Provider<WhiteListPolicyUpdateListener> mWhiteListPolicyUpdateListenerProvider;

    public FFSProvisioningService_MembersInjector(Provider<Context> provider, Provider<SharedPreferencesProvider> provider2, Provider<WhiteListPolicyCoordinator> provider3, Provider<WhiteListPolicyUpdateListener> provider4, Provider<ProvisionerClientData> provider5, Provider<DevicePowerMonitor> provider6, Provider<FFSProvisioningServiceMetricsRecorder> provider7, Provider<LocationPermissionsHelper> provider8, Provider<FFSArcusSyncCoordinator> provider9) {
        this.mContextProvider = provider;
        this.mSharedPreferencesProvider = provider2;
        this.mWhiteListPolicyCoordinatorProvider = provider3;
        this.mWhiteListPolicyUpdateListenerProvider = provider4;
        this.mProvisionerClientDataProvider = provider5;
        this.mDevicePowerMonitorProvider = provider6;
        this.mFFSServiceMetricsRecorderProvider = provider7;
        this.mLocationPermissionsHelperProvider = provider8;
        this.mFFSArcusSyncCoordinatorProvider = provider9;
    }

    public static MembersInjector<FFSProvisioningService> create(Provider<Context> provider, Provider<SharedPreferencesProvider> provider2, Provider<WhiteListPolicyCoordinator> provider3, Provider<WhiteListPolicyUpdateListener> provider4, Provider<ProvisionerClientData> provider5, Provider<DevicePowerMonitor> provider6, Provider<FFSProvisioningServiceMetricsRecorder> provider7, Provider<LocationPermissionsHelper> provider8, Provider<FFSArcusSyncCoordinator> provider9) {
        return new FFSProvisioningService_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
    }

    public static void injectMContext(FFSProvisioningService fFSProvisioningService, Context context) {
        fFSProvisioningService.mContext = context;
    }

    public static void injectMDevicePowerMonitor(FFSProvisioningService fFSProvisioningService, DevicePowerMonitor devicePowerMonitor) {
        fFSProvisioningService.mDevicePowerMonitor = devicePowerMonitor;
    }

    public static void injectMFFSArcusSyncCoordinator(FFSProvisioningService fFSProvisioningService, FFSArcusSyncCoordinator fFSArcusSyncCoordinator) {
        fFSProvisioningService.mFFSArcusSyncCoordinator = fFSArcusSyncCoordinator;
    }

    public static void injectMFFSServiceMetricsRecorder(FFSProvisioningService fFSProvisioningService, FFSProvisioningServiceMetricsRecorder fFSProvisioningServiceMetricsRecorder) {
        fFSProvisioningService.mFFSServiceMetricsRecorder = fFSProvisioningServiceMetricsRecorder;
    }

    public static void injectMLocationPermissionsHelper(FFSProvisioningService fFSProvisioningService, LocationPermissionsHelper locationPermissionsHelper) {
        fFSProvisioningService.mLocationPermissionsHelper = locationPermissionsHelper;
    }

    public static void injectMProvisionerClientData(FFSProvisioningService fFSProvisioningService, ProvisionerClientData provisionerClientData) {
        fFSProvisioningService.mProvisionerClientData = provisionerClientData;
    }

    public static void injectMSharedPreferencesProvider(FFSProvisioningService fFSProvisioningService, SharedPreferencesProvider sharedPreferencesProvider) {
        fFSProvisioningService.mSharedPreferencesProvider = sharedPreferencesProvider;
    }

    public static void injectMWhiteListPolicyCoordinator(FFSProvisioningService fFSProvisioningService, WhiteListPolicyCoordinator whiteListPolicyCoordinator) {
        fFSProvisioningService.mWhiteListPolicyCoordinator = whiteListPolicyCoordinator;
    }

    public static void injectMWhiteListPolicyUpdateListener(FFSProvisioningService fFSProvisioningService, WhiteListPolicyUpdateListener whiteListPolicyUpdateListener) {
        fFSProvisioningService.mWhiteListPolicyUpdateListener = whiteListPolicyUpdateListener;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(FFSProvisioningService fFSProvisioningService) {
        injectMContext(fFSProvisioningService, this.mContextProvider.mo10268get());
        injectMSharedPreferencesProvider(fFSProvisioningService, this.mSharedPreferencesProvider.mo10268get());
        injectMWhiteListPolicyCoordinator(fFSProvisioningService, this.mWhiteListPolicyCoordinatorProvider.mo10268get());
        injectMWhiteListPolicyUpdateListener(fFSProvisioningService, this.mWhiteListPolicyUpdateListenerProvider.mo10268get());
        injectMProvisionerClientData(fFSProvisioningService, this.mProvisionerClientDataProvider.mo10268get());
        injectMDevicePowerMonitor(fFSProvisioningService, this.mDevicePowerMonitorProvider.mo10268get());
        injectMFFSServiceMetricsRecorder(fFSProvisioningService, this.mFFSServiceMetricsRecorderProvider.mo10268get());
        injectMLocationPermissionsHelper(fFSProvisioningService, this.mLocationPermissionsHelperProvider.mo10268get());
        injectMFFSArcusSyncCoordinator(fFSProvisioningService, this.mFFSArcusSyncCoordinatorProvider.mo10268get());
    }
}
