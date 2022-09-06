package com.amazon.communication.watchdog;

import amazon.communication.connection.Policy;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.EndpointIdentityFactory;
import com.amazon.communication.NetworkStabilityMonitor;
import com.amazon.communication.ScreenEventListener;
import com.amazon.communication.socket.ConnectReason;
import com.amazon.communication.socket.ProtocolSocket;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public final class SystemSnapshot {
    protected final ConnectReason mConnectReason;
    protected final boolean mConnectionThrottled;
    protected final EndpointIdentity mEndpoint;
    protected final boolean mIsConnectivityPossible;
    protected final NetworkStabilityMonitor.NetworkStabilityState mNetworkStabilityState;
    protected final Policy mPolicy;
    protected final ProtocolSocket mProtocolSocket;
    protected final ProtocolSocket.ProtocolSocketState mProtocolSocketState;
    protected final ScreenEventListener.Event mScreenState;
    protected final boolean mSmartSuspendEnabled;
    protected final boolean mSmartSuspendRadioOn;
    protected final boolean mSocketAcquisitionFailed;
    protected final TriggerType mTriggerId;

    /* loaded from: classes12.dex */
    public static class Builder {
        protected TriggerType mTriggerId = TriggerType.NONE;
        protected boolean mIsConnectivityPossible = false;
        protected ProtocolSocket.ProtocolSocketState mProtocolSocketState = ProtocolSocket.ProtocolSocketState.UNKNOWN;
        protected boolean mSocketAcquisitionFailed = false;
        protected ScreenEventListener.Event mScreenState = ScreenEventListener.Event.OFF;
        protected NetworkStabilityMonitor.NetworkStabilityState mNetworkStabilityState = NetworkStabilityMonitor.NetworkStabilityState.UNKNOWN;
        protected boolean mSmartSuspendEnabled = false;
        protected boolean mSmartSuspendRadioOn = false;
        protected ConnectReason mConnectReason = new ConnectReason(ConnectReason.ReasonString.ServiceStarted, 1);
        protected Policy mPolicy = new Policy.Builder().build();
        protected EndpointIdentity mEndpoint = EndpointIdentityFactory.createUrlEndpointIdentity("Endpoint");
        private boolean mBuilt = false;
        protected boolean mConnectionThrottled = false;
        protected ProtocolSocket mProtocolSocket = null;

        private void enforceSingleBuild() {
            if (!this.mBuilt) {
                return;
            }
            throw new IllegalStateException("Each builder can only be used once. Create a new builder");
        }

        public SystemSnapshot build() {
            enforceSingleBuild();
            this.mBuilt = true;
            TriggerType triggerType = this.mTriggerId;
            if (triggerType != TriggerType.NONE) {
                return new SystemSnapshot(triggerType, this.mIsConnectivityPossible, this.mProtocolSocketState, this.mSocketAcquisitionFailed, this.mScreenState, this.mNetworkStabilityState, this.mSmartSuspendEnabled, this.mSmartSuspendRadioOn, this.mConnectReason, this.mPolicy, this.mEndpoint, this.mConnectionThrottled, this.mProtocolSocket);
            }
            throw new IllegalStateException("trigger cannot be W_TRIGGER.NONE, you must specify a valid one");
        }

        public Builder setConnectReason(ConnectReason connectReason) {
            enforceSingleBuild();
            this.mConnectReason = connectReason;
            return this;
        }

        public Builder setConnectionThrottled(boolean z) {
            enforceSingleBuild();
            this.mConnectionThrottled = z;
            return this;
        }

        public Builder setEndpoint(EndpointIdentity endpointIdentity) {
            enforceSingleBuild();
            this.mEndpoint = endpointIdentity;
            return this;
        }

        public Builder setIsConnectivityPossible(boolean z) {
            enforceSingleBuild();
            this.mIsConnectivityPossible = z;
            return this;
        }

        public Builder setNetworkStabilityState(NetworkStabilityMonitor.NetworkStabilityState networkStabilityState) {
            enforceSingleBuild();
            this.mNetworkStabilityState = networkStabilityState;
            return this;
        }

        public Builder setPolicy(Policy policy) {
            enforceSingleBuild();
            this.mPolicy = policy;
            return this;
        }

        public Builder setProtocolSocket(ProtocolSocket protocolSocket) {
            enforceSingleBuild();
            this.mProtocolSocket = protocolSocket;
            return this;
        }

        public Builder setProtocolSocketState(ProtocolSocket.ProtocolSocketState protocolSocketState) {
            enforceSingleBuild();
            this.mProtocolSocketState = protocolSocketState;
            return this;
        }

        public Builder setScreenState(ScreenEventListener.Event event) {
            enforceSingleBuild();
            this.mScreenState = event;
            return this;
        }

        public Builder setSmartSuspendEnabled(boolean z) {
            enforceSingleBuild();
            this.mSmartSuspendEnabled = z;
            return this;
        }

        public Builder setSmartSuspendRadioOn(boolean z) {
            enforceSingleBuild();
            this.mSmartSuspendRadioOn = z;
            return this;
        }

        public Builder setSocketAcquisitionFailed(boolean z) {
            enforceSingleBuild();
            this.mSocketAcquisitionFailed = z;
            return this;
        }

        public Builder setTriggerId(TriggerType triggerType) {
            enforceSingleBuild();
            this.mTriggerId = triggerType;
            return this;
        }
    }

    public SystemSnapshot(TriggerType triggerType, boolean z, ProtocolSocket.ProtocolSocketState protocolSocketState, boolean z2, ScreenEventListener.Event event, NetworkStabilityMonitor.NetworkStabilityState networkStabilityState, boolean z3, boolean z4, ConnectReason connectReason, Policy policy, EndpointIdentity endpointIdentity, boolean z5, ProtocolSocket protocolSocket) {
        this.mTriggerId = triggerType;
        this.mIsConnectivityPossible = z;
        this.mProtocolSocketState = protocolSocketState;
        this.mSocketAcquisitionFailed = z2;
        this.mScreenState = event;
        this.mNetworkStabilityState = networkStabilityState;
        this.mSmartSuspendEnabled = z3;
        this.mSmartSuspendRadioOn = z4;
        this.mConnectReason = connectReason;
        this.mPolicy = policy;
        this.mEndpoint = endpointIdentity;
        this.mConnectionThrottled = z5;
        this.mProtocolSocket = protocolSocket;
    }

    public ConnectReason getConnectReason() {
        return this.mConnectReason;
    }

    public boolean getConnectionThrottled() {
        return this.mConnectionThrottled;
    }

    public EndpointIdentity getEndpoint() {
        return this.mEndpoint;
    }

    public boolean getIsConnectivityPossible() {
        return this.mIsConnectivityPossible;
    }

    public NetworkStabilityMonitor.NetworkStabilityState getNetworkStabilityState() {
        return this.mNetworkStabilityState;
    }

    public Policy getPolicy() {
        return this.mPolicy;
    }

    public ProtocolSocket getProtocolSocket() {
        return this.mProtocolSocket;
    }

    public ProtocolSocket.ProtocolSocketState getProtocolSocketState() {
        return this.mProtocolSocketState;
    }

    public ScreenEventListener.Event getScreenState() {
        return this.mScreenState;
    }

    public boolean getSmartSuspendEnabled() {
        return this.mSmartSuspendEnabled;
    }

    public boolean getSmartSuspendRadioOn() {
        return this.mSmartSuspendRadioOn;
    }

    public boolean getSocketAcquisitionFailed() {
        return this.mSocketAcquisitionFailed;
    }

    public TriggerType getTriggerId() {
        return this.mTriggerId;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("(TriggerId:");
        outline107.append(this.mTriggerId.name());
        outline107.append(";");
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(GeneratedOutlineSupport1.outline97(GeneratedOutlineSupport1.outline113(outline107.toString(), "IsConnectivityPossible:"), this.mIsConnectivityPossible, ";"), "ProtocolSocketState:");
        outline113.append(this.mProtocolSocketState.name());
        outline113.append(";");
        StringBuilder outline1132 = GeneratedOutlineSupport1.outline113(GeneratedOutlineSupport1.outline97(GeneratedOutlineSupport1.outline113(outline113.toString(), "SocketAcquisitionFailed:"), this.mSocketAcquisitionFailed, ";"), "ScreenState:");
        outline1132.append(this.mScreenState.name());
        outline1132.append(";");
        StringBuilder outline1133 = GeneratedOutlineSupport1.outline113(outline1132.toString(), "NetworkStabilityState:");
        outline1133.append(this.mNetworkStabilityState.name());
        outline1133.append(";");
        StringBuilder outline1134 = GeneratedOutlineSupport1.outline113(GeneratedOutlineSupport1.outline97(GeneratedOutlineSupport1.outline113(GeneratedOutlineSupport1.outline97(GeneratedOutlineSupport1.outline113(outline1133.toString(), "SmartSuspendEnabled:"), this.mSmartSuspendEnabled, ";"), "SmartSuspendRadioOn:"), this.mSmartSuspendRadioOn, ";"), "ConnectReasonString:");
        outline1134.append(this.mConnectReason);
        outline1134.append(";");
        StringBuilder outline1135 = GeneratedOutlineSupport1.outline113(outline1134.toString(), "Policy:");
        outline1135.append(this.mPolicy);
        outline1135.append(";");
        StringBuilder outline1136 = GeneratedOutlineSupport1.outline113(outline1135.toString(), "Endpoint:");
        outline1136.append(EndpointIdentity.logSafe(this.mEndpoint));
        outline1136.append(";");
        StringBuilder outline1137 = GeneratedOutlineSupport1.outline113(GeneratedOutlineSupport1.outline97(GeneratedOutlineSupport1.outline113(outline1136.toString(), "ConnectionThrottled:"), this.mConnectionThrottled, ";"), "ProtocolSocket:");
        outline1137.append(this.mProtocolSocket);
        outline1137.append(";");
        return outline1137.toString();
    }
}
