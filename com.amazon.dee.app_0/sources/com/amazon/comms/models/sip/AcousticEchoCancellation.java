package com.amazon.comms.models.sip;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NonNull;
@JsonIgnoreProperties(ignoreUnknown = true)
/* loaded from: classes11.dex */
public class AcousticEchoCancellation {
    @NonNull
    TxRxMediaConfiguration configuration;

    /* loaded from: classes11.dex */
    public static class AcousticEchoCancellationBuilder {
        private TxRxMediaConfiguration configuration;

        AcousticEchoCancellationBuilder() {
        }

        public AcousticEchoCancellation build() {
            return new AcousticEchoCancellation(this.configuration);
        }

        public AcousticEchoCancellationBuilder configuration(TxRxMediaConfiguration txRxMediaConfiguration) {
            this.configuration = txRxMediaConfiguration;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AcousticEchoCancellation.AcousticEchoCancellationBuilder(configuration=");
            outline107.append(this.configuration);
            outline107.append(")");
            return outline107.toString();
        }
    }

    public AcousticEchoCancellation() {
    }

    public static AcousticEchoCancellationBuilder builder() {
        return new AcousticEchoCancellationBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof AcousticEchoCancellation;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AcousticEchoCancellation)) {
            return false;
        }
        AcousticEchoCancellation acousticEchoCancellation = (AcousticEchoCancellation) obj;
        if (!acousticEchoCancellation.canEqual(this)) {
            return false;
        }
        TxRxMediaConfiguration configuration = getConfiguration();
        TxRxMediaConfiguration configuration2 = acousticEchoCancellation.getConfiguration();
        return configuration != null ? configuration.equals(configuration2) : configuration2 == null;
    }

    @NonNull
    public TxRxMediaConfiguration getConfiguration() {
        return this.configuration;
    }

    public int hashCode() {
        TxRxMediaConfiguration configuration = getConfiguration();
        return 59 + (configuration == null ? 43 : configuration.hashCode());
    }

    public void setConfiguration(@NonNull TxRxMediaConfiguration txRxMediaConfiguration) {
        if (txRxMediaConfiguration != null) {
            this.configuration = txRxMediaConfiguration;
            return;
        }
        throw new IllegalArgumentException("configuration is null");
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AcousticEchoCancellation(configuration=");
        outline107.append(getConfiguration());
        outline107.append(")");
        return outline107.toString();
    }

    public AcousticEchoCancellation(@NonNull TxRxMediaConfiguration txRxMediaConfiguration) {
        if (txRxMediaConfiguration != null) {
            this.configuration = txRxMediaConfiguration;
            return;
        }
        throw new IllegalArgumentException("configuration is null");
    }
}
