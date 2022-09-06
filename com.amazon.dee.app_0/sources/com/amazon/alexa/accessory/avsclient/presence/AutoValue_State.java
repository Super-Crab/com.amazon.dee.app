package com.amazon.alexa.accessory.avsclient.presence;

import com.amazon.alexa.accessory.avsclient.presence.State;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.annotation.Nullable;
/* loaded from: classes.dex */
final class AutoValue_State extends State {
    private final ConnectionState connectionState;
    private final PresenceState presenceState;
    private final ProactivelyTargetable proactivelyTargetable;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class Builder extends State.Builder {
        private ConnectionState connectionState;
        private PresenceState presenceState;
        private ProactivelyTargetable proactivelyTargetable;

        @Override // com.amazon.alexa.accessory.avsclient.presence.State.Builder
        public State build() {
            String str = "";
            if (this.connectionState == null) {
                str = GeneratedOutlineSupport1.outline72(str, " connectionState");
            }
            if (str.isEmpty()) {
                return new AutoValue_State(this.connectionState, this.proactivelyTargetable, this.presenceState);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
        }

        @Override // com.amazon.alexa.accessory.avsclient.presence.State.Builder
        public State.Builder setConnectionState(ConnectionState connectionState) {
            if (connectionState != null) {
                this.connectionState = connectionState;
                return this;
            }
            throw new NullPointerException("Null connectionState");
        }

        @Override // com.amazon.alexa.accessory.avsclient.presence.State.Builder
        public State.Builder setPresenceState(@Nullable PresenceState presenceState) {
            this.presenceState = presenceState;
            return this;
        }

        @Override // com.amazon.alexa.accessory.avsclient.presence.State.Builder
        public State.Builder setProactivelyTargetable(@Nullable ProactivelyTargetable proactivelyTargetable) {
            this.proactivelyTargetable = proactivelyTargetable;
            return this;
        }
    }

    public boolean equals(Object obj) {
        ProactivelyTargetable proactivelyTargetable;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof State)) {
            return false;
        }
        State state = (State) obj;
        if (this.connectionState.equals(state.getConnectionState()) && ((proactivelyTargetable = this.proactivelyTargetable) != null ? proactivelyTargetable.equals(state.getProactivelyTargetable()) : state.getProactivelyTargetable() == null)) {
            PresenceState presenceState = this.presenceState;
            if (presenceState == null) {
                if (state.getPresenceState() == null) {
                    return true;
                }
            } else if (presenceState.equals(state.getPresenceState())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.amazon.alexa.accessory.avsclient.presence.State
    public ConnectionState getConnectionState() {
        return this.connectionState;
    }

    @Override // com.amazon.alexa.accessory.avsclient.presence.State
    @Nullable
    public PresenceState getPresenceState() {
        return this.presenceState;
    }

    @Override // com.amazon.alexa.accessory.avsclient.presence.State
    @Nullable
    public ProactivelyTargetable getProactivelyTargetable() {
        return this.proactivelyTargetable;
    }

    public int hashCode() {
        int hashCode = (this.connectionState.hashCode() ^ 1000003) * 1000003;
        ProactivelyTargetable proactivelyTargetable = this.proactivelyTargetable;
        int i = 0;
        int hashCode2 = (hashCode ^ (proactivelyTargetable == null ? 0 : proactivelyTargetable.hashCode())) * 1000003;
        PresenceState presenceState = this.presenceState;
        if (presenceState != null) {
            i = presenceState.hashCode();
        }
        return hashCode2 ^ i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("State{connectionState=");
        outline107.append(this.connectionState);
        outline107.append(", proactivelyTargetable=");
        outline107.append(this.proactivelyTargetable);
        outline107.append(", presenceState=");
        outline107.append(this.presenceState);
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    private AutoValue_State(ConnectionState connectionState, @Nullable ProactivelyTargetable proactivelyTargetable, @Nullable PresenceState presenceState) {
        this.connectionState = connectionState;
        this.proactivelyTargetable = proactivelyTargetable;
        this.presenceState = presenceState;
    }
}
