package com.amazon.alexa.handsfree.devices;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.constants.Carrier;
import com.amazon.alexa.handsfree.devices.constants.Manufacturer;
import com.amazon.alexa.handsfree.devices.constants.VoiceApp;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes8.dex */
public class AMPDInformationProvider {
    public static final String DEVICE_CARRIER_KEY = "carrier";
    public static final String DEVICE_KEY = "device";
    public static final String DEVICE_MANUFACTURER_KEY = "manufacturer";
    public static final String DEVICE_MODEL_KEY = "model";
    public static final String DEVICE_PRODUCT_KEY = "product";
    public static final String DEVICE_TYPE_KEY = "type";
    private static final int MINIMUM_SUPPORTED_ANDROID_VERSION = 26;
    private static final String NULL_STRING = "null";
    @VisibleForTesting
    static final int QVA_TRUE_TURN_KEY_MIN_VERSION_CODE = 301001;
    @VisibleForTesting
    static final int QVA_TRUE_TURN_KEY_STUB_VERSION_CODE = 301000;
    @VisibleForTesting
    static final String VESPER_PACKAGE = "com.verizon.mips.services";
    private static AMPDInformationProvider mInstance;
    private DeviceInformation mDeviceInformation;
    private final Map<String, String> mDeviceInformationMap;
    private final PackageManager mPackageManager;

    @VisibleForTesting
    AMPDInformationProvider(@NonNull Context context, @NonNull DeviceTypeInformationProvider deviceTypeInformationProvider, @NonNull Map<String, String> map) {
        this.mDeviceInformation = deviceTypeInformationProvider.getSupportedDeviceInformation(context);
        this.mDeviceInformationMap = map;
        this.mPackageManager = context.getPackageManager();
    }

    public static synchronized AMPDInformationProvider getInstance(@NonNull Context context) {
        AMPDInformationProvider aMPDInformationProvider;
        synchronized (AMPDInformationProvider.class) {
            if (mInstance == null) {
                mInstance = new AMPDInformationProvider(context, DeviceTypeInformationProvider.getInstance(context), new HashMap());
            }
            aMPDInformationProvider = mInstance;
        }
        return aMPDInformationProvider;
    }

    private boolean isRootedBinaryOnDevice() {
        for (String str : new String[]{"/system/app/Superuser.apk", "/system/bin/failsafe/su", "/system/bin/su", "/system/xbin/su", "/system/xbin/which su", "/system/bin/which su", "/system/usr/we-need-root/su-backup"}) {
            if (new File(str).exists()) {
                return true;
            }
        }
        return false;
    }

    private boolean isVendorRootedDevice() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    @NonNull
    public Map<String, String> getDeviceInformation() {
        if (this.mDeviceInformation != null && this.mDeviceInformationMap.isEmpty()) {
            this.mDeviceInformationMap.put("type", this.mDeviceInformation.getType());
            this.mDeviceInformationMap.put("model", this.mDeviceInformation.getModel());
            this.mDeviceInformationMap.put("manufacturer", this.mDeviceInformation.getManufacturer());
            this.mDeviceInformationMap.put(DEVICE_PRODUCT_KEY, this.mDeviceInformation.getProduct());
            this.mDeviceInformationMap.put("device", this.mDeviceInformation.getDevice());
            this.mDeviceInformationMap.put(DEVICE_CARRIER_KEY, this.mDeviceInformation.getCarrier());
        }
        return this.mDeviceInformationMap;
    }

    public boolean getDeviceRootStatus() {
        return isRootedBinaryOnDevice() || isVendorRootedDevice();
    }

