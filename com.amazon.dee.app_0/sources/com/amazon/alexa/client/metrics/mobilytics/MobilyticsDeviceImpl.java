package com.amazon.alexa.client.metrics.mobilytics;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.alexa.mobilytics.identity.MobilyticsDevice;
/* loaded from: classes6.dex */
public class MobilyticsDeviceImpl implements MobilyticsDevice {
    private final String deviceSerialNumber;
    private final String deviceType;

    /* loaded from: classes6.dex */
    public static class Builder {
        private String deviceSerialNumber;
        private String deviceType;

        public MobilyticsDeviceImpl build() {
            return new MobilyticsDeviceImpl(this);
        }

        public Builder withDeviceSerialNumber(String str) {
            this.deviceSerialNumber = str;
            return this;
        }

        public Builder withDeviceType(String str) {
            this.deviceType = str;
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsDevice
    @NonNull
    public String deviceSerialNumber() {
        String str = this.deviceSerialNumber;
        return (str == null || TextUtils.isEmpty(str)) ? "Unknown" : this.deviceSerialNumber;
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsDevice
    @NonNull
    public String deviceType() {
        String str = this.deviceType;
        return (str == null || TextUtils.isEmpty(str)) ? "Unknown" : this.deviceType;
    }

    private MobilyticsDeviceImpl(Builder builder) {
        this.deviceType = builder.deviceType;
        this.deviceSerialNumber = builder.deviceSerialNumber;
    }
}
