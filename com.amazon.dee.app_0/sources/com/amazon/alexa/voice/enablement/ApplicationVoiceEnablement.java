package com.amazon.alexa.voice.enablement;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.device.api.DeviceInformation;
import java.util.Set;
/* loaded from: classes11.dex */
final class ApplicationVoiceEnablement implements VoiceEnablement {
    private final Set<BlacklistableDevice> blacklistedDevices;
    private final DeviceInformation deviceInformation;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApplicationVoiceEnablement(DeviceInformation deviceInformation) {
        this(deviceInformation, new DefaultDeviceBlacklist());
    }

    @Override // com.amazon.alexa.voice.enablement.VoiceEnablement
    public boolean isVoicePossible() {
        if (this.deviceInformation.isFireOS()) {
            return false;
        }
        return !this.blacklistedDevices.contains(BlacklistableDevice.instance(this.deviceInformation.getManufacturer(), this.deviceInformation.getModel()));
    }

    @VisibleForTesting
    ApplicationVoiceEnablement(DeviceInformation deviceInformation, DeviceBlacklist deviceBlacklist) {
        this.deviceInformation = deviceInformation;
        this.blacklistedDevices = deviceBlacklist.getBlacklistedDevices();
    }
}
