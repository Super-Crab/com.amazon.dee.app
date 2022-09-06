package com.amazon.alexa.device;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.device.api.DeviceInformationException;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.protocols.service.api.ComponentGetter;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.identity.auth.device.api.DeviceDataKeys;
import com.amazon.identity.auth.device.api.DeviceDataStore;
import com.amazon.identity.auth.device.api.DeviceDataStoreException;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
/* loaded from: classes.dex */
public final class DefaultDeviceInformation implements DeviceInformation {
    public static final String APP_VERSION = "APP_VERSION";
    private static final String DEFAULT_VERSION_NAME = "2.2.0.0";
    public static final String DEVICE_COUNTRY = "DEVICE_COUNTRY";
    public static final String DEVICE_DENSITY = "DEVICE_DENSITY";
    public static final String DEVICE_HEIGHT = "DEVICE_HEIGHT";
    public static final String DEVICE_LANGUAGE = "DEVICE_LANGUAGE";
    public static final String DEVICE_MANUFACTURER = "DEVICE_MANUFACTURER";
    public static final String DEVICE_MODEL = "DEVICE_MODEL";
    public static final String DEVICE_PRODUCT = "DEVICE_PRODUCT";
    public static final String DEVICE_WIDTH = "DEVICE_WIDTH";
    public static final String FREE_RAM = "FREE_RAM";
    public static final String MEMORY_CLASS = "MEMORY_CLASS";
    public static final String NETWORK_TYPE = "NETWORK_TYPE";
    public static final String OS_ANDROID = "ANDROID";
    public static final String OS_FIREOS = "FIRE_OS";
    public static final String OS_TYPE = "OS_TYPE";
    public static final String OS_VERSION = "OS_VERSION";
    public static final String PLATFORM_TYPE = "Android";
    public static final String SDK_INT = "SDK_INT";
    private static final String TAG = "DefaultDeviceInformation";
    private final Context context;
    private final DefaultScreenInformation defaultScreenInformation;
    private DeviceDataStore deviceDataStore;
    private volatile Map<String, Object> dynamicDeviceData;
    private final Object dynamicDeviceDataUpdateLock = new Object();
    private final LazyComponent<NetworkService> networkService;
    private final LazyComponent<PersistentStorage.Factory> persistentStorage;
    private volatile Map<String, Object> staticDeviceData;

    public DefaultDeviceInformation(ComponentGetter componentGetter, Context context) {
        this.context = context;
        this.defaultScreenInformation = new DefaultScreenInformation(context);
        this.networkService = componentGetter.get(NetworkService.class);
        this.persistentStorage = componentGetter.get(PersistentStorage.Factory.class);
    }

    private Map<String, Object> computeCurrentDynamicDeviceData() {
        HashMap hashMap = new HashMap();
        hashMap.put("FREE_RAM", Long.valueOf(getFreeRam() / 1048576));
        hashMap.put("NETWORK_TYPE", this.networkService.mo10268get().getNetworkType());
        return hashMap;
    }

