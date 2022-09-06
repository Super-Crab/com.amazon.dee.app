package com.amazon.comms.models.sip;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
/* loaded from: classes11.dex */
public class MediaConfiguration {
    private AudioMediaConfiguration audio;

    /* loaded from: classes11.dex */
    public static class MediaConfigurationBuilder {
        private AudioMediaConfiguration audio;

        MediaConfigurationBuilder() {
        }

        public MediaConfigurationBuilder audio(AudioMediaConfiguration audioMediaConfiguration) {
            this.audio = audioMediaConfiguration;
            return this;
        }

        public MediaConfiguration build() {
            return new MediaConfiguration(this.audio);
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MediaConfiguration.MediaConfigurationBuilder(audio=");
            outline107.append(this.audio);
            outline107.append(")");
            return outline107.toString();
        }
    }

    public MediaConfiguration(AudioMediaConfiguration audioMediaConfiguration) {
        this.audio = audioMediaConfiguration;
    }

    public static MediaConfigurationBuilder builder() {
        return new MediaConfigurationBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof MediaConfiguration;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MediaConfiguration)) {
            return false;
        }
        MediaConfiguration mediaConfiguration = (MediaConfiguration) obj;
        if (!mediaConfiguration.canEqual(this)) {
            return false;
        }
        AudioMediaConfiguration audio = getAudio();
        AudioMediaConfiguration audio2 = mediaConfiguration.getAudio();
        return audio != null ? audio.equals(audio2) : audio2 == null;
    }

    public AudioMediaConfiguration getAudio() {
        return this.audio;
    }

    public int hashCode() {
        AudioMediaConfiguration audio = getAudio();
        return 59 + (audio == null ? 43 : audio.hashCode());
    }

    public void setAudio(AudioMediaConfiguration audioMediaConfiguration) {
        this.audio = audioMediaConfiguration;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MediaConfiguration(audio=");
        outline107.append(getAudio());
        outline107.append(")");
        return outline107.toString();
    }

    public MediaConfiguration() {
    }
}
