package com.amazon.deecomms.nativemodules;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.util.CookieUtils;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import com.amazon.deecomms.core.CloudDriveModule;
import com.amazon.deecomms.core.CommsComponent;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.exceptions.InvalidCommsIdentityException;
import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import com.amazon.deecomms.util.DeviceInfo;
import com.amazon.identity.auth.device.api.DeviceDataKeys;
import com.amazon.identity.auth.device.api.DeviceDataStoreException;
import com.amazon.tarazed.core.logging.TarazedLogFormatter;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes12.dex */
public class LocalKeyValueStore extends ReactContextBaseJavaModule {
    private static final String ACMS_ENDPOINT = "serviceEndpointACMS";
    private static final String ALLOW_CONTACTS_FROM_THIS_DEVICE_KEY = "AllowContactsFromThisDevice";
    private static final String AMZN_COOKIE = "amznCookie";
    private static final String AMZN_SESSION_ID = "amznSessionId";
    private static final String AUTOPROVISIONED_KEY = "autoprovisioned";
    private static final String CDS_APPLICATION_ID = "cdsApplicationId";
    private static final String CDS_COOKIE = "CDSCookie";
    private static final String CDS_ENDPOINT = "serviceEndpointCDS";
    private static final String CDS_SESSION_ID = "CDSSessionId";
    private static final String CLIENT_ID = "clientId";
    private static final String COBO_ALERT_SHOWN = "comms_cobo_onetime_popup";
    private static final String COMMS_ID_KEY = "commsId";
    private static final String CONTACTS_INITIAL_DOWNLOADED = "contactsInitialLoaded";
    private static final String CONTACT_ACCESS_TYPE = "contactAccessType";
    private static final String CONTACT_SYNC_PREF = "contactSyncPreference";
    private static final String COR_KEY = "COR";
    private static final String DEVICE_ID_KEY = "com.amazon.alexa.comms.deviceID";
    private static final String DEVICE_TYPE = "com.amazon.alexa.comms.deviceType";
    private static final String DIRECTED_ID = "directedId";
    private static final String DYNAMIC_UI_ENDPOINT = "serviceEndpointDynamicUI";
    private static final String ENABLE_ANNOUNCEMENT_PUSH_NOTIFICATION = "enableAnnouncementPushNotification";
    private static final String HAS_SET_MASTER_DEVICE = "hasSetMasterDevice";
    private static final String HAS_SHOWN_SHARING_FTUE = "show_sharing_ftue";
    private static final String HOMEGROUP_COMMS_ID_KEY = "homeGroupCommsId";
    private static final String IS_IN_DRIVE_MODE = "isInDriveMode";
    private static final String LAST_SYNCED_ETAG_VALUE = "lastSyncedEntityTag";
    private static final String LAST_UPDATED_TIME_FOR_CONVERSATIONS = "lastUpdateTimeForConversations";
    private static final String LOCALE = "locale";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, LocalKeyValueStore.class);
    private static final String MEDIA_AUTH_TOKEN = "mediaAuthToken";
    private static final String MEDIA_AUTH_TOKEN_EXPIRATION = "mediaAuthTokenExpiration";
    private static final String PFM_KEY = "PFM";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String PROVISIONED_KEY = "provisioned";
    private static final String PROVISION_STATUS = "provisionStatus";
    private static final String RELATIONSHIPS = "relationships";
    private static final String SERIAL_NUMBER = "com.amazon.alexa.comms.serialNumber";
    private static final String SHOULD_SHOW_DROP_IN_DISCOVERY = "show_drop_in_announcement";
    private static final String USER_AGENT = "userAgent";
    private final CommsComponent mCommsComponent;
    private final CommsIdentityManager mCommsIdentityManager;
    private final CommsInternal mCommsInternal;
    private final DeviceInfo mDeviceInfo;

    public LocalKeyValueStore(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, new DeviceInfo(), CommsDaggerWrapper.getComponent());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private Object getValueOf(String str) throws DeviceDataStoreException, IllegalArgumentException {
        char c;
        switch (str.hashCode()) {
            case -2118174745:
                if (str.equals(LAST_SYNCED_ETAG_VALUE)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -1797209036:
                if (str.equals(PROVISIONED_KEY)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1534644077:
                if (str.equals(LAST_UPDATED_TIME_FOR_CONVERSATIONS)) {
                    c = 21;
                    break;
                }
                c = 65535;
                break;
            case -1327482820:
                if (str.equals(MEDIA_AUTH_TOKEN_EXPIRATION)) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case -1240562259:
                if (str.equals(MEDIA_AUTH_TOKEN)) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case -1192969641:
                if (str.equals("phoneNumber")) {
                    c = '%';
                    break;
                }
                c = 65535;
                break;
            case -1116844320:
                if (str.equals(HAS_SET_MASTER_DEVICE)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case -1097462182:
                if (str.equals("locale")) {
                    c = 25;
                    break;
                }
                c = 65535;
                break;
            case -1089008775:
                if (str.equals(CDS_APPLICATION_ID)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1068949728:
                if (str.equals(DEVICE_ID_KEY)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -763122657:
                if (str.equals(DEVICE_TYPE)) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case -742646058:
                if (str.equals(CONTACTS_INITIAL_DOWNLOADED)) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case -736911065:
                if (str.equals(PROVISION_STATUS)) {
                    c = '!';
                    break;
                }
                c = 65535;
                break;
            case -616424674:
                if (str.equals(IS_IN_DRIVE_MODE)) {
                    c = '$';
                    break;
                }
                c = 65535;
                break;
            case -489642797:
                if (str.equals("show_drop_in_announcement")) {
                    c = 22;
                    break;
                }
                c = 65535;
                break;
            case -370793629:
                if (str.equals("show_sharing_ftue")) {
                    c = '\"';
                    break;
                }
                c = 65535;
                break;
            case -126828802:
                if (str.equals(CONTACT_ACCESS_TYPE)) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case -92500046:
                if (str.equals(ACMS_ENDPOINT)) {
                    c = Chars.SPACE;
                    break;
                }
                c = 65535;
                break;
            case -36508983:
                if (str.equals(DYNAMIC_UI_ENDPOINT)) {
                    c = 26;
                    break;
                }
                c = 65535;
                break;
            case 66918:
                if (str.equals("COR")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 79127:
                if (str.equals("PFM")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 84092182:
                if (str.equals(CDS_COOKIE)) {
                    c = TarazedLogFormatter.FILE_SEPARATOR;
                    break;
                }
                c = 65535;
                break;
            case 135565416:
                if (str.equals(CDS_ENDPOINT)) {
                    c = 31;
                    break;
                }
                c = 65535;
                break;
            case 203634018:
                if (str.equals(HOMEGROUP_COMMS_ID_KEY)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 224323971:
                if (str.equals("directedId")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 311430650:
                if (str.equals(USER_AGENT)) {
                    c = 24;
                    break;
                }
                c = 65535;
                break;
            case 472535355:
                if (str.equals(RELATIONSHIPS)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 780208593:
                if (str.equals(AMZN_SESSION_ID)) {
                    c = 29;
                    break;
                }
                c = 65535;
                break;
            case 790003631:
                if (str.equals("enableAnnouncementPushNotification")) {
                    c = '#';
                    break;
                }
                c = 65535;
                break;
            case 908408390:
                if (str.equals("clientId")) {
                    c = 23;
                    break;
                }
                c = 65535;
                break;
            case 950410850:
                if (str.equals("commsId")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1010869782:
                if (str.equals("contactSyncPreference")) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 1153880869:
                if (str.equals(AUTOPROVISIONED_KEY)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1606012511:
                if (str.equals(CDS_SESSION_ID)) {
                    c = 30;
                    break;
                }
                c = 65535;
                break;
            case 1708715748:
                if (str.equals(AMZN_COOKIE)) {
                    c = 27;
                    break;
                }
                c = 65535;
                break;
            case 1895078810:
                if (str.equals(ALLOW_CONTACTS_FROM_THIS_DEVICE_KEY)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 2057208844:
                if (str.equals(SERIAL_NUMBER)) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case 2119977970:
                if (str.equals(COBO_ALERT_SHOWN)) {
                    c = 17;
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
                return CloudDriveModule.BASE_64_APP_ID;
            case 1:
                String commsId = this.mCommsIdentityManager.getCommsId("LocalKeyValueStore", false);
                if (TextUtils.isEmpty(commsId)) {
                    LOG.i("Null comms id in key value store");
                    MetricsHelper.recordOperationalMetricWithSource(MetricKeys.COMMSID_NULL, LocalKeyValueStore.class.getSimpleName());
                }
                return commsId;
            case 2:
                String homeGroupId = this.mCommsIdentityManager.getHomeGroupId("LocalKeyValueStore", false);
                if (TextUtils.isEmpty(homeGroupId)) {
                    LOG.i("Null homegroup id in key value store");
                    MetricsHelper.recordOperationalMetricWithSource(MetricKeys.HOMEGROUP_NULL, LocalKeyValueStore.class.getSimpleName());
                }
                return homeGroupId;
            case 3:
                return this.mCommsIdentityManager.getDirectedId("LocalKeyValueStore", false);
            case 4:
                return this.mDeviceInfo.getUniqueDeviceId(getReactApplicationContext());
            case 5:
                return Boolean.valueOf(!Utils.isNullOrEmpty(this.mCommsIdentityManager.getCommsId("LocalKeyValueStore", false)));
            case 6:
                return Boolean.valueOf(CommsProvisionStatus.AUTO_PROVISIONED.equals(this.mCommsIdentityManager.getProvisionStatus(false, "LocalKeyValueStore", false)));
            case 7:
                return Boolean.valueOf(Utils.getBooleanPreferenceFromSharedPrefs(getReactApplicationContext(), Constants.SHOULD_SUPPORT_CONTACTS_ON_DEVICES, true));
            case '\b':
                return Boolean.valueOf(Utils.getBooleanPreferenceFromSharedPrefs(getReactApplicationContext(), HAS_SET_MASTER_DEVICE, false));
            case '\t':
                return Utils.getStringPreferenceFromSharedPrefs(getReactApplicationContext(), LAST_SYNCED_ETAG_VALUE, null);
            case '\n':
                return Utils.getStringPreferenceFromSharedPrefs(getReactApplicationContext(), RELATIONSHIPS, null);
            case 11:
                return this.mCommsIdentityManager.getPreferredMarketplace(false);
            case '\f':
                return this.mCommsIdentityManager.getCountryOfResidence();
            case '\r':
            case 14:
                return Utils.getStringPreferenceFromSharedPrefs(getReactApplicationContext(), str, null);
            case 15:
                return Utils.getStringPreferenceFromSharedPrefs(getReactApplicationContext(), "contactSyncPreference", null);
            case 16:
                return Utils.getStringPreferenceFromSharedPrefs(getReactApplicationContext(), CONTACT_ACCESS_TYPE, null);
            case 17:
                return Boolean.valueOf(Utils.getBooleanPreferenceFromSharedPrefs(getReactApplicationContext(), Constants.SHARED_PREF_FIRST_COBO_CALL_WARNING_SHOWN, false));
            case 18:
                return Boolean.valueOf(Utils.getBooleanPreferenceFromSharedPrefs(getReactApplicationContext(), Constants.INITIAL_CONTACTS_DOWNLOAD_SUCCESS, false));
            case 19:
                return Utils.getValueFromDataStore(getReactApplicationContext(), DeviceDataKeys.KEY_DEVICE_SERIAL_NUMBER);
            case 20:
                return Utils.getValueFromDataStore(getReactApplicationContext(), "DeviceType");
            case 21:
                return Utils.getStringPreferenceFromSharedPrefs(getReactApplicationContext(), LAST_UPDATED_TIME_FOR_CONVERSATIONS, null);
            case 22:
                return Boolean.valueOf(Utils.getBooleanPreferenceFromSharedPrefs(getReactApplicationContext(), "show_drop_in_announcement", true));
            case 23:
                return this.mCommsComponent.getCommsInternal().getClientID();
            case 24:
                return Utils.getUserAgent();
            case 25:
                return this.mCommsComponent.getCommsInternal().getLocale();
            case 26:
                return this.mCommsComponent.getAppUrl().getDynamicUIServiceURL();
            case 27:
            case 28:
                return CookieUtils.getSendableCookies(this.mCommsIdentityManager.getDirectedId("LocalKeyValueStore", false), false);
            case 29:
            case 30:
                return CookieUtils.extractSessionIdFromCookies(CookieUtils.getCookiesForDirectedId(this.mCommsIdentityManager.getDirectedId("LocalKeyValueStore", false), false));
            case 31:
                return this.mCommsComponent.getFileTransmitter().getCDSGroupEndpoint();
            case ' ':
                return this.mCommsComponent.getAppUrl().getACMSServiceURL();
            case '!':
                CommsProvisionStatus provisionStatus = this.mCommsIdentityManager.getProvisionStatus(false, "LocalKeyValueStore", false);
                if (provisionStatus == null) {
                    LOG.i("Null provision status in key value store");
                    provisionStatus = CommsProvisionStatus.UNKNOWN;
                }
                if (provisionStatus == CommsProvisionStatus.UNKNOWN) {
                    LOG.i("Unknown provisioning status in key value store");
                    MetricsHelper.recordOperationalMetricWithSource(MetricKeys.PROVISION_NULL, "LocalKeyValueStore");
                }
                return provisionStatus.name();
            case '\"':
                return Boolean.valueOf(Utils.getBooleanPreferenceFromSharedPrefs(getReactApplicationContext(), "show_sharing_ftue", false));
            case '#':
                return Boolean.valueOf(Utils.getBooleanPreferenceFromSharedPrefs(getReactApplicationContext(), "enableAnnouncementPushNotification", true));
            case '$':
                return Boolean.valueOf(this.mCommsComponent.getDriveModeSharedPreferencesUseCase().isInDriveMode());
            case '%':
                return this.mCommsIdentityManager.getPhoneNumber();
            default:
                throw new IllegalArgumentException(GeneratedOutlineSupport.outline0("Key not found: ", str));
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "CommsLocalKeyValueStore";
    }

    @ReactMethod
    public void getValue(String str, Promise promise) {
        try {
            promise.resolve(getValueOf(str));
        } catch (Exception e) {
            LOG.e(e.getMessage());
            promise.reject(e);
        }
    }

    @ReactMethod
    public void getValues(@NonNull ReadableArray readableArray, @NonNull Promise promise) {
        HashMap hashMap = new HashMap(readableArray.size());
        Iterator<Object> it2 = readableArray.toArrayList().iterator();
        while (it2.hasNext()) {
            Object next = it2.next();
            if (!(next instanceof String)) {
                CommsLogger commsLogger = LOG;
                commsLogger.e("Invalid key. Key is not a string: " + next);
                promise.reject((String) null, "Invalid key. Key is not a string: " + next);
                return;
            }
            String str = (String) next;
            try {
                hashMap.put(str, getValueOf(str));
            } catch (Exception e) {
                LOG.e(e.getMessage());
                promise.reject(e);
                return;
            }
        }
        promise.resolve(ReactBridgeSerializer.toWritableMap(hashMap));
    }

    @ReactMethod
    public void setBooleanValue(String str, boolean z, Promise promise) {
        if (HAS_SET_MASTER_DEVICE.equals(str)) {
            Utils.writeBooleanPreferenceToSharedPrefs(getReactApplicationContext(), str, z);
            promise.resolve(null);
        } else if (COBO_ALERT_SHOWN.equals(str)) {
            Utils.writeBooleanPreferenceToSharedPrefs(getReactApplicationContext(), Constants.SHARED_PREF_FIRST_COBO_CALL_WARNING_SHOWN, z);
            promise.resolve(null);
        } else if (CONTACTS_INITIAL_DOWNLOADED.equals(str)) {
            Utils.writeBooleanPreferenceToSharedPrefs(getReactApplicationContext(), Constants.INITIAL_CONTACTS_DOWNLOAD_SUCCESS, z);
            promise.resolve(null);
        } else if ("show_drop_in_announcement".equals(str)) {
            Utils.writeBooleanPreferenceToSharedPrefs(getReactApplicationContext(), "show_drop_in_announcement", z);
            promise.resolve(null);
        } else if ("show_sharing_ftue".equals(str)) {
            Utils.writeBooleanPreferenceToSharedPrefs(getReactApplicationContext(), "show_sharing_ftue", z);
            promise.resolve(null);
        } else if ("enableAnnouncementPushNotification".equals(str)) {
            Utils.writeBooleanPreferenceToSharedPrefs(getReactApplicationContext(), "enableAnnouncementPushNotification", z);
            promise.resolve(null);
        } else {
            promise.reject((String) null, String.format("Unsupported key name: '%s'.", str));
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @ReactMethod
    public void setStringValue(String str, String str2, Promise promise) {
        char c;
        switch (str.hashCode()) {
            case -2118174745:
                if (str.equals(LAST_SYNCED_ETAG_VALUE)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -1534644077:
                if (str.equals(LAST_UPDATED_TIME_FOR_CONVERSATIONS)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1327482820:
                if (str.equals(MEDIA_AUTH_TOKEN_EXPIRATION)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1240562259:
                if (str.equals(MEDIA_AUTH_TOKEN)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -736911065:
                if (str.equals(PROVISION_STATUS)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -126828802:
                if (str.equals(CONTACT_ACCESS_TYPE)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 472535355:
                if (str.equals(RELATIONSHIPS)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1010869782:
                if (str.equals("contactSyncPreference")) {
                    c = 4;
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
            case 1:
            case 2:
            case 3:
                Utils.writeStringPreferenceToSharedPrefs(getReactApplicationContext(), str, str2);
                promise.resolve(null);
                return;
            case 4:
                Utils.writeStringPreferenceToSharedPrefs(getReactApplicationContext(), str, str2);
                promise.resolve(null);
                return;
            case 5:
                Utils.writeStringPreferenceToSharedPrefs(getReactApplicationContext(), str, str2);
                promise.resolve(null);
                return;
            case 6:
                Utils.writeStringPreferenceToSharedPrefs(getReactApplicationContext(), str, str2);
                promise.resolve(null);
                return;
            case 7:
                try {
                    this.mCommsIdentityManager.setProvisionStatus(CommsProvisionStatus.valueOf(str2), "localKeyValueStore", false, false);
                    this.mCommsIdentityManager.onCurrentUserUpdated();
                } catch (InvalidCommsIdentityException e) {
                    promise.reject("Failed to update identity after setting the provisioning status", e);
                }
                Utils.writeStringPreferenceToSharedPrefs(getReactApplicationContext(), str, str2);
                promise.resolve(null);
                return;
            default:
                promise.reject((String) null, String.format("Unsupported key name: '%s'.", str));
                return;
        }
    }

    public LocalKeyValueStore(ReactApplicationContext reactApplicationContext, DeviceInfo deviceInfo, CommsComponent commsComponent) {
        super(reactApplicationContext);
        this.mDeviceInfo = deviceInfo;
        this.mCommsComponent = commsComponent;
        this.mCommsIdentityManager = commsComponent.getCommsIdentityManager();
        this.mCommsInternal = commsComponent.getCommsInternal();
    }
}
