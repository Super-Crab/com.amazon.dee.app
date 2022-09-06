package com.amazon.alexa.accessory.notificationpublisher.storage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.notificationpublisher.FeatureToggleModule;
import com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.servicerequest.DevicePreferencesRequestSender;
import com.amazon.alexa.accessory.notificationpublisher.servicerequest.DevicePreferencesResponseHandler;
import com.amazon.alexa.accessory.notificationpublisher.servicerequest.UssSettingRequestSender;
import com.amazon.alexa.accessory.notificationpublisher.servicerequest.UssSettingResponseHandler;
import com.amazon.alexa.accessory.notificationpublisher.utils.FeatureAccessChecker;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsConstants;
import com.google.common.base.Strings;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class StorageWrapper implements UssSettingResponseHandler, DevicePreferencesResponseHandler {
    private static final Semaphore DEVICE_ACCOUNT_ID_STORAGE_SEMAPHORE = new Semaphore(1);
    private static final Set<String> SET_OF_SETTING_KEYS_ONLY_STORED_LOCALLY = new HashSet(Arrays.asList(SettingsStorageModule.LOW_DISTRACTION_MODE_KEY, SettingsStorageModule.SILENT_DISTRACTION_MODE_KEY, SettingsStorageModule.GROUP_MESSAGES_MASTER_TOGGLE_KEY, SettingsStorageModule.REPLY_KEY, SettingsStorageModule.REPLY_READ_BACK_KEY));
    private static final Set<String> SET_OF_SETTING_KEYS_WITH_GROUP_SETTINGS_ONLY_STORED_LOCALLY = new HashSet(Arrays.asList(SettingsStorageModule.LOW_DISTRACTION_MODE_KEY, SettingsStorageModule.SILENT_DISTRACTION_MODE_KEY, SettingsStorageModule.FOCUS_FILTER_APP_GROUP_SETTINGS_KEY, SettingsStorageModule.FOCUS_FILTER_NAMED_GROUP_SETTINGS_KEY, SettingsStorageModule.GROUP_MESSAGES_MASTER_TOGGLE_KEY, SettingsStorageModule.REPLY_KEY, SettingsStorageModule.REPLY_READ_BACK_KEY));
    private static final Set<String> SET_OF_SETTING_KEYS_WITH_JSON_OBJECT_VALUES = new HashSet(Arrays.asList(SettingsStorageModule.FOCUS_FILTER_APP_GROUP_SETTINGS_KEY, SettingsStorageModule.FOCUS_FILTER_NAMED_GROUP_SETTINGS_KEY, SettingsStorageModule.FOCUS_FILTER_APP_SETTINGS_KEY, SettingsStorageModule.FOCUS_FILTER_CONTACT_SETTINGS_KEY, SettingsStorageModule.VIP_FILTER_CONTACT_SETTINGS_KEY));
    private static final String TAG = "StorageWrapper";
    private final AsyncLocalStorage asyncLocalStorage;
    private final AsyncLocalStorage deviceAccountAsyncLocalStorage;
    private final String localStorageTag;
    private final UssSettingRequestSender ussSettingRequestSender;

    public StorageWrapper() {
        this(DependencyProvider.getNativeLocalStorageModule(), LocalStorageHelper.FOCUS_FILTER_TAG);
    }

    public static String getDeviceAccountId(String str) {
        try {
            String serialNumber = DependencyProvider.getSerialNumber();
            JSONArray jSONArray = new JSONObject(str).getJSONArray("devicePreferences");
            if (jSONArray == null || jSONArray.length() == 0) {
                return null;
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                JSONArray optJSONArray = jSONObject.optJSONArray("appDeviceList");
                if (optJSONArray != null || optJSONArray.length() != 0) {
                    for (int length = jSONObject.optJSONArray("appDeviceList").length() - 1; length >= 0; length--) {
                        JSONObject jSONObject2 = (JSONObject) optJSONArray.get(length);
                        if (serialNumber.equals(jSONObject2.optString(Constants.BUNDLE_SERIAL_NUMBER))) {
                            return jSONObject2.optString("deviceAccountId");
                        }
                    }
                    continue;
                }
            }
            return null;
        } catch (Exception e) {
            Log.e(TAG, "getDeviceAccountId: Exception - " + e);
            return null;
        }
    }

    private boolean isEmptyJson(String str) {
        return str == null || str.equals("{}");
    }

    private JSONObject stringToJson(String str) throws JSONException {
        if (Strings.isNullOrEmpty(str)) {
            return new JSONObject();
        }
        return new JSONObject(str);
    }

    private void updateBooleanSettingsFromCloud(@NonNull String str, boolean z, boolean z2) {
        Log.d(TAG, String.format(Locale.US, "updateBooleanSettingsFromCloud - key: %s, value: %s, willRetry: %s", str, Boolean.valueOf(z), Boolean.valueOf(z2)));
        try {
            putLocal(str, Boolean.valueOf(z));
            if (FeatureAccessChecker.hasOtgVipFilterAccess() && FeatureAccessChecker.hasOtgVipFilterUssSettingsAccess()) {
                Log.i(TAG, String.format("updateBooleanSettingsFromCloud for migration key: %s, value: %s,", str, Boolean.valueOf(z)));
                if (SettingsStorageModule.FORWARD_NOTIFICATION_TO_ACCESSORY_KEY.equals(str)) {
                    if (MigrationHelper.isForwardNotificationToAccessoryFetched()) {
                        VipFilterSettingStorageHelper.getInstance().put("A3IYPH06PH1HRA", SettingsStorageModule.VIP_FILTER_FORWARD_NOTIFICATION_TO_ACCESSORY_ENABLEMENT_KEY, Boolean.valueOf(z), SettingsStorageModule.VIP_FILTER_FORWARD_NOTIFICATION_TO_ACCESSORY_ENABLEMENT_KEY);
                    } else {
                        MigrationHelper.setOldZionForwardNotificationToAccessory(Boolean.valueOf(z));
                    }
                } else if (SettingsStorageModule.APPROVE_INVITATION_ON_ACCESSORY.equals(str)) {
                    if (MigrationHelper.isApproveInvitationOnAccessoryFetched()) {
                        VipFilterSettingStorageHelper.getInstance().put("A3IYPH06PH1HRA", SettingsStorageModule.VIP_FILTER_APPROVE_INVITATION_ON_ACCESSORY, Boolean.valueOf(z), SettingsStorageModule.VIP_FILTER_APPROVE_INVITATION_ON_ACCESSORY);
                    } else {
                        MigrationHelper.setOldZionApproveInvitationOnAccessory(Boolean.valueOf(z));
                    }
                } else if (SettingsStorageModule.FOCUS_FILTER_ENABLED_KEY.equals(str)) {
                    if (MigrationHelper.isStatusEnablementFetched()) {
                        VipFilterSettingStorageHelper.getInstance().put("A3IYPH06PH1HRA", SettingsStorageModule.VIP_FILTER_STATUS_ENABLEMENT_KEY, Boolean.valueOf(z), SettingsStorageModule.VIP_FILTER_STATUS_ENABLEMENT_KEY);
                    } else {
                        MigrationHelper.setOldZionStatusEnablement(Boolean.valueOf(z));
                    }
                }
            } else if (str.equals(SettingsStorageModule.FORWARD_NOTIFICATION_TO_ACCESSORY_KEY)) {
                FeatureToggleModule.getInstance().onToggleChanged(z);
            }
        } catch (Exception e) {
            Log.e(TAG, String.format(Locale.US, "Failed to update local storage for key: %s, Error: %s, willRetry: %s", str, e, Boolean.valueOf(z2)), e);
            if (!z2) {
                return;
            }
            updateBooleanSettingsFromCloud(str, z, false);
        }
    }

    private void updateMapFromCloud(@NonNull JSONObject jSONObject, @NonNull String str, boolean z) {
        Log.d(TAG, String.format(Locale.US, "updateMapFromCloud - arrayKey: %s, json length: %d, willRetry: %s", str, Integer.valueOf(jSONObject.length()), Boolean.valueOf(z)));
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                putLocal(str2, jSONObject.getJSONObject(str2));
                jSONArray.put(str2);
            }
            putLocal(str, jSONArray);
        } catch (Exception e) {
            Log.e(TAG, String.format(Locale.US, "Failed to update local storage. Error: %s, willRetry: %s", e, Boolean.valueOf(z)));
            if (!z) {
                return;
            }
            updateMapFromCloud(jSONObject, str, false);
        }
    }

    public void clear() throws IllegalArgumentException, RxBlockingCallException {
        Log.d(TAG, MetricsConstants.Method.CACHE_CLEAR);
        this.asyncLocalStorage.clearSync();
        clearCloud();
        MetricsRecorder.getInstance().recordCounter(com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants.STORAGEWRAPPER_CLEAR);
    }

    public void clearCloud() {
        Log.d(TAG, "clear cloud storage");
        Executors.newCachedThreadPool().submit(new Runnable() { // from class: com.amazon.alexa.accessory.notificationpublisher.storage.StorageWrapper.7
            @Override // java.lang.Runnable
            public void run() {
                StorageWrapper.this.ussSettingRequestSender.checkDeviceAccountIdAndSetUssSettingsRequest(SettingsStorageModule.FOCUS_FILTER_APP_SETTINGS_KEY, LocalStorageHelper.EMPTY_VALUE_JSON_OBJECT_STR);
                StorageWrapper.this.ussSettingRequestSender.checkDeviceAccountIdAndSetUssSettingsRequest(SettingsStorageModule.FOCUS_FILTER_CONTACT_SETTINGS_KEY, LocalStorageHelper.EMPTY_VALUE_JSON_OBJECT_STR);
            }
        });
    }

    public void fetchSettingsAfterUserLogin() {
        Log.d(TAG, "fetchSettingsAfterUserLogin");
        if (LocalStorageHelper.FOCUS_FILTER_TAG.equals(this.localStorageTag)) {
            this.ussSettingRequestSender.checkDeviceAccountIdAndGetUssSettingsRequest(SettingsStorageModule.FORWARD_NOTIFICATION_TO_ACCESSORY_KEY, this);
            this.ussSettingRequestSender.checkDeviceAccountIdAndGetUssSettingsRequest(SettingsStorageModule.FOCUS_FILTER_ENABLED_KEY, this);
            this.ussSettingRequestSender.checkDeviceAccountIdAndGetUssSettingsRequest(SettingsStorageModule.APPROVE_INVITATION_ON_ACCESSORY, this);
            this.ussSettingRequestSender.checkDeviceAccountIdAndGetUssSettingsRequest(SettingsStorageModule.FOCUS_FILTER_APP_SETTINGS_KEY, this);
            this.ussSettingRequestSender.checkDeviceAccountIdAndGetUssSettingsRequest(SettingsStorageModule.FOCUS_FILTER_CONTACT_SETTINGS_KEY, this);
            if (!FeatureAccessChecker.hasGroupNotificationUssSettingsFeatureAccess()) {
                return;
            }
            this.ussSettingRequestSender.checkDeviceAccountIdAndGetUssSettingsRequest(SettingsStorageModule.FOCUS_FILTER_APP_GROUP_SETTINGS_KEY, this);
            this.ussSettingRequestSender.checkDeviceAccountIdAndGetUssSettingsRequest(SettingsStorageModule.FOCUS_FILTER_NAMED_GROUP_SETTINGS_KEY, this);
            return;
        }
        Log.d(TAG, "Fetching for multi device settings after login");
        this.ussSettingRequestSender.checkDeviceAccountIdAndGetUssSettingsRequest(SettingsStorageModule.VIP_FILTER_STATUS_ENABLEMENT_KEY, this);
        this.ussSettingRequestSender.checkDeviceAccountIdAndGetUssSettingsRequest(SettingsStorageModule.VIP_FILTER_SYNC_STATUS_ENABLEMENT_KEY, this);
        this.ussSettingRequestSender.checkDeviceAccountIdAndGetUssSettingsRequest(SettingsStorageModule.VIP_FILTER_FORWARD_NOTIFICATION_TO_ACCESSORY_ENABLEMENT_KEY, this);
        this.ussSettingRequestSender.checkDeviceAccountIdAndGetUssSettingsRequest(SettingsStorageModule.VIP_FILTER_APPROVE_INVITATION_ON_ACCESSORY, this);
    }

    public Object get(@NonNull String str) throws JSONException, RxBlockingCallException {
        GeneratedOutlineSupport1.outline165("get from local storage -- key: ", str, TAG);
        try {
            Object sync = this.asyncLocalStorage.getSync(str, null);
            MetricsRecorder.getInstance().recordCounter(com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants.STORAGEWRAPPER_GET_SUCCESS);
            return sync;
        } catch (Exception e) {
            MetricsRecorder.getInstance().recordCounter(com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants.STORAGEWRAPPER_GET_ERROR, MetricsRecorder.customAttributesForException(e));
            throw e;
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.servicerequest.DevicePreferencesResponseHandler
    public void handleGetDevicePreferencesResponse(boolean z, Response response) {
        Log.i(TAG, String.format("handleGetDevicePreferencesResponse - success: %s", Boolean.valueOf(z)));
        String str = TAG;
        Log.d(str, "handleGetDevicePreferencesResponse - response " + response);
        try {
            try {
                if (z && response != null) {
                    String string = response.body().string();
                    String str2 = TAG;
                    Log.d(str2, "handleGetDevicePreferencesResponse - value string: " + string);
                    String deviceAccountId = getDeviceAccountId(string);
                    DEVICE_ACCOUNT_ID_STORAGE_SEMAPHORE.acquire();
                    this.deviceAccountAsyncLocalStorage.put(SettingsStorageModule.DEVICE_ACCOUNT_ID_KEY, deviceAccountId, null, new PutValueCallback() { // from class: com.amazon.alexa.accessory.notificationpublisher.storage.StorageWrapper.1
                        @Override // com.amazon.alexa.accessory.notificationpublisher.storage.PutValueCallback
                        public void onComplete(Object obj) {
                            Log.d(StorageWrapper.TAG, "handleGetDevicePreferencesResponse - successfully put device account id into local storage");
                            StorageWrapper.this.fetchSettingsAfterUserLogin();
                            StorageWrapper.DEVICE_ACCOUNT_ID_STORAGE_SEMAPHORE.release();
                        }

                        @Override // com.amazon.alexa.accessory.notificationpublisher.storage.PutValueCallback
                        public void onError(Throwable th) {
                            String str3 = StorageWrapper.TAG;
                            Log.e(str3, "handleGetDevicePreferencesResponse - Failed to store device account id: " + th);
                            StorageWrapper.DEVICE_ACCOUNT_ID_STORAGE_SEMAPHORE.release();
                        }
                    });
                } else {
                    Log.w(TAG, "handleGetDevicePreferencesResponse - GET device preferences failed");
                }
                if (response == null) {
                    return;
                }
            } catch (Exception e) {
                DEVICE_ACCOUNT_ID_STORAGE_SEMAPHORE.release();
                String str3 = TAG;
                Log.e(str3, "handleGetDevicePreferencesResponse: Exception - " + e);
                if (response == null) {
                    return;
                }
            }
            response.close();
        } catch (Throwable th) {
            if (response != null) {
                response.close();
            }
            throw th;
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.servicerequest.UssSettingResponseHandler
    public void handleGetUssResponse(boolean z, String str, Response response) {
        Log.d(TAG, String.format("handleGetUssResponse - success: %s, cloudKey: %s", Boolean.valueOf(z), str));
        String str2 = TAG;
        Log.d(str2, "handleGetUssResponse - response " + response);
        try {
            try {
                if (z && response != null) {
                    String optString = new JSONObject(response.body().string()).optString("value");
                    String str3 = TAG;
                    Log.d(str3, "handleGetUssResponse - value string: " + optString);
                    if (isEmptyJson(optString)) {
                        String str4 = TAG;
                        Log.d(str4, "handleGetUssResponse - not updating local storage to empty value for " + str);
                    } else if (SettingsStorageModule.STORAGE_KEY_CLOUD_KEY_BI_MAP.containsKey(str)) {
                        JSONObject stringToJson = stringToJson(optString);
                        String str5 = TAG;
                        Log.d(str5, "handleGetUssResponse - value JSON: " + stringToJson);
                        updateMapFromCloud(stringToJson, SettingsStorageModule.STORAGE_KEY_CLOUD_KEY_BI_MAP.get(str), true);
                    } else if (VipFilterSettingStorageHelper.VIP_FILTER_SETTING_KEYS.contains(str)) {
                        JSONObject stringToJson2 = stringToJson(optString);
                        String str6 = TAG;
                        Log.d(str6, "handleGetUssResponse - value JSON by device type: " + stringToJson2);
                        if (FeatureAccessChecker.hasOtgVipFilterAccess() && FeatureAccessChecker.hasOtgVipFilterUssSettingsAccess()) {
                            putLocal(str, stringToJson2);
                            if (SettingsStorageModule.VIP_FILTER_FORWARD_NOTIFICATION_TO_ACCESSORY_ENABLEMENT_KEY.equals(str)) {
                                FeatureToggleModule.getInstance().onToggleChanged();
                            }
                            MigrationHelper.updateFetchStatus(str);
                        }
                    } else if (str.equals(SettingsStorageModule.FOCUS_FILTER_APP_GROUP_SETTINGS_KEY)) {
                        JSONObject stringToJson3 = stringToJson(optString);
                        String str7 = TAG;
                        Log.d(str7, "handleGetUssResponse - unnamed group setting value JSON: " + stringToJson3);
                        putLocal(str, stringToJson3);
                    } else {
                        updateBooleanSettingsFromCloud(str, Boolean.valueOf(optString).booleanValue(), true);
                    }
                } else {
                    String str8 = TAG;
                    Log.d(str8, "handleGetUssResponse - GET Settings failed for key " + str);
                }
                if (response == null) {
                    return;
                }
            } catch (Exception e) {
                Log.e(TAG, "handleGetUssResponse: Exception.", e);
                if (response == null) {
                    return;
                }
            }
            response.close();
        } catch (Throwable th) {
            if (response != null) {
                response.close();
            }
            throw th;
        }
    }

    public void put(@NonNull String str, @NonNull Object obj, @Nullable String str2) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        if (obj == null) {
            Log.i(TAG, "put - input value object cannot be null");
            return;
        }
        Log.d(TAG, String.format(Locale.US, "put - key: %s, cloudKey: %s", str, str2));
        String str3 = TAG;
        Log.d(str3, "put - value: " + obj);
        putLocalSync(str, obj);
        String str4 = Strings.isNullOrEmpty(str2) ? str : str2;
        Set<String> set = FeatureAccessChecker.hasGroupNotificationUssSettingsFeatureAccess() ? SET_OF_SETTING_KEYS_ONLY_STORED_LOCALLY : SET_OF_SETTING_KEYS_WITH_GROUP_SETTINGS_ONLY_STORED_LOCALLY;
        if (Strings.isNullOrEmpty(str2) || set.contains(str2)) {
            return;
        }
        putCloud(str4, obj, str);
    }

    public void putCloud(@NonNull final String str, @NonNull Object obj, String str2) throws JSONException, RxBlockingCallException {
        Log.d(TAG, String.format(Locale.US, "put to cloud storage - key: %s, value: %s", str, obj));
        final JSONObject jSONObject = new JSONObject();
        if (obj == null) {
            Log.d(TAG, "putCloud - input value object cannot be null");
        } else if (SET_OF_SETTING_KEYS_WITH_JSON_OBJECT_VALUES.contains(str)) {
            JSONObject jSONObject2 = new JSONObject(obj.toString());
            char c = 65535;
            switch (str.hashCode()) {
                case -131220651:
                    if (str.equals(SettingsStorageModule.FOCUS_FILTER_APP_SETTINGS_KEY)) {
                        c = 2;
                        break;
                    }
                    break;
                case 661514516:
                    if (str.equals(SettingsStorageModule.FOCUS_FILTER_CONTACT_SETTINGS_KEY)) {
                        c = 4;
                        break;
                    }
                    break;
                case 788877774:
                    if (str.equals(SettingsStorageModule.FOCUS_FILTER_APP_GROUP_SETTINGS_KEY)) {
                        c = 0;
                        break;
                    }
                    break;
                case 1438189747:
                    if (str.equals(SettingsStorageModule.FOCUS_FILTER_NAMED_GROUP_SETTINGS_KEY)) {
                        c = 1;
                        break;
                    }
                    break;
                case 1482260942:
                    if (str.equals(SettingsStorageModule.VIP_FILTER_CONTACT_SETTINGS_KEY)) {
                        c = 3;
                        break;
                    }
                    break;
            }
            if (c != 0) {
                if (c == 1) {
                    jSONObject2 = SettingsStorageModule.getInstance().getAllUpdatedNamedGroupFilterSettings(str2, jSONObject2);
                } else if (c == 2) {
                    jSONObject2 = SettingsStorageModule.getInstance().getAllUpdatedAppFilterSettings(str2, jSONObject2);
                } else if (c != 3) {
                    jSONObject2 = SettingsStorageModule.getInstance().getAllUpdatedContactFilterSettings(str2, jSONObject2);
                } else {
                    jSONObject2 = SettingsStorageModule.getInstance().getAllUpdatedContactFilterSettings();
                }
            }
            jSONObject.put("value", jSONObject2.toString());
            Executors.newCachedThreadPool().submit(new Runnable() { // from class: com.amazon.alexa.accessory.notificationpublisher.storage.StorageWrapper.5
                @Override // java.lang.Runnable
                public void run() {
                    StorageWrapper.this.ussSettingRequestSender.checkDeviceAccountIdAndSetUssSettingsRequest(str, jSONObject);
                }
            });
        } else {
            jSONObject.put("value", obj.toString());
            Executors.newCachedThreadPool().submit(new Runnable() { // from class: com.amazon.alexa.accessory.notificationpublisher.storage.StorageWrapper.6
                @Override // java.lang.Runnable
                public void run() {
                    StorageWrapper.this.ussSettingRequestSender.checkDeviceAccountIdAndSetUssSettingsRequest(str, jSONObject);
                }
            });
        }
    }

    public void putLocal(@NonNull final String str, @Nullable Object obj) {
        Log.d(TAG, String.format(Locale.US, "put to local storage - key: %s, value: %s", str, obj));
        this.asyncLocalStorage.put(str, obj, null, new PutValueCallback() { // from class: com.amazon.alexa.accessory.notificationpublisher.storage.StorageWrapper.4
            @Override // com.amazon.alexa.accessory.notificationpublisher.storage.PutValueCallback
            public void onComplete(Object obj2) {
                String str2 = StorageWrapper.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("putLocal - put succeeded for key: ");
                outline107.append(str);
                outline107.append(", value: ");
                outline107.append(obj2.toString());
                Log.i(str2, outline107.toString());
            }

            @Override // com.amazon.alexa.accessory.notificationpublisher.storage.PutValueCallback
            public void onError(Throwable th) {
                String str2 = StorageWrapper.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("putLocal - Error: ");
                outline107.append(th.getMessage());
                Log.e(str2, outline107.toString());
                MetricsRecorder.getInstance().recordCounter(com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants.STORAGEWRAPPER_PUT_ERROR, MetricsRecorder.customAttributesForException(new Exception(th.getMessage(), th)));
            }
        });
    }

    public void putLocalSync(@NonNull String str, @Nullable Object obj) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        Log.d(TAG, String.format(Locale.US, "put to local storage with blocking call - key: %s, value: %s", str, obj));
        try {
            this.asyncLocalStorage.putSync(str, obj, null);
        } catch (Exception e) {
            MetricsRecorder.getInstance().recordCounter(com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants.STORAGEWRAPPER_PUT_SYNC_ERROR, MetricsRecorder.customAttributesForException(e));
            throw e;
        }
    }

    public void remove(@NonNull String str, @Nullable String str2) throws RxBlockingCallException {
        Log.d(TAG, String.format(Locale.US, "remove - key: %s, cloudKey: %s", str, str2));
        this.asyncLocalStorage.removeSync(str, false);
        removeCloud(str2, str);
    }

    public void removeCloud(@NonNull String str, @Nullable Object obj) {
        Log.d(TAG, String.format(Locale.US, "remove from cloud storage - key: %s, value: %s", str, obj));
        this.ussSettingRequestSender.removeUssSettingsRequest(str, obj);
    }

    public void removeLocal(@NonNull final String str) {
        Log.i(TAG, String.format(Locale.US, "remove - key: %s", str));
        this.asyncLocalStorage.remove(str, false, new AsyncLocalStorageCallback() { // from class: com.amazon.alexa.accessory.notificationpublisher.storage.StorageWrapper.3
            @Override // com.amazon.alexa.accessory.notificationpublisher.storage.AsyncLocalStorageCallback
            public void onComplete(@Nullable Object obj) {
                String str2 = StorageWrapper.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("removeLocal - remove succeeded for key: ");
                outline107.append(str);
                Log.i(str2, outline107.toString());
            }

            @Override // com.amazon.alexa.accessory.notificationpublisher.storage.AsyncLocalStorageCallback
            public void onError(Throwable th) {
                String str2 = StorageWrapper.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("removeLocal - Error: ");
                outline107.append(th.getMessage());
                Log.e(str2, outline107.toString());
                MetricsRecorder.getInstance().recordCounter(com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants.STORAGEWRAPPER_REMOVE_ERROR, MetricsRecorder.customAttributesForException(new Exception(th.getMessage(), th)));
            }
        });
    }

    public void startFetchFromCloudAsyncTask() {
        Executors.newCachedThreadPool().submit(new Runnable() { // from class: com.amazon.alexa.accessory.notificationpublisher.storage.StorageWrapper.2
            @Override // java.lang.Runnable
            public void run() {
                Log.i(StorageWrapper.TAG, "startFetchFromCloudAsyncTask");
                DevicePreferencesRequestSender.getDevicePreferencesRequest(this);
            }
        });
    }

    public StorageWrapper(String str) {
        this(DependencyProvider.getNativeLocalStorageModule(), str);
    }

    public StorageWrapper(NativeLocalStorageModule nativeLocalStorageModule, String str) {
        if (nativeLocalStorageModule != null) {
            this.localStorageTag = str;
            this.asyncLocalStorage = new AsyncLocalStorage(nativeLocalStorageModule, str);
            this.deviceAccountAsyncLocalStorage = new AsyncLocalStorage(nativeLocalStorageModule, SettingsStorageModule.DEVICE_ACCOUNT_ID_KEY);
            this.ussSettingRequestSender = new UssSettingRequestSender(this.deviceAccountAsyncLocalStorage);
            return;
        }
        throw new IllegalArgumentException("Cannot initialize StorageWrapper with a null NativeLocalStorageModule.");
    }
}
