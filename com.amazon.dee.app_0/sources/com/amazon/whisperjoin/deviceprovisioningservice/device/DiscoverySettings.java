package com.amazon.whisperjoin.deviceprovisioningservice.device;

import com.amazon.whisperjoin.common.sharedtypes.devices.DeviceFilter;
import com.amazon.whisperjoin.common.sharedtypes.radios.ScanningMode;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class DiscoverySettings {
    private final DeviceFilter mDeviceFilter;
    private final int mLimitForNumberOfDiscoveredDevices;
    private final ScanningMode mScanningMode;

    public DiscoverySettings(DeviceFilter deviceFilter, ScanningMode scanningMode, int i) {
        if (deviceFilter != null) {
            if (scanningMode == null) {
                throw new IllegalArgumentException("scanningMode can not be null");
            }
            if (i > 0) {
                this.mDeviceFilter = deviceFilter;
                this.mScanningMode = scanningMode;
                this.mLimitForNumberOfDiscoveredDevices = i;
                return;
            }
            throw new IllegalArgumentException("limitForNumberOfDiscoveredDevices must be greater than zero");
        }
        throw new IllegalArgumentException("deviceFilter can not be null");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DiscoverySettings.class != obj.getClass()) {
            return false;
        }
        DiscoverySettings discoverySettings = (DiscoverySettings) obj;
        return this.mLimitForNumberOfDiscoveredDevices == discoverySettings.mLimitForNumberOfDiscoveredDevices && Objects.equal(this.mDeviceFilter, discoverySettings.mDeviceFilter) && this.mScanningMode == discoverySettings.mScanningMode;
    }

    public DeviceFilter getDeviceFilter() {
        return this.mDeviceFilter;
    }

    public int getLimitForNumberOfDiscoveredDevices() {
        return this.mLimitForNumberOfDiscoveredDevices;
    }

    public ScanningMode getScanningMode() {
        return this.mScanningMode;
    }

    public int hashCode() {
        return Objects.hashCode(this.mDeviceFilter, this.mScanningMode, Integer.valueOf(this.mLimitForNumberOfDiscoveredDevices));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mDeviceFilter", this.mDeviceFilter).add("mScanningMode", this.mScanningMode).add("mLimitForNumberOfDiscoveredDevices", this.mLimitForNumberOfDiscoveredDevices).toString();
    }
}
