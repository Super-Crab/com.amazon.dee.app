package com.amazon.alexa.client.alexaservice.componentstate;

import com.amazon.alexa.client.core.componentstate.ComponentStatePayload;
/* loaded from: classes6.dex */
public final class AutoValue_ComponentState extends ComponentState {
    public final ComponentStateHeader header;
    public final ComponentStatePayload payload;

    public AutoValue_ComponentState(ComponentStateHeader componentStateHeader, ComponentStatePayload componentStatePayload) {
        if (componentStateHeader != null) {
            this.header = componentStateHeader;
            if (componentStatePayload != null) {
                this.payload = componentStatePayload;
                return;
            }
            throw new NullPointerException("Null payload");
        }
        throw new NullPointerException("Null header");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ComponentState)) {
            return false;
        }
        ComponentState componentState = (ComponentState) obj;
        return this.header.equals(componentState.getHeader()) && this.payload.equals(componentState.getPayload());
    }

    @Override // com.amazon.alexa.client.alexaservice.componentstate.ComponentState
    public ComponentStateHeader getHeader() {
        return this.header;
    }

    @Override // com.amazon.alexa.client.alexaservice.componentstate.ComponentState
    public ComponentStatePayload getPayload() {
        return this.payload;
    }

    public int hashCode() {
        return ((this.header.hashCode() ^ 1000003) * 1000003) ^ this.payload.hashCode();
    }
}
