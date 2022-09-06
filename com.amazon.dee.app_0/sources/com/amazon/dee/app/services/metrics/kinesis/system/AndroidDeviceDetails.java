package com.amazon.dee.app.services.metrics.kinesis.system;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.amazon.dee.app.services.logging.Log;
import java.util.Locale;
/* loaded from: classes12.dex */
public class AndroidDeviceDetails implements DeviceDetails {
    private static final String TAG = Log.tag(AndroidDeviceDetails.class);
    private final Context context;

    public AndroidDeviceDetails(Context context) {
        this.context = context;
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.system.DeviceDetails
    public String carrier() {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) this.context.getSystemService("phone");
            if (TextUtils.isEmpty(telephonyManager.getNetworkOperatorName())) {
                return null;
            }
            return telephonyManager.getNetworkOperatorName();
        } catch (Exception e) {
            Log.w(TAG, "Failed to retrieve carrier name with error: %s", e);
            return null;
        }
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.system.DeviceDetails
    public String getCountry() {
        return Locale.getDefault() != null ? Locale.getDefault().toString().split("_").length > 1 ? Locale.getDefault().toString().split("_")[1] : "" : "Unknown";
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.system.DeviceDetails
    public String getLanguage() {
        return Locale.getDefault() != null ? Locale.getDefault().toString().split("_").length > 0 ? Locale.getDefault().toString().split("_")[0] : "" : "Unknown";
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.system.DeviceDetails
    public String locale() {
        return Locale.getDefault() != null ? Locale.getDefault().toString() : "Unknown";
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.system.DeviceDetails
    public String manufacturer() {
        return Build.MANUFACTURER;
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.system.DeviceDetails
    public String model() {
        return Build.MODEL;
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.system.DeviceDetails
    public String platform() {
        return "ANDROID";
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.system.DeviceDetails
    public String platformVersion() {
        return Build.VERSION.RELEASE;
    }
}
