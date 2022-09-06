package com.amazon.dee.app.elements.bridges;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationManagerCompat;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.dee.app.R;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
@ReactModule(name = "DeviceState")
/* loaded from: classes12.dex */
public class DeviceStateModule extends ReactContextBaseJavaModule {
    public static final String BLUETOOTH_NOT_SUPPORTED = "Device does not support Bluetooth";
    public static final String CANCEL = "cancel";
    public static final String GPS_LOCATION_OFF = "GPS/Location services are off";
    public static final String INTERNET_NOT_CONNECTED = "Internet not connected";
    @VisibleForTesting
    static final String LAST_PROMPT_TIME_KEY = "LAST_NOTIFICATION_PROMPT_TIME";
    private static final String MODULE_NAME = "DeviceStateService";
    public static final String NOTIFICATIONS_OFF = "Notification is off";
    public static final String OFF = "off";
    public static final String ON = "on";
    @VisibleForTesting
    static final String STORAGE_NAME = "DEVICE_STATE_PREFS";
    public static final String USER_CANCELLED_REQUEST = "User cancelled request";
    private final ReactApplicationContext reactContext;
    private final PersistentStorage storage;

    public DeviceStateModule(ReactApplicationContext reactApplicationContext, PersistentStorage.Factory factory) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
        this.storage = factory.create(STORAGE_NAME);
    }

    static /* synthetic */ void lambda$requestEnable$1(Promise promise, DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        promise.reject(CANCEL, USER_CANCELLED_REQUEST);
    }

    static /* synthetic */ void lambda$requestEnable$3(Promise promise, DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        promise.reject(CANCEL, USER_CANCELLED_REQUEST);
    }

    private void updatePromptTimestamp() {
        this.storage.edit().set(LAST_PROMPT_TIME_KEY, DateTime.now(DateTimeZone.UTC).getMillis()).commit();
    }

    boolean areNotificationsEnabled() {
        return NotificationManagerCompat.from(this.reactContext).areNotificationsEnabled();
    }

    @ReactMethod
    public void getBluetoothState(Promise promise) {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null) {
            promise.reject("off", BLUETOOTH_NOT_SUPPORTED);
        } else if (defaultAdapter.isEnabled()) {
            promise.resolve("on");
        } else {
            promise.resolve("off");
        }
    }

    @ReactMethod
    public void getGPSLocationState(Promise promise) {
        LocationManager locationManager = (LocationManager) this.reactContext.getSystemService("location");
        boolean z = false;
        if (locationManager != null && (locationManager.isProviderEnabled("gps") || locationManager.isProviderEnabled("network"))) {
            z = true;
        }
        if (z) {
            promise.resolve(null);
        } else {
            promise.reject("off", GPS_LOCATION_OFF);
        }
    }

    @ReactMethod
    public void getInternetState(Promise promise) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) this.reactContext.getSystemService("connectivity");
        boolean z = false;
        if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected()) {
            z = true;
        }
        if (z) {
            promise.resolve(null);
        } else {
            promise.reject("off", INTERNET_NOT_CONNECTED);
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void getNotificationState(Promise promise) {
        if (NotificationManagerCompat.from(this.reactContext).areNotificationsEnabled()) {
            promise.resolve(null);
        } else {
            promise.reject("off", NOTIFICATIONS_OFF);
        }
    }

    boolean isNotificationsPromptAllowed() {
        return !(this.storage.getLong(LAST_PROMPT_TIME_KEY, 0L) > 0);
    }

    public /* synthetic */ void lambda$requestEnable$0$DeviceStateModule(Intent intent, Promise promise, DialogInterface dialogInterface, int i) {
        this.reactContext.startActivity(intent.setFlags(268435456));
        dialogInterface.dismiss();
        promise.resolve(null);
    }

    public /* synthetic */ void lambda$requestEnable$2$DeviceStateModule(Promise promise, DialogInterface dialogInterface, int i) {
        BluetoothAdapter.getDefaultAdapter().enable();
        this.reactContext.getCurrentActivity().onBackPressed();
        dialogInterface.dismiss();
        promise.resolve(null);
    }

    @ReactMethod
    public void requestBluetoothEnable(Promise promise) {
        requestEnable(promise, this.reactContext.getString(R.string.device_status_title_bluetooth), this.reactContext.getString(R.string.device_status_enable_bluetooth), this.reactContext.getString(R.string.main_menu_settings), "android.settings.SETTINGS");
    }

    @ReactMethod
    public void requestBluetoothEnablePhoneId(Promise promise) {
        requestEnable(promise, this.reactContext.getString(R.string.device_status_title_bluetooth_phone_id), this.reactContext.getString(R.string.device_status_enable_bluetooth_phone_id), this.reactContext.getString(R.string.device_status_enable_bluetooth_phone_id_ok), this.reactContext.getString(R.string.device_status_enable_bluetooth_phone_id_cancel), "android.settings.BLUETOOTH_SETTINGS");
    }

    @VisibleForTesting
    void requestEnable(final Promise promise, String str, String str2, String str3, final Intent intent) {
        new AlertDialog.Builder(this.reactContext.getCurrentActivity()).setTitle(str).setMessage(str2).setPositiveButton(str3, new DialogInterface.OnClickListener() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$DeviceStateModule$R3-jpcLC3HxmhwLzV6nH9iEtdlo
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DeviceStateModule.this.lambda$requestEnable$0$DeviceStateModule(intent, promise, dialogInterface, i);
            }
        }).setNegativeButton(this.reactContext.getString(R.string.device_status_cancel), new DialogInterface.OnClickListener() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$DeviceStateModule$u84UxzLN9aoWPHIJVfIZKQzfLDw
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                Promise promise2 = Promise.this;
                dialogInterface.dismiss();
                promise2.reject(DeviceStateModule.CANCEL, DeviceStateModule.USER_CANCELLED_REQUEST);
            }
        }).setCancelable(false).show();
    }

    @ReactMethod
    public void requestGPSLocationEnable(Promise promise) {
        requestEnable(promise, this.reactContext.getString(R.string.device_status_title_gpslocation), this.reactContext.getString(R.string.device_status_enable_gpslocation), this.reactContext.getString(R.string.main_menu_settings), "android.settings.SETTINGS");
    }

    @ReactMethod
    public void requestInternetEnable(Promise promise) {
        promise.resolve(null);
    }

    @ReactMethod
    public void requestNotificationEnable(Promise promise) {
        if (areNotificationsEnabled()) {
            promise.resolve("on");
        } else if (!isNotificationsPromptAllowed()) {
            promise.reject("off", NOTIFICATIONS_OFF);
        } else {
            requestEnable(promise, this.reactContext.getString(R.string.device_status_enable_notifications_title), this.reactContext.getString(R.string.device_status_enable_notifications_message), this.reactContext.getString(R.string.device_status_enable_notifications_link_label), new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", this.reactContext.getPackageName(), null)));
            updatePromptTimestamp();
        }
    }

    @VisibleForTesting
    void requestEnable(final Promise promise, String str, String str2, String str3, String str4, Intent intent) {
        new AlertDialog.Builder(this.reactContext.getCurrentActivity()).setTitle(str).setMessage(str2).setPositiveButton(str3, new DialogInterface.OnClickListener() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$DeviceStateModule$eDnraYMm-siJQfNg39OMMKBignI
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DeviceStateModule.this.lambda$requestEnable$2$DeviceStateModule(promise, dialogInterface, i);
            }
        }).setNegativeButton(str4, new DialogInterface.OnClickListener() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$DeviceStateModule$FU5CcDbJxYNEiA9_VhFK3iKfdzg
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                Promise promise2 = Promise.this;
                dialogInterface.dismiss();
                promise2.reject(DeviceStateModule.CANCEL, DeviceStateModule.USER_CANCELLED_REQUEST);
            }
        }).setCancelable(false).show();
    }

    private void requestEnable(Promise promise, String str, String str2, String str3, String str4) {
        requestEnable(promise, str, str2, str3, new Intent(str4));
    }

    private void requestEnable(Promise promise, String str, String str2, String str3, String str4, String str5) {
        requestEnable(promise, str, str2, str3, str4, new Intent(str5));
    }
}
