package com.amazon.alexa.devices.settings.location;

import android.text.TextUtils;
/* loaded from: classes6.dex */
public class DeviceLocationBuilder {
    private String mAddress;
    private String mCity;
    private String mCountryCode;
    private String mPostalCode;
    private String mState;

    public DeviceLocationBuilder(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mPostalCode = str;
            return;
        }
        throw new IllegalArgumentException("Postal Code cannot be null");
    }

    public DeviceLocation build() {
        return new DeviceLocation(this.mPostalCode, this.mAddress, this.mCity, this.mState, this.mCountryCode);
    }

    public DeviceLocationBuilder setAddress(String str) {
        this.mAddress = str;
        return this;
    }

    public DeviceLocationBuilder setCity(String str) {
        this.mCity = str;
        return this;
    }

    public DeviceLocationBuilder setCountryCode(String str) {
        this.mCountryCode = str;
        return this;
    }

    public DeviceLocationBuilder setState(String str) {
        this.mState = str;
        return this;
    }
}
