package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system;

import android.os.Build;
import java.util.Locale;
@Deprecated
/* loaded from: classes13.dex */
public class AndroidDeviceDetails implements DeviceDetails {
    private final String carrier;

    public AndroidDeviceDetails(String str) {
        this.carrier = str;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.DeviceDetails
    public String carrier() {
        return this.carrier;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.DeviceDetails
    public Locale locale() {
        return Locale.getDefault();
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.DeviceDetails
    public String manufacturer() {
        return Build.MANUFACTURER;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.DeviceDetails
    public String model() {
        return Build.MODEL;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.DeviceDetails
    public String platform() {
        return "ANDROID";
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.DeviceDetails
    public String platformVersion() {
        return Build.VERSION.RELEASE;
    }
}
