package com.amazon.comms.models.sip;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
/* loaded from: classes11.dex */
public class AudioMediaConfiguration {
    private AcousticEchoCancellation acousticEchoCancellation;
    private AutomaticGainControl automaticGainControl;
    private NoiseReduction noiseReduction;
    private ResidualEchoSuppression residualEchoSuppression;

    /* loaded from: classes11.dex */
    public static class AudioMediaConfigurationBuilder {
        private AcousticEchoCancellation acousticEchoCancellation;
        private AutomaticGainControl automaticGainControl;
        private NoiseReduction noiseReduction;
        private ResidualEchoSuppression residualEchoSuppression;

        AudioMediaConfigurationBuilder() {
        }

        public AudioMediaConfigurationBuilder acousticEchoCancellation(AcousticEchoCancellation acousticEchoCancellation) {
            this.acousticEchoCancellation = acousticEchoCancellation;
            return this;
        }

        public AudioMediaConfigurationBuilder automaticGainControl(AutomaticGainControl automaticGainControl) {
            this.automaticGainControl = automaticGainControl;
            return this;
        }

        public AudioMediaConfiguration build() {
            return new AudioMediaConfiguration(this.automaticGainControl, this.noiseReduction, this.acousticEchoCancellation, this.residualEchoSuppression);
        }

        public AudioMediaConfigurationBuilder noiseReduction(NoiseReduction noiseReduction) {
            this.noiseReduction = noiseReduction;
            return this;
        }

        public AudioMediaConfigurationBuilder residualEchoSuppression(ResidualEchoSuppression residualEchoSuppression) {
            this.residualEchoSuppression = residualEchoSuppression;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AudioMediaConfiguration.AudioMediaConfigurationBuilder(automaticGainControl=");
            outline107.append(this.automaticGainControl);
            outline107.append(", noiseReduction=");
            outline107.append(this.noiseReduction);
            outline107.append(", acousticEchoCancellation=");
            outline107.append(this.acousticEchoCancellation);
            outline107.append(", residualEchoSuppression=");
            outline107.append(this.residualEchoSuppression);
            outline107.append(")");
            return outline107.toString();
        }
    }

    public AudioMediaConfiguration() {
    }

    public static AudioMediaConfigurationBuilder builder() {
        return new AudioMediaConfigurationBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof AudioMediaConfiguration;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AudioMediaConfiguration)) {
            return false;
        }
        AudioMediaConfiguration audioMediaConfiguration = (AudioMediaConfiguration) obj;
        if (!audioMediaConfiguration.canEqual(this)) {
            return false;
        }
        AutomaticGainControl automaticGainControl = getAutomaticGainControl();
        AutomaticGainControl automaticGainControl2 = audioMediaConfiguration.getAutomaticGainControl();
        if (automaticGainControl != null ? !automaticGainControl.equals(automaticGainControl2) : automaticGainControl2 != null) {
            return false;
        }
        NoiseReduction noiseReduction = getNoiseReduction();
        NoiseReduction noiseReduction2 = audioMediaConfiguration.getNoiseReduction();
        if (noiseReduction != null ? !noiseReduction.equals(noiseReduction2) : noiseReduction2 != null) {
            return false;
        }
        AcousticEchoCancellation acousticEchoCancellation = getAcousticEchoCancellation();
        AcousticEchoCancellation acousticEchoCancellation2 = audioMediaConfiguration.getAcousticEchoCancellation();
        if (acousticEchoCancellation != null ? !acousticEchoCancellation.equals(acousticEchoCancellation2) : acousticEchoCancellation2 != null) {
            return false;
        }
        ResidualEchoSuppression residualEchoSuppression = getResidualEchoSuppression();
        ResidualEchoSuppression residualEchoSuppression2 = audioMediaConfiguration.getResidualEchoSuppression();
        return residualEchoSuppression != null ? residualEchoSuppression.equals(residualEchoSuppression2) : residualEchoSuppression2 == null;
    }

    public AcousticEchoCancellation getAcousticEchoCancellation() {
        return this.acousticEchoCancellation;
    }

    public AutomaticGainControl getAutomaticGainControl() {
        return this.automaticGainControl;
    }

    public NoiseReduction getNoiseReduction() {
        return this.noiseReduction;
    }

    public ResidualEchoSuppression getResidualEchoSuppression() {
        return this.residualEchoSuppression;
    }

    public int hashCode() {
        AutomaticGainControl automaticGainControl = getAutomaticGainControl();
        int i = 43;
        int hashCode = automaticGainControl == null ? 43 : automaticGainControl.hashCode();
        NoiseReduction noiseReduction = getNoiseReduction();
        int hashCode2 = ((hashCode + 59) * 59) + (noiseReduction == null ? 43 : noiseReduction.hashCode());
        AcousticEchoCancellation acousticEchoCancellation = getAcousticEchoCancellation();
        int hashCode3 = (hashCode2 * 59) + (acousticEchoCancellation == null ? 43 : acousticEchoCancellation.hashCode());
        ResidualEchoSuppression residualEchoSuppression = getResidualEchoSuppression();
        int i2 = hashCode3 * 59;
        if (residualEchoSuppression != null) {
            i = residualEchoSuppression.hashCode();
        }
        return i2 + i;
    }

    public void setAcousticEchoCancellation(AcousticEchoCancellation acousticEchoCancellation) {
        this.acousticEchoCancellation = acousticEchoCancellation;
    }

    public void setAutomaticGainControl(AutomaticGainControl automaticGainControl) {
        this.automaticGainControl = automaticGainControl;
    }

    public void setNoiseReduction(NoiseReduction noiseReduction) {
        this.noiseReduction = noiseReduction;
    }

    public void setResidualEchoSuppression(ResidualEchoSuppression residualEchoSuppression) {
        this.residualEchoSuppression = residualEchoSuppression;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AudioMediaConfiguration(automaticGainControl=");
        outline107.append(getAutomaticGainControl());
        outline107.append(", noiseReduction=");
        outline107.append(getNoiseReduction());
        outline107.append(", acousticEchoCancellation=");
        outline107.append(getAcousticEchoCancellation());
        outline107.append(", residualEchoSuppression=");
        outline107.append(getResidualEchoSuppression());
        outline107.append(")");
        return outline107.toString();
    }

    public AudioMediaConfiguration(AutomaticGainControl automaticGainControl, NoiseReduction noiseReduction, AcousticEchoCancellation acousticEchoCancellation, ResidualEchoSuppression residualEchoSuppression) {
        this.automaticGainControl = automaticGainControl;
        this.noiseReduction = noiseReduction;
        this.acousticEchoCancellation = acousticEchoCancellation;
        this.residualEchoSuppression = residualEchoSuppression;
    }
}
