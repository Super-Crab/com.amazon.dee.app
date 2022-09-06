package com.amazon.deecomms.nativemodules;

import android.telephony.TelephonyManager;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
/* loaded from: classes12.dex */
public class CarrierConfigurationBridge extends ReactContextBaseJavaModule {
    public CarrierConfigurationBridge(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private String getMobileCountryCodeAndMobileNetworkCode() {
        TelephonyManager telephonyManager = getTelephonyManager();
        if (telephonyManager != null) {
            return telephonyManager.getSimOperator();
        }
        return null;
    }

    private TelephonyManager getTelephonyManager() {
        return (TelephonyManager) getReactApplicationContext().getApplicationContext().getSystemService("phone");
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0010, code lost:
        if (r0.equals("") != false) goto L8;
     */
    @com.facebook.react.bridge.ReactMethod
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void getIsoCountryCode(com.facebook.react.bridge.Promise r3) {
        /*
            r2 = this;
            android.telephony.TelephonyManager r0 = r2.getTelephonyManager()
            if (r0 == 0) goto L12
            java.lang.String r0 = r0.getSimCountryIso()
            java.lang.String r1 = ""
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L13
        L12:
            r0 = 0
        L13:
            r3.resolve(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.deecomms.nativemodules.CarrierConfigurationBridge.getIsoCountryCode(com.facebook.react.bridge.Promise):void");
    }

    @ReactMethod
    public void getMobileCountryCode(Promise promise) {
        String mobileCountryCodeAndMobileNetworkCode = getMobileCountryCodeAndMobileNetworkCode();
        promise.resolve((mobileCountryCodeAndMobileNetworkCode == null || mobileCountryCodeAndMobileNetworkCode.length() <= 2) ? null : mobileCountryCodeAndMobileNetworkCode.substring(0, 3));
    }

    @ReactMethod
    public void getMobileNetworkCode(Promise promise) {
        String mobileCountryCodeAndMobileNetworkCode = getMobileCountryCodeAndMobileNetworkCode();
        promise.resolve((mobileCountryCodeAndMobileNetworkCode == null || mobileCountryCodeAndMobileNetworkCode.length() <= 3) ? null : mobileCountryCodeAndMobileNetworkCode.substring(3));
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "CarrierConfigurationBridge";
    }
}
