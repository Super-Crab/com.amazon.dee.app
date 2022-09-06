package com.amazon.alexa.accessory.notificationpublisher.storage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;
import java.util.Locale;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class VipFilterSettingStorageHelper {
    public static final String EMPTY_STRING = "";
    private static final String LOCAL_STORAGE_TAG = "VIP_FILTER";
    private static final String TAG = "VipFilterSettingStorageHelper";
    private static VipFilterSettingStorageHelper vipFilterSettingStorageHelper;
    private StorageWrapper storageWrapper;
    public static final Set<String> VIP_FILTER_SETTING_KEYS = new ImmutableSet.Builder().mo7849add((ImmutableSet.Builder) SettingsStorageModule.VIP_FILTER_APPROVE_INVITATION_ON_ACCESSORY).mo7849add((ImmutableSet.Builder) SettingsStorageModule.VIP_FILTER_APP_SINGLE_NAMED_GROUP_STATUS).mo7849add((ImmutableSet.Builder) SettingsStorageModule.VIP_FILTER_APP_STATUS).mo7849add((ImmutableSet.Builder) SettingsStorageModule.VIP_FILTER_APP_UNNAMED_GROUP_STATUS_KEY).mo7849add((ImmutableSet.Builder) SettingsStorageModule.VIP_FILTER_CONTACT_STATUS_KEY).mo7849add((ImmutableSet.Builder) SettingsStorageModule.VIP_FILTER_FORWARD_NOTIFICATION_TO_ACCESSORY_ENABLEMENT_KEY).mo7849add((ImmutableSet.Builder) SettingsStorageModule.VIP_FILTER_STATUS_ENABLEMENT_KEY).mo7849add((ImmutableSet.Builder) SettingsStorageModule.VIP_FILTER_SYNC_STATUS_ENABLEMENT_KEY).mo7849add((ImmutableSet.Builder) SettingsStorageModule.LOW_DISTRACTION_MODE_KEY).mo7849add((ImmutableSet.Builder) SettingsStorageModule.SILENT_DISTRACTION_MODE_KEY).mo7849add((ImmutableSet.Builder) SettingsStorageModule.GROUP_MESSAGES_MASTER_TOGGLE_KEY).mo7849add((ImmutableSet.Builder) SettingsStorageModule.REPLY_FTU_FEATURE_EDUCATION_COMPLETE_KEY).mo7849add((ImmutableSet.Builder) SettingsStorageModule.GROUP_MESSAGES_USER_EDUCATION_COMPLETE_KEY).mo7849add((ImmutableSet.Builder) SettingsStorageModule.FOCUS_FILTER_REJECTED_INVITATION_NUM_KEY).mo7849add((ImmutableSet.Builder) SettingsStorageModule.FOCUS_FILTER_OPENED_NOTIFICATION_NUM_KEY).mo7852build();
    private static final Set<String> VIP_FILTER_STRING_STRING_PAIR_SETTINGS = new ImmutableSet.Builder().mo7849add((ImmutableSet.Builder) SettingsStorageModule.VIP_FILTER_APPROVE_INVITATION_ON_ACCESSORY).mo7849add((ImmutableSet.Builder) SettingsStorageModule.VIP_FILTER_FORWARD_NOTIFICATION_TO_ACCESSORY_ENABLEMENT_KEY).mo7849add((ImmutableSet.Builder) SettingsStorageModule.VIP_FILTER_STATUS_ENABLEMENT_KEY).mo7849add((ImmutableSet.Builder) SettingsStorageModule.VIP_FILTER_SYNC_STATUS_ENABLEMENT_KEY).mo7849add((ImmutableSet.Builder) SettingsStorageModule.LOW_DISTRACTION_MODE_KEY).mo7849add((ImmutableSet.Builder) SettingsStorageModule.SILENT_DISTRACTION_MODE_KEY).mo7849add((ImmutableSet.Builder) SettingsStorageModule.GROUP_MESSAGES_MASTER_TOGGLE_KEY).mo7849add((ImmutableSet.Builder) SettingsStorageModule.GROUP_MESSAGES_USER_EDUCATION_COMPLETE_KEY).mo7849add((ImmutableSet.Builder) SettingsStorageModule.REPLY_FTU_FEATURE_EDUCATION_COMPLETE_KEY).mo7849add((ImmutableSet.Builder) SettingsStorageModule.FOCUS_FILTER_REJECTED_INVITATION_NUM_KEY).mo7849add((ImmutableSet.Builder) SettingsStorageModule.FOCUS_FILTER_OPENED_NOTIFICATION_NUM_KEY).mo7852build();
    public static final JSONObject EMPTY_JSON_OBJECT = new JSONObject();

    private VipFilterSettingStorageHelper() {
    }

    private Object convertToBooleanForEnablementSettings(String str, Object obj) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode == -502436310) {
            if (str.equals(SettingsStorageModule.VIP_FILTER_FORWARD_NOTIFICATION_TO_ACCESSORY_ENABLEMENT_KEY)) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 1846186485) {
            if (hashCode == 1869150543 && str.equals(SettingsStorageModule.VIP_FILTER_APPROVE_INVITATION_ON_ACCESSORY)) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals(SettingsStorageModule.VIP_FILTER_STATUS_ENABLEMENT_KEY)) {
                c = 2;
            }
            c = 65535;
        }
        return (c == 0 || c == 1 || c == 2) ? Boolean.valueOf("ENABLED".equalsIgnoreCase(obj.toString())) : obj;
    }

    private Object convertToEnabledForEnablementSettings(String str, Object obj) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode == -502436310) {
            if (str.equals(SettingsStorageModule.VIP_FILTER_FORWARD_NOTIFICATION_TO_ACCESSORY_ENABLEMENT_KEY)) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 1846186485) {
            if (hashCode == 1869150543 && str.equals(SettingsStorageModule.VIP_FILTER_APPROVE_INVITATION_ON_ACCESSORY)) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals(SettingsStorageModule.VIP_FILTER_STATUS_ENABLEMENT_KEY)) {
                c = 2;
            }
            c = 65535;
        }
        return (c == 0 || c == 1 || c == 2) ? ((Boolean) obj).booleanValue() ? "ENABLED" : "DISABLED" : obj;
    }

    public static VipFilterSettingStorageHelper getInstance() {
        if (vipFilterSettingStorageHelper == null) {
            vipFilterSettingStorageHelper = new VipFilterSettingStorageHelper();
        }
        return vipFilterSettingStorageHelper;
    }

    private synchronized StorageWrapper getStorageWrapper() {
        if (this.storageWrapper != null) {
            return this.storageWrapper;
        }
        this.storageWrapper = new StorageWrapper(LOCAL_STORAGE_TAG);
        return this.storageWrapper;
    }

    public void clear(@NonNull String str) throws IllegalArgumentException, RxBlockingCallException, JSONException {
        Log.d(TAG, String.format(Locale.US, "clear - device type: %s", str));
        try {
            for (String str2 : VIP_FILTER_SETTING_KEYS) {
                JSONObject jSONObject = (JSONObject) getStorageWrapper().get(str2);
                if (jSONObject != null && jSONObject.has(str)) {
                    if (SettingsStorageModule.VIP_FILTER_CONTACT_STATUS_KEY.equals(str2) || SettingsStorageModule.VIP_FILTER_APP_STATUS.equals(str2) || SettingsStorageModule.VIP_FILTER_APP_SINGLE_NAMED_GROUP_STATUS.equals(str2) || SettingsStorageModule.VIP_FILTER_APP_UNNAMED_GROUP_STATUS_KEY.equals(str2)) {
                        getStorageWrapper().putCloud(str2, EMPTY_JSON_OBJECT, str2);
                    }
                    jSONObject.remove(str);
                    getStorageWrapper().putLocal(str2, jSONObject);
                }
            }
        } catch (Exception e) {
            String str3 = TAG;
            Log.e(str3, "Failed to clear for " + str, e);
            throw e;
        }
    }

    public Object get(@NonNull String str, @NonNull String str2) throws JSONException, RxBlockingCallException {
        String str3 = TAG;
        Log.d(str3, "get - deviceType: " + str + " key: " + str2);
        try {
            JSONObject jSONObject = (JSONObject) getStorageWrapper().get(str2);
            String str4 = TAG;
            Log.d(str4, "get -- value: " + jSONObject);
            if (jSONObject != null && jSONObject.has(str)) {
                return convertToBooleanForEnablementSettings(str2, jSONObject.get(str));
            }
            return VIP_FILTER_STRING_STRING_PAIR_SETTINGS.contains(str2) ? "" : EMPTY_JSON_OBJECT;
        } catch (Exception e) {
            Log.e(TAG, GeneratedOutlineSupport1.outline76("Failed to get setting: ", str2, " for deviceType: ", str), e);
            throw e;
        }
    }

    public void put(@NonNull String str, @NonNull String str2, @NonNull Object obj, @Nullable String str3) throws RxBlockingCallException, JSONException {
        JSONObject jSONObject;
        Log.d(TAG, String.format(Locale.US, "put - deviceType: %s, key: %s, cloudKey: %s", str, str2, str3));
        try {
            if (Strings.isNullOrEmpty(str3)) {
                str3 = str2;
            }
            Object obj2 = getStorageWrapper().get(str2);
            if (obj2 == null) {
                String str4 = TAG;
                Log.d(str4, "Creating a new setting for this device type: " + str);
                jSONObject = new JSONObject();
            } else {
                jSONObject = (JSONObject) obj2;
            }
            jSONObject.put(str, convertToEnabledForEnablementSettings(str2, obj));
            getStorageWrapper().put(str2, jSONObject, str3);
        } catch (Exception e) {
            Log.e(TAG, GeneratedOutlineSupport1.outline76("Failed to put setting: ", str2, " for deviceType: ", str), e);
            throw e;
        }
    }

    public void putLocal(@NonNull String str, @NonNull String str2, @NonNull Object obj) throws JSONException, RxBlockingCallException {
        JSONObject jSONObject;
        try {
            Object obj2 = getStorageWrapper().get(str2);
            if (obj2 == null) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = (JSONObject) obj2;
            }
            jSONObject.put(str, obj);
            getStorageWrapper().putLocal(str2, jSONObject);
        } catch (RxBlockingCallException e) {
            e.printStackTrace();
            throw e;
        } catch (JSONException e2) {
            e2.printStackTrace();
            throw e2;
        }
    }

    public void putLocalSync(@NonNull String str, @NonNull String str2, @NonNull Object obj) throws JSONException, RxBlockingCallException {
        JSONObject jSONObject;
        try {
            Object obj2 = getStorageWrapper().get(str2);
            if (obj2 == null) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = (JSONObject) obj2;
            }
            jSONObject.put(str, obj);
            getStorageWrapper().putLocalSync(str2, jSONObject);
        } catch (RxBlockingCallException e) {
            e.printStackTrace();
            throw e;
        } catch (JSONException e2) {
            e2.printStackTrace();
            throw e2;
        }
    }

    public void remove(@NonNull String str, @NonNull String str2, @Nullable String str3) throws JSONException, RxBlockingCallException {
        Log.d(TAG, String.format(Locale.US, "remove - device type: %s, key: %s, cloudKey: %s", str, str2, str3));
        try {
            JSONObject jSONObject = (JSONObject) getStorageWrapper().get(str2);
            if (jSONObject != null && jSONObject.has(str)) {
                jSONObject.remove(str);
                if (Strings.isNullOrEmpty(str3)) {
                    str3 = str2;
                }
                getStorageWrapper().put(str2, jSONObject, str3);
                return;
            }
            String str4 = TAG;
            Log.d(str4, "There is not matching device type to remove: " + str);
        } catch (Exception e) {
            String str5 = TAG;
            Log.e(str5, "Failed to remove key: " + str2 + " for deviceType: " + str);
            throw e;
        }
    }

    public void startFetchFromCloudAsyncTask() {
        Log.d(TAG, "Starts fetching VIP Filter settings.");
        getStorageWrapper().startFetchFromCloudAsyncTask();
    }

    @VisibleForTesting
    VipFilterSettingStorageHelper(StorageWrapper storageWrapper) {
        this.storageWrapper = storageWrapper;
    }
}
