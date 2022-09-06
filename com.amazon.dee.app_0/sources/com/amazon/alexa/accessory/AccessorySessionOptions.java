package com.amazon.alexa.accessory;

import com.amazon.alexa.accessory.capabilities.Capability;
import java.util.List;
/* loaded from: classes.dex */
public class AccessorySessionOptions {
    private List<Capability> supportedCapabilities;
    private boolean useUnsecureLowEnergyConnection = false;
    private boolean deviceKnown = false;
    private boolean forceConnection = false;

    public List<Capability> getSupportedCapabilities() {
        return this.supportedCapabilities;
    }

    public boolean isDeviceKnown() {
        return this.deviceKnown;
    }

    public void setDeviceKnown(boolean z) {
        this.deviceKnown = z;
    }

    public void setForceConnection(boolean z) {
        this.forceConnection = z;
    }

    public void setSupportedCapabilities(List<Capability> list) {
        this.supportedCapabilities = list;
    }

    public boolean shouldForceConnection() {
        return this.forceConnection;
    }

    public boolean shouldUseUnsecureLowEnergyConnection() {
        return this.useUnsecureLowEnergyConnection;
    }

    public void useUnsecureLowEnergyConnection(boolean z) {
        this.useUnsecureLowEnergyConnection = z;
    }
}
