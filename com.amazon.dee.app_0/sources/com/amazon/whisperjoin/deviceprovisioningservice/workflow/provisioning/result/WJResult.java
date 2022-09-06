package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.registration.CBLRegistrationDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConnectionDetails;
import com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ConnectionOperationStatusUpdate;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.DiscoveredProvisionable;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisionableConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningDetails;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.Event;
import java.util.List;
/* loaded from: classes13.dex */
public abstract class WJResult<T> extends Event<T> {
    public final WJProvisionee mWJProvisionee;

    /* loaded from: classes13.dex */
    public static final class Connection extends WJResult<ConnectionOperationStatusUpdate> {
        private Connection(WJProvisionee wJProvisionee, ConnectionOperationStatusUpdate connectionOperationStatusUpdate, Event.State state, Throwable th) {
            super(wJProvisionee, connectionOperationStatusUpdate, state, th);
        }

        public static Connection error(WJProvisionee wJProvisionee, ConnectionOperationStatusUpdate connectionOperationStatusUpdate, Throwable th) {
            return new Connection(wJProvisionee, connectionOperationStatusUpdate, Event.State.ERROR, th);
        }

        public static Connection inProgress(WJProvisionee wJProvisionee, ConnectionOperationStatusUpdate connectionOperationStatusUpdate) {
            return new Connection(wJProvisionee, connectionOperationStatusUpdate, Event.State.IN_PROGRESS, null);
        }

        public static Connection success(WJProvisionee wJProvisionee, ConnectionOperationStatusUpdate connectionOperationStatusUpdate) {
            return new Connection(wJProvisionee, connectionOperationStatusUpdate, Event.State.SUCCESS, null);
        }

        public static Connection update(WJProvisionee wJProvisionee, ConnectionOperationStatusUpdate connectionOperationStatusUpdate) {
            return new Connection(wJProvisionee, connectionOperationStatusUpdate, Event.State.UPDATE, null);
        }
    }

    /* loaded from: classes13.dex */
    public static final class Disconnection extends WJResult<Void> {
        private Disconnection(WJProvisionee wJProvisionee, Event.State state, Throwable th) {
            super(wJProvisionee, null, state, th);
        }

        public static Disconnection error(WJProvisionee wJProvisionee, Throwable th) {
            return new Disconnection(wJProvisionee, Event.State.ERROR, th);
        }

        public static Disconnection success(WJProvisionee wJProvisionee) {
            return new Disconnection(wJProvisionee, Event.State.SUCCESS, null);
        }
    }

    /* loaded from: classes13.dex */
    public static final class Discovery extends WJResult<List<DiscoveredProvisionable>> {
        private Discovery(WJProvisionee wJProvisionee, List<DiscoveredProvisionable> list, Event.State state, Throwable th) {
            super(wJProvisionee, list, state, th);
        }

        public static Discovery error(Throwable th) {
            return new Discovery(null, null, Event.State.ERROR, th);
        }

        public static Discovery idle() {
            return new Discovery(null, null, Event.State.IDLE, null);
        }

        public static Discovery inProgress() {
            return new Discovery(null, null, Event.State.IN_PROGRESS, null);
        }

        public static Discovery success(List<DiscoveredProvisionable> list) {
            return new Discovery(null, list, Event.State.SUCCESS, null);
        }
    }

    /* loaded from: classes13.dex */
    public static final class GetProvisioningDetails extends WJResult<ProvisioningDetails> {
        private GetProvisioningDetails(WJProvisionee wJProvisionee, ProvisioningDetails provisioningDetails, Event.State state, Throwable th) {
            super(wJProvisionee, provisioningDetails, state, th);
        }

        public static GetProvisioningDetails error(WJProvisionee wJProvisionee, Throwable th, ProvisioningDetails provisioningDetails) {
            return new GetProvisioningDetails(wJProvisionee, provisioningDetails, Event.State.ERROR, th);
        }

        public static GetProvisioningDetails inProgress(WJProvisionee wJProvisionee) {
            return new GetProvisioningDetails(wJProvisionee, null, Event.State.IN_PROGRESS, null);
        }

        public static GetProvisioningDetails success(WJProvisionee wJProvisionee, ProvisioningDetails provisioningDetails) {
            return new GetProvisioningDetails(wJProvisionee, provisioningDetails, Event.State.SUCCESS, null);
        }
    }

    /* loaded from: classes13.dex */
    public static final class NetworkScanComplete extends WJResult<Void> {
        private NetworkScanComplete(WJProvisionee wJProvisionee, Event.State state) {
            super(wJProvisionee, null, state, null);
        }

        public static NetworkScanComplete update(WJProvisionee wJProvisionee) {
            return new NetworkScanComplete(wJProvisionee, Event.State.UPDATE);
        }
    }

    /* loaded from: classes13.dex */
    public static final class ProvisionDevice extends WJResult<ProvisionableConfiguration> {
        private ProvisionDevice(WJProvisionee wJProvisionee, ProvisionableConfiguration provisionableConfiguration, Event.State state, Throwable th) {
            super(wJProvisionee, provisionableConfiguration, state, th);
        }

