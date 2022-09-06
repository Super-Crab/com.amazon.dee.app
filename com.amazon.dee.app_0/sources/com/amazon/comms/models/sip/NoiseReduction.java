package com.amazon.comms.models.sip;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NonNull;
@JsonIgnoreProperties(ignoreUnknown = true)
/* loaded from: classes11.dex */
public class NoiseReduction {
    @NonNull
    private TxRxMediaConfiguration receiveConfiguration;
    @NonNull
    private TxRxMediaConfiguration transmitConfiguration;

    /* loaded from: classes11.dex */
    public static class NoiseReductionBuilder {
        private TxRxMediaConfiguration receiveConfiguration;
        private TxRxMediaConfiguration transmitConfiguration;

        NoiseReductionBuilder() {
        }

        public NoiseReduction build() {
            return new NoiseReduction(this.transmitConfiguration, this.receiveConfiguration);
        }

        public NoiseReductionBuilder receiveConfiguration(TxRxMediaConfiguration txRxMediaConfiguration) {
            this.receiveConfiguration = txRxMediaConfiguration;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("NoiseReduction.NoiseReductionBuilder(transmitConfiguration=");
            outline107.append(this.transmitConfiguration);
            outline107.append(", receiveConfiguration=");
            outline107.append(this.receiveConfiguration);
            outline107.append(")");
            return outline107.toString();
        }

        public NoiseReductionBuilder transmitConfiguration(TxRxMediaConfiguration txRxMediaConfiguration) {
            this.transmitConfiguration = txRxMediaConfiguration;
            return this;
        }
    }

    public NoiseReduction() {
    }

    public static NoiseReductionBuilder builder() {
        return new NoiseReductionBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof NoiseReduction;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NoiseReduction)) {
            return false;
        }
        NoiseReduction noiseReduction = (NoiseReduction) obj;
        if (!noiseReduction.canEqual(this)) {
            return false;
        }
        TxRxMediaConfiguration transmitConfiguration = getTransmitConfiguration();
        TxRxMediaConfiguration transmitConfiguration2 = noiseReduction.getTransmitConfiguration();
        if (transmitConfiguration != null ? !transmitConfiguration.equals(transmitConfiguration2) : transmitConfiguration2 != null) {
            return false;
        }
        TxRxMediaConfiguration receiveConfiguration = getReceiveConfiguration();
        TxRxMediaConfiguration receiveConfiguration2 = noiseReduction.getReceiveConfiguration();
        return receiveConfiguration != null ? receiveConfiguration.equals(receiveConfiguration2) : receiveConfiguration2 == null;
    }

    @NonNull
    public TxRxMediaConfiguration getReceiveConfiguration() {
        return this.receiveConfiguration;
    }

    @NonNull
    public TxRxMediaConfiguration getTransmitConfiguration() {
        return this.transmitConfiguration;
    }

    public int hashCode() {
        TxRxMediaConfiguration transmitConfiguration = getTransmitConfiguration();
        int i = 43;
        int hashCode = transmitConfiguration == null ? 43 : transmitConfiguration.hashCode();
        TxRxMediaConfiguration receiveConfiguration = getReceiveConfiguration();
        int i2 = (hashCode + 59) * 59;
        if (receiveConfiguration != null) {
            i = receiveConfiguration.hashCode();
        }
        return i2 + i;
    }

    public void setReceiveConfiguration(@NonNull TxRxMediaConfiguration txRxMediaConfiguration) {
        if (txRxMediaConfiguration != null) {
            this.receiveConfiguration = txRxMediaConfiguration;
            return;
        }
        throw new IllegalArgumentException("receiveConfiguration is null");
    }

    public void setTransmitConfiguration(@NonNull TxRxMediaConfiguration txRxMediaConfiguration) {
        if (txRxMediaConfiguration != null) {
            this.transmitConfiguration = txRxMediaConfiguration;
            return;
        }
        throw new IllegalArgumentException("transmitConfiguration is null");
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("NoiseReduction(transmitConfiguration=");
        outline107.append(getTransmitConfiguration());
        outline107.append(", receiveConfiguration=");
        outline107.append(getReceiveConfiguration());
        outline107.append(")");
        return outline107.toString();
    }

    public NoiseReduction(@NonNull TxRxMediaConfiguration txRxMediaConfiguration, @NonNull TxRxMediaConfiguration txRxMediaConfiguration2) {
        if (txRxMediaConfiguration != null) {
            if (txRxMediaConfiguration2 == null) {
                throw new IllegalArgumentException("receiveConfiguration is null");
            }
            this.transmitConfiguration = txRxMediaConfiguration;
            this.receiveConfiguration = txRxMediaConfiguration2;
            return;
        }
        throw new IllegalArgumentException("transmitConfiguration is null");
    }
}