    @Nullable
    private String getCarrier() {
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

    @NonNull
    private String getCountry() {
        return Locale.getDefault().getCountry();
    }

    private long getFreeRam() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) this.context.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME)).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    @NonNull
    private String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    @NonNull
    private String getOsType() {
        return isFireOS() ? "FIRE_OS" : "ANDROID";
    }

    @NonNull
    private String getProduct() {
        return Build.PRODUCT;
    }

    @Override // com.amazon.alexa.device.api.DeviceInformation
    @SuppressLint({"HardwareIds"})
    public String getAndroidId() {
        return Settings.Secure.getString(this.context.getContentResolver(), "android_id");
    }

    @Override // com.amazon.alexa.device.api.DeviceInformation
    public String getBrand() {
        return Build.BRAND;
    }

    @Override // com.amazon.alexa.device.api.DeviceInformation
    public String getDeviceSerialNumber() throws DeviceInformationException {
        if (this.deviceDataStore == null) {
            this.deviceDataStore = DeviceDataStore.getInstance(this.context);
        }
        try {
            String value = this.deviceDataStore.getValue(DeviceDataKeys.KEY_DEVICE_SERIAL_NUMBER);
            if (!TextUtils.isEmpty(value)) {
                return value;
            }
            throw new DeviceInformationException("No DSN in MAP data store.");
        } catch (DeviceDataStoreException e) {
            throw new DeviceInformationException(e);
        }
    }

    @Override // com.amazon.alexa.device.api.DeviceInformation
    @NonNull
    public String getDeviceType() throws DeviceInformationException {
        if (this.deviceDataStore == null) {
            this.deviceDataStore = DeviceDataStore.getInstance(this.context);
        }
        try {
            String value = this.deviceDataStore.getValue("DeviceType");
            if (!TextUtils.isEmpty(value)) {
                return value;
            }
            throw new DeviceInformationException("No device type in MAP data store.");
        } catch (DeviceDataStoreException e) {
            throw new DeviceInformationException(e);
        }
    }

    @Override // com.amazon.alexa.device.api.DeviceInformation
    public DisplayMetrics getDisplayMetrics() {
        return this.defaultScreenInformation.getDisplayMetrics();
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x000d, code lost:
        if (r1.dynamicDeviceData == null) goto L10;
     */
    @Override // com.amazon.alexa.device.api.DeviceInformation
    @androidx.annotation.NonNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.Map<java.lang.String, java.lang.Object> getDynamicDeviceProfileData(boolean r2) {
        /*
            r1 = this;
            if (r2 != 0) goto L6
            java.util.Map<java.lang.String, java.lang.Object> r0 = r1.dynamicDeviceData
            if (r0 != 0) goto L1a
        L6:
            java.lang.Object r0 = r1.dynamicDeviceDataUpdateLock
            monitor-enter(r0)
            if (r2 != 0) goto Lf
            java.util.Map<java.lang.String, java.lang.Object> r2 = r1.dynamicDeviceData     // Catch: java.lang.Throwable -> L1d
            if (r2 != 0) goto L19
        Lf:
            java.util.Map r2 = r1.computeCurrentDynamicDeviceData()     // Catch: java.lang.Throwable -> L1d
            java.util.Map r2 = java.util.Collections.unmodifiableMap(r2)     // Catch: java.lang.Throwable -> L1d
            r1.dynamicDeviceData = r2     // Catch: java.lang.Throwable -> L1d
        L19:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L1d
        L1a:
            java.util.Map<java.lang.String, java.lang.Object> r2 = r1.dynamicDeviceData
            return r2
        L1d:
            r2 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L1d
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.device.DefaultDeviceInformation.getDynamicDeviceProfileData(boolean):java.util.Map");
    }

    @Override // com.amazon.alexa.device.api.DeviceInformation
    @Nullable
    public String getFireOSVersion() {
        try {
            String str = (String) Class.forName("amazon.os.Build$VERSION").getField("FIREOS").get(null);
            String str2 = "FireOS version: " + str;
            return str;
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (Exception e) {
            Log.i(TAG, "Could not read FireOS version field - unable to determine FireOS version.", e);
            return null;
        }
    }

    @Override // com.amazon.alexa.device.api.DeviceInformation
    public String getId() {
        PersistentStorage create = this.persistentStorage.mo10268get().create("device.information");
        String string = create.getString("id");
        if (TextUtils.isEmpty(string)) {
            String uuid = UUID.randomUUID().toString();
            create.edit().set("id", uuid).commit();
            return uuid;
        }
        return string;
    }

    @Override // com.amazon.alexa.device.api.DeviceInformation
    @NonNull
    public String getLanguage() {
        return Locale.getDefault().getDisplayLanguage();
    }

    @Override // com.amazon.alexa.device.api.DeviceInformation
    public String getManufacturer() {
        return Build.MANUFACTURER;
    }

    @Override // com.amazon.alexa.device.api.DeviceInformation
    public int getMemoryClass() {
        return ((ActivityManager) this.context.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME)).getMemoryClass();
    }

    @Override // com.amazon.alexa.device.api.DeviceInformation
    public String getModel() {
        return Build.MODEL;
    }

    @Override // com.amazon.alexa.device.api.DeviceInformation
    public String getPlatformType() {
        return "Android";
    }

    @Override // com.amazon.alexa.device.api.DeviceInformation
    public double getScreenSize() {
        return this.defaultScreenInformation.getScreenSize();
    }

    @Override // com.amazon.alexa.device.api.DeviceInformation
    @SuppressLint({"HardwareIds"})
    public String getSerial() {
        return Build.SERIAL;
    }

    @Override // com.amazon.alexa.device.api.DeviceInformation
    @NonNull
    public Map<String, Object> getStaticDeviceProfileData() {
        if (this.staticDeviceData == null) {
            HashMap hashMap = new HashMap();
            hashMap.put("DEVICE_MODEL", getModel());
            hashMap.put("DEVICE_LANGUAGE", getLanguage());
            hashMap.put("DEVICE_COUNTRY", getCountry());
            hashMap.put("DEVICE_MANUFACTURER", getManufacturer());
            hashMap.put("OS_VERSION", getOSVersion());
            hashMap.put("DEVICE_PRODUCT", getProduct());
            hashMap.put("SDK_INT", Integer.valueOf(getVersionSdkInt()));
            hashMap.put("OS_TYPE", getOsType());
            hashMap.put(APP_VERSION, getVersionName());
            hashMap.put(MEMORY_CLASS, Integer.valueOf(getMemoryClass()));
            Point point = new Point();
            ((WindowManager) this.context.getSystemService("window")).getDefaultDisplay().getSize(point);
            hashMap.put("DEVICE_WIDTH", Integer.valueOf(point.x));
            hashMap.put("DEVICE_HEIGHT", Integer.valueOf(point.y));
            hashMap.put("DEVICE_DENSITY", Float.valueOf(Resources.getSystem().getDisplayMetrics().density));
            this.staticDeviceData = Collections.unmodifiableMap(hashMap);
        }
        return this.staticDeviceData;
    }

    @Override // com.amazon.alexa.device.api.DeviceInformation
    public String getVersionName() {
        try {
            return this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Could not find the package name. ", e);
            Log.w(TAG, "Could not get the version name. Returning default version name.");
            return DEFAULT_VERSION_NAME;
        }
    }

    @Override // com.amazon.alexa.device.api.DeviceInformation
    public String getVersionRelease() {
        return Build.VERSION.RELEASE;
    }

    @Override // com.amazon.alexa.device.api.DeviceInformation
    public int getVersionSdkInt() {
        return Build.VERSION.SDK_INT;
    }

    @Override // com.amazon.alexa.device.api.DeviceInformation
    public boolean isFireOS() {
        try {
            Class.forName("amazon.os.Build$VERSION");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    @Override // com.amazon.alexa.device.api.DeviceInformation
    public boolean isPhoneFormFactor() {
        return this.defaultScreenInformation.isPhoneFormFactor();
    }

    @Override // com.amazon.alexa.device.api.DeviceInformation
    public void refreshDeviceInfoData() {
        getDynamicDeviceProfileData(true);
    }
}