        public static ProvisionDevice error(WJProvisionee wJProvisionee, Throwable th) {
            return new ProvisionDevice(wJProvisionee, null, Event.State.ERROR, th);
        }

        public static ProvisionDevice inProgress(WJProvisionee wJProvisionee) {
            return new ProvisionDevice(wJProvisionee, null, Event.State.IN_PROGRESS, null);
        }

        public static ProvisionDevice success(WJProvisionee wJProvisionee, ProvisionableConfiguration provisionableConfiguration) {
            return new ProvisionDevice(wJProvisionee, provisionableConfiguration, Event.State.SUCCESS, null);
        }
    }

    /* loaded from: classes13.dex */
    public static final class ProvisioningDoneStateChange extends WJResult<Void> {
        private ProvisioningDoneStateChange(WJProvisionee wJProvisionee, Event.State state, Throwable th) {
            super(wJProvisionee, null, state, th);
        }

        public static ProvisioningDoneStateChange error(WJProvisionee wJProvisionee, Throwable th) {
            return new ProvisioningDoneStateChange(wJProvisionee, Event.State.ERROR, th);
        }

        public static ProvisioningDoneStateChange success(WJProvisionee wJProvisionee) {
            return new ProvisioningDoneStateChange(wJProvisionee, Event.State.SUCCESS, null);
        }
    }

    /* loaded from: classes13.dex */
    public static final class RegistrationStateChange extends WJResult<CBLRegistrationDetails> {
        private RegistrationStateChange(WJProvisionee wJProvisionee, CBLRegistrationDetails cBLRegistrationDetails, Event.State state) {
            super(wJProvisionee, cBLRegistrationDetails, state, null);
        }

        public static RegistrationStateChange update(WJProvisionee wJProvisionee, CBLRegistrationDetails cBLRegistrationDetails) {
            return new RegistrationStateChange(wJProvisionee, cBLRegistrationDetails, Event.State.UPDATE);
        }
    }

    /* loaded from: classes13.dex */
    public static final class VerifyProvisioning extends WJResult<ProvisionableConfiguration> {
        private VerifyProvisioning(WJProvisionee wJProvisionee, ProvisionableConfiguration provisionableConfiguration, Event.State state, Throwable th) {
            super(wJProvisionee, provisionableConfiguration, state, th);
        }

        public static VerifyProvisioning error(WJProvisionee wJProvisionee, Throwable th) {
            return new VerifyProvisioning(wJProvisionee, null, Event.State.ERROR, th);
        }

        public static VerifyProvisioning inProgress(WJProvisionee wJProvisionee) {
            return new VerifyProvisioning(wJProvisionee, null, Event.State.IN_PROGRESS, null);
        }

        public static VerifyProvisioning success(WJProvisionee wJProvisionee, ProvisionableConfiguration provisionableConfiguration) {
            return new VerifyProvisioning(wJProvisionee, provisionableConfiguration, Event.State.SUCCESS, null);
        }
    }

    /* loaded from: classes13.dex */
    public static final class WifiConnectionStateChange extends WJResult<WifiConnectionDetails> {
        private WifiConnectionStateChange(WJProvisionee wJProvisionee, WifiConnectionDetails wifiConnectionDetails, Event.State state) {
            super(wJProvisionee, wifiConnectionDetails, state, null);
        }

        public static WifiConnectionStateChange update(WJProvisionee wJProvisionee, WifiConnectionDetails wifiConnectionDetails) {
            return new WifiConnectionStateChange(wJProvisionee, wifiConnectionDetails, Event.State.UPDATE);
        }
    }

    /* loaded from: classes13.dex */
    public static final class WorkflowIdle extends WJResult<Void> {
        private WorkflowIdle(Event.State state, Throwable th) {
            super(null, null, state, th);
        }

        public static WorkflowIdle error(Throwable th) {
            return new WorkflowIdle(Event.State.ERROR, th);
        }

        public static WorkflowIdle success() {
            return new WorkflowIdle(Event.State.SUCCESS, null);
        }
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.Event
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        WJProvisionee wJProvisionee = this.mWJProvisionee;
        WJProvisionee wJProvisionee2 = ((WJResult) obj).mWJProvisionee;
        return wJProvisionee != null ? wJProvisionee.equals(wJProvisionee2) : wJProvisionee2 == null;
    }

    public WJProvisionee getWJProvisionee() {
        return this.mWJProvisionee;
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.Event
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        WJProvisionee wJProvisionee = this.mWJProvisionee;
        return hashCode + (wJProvisionee != null ? wJProvisionee.hashCode() : 0);
    }

    public boolean isA(Class<? extends WJResult> cls) {
        return getClass() == cls;
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.Event
    public String toString() {
        return getClass().getSimpleName() + EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN + super.toString() + "}  mWJProvisionee=" + this.mWJProvisionee + "\n";
    }

    private WJResult(WJProvisionee wJProvisionee, T t, Event.State state, Throwable th) {
        super(t, state, th);
        this.mWJProvisionee = wJProvisionee;
    }
}
