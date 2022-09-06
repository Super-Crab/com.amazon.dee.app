package com.amazon.alexa.accessory.notificationpublisher.storage;

import android.util.Base64;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.notificationpublisher.ProcessNotificationModule;
import com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.notificationsource.CommsNotificationSource;
import com.amazon.alexa.accessory.notificationpublisher.notificationsource.GenericNotificationSource;
import com.amazon.alexa.accessory.notificationpublisher.notificationsource.GroupNotificationSource;
import com.amazon.alexa.accessory.notificationpublisher.notificationsource.NotificationSource;
import com.amazon.alexa.accessory.notificationpublisher.providers.AccessoryProvider;
import com.amazon.alexa.accessory.notificationpublisher.utils.FeatureAccessChecker;
import com.amazon.alexa.accessory.notificationpublisher.utils.GroupNotificationHelper;
import com.amazon.alexa.accessory.notificationpublisher.utils.JSONHelpers;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableMap;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class SettingsStorageModule {
    public static final String AMDSS_VIP_FILTER_APPROVE_INVITATION_ON_ACCESSORY = "vipFilterApproveInvitationOnAccessory";
    public static final String AMDSS_VIP_FILTER_APP_SINGLE_NAMED_GROUP_STATUS = "vipFilterAppSingleNamedGroupStatus";
    public static final String AMDSS_VIP_FILTER_APP_STATUS = "vipFilterAppStatus";
    public static final String AMDSS_VIP_FILTER_APP_UNNAMED_GROUP_STATUS_KEY = "vipFilterAppUnnamedGroupStatus";
    public static final String AMDSS_VIP_FILTER_CONTACT_STATUS_KEY = "vipFilterContactStatus";
    public static final String AMDSS_VIP_FILTER_FORWARD_NOTIFICATION_TO_ACCESSORY_ENABLEMENT_KEY = "vipFilterForwardNotificationToAccessoryEnablement";
    public static final String AMDSS_VIP_FILTER_STATUS_ENABLEMENT_KEY = "vipFilterStatusEnablement";
    public static final String AMDSS_VIP_FILTER_SYNC_STATUS_ENABLEMENT_KEY = "vipFilterSyncStatusEnablement";
    public static final String APPROVE_INVITATION_ON_ACCESSORY = "FocusFilter.approveInvitationOnAccessory";
    private static final boolean DEFAULT_APPROVE_INVITATION_ON_ACCESSORY = false;
    private static final boolean DEFAULT_FOCUS_FILTER_ENABLED = false;
    public static final boolean DEFAULT_FORWARD_NOTIFICATION_TO_ACCESSORY = false;
    public static final boolean DEFAULT_GROUP_MESSAGES_MASTER_TOGGLE_VALUE = false;
    public static final boolean DEFAULT_LOW_DISTRACTION_MODE_VALUE = false;
    public static final boolean DEFAULT_REPLY = true;
    public static final boolean DEFAULT_REPLY_READ_BACK = true;
    public static final boolean DEFAULT_SILENT_DISTRACTION_MODE_VALUE = false;
    public static final String DEVICE_ACCOUNT_ID_KEY = "DeviceAccountId";
    public static final String DISABLED = "DISABLED";
    public static final String DWCS_APPROVE_INVITATION_ON_ACCESSORY = "focusFilterApproveInvitationOnAccessory";
    public static final String DWCS_FOCUS_FILTER_APP_SETTINGS_KEY = "focusFilterAppStatus";
    public static final String DWCS_FOCUS_FILTER_CONTACT_SETTINGS_KEY = "focusFilterContactStatus";
    public static final String DWCS_FOCUS_FILTER_ENABLED_KEY = "focusFilterStatus";
    public static final String DWCS_FOCUS_FILTER_NAMED_GROUP_SETTINGS_KEY = "focusFilterAppSingleNamedGroupStatus";
    public static final String DWCS_FOCUS_FILTER_UNNAMED_GROUP_SETTINGS_KEY = "focusFilterAppUnnamedGroupStatus";
    public static final String DWCS_FORWARD_NOTIFICATION_TO_ACCESSORY_KEY = "focusFilterForwardNotificationToAccessory";
    public static final String ENABLED = "ENABLED";
    public static final String FILTER_SETTINGS_ALIAS_KEY = "contactAlias";
    public static final String FILTER_SETTINGS_APP_ID_KEY = "appId";
    public static final String FILTER_SETTINGS_APP_NAME_KEY = "appName";
    private static final String FILTER_SETTINGS_APP_TYPE_KEY = "appType";
    public static final String FILTER_SETTINGS_CONTACT_NAME_KEY = "contactName";
    private static final String FILTER_SETTINGS_GROUP_NAME_KEY = "groupName";
    public static final String FILTER_SETTINGS_LAST_UPDATED_KEY = "updated";
    public static final String FILTER_SETTINGS_STATE_KEY = "status";
    private static final String FILTER_SETTINGS_UNNAMED_GROUP_STATE_KEY = "appUnnamedGroupStatus";
    public static final String FOCUS_FILTER_APP_GROUP_SETTINGS_KEY = "FocusFilter.appGroupStatus";
    public static final String FOCUS_FILTER_ENABLED_KEY = "FocusFilter.status";
    public static final String FOCUS_FILTER_OPENED_NOTIFICATION_NUM_KEY = "FocusFilterOpenedNotificationNum";
    public static final String FOCUS_FILTER_REJECTED_INVITATION_NUM_KEY = "FocusFilterRejectedInvitationNum";
    public static final String FOCUS_FILTER_REPLY_RECORD_PROMPT_NUM_KEY = "FocusFilterReplyRecordPromptsNum";
    public static final String FORWARD_NOTIFICATION_TO_ACCESSORY_KEY = "FocusFilter.forwardNotificationToAccessory";
    public static final String GROUP_MESSAGES_MASTER_TOGGLE_KEY = "FocusFilter.enableGroupMessages";
    public static final String GROUP_MESSAGES_USER_EDUCATION_COMPLETE_KEY = "GroupMessagesUserEducationComplete";
    public static final String LOW_DISTRACTION_MODE_KEY = "FocusFilter.lowDistractionMode";
    @VisibleForTesting
    static final String MIGRATED_ON_INITIALIZING_TOGGLE_VALUE_KEY = "migration_on_initializing_toggle_value";
    public static final String REPLY_FTU_FEATURE_EDUCATION_COMPLETE_KEY = "ReplyFtuFeatureEducationComplete";
    public static final String REPLY_KEY = "FocusFilter.enableReply";
    public static final String REPLY_READ_BACK_KEY = "FocusFilter.enableReplyReadBack";
    public static final String SILENT_DISTRACTION_MODE_KEY = "FocusFilter.silentDistractionMode";
    private static final String TAG = "SettingsStorageModule";
    public static final String VIP_FILTER_APPROVE_INVITATION_ON_ACCESSORY = "Alexa.Accessories.VipFilter.approveInvitationOnAccessory";
    public static final String VIP_FILTER_APP_SINGLE_NAMED_GROUP_STATUS = "Alexa.Accessories.VipFilter.appSingleNamedGroupStatus";
    public static final String VIP_FILTER_APP_STATUS = "Alexa.Accessories.VipFilter.appStatus";
    public static final String VIP_FILTER_APP_UNNAMED_GROUP_STATUS_KEY = "Alexa.Accessories.VipFilter.appUnnamedGroupStatus";
    public static final String VIP_FILTER_CONTACT_SETTINGS_KEY = "VipFilterContactSettings";
    public static final String VIP_FILTER_CONTACT_STATUS_KEY = "Alexa.Accessories.VipFilter.contactStatus";
    public static final String VIP_FILTER_FORWARD_NOTIFICATION_TO_ACCESSORY_ENABLEMENT_KEY = "Alexa.Accessories.VipFilter.forwardNotificationToAccessoryEnablement";
    public static final String VIP_FILTER_STATUS_ENABLEMENT_KEY = "Alexa.Accessories.VipFilter.statusEnablement";
    public static final String VIP_FILTER_SYNC_STATUS_ENABLEMENT_KEY = "Alexa.Accessories.VipFilter.syncStatusEnablement";
    private static SettingsStorageModule settingsInstance;
    private StorageWrapper storage = new StorageWrapper();
    private VipFilterSettingStorageHelper vipFilterSettingStorageHelper = VipFilterSettingStorageHelper.getInstance();
    public static final String FOCUS_FILTER_CONTACT_SETTINGS_KEY = "FocusFilter.contactStatus";
    public static final String FOCUS_FILTER_CONTACT_SETTINGS_KEYS_KEY = "FocusFilterContactSettingsKeys";
    public static final String FOCUS_FILTER_APP_SETTINGS_KEY = "FocusFilter.appStatus";
    public static final String FOCUS_FILTER_APP_SETTINGS_KEYS_KEY = "FocusFilterAppSettingsKeys";
    public static final String FOCUS_FILTER_NAMED_GROUP_SETTINGS_KEY = "FocusFilter.groupStatus";
    public static final String FOCUS_FILTER_NAMED_GROUP_SETTINGS_KEYS_KEY = "FocusFilterNamedGroupSettingKeys";
    public static final BiMap<String, String> STORAGE_KEY_CLOUD_KEY_BI_MAP = HashBiMap.create(new ImmutableMap.Builder().mo7828put(FOCUS_FILTER_CONTACT_SETTINGS_KEY, FOCUS_FILTER_CONTACT_SETTINGS_KEYS_KEY).mo7828put(FOCUS_FILTER_APP_SETTINGS_KEY, FOCUS_FILTER_APP_SETTINGS_KEYS_KEY).mo7828put(FOCUS_FILTER_NAMED_GROUP_SETTINGS_KEY, FOCUS_FILTER_NAMED_GROUP_SETTINGS_KEYS_KEY).mo7826build());
    private static final FilterSettingsState DEFAULT_GENERIC_FILTER_STATE = FilterSettingsState.NOT_DETERMINED;
    private static final FilterSettingsState DEFAULT_COMMS_APP_FILTER_STATE = FilterSettingsState.ACCEPTED;
    private static final ImmutableMap<ProcessNotificationModule.NotificationType, FilterSettingsState> DEFAULT_FILTER_STATE_APP_TYPE_MAP = new ImmutableMap.Builder().mo7828put(ProcessNotificationModule.NotificationType.COMMS, DEFAULT_COMMS_APP_FILTER_STATE).mo7828put(ProcessNotificationModule.NotificationType.OTHER, DEFAULT_GENERIC_FILTER_STATE).mo7826build();

    /* loaded from: classes.dex */
    public enum FilterSettingsState {
        ACCEPTED,
        REJECTED,
        NOT_DETERMINED
    }

    @VisibleForTesting
    SettingsStorageModule() {
    }

    public static String buildEncodedFilterSettingsKey(@NonNull String str) throws UnsupportedEncodingException {
        return Base64.encodeToString(str.getBytes("UTF-8"), 0);
    }

    private JSONObject createUnnamedGroupFilterJsonWithDefaultState() throws JSONException {
        return new JSONObject().put(FILTER_SETTINGS_UNNAMED_GROUP_STATE_KEY, DEFAULT_GENERIC_FILTER_STATE);
    }

    private boolean doesGroupFilterSettingsStateKeyExistInKeyArray(String str) throws RxBlockingCallException, JSONException {
        JSONArray allNamedGroupFilterSettingsKeys = getAllNamedGroupFilterSettingsKeys();
        if (allNamedGroupFilterSettingsKeys == null) {
            return false;
        }
        for (int i = 0; i < allNamedGroupFilterSettingsKeys.length(); i++) {
            if (allNamedGroupFilterSettingsKeys.get(i).equals(str)) {
                return true;
            }
        }
        return false;
    }

    private JSONArray getAllAppFilterSettingsKeys() throws JSONException, IllegalArgumentException, RxBlockingCallException {
        try {
            return (JSONArray) JSONArray.class.cast(this.storage.get(FOCUS_FILTER_APP_SETTINGS_KEYS_KEY));
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "getAllAppFilterSettingsKeys failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_getAllAppFilterSettingsKeys", MetricsRecorder.customAttributesForException(e));
            throw e;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static String getDWCSSettingKeyFromLocalSettingKey(String str) {
        char c;
        switch (str.hashCode()) {
            case -1323759184:
                if (str.equals(VIP_FILTER_SYNC_STATUS_ENABLEMENT_KEY)) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case -804597377:
                if (str.equals(VIP_FILTER_APP_SINGLE_NAMED_GROUP_STATUS)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case -632766421:
                if (str.equals(APPROVE_INVITATION_ON_ACCESSORY)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -502436310:
                if (str.equals(VIP_FILTER_FORWARD_NOTIFICATION_TO_ACCESSORY_ENABLEMENT_KEY)) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case -131220651:
                if (str.equals(FOCUS_FILTER_APP_SETTINGS_KEY)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 569314416:
                if (str.equals(VIP_FILTER_CONTACT_STATUS_KEY)) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 661514516:
                if (str.equals(FOCUS_FILTER_CONTACT_SETTINGS_KEY)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 788877774:
                if (str.equals(FOCUS_FILTER_APP_GROUP_SETTINGS_KEY)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1044733893:
                if (str.equals(FORWARD_NOTIFICATION_TO_ACCESSORY_KEY)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1438189747:
                if (str.equals(FOCUS_FILTER_NAMED_GROUP_SETTINGS_KEY)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1482260942:
                if (str.equals(VIP_FILTER_CONTACT_SETTINGS_KEY)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1537141937:
                if (str.equals(VIP_FILTER_APP_STATUS)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 1807911248:
                if (str.equals(FOCUS_FILTER_ENABLED_KEY)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1846186485:
                if (str.equals(VIP_FILTER_STATUS_ENABLEMENT_KEY)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 1869150543:
                if (str.equals(VIP_FILTER_APPROVE_INVITATION_ON_ACCESSORY)) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 2003602736:
                if (str.equals(VIP_FILTER_APP_UNNAMED_GROUP_STATUS_KEY)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return DWCS_FOCUS_FILTER_UNNAMED_GROUP_SETTINGS_KEY;
            case 1:
                return DWCS_FOCUS_FILTER_NAMED_GROUP_SETTINGS_KEY;
            case 2:
                return DWCS_FOCUS_FILTER_APP_SETTINGS_KEY;
            case 3:
            case 4:
                return DWCS_FOCUS_FILTER_CONTACT_SETTINGS_KEY;
            case 5:
                return DWCS_FORWARD_NOTIFICATION_TO_ACCESSORY_KEY;
            case 6:
                return DWCS_APPROVE_INVITATION_ON_ACCESSORY;
            case 7:
                return DWCS_FOCUS_FILTER_ENABLED_KEY;
            case '\b':
                return AMDSS_VIP_FILTER_APP_SINGLE_NAMED_GROUP_STATUS;
            case '\t':
                return AMDSS_VIP_FILTER_APP_STATUS;
            case '\n':
                return AMDSS_VIP_FILTER_APP_UNNAMED_GROUP_STATUS_KEY;
            case 11:
                return AMDSS_VIP_FILTER_STATUS_ENABLEMENT_KEY;
            case '\f':
                return AMDSS_VIP_FILTER_CONTACT_STATUS_KEY;
            case '\r':
                return AMDSS_VIP_FILTER_SYNC_STATUS_ENABLEMENT_KEY;
            case 14:
                return AMDSS_VIP_FILTER_APPROVE_INVITATION_ON_ACCESSORY;
            case 15:
                return AMDSS_VIP_FILTER_FORWARD_NOTIFICATION_TO_ACCESSORY_ENABLEMENT_KEY;
            default:
                return null;
        }
    }

    private FilterSettingsState getDefaultFilterStateByType(ProcessNotificationModule.NotificationType notificationType) {
        if (notificationType != null && DEFAULT_FILTER_STATE_APP_TYPE_MAP.containsKey(notificationType)) {
            return DEFAULT_FILTER_STATE_APP_TYPE_MAP.mo7740get(notificationType);
        }
        return DEFAULT_GENERIC_FILTER_STATE;
    }

    public static synchronized SettingsStorageModule getInstance() {
        SettingsStorageModule settingsStorageModule;
        synchronized (SettingsStorageModule.class) {
            if (settingsInstance == null) {
                Log.d(TAG, "getInstance - Creating new instance");
                settingsInstance = new SettingsStorageModule();
            }
            settingsStorageModule = settingsInstance;
        }
        return settingsStorageModule;
    }

    private Integer getNumOfOpenedNotificationOld() {
        try {
            Object obj = this.storage.get(FOCUS_FILTER_OPENED_NOTIFICATION_NUM_KEY);
            if (obj != null) {
                return (Integer) Integer.class.cast(obj);
            }
            return 0;
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "getNumOfOpenedNotification failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_getNumOfOpenedNotification", MetricsRecorder.customAttributesForException(e));
            return 0;
        } catch (Exception e2) {
            Log.e(TAG, "getNumOfOpenedNotification Exception:", e2);
            return 0;
        }
    }

    private Integer getNumOfRejectedInvitationOld() {
        try {
            Object obj = this.storage.get(FOCUS_FILTER_REJECTED_INVITATION_NUM_KEY);
            if (obj != null) {
                return (Integer) Integer.class.cast(obj);
            }
            return 0;
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "getNumOfRejectedInvitation failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_getNumOfRejectedInvitation", MetricsRecorder.customAttributesForException(e));
            return 0;
        } catch (Exception e2) {
            Log.e(TAG, "getNumOfRejectedInvitation Exception:", e2);
            return 0;
        }
    }

    private Object getWithDefault(@NonNull String str, Object obj) throws JSONException, RxBlockingCallException {
        try {
            Object obj2 = this.storage.get(str);
            if (obj2 != null) {
                return obj2;
            }
            Log.d(TAG, String.format(Locale.US, "getWithDefault -- %s is null, return default value %s", str, obj));
            return obj;
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "getWithDefault failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter(GeneratedOutlineSupport1.outline72("FocusFilter_rx_blocking_call_exception_getWithDefault_", str), MetricsRecorder.customAttributesForException(e));
            throw e;
        }
    }

    private boolean isMigrated() {
        try {
            Object obj = this.storage.get(MIGRATED_ON_INITIALIZING_TOGGLE_VALUE_KEY);
            if (!(obj instanceof Boolean)) {
                return false;
            }
            return ((Boolean) obj).booleanValue();
        } catch (Exception e) {
            Log.e(TAG, "isMigrated - failed", e);
            return false;
        }
    }

    private boolean isSettingTrue(String str, Boolean bool) {
        try {
            for (Accessory accessory : AccessoryProvider.getConnectedAccessoryList()) {
                if (((Boolean) getWithDefault(AccessoryProvider.getAccessoryDeviceType(accessory), str, bool)).booleanValue()) {
                    return true;
                }
            }
        } catch (Exception e) {
            Log.i(TAG, GeneratedOutlineSupport1.outline72("Failed to check true on key ", str), e);
        }
        return bool.booleanValue();
    }

    private void migrateEnablement(String str, String str2) {
        try {
            this.vipFilterSettingStorageHelper.put("A3IYPH06PH1HRA", str2, (Boolean) getWithDefault(str, false), str2);
        } catch (Exception e) {
            Log.e(TAG, String.format("migrateEnablement - failed with oldKey: %s newKey: %s", str, str2), e);
        }
    }

    private JSONArray putAllAppFilterSettingsKeys(@NonNull JSONArray jSONArray) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        try {
            String str = TAG;
            Log.i(str, "putAllAppFilterSettingsKeys size: " + jSONArray.length());
            this.storage.putLocalSync(FOCUS_FILTER_APP_SETTINGS_KEYS_KEY, jSONArray);
            return jSONArray;
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "putAllAppFilterSettingsKeys failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_putAllAppFilterSettingsKeys", MetricsRecorder.customAttributesForException(e));
            throw e;
        }
    }

    private JSONArray putAllNamedGroupFilterSettingsKeys(@NonNull JSONArray jSONArray) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        try {
            String str = TAG;
            Log.i(str, "putAllNamedGroupFilterSettingsKeys size: " + jSONArray.length());
            this.storage.putLocalSync(FOCUS_FILTER_NAMED_GROUP_SETTINGS_KEYS_KEY, jSONArray);
            return jSONArray;
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "putAllNamedGroupFilterSettingsKeys failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_putAllNamedGroupFilterSettingsKeys", MetricsRecorder.customAttributesForException(e));
            throw e;
        }
    }

    private Integer putNumOfOpenedNotificationOld(@NonNull Integer num) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        try {
            this.storage.putLocalSync(FOCUS_FILTER_OPENED_NOTIFICATION_NUM_KEY, num);
            return num;
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "putNumOfOpenedNotification failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_putNumOfOpenedNotification", MetricsRecorder.customAttributesForException(e));
            throw e;
        }
    }

    public static void recordMetricsBasedOnKey(String str, Object obj) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode != -632766421) {
            if (hashCode == 1807911248 && str.equals(FOCUS_FILTER_ENABLED_KEY)) {
                c = 0;
            }
            c = 65535;
        } else {
            if (str.equals(APPROVE_INVITATION_ON_ACCESSORY)) {
                c = 1;
            }
            c = 65535;
        }
        String str2 = c != 0 ? c != 1 ? null : MetricsConstants.APPROVE_INVITATION_PREFIX : MetricsConstants.FILTER_NOTIFICATION_PREFIX;
        if (!Strings.isNullOrEmpty(str2)) {
            MetricsRecorder.getInstance().recordCounter(GeneratedOutlineSupport1.outline72(str2, ((Boolean) obj).booleanValue() ? "_on" : "_off"));
        }
    }

    public static synchronized void releaseInstance() {
        synchronized (SettingsStorageModule.class) {
            Log.d(TAG, "releaseInstance");
            settingsInstance = null;
        }
    }

    private void syncNamedGroupFilterSettingsStateInKeyArray(@NonNull GroupNotificationSource groupNotificationSource) throws RxBlockingCallException, JSONException, UnsupportedEncodingException {
        String buildEncodedFilterSettingsKey = buildEncodedFilterSettingsKey(groupNotificationSource.getSourceId());
        if (!doesGroupFilterSettingsStateKeyExistInKeyArray(buildEncodedFilterSettingsKey)) {
            Log.i(TAG, String.format(Locale.US, "Detected inconsistency with group state and its key array, sourceId: %s encodedKey: %s", groupNotificationSource.getSourceId(), buildEncodedFilterSettingsKey));
            putNamedGroupFilterSettings(buildEncodedFilterSettingsKey, getNamedGroupFilterSettingsJson(groupNotificationSource.getSourceId()), true);
        }
    }

    public FilterSettingsState createAndPutAppFilterSettings(@NonNull JSONObject jSONObject) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        String sourceId = new GenericNotificationSource(jSONObject).getSourceId();
        JSONObject createAppFilterJsonWithDefaultState = createAppFilterJsonWithDefaultState(jSONObject);
        Log.d(TAG, String.format(Locale.US, "createAndPutAppFilterSettings - app filter: %s, sourceId: %s", createAppFilterJsonWithDefaultState, sourceId));
        putAppFilterSettings(sourceId, createAppFilterJsonWithDefaultState, true);
        return getStateFromSettingsJson(createAppFilterJsonWithDefaultState);
    }

    public FilterSettingsState createAndPutFilterSettings(@NonNull JSONObject jSONObject) throws JSONException, UnsupportedEncodingException, IllegalArgumentException, RxBlockingCallException {
        boolean isGroupMessage = GroupNotificationHelper.isGroupMessage(jSONObject);
        if (2 == GroupNotificationHelper.getGroupMessageType(jSONObject)) {
            return createAndPutUnnamedGroupFilterSettings(jSONObject);
        }
        if (isGroupMessage) {
            String sourceId = new GroupNotificationSource(jSONObject).getSourceId();
            String buildEncodedFilterSettingsKey = buildEncodedFilterSettingsKey(sourceId);
            String str = TAG;
            Log.d(str, "createAndPutFilterSettings -- encodedKey: " + buildEncodedFilterSettingsKey);
            JSONObject createNamedGroupFilterJsonWithDefaultState = createNamedGroupFilterJsonWithDefaultState(jSONObject);
            Log.d(TAG, String.format(Locale.US, "createAndPutFilterSettings - filter: %s, sourceId: %s", createNamedGroupFilterJsonWithDefaultState, sourceId));
            putNamedGroupFilterSettings(buildEncodedFilterSettingsKey, createNamedGroupFilterJsonWithDefaultState, true);
            return getStateFromSettingsJson(createNamedGroupFilterJsonWithDefaultState);
        }
        String sourceId2 = new CommsNotificationSource(jSONObject).getSourceId();
        String buildEncodedFilterSettingsKey2 = buildEncodedFilterSettingsKey(sourceId2);
        String str2 = TAG;
        Log.d(str2, "createAndPutFilterSettings -- encodedKey: " + buildEncodedFilterSettingsKey2);
        JSONObject createContactFilterJsonWithDefaultState = createContactFilterJsonWithDefaultState(jSONObject);
        Log.d(TAG, String.format(Locale.US, "createAndPutFilterSettings - filter: %s, sourceId: %s", createContactFilterJsonWithDefaultState, sourceId2));
        putContactFilterSettings(buildEncodedFilterSettingsKey2, createContactFilterJsonWithDefaultState, true);
        return getStateFromSettingsJson(createContactFilterJsonWithDefaultState);
    }

    public FilterSettingsState createAndPutUnnamedGroupFilterSettings(@NonNull JSONObject jSONObject) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        String packageId = new GroupNotificationSource(jSONObject).getPackageId();
        JSONObject createUnnamedGroupFilterJsonWithDefaultState = createUnnamedGroupFilterJsonWithDefaultState();
        Log.d(TAG, String.format(Locale.US, "createAndPutUnnamedGroupFilterSettings - unnamedGroup filter: %s, sourceId: %s", createUnnamedGroupFilterJsonWithDefaultState, packageId));
        putUnnamedGroupFilterSettings(packageId, createUnnamedGroupFilterJsonWithDefaultState);
        return getUnnamedGroupStateFromSettingsJson(createUnnamedGroupFilterJsonWithDefaultState);
    }

    public JSONObject createAppFilterJson(@NonNull JSONObject jSONObject, @NonNull FilterSettingsState filterSettingsState) throws JSONException {
        return new JSONObject().put(FILTER_SETTINGS_APP_NAME_KEY, jSONObject.getString(FILTER_SETTINGS_APP_NAME_KEY)).put(FILTER_SETTINGS_APP_TYPE_KEY, jSONObject.getString("type")).put("status", filterSettingsState.name()).put(FILTER_SETTINGS_LAST_UPDATED_KEY, System.currentTimeMillis());
    }

    public JSONObject createAppFilterJsonWithDefaultState(@NonNull JSONObject jSONObject) throws JSONException {
        ProcessNotificationModule.NotificationType notificationType;
        try {
            notificationType = ProcessNotificationModule.getNotificationType(jSONObject);
        } catch (Exception e) {
            Log.e(TAG, "createAppFilterJsonWithDefaultState - cannot get notification type", e);
            notificationType = null;
        }
        return createAppFilterJson(jSONObject, getDefaultFilterStateByType(notificationType));
    }

    public JSONObject createContactFilterJson(@NonNull JSONObject jSONObject, @NonNull FilterSettingsState filterSettingsState, @Nullable String str) throws JSONException {
        String string = jSONObject.getString(ContactProviderContract.CONTACT_PATH);
        JSONObject put = new JSONObject().put(FILTER_SETTINGS_CONTACT_NAME_KEY, string).put(FILTER_SETTINGS_APP_ID_KEY, jSONObject.getString("packageIdentifier")).put("status", filterSettingsState.name());
        if (Strings.isNullOrEmpty(str)) {
            str = string;
        }
        return put.put(FILTER_SETTINGS_ALIAS_KEY, str).put(FILTER_SETTINGS_LAST_UPDATED_KEY, System.currentTimeMillis());
    }

    public JSONObject createContactFilterJsonWithDefaultState(@NonNull JSONObject jSONObject) throws JSONException {
        return createContactFilterJson(jSONObject, DEFAULT_GENERIC_FILTER_STATE, null);
    }

    public JSONObject createNamedGroupFilterJson(@NonNull JSONObject jSONObject, @NonNull FilterSettingsState filterSettingsState) throws JSONException {
        return new JSONObject().put("groupName", jSONObject.getString("groupName")).put(FILTER_SETTINGS_APP_ID_KEY, jSONObject.getString("packageIdentifier")).put("status", filterSettingsState.name()).put(FILTER_SETTINGS_LAST_UPDATED_KEY, System.currentTimeMillis());
    }

    public JSONObject createNamedGroupFilterJsonWithDefaultState(@NonNull JSONObject jSONObject) throws JSONException {
        return createNamedGroupFilterJson(jSONObject, DEFAULT_GENERIC_FILTER_STATE);
    }

    public JSONArray getAllContactFilterSettingsKeys() throws JSONException, IllegalArgumentException, RxBlockingCallException {
        try {
            return (JSONArray) JSONArray.class.cast(this.storage.get(FOCUS_FILTER_CONTACT_SETTINGS_KEYS_KEY));
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "getAllContactFilterSettingsKeys failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_getAllContactFilterSettingsKeys", MetricsRecorder.customAttributesForException(e));
            throw e;
        }
    }

    public JSONArray getAllNamedGroupFilterSettingsKeys() throws JSONException, IllegalArgumentException, RxBlockingCallException {
        try {
            return (JSONArray) JSONArray.class.cast(this.storage.get(FOCUS_FILTER_NAMED_GROUP_SETTINGS_KEYS_KEY));
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "getAllNamedGroupFilterSettingsKeys failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_getAllNamedGroupFilterSettingsKeys", MetricsRecorder.customAttributesForException(e));
            throw e;
        }
    }

    public JSONObject getAllUpdatedAppFilterSettings(String str, JSONObject jSONObject) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        JSONArray allAppFilterSettingsKeys = getAllAppFilterSettingsKeys();
        JSONObject jSONObject2 = new JSONObject();
        if (allAppFilterSettingsKeys != null) {
            for (int i = 0; i < allAppFilterSettingsKeys.length(); i++) {
                String string = allAppFilterSettingsKeys.getString(i);
                jSONObject2.put(string, getAppFilterSettings(string));
            }
        }
        jSONObject2.put(str, jSONObject);
        return jSONObject2;
    }

    public JSONObject getAllUpdatedContactFilterSettings(String str, JSONObject jSONObject) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        JSONObject allUpdatedContactFilterSettings = getAllUpdatedContactFilterSettings();
        allUpdatedContactFilterSettings.put(str, jSONObject);
        return allUpdatedContactFilterSettings;
    }

    public JSONObject getAllUpdatedNamedGroupFilterSettings(String str, JSONObject jSONObject) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        JSONObject allUpdatedNamedGroupFilterSettings = getAllUpdatedNamedGroupFilterSettings();
        allUpdatedNamedGroupFilterSettings.put(str, jSONObject);
        return allUpdatedNamedGroupFilterSettings;
    }

    public JSONObject getAppFilterSettings(@NonNull String str) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        GeneratedOutlineSupport1.outline166("getAppFilterSettingsJson -- notificationSourceId: ", str, TAG);
        try {
            return (JSONObject) JSONObject.class.cast(this.storage.get(str));
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "getAppFilterSettings failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_getAppFilterSettings", MetricsRecorder.customAttributesForException(e));
            throw e;
        }
    }

    public FilterSettingsState getAppFilterSettingsState(@NonNull String str) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        JSONObject appFilterSettings = getAppFilterSettings(str);
        if (appFilterSettings == null) {
            GeneratedOutlineSupport1.outline165("getAppFilterSettingsState - App filter not exist ", str, TAG);
            return null;
        }
        return getStateFromSettingsJson(appFilterSettings);
    }

    public Boolean getApproveInvitationOnAccessoryWithDefault() throws JSONException, RxBlockingCallException {
        if (FeatureAccessChecker.hasOtgVipFilterAccess() && FeatureAccessChecker.hasOtgVipFilterUssSettingsAccess()) {
            return Boolean.valueOf(isSettingTrue(VIP_FILTER_APPROVE_INVITATION_ON_ACCESSORY, false));
        }
        return (Boolean) Boolean.class.cast(getWithDefault(APPROVE_INVITATION_ON_ACCESSORY, false));
    }

    public JSONObject getContactFilterSettingsJson(@NonNull String str) throws JSONException, UnsupportedEncodingException, IllegalArgumentException, RxBlockingCallException {
        String buildEncodedFilterSettingsKey = buildEncodedFilterSettingsKey(str);
        String str2 = TAG;
        Log.d(str2, "getContactFilterSettingsJson -- encodedKey: " + buildEncodedFilterSettingsKey);
        return getFilterSettingsJsonWithEncodedKey(buildEncodedFilterSettingsKey);
    }

    public FilterSettingsState getContactFilterSettingsState(@NonNull CommsNotificationSource commsNotificationSource) throws JSONException, UnsupportedEncodingException, IllegalArgumentException, RxBlockingCallException {
        return getContactFilterSettingsState(commsNotificationSource.getSourceId());
    }

    public JSONObject getFilterSettingsJsonWithEncodedKey(@NonNull String str) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        try {
            String str2 = TAG;
            Log.i(str2, "getFilterSettingsJson -- encodedKey: " + str);
            return (JSONObject) JSONObject.class.cast(this.storage.get(str));
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "getFilterSettingsJsonWithEncodedKey failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_getFilterSettingsJsonWithEncodedKey", MetricsRecorder.customAttributesForException(e));
            throw e;
        }
    }

    public Boolean getFocusFilterEnabledWithDefault() throws JSONException, RxBlockingCallException {
        if (FeatureAccessChecker.hasOtgVipFilterAccess() && FeatureAccessChecker.hasOtgVipFilterUssSettingsAccess()) {
            return Boolean.valueOf(isSettingTrue(VIP_FILTER_STATUS_ENABLEMENT_KEY, false));
        }
        return (Boolean) Boolean.class.cast(getWithDefault(FOCUS_FILTER_ENABLED_KEY, false));
    }

    public Boolean getForwardNotificationToAccessoryWithDefault() throws JSONException, RxBlockingCallException {
        if (FeatureAccessChecker.hasOtgVipFilterAccess() && FeatureAccessChecker.hasOtgVipFilterUssSettingsAccess()) {
            return Boolean.valueOf(isSettingTrue(VIP_FILTER_FORWARD_NOTIFICATION_TO_ACCESSORY_ENABLEMENT_KEY, false));
        }
        return (Boolean) getWithDefault(FORWARD_NOTIFICATION_TO_ACCESSORY_KEY, false);
    }

    public FilterSettingsState getGroupFilterSettingsState(@NonNull GroupNotificationSource groupNotificationSource) throws JSONException, UnsupportedEncodingException, IllegalArgumentException, RxBlockingCallException {
        boolean isUnnamedGroupType = groupNotificationSource.isUnnamedGroupType();
        FilterSettingsState groupFilterSettingsState = getGroupFilterSettingsState(groupNotificationSource, isUnnamedGroupType);
        if (groupFilterSettingsState != null && !isUnnamedGroupType) {
            syncNamedGroupFilterSettingsStateInKeyArray(groupNotificationSource);
        }
        return groupFilterSettingsState;
    }

    public Boolean getGroupMessagesMasterToggleSettingWithDefault() throws JSONException, RxBlockingCallException {
        return (Boolean) getWithDefault(GROUP_MESSAGES_MASTER_TOGGLE_KEY, false);
    }

    public Boolean getGroupMessagesUserEducationComplete() {
        try {
            Object obj = this.storage.get(GROUP_MESSAGES_USER_EDUCATION_COMPLETE_KEY);
            if (obj != null) {
                return (Boolean) Boolean.class.cast(obj);
            }
            return false;
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "getGroupMessagesUserEducationComplete failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_getGroupMessagesUserEducationComplete", MetricsRecorder.customAttributesForException(e));
            return false;
        } catch (Exception e2) {
            Log.e(TAG, "getGroupMessagesUserEducationComplete - Exception: ", e2);
            return false;
        }
    }

    public Boolean getLowDistractionModeSettingWithDefault() throws JSONException, RxBlockingCallException {
        return (Boolean) getWithDefault(LOW_DISTRACTION_MODE_KEY, false);
    }

    public JSONObject getNamedGroupFilterSettingsJson(@NonNull String str) throws JSONException, UnsupportedEncodingException, IllegalArgumentException, RxBlockingCallException {
        String buildEncodedFilterSettingsKey = buildEncodedFilterSettingsKey(str);
        String str2 = TAG;
        Log.d(str2, "getNamedGroupFilterSettingsJson -- encodedKey: " + buildEncodedFilterSettingsKey);
        return getFilterSettingsJsonWithEncodedKey(buildEncodedFilterSettingsKey);
    }

    public Integer getNumOfOpenedNotification() {
        if (FeatureAccessChecker.hasOtgVipFilterAccess() && FeatureAccessChecker.hasOtgVipFilterUssSettingsAccess()) {
            return getNumOfOpenedNotification(AccessoryProvider.getAccessoryDeviceType());
        }
        return getNumOfOpenedNotificationOld();
    }

    public Integer getNumOfRecordPrompts() {
        try {
            Object obj = this.storage.get(FOCUS_FILTER_REPLY_RECORD_PROMPT_NUM_KEY);
            if (obj != null) {
                return (Integer) Integer.class.cast(obj);
            }
            return 0;
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "getNumOfRecordPrompts failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_getNumOfRecordPrompts", MetricsRecorder.customAttributesForException(e));
            return 0;
        } catch (Exception e2) {
            Log.e(TAG, "getNumOfRecordPrompts Exception:", e2);
            return 0;
        }
    }

    public Integer getNumOfRejectedInvitation() {
        if (FeatureAccessChecker.hasOtgVipFilterAccess() && FeatureAccessChecker.hasOtgVipFilterUssSettingsAccess()) {
            return getNumOfRejectedInvitation(AccessoryProvider.getAccessoryDeviceType());
        }
        return getNumOfRejectedInvitationOld();
    }

    public Boolean getReplyFtuFeatureEducationComplete() {
        try {
            Object obj = this.storage.get(REPLY_FTU_FEATURE_EDUCATION_COMPLETE_KEY);
            if (obj != null) {
                return (Boolean) Boolean.class.cast(obj);
            }
            return false;
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "getReplyFtuFeatureEducationComplete failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_getReplyFtuFeatureEducationComplete", MetricsRecorder.customAttributesForException(e));
            return false;
        } catch (Exception e2) {
            Log.e(TAG, "getReplyFtuFeatureEducationComplete - Exception: ", e2);
            return false;
        }
    }

    public Boolean getReplyReadBackSettingsWithDefault() {
        try {
            return (Boolean) Boolean.class.cast(getWithDefault(REPLY_READ_BACK_KEY, true));
        } catch (RxBlockingCallException | JSONException e) {
            Log.e(TAG, "getReplyReadBackSettingsWithDefault failed:", e);
            return true;
        }
    }

    public Boolean getReplySettingsWithDefault() {
        try {
            return (Boolean) Boolean.class.cast(getWithDefault(REPLY_KEY, true));
        } catch (RxBlockingCallException | JSONException e) {
            Log.e(TAG, "getReplySettingsWithDefault failed:", e);
            return true;
        }
    }

    public Boolean getSilentDistractionModeSetting(String str) throws JSONException, RxBlockingCallException {
        return (Boolean) getWithDefault(str, SILENT_DISTRACTION_MODE_KEY, false);
    }

    public Boolean getSilentDistractionModeSettingWithDefault() throws JSONException, RxBlockingCallException {
        return (Boolean) getWithDefault(SILENT_DISTRACTION_MODE_KEY, false);
    }

    public FilterSettingsState getStateFromSettingsJson(@NonNull JSONObject jSONObject) throws JSONException {
        return FilterSettingsState.valueOf(jSONObject.getString("status"));
    }

    public ProcessNotificationModule.NotificationType getTypeFromAppSettingsJson(@NonNull JSONObject jSONObject) throws JSONException {
        return ProcessNotificationModule.NotificationType.valueOf(jSONObject.getString(FILTER_SETTINGS_APP_TYPE_KEY));
    }

    public JSONObject getUnnamedGroupFilterSettings(@NonNull String str) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        GeneratedOutlineSupport1.outline166("getUnnamedGroupFilterSettings -- notificationSourceId: ", str, TAG);
        try {
            Object obj = this.storage.get(FOCUS_FILTER_APP_GROUP_SETTINGS_KEY);
            JSONObject jSONObject = obj == null ? null : (JSONObject) JSONObject.class.cast(obj);
            if (jSONObject != null) {
                return jSONObject.optJSONObject(str);
            }
            return null;
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "getUnnamedGroupFilterSettings failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_getUnnamedGroupFilterSettings", MetricsRecorder.customAttributesForException(e));
            throw e;
        }
    }

    public JSONObject getUnnamedGroupFilterSettingsJson(@NonNull String str) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        return getUnnamedGroupFilterSettings(str);
    }

    public FilterSettingsState getUnnamedGroupStateFromSettingsJson(@NonNull JSONObject jSONObject) throws JSONException {
        return FilterSettingsState.valueOf(jSONObject.getString(FILTER_SETTINGS_UNNAMED_GROUP_STATE_KEY));
    }

    public void migrate() {
        if (FeatureAccessChecker.hasOtgVipFilterAccess() && FeatureAccessChecker.hasOtgVipFilterUssSettingsAccess()) {
            if (!isMigrated()) {
                try {
                    Log.d(TAG, "migrate - started");
                    this.storage.putLocal(MIGRATED_ON_INITIALIZING_TOGGLE_VALUE_KEY, true);
                    migrateEnablement(FORWARD_NOTIFICATION_TO_ACCESSORY_KEY, VIP_FILTER_FORWARD_NOTIFICATION_TO_ACCESSORY_ENABLEMENT_KEY);
                    migrateEnablement(FOCUS_FILTER_ENABLED_KEY, VIP_FILTER_STATUS_ENABLEMENT_KEY);
                    migrateEnablement(APPROVE_INVITATION_ON_ACCESSORY, VIP_FILTER_APPROVE_INVITATION_ON_ACCESSORY);
                    this.vipFilterSettingStorageHelper.putLocal("A3IYPH06PH1HRA", LOW_DISTRACTION_MODE_KEY, getLowDistractionModeSettingWithDefault());
                    this.vipFilterSettingStorageHelper.putLocal("A3IYPH06PH1HRA", SILENT_DISTRACTION_MODE_KEY, getSilentDistractionModeSettingWithDefault());
                    this.vipFilterSettingStorageHelper.putLocal("A3IYPH06PH1HRA", FOCUS_FILTER_OPENED_NOTIFICATION_NUM_KEY, getNumOfOpenedNotificationOld());
                    this.vipFilterSettingStorageHelper.putLocal("A3IYPH06PH1HRA", FOCUS_FILTER_REJECTED_INVITATION_NUM_KEY, getNumOfRejectedInvitationOld());
                    Log.d(TAG, "migrate - end");
                    return;
                } catch (Exception e) {
                    Log.e(TAG, "migrate - failed", e);
                    return;
                }
            }
            Log.d(TAG, "migration already done.");
        } else if (!isMigrated()) {
        } else {
            this.storage.putLocal(MIGRATED_ON_INITIALIZING_TOGGLE_VALUE_KEY, false);
        }
    }

    public JSONArray putAllContactFilterSettingsKeys(@NonNull JSONArray jSONArray) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        try {
            String str = TAG;
            Log.i(str, "putAllContactFilterSettingsKeys size: " + jSONArray.length());
            this.storage.putLocalSync(FOCUS_FILTER_CONTACT_SETTINGS_KEYS_KEY, jSONArray);
            return jSONArray;
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "putAllContactFilterSettingsKeys failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_putAllContactFilterSettingsKeys", MetricsRecorder.customAttributesForException(e));
            throw e;
        }
    }

    public void putAppFilterSettings(@NonNull String str, @NonNull JSONObject jSONObject, boolean z) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        Log.i(TAG, String.format(Locale.US, "putAppFilterSettings - sourceId: %s, doUpdateKeys: %s", str, Boolean.valueOf(z)));
        try {
            this.storage.put(str, jSONObject, FOCUS_FILTER_APP_SETTINGS_KEY);
            if (!z) {
                return;
            }
            JSONArray allAppFilterSettingsKeys = getAllAppFilterSettingsKeys();
            if (allAppFilterSettingsKeys == null) {
                putAllAppFilterSettingsKeys(new JSONArray().put(str));
                return;
            }
            String str2 = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AllAppFilterSettingsKeys size: ");
            outline107.append(allAppFilterSettingsKeys.length());
            Log.i(str2, outline107.toString());
            if (JSONHelpers.isArrayContains(allAppFilterSettingsKeys, str)) {
                return;
            }
            allAppFilterSettingsKeys.put(str);
            String str3 = TAG;
            Log.d(str3, "putAppFilterSettings - allAppFilterKeyList after append " + allAppFilterSettingsKeys);
            putAllAppFilterSettingsKeys(allAppFilterSettingsKeys);
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "putAppFilterSettings failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_putAppFilterSettings", MetricsRecorder.customAttributesForException(e));
            throw e;
        }
    }

    public void putContactFilterSettings(@NonNull CommsNotificationSource commsNotificationSource, @NonNull JSONObject jSONObject, boolean z) throws JSONException, UnsupportedEncodingException, IllegalArgumentException, RxBlockingCallException {
        putContactFilterSettings(buildEncodedFilterSettingsKey(commsNotificationSource.getSourceId()), jSONObject, z);
    }

    public Boolean putGroupMessagesUserEducationComplete(@NonNull Boolean bool) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        try {
            this.storage.putLocalSync(GROUP_MESSAGES_USER_EDUCATION_COMPLETE_KEY, bool);
            return bool;
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "putGroupMessagesUserEducationComplete - Failed with RxBlockingCallException: ", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_putGroupMessagesUserEducationComplete", MetricsRecorder.customAttributesForException(e));
            throw e;
        }
    }

    public void putNamedGroupFilterSettings(@NonNull String str, @NonNull JSONObject jSONObject, boolean z) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        Log.d(TAG, String.format(Locale.US, "putNamedGroupFilterSettings - encodedNamedGroupKey: %s, doUpdateKeys: %s", str, Boolean.valueOf(z)));
        try {
            this.storage.put(str, jSONObject, FOCUS_FILTER_NAMED_GROUP_SETTINGS_KEY);
            if (!z) {
                return;
            }
            JSONArray allNamedGroupFilterSettingsKeys = getAllNamedGroupFilterSettingsKeys();
            if (allNamedGroupFilterSettingsKeys == null) {
                putAllNamedGroupFilterSettingsKeys(new JSONArray().put(str));
                return;
            }
            String str2 = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AllNamedGroupFilterSettingsKeys size: ");
            outline107.append(allNamedGroupFilterSettingsKeys.length());
            Log.i(str2, outline107.toString());
            if (JSONHelpers.isArrayContains(allNamedGroupFilterSettingsKeys, str)) {
                return;
            }
            allNamedGroupFilterSettingsKeys.put(str);
            String str3 = TAG;
            Log.d(str3, "putNamedGroupFilterSettings - allNamedGroupFilterKeyList after append " + allNamedGroupFilterSettingsKeys);
            putAllNamedGroupFilterSettingsKeys(allNamedGroupFilterSettingsKeys);
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "putNamedGroupFilterSettings failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_putNamedGroupFilterSettings", MetricsRecorder.customAttributesForException(e));
            throw e;
        }
    }

    public Integer putNumOfOpenedNotification(@NonNull Integer num) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        if (FeatureAccessChecker.hasOtgVipFilterAccess() && FeatureAccessChecker.hasOtgVipFilterUssSettingsAccess()) {
            return putNumOfOpenedNotification(AccessoryProvider.getAccessoryDeviceType(), num);
        }
        return putNumOfOpenedNotificationOld(num);
    }

    public Integer putNumOfRecordPrompts(@NonNull Integer num) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        try {
            this.storage.putLocalSync(FOCUS_FILTER_REPLY_RECORD_PROMPT_NUM_KEY, num);
            return num;
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "putNumOfRecordPrompts failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_putNumOfRecordPrompts", MetricsRecorder.customAttributesForException(e));
            throw e;
        }
    }

    public Integer putNumOfRejectedInvitation(@NonNull Integer num) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        try {
            this.storage.putLocalSync(FOCUS_FILTER_REJECTED_INVITATION_NUM_KEY, num);
            return num;
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "putNumOfRejectedInvitation failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_putNumOfRejectedInvitation", MetricsRecorder.customAttributesForException(e));
            throw e;
        }
    }

    public Boolean putReplyFtuFeatureEducationComplete(@NonNull Boolean bool) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        try {
            this.storage.putLocalSync(REPLY_FTU_FEATURE_EDUCATION_COMPLETE_KEY, bool);
            return bool;
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "putReplyFtuFeatureEducationComplete - Failed with RxBlockingCallException: ", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_putReplyFtuFeatureEducationComplete", MetricsRecorder.customAttributesForException(e));
            throw e;
        }
    }

    public void putSilentDistractionModeValue(boolean z) {
        try {
            this.storage.putLocalSync(SILENT_DISTRACTION_MODE_KEY, Boolean.valueOf(z));
        } catch (RxBlockingCallException | JSONException unused) {
            Log.e(TAG, "Unable to add silentMode value to storage");
        }
    }

    public void putUnnamedGroupFilterSettings(@NonNull String str, @NonNull JSONObject jSONObject) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        Log.i(TAG, String.format(Locale.US, "putUnnamedGroupFilterSettings - sourceId: %s", str));
        try {
            JSONObject jSONObject2 = new JSONObject();
            Object obj = this.storage.get(FOCUS_FILTER_APP_GROUP_SETTINGS_KEY);
            if (obj != null) {
                jSONObject2 = (JSONObject) JSONObject.class.cast(obj);
            }
            jSONObject2.put(str, jSONObject);
            this.storage.put(FOCUS_FILTER_APP_GROUP_SETTINGS_KEY, jSONObject2, FOCUS_FILTER_APP_GROUP_SETTINGS_KEY);
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "putUnnamedGroupFilterSettings failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_putUnnamedGroupFilterSettings", MetricsRecorder.customAttributesForException(e));
            throw e;
        }
    }

    public boolean toggleForwardNotificationToAccessory(String str) {
        try {
            boolean z = !((Boolean) getWithDefault(str, VIP_FILTER_FORWARD_NOTIFICATION_TO_ACCESSORY_ENABLEMENT_KEY, false)).booleanValue();
            VipFilterSettingStorageHelper.getInstance().put(str, VIP_FILTER_FORWARD_NOTIFICATION_TO_ACCESSORY_ENABLEMENT_KEY, Boolean.valueOf(z), VIP_FILTER_FORWARD_NOTIFICATION_TO_ACCESSORY_ENABLEMENT_KEY);
            if ("A3IYPH06PH1HRA".equals(str)) {
                this.storage.put(FORWARD_NOTIFICATION_TO_ACCESSORY_KEY, Boolean.valueOf(z), FORWARD_NOTIFICATION_TO_ACCESSORY_KEY);
            }
            return z;
        } catch (Exception e) {
            Log.e(TAG, "Error toggling phone notification feature", e);
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOGGLE_PHONE_NOTIFICATION_FAILURE, MetricsRecorder.customAttributesForDeviceTypeAndException(str, e));
            return false;
        }
    }

    public void updateAppFilterState(@NonNull JSONObject jSONObject, FilterSettingsState filterSettingsState) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        String sourceId = new GenericNotificationSource(jSONObject).getSourceId();
        Log.i(TAG, String.format(Locale.US, "updateAppFilterState - sourceId: %s, state: %s", sourceId, filterSettingsState));
        JSONObject appFilterSettings = getAppFilterSettings(sourceId);
        if (appFilterSettings == null) {
            putAppFilterSettings(sourceId, createAppFilterJson(jSONObject, filterSettingsState), true);
            return;
        }
        appFilterSettings.put("status", filterSettingsState.name()).put(FILTER_SETTINGS_LAST_UPDATED_KEY, System.currentTimeMillis());
        putAppFilterSettings(sourceId, appFilterSettings, false);
    }

    public FilterSettingsState updateAppFilterWithNewType(@NonNull String str, @NonNull JSONObject jSONObject, @NonNull ProcessNotificationModule.NotificationType notificationType) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        return updateAppFilterWithNewTypeAndState(str, jSONObject, notificationType, getDefaultFilterStateByType(notificationType));
    }

    public FilterSettingsState updateAppFilterWithNewTypeAndState(@NonNull String str, @NonNull JSONObject jSONObject, @NonNull ProcessNotificationModule.NotificationType notificationType, @NonNull FilterSettingsState filterSettingsState) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        jSONObject.put(FILTER_SETTINGS_APP_TYPE_KEY, notificationType.name()).put("status", filterSettingsState).put(FILTER_SETTINGS_LAST_UPDATED_KEY, System.currentTimeMillis());
        putAppFilterSettings(str, jSONObject, false);
        return filterSettingsState;
    }

    public void updateContactFilterState(@NonNull JSONObject jSONObject, @NonNull FilterSettingsState filterSettingsState) throws JSONException, UnsupportedEncodingException, IllegalArgumentException, RxBlockingCallException {
        CommsNotificationSource commsNotificationSource = new CommsNotificationSource(jSONObject);
        Log.d(TAG, String.format(Locale.US, "updateContactFilterState -- sourceId %s, state %s", commsNotificationSource.getSourceId(), filterSettingsState));
        JSONObject contactFilterSettingsJson = getContactFilterSettingsJson(commsNotificationSource);
        if (contactFilterSettingsJson == null) {
            putContactFilterSettings(commsNotificationSource, createContactFilterJson(jSONObject, filterSettingsState, null), true);
            return;
        }
        contactFilterSettingsJson.put("status", filterSettingsState.name()).put(FILTER_SETTINGS_LAST_UPDATED_KEY, System.currentTimeMillis());
        putContactFilterSettings(commsNotificationSource, contactFilterSettingsJson, false);
    }

    public void updateGroupFilterState(@NonNull JSONObject jSONObject, @NonNull FilterSettingsState filterSettingsState) throws JSONException, UnsupportedEncodingException, IllegalArgumentException, RxBlockingCallException {
        GroupNotificationSource groupNotificationSource = new GroupNotificationSource(jSONObject);
        Log.d(TAG, String.format(Locale.US, "updateGroupFilterState -- sourceId %s, state %s", groupNotificationSource.getSourceId(), filterSettingsState));
        if (2 == GroupNotificationHelper.getGroupMessageType(jSONObject)) {
            JSONObject unnamedGroupFilterSettingsJson = getUnnamedGroupFilterSettingsJson(groupNotificationSource.getPackageId());
            if (unnamedGroupFilterSettingsJson == null) {
                unnamedGroupFilterSettingsJson = new JSONObject();
            }
            unnamedGroupFilterSettingsJson.put(FILTER_SETTINGS_UNNAMED_GROUP_STATE_KEY, filterSettingsState.name());
            putUnnamedGroupFilterSettings(groupNotificationSource.getPackageId(), unnamedGroupFilterSettingsJson);
            return;
        }
        JSONObject namedGroupFilterSettingsJson = getNamedGroupFilterSettingsJson(groupNotificationSource);
        String buildEncodedFilterSettingsKey = buildEncodedFilterSettingsKey(groupNotificationSource.getSourceId());
        if (namedGroupFilterSettingsJson == null) {
            putNamedGroupFilterSettings(buildEncodedFilterSettingsKey, namedGroupFilterSettingsJson, true);
            return;
        }
        namedGroupFilterSettingsJson.put("status", filterSettingsState.name()).put(FILTER_SETTINGS_LAST_UPDATED_KEY, System.currentTimeMillis());
        putNamedGroupFilterSettings(buildEncodedFilterSettingsKey, namedGroupFilterSettingsJson, false);
    }

    private FilterSettingsState getContactFilterSettingsState(@NonNull String str) throws JSONException, UnsupportedEncodingException, IllegalArgumentException, RxBlockingCallException {
        String str2 = TAG;
        Log.d(str2, "getContactFilterSettingsState -- sourceId: " + str);
        JSONObject contactFilterSettingsJson = getContactFilterSettingsJson(str);
        if (contactFilterSettingsJson == null) {
            GeneratedOutlineSupport1.outline165("getContactFilterSettingsState - Contact filter not exist ", str, TAG);
            return null;
        }
        return getStateFromSettingsJson(contactFilterSettingsJson);
    }

    public JSONObject getAllUpdatedContactFilterSettings() throws JSONException, IllegalArgumentException, RxBlockingCallException {
        JSONArray allContactFilterSettingsKeys = getAllContactFilterSettingsKeys();
        JSONObject jSONObject = new JSONObject();
        if (allContactFilterSettingsKeys != null) {
            for (int i = 0; i < allContactFilterSettingsKeys.length(); i++) {
                String string = allContactFilterSettingsKeys.getString(i);
                jSONObject.put(string, getFilterSettingsJsonWithEncodedKey(string));
            }
        }
        return jSONObject;
    }

    public JSONObject getAllUpdatedNamedGroupFilterSettings() throws JSONException, IllegalArgumentException, RxBlockingCallException {
        JSONArray allNamedGroupFilterSettingsKeys = getAllNamedGroupFilterSettingsKeys();
        JSONObject jSONObject = new JSONObject();
        if (allNamedGroupFilterSettingsKeys != null) {
            for (int i = 0; i < allNamedGroupFilterSettingsKeys.length(); i++) {
                String string = allNamedGroupFilterSettingsKeys.getString(i);
                jSONObject.put(string, getFilterSettingsJsonWithEncodedKey(string));
            }
        }
        return jSONObject;
    }

    public Boolean getLowDistractionModeSettingWithDefault(String str) throws JSONException, RxBlockingCallException {
        return (Boolean) getWithDefault(str, LOW_DISTRACTION_MODE_KEY, false);
    }

    public synchronized void putContactFilterSettings(@NonNull String str, @NonNull JSONObject jSONObject, boolean z) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        Log.d(TAG, String.format(Locale.US, "putContactFilterSettings - encodedContactKey: %s, doUpdateKeys: %s", str, Boolean.valueOf(z)));
        try {
            this.storage.put(str, jSONObject, FOCUS_FILTER_CONTACT_SETTINGS_KEY);
            if (z) {
                JSONArray allContactFilterSettingsKeys = getAllContactFilterSettingsKeys();
                if (allContactFilterSettingsKeys == null) {
                    putAllContactFilterSettingsKeys(new JSONArray().put(str));
                } else {
                    String str2 = TAG;
                    Log.i(str2, "AllContactFilterSettingsKeys size: " + allContactFilterSettingsKeys.length());
                    if (!JSONHelpers.isArrayContains(allContactFilterSettingsKeys, str)) {
                        allContactFilterSettingsKeys.put(str);
                        String str3 = TAG;
                        Log.d(str3, "putContactFilterSettings - allContactFilterKeyList after append " + allContactFilterSettingsKeys);
                        putAllContactFilterSettingsKeys(allContactFilterSettingsKeys);
                    }
                }
            }
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "putContactFilterSettings failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_putContactFilterSettings", MetricsRecorder.customAttributesForException(e));
            throw e;
        }
    }

    public void putSilentDistractionModeValue(String str, boolean z) {
        if (str == null) {
            return;
        }
        try {
            this.vipFilterSettingStorageHelper.putLocalSync(str, SILENT_DISTRACTION_MODE_KEY, Boolean.valueOf(z));
            if (!"A3IYPH06PH1HRA".equals(str)) {
                return;
            }
            putSilentDistractionModeValue(z);
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "Unable to add silentMode value to storage due to RxBlockingCallException", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_putSilentDistractionModeValue", MetricsRecorder.customAttributesForDeviceTypeAndException(str, e));
        } catch (JSONException e2) {
            Log.e(TAG, "Unable to add silentMode value to storage due to JSONException", e2);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_json_exception_putSilentDistractionModeValue", MetricsRecorder.customAttributesForDeviceTypeAndException(str, e2));
        }
    }

    private FilterSettingsState getGroupFilterSettingsState(@NonNull NotificationSource notificationSource, @NonNull boolean z) throws JSONException, UnsupportedEncodingException, IllegalArgumentException, RxBlockingCallException {
        JSONObject namedGroupFilterSettingsJson;
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("getGroupFilterSettingsState -- sourceId: ");
        outline107.append(notificationSource.getSourceId());
        Log.d(str, outline107.toString());
        if (z) {
            namedGroupFilterSettingsJson = getUnnamedGroupFilterSettingsJson(notificationSource.getPackageId());
        } else {
            namedGroupFilterSettingsJson = getNamedGroupFilterSettingsJson(notificationSource.getSourceId());
        }
        if (namedGroupFilterSettingsJson != null) {
            if (z) {
                return getUnnamedGroupStateFromSettingsJson(namedGroupFilterSettingsJson);
            }
            return getStateFromSettingsJson(namedGroupFilterSettingsJson);
        }
        String str2 = TAG;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("getGroupFilterSettingsState - Group filter not exist ");
        outline1072.append(notificationSource.getSourceId());
        Log.d(str2, outline1072.toString());
        return null;
    }

    public FilterSettingsState getAppFilterSettingsState(@NonNull GenericNotificationSource genericNotificationSource) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        return getAppFilterSettingsState(genericNotificationSource.getSourceId());
    }

    @VisibleForTesting
    JSONObject getContactFilterSettingsJson(String str, @NonNull String str2) throws JSONException, RxBlockingCallException {
        GeneratedOutlineSupport1.outline165("getContactFilterSettingsJsonByDeviceType -- encodedKey: ", str2, TAG);
        if (str == null) {
            return null;
        }
        try {
            String str3 = TAG;
            Log.i(str3, "getFilterSettingsJson -- encodedKey: " + str2);
            return ((JSONObject) this.vipFilterSettingStorageHelper.get(str, VIP_FILTER_CONTACT_STATUS_KEY)).optJSONObject(str2);
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "getFilterSettingsJsonWithEncodedKeyByDeviceType failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_getFilterSettingsJsonWithEncodedKey", MetricsRecorder.customAttributesForDeviceTypeAndException(str, e));
            throw e;
        } catch (JSONException e2) {
            Log.e(TAG, "getFilterSettingsJsonWithEncodedKeyByDeviceType failed with JSONException:", e2);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_json_exception_getFilterSettingsJsonWithEncodedKey", MetricsRecorder.customAttributesForDeviceTypeAndException(str, e2));
            throw e2;
        }
    }

    @VisibleForTesting
    JSONObject getNamedGroupFilterSettingsJson(String str, @NonNull String str2) throws RxBlockingCallException, JSONException {
        if (str == null) {
            return null;
        }
        try {
            JSONObject jSONObject = (JSONObject) this.vipFilterSettingStorageHelper.get(str, VIP_FILTER_APP_SINGLE_NAMED_GROUP_STATUS);
            if (jSONObject != null) {
                return jSONObject.optJSONObject(str2);
            }
            return null;
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "Failed to getNamedGroupFilterSettingsJsonByDeviceType due to RxBlockingCallException", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_getUnnamedGroupFilterSettings", MetricsRecorder.customAttributesForDeviceTypeAndException(str, e));
            throw e;
        } catch (JSONException e2) {
            Log.e(TAG, "Failed to getNamedGroupFilterSettingsJsonByDeviceType due to RxBlockingCallException", e2);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_json_exception_getUnnamedGroupFilterSettings", MetricsRecorder.customAttributesForDeviceTypeAndException(str, e2));
            throw e2;
        }
    }

    public Integer getNumOfOpenedNotification(String str) {
        if (str == null) {
            return 0;
        }
        try {
            Object obj = this.vipFilterSettingStorageHelper.get(str, FOCUS_FILTER_OPENED_NOTIFICATION_NUM_KEY);
            if (!"".equals(obj)) {
                return (Integer) Integer.class.cast(obj);
            }
            return 0;
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "getNumOfOpenedNotificationByDeviceType failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_getNumOfOpenedNotification", MetricsRecorder.customAttributesForDeviceTypeAndException(str, e));
            return 0;
        } catch (Exception e2) {
            Log.e(TAG, "getNumOfOpenedNotificationByDeviceType Exception:", e2);
            return 0;
        }
    }

    public Integer getNumOfRejectedInvitation(String str) {
        if (str == null) {
            return 0;
        }
        try {
            Object obj = this.vipFilterSettingStorageHelper.get(str, FOCUS_FILTER_REJECTED_INVITATION_NUM_KEY);
            if (!"".equals(obj)) {
                return (Integer) Integer.class.cast(obj);
            }
            return 0;
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "getNumOfRejectedInvitationByDeviceType failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_getNumOfRejectedInvitation", MetricsRecorder.customAttributesForDeviceTypeAndException(str, e));
            return 0;
        } catch (Exception e2) {
            Log.e(TAG, "getNumOfRejectedInvitationByDeviceType Exception:", e2);
            return 0;
        }
    }

    public Integer putNumOfOpenedNotification(String str, @NonNull Integer num) throws RxBlockingCallException, JSONException {
        if (str == null) {
            return num;
        }
        try {
            this.vipFilterSettingStorageHelper.putLocalSync(str, FOCUS_FILTER_OPENED_NOTIFICATION_NUM_KEY, num);
            if ("A3IYPH06PH1HRA".equals(str)) {
                putNumOfOpenedNotificationOld(num);
            }
            return num;
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "putNumOfOpenedNotification failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_putNumOfOpenedNotification", MetricsRecorder.customAttributesForDeviceTypeAndException(str, e));
            throw e;
        } catch (JSONException e2) {
            Log.e(TAG, "putNumOfOpenedNotification failed with JSONException:", e2);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_json_exception_putNumOfOpenedNotification", MetricsRecorder.customAttributesForDeviceTypeAndException(str, e2));
            throw e2;
        }
    }

    public Integer putNumOfRejectedInvitation(String str, @NonNull Integer num) throws RxBlockingCallException, JSONException {
        if (str == null) {
            return num;
        }
        try {
            this.vipFilterSettingStorageHelper.putLocalSync(str, FOCUS_FILTER_REJECTED_INVITATION_NUM_KEY, num);
            if ("A3IYPH06PH1HRA".equals(str)) {
                putNumOfRejectedInvitation(num);
            }
            return num;
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "putNumOfRejectedInvitation failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_putNumOfRejectedInvitation", MetricsRecorder.customAttributesForDeviceTypeAndException(str, e));
            throw e;
        } catch (JSONException e2) {
            throw e2;
        }
    }

    @VisibleForTesting
    JSONObject getAppFilterSettings(String str, @NonNull String str2) throws JSONException, RxBlockingCallException {
        if (str == null) {
            return null;
        }
        try {
            return ((JSONObject) this.vipFilterSettingStorageHelper.get(str, VIP_FILTER_APP_STATUS)).optJSONObject(str2);
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "getAppFilterSettings by deviceType failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_getAppFilterSettings", MetricsRecorder.customAttributesForDeviceTypeAndException(str, e));
            throw e;
        } catch (JSONException e2) {
            Log.e(TAG, "getAppFilterSettings by deviceType failed with JSONException:", e2);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_json_exception_getAppFilterSettings", MetricsRecorder.customAttributesForDeviceTypeAndException(str, e2));
            throw e2;
        }
    }

    @VisibleForTesting
    Object getWithDefault(String str, @NonNull String str2, Object obj) throws JSONException, RxBlockingCallException {
        if (str == null) {
            Log.i(TAG, "DeviceType is null, so returns default value");
            return obj;
        }
        try {
            Object obj2 = this.vipFilterSettingStorageHelper.get(str, str2);
            if (!"".equals(obj2) && !VipFilterSettingStorageHelper.EMPTY_JSON_OBJECT.equals(obj2)) {
                return obj2;
            }
            Log.d(TAG, String.format(Locale.US, "getWithDefault -- value of %s is null, return default value %s", str2, obj));
            return obj;
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "getWithDefault by deviceType failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter(GeneratedOutlineSupport1.outline72("FocusFilter_rx_blocking_call_exception_getWithDefault_", str2), MetricsRecorder.customAttributesForDeviceTypeAndException(str, e));
            throw e;
        } catch (JSONException e2) {
            Log.e(TAG, "getWithDefault by deviceType failed with JSONException:", e2);
            MetricsRecorder.getInstance().recordCounter(GeneratedOutlineSupport1.outline72("FocusFilter_json_exception_getWithDefault_", str2), MetricsRecorder.customAttributesForDeviceTypeAndException(str, e2));
            throw e2;
        }
    }

    @VisibleForTesting
    JSONObject getUnnamedGroupFilterSettings(String str, @NonNull String str2) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        GeneratedOutlineSupport1.outline166("getUnnamedGroupFilterSettingsByDeviceType -- notificationSourceId: ", str2, TAG);
        if (str == null) {
            return null;
        }
        try {
            return ((JSONObject) this.vipFilterSettingStorageHelper.get(str, VIP_FILTER_APP_UNNAMED_GROUP_STATUS_KEY)).optJSONObject(str2);
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "getUnnamedGroupFilterSettingsByDeviceType failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_getUnnamedGroupFilterSettings", MetricsRecorder.customAttributesForDeviceTypeAndException(str, e));
            throw e;
        } catch (JSONException e2) {
            Log.e(TAG, "getUnnamedGroupFilterSettingsByDeviceType failed with JSONException:", e2);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_json_exception_getUnnamedGroupFilterSettingsByDeviceType", MetricsRecorder.customAttributesForDeviceTypeAndException(str, e2));
            throw e2;
        }
    }

    @VisibleForTesting
    void putUnnamedGroupFilterSettings(String str, @NonNull String str2, @NonNull JSONObject jSONObject) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        Log.i(TAG, "putUnnamedGroupFilterSettings by deviceType");
        if (str == null) {
            return;
        }
        try {
            JSONObject jSONObject2 = (JSONObject) this.vipFilterSettingStorageHelper.get(str, VIP_FILTER_APP_UNNAMED_GROUP_STATUS_KEY);
            jSONObject2.put(str2, jSONObject);
            this.vipFilterSettingStorageHelper.put(str, VIP_FILTER_APP_UNNAMED_GROUP_STATUS_KEY, jSONObject2, VIP_FILTER_APP_UNNAMED_GROUP_STATUS_KEY);
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "putUnnamedGroupFilterSettings failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_putUnnamedGroupFilterSettings", MetricsRecorder.customAttributesForDeviceTypeAndException(str, e));
            throw e;
        } catch (JSONException e2) {
            throw e2;
        }
    }

    public JSONObject getNamedGroupFilterSettingsJson(@NonNull GroupNotificationSource groupNotificationSource) throws JSONException, UnsupportedEncodingException, IllegalArgumentException, RxBlockingCallException {
        return getNamedGroupFilterSettingsJson(groupNotificationSource.getSourceId());
    }

    public JSONObject getContactFilterSettingsJson(@NonNull CommsNotificationSource commsNotificationSource) throws JSONException, UnsupportedEncodingException, IllegalArgumentException, RxBlockingCallException {
        return getContactFilterSettingsJson(commsNotificationSource.getSourceId());
    }

    @VisibleForTesting
    void putAppFilterSettings(String str, @NonNull String str2, @NonNull JSONObject jSONObject) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        Log.i(TAG, String.format(Locale.US, "putAppFilterSettings by deviceType - sourceId: %s", str2));
        if (str == null) {
            return;
        }
        try {
            JSONObject jSONObject2 = (JSONObject) this.vipFilterSettingStorageHelper.get(str, VIP_FILTER_APP_STATUS);
            jSONObject2.put(str2, jSONObject);
            this.vipFilterSettingStorageHelper.put(str, VIP_FILTER_APP_STATUS, jSONObject2, VIP_FILTER_APP_STATUS);
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "putAppFilterSettings by deviceType failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_putAppFilterSettings", MetricsRecorder.customAttributesForDeviceTypeAndException(str, e));
            throw e;
        }
    }

    @VisibleForTesting
    void putNamedGroupFilterSettings(String str, @NonNull String str2, @NonNull JSONObject jSONObject) throws JSONException, IllegalArgumentException, RxBlockingCallException {
        if (str == null) {
            return;
        }
        try {
            JSONObject jSONObject2 = (JSONObject) this.vipFilterSettingStorageHelper.get(str, VIP_FILTER_APP_SINGLE_NAMED_GROUP_STATUS);
            jSONObject2.put(str2, jSONObject);
            this.vipFilterSettingStorageHelper.put(str, VIP_FILTER_APP_SINGLE_NAMED_GROUP_STATUS, jSONObject2, VIP_FILTER_APP_SINGLE_NAMED_GROUP_STATUS);
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "putNamedGroupFilterSettings by deviceType failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_putNamedGroupFilterSettings", MetricsRecorder.customAttributesForDeviceTypeAndException(str, e));
            throw e;
        } catch (JSONException e2) {
            Log.e(TAG, "putNamedGroupFilterSettings by deviceType failed with JSONException:", e2);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_json_exception_putNamedGroupFilterSettings", MetricsRecorder.customAttributesForDeviceTypeAndException(str, e2));
            throw e2;
        }
    }

    @VisibleForTesting
    void putContactFilterSettings(String str, @NonNull String str2, @NonNull JSONObject jSONObject) throws JSONException, RxBlockingCallException {
        if (str == null) {
            return;
        }
        try {
            JSONObject jSONObject2 = (JSONObject) this.vipFilterSettingStorageHelper.get(str, VIP_FILTER_CONTACT_STATUS_KEY);
            jSONObject2.put(str2, jSONObject);
            this.vipFilterSettingStorageHelper.put(str, VIP_FILTER_CONTACT_STATUS_KEY, jSONObject2, VIP_FILTER_CONTACT_STATUS_KEY);
        } catch (RxBlockingCallException e) {
            Log.e(TAG, "putContactFilterSettings by device type failed with RxBlockingCallException:", e);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_putContactFilterSettings", MetricsRecorder.customAttributesForDeviceTypeAndException(str, e));
            throw e;
        } catch (JSONException e2) {
            Log.e(TAG, "putContactFilterSettings by device type failed with JSONException:", e2);
            MetricsRecorder.getInstance().recordCounter("FocusFilter_json_exception_putContactFilterSettings", MetricsRecorder.customAttributesForDeviceTypeAndException(str, e2));
            throw e2;
        }
    }
}
