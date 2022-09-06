package com.amazon.alexa.api;

import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.utils.validation.Preconditions;
import java.util.Objects;
/* loaded from: classes6.dex */
public class AudioDevice {
    private String friendlyName;
    private String physicalAddress;
    public static String DEFAULT_PHYSICAL_ADDRESS = "INTERNAL";
    public static String DEFAULT_FRIENDLY_NAME = "DEFAULT_OUTPUT";
    public static AudioDevice DEFAULT = new AudioDevice(DEFAULT_PHYSICAL_ADDRESS, DEFAULT_FRIENDLY_NAME);

    private AudioDevice(@NonNull String str, @NonNull String str2) {
        Preconditions.notNull(str, "Physical address cannot be null");
        Preconditions.notNull(str2, "Friendly name cannot be null");
        this.physicalAddress = str;
        this.friendlyName = str2;
    }

    public static AudioDevice create(@NonNull String str, @NonNull String str2) {
        return new AudioDevice(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AudioDevice)) {
            return false;
        }
        AudioDevice audioDevice = (AudioDevice) obj;
        return getPhysicalAddress().equals(audioDevice.getPhysicalAddress()) && getFriendlyName().equals(audioDevice.getFriendlyName());
    }

    public String getFriendlyName() {
        return this.friendlyName;
    }

    public String getPhysicalAddress() {
        return this.physicalAddress;
    }

    public int hashCode() {
        return Objects.hash(getPhysicalAddress(), getFriendlyName());
    }
}
