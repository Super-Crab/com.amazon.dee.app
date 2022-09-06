package com.amazon.whisperjoin.common.sharedtypes.utility;

import com.amazon.whisperjoin.common.sharedtypes.configuration.RadioConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.devices.DeviceFilter;
import com.amazon.whisperjoin.common.sharedtypes.radios.DiscoveryService;
import com.amazon.whisperjoin.common.sharedtypes.radios.ScanningMode;
import java.util.ArrayList;
import java.util.Collection;
/* loaded from: classes13.dex */
public class DiscoveryController {
    private static final String TAG = "com.amazon.whisperjoin.common.sharedtypes.utility.DiscoveryController";
    private final DiscoveryService.BleScanDataCoalescer mBleScanDataCoalescer;
    private final DeviceFilter mDeviceFilter;
    private final Collection<RadioConfiguration> mRadioConfigurations;
    private final ScanningMode mScanningMode;

    public DiscoveryController(DeviceFilter deviceFilter, Collection<RadioConfiguration> collection, DiscoveryService.BleScanDataCoalescer bleScanDataCoalescer, ScanningMode scanningMode) {
        if (deviceFilter != null) {
            if (collection == null || collection.size() == 0) {
                throw new IllegalArgumentException("radioConfigurations can not be null or empty");
            }
            if (bleScanDataCoalescer == null) {
                throw new IllegalArgumentException("bleScanDataCoalescer can not be null");
            }
            if (scanningMode != null) {
                this.mDeviceFilter = deviceFilter;
                this.mRadioConfigurations = new ArrayList(collection);
                this.mBleScanDataCoalescer = bleScanDataCoalescer;
                this.mScanningMode = scanningMode;
                return;
            }
            throw new IllegalArgumentException("scanningMode can not be null");
        }
        throw new IllegalArgumentException("device filter can not be null");
    }

    public void startDiscovery() {
        try {
            for (RadioConfiguration radioConfiguration : this.mRadioConfigurations) {
                DiscoveryService discoveryService = radioConfiguration.getDiscoveryService();
                if (discoveryService != null) {
                    discoveryService.start(this.mDeviceFilter, this.mBleScanDataCoalescer, this.mScanningMode);
                } else {
                    throw new RuntimeException("discoveryService should never be null");
                }
            }
        } catch (Exception e) {
            WJLog.e(TAG, "Error starting discovery", e);
        }
    }

    public void stopDiscovery() {
        try {
            for (RadioConfiguration radioConfiguration : this.mRadioConfigurations) {
                DiscoveryService discoveryService = radioConfiguration.getDiscoveryService();
                if (discoveryService != null) {
                    discoveryService.stop();
                } else {
                    throw new RuntimeException("discoveryService should never be null");
                }
            }
        } catch (Exception e) {
            WJLog.e(TAG, "Error stopping discovery", e);
        }
    }
}
