package com.amazon.dee.app.elements.bridges;

import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.view.Display;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.device.api.DeviceInformationException;
import com.amazon.alexa.voice.handsfree.reactnative.ReactMethodsAMPDInfoAdapter;
import com.amazon.dee.app.services.bluetooth.BluetoothService;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.identity.auth.device.api.DeviceDataStore;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
@ReactModule(name = "DeviceInformation")
/* loaded from: classes12.dex */
public class DeviceInformationModule extends ReactContextBaseJavaModule implements LifecycleEventListener {
    private static final String AMAZON_DEVICE_TYPE = "deviceType";
    @VisibleForTesting
    static final String AMAZON_INFO = "amazonInfo";
    private static final String AMAZON_SERIAL_NUMBER = "serialNumber";
    @VisibleForTesting
    static final String AUDIO_DEVICES = "audioDevices";
    private static final String BUILD = "build";
    @VisibleForTesting
    static final String BUNDLE = "bundle";
    static final String CHANGED = "changed";
    private static final String DATETIME_SETTINGS = "dateTimeSettings";
    @VisibleForTesting
    static final String DEVICE = "device";
    private static final String DEVICE_TYPE = "deviceType";
    private static final String FIRE_OS_VERSION = "fireOSVersion";
    private static final String HEIGHT = "height";
    private static final String HOUR_FORMAT = "hourFormat";
    private static final String ID = "id";
    private static final String IS_ALEXA_ENABLED_ON_FIRE_OS = "isAlexaEnabledOnFireOS";
    private static final String IS_FIRE_OS = "isFireOS";
    private static final String LANDSCAPE = "landscape";
    private static final String LOCALE = "locale";
    private static final String MEMORY_CLASS = "memoryClass";
    private static final String MODEL = "model";
    private static final String MODULE = "DeviceInformation";
    private static final String NAME = "name";
    public static final String ON_CONFIG_CHANGE_EVENT = "CONFIG_CHANGED";
    private static final String ORIENTATION = "orientation";
    private static final String PHONE = "phone";
    private static final String PORTRAIT = "portrait";
    @VisibleForTesting
    static final String SCREEN = "screen";
    @VisibleForTesting
    static final String SYSTEM = "system";
    private static final String TABLET = "tablet";
    private static final String TAG = Log.tag(DeviceInformationModule.class);
    private static final String TIME_ZONE_ID = "timeZoneId";
    private static final String TV = "tv";
    private static final String UNKNOWN = "unknown";
    private static final String VERSION = "version";
    private static final String WIDTH = "width";
    private final BluetoothService bluetoothService;
    private final ConfigurationReceiver configurationReceiver;
    private final ReactApplicationContext context;
    private final DeviceDataStore deviceDataStore;
    private final DeviceInformation deviceInformation;
    private Disposable emitBluetoothDeviceChangeEventDisposable;
    private final TimeZoneChangeReceiver timeZoneChangeReceiver;

