package com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration;

import com.amazon.whisperjoin.common.sharedtypes.cryptography.TrustProvider;
import java.util.Objects;
import org.apache.commons.lang.builder.HashCodeBuilder;
/* loaded from: classes13.dex */
public class DeviceConnectionConfiguration {
    private String pin;
    private String publicKey;
    private final TrustProvider.TrustState trustState;

    /* loaded from: classes13.dex */
    public static class Builder {
        private String pin;
        private String publicKey;
        private TrustProvider.TrustState trustState;

        public DeviceConnectionConfiguration build() {
            TrustProvider.TrustState trustState = this.trustState;
            if (trustState != null) {
                return new DeviceConnectionConfiguration(trustState, this.pin, this.publicKey);
            }
            throw new IllegalArgumentException("trustState can not be null");
        }

        public Builder withPin(String str) {
            this.pin = str;
            return this;
        }

        public Builder withPublicKey(String str) {
            this.publicKey = str;
            return this;
        }

        public Builder withTrustState(TrustProvider.TrustState trustState) {
            this.trustState = trustState;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DeviceConnectionConfiguration.class != obj.getClass()) {
            return false;
        }
        DeviceConnectionConfiguration deviceConnectionConfiguration = (DeviceConnectionConfiguration) obj;
        return this.trustState.equals(deviceConnectionConfiguration.getTrustState()) && Objects.equals(this.pin, deviceConnectionConfiguration.getPin()) && Objects.equals(this.publicKey, deviceConnectionConfiguration.getPublicKey());
    }

    public String getPin() {
        return this.pin;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public TrustProvider.TrustState getTrustState() {
        return this.trustState;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getTrustState()).append(getPin()).append(getPublicKey()).toHashCode();
    }

    private DeviceConnectionConfiguration(TrustProvider.TrustState trustState, String str, String str2) {
        this.trustState = trustState;
        this.pin = str;
        this.publicKey = str2;
    }
}
