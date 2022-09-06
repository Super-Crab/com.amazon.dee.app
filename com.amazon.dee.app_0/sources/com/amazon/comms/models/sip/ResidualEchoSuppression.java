package com.amazon.comms.models.sip;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NonNull;
@JsonIgnoreProperties(ignoreUnknown = true)
/* loaded from: classes11.dex */
public class ResidualEchoSuppression {
    @NonNull
    TxRxMediaConfiguration configuration;

    /* loaded from: classes11.dex */
    public static class ResidualEchoSuppressionBuilder {
        private TxRxMediaConfiguration configuration;

        ResidualEchoSuppressionBuilder() {
        }

        public ResidualEchoSuppression build() {
            return new ResidualEchoSuppression(this.configuration);
        }

        public ResidualEchoSuppressionBuilder configuration(TxRxMediaConfiguration txRxMediaConfiguration) {
            this.configuration = txRxMediaConfiguration;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ResidualEchoSuppression.ResidualEchoSuppressionBuilder(configuration=");
            outline107.append(this.configuration);
            outline107.append(")");
            return outline107.toString();
        }
    }

    public ResidualEchoSuppression() {
    }

    public static ResidualEchoSuppressionBuilder builder() {
        return new ResidualEchoSuppressionBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof ResidualEchoSuppression;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ResidualEchoSuppression)) {
            return false;
        }
        ResidualEchoSuppression residualEchoSuppression = (ResidualEchoSuppression) obj;
        if (!residualEchoSuppression.canEqual(this)) {
            return false;
        }
        TxRxMediaConfiguration configuration = getConfiguration();
        TxRxMediaConfiguration configuration2 = residualEchoSuppression.getConfiguration();
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
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ResidualEchoSuppression(configuration=");
        outline107.append(getConfiguration());
        outline107.append(")");
        return outline107.toString();
    }

    public ResidualEchoSuppression(@NonNull TxRxMediaConfiguration txRxMediaConfiguration) {
        if (txRxMediaConfiguration != null) {
            this.configuration = txRxMediaConfiguration;
            return;
        }
        throw new IllegalArgumentException("configuration is null");
    }
}
