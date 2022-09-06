package com.amazon.whisperjoin.provisionerSDK;

import com.amazon.whisperbridge.ble.AdvertisementData;
import com.amazon.whisperjoin.common.sharedtypes.configuration.RadioConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.devices.DeviceFilter;
import com.amazon.whisperjoin.common.sharedtypes.devices.DiscoveredRadio;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.PeripheralDevice;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.RadioNotEnabledException;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.ScanException;
import com.amazon.whisperjoin.common.sharedtypes.radios.ScanningMode;
import com.amazon.whisperjoin.common.sharedtypes.radios.SupportedRadios;
import com.amazon.whisperjoin.common.sharedtypes.utility.DiscoveryController;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.provisionerSDK.devices.PeripheralDeviceDetailsScanDataProcessor;
import com.amazon.whisperjoin.provisionerSDK.utility.BleScanDataCoalescer;
import java.util.ArrayList;
import java.util.Collection;
/* loaded from: classes13.dex */
public class ProvisioningManager {
    private static final String TAG = "com.amazon.whisperjoin.provisionerSDK.ProvisioningManager";
    private DiscoveryController mDiscoveryController;
    private final Collection<RadioConfiguration> mRadioConfiguration;

    /* loaded from: classes13.dex */
    public static class ProvisioningManagerBuilder {
        private Collection<RadioConfiguration> mRadioConfiguration = new ArrayList();

        public ProvisioningManager build() {
            return new ProvisioningManager(this);
        }

        public ProvisioningManagerBuilder withRadioConfiguration(Collection<RadioConfiguration> collection) {
            if (collection != null) {
                if (!collection.isEmpty()) {
                    this.mRadioConfiguration = new ArrayList(collection);
                    return this;
                }
                throw new IllegalArgumentException("RadioConfiguration must have at least one interface");
            }
            throw new IllegalArgumentException("RadioConfiguration must not be null");
        }
    }

    ProvisioningManager(ProvisioningManagerBuilder provisioningManagerBuilder) {
        this.mRadioConfiguration = provisioningManagerBuilder.mRadioConfiguration;
    }

    public <DeviceType extends PeripheralDevice> DeviceType createPeripheralDevice(WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails, DiscoveredRadio discoveredRadio) {
        RadioConfiguration next = this.mRadioConfiguration.iterator().next();
        if (next != null && next.getConnectionFactory() != null) {
            try {
                return (DeviceType) next.getConnectionFactory().create(whisperJoinPeripheralDeviceDetails, discoveredRadio);
            } catch (ClassCastException e) {
                WJLog.e(TAG, "unable to create requested device interface", e);
                return null;
            }
        }
        throw new IllegalArgumentException("Provisioning manager does not have a connection factory");
    }

    public void pauseDiscovery() {
        DiscoveryController discoveryController = this.mDiscoveryController;
        if (discoveryController == null) {
            WJLog.w(TAG, "Can't pause discovery if not active");
        } else {
            discoveryController.stopDiscovery();
        }
    }

    public void resumeDiscovery() {
        DiscoveryController discoveryController = this.mDiscoveryController;
        if (discoveryController == null) {
            WJLog.w(TAG, "Must start first before pause/resume can be called");
        } else {
            discoveryController.startDiscovery();
        }
    }

    public void startPeripheralDeviceDiscovery(DeviceFilter deviceFilter, ScanningMode scanningMode) throws RadioNotEnabledException, ScanException {
        if (deviceFilter != null) {
            if (scanningMode != null) {
                if (!scanningMode.equals(ScanningMode.PROHIBITED)) {
                    RadioConfiguration next = this.mRadioConfiguration.iterator().next();
                    if (next.getRadioType().equals(SupportedRadios.BLE.getString())) {
                        this.mDiscoveryController = new DiscoveryController(deviceFilter, this.mRadioConfiguration, new BleScanDataCoalescer(new PeripheralDeviceDetailsScanDataProcessor(next.getConnectionFactory(), new AdvertisementData.Factory())), scanningMode);
                        this.mDiscoveryController.startDiscovery();
                        return;
                    }
                    throw new IllegalStateException("only expect BLE radio configuration");
                }
                throw new ScanException("Prohibited from scanning");
            }
            throw new IllegalArgumentException("scanningMode can not be null");
        }
        throw new IllegalArgumentException("deviceFilter can not be null");
    }

    public void stopPeripheralDeviceDiscovery() {
        DiscoveryController discoveryController = this.mDiscoveryController;
        if (discoveryController == null) {
            WJLog.w(TAG, "Stop discovery called while discovery isn't active");
            return;
        }
        discoveryController.stopDiscovery();
        this.mDiscoveryController = null;
    }
}
