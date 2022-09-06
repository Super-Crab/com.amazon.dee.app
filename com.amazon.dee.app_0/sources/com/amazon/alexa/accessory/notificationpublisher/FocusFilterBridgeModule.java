package com.amazon.alexa.accessory.notificationpublisher;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StatusEventManager;
import com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.notificationlistener.NotificationListenerProxy;
import com.amazon.alexa.accessory.notificationpublisher.providers.DistractionModeProvider;
import com.amazon.alexa.accessory.notificationpublisher.storage.ContactFilterSettingsManager;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.amazon.alexa.accessory.notificationpublisher.storage.StorageWrapper;
import com.amazon.alexa.accessory.notificationpublisher.storage.VipFilterSettingStorageHelper;
import com.amazon.alexa.accessory.notificationpublisher.utils.Constants;
import com.amazon.alexa.accessory.notificationpublisher.utils.FeatureAccessChecker;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsConstants;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.google.common.base.Strings;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class FocusFilterBridgeModule implements ActivityEventListener {
    @VisibleForTesting
    static final String GET_DEFAULT_MESSAGING_APP_INFO = "GetDefaultMessagingAppInfo";
    private static final String NOTIFICATION_ACCESS_SCREEN_FAILURE = "NOTIFICATION_ACCESS_SCREEN_FAILURE";
    private static final String NOTIFICATION_LISTENER_SETTINGS = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS";
    private static final int NOTIFICATION_REQUEST_CODE = 35790;
    private static final String STORAGE_FAILURE = "STORAGE_FAILURE";
    private static final String TAG = "com.amazon.alexa.accessory.notificationpublisher.FocusFilterBridgeModule";
    private Promise notificationAccessPromise;
    private final ReactApplicationContext reactContext;
    private StorageWrapper storageUpdater;

    public FocusFilterBridgeModule(ReactApplicationContext reactApplicationContext) {
        this.reactContext = reactApplicationContext;
    }

    private synchronized StorageWrapper getStorageWrapper() {
        if (this.storageUpdater != null) {
            return this.storageUpdater;
        }
        this.storageUpdater = new StorageWrapper();
        return this.storageUpdater;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ObservableSource lambda$getObservable$0(Boolean bool) throws Throwable {
        String str = TAG;
        Log.d(str, "phone notification toggle received as: " + bool);
        return Observable.just(String.valueOf(bool));
    }

    private void migrateBackwardForZion(String str, Object obj) {
        char c = 65535;
        try {
            switch (str.hashCode()) {
                case -1198200849:
                    if (str.equals(SettingsStorageModule.LOW_DISTRACTION_MODE_KEY)) {
                        c = 3;
                        break;
                    }
                    break;
                case -807487662:
                    if (str.equals(SettingsStorageModule.SILENT_DISTRACTION_MODE_KEY)) {
                        c = 4;
                        break;
                    }
                    break;
                case -502436310:
                    if (str.equals(SettingsStorageModule.VIP_FILTER_FORWARD_NOTIFICATION_TO_ACCESSORY_ENABLEMENT_KEY)) {
                        c = 0;
                        break;
                    }
                    break;
                case 1846186485:
                    if (str.equals(SettingsStorageModule.VIP_FILTER_STATUS_ENABLEMENT_KEY)) {
                        c = 2;
                        break;
                    }
                    break;
                case 1869150543:
                    if (str.equals(SettingsStorageModule.VIP_FILTER_APPROVE_INVITATION_ON_ACCESSORY)) {
                        c = 1;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                getStorageWrapper().put(SettingsStorageModule.FORWARD_NOTIFICATION_TO_ACCESSORY_KEY, obj, SettingsStorageModule.FORWARD_NOTIFICATION_TO_ACCESSORY_KEY);
            } else if (c == 1) {
                getStorageWrapper().put(SettingsStorageModule.APPROVE_INVITATION_ON_ACCESSORY, obj, SettingsStorageModule.APPROVE_INVITATION_ON_ACCESSORY);
            } else if (c == 2) {
                getStorageWrapper().put(SettingsStorageModule.FOCUS_FILTER_ENABLED_KEY, obj, SettingsStorageModule.FOCUS_FILTER_ENABLED_KEY);
            } else if (c != 3 && c != 4) {
                Log.d(TAG, "Key is not valid for migrate back: " + str);
            } else {
                getStorageWrapper().put(str, obj, str);
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to migrate to Zephyr from multi device settings for " + str, e);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void putForMigrate(String str, @NonNull String str2, String str3, Promise promise) {
        char c;
        switch (str.hashCode()) {
            case -1198200849:
                if (str.equals(SettingsStorageModule.LOW_DISTRACTION_MODE_KEY)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -807487662:
                if (str.equals(SettingsStorageModule.SILENT_DISTRACTION_MODE_KEY)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -632766421:
                if (str.equals(SettingsStorageModule.APPROVE_INVITATION_ON_ACCESSORY)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -632704118:
                if (str.equals(SettingsStorageModule.GROUP_MESSAGES_MASTER_TOGGLE_KEY)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1044733893:
                if (str.equals(SettingsStorageModule.FORWARD_NOTIFICATION_TO_ACCESSORY_KEY)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1807911248:
                if (str.equals(SettingsStorageModule.FOCUS_FILTER_ENABLED_KEY)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            put("A3IYPH06PH1HRA", SettingsStorageModule.VIP_FILTER_FORWARD_NOTIFICATION_TO_ACCESSORY_ENABLEMENT_KEY, str2, SettingsStorageModule.VIP_FILTER_FORWARD_NOTIFICATION_TO_ACCESSORY_ENABLEMENT_KEY, promise);
        } else if (c == 1) {
            put("A3IYPH06PH1HRA", SettingsStorageModule.VIP_FILTER_APPROVE_INVITATION_ON_ACCESSORY, str2, SettingsStorageModule.VIP_FILTER_APPROVE_INVITATION_ON_ACCESSORY, promise);
        } else if (c == 2) {
            put("A3IYPH06PH1HRA", SettingsStorageModule.VIP_FILTER_STATUS_ENABLEMENT_KEY, str2, SettingsStorageModule.VIP_FILTER_STATUS_ENABLEMENT_KEY, promise);
        } else if (c != 3 && c != 4) {
            if (c == 5) {
                try {
                    FeatureToggleModule.getInstance().onGroupMessagesMasterToggleChanged(((Boolean) Boolean.class.cast(new JSONObject(str2).get("value"))).booleanValue());
                } catch (JSONException e) {
                    Log.e(TAG, "Error during migrating from the existing method for group messages master toggle");
                    promise.reject(STORAGE_FAILURE, "Exception when save value to storage. Info: " + e);
                    return;
                }
            }
            try {
                Log.d(TAG, "Migration not needed for key: " + str);
                Object obj = new JSONObject(str2).get("value");
                this.storageUpdater.put(str, obj, str3);
                SettingsStorageModule.recordMetricsBasedOnKey(str, obj);
                promise.resolve(true);
            } catch (Exception e2) {
                if (e2 instanceof RxBlockingCallException) {
                    MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_FocusFilterBridgeModule_put", MetricsRecorder.customAttributesForException(e2));
                }
                Log.e(TAG, "Put for migrate - storing to the old failed", e2);
                promise.reject(STORAGE_FAILURE, "Exception when save value to storage. Info: " + e2);
            }
        } else {
            put("A3IYPH06PH1HRA", str, str2, str3, promise);
        }
    }

    public void clear(Promise promise) {
        Log.d(TAG, MetricsConstants.Method.CACHE_CLEAR);
        try {
            getStorageWrapper().clear();
            StatusEventManager.getInstance().onClearFilterSettings();
            if (FeatureAccessChecker.hasOtgVipFilterAccess() && FeatureAccessChecker.hasOtgVipFilterUssSettingsAccess()) {
                clear("A3IYPH06PH1HRA", promise);
            } else {
                promise.resolve(true);
            }
        } catch (Exception e) {
            if (e instanceof RxBlockingCallException) {
                MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_FocusFilterBridgeModule_clear", MetricsRecorder.customAttributesForException(e));
            }
            promise.reject(STORAGE_FAILURE, "Exception when clear scope from storage. Info: " + e);
        }
    }

    public void get(@NonNull String str, Promise promise) {
        GeneratedOutlineSupport1.outline165("get - key: ", str, TAG);
        try {
            boolean z = getStorageWrapper().get(str);
            String str2 = TAG;
            Log.d(str2, "get -- value: " + z);
            if (str.equals(SettingsStorageModule.REPLY_READ_BACK_KEY) && z == null) {
                getStorageWrapper().put(str, true, null);
                Log.d(TAG, "get -- value is null, put default value to storage: true");
                z = true;
            } else if (str.equals(SettingsStorageModule.REPLY_KEY) && z == null) {
                getStorageWrapper().put(str, true, null);
                Log.d(TAG, "get -- value is null, put default value to storage: true");
                z = true;
            } else if (str.equals(SettingsStorageModule.LOW_DISTRACTION_MODE_KEY) && z == null) {
                getStorageWrapper().put(str, false, null);
                Log.d(TAG, "get -- value is null, put default value to storage: false");
                z = false;
            } else if (str.equals(SettingsStorageModule.GROUP_MESSAGES_MASTER_TOGGLE_KEY) && z == null) {
                getStorageWrapper().put(str, false, null);
                Log.d(TAG, "get -- value is null, put default value to storage: false");
                z = false;
            }
            String jSONObject = new JSONObject().put("value", z).toString();
            String str3 = TAG;
            Log.d(str3, "get -- value json: " + jSONObject);
            promise.resolve(jSONObject);
        } catch (Exception e) {
            if (e instanceof RxBlockingCallException) {
                MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_FocusFilterBridgeModule_get", MetricsRecorder.customAttributesForException(e));
            }
            promise.reject(STORAGE_FAILURE, "Exception when fetch value from storage. Info: " + e);
        }
    }

    public Observable<String> getObservable(@NonNull String str) {
        if (Constants.FOCUS_FILTER_DISTRACTION_MODE_OBSERVABLE.equals(str)) {
            return DistractionModeProvider.getDistractionModeObservable();
        }
        if (Constants.PHONE_NOTIFICATION_TOGGLE_OBSERVABLE.equals(str)) {
            return FeatureToggleModule.getInstance().queryNotificationForwardingStatus().flatMap($$Lambda$FocusFilterBridgeModule$LeEFX7CEMfG0FAXCo0lO2Fnp9Yc.INSTANCE);
        }
        return Observable.error(new Throwable(GeneratedOutlineSupport1.outline72("No observable linked with key: ", str)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Boolean, java.lang.Object] */
    public void hasNotificationPermission(Promise promise) {
        String str;
        StringBuilder sb;
        String str2 = "hasNotificationPermission - ";
        boolean z = false;
        try {
            try {
                z = NotificationListenerProxy.hasNotificationListenerPermissions(this.reactContext);
                str = TAG;
                sb = new StringBuilder();
            } catch (Exception e) {
                Log.w(TAG, "hasNotificationPermission caught an exception.", e);
                str = TAG;
                sb = new StringBuilder();
            }
            sb.append((String) str2);
            sb.append(z);
            Log.i(str, sb.toString());
            str2 = Boolean.valueOf(z);
            promise.resolve(str2);
        } catch (Throwable th) {
            String str3 = TAG;
            Log.i(str3, str2 + z);
            promise.resolve(Boolean.valueOf(z));
            throw th;
        }
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        Promise promise;
        String str = TAG;
        Log.d(str, "onActivityResult- requestCode = " + i);
        if (i == NOTIFICATION_REQUEST_CODE) {
            boolean z = false;
            try {
                try {
                    z = NotificationListenerProxy.hasNotificationListenerPermissions(this.reactContext);
                    String str2 = TAG;
                    Log.i(str2, "onActivityResult - Notification access enabled state = " + z);
                    promise = this.notificationAccessPromise;
                    if (promise == null) {
                        return;
                    }
                } catch (Exception e) {
                    Log.w(TAG, "onActivityResult - Exception.", e);
                    e.printStackTrace();
                    promise = this.notificationAccessPromise;
                    if (promise == null) {
                        return;
                    }
                }
                promise.resolve(Boolean.valueOf(z));
            } catch (Throwable th) {
                Promise promise2 = this.notificationAccessPromise;
                if (promise2 != null) {
                    promise2.resolve(Boolean.valueOf(z));
                }
                throw th;
            }
        }
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
        Log.d(TAG, "onNewIntent");
    }

    public void put(@NonNull String str, @NonNull String str2, @Nullable String str3, Promise promise) {
        Log.d(TAG, String.format(Locale.US, "put - key: %s, cloudKey: %s", str, str3));
        GeneratedOutlineSupport1.outline165("put - valueString: ", str2, TAG);
        try {
            if (FeatureAccessChecker.hasOtgVipFilterAccess() && FeatureAccessChecker.hasOtgVipFilterUssSettingsAccess()) {
                putForMigrate(str, str2, str3, promise);
                return;
            }
            Object obj = new JSONObject(str2).get("value");
            if (Strings.isNullOrEmpty(str3)) {
                str3 = str;
            }
            getStorageWrapper().put(str, obj, str3);
            SettingsStorageModule.recordMetricsBasedOnKey(str, obj);
            promise.resolve(true);
            if (str.equals(SettingsStorageModule.FORWARD_NOTIFICATION_TO_ACCESSORY_KEY)) {
                FeatureToggleModule.getInstance().onToggleChanged(((Boolean) Boolean.class.cast(obj)).booleanValue());
            } else if (str.equals(SettingsStorageModule.LOW_DISTRACTION_MODE_KEY)) {
                FeatureToggleModule.getInstance().onLowDistractionModeChanged(((Boolean) Boolean.class.cast(obj)).booleanValue());
            } else if (str.equals(SettingsStorageModule.SILENT_DISTRACTION_MODE_KEY)) {
                FeatureToggleModule.getInstance().onSilentDistractionModeChanged(((Boolean) Boolean.class.cast(obj)).booleanValue());
            } else if (str.equals(SettingsStorageModule.GROUP_MESSAGES_MASTER_TOGGLE_KEY)) {
                FeatureToggleModule.getInstance().onGroupMessagesMasterToggleChanged(((Boolean) Boolean.class.cast(obj)).booleanValue());
            } else if (str.equals(SettingsStorageModule.APPROVE_INVITATION_ON_ACCESSORY)) {
                StatusEventManager.getInstance().onPauseNewRequestToggleChanged(((Boolean) Boolean.class.cast(obj)).booleanValue());
            } else if (!str.equals(SettingsStorageModule.FOCUS_FILTER_ENABLED_KEY)) {
            } else {
                StatusEventManager.getInstance().onVipFilterToggleChanged(((Boolean) Boolean.class.cast(obj)).booleanValue());
            }
        } catch (Exception e) {
            if (e instanceof RxBlockingCallException) {
                MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_FocusFilterBridgeModule_put", MetricsRecorder.customAttributesForException(e));
            }
            promise.reject(STORAGE_FAILURE, "Exception when save value to storage. Info: " + e);
        }
    }

    public void remove(@NonNull String str, @Nullable String str2, Promise promise) {
        Log.d(TAG, String.format(Locale.US, "remove - key: %s, cloudKey: %s", str, str2));
        try {
            getStorageWrapper().remove(str, str2);
            promise.resolve(true);
        } catch (Exception e) {
            if (e instanceof RxBlockingCallException) {
                MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_FocusFilterBridgeModule_remove", MetricsRecorder.customAttributesForException(e));
            }
            promise.reject(STORAGE_FAILURE, "Exception when remove value from storage. Info: " + e);
        }
    }

    public void showNotificationAccess(Promise promise) {
        this.notificationAccessPromise = promise;
        try {
            this.reactContext.getCurrentActivity().startActivityForResult(new Intent(NOTIFICATION_LISTENER_SETTINGS), NOTIFICATION_REQUEST_CODE);
        } catch (Exception e) {
            Log.w(TAG, "showNotificationAccess caught exception.", e);
            e.printStackTrace();
            Promise promise2 = this.notificationAccessPromise;
            promise2.reject(NOTIFICATION_ACCESS_SCREEN_FAILURE, "Exception when showing notification access screen. Info: " + e);
        }
    }

    @VisibleForTesting
    FocusFilterBridgeModule(ReactApplicationContext reactApplicationContext, StorageWrapper storageWrapper) {
        this.reactContext = reactApplicationContext;
        this.storageUpdater = storageWrapper;
    }

    public void remove(@NonNull String str, @NonNull String str2, @Nullable String str3, Promise promise) {
        Log.d(TAG, String.format(Locale.US, "remove - device type: %s. key: %s, cloudKey: %s", str, str2, str3));
        try {
            VipFilterSettingStorageHelper.getInstance().remove(str, str2, str3);
            promise.resolve(true);
            MetricsRecorder.getInstance().recordCounter(com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants.VIP_FILTER_FOCUS_FILTER_BRIDGE_MODULE_REMOVE_SUCCESS, MetricsRecorder.customAttributesForDeviceType(str));
        } catch (Exception e) {
            MetricsRecorder.getInstance().recordCounter(com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants.VIP_FILTER_FOCUS_FILTER_BRIDGE_MODULE_REMOVE_FAILURE, MetricsRecorder.customAttributesForDeviceTypeAndException(str, e));
            promise.reject(STORAGE_FAILURE, "Exception when remove value by device type. Info: " + e);
        }
    }

    public void clear(String str, Promise promise) {
        GeneratedOutlineSupport1.outline165("clear - deviceType: ", str, TAG);
        try {
            VipFilterSettingStorageHelper.getInstance().clear(str);
            promise.resolve(true);
            MetricsRecorder.getInstance().recordCounter(com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants.VIP_FILTER_FOCUS_FILTER_BRIDGE_MODULE_CLEAR_SUCCESS, MetricsRecorder.customAttributesForDeviceType(str));
        } catch (Exception e) {
            MetricsRecorder.getInstance().recordCounter(com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants.VIP_FILTER_FOCUS_FILTER_BRIDGE_MODULE_CLEAR_FAILURE, MetricsRecorder.customAttributesForDeviceTypeAndException(str, e));
            promise.reject(STORAGE_FAILURE, "Exception when clear by device type. Info: " + e);
        }
    }

    public void get(@NonNull String str, @NonNull String str2, Promise promise) {
        String jSONObject;
        Log.d(TAG, String.format(Locale.US, "get - deviceType: %s, key: %s", str, str2));
        char c = 65535;
        try {
            if (str2.hashCode() == -2032006378 && str2.equals(GET_DEFAULT_MESSAGING_APP_INFO)) {
                c = 0;
            }
            if (c != 0) {
                Object obj = VipFilterSettingStorageHelper.getInstance().get(str, str2);
                Log.d(TAG, "get -- value: " + obj);
                jSONObject = new JSONObject().put("value", obj).toString();
            } else {
                jSONObject = new JSONObject().put("value", ContactFilterSettingsManager.getInstance().getDefaultSmsMessagingAppInfo(this.reactContext)).toString();
            }
            Log.d(TAG, "get -- value json: " + jSONObject);
            promise.resolve(jSONObject);
            MetricsRecorder.getInstance().recordCounter(com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants.VIP_FILTER_FOCUS_FILTER_BRIDGE_MODULE_GET_SUCCESS, MetricsRecorder.customAttributesForDeviceType(str));
        } catch (Exception e) {
            MetricsRecorder.getInstance().recordCounter(com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants.VIP_FILTER_FOCUS_FILTER_BRIDGE_MODULE_GET_FAILURE, MetricsRecorder.customAttributesForDeviceTypeAndException(str, e));
            promise.reject(STORAGE_FAILURE, "Exception when fetch value from Vip Filter Setting Helper. Info: " + e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0039, code lost:
        com.amazon.alexa.accessory.notificationpublisher.storage.VipFilterSettingStorageHelper.getInstance().put(r6, r7, r8, r9);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void put(@androidx.annotation.NonNull java.lang.String r6, @androidx.annotation.NonNull java.lang.String r7, @androidx.annotation.NonNull java.lang.String r8, @androidx.annotation.Nullable java.lang.String r9, com.facebook.react.bridge.Promise r10) {
        /*
            r5 = this;
            java.util.Locale r0 = java.util.Locale.US
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r2 = 0
            r1[r2] = r7
            r3 = 1
            r1[r3] = r9
            java.lang.String r4 = "put - key: %s, cloudKey: %s"
            java.lang.String r0 = java.lang.String.format(r0, r4, r1)
            java.lang.String r1 = com.amazon.alexa.accessory.notificationpublisher.FocusFilterBridgeModule.TAG
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.d(r1, r0)
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: java.lang.Exception -> Lee
            r0.<init>(r8)     // Catch: java.lang.Exception -> Lee
            java.lang.String r8 = "value"
            java.lang.Object r8 = r0.get(r8)     // Catch: java.lang.Exception -> Lee
            r0 = -1
            int r1 = r7.hashCode()     // Catch: java.lang.Exception -> Lee
            r4 = 1482260942(0x585981ce, float:9.5660612E14)
            if (r1 == r4) goto L2e
            goto L37
        L2e:
            java.lang.String r1 = "VipFilterContactSettings"
            boolean r1 = r7.equals(r1)     // Catch: java.lang.Exception -> Lee
            if (r1 == 0) goto L37
            r0 = r2
        L37:
            if (r0 == 0) goto L41
            com.amazon.alexa.accessory.notificationpublisher.storage.VipFilterSettingStorageHelper r0 = com.amazon.alexa.accessory.notificationpublisher.storage.VipFilterSettingStorageHelper.getInstance()     // Catch: java.lang.Exception -> Lee
            r0.put(r6, r7, r8, r9)     // Catch: java.lang.Exception -> Lee
            goto L48
        L41:
            com.amazon.alexa.accessory.notificationpublisher.storage.ContactFilterSettingsManager r9 = com.amazon.alexa.accessory.notificationpublisher.storage.ContactFilterSettingsManager.getInstance()     // Catch: java.lang.Exception -> Lee
            r9.addContactSettings(r7, r8)     // Catch: java.lang.Exception -> Lee
        L48:
            com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder r9 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.getInstance()     // Catch: java.lang.Exception -> Lee
            java.lang.String r0 = "VipFilter_focus_filter_bridge_module_put_success"
            java.util.Map r1 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.customAttributesForDeviceType(r6)     // Catch: java.lang.Exception -> Lee
            r9.recordCounter(r0, r1)     // Catch: java.lang.Exception -> Lee
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r3)     // Catch: java.lang.Exception -> Lee
            r10.resolve(r9)     // Catch: java.lang.Exception -> Lee
            com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder r9 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.getInstance()     // Catch: java.lang.Exception -> Lee
            r9.recordBIMetric(r6, r7, r8)     // Catch: java.lang.Exception -> Lee
            java.lang.String r9 = "Alexa.Accessories.VipFilter.forwardNotificationToAccessoryEnablement"
            boolean r9 = r9.equals(r7)     // Catch: java.lang.Exception -> Lee
            if (r9 == 0) goto L73
            com.amazon.alexa.accessory.notificationpublisher.FeatureToggleModule r9 = com.amazon.alexa.accessory.notificationpublisher.FeatureToggleModule.getInstance()     // Catch: java.lang.Exception -> Lee
            r9.onToggleChanged()     // Catch: java.lang.Exception -> Lee
            goto Le2
        L73:
            java.lang.String r9 = "FocusFilter.lowDistractionMode"
            boolean r9 = r9.equals(r7)     // Catch: java.lang.Exception -> Lee
            if (r9 == 0) goto L8f
            com.amazon.alexa.accessory.notificationpublisher.FeatureToggleModule r9 = com.amazon.alexa.accessory.notificationpublisher.FeatureToggleModule.getInstance()     // Catch: java.lang.Exception -> Lee
            java.lang.Class<java.lang.Boolean> r0 = java.lang.Boolean.class
            java.lang.Object r0 = r0.cast(r8)     // Catch: java.lang.Exception -> Lee
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch: java.lang.Exception -> Lee
            boolean r0 = r0.booleanValue()     // Catch: java.lang.Exception -> Lee
            r9.onLowDistractionModeChanged(r6, r0)     // Catch: java.lang.Exception -> Lee
            goto Le2
        L8f:
            java.lang.String r9 = "FocusFilter.silentDistractionMode"
            boolean r9 = r9.equals(r7)     // Catch: java.lang.Exception -> Lee
            if (r9 == 0) goto Lab
            com.amazon.alexa.accessory.notificationpublisher.FeatureToggleModule r9 = com.amazon.alexa.accessory.notificationpublisher.FeatureToggleModule.getInstance()     // Catch: java.lang.Exception -> Lee
            java.lang.Class<java.lang.Boolean> r0 = java.lang.Boolean.class
            java.lang.Object r0 = r0.cast(r8)     // Catch: java.lang.Exception -> Lee
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch: java.lang.Exception -> Lee
            boolean r0 = r0.booleanValue()     // Catch: java.lang.Exception -> Lee
            r9.onSilentDistractionModeChanged(r6, r0)     // Catch: java.lang.Exception -> Lee
            goto Le2
        Lab:
            java.lang.String r9 = "Alexa.Accessories.VipFilter.approveInvitationOnAccessory"
            boolean r9 = r9.equals(r7)     // Catch: java.lang.Exception -> Lee
            if (r9 == 0) goto Lc7
            com.amazon.alexa.accessory.notificationpublisher.consumption.StatusEventManager r9 = com.amazon.alexa.accessory.notificationpublisher.consumption.StatusEventManager.getInstance()     // Catch: java.lang.Exception -> Lee
            com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule r0 = com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule.getInstance()     // Catch: java.lang.Exception -> Lee
            java.lang.Boolean r0 = r0.getApproveInvitationOnAccessoryWithDefault()     // Catch: java.lang.Exception -> Lee
            boolean r0 = r0.booleanValue()     // Catch: java.lang.Exception -> Lee
            r9.onPauseNewRequestToggleChanged(r0)     // Catch: java.lang.Exception -> Lee
            goto Le2
        Lc7:
            java.lang.String r9 = "Alexa.Accessories.VipFilter.statusEnablement"
            boolean r9 = r9.equals(r7)     // Catch: java.lang.Exception -> Lee
            if (r9 == 0) goto Le2
            com.amazon.alexa.accessory.notificationpublisher.consumption.StatusEventManager r9 = com.amazon.alexa.accessory.notificationpublisher.consumption.StatusEventManager.getInstance()     // Catch: java.lang.Exception -> Lee
            com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule r0 = com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule.getInstance()     // Catch: java.lang.Exception -> Lee
            java.lang.Boolean r0 = r0.getFocusFilterEnabledWithDefault()     // Catch: java.lang.Exception -> Lee
            boolean r0 = r0.booleanValue()     // Catch: java.lang.Exception -> Lee
            r9.onVipFilterToggleChanged(r0)     // Catch: java.lang.Exception -> Lee
        Le2:
            java.lang.String r9 = "A3IYPH06PH1HRA"
            boolean r9 = r9.equals(r6)     // Catch: java.lang.Exception -> Lee
            if (r9 == 0) goto L112
            r5.migrateBackwardForZion(r7, r8)     // Catch: java.lang.Exception -> Lee
            goto L112
        Lee:
            r7 = move-exception
            com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder r8 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.getInstance()
            java.util.Map r6 = com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder.customAttributesForDeviceTypeAndException(r6, r7)
            java.lang.String r9 = "VipFilter_focus_filter_bridge_module_put_failure"
            r8.recordCounter(r9, r6)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "Exception when save value to Vip Filter Setting Helper. Info: "
            r6.append(r8)
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            java.lang.String r7 = "STORAGE_FAILURE"
            r10.reject(r7, r6)
        L112:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessory.notificationpublisher.FocusFilterBridgeModule.put(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.facebook.react.bridge.Promise):void");
    }
}
