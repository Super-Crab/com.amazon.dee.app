package com.amazon.deecomms.common.network.acmsrecipes;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.clouddrive.cdasdk.aps.account.FeatureState;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.ndt.model.DevicePermissionsPreference;
import com.amazon.deecomms.ndt.model.GetDeviceCommsPreferencesResponse;
import com.amazon.deecomms.notifications.PushNotificationManager;
import com.amazon.deecomms.util.ThreadUtils;
import com.amazon.identity.auth.device.api.DeviceDataKeys;
import com.amazon.identity.auth.device.api.DeviceDataStoreException;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.text.MessageFormat;
/* loaded from: classes12.dex */
public class GetDeviceCommsPreferences {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, GetDeviceCommsPreferences.class);
    private final ACMSClient client;
    private String requestId;

    public GetDeviceCommsPreferences() {
        this.client = new ACMSClient(MetricKeys.OP_GET_DEVICE_COMMS_PREFERENCES);
    }

    @MainThread
    public static void cleanUpCommsIfDisabled(boolean z, boolean z2, @NonNull ApplicationManager applicationManager) {
        if (!z || z2) {
            return;
        }
        boolean removeCommsRoutes = applicationManager.removeCommsRoutes();
        if (applicationManager.isCurrentlyOnCommsScreen()) {
            LOG.i("Comms disabled. Navigating from comms screen to home.");
            applicationManager.navigateHome();
        } else if (!removeCommsRoutes) {
        } else {
            LOG.i("Comms disabled. Adding home to backstack if empty.");
            applicationManager.addHomeToBackStackIfEmpty();
        }
    }

    public static synchronized boolean isDeviceCommunicationsEnabled(@NonNull Context context, @NonNull CapabilitiesManager capabilitiesManager) {
        synchronized (GetDeviceCommsPreferences.class) {
            if (Utils.isFireOS() && capabilitiesManager.isFireOSCommsGatingEnabled()) {
                boolean booleanPreferenceFromSharedPrefs = Utils.getBooleanPreferenceFromSharedPrefs(context, Constants.SHARED_PREF_KEY_COMMS_FIRE_TAB_ENABLED, false);
                CommsLogger commsLogger = LOG;
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("Communications on this device is ");
                outline1.append(booleanPreferenceFromSharedPrefs ? "enabled" : FeatureState.DISABLED);
                commsLogger.i(outline1.toString());
                return booleanPreferenceFromSharedPrefs;
            }
            LOG.i("Communications on this device is enabled.");
            return true;
        }
    }

    private boolean isPrefEnabled(@NonNull GetDeviceCommsPreferencesResponse getDeviceCommsPreferencesResponse) {
        DevicePermissionsPreference devicePermissionsPreference = getDeviceCommsPreferencesResponse.getDevicePermissionsPreferences().get(0);
        boolean allowed = devicePermissionsPreference.getAllowed();
        String state = devicePermissionsPreference.getState();
        return !allowed || "ON".equals(state) || Constants.STATUS_WHITELISTED.equals(state);
    }

    public static synchronized void setCommunicationsPreference(@NonNull Context context, @NonNull PushNotificationManager pushNotificationManager, boolean z, @NonNull CapabilitiesManager capabilitiesManager) {
        synchronized (GetDeviceCommsPreferences.class) {
            if (Utils.isFireOS() && capabilitiesManager.isFireOSCommsGatingEnabled()) {
                boolean isDeviceCommunicationsEnabled = isDeviceCommunicationsEnabled(context, capabilitiesManager);
                boolean sharedPreferenceDefined = Utils.sharedPreferenceDefined(context, Constants.SHARED_PREF_KEY_COMMS_FIRE_TAB_ENABLED);
                if (sharedPreferenceDefined && z == isDeviceCommunicationsEnabled) {
                    CommsLogger commsLogger = LOG;
                    commsLogger.i("Communications for this device is already: " + z + ". Returning.");
                    return;
                }
                CommsLogger commsLogger2 = LOG;
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("Communications for this device has been turned ");
                outline1.append(String.valueOf(z));
                commsLogger2.i(outline1.toString());
                Utils.writeBooleanPreferenceToSharedPrefs(context, Constants.SHARED_PREF_KEY_COMMS_FIRE_TAB_ENABLED, z);
                if (!sharedPreferenceDefined && z) {
                    return;
                }
                if (sharedPreferenceDefined && z != isDeviceCommunicationsEnabled) {
                    MetricsHelper.recordSingleOccurrenceOperational(z ? MetricKeys.COMMS_ENABLED : MetricKeys.COMMS_DISABLED);
                }
                String str = z ? "Registering " : "Deregistering ";
                CommsLogger commsLogger3 = LOG;
                commsLogger3.i(str + " with SIP");
                LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(z ? Constants.APPLICATION_FOREGROUND_CHECKED_ACTION : Constants.DEVICE_COMMUNICATIONS_OFF));
                GeneratedOutlineSupport.outline4(str, " for push notifications", LOG);
                if (z) {
                    pushNotificationManager.registerForPush();
                } else {
                    pushNotificationManager.deregisterForPushAsynchronously();
                }
            }
        }
    }

    public static void updateCommsPrefForLoggedInUserHelper(@NonNull Context context, @NonNull PushNotificationManager pushNotificationManager, @NonNull CapabilitiesManager capabilitiesManager) {
        ThreadUtils.checkNotMainThread();
        new GetDeviceCommsPreferences().updateCommsPrefForLoggedInDevice(context, pushNotificationManager, capabilitiesManager);
    }

    public boolean getDevicesCommsAnnouncementPreference(@NonNull Context context) {
        try {
            String valueFromDataStore = Utils.getValueFromDataStore(context, "DeviceType");
            String valueFromDataStore2 = Utils.getValueFromDataStore(context, DeviceDataKeys.KEY_DEVICE_SERIAL_NUMBER);
            if (valueFromDataStore != null && valueFromDataStore2 != null) {
                GetDeviceCommsPreferencesResponse devicesCommsPreference = getDevicesCommsPreference(valueFromDataStore, valueFromDataStore2, AppUrl.GET_DEVICE_ANNOUNCEMENT_PREFERENCES);
                if (devicesCommsPreference == null) {
                    LOG.e("Announcement Device Preference couldn't be retrieved.");
                    return false;
                }
                return isPrefEnabled(devicesCommsPreference);
            }
            LOG.e(String.format("DeviceType or deviceId are empty. DeviceId: %s, deviceType: %s. Won't fetch announcement preferences.", valueFromDataStore2, valueFromDataStore));
            return false;
        } catch (DeviceDataStoreException e) {
            CommsLogger commsLogger = LOG;
            commsLogger.e("Exception occurred while trying to retrieve Announcement Device Preference." + e);
            return false;
        }
    }

    @VisibleForTesting
    GetDeviceCommsPreferencesResponse getDevicesCommsPreference(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        ThreadUtils.checkNotMainThread();
        try {
            IHttpClient.Request request = this.client.request(MessageFormat.format(str3, str, str2));
            this.requestId = request.getRequestId();
            IHttpClient.Response mo3640execute = request.authenticated().get().mo3640execute();
            if (mo3640execute != null) {
                return (GetDeviceCommsPreferencesResponse) mo3640execute.convert(GetDeviceCommsPreferencesResponse.class);
            }
            return null;
        } catch (ServiceException e) {
            CommsLogger commsLogger = LOG;
            commsLogger.e("Error retrieving device preferences for url:" + str3 + ". Exception Thrown:" + e);
            return null;
        }
    }

    public String getRequestId() {
        return this.requestId;
    }

    @VisibleForTesting
    void updateCommsPrefForLoggedInDevice(@NonNull Context context, @NonNull PushNotificationManager pushNotificationManager, @NonNull CapabilitiesManager capabilitiesManager) {
        ThreadUtils.checkNotMainThread();
        try {
            if (Utils.isFireOS() && capabilitiesManager.isFireOSCommsGatingEnabled()) {
                String valueFromDataStore = Utils.getValueFromDataStore(context, "DeviceType");
                String valueFromDataStore2 = Utils.getValueFromDataStore(context, DeviceDataKeys.KEY_DEVICE_SERIAL_NUMBER);
                if (valueFromDataStore != null && valueFromDataStore2 != null) {
                    GetDeviceCommsPreferencesResponse devicesCommsPreference = getDevicesCommsPreference(valueFromDataStore, valueFromDataStore2, AppUrl.GET_DEVICE_COMMS_PREFERENCES);
                    if (devicesCommsPreference == null) {
                        LOG.e("Error retrieving device preferences. Won't set comms preferences. ");
                        return;
                    }
                    setCommunicationsPreference(context, pushNotificationManager, isPrefEnabled(devicesCommsPreference), capabilitiesManager);
                    LOG.i("Successfully retrieved communications preference and added to cache.");
                    return;
                }
                LOG.e(String.format("DeviceType or deviceId are empty. DeviceId: %s, deviceType: %s. Won't fetch preferences.", valueFromDataStore2, valueFromDataStore));
                return;
            }
            Utils.writeBooleanPreferenceToSharedPrefs(context, Constants.SHARED_PREF_KEY_COMMS_FIRE_TAB_ENABLED, true);
        } catch (DeviceDataStoreException e) {
            CommsLogger commsLogger = LOG;
            commsLogger.e("Error retrieving device preferences. Won't set preferences. " + e);
        }
    }

    public GetDeviceCommsPreferences(@NonNull ACMSClient aCMSClient) {
        this.client = aCMSClient;
    }
}
