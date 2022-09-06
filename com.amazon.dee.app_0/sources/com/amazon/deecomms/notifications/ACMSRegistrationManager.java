package com.amazon.deecomms.notifications;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.metrics.CommsMetric;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.network.acmsrecipes.DeregisterForPushNotifications;
import com.amazon.deecomms.common.network.acmsrecipes.RegisterForPushNotifications;
import com.amazon.deecomms.common.util.Utils;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.HashMap;
/* loaded from: classes12.dex */
public final class ACMSRegistrationManager {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ACMSRegistrationManager.class);
    @VisibleForTesting
    static final String REGISTERED_WITH_ACMS = ACMSRegistrationManager.class.getPackage().getName() + "_REGISTERED_WITH_ACMS_USING";

    private ACMSRegistrationManager() {
    }

    public static void deregisterForPush(Context context, String str, String str2, String str3) {
        String outline0 = GeneratedOutlineSupport.outline0(str3, ".skip");
        HashMap hashMap = new HashMap();
        if (context == null) {
            LOG.e("Unable to de-register for push notifications, context was null");
            hashMap.put("errorSource", MetricKeys.VALUE_MISSING_CONTEXT);
            MetricsHelper.recordCounterMetric(CommsMetric.MetricType.Operational, GeneratedOutlineSupport.outline0(outline0, ".context"), 1.0d, hashMap);
        } else if (TextUtils.isEmpty(str2)) {
            LOG.e("Unable to de-register for push notifications, clientID was null or empty");
            hashMap.put("errorSource", MetricKeys.VALUE_MISSING_CLIENT_ID);
            MetricsHelper.recordCounterMetric(CommsMetric.MetricType.Operational, GeneratedOutlineSupport.outline0(outline0, ".clientID"), 1.0d, hashMap);
        } else if (TextUtils.isEmpty(str)) {
            LOG.e("Unable to de-register for push notifications, appID was null or empty");
            hashMap.put("errorSource", MetricKeys.VALUE_MISSING_APP_ID);
            MetricsHelper.recordCounterMetric(CommsMetric.MetricType.Operational, GeneratedOutlineSupport.outline0(outline0, ".appID"), 1.0d, hashMap);
        } else {
            String commsId = CommsInternal.getInstance().getCommsId();
            if (TextUtils.isEmpty(commsId)) {
                LOG.e("Cannot de-register for push notifications, commsID was null or empty (User is deprovisioned)");
                hashMap.put("errorSource", MetricKeys.VALUE_MISSING_COMMS_ID);
                MetricsHelper.recordCounterMetric(CommsMetric.MetricType.Operational, GeneratedOutlineSupport.outline0(outline0, ".commsID"), 1.0d, hashMap);
                return;
            }
            try {
                String execute = new DeregisterForPushNotifications(str3).execute(commsId, str2, str);
                Utils.removePreferenceFromSharedPrefs(context, REGISTERED_WITH_ACMS);
                Utils.removePreferenceFromSharedPrefs(context, Constants.ROOT_DIRECTED_ID);
                CommsLogger commsLogger = LOG;
                StringBuilder sb = new StringBuilder();
                sb.append("Successfully deregistered for push notifications, response: ");
                sb.append(execute);
                commsLogger.i(sb.toString());
            } catch (ServiceException e) {
                LOG.e("Error trying to deregister for push notifications", e);
            }
        }
    }

    public static boolean registerForPush(Context context, String str, String str2, @NonNull PushNotificationService pushNotificationService, PushNotificationService pushNotificationService2, String str3, String str4, String str5, @NonNull String str6) {
        Object[] objArr = new Object[7];
        objArr[0] = str;
        objArr[1] = str2;
        objArr[2] = pushNotificationService.toString();
        objArr[3] = pushNotificationService2 == null ? "" : pushNotificationService2.toString();
        objArr[4] = str3;
        objArr[5] = str4;
        objArr[6] = str5;
        String format = String.format("%s_%s_%s_%s_%s_%s_%s", objArr);
        if (!format.equals(Utils.getStringPreferenceFromSharedPrefs(context, REGISTERED_WITH_ACMS, null))) {
            try {
                String execute = new RegisterForPushNotifications(str6).execute(str4, str2, str, pushNotificationService, pushNotificationService2, str5, str3);
                CommsLogger commsLogger = LOG;
                StringBuilder sb = new StringBuilder();
                sb.append("Successfully registered for push notifications, response: ");
                sb.append(execute);
                commsLogger.i(sb.toString());
                Utils.writeStringPreferenceToSharedPrefs(context, REGISTERED_WITH_ACMS, format);
                return true;
            } catch (ServiceException e) {
                LOG.e("Error trying to register for push notifications", e);
                Utils.removePreferenceFromSharedPrefs(context, REGISTERED_WITH_ACMS);
                return false;
            }
        }
        LOG.i("Received register push notification req but already registered for push notifications");
        return true;
    }
}