    /* loaded from: classes12.dex */
    public class ConfigurationReceiver extends BroadcastReceiver {
        public ConfigurationReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            DeviceInformationModule.this.deviceOrientationChanged();
        }
    }

    /* loaded from: classes12.dex */
    public class TimeZoneChangeReceiver extends BroadcastReceiver {
        public TimeZoneChangeReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            DeviceInformationModule.this.timeZoneChanged();
        }
    }

    public DeviceInformationModule(ReactApplicationContext reactApplicationContext, DeviceInformation deviceInformation, BluetoothService bluetoothService) {
        super(reactApplicationContext);
        this.context = reactApplicationContext;
        this.context.addLifecycleEventListener(this);
        this.deviceInformation = deviceInformation;
        this.deviceDataStore = DeviceDataStore.getInstance(this.context);
        this.configurationReceiver = new ConfigurationReceiver();
        LocalBroadcastManager.getInstance(this.context).registerReceiver(this.configurationReceiver, new IntentFilter(ON_CONFIG_CHANGE_EVENT));
        this.timeZoneChangeReceiver = new TimeZoneChangeReceiver();
        LocalBroadcastManager.getInstance(this.context).registerReceiver(this.timeZoneChangeReceiver, new IntentFilter("android.intent.action.TIMEZONE_CHANGED"));
        this.bluetoothService = bluetoothService;
    }

    private void emitChangeEventToBridge(WritableMap writableMap) {
        if (this.context.hasActiveCatalystInstance()) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(CHANGED, writableMap);
        }
    }

    private Map<String, String> getDeviceInfo() {
        HashMap hashMap = new HashMap();
        hashMap.put("model", Build.PRODUCT);
        hashMap.put("name", Build.BRAND + " " + Build.DEVICE);
        return hashMap;
    }

    @Nullable
    @SuppressFBWarnings({"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"})
    private DisplayMetrics getDisplayMetrics() {
        if (getCurrentActivity() != null) {
            Display defaultDisplay = getCurrentActivity().getWindowManager().getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics);
            return displayMetrics;
        }
        return null;
    }

    private String getInterface() {
        return ((UiModeManager) this.context.getSystemService("uimode")).getCurrentModeType() == 4 ? TV : this.deviceInformation.isPhoneFormFactor() ? PHONE : TABLET;
    }

    private WritableMap getScreenInfoAsWritableMap() {
        return getWritableMapFromObjectMap(getScreenInfo());
    }

    private WritableArray getWritableArrayFromListOfStringMap(List<? extends Map<String, String>> list) {
        WritableArray createArray = Arguments.createArray();
        for (Map<String, String> map : list) {
            createArray.pushMap(getWritableMapFromStringMap(map));
        }
        return createArray;
    }

    private WritableMap getWritableMapFromObjectMap(Map<String, Object> map) {
        WritableMap createMap = Arguments.createMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() != null) {
                if (entry.getValue() instanceof Integer) {
                    createMap.putInt(entry.getKey(), ((Integer) entry.getValue()).intValue());
                } else if (entry.getValue() instanceof String) {
                    createMap.putString(entry.getKey(), entry.getValue().toString());
                } else if (entry.getValue() instanceof Map) {
                    createMap.putMap(entry.getKey(), getWritableMapFromObjectMap((Map) entry.getValue()));
                } else {
                    String str = TAG;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error: Cannot parse map key ");
                    outline107.append(entry.getKey());
                    outline107.append(" as it's not a valid type.");
                    Log.i(str, outline107.toString());
                }
            }
        }
        return createMap;
    }

    private WritableMap getWritableMapFromStringMap(Map<String, String> map) {
        WritableMap createMap = Arguments.createMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue() != null) {
                createMap.putString(entry.getKey(), entry.getValue().toString());
            }
        }
        return createMap;
    }

    private WritableMap setEmitDataWithBluetoothDevices(List<? extends Map<String, String>> list) {
        WritableMap createMap = Arguments.createMap();
        createMap.putArray(AUDIO_DEVICES, getWritableArrayFromListOfStringMap(list));
        return createMap;
    }

    public void deviceOrientationChanged() {
        if (!this.context.hasActiveCatalystInstance()) {
            return;
        }
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putMap(SCREEN, getScreenInfoAsWritableMap());
        emitChangeEventToBridge(writableNativeMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public void emitBluetoothDeviceChangeEvent(List<? extends Map<String, String>> list) {
        emitChangeEventToBridge(setEmitDataWithBluetoothDevices(list));
    }

    @ReactMethod
    @SuppressFBWarnings({"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"})
    public void get(Promise promise) {
        Map<String, Object> constants = getConstants();
        constants.put(SCREEN, getScreenInfo());
        constants.put(AUDIO_DEVICES, this.bluetoothService.getBluetoothDevices());
        promise.resolve(constants);
    }

    @VisibleForTesting
    Map<String, String> getAmazonInfo() {
        HashMap hashMap = new HashMap();
        try {
            hashMap.put("deviceType", this.deviceInformation.getDeviceType());
            hashMap.put("serialNumber", this.deviceInformation.getDeviceSerialNumber());
        } catch (DeviceInformationException e) {
            Log.e(TAG, e.getMessage());
        }
        return hashMap;
    }

    @VisibleForTesting
    Map<String, String> getBundleInfo() {
        PackageManager packageManager = this.context.getPackageManager();
        String packageName = this.context.getPackageName();
        HashMap outline133 = GeneratedOutlineSupport1.outline133("id", packageName);
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            outline133.put("version", packageInfo.versionName);
            outline133.put("build", Integer.toString(packageInfo.versionCode));
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return outline133;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put("device", getDeviceInfo());
        hashMap.put(SYSTEM, getSystemInfo());
        hashMap.put(BUNDLE, getBundleInfo());
        hashMap.put(AMAZON_INFO, getAmazonInfo());
        return hashMap;
    }

    @ReactMethod
    public void getDevicePartnerInfo(Promise promise) {
        promise.resolve(ReactMethodsAMPDInfoAdapter.getInstance(this.context).getDevicePartnerInfo());
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "DeviceInformation";
    }

    @SuppressFBWarnings({"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"})
    @VisibleForTesting
    Map<String, Object> getScreenInfo() {
        HashMap hashMap = new HashMap();
        DisplayMetrics displayMetrics = getDisplayMetrics();
        if (displayMetrics != null) {
            hashMap.put("width", Integer.valueOf((int) (displayMetrics.widthPixels / displayMetrics.scaledDensity)));
            hashMap.put("height", Integer.valueOf((int) (displayMetrics.heightPixels / displayMetrics.scaledDensity)));
        }
        if (getCurrentActivity() != null) {
            int i = getCurrentActivity().getResources().getConfiguration().orientation;
            if (i == 1) {
                hashMap.put(ORIENTATION, PORTRAIT);
            } else if (i == 2) {
                hashMap.put(ORIENTATION, LANDSCAPE);
            } else {
                hashMap.put(ORIENTATION, "unknown");
            }
        }
        return hashMap;
    }

    @VisibleForTesting
    Map<String, Object> getSystemInfo() {
        HashMap outline133 = GeneratedOutlineSupport1.outline133("name", Build.VERSION_CODES.class.getFields()[Build.VERSION.SDK_INT].getName());
        outline133.put("version", Build.VERSION.RELEASE);
        outline133.put("deviceType", getInterface());
        outline133.put("locale", Locale.getDefault().toString());
        outline133.put(IS_FIRE_OS, String.valueOf(this.deviceInformation.isFireOS()));
        outline133.put(FIRE_OS_VERSION, String.valueOf(this.deviceInformation.getFireOSVersion()));
        outline133.put(MEMORY_CLASS, Integer.valueOf(this.deviceInformation.getMemoryClass()));
        if (this.deviceInformation.isFireOS()) {
            outline133.put(IS_ALEXA_ENABLED_ON_FIRE_OS, String.valueOf(isAlexaVoiceAssistantEnabledOnFireOS()));
        }
        HashMap hashMap = new HashMap();
        hashMap.put(HOUR_FORMAT, DateFormat.is24HourFormat(this.context) ? "hour24" : "hour12");
        hashMap.put(TIME_ZONE_ID, TimeZone.getDefault().getID());
        outline133.put(DATETIME_SETTINGS, hashMap);
        return outline133;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        this.emitBluetoothDeviceChangeEventDisposable = this.bluetoothService.onBluetoothDevicesChanged().subscribe(new Consumer() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$wZ0bOFaEWfYIdLHKHeH3N6Xifr4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DeviceInformationModule.this.emitBluetoothDeviceChangeEvent((List) obj);
            }
        });
    }

    @VisibleForTesting
    boolean isAlexaVoiceAssistantEnabledOnFireOS() {
        try {
            return Settings.Secure.getInt(this.context.getContentResolver(), "alexa_enabled") == 1;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    @ReactMethod
    @SuppressFBWarnings({"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"})
    public void navigateToAlexaSettingOnFireOS(Promise promise) {
        if (!this.deviceInformation.isFireOS()) {
            Log.e(TAG, "navigateToAlexaSettingOnFireOS method unable to complete on non-fireOS platform.");
            promise.reject("Cannot execute on non-fireOS platform", "Invalid platform");
            return;
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.amazon.frameworksettings", "com.amazon.frameworksettings.alexa.AlexaSettingsActivity"));
        if (getCurrentActivity() != null) {
            try {
                getCurrentActivity().startActivity(intent);
                promise.resolve(null);
                return;
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
                promise.reject(e);
                return;
            }
        }
        promise.reject("Error - Cannot find current activity", "Unknown");
    }

    public void onForeground() {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putMap(SCREEN, getScreenInfoAsWritableMap());
        writableNativeMap.putMap(SYSTEM, getWritableMapFromObjectMap(getSystemInfo()));
        writableNativeMap.putMap(BUNDLE, getWritableMapFromStringMap(getBundleInfo()));
        writableNativeMap.putMap("device", getWritableMapFromStringMap(getDeviceInfo()));
        writableNativeMap.putMap(AMAZON_INFO, getWritableMapFromStringMap(getAmazonInfo()));
        writableNativeMap.putArray(AUDIO_DEVICES, getWritableArrayFromListOfStringMap(this.bluetoothService.getBluetoothDevices()));
        emitChangeEventToBridge(writableNativeMap);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        if (this.configurationReceiver != null) {
            LocalBroadcastManager.getInstance(this.context).unregisterReceiver(this.configurationReceiver);
        }
        if (this.timeZoneChangeReceiver != null) {
            LocalBroadcastManager.getInstance(this.context).unregisterReceiver(this.timeZoneChangeReceiver);
        }
        Disposable disposable = this.emitBluetoothDeviceChangeEventDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        LocalBroadcastManager.getInstance(this.context).unregisterReceiver(this.configurationReceiver);
        LocalBroadcastManager.getInstance(this.context).unregisterReceiver(this.timeZoneChangeReceiver);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        LocalBroadcastManager.getInstance(this.context).registerReceiver(this.configurationReceiver, new IntentFilter(ON_CONFIG_CHANGE_EVENT));
        LocalBroadcastManager.getInstance(this.context).registerReceiver(this.timeZoneChangeReceiver, new IntentFilter("android.intent.action.TIMEZONE_CHANGED"));
        onForeground();
    }

    public void timeZoneChanged() {
        if (!this.context.hasActiveCatalystInstance()) {
            return;
        }
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putMap(SYSTEM, getWritableMapFromObjectMap(getSystemInfo()));
        emitChangeEventToBridge(writableNativeMap);
    }
}
