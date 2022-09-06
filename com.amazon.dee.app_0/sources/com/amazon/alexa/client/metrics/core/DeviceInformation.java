package com.amazon.alexa.client.metrics.core;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.client.core.device.PersistentStorage;
import dagger.Lazy;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
/* loaded from: classes6.dex */
public class DeviceInformation {
    private static final String APP_VERSION = "AppVersion";
    @VisibleForTesting
    public static final String DEVICE_COUNTRY = "DEVICE_COUNTRY";
    @VisibleForTesting
    public static final String DEVICE_DENSITY = "DEVICE_DENSITY";
    @VisibleForTesting
    public static final String DEVICE_HEIGHT = "DEVICE_HEIGHT";
    @VisibleForTesting
    public static final String DEVICE_LANGUAGE = "DEVICE_LANGUAGE";
    @VisibleForTesting
    public static final String DEVICE_MANUFACTURER = "DEVICE_MANUFACTURER";
    @VisibleForTesting
    public static final String DEVICE_MODEL = "DEVICE_MODEL";
    @VisibleForTesting
    public static final String DEVICE_PRODUCT = "DEVICE_PRODUCT";
    @VisibleForTesting
    public static final String DEVICE_WIDTH = "DEVICE_WIDTH";
    @VisibleForTesting
    public static final String FREE_RAM = "FREE_RAM";
    @VisibleForTesting
    public static final String NETWORK_TYPE = "NETWORK_TYPE";
    private static final String OS_ANDROID = "ANDROID";
    private static final String OS_FIREOS = "FIRE_OS";
    @VisibleForTesting
    public static final String OS_TYPE = "OS_TYPE";
    @VisibleForTesting
    public static final String OS_VERSION = "OS_VERSION";
    @VisibleForTesting
    public static final String SDK_INT = "SDK_INT";
    @VisibleForTesting
    public static final String SERVICE_VERSION = "SERVICE_VERSION";
    @VisibleForTesting
    public static final String SIGNAL_STRENGTH = "SIGNAL_STRENGTH";
    private static final String TAG = "DeviceInformation";
    private final ActivityManager activityManager;
    private final AppInformation appInformation;
    private String countryCode;
    private volatile Map<String, Object> dynamicDeviceData;
    private final NetworkStateProvider networkStateProvider;
    private final Lazy<PersistentStorage> persistentStorage;
    private final ServiceVersionProvider serviceVersionProvider;
    private volatile Map<String, Object> staticDeviceData;
    private final TelephonyManager telephonyManager;
    private final WindowManager windowManager;
    private final Object staticDeviceDataUpdateLock = new Object();
    private final Object dynamicDeviceDataUpdateLock = new Object();

    /* loaded from: classes6.dex */
    public interface ServiceVersionProvider {
        String getServiceVersion();
    }

    public DeviceInformation(Context context, ActivityManager activityManager, TelephonyManager telephonyManager, WindowManager windowManager, NetworkStateProvider networkStateProvider, AppInformation appInformation, Lazy<PersistentStorage> lazy, ServiceVersionProvider serviceVersionProvider) {
        this.activityManager = activityManager;
        this.telephonyManager = telephonyManager;
        this.windowManager = windowManager;
        this.networkStateProvider = networkStateProvider;
        this.appInformation = appInformation;
        this.persistentStorage = lazy;
        this.serviceVersionProvider = serviceVersionProvider;
        setDefaultValues(context);
    }

    private Map<String, Object> computeCurrentDynamicDeviceData() {
        HashMap hashMap = new HashMap();
        long freeRam = getFreeRam();
        if (freeRam > 0) {
            freeRam /= 1048576;
        }
        hashMap.put("FREE_RAM", Long.valueOf(freeRam));
        hashMap.put("NETWORK_TYPE", this.networkStateProvider.getNetworkType());
        hashMap.put(SIGNAL_STRENGTH, this.networkStateProvider.getSignalStrength());
        return hashMap;
    }

    private Map<String, Object> createStaticDeviceProfileData() {
        HashMap hashMap = new HashMap();
        hashMap.put("DEVICE_MODEL", getModel());
        hashMap.put("DEVICE_LANGUAGE", getLanguage());
        hashMap.put("DEVICE_COUNTRY", getCountry());
        hashMap.put("DEVICE_MANUFACTURER", getManufacturer());
        hashMap.put("OS_VERSION", getOSVersion());
        hashMap.put("DEVICE_PRODUCT", getProduct());
        hashMap.put("SDK_INT", Integer.valueOf(getVersionSdkInt()));
        hashMap.put("OS_TYPE", getOsType());
        hashMap.put(SERVICE_VERSION, getVersionName());
        hashMap.put("AppVersion", this.appInformation.getHostAppVersion());
        Point point = new Point();
        this.windowManager.getDefaultDisplay().getSize(point);
        hashMap.put("DEVICE_WIDTH", Integer.valueOf(point.x));
        hashMap.put("DEVICE_HEIGHT", Integer.valueOf(point.y));
        hashMap.put("DEVICE_DENSITY", Float.valueOf(Resources.getSystem().getDisplayMetrics().density));
        return hashMap;
    }

    @NonNull
    private String getCountry() {
        return Locale.getDefault().getCountry();
    }

    private long getFreeRam() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        this.activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    @NonNull
    private String getLanguage() {
        return Locale.getDefault().getDisplayLanguage();
    }

    @NonNull
    private String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    @NonNull
    private String getProduct() {
        return Build.PRODUCT;
    }

    private void setDefaultValues(Context context) {
        String simCountryIso = this.telephonyManager.getSimCountryIso();
        if (simCountryIso != null && !simCountryIso.isEmpty()) {
            this.countryCode = simCountryIso.toUpperCase();
        }
        String str = this.countryCode;
        if (str == null || str.isEmpty()) {
            Locale locale = context.getResources().getConfiguration().locale;
            String str2 = "Current locale: " + locale;
            this.countryCode = locale.getCountry();
        }
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    @NonNull
    public Point getDeviceSize() {
        Point point = new Point();
        this.windowManager.getDefaultDisplay().getSize(point);
        return point;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x000d, code lost:
        if (r1.dynamicDeviceData == null) goto L10;
     */
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
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.client.metrics.core.DeviceInformation.getDynamicDeviceProfileData(boolean):java.util.Map");
    }

    public String getId() {
        String string = this.persistentStorage.mo358get().getString("id");
        if (TextUtils.isEmpty(string)) {
            String uuid = UUID.randomUUID().toString();
            this.persistentStorage.mo358get().edit().set("id", uuid).commitAsynchronously();
            return uuid;
        }
        return string;
    }

    public String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public String getModel() {
        return Build.MODEL;
    }

    @NonNull
    public String getOsType() {
        return isFireOS() ? "FIRE_OS" : "ANDROID";
    }

    public String getSerial() {
        return Build.SERIAL;
    }

    @NonNull
    public Map<String, Object> getStaticDeviceProfileData() {
        if (this.staticDeviceData == null) {
            synchronized (this.staticDeviceDataUpdateLock) {
                if (this.staticDeviceData == null) {
                    this.staticDeviceData = Collections.unmodifiableMap(createStaticDeviceProfileData());
                }
            }
        }
        return this.staticDeviceData;
    }

    public String getVersionName() {
        return this.serviceVersionProvider.getServiceVersion();
    }

    public int getVersionSdkInt() {
        return Build.VERSION.SDK_INT;
    }

    public boolean isFireOS() {
        try {
            Class.forName("amazon.os.Build$VERSION");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}
