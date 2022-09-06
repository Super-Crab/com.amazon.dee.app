package com.amazon.alexa.devices.attentionstate;
/* loaded from: classes6.dex */
public class AttentionStateNotification {
    private State mAttentionState;

    public AttentionStateNotification(State state) {
        this.mAttentionState = state;
    }

    public State getAttentionState() {
        return this.mAttentionState;
    }
}
