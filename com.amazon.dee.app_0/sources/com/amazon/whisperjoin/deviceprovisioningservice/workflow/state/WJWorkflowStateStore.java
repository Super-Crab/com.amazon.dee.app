package com.amazon.whisperjoin.deviceprovisioningservice.workflow.state;

import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result.WJResult;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.DiscoveredProvisionable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.MoreObjects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes13.dex */
public class WJWorkflowStateStore {
    private static final String TAG = "WJWorkflowStateStore";
    private final Map<WJProvisionee, DeviceSession> mActiveSessions;
    private final List<DiscoveredProvisionable> mDiscoveredDevices;
    private final boolean mIsDiscoveryActive;
    private final WJResult mLastWJResult;

    /* loaded from: classes13.dex */
    public static class Mutator {
        private Map<WJProvisionee, DeviceSession> mActiveSessions;
        private List<DiscoveredProvisionable> mDiscoveredDevices;
        private boolean mIsDiscoveryActive;
        private WJResult mLastEvent;

        public Mutator(WJWorkflowStateStore wJWorkflowStateStore) {
            this.mLastEvent = wJWorkflowStateStore.mLastWJResult;
            this.mDiscoveredDevices = new ArrayList(wJWorkflowStateStore.mDiscoveredDevices);
            this.mIsDiscoveryActive = wJWorkflowStateStore.mIsDiscoveryActive;
            this.mActiveSessions = new HashMap(wJWorkflowStateStore.mActiveSessions);
        }

        private void assertSessionExists(WJProvisionee wJProvisionee) {
            if (this.mActiveSessions.containsKey(wJProvisionee)) {
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No session for ");
            outline107.append(wJProvisionee.toString());
            throw new IllegalStateException(outline107.toString());
        }

        private DeviceSession createSession(DiscoveredProvisionable discoveredProvisionable) {
            if (!this.mActiveSessions.containsKey(discoveredProvisionable.getWJProvisionee())) {
                DeviceSession deviceSession = new DeviceSession(discoveredProvisionable);
                this.mActiveSessions.put(discoveredProvisionable.getWJProvisionee(), deviceSession);
                return deviceSession;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Session already exists for ");
            outline107.append(discoveredProvisionable.getWJProvisionee().toString());
            throw new IllegalStateException(outline107.toString());
        }

        public Mutator addDiscoveredDevice(DiscoveredProvisionable discoveredProvisionable) {
            Iterator<DiscoveredProvisionable> it2 = this.mDiscoveredDevices.iterator();
            while (it2.hasNext()) {
                WJProvisionee wJProvisionee = it2.next().getWJProvisionee();
                if (wJProvisionee.getPeripheralDeviceDetails().getDeviceIdentity().equals(discoveredProvisionable.getWJProvisionee().getPeripheralDeviceDetails().getDeviceIdentity())) {
                    String str = WJWorkflowStateStore.TAG;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Removing discovered device old entry for wjProvisionee: ");
                    outline107.append(wJProvisionee.getPeripheralDeviceDetails().getDeviceIdentity());
                    WJLog.d(str, outline107.toString());
                    it2.remove();
                }
            }
            this.mDiscoveredDevices.add(0, discoveredProvisionable);
            createSession(discoveredProvisionable);
            return this;
        }

        public Mutator addDiscoveredDevices(List<DiscoveredProvisionable> list) {
            for (DiscoveredProvisionable discoveredProvisionable : list) {
                addDiscoveredDevice(discoveredProvisionable);
            }
            return this;
        }

        public Mutator clearDiscoveredDevices() {
            this.mDiscoveredDevices.clear();
            return this;
        }

        public WJWorkflowStateStore create() {
            return new WJWorkflowStateStore(this.mLastEvent, this.mDiscoveredDevices, this.mIsDiscoveryActive, this.mActiveSessions);
        }

        public Mutator removeSession(WJProvisionee wJProvisionee) {
            if (this.mActiveSessions.remove(wJProvisionee) == null) {
                WJLog.w(WJWorkflowStateStore.TAG, "Attempted to remove session that was not found");
            }
            return this;
        }

        public Mutator reset() {
            this.mActiveSessions.clear();
            this.mDiscoveredDevices.clear();
            this.mIsDiscoveryActive = false;
            return this;
        }

        public Mutator setDiscoveryActive(boolean z) {
            this.mIsDiscoveryActive = z;
            return this;
        }

        public Mutator setLastResult(WJResult wJResult) {
            this.mLastEvent = wJResult;
            return this;
        }

        public Mutator updateSession(WJProvisionee wJProvisionee, DeviceSession deviceSession) {
            assertSessionExists(wJProvisionee);
            this.mActiveSessions.put(wJProvisionee, deviceSession);
            return this;
        }
    }

    public Iterator<Map.Entry<WJProvisionee, DeviceSession>> getActiveSessions() {
        return Collections.unmodifiableSet(this.mActiveSessions.entrySet()).iterator();
    }

    public List<DiscoveredProvisionable> getDiscoveredDevices() {
        return this.mDiscoveredDevices;
    }

    public WJResult getLastWJResult() {
        return this.mLastWJResult;
    }

    public DeviceSession getSession(WJProvisionee wJProvisionee) {
        return this.mActiveSessions.get(wJProvisionee);
    }

    public boolean isDiscoveryActive() {
        return this.mIsDiscoveryActive;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mLastWJResult", this.mLastWJResult).add("mDiscoveredDevices", this.mDiscoveredDevices).add("mIsDiscoveryActive", this.mIsDiscoveryActive).add("mActiveSessions", this.mActiveSessions).toString();
    }

    public WJWorkflowStateStore() {
        this(null, new ArrayList(), false, new HashMap());
    }

    private WJWorkflowStateStore(WJResult wJResult, List<DiscoveredProvisionable> list, boolean z, Map<WJProvisionee, DeviceSession> map) {
        if (list != null) {
            if (map != null) {
                this.mLastWJResult = wJResult;
                this.mDiscoveredDevices = list;
                this.mActiveSessions = map;
                this.mIsDiscoveryActive = z;
                return;
            }
            throw new IllegalArgumentException("Active Sessions can't be null");
        }
        throw new IllegalArgumentException("DiscoveredDevices can't be null");
    }
}