    @Nullable
    public PackageInfo getPackageInfoIfEnabled(@NonNull String str) {
        try {
            if (this.mPackageManager.getApplicationInfo(str, 0).enabled) {
                return this.mPackageManager.getPackageInfo(str, 0);
            }
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public String getSettingsComponent() {
        return null;
    }

    public String getSetupComponent() {
        return null;
    }

    @Nullable
    public String getVoiceAppPackageName() {
        if (isHandsFreeCapable()) {
            return this.mDeviceInformation.getVoiceApp().getPackageName();
        }
        return null;
    }

    @NonNull
    public String getVoiceAppPackageNameString() {
        return isHandsFreeCapable() ? this.mDeviceInformation.getVoiceApp().getPackageName() : "null";
    }

    @Nullable
    public Integer getVoiceAppVersion() {
        PackageInfo packageInfoIfEnabled;
        DeviceInformation deviceInformation = this.mDeviceInformation;
        if (deviceInformation == null || (packageInfoIfEnabled = getPackageInfoIfEnabled(deviceInformation.getVoiceApp().getPackageName())) == null) {
            return null;
        }
        return Integer.valueOf(packageInfoIfEnabled.versionCode);
    }

    @NonNull
    public String getVoiceAppVersionString() {
        Integer voiceAppVersion = getVoiceAppVersion();
        return voiceAppVersion == null ? "null" : Integer.toString(voiceAppVersion.intValue());
    }

    @VisibleForTesting
    boolean hasSupportedVoiceApp() {
        PackageInfo packageInfoIfEnabled = getPackageInfoIfEnabled(this.mDeviceInformation.getVoiceApp().getPackageName());
        if (packageInfoIfEnabled == null) {
            return false;
        }
        Integer supportedVoiceAppVersion = this.mDeviceInformation.getSupportedVoiceAppVersion();
        return supportedVoiceAppVersion == null || packageInfoIfEnabled.versionCode >= supportedVoiceAppVersion.intValue();
    }

    public boolean isAMPDDevice() {
        return isPTAOrWidgetPreload() || isPTTDevice() || isHandsFreeCapable();
    }

    public boolean isAmok() {
        PackageInfo packageInfoIfEnabled = getPackageInfoIfEnabled(VoiceApp.QUEBEC.getPackageName());
        return isHandsFreeCapable() && ((packageInfoIfEnabled != null && packageInfoIfEnabled.versionCode < QVA_TRUE_TURN_KEY_MIN_VERSION_CODE) || (getPackageInfoIfEnabled(VoiceApp.MIKE.getPackageName()) != null));
    }

    public boolean isBSR() {
        return this.mDeviceInformation != null && isHandsFreeCapable() && this.mDeviceInformation.isBSR();
    }

    public boolean isEdgeSV() {
        DeviceInformation deviceInformation = this.mDeviceInformation;
        if (deviceInformation == null) {
            return false;
        }
        VoiceApp voiceApp = deviceInformation.getVoiceApp();
        PackageInfo packageInfoIfEnabled = getPackageInfoIfEnabled(voiceApp.getPackageName());
        if (packageInfoIfEnabled == null) {
            return false;
        }
        return isHandsFreeCapable() && this.mDeviceInformation.isEdgeSV() && (Integer.valueOf(packageInfoIfEnabled.versionCode).intValue() >= voiceApp.getMinVersion1PSV());
    }

    public boolean isHandsFreeCapable() {
        if (this.mDeviceInformation == null) {
            return false;
        }
        if (!hasSupportedVoiceApp() && !this.mDeviceInformation.getVoiceApp().equals(VoiceApp.MIKE)) {
            return false;
        }
        int i = Build.VERSION.SDK_INT;
        if (this.mDeviceInformation.getVersionComparator() != null) {
            return this.mDeviceInformation.getVersionComparator().hasMinimumVersion();
        }
        return true;
    }

    public boolean isHandsFreeMike() {
        return isHandsFreeCapable() && Manufacturer.MIKE.getManufacturer().equals(this.mDeviceInformation.getManufacturer());
    }

    public boolean isHandsFreeXray() {
        return isHandsFreeCapable() && Manufacturer.XRAY.getManufacturer().equals(this.mDeviceInformation.getManufacturer());
    }

    public boolean isPTAOrWidgetPreload() {
        return false;
    }

    public boolean isPTTDevice() {
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0033, code lost:
        r0 = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean isTrueTurnKey() {
        /*
            r7 = this;
            com.amazon.alexa.handsfree.devices.constants.VoiceApp[] r0 = com.amazon.alexa.handsfree.devices.constants.VoiceApp.values()
            int r1 = r0.length
            r2 = 0
            r3 = r2
        L7:
            r4 = 1
            if (r3 >= r1) goto L33
            r5 = r0[r3]
            com.amazon.alexa.handsfree.devices.constants.VoiceApp r6 = com.amazon.alexa.handsfree.devices.constants.VoiceApp.MIKE
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L15
            goto L1f
        L15:
            java.lang.String r6 = r5.getPackageName()
            android.content.pm.PackageInfo r6 = r7.getPackageInfoIfEnabled(r6)
            if (r6 != 0) goto L22
        L1f:
            int r3 = r3 + 1
            goto L7
        L22:
            com.amazon.alexa.handsfree.devices.constants.VoiceApp r0 = com.amazon.alexa.handsfree.devices.constants.VoiceApp.QUEBEC
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L31
            int r0 = r6.versionCode
            r1 = 301001(0x497c9, float:4.21792E-40)
            if (r0 < r1) goto L33
        L31:
            r0 = r4
            goto L34
        L33:
            r0 = r2
        L34:
            boolean r1 = r7.isHandsFreeCapable()
            if (r1 == 0) goto L3d
            if (r0 == 0) goto L3d
            r2 = r4
        L3d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.handsfree.devices.AMPDInformationProvider.isTrueTurnKey():boolean");
    }

    public boolean isTrueTurnkeyQVAStub() {
        PackageInfo packageInfoIfEnabled = getPackageInfoIfEnabled(VoiceApp.QUEBEC.getPackageName());
        return packageInfoIfEnabled != null && packageInfoIfEnabled.versionCode == QVA_TRUE_TURN_KEY_STUB_VERSION_CODE;
    }

    public boolean isVesper() {
        return isHandsFreeCapable() && Carrier.VESPER.getCarrier().equals(this.mDeviceInformation.getCarrier()) && isVesperAppPresent();
    }

    public boolean isVesperAppPresent() {
        return getPackageInfoIfEnabled(VESPER_PACKAGE) != null;
    }
}
