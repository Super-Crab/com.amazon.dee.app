package com.amazon.whisperjoin.deviceprovisioningservice.device;

import android.bluetooth.le.ScanFilter;
import android.content.Context;
import com.amazon.whisperjoin.common.sharedtypes.configuration.OveractiveConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.RadioNotEnabledException;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.ScanException;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.DeviceEvent;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.DiscoveryEvent;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.ProvisioningEvent;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import com.amazon.whisperjoin.provisionerSDK.ProvisioningManager;
import com.amazon.whisperjoin.provisionerSDK.configuration.defaults.ProvisionerSDKConfiguration;
import com.amazon.whisperjoin.provisionerSDK.devices.AmazonPeripheralDevice;
import com.amazon.whisperjoin.provisionerSDK.utility.Observers;
import java.util.List;
/* loaded from: classes13.dex */
public class ProvisioningManagerProvider {
    private final Observers<ProvisioningEvent<DeviceEvent>> mDeviceEventObservers;
    private final Observers<ProvisioningEvent<DiscoveryEvent>> mDiscoveryEventObservers;
    private final MetricsRecorderProvider mMetricsRecorderProvider;
    private final ProvisioningManager mProvisioningManager;

    public ProvisioningManagerProvider(Context context, DSSClient dSSClient, MetricsRecorderProvider metricsRecorderProvider, List<ScanFilter> list, OveractiveConfiguration overactiveConfiguration) {
        this.mDeviceEventObservers = new Observers<>();
        this.mDiscoveryEventObservers = new Observers<>();
        this.mProvisioningManager = buildManager(context, dSSClient, list, overactiveConfiguration);
        this.mMetricsRecorderProvider = metricsRecorderProvider;
    }

    private ProvisioningManager buildManager(Context context, DSSClient dSSClient, List<ScanFilter> list, OveractiveConfiguration overactiveConfiguration) {
        return new ProvisioningManager.ProvisioningManagerBuilder().withRadioConfiguration(new ProvisionerSDKConfiguration.ConfigurationBuilder().withContext(context).withDiscoveryObservers(this.mDiscoveryEventObservers).withDeviceObservers(this.mDeviceEventObservers).withScanFilters(list).withDSSClient(dSSClient).withOveractiveConfiguration(overactiveConfiguration).build()).build();
    }

    public void addDeviceEventObserver(Observers.RunnableEvent<ProvisioningEvent<DeviceEvent>> runnableEvent) {
        this.mDeviceEventObservers.addObserver(runnableEvent);
    }

    public void addDiscoveryEventObserver(Observers.RunnableEvent<ProvisioningEvent<DiscoveryEvent>> runnableEvent) {
        this.mDiscoveryEventObservers.addObserver(runnableEvent);
    }

    public WJProvisionee createWJProvsionee(WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails) {
        if (whisperJoinPeripheralDeviceDetails.getRadios().iterator().hasNext()) {
            return new WJProvisionee((AmazonPeripheralDevice) this.mProvisioningManager.createPeripheralDevice(whisperJoinPeripheralDeviceDetails, whisperJoinPeripheralDeviceDetails.getRadios().iterator().next()), this.mMetricsRecorderProvider);
        }
        throw new IllegalStateException("Expected device to have 1 radio");
    }

    public void pauseDiscovery() {
        this.mProvisioningManager.pauseDiscovery();
    }

    public void removeDeviceEventObserver(Observers.RunnableEvent<ProvisioningEvent<DeviceEvent>> runnableEvent) {
        this.mDeviceEventObservers.removeObserver(runnableEvent);
    }

    public void removeDiscoveryEventObserver(Observers.RunnableEvent<ProvisioningEvent<DiscoveryEvent>> runnableEvent) {
        this.mDiscoveryEventObservers.removeObserver(runnableEvent);
    }

    public void resumeDiscovery() {
        this.mProvisioningManager.resumeDiscovery();
    }

    public void startDiscovery(DiscoverySettings discoverySettings) throws RadioNotEnabledException, ScanException {
        this.mProvisioningManager.startPeripheralDeviceDiscovery(discoverySettings.getDeviceFilter(), discoverySettings.getScanningMode());
    }

    public void stopDiscovery() {
        this.mProvisioningManager.stopPeripheralDeviceDiscovery();
    }

    ProvisioningManagerProvider(ProvisioningManager provisioningManager, MetricsRecorderProvider metricsRecorderProvider) {
        this.mProvisioningManager = provisioningManager;
        this.mDeviceEventObservers = new Observers<>();
        this.mDiscoveryEventObservers = new Observers<>();
        this.mMetricsRecorderProvider = metricsRecorderProvider;
    }

    ProvisioningManagerProvider(ProvisioningManager provisioningManager, Observers<ProvisioningEvent<DiscoveryEvent>> observers, Observers<ProvisioningEvent<DeviceEvent>> observers2, MetricsRecorderProvider metricsRecorderProvider) {
        this.mDiscoveryEventObservers = observers;
        this.mDeviceEventObservers = observers2;
        this.mProvisioningManager = provisioningManager;
        this.mMetricsRecorderProvider = metricsRecorderProvider;
    }
}
