package com.amazon.alexa.api;

import com.amazon.alexa.client.annotations.Nullable;
import java.util.Objects;
/* loaded from: classes6.dex */
public class AudioOutputContext {
    public static AudioOutputContext DEFAULT = new AudioOutputContext(AudioDevice.DEFAULT, false);
    private AudioDevice audioDevice;
    private boolean shouldPlayOverSco;

    public AudioOutputContext(@Nullable AudioDevice audioDevice, boolean z) {
        this.audioDevice = audioDevice == null ? AudioDevice.DEFAULT : audioDevice;
        this.shouldPlayOverSco = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AudioOutputContext)) {
            return false;
        }
        AudioOutputContext audioOutputContext = (AudioOutputContext) obj;
        return this.shouldPlayOverSco == audioOutputContext.shouldPlayOverSco && this.audioDevice.equals(audioOutputContext.audioDevice);
    }

    public AudioDevice getDevice() {
        return this.audioDevice;
    }

    public int hashCode() {
        return Objects.hash(this.audioDevice, Boolean.valueOf(this.shouldPlayOverSco));
    }

    public boolean shouldPlayOverSco() {
        return this.shouldPlayOverSco;
    }
}
