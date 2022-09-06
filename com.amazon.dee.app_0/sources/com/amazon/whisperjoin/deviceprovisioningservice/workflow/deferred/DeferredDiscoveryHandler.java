package com.amazon.whisperjoin.deviceprovisioningservice.workflow.deferred;

import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.util.Clock;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceDiscoveryEvent;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.Event;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
/* loaded from: classes13.dex */
public class DeferredDiscoveryHandler {
    private static final long MAX_OVERDUE_WINDOW_SECONDS = 30;
    private static final long MAX_WAIT_TIME_SECONDS = TimeUnit.SECONDS.convert(1, TimeUnit.DAYS);
    private static final long MIN_WAIT_TIME_SECONDS = 5;
    private static final String TAG = "DeferredDiscoveryHandler";
    private final Clock mClock;
    private final Map<DeferredDiscoveryCallDetails, Long> mDeferredDevices = new HashMap();

    public DeferredDiscoveryHandler(Clock clock) {
        this.mClock = clock;
    }

    private boolean isEligibleForRediscovery(DeviceDiscoveryEvent deviceDiscoveryEvent) {
        boolean z = false;
        if (!Event.State.UPDATE.equals(deviceDiscoveryEvent.getState())) {
            return false;
        }
        if (!(deviceDiscoveryEvent.getData() instanceof WhisperJoinPeripheralDeviceDetails)) {
            WJLog.d(TAG, "Unknown or null data type for discovery event");
            return false;
        }
        WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails = (WhisperJoinPeripheralDeviceDetails) deviceDiscoveryEvent.getData();
        DeferredDiscoveryCallDetails deferredDiscoveryCallDetails = new DeferredDiscoveryCallDetails(whisperJoinPeripheralDeviceDetails.getDeviceIdentity(), whisperJoinPeripheralDeviceDetails.getProductIndex(), whisperJoinPeripheralDeviceDetails.getClientNonce());
        Long l = this.mDeferredDevices.get(deferredDiscoveryCallDetails);
        if (l != null && l.longValue() <= this.mClock.epochTimeMillis()) {
            z = true;
        }
        if (z) {
            String str = TAG;
            WJLog.d(str, "Allowing re-discovery for: " + deferredDiscoveryCallDetails);
            this.mDeferredDevices.remove(deferredDiscoveryCallDetails);
        }
        return z;
    }

    private void removeExpired() {
        Iterator<Map.Entry<DeferredDiscoveryCallDetails, Long>> it2 = this.mDeferredDevices.entrySet().iterator();
        long epochTimeMillis = this.mClock.epochTimeMillis();
        while (it2.hasNext()) {
            if (epochTimeMillis - it2.next().getValue().longValue() > 30000) {
                it2.remove();
            }
        }
    }

    public boolean canProceed(DeviceDiscoveryEvent deviceDiscoveryEvent) {
        if (deviceDiscoveryEvent == null) {
            return false;
        }
        boolean isEligibleForRediscovery = isEligibleForRediscovery(deviceDiscoveryEvent);
        removeExpired();
        return isEligibleForRediscovery;
    }

    public void defer(WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails, long j) {
        if (whisperJoinPeripheralDeviceDetails == null || j < 0) {
            return;
        }
        long min = Math.min(Math.max(j, 5L), MAX_WAIT_TIME_SECONDS);
        long epochTimeMillis = (1000 * min) + this.mClock.epochTimeMillis();
        DeferredDiscoveryCallDetails deferredDiscoveryCallDetails = new DeferredDiscoveryCallDetails(whisperJoinPeripheralDeviceDetails.getDeviceIdentity(), whisperJoinPeripheralDeviceDetails.getProductIndex(), whisperJoinPeripheralDeviceDetails.getClientNonce());
        WJLog.d(TAG, String.format(Locale.ENGLISH, "Deferring check for %s for %d seconds. Expiry: %d", deferredDiscoveryCallDetails, Long.valueOf(min), Long.valueOf(epochTimeMillis)));
        this.mDeferredDevices.put(deferredDiscoveryCallDetails, Long.valueOf(epochTimeMillis));
    }
}
