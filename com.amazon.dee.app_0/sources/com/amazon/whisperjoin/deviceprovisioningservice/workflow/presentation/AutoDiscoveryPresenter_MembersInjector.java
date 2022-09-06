package com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation;

import com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.device.DiscoverySettings;
import com.amazon.whisperjoin.deviceprovisioningservice.metrics.AutoDiscoveryMetricsRecorder;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceDiscoveryStream;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.CustomerProvisioneesSetupStatusSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class AutoDiscoveryPresenter_MembersInjector implements MembersInjector<AutoDiscoveryPresenter> {
    private final Provider<CustomerProvisioneesSetupStatusSyncCoordinator> mAutoDiscoverySyncCoordinatorProvider;
    private final Provider<DeviceDiscoveryStream> mDeviceDiscoveryStreamProvider;
    private final Provider<DiscoverySettings> mDiscoverySettingsProvider;
    private final Provider<DSSClient> mDssClientProvider;
    private final Provider<FFSArcusSyncCoordinator> mFFSArcusSyncCoordinatorProvider;
    private final Provider<AutoDiscoveryMetricsRecorder> mMetricsRecorderProvider;
    private final Provider<ProvisionerClientData> mProvisionerClientDataProvider;
    private final Provider<WJErrorMapper<Throwable>> mWJErrorMapperProvider;

    public AutoDiscoveryPresenter_MembersInjector(Provider<DiscoverySettings> provider, Provider<DeviceDiscoveryStream> provider2, Provider<CustomerProvisioneesSetupStatusSyncCoordinator> provider3, Provider<FFSArcusSyncCoordinator> provider4, Provider<ProvisionerClientData> provider5, Provider<WJErrorMapper<Throwable>> provider6, Provider<AutoDiscoveryMetricsRecorder> provider7, Provider<DSSClient> provider8) {
        this.mDiscoverySettingsProvider = provider;
        this.mDeviceDiscoveryStreamProvider = provider2;
        this.mAutoDiscoverySyncCoordinatorProvider = provider3;
        this.mFFSArcusSyncCoordinatorProvider = provider4;
        this.mProvisionerClientDataProvider = provider5;
        this.mWJErrorMapperProvider = provider6;
        this.mMetricsRecorderProvider = provider7;
        this.mDssClientProvider = provider8;
    }

    public static MembersInjector<AutoDiscoveryPresenter> create(Provider<DiscoverySettings> provider, Provider<DeviceDiscoveryStream> provider2, Provider<CustomerProvisioneesSetupStatusSyncCoordinator> provider3, Provider<FFSArcusSyncCoordinator> provider4, Provider<ProvisionerClientData> provider5, Provider<WJErrorMapper<Throwable>> provider6, Provider<AutoDiscoveryMetricsRecorder> provider7, Provider<DSSClient> provider8) {
        return new AutoDiscoveryPresenter_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static void injectMAutoDiscoverySyncCoordinator(AutoDiscoveryPresenter autoDiscoveryPresenter, CustomerProvisioneesSetupStatusSyncCoordinator customerProvisioneesSetupStatusSyncCoordinator) {
        autoDiscoveryPresenter.mAutoDiscoverySyncCoordinator = customerProvisioneesSetupStatusSyncCoordinator;
    }

    public static void injectMDeviceDiscoveryStream(AutoDiscoveryPresenter autoDiscoveryPresenter, DeviceDiscoveryStream deviceDiscoveryStream) {
        autoDiscoveryPresenter.mDeviceDiscoveryStream = deviceDiscoveryStream;
    }

    public static void injectMDiscoverySettings(AutoDiscoveryPresenter autoDiscoveryPresenter, DiscoverySettings discoverySettings) {
        autoDiscoveryPresenter.mDiscoverySettings = discoverySettings;
    }

    public static void injectMDssClient(AutoDiscoveryPresenter autoDiscoveryPresenter, DSSClient dSSClient) {
        autoDiscoveryPresenter.mDssClient = dSSClient;
    }

    public static void injectMFFSArcusSyncCoordinator(AutoDiscoveryPresenter autoDiscoveryPresenter, FFSArcusSyncCoordinator fFSArcusSyncCoordinator) {
        autoDiscoveryPresenter.mFFSArcusSyncCoordinator = fFSArcusSyncCoordinator;
    }

    public static void injectMMetricsRecorder(AutoDiscoveryPresenter autoDiscoveryPresenter, AutoDiscoveryMetricsRecorder autoDiscoveryMetricsRecorder) {
        autoDiscoveryPresenter.mMetricsRecorder = autoDiscoveryMetricsRecorder;
    }

    public static void injectMProvisionerClientData(AutoDiscoveryPresenter autoDiscoveryPresenter, ProvisionerClientData provisionerClientData) {
        autoDiscoveryPresenter.mProvisionerClientData = provisionerClientData;
    }

    public static void injectMWJErrorMapper(AutoDiscoveryPresenter autoDiscoveryPresenter, WJErrorMapper<Throwable> wJErrorMapper) {
        autoDiscoveryPresenter.mWJErrorMapper = wJErrorMapper;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AutoDiscoveryPresenter autoDiscoveryPresenter) {
        injectMDiscoverySettings(autoDiscoveryPresenter, this.mDiscoverySettingsProvider.mo10268get());
        injectMDeviceDiscoveryStream(autoDiscoveryPresenter, this.mDeviceDiscoveryStreamProvider.mo10268get());
        injectMAutoDiscoverySyncCoordinator(autoDiscoveryPresenter, this.mAutoDiscoverySyncCoordinatorProvider.mo10268get());
        injectMFFSArcusSyncCoordinator(autoDiscoveryPresenter, this.mFFSArcusSyncCoordinatorProvider.mo10268get());
        injectMProvisionerClientData(autoDiscoveryPresenter, this.mProvisionerClientDataProvider.mo10268get());
        injectMWJErrorMapper(autoDiscoveryPresenter, this.mWJErrorMapperProvider.mo10268get());
        injectMMetricsRecorder(autoDiscoveryPresenter, this.mMetricsRecorderProvider.mo10268get());
        injectMDssClient(autoDiscoveryPresenter, this.mDssClientProvider.mo10268get());
    }
}
