package com.amazon.alexa.accessory.avsclient.presence;

import com.amazon.alexa.accessory.avsclient.presence.AutoValue_State;
import com.google.auto.value.AutoValue;
import javax.annotation.Nullable;
@AutoValue
/* loaded from: classes.dex */
public abstract class State {

    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class Builder {
        public abstract State build();

        public abstract Builder setConnectionState(ConnectionState connectionState);

        public abstract Builder setPresenceState(PresenceState presenceState);

        public abstract Builder setProactivelyTargetable(ProactivelyTargetable proactivelyTargetable);
    }

    public static Builder builder() {
        return new AutoValue_State.Builder();
    }

    public abstract ConnectionState getConnectionState();

    @Nullable
    public abstract PresenceState getPresenceState();

    @Nullable
    public abstract ProactivelyTargetable getProactivelyTargetable();
}
