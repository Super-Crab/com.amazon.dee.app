package com.amazon.whisperjoin.common.sharedtypes.radios;

import com.amazon.whisperjoin.common.sharedtypes.ble.BleScanData;
import com.amazon.whisperjoin.common.sharedtypes.devices.AbstractPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.devices.DeviceFilter;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.RadioNotEnabledException;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.ScanException;
/* loaded from: classes13.dex */
public interface DiscoveryService {

    /* loaded from: classes13.dex */
    public interface BleScanDataCoalescer {
        DiscoveryResult coalesce(BleScanData bleScanData);
    }

    /* loaded from: classes13.dex */
    public static class DiscoveryResult {
        private AbstractPeripheralDeviceDetails mDeviceDetails;
        private Event mResult;

        public DiscoveryResult(Event event, AbstractPeripheralDeviceDetails abstractPeripheralDeviceDetails) {
            if (abstractPeripheralDeviceDetails != null) {
                this.mResult = event;
                this.mDeviceDetails = abstractPeripheralDeviceDetails;
                return;
            }
            throw new IllegalArgumentException("device must not be null");
        }

        public AbstractPeripheralDeviceDetails getDevice() {
            return this.mDeviceDetails;
        }

        public Event getEvent() {
            return this.mResult;
        }
    }

    /* loaded from: classes13.dex */
    public enum Event {
        WJ_DEVICE_ADDED,
        WJ_DEVICE_UPDATED,
        THIRD_PARTY_ADDED,
        THIRD_PARTY_UPDATED,
        ERROR
    }

    void start(DeviceFilter deviceFilter, BleScanDataCoalescer bleScanDataCoalescer, ScanningMode scanningMode) throws RadioNotEnabledException, ScanException;

    void stop();
}
