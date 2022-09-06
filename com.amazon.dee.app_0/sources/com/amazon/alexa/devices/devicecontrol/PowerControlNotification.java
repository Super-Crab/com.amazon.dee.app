package com.amazon.alexa.devices.devicecontrol;
/* loaded from: classes6.dex */
public class PowerControlNotification {
    private PowerState mTargetState;

    public PowerControlNotification(PowerState powerState) {
        this.mTargetState = powerState;
    }

    public PowerState getTargetState() {
        return this.mTargetState;
    }
}
