package com.amazon.deecomms.calling.util;

import android.annotation.TargetApi;
import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.telecom.TelecomConstants;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.util.DeviceInfo;
import com.android.tools.r8.GeneratedOutlineSupport;
/* loaded from: classes12.dex */
public final class TelecomUtils {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, TelecomUtils.class);

    private TelecomUtils() {
    }

    public static boolean isAudioPickerEnabled() {
        return Utils.getBooleanPreferenceFromSharedPrefs(CommsDaggerWrapper.getComponent().getContext(), TelecomConstants.SHARED_PREF_KEY_TELECOM_SUPPORTED, false);
    }

    @TargetApi(26)
    public static void registerPhoneAccount(@NonNull String str, @NonNull Context context) {
        CommsLogger commsLogger = LOG;
        commsLogger.i("telecom registerPhoneAccount called from: " + str);
        MetricsHelper.recordOperationalMetricWithSource(MetricKeys.REGISTER_PHONE_ACCOUNT, str);
        CommsDaggerWrapper.getComponent().getTelecomManager().registerPhoneAccount(CommsDaggerWrapper.getComponent().getPhoneAccount());
        Utils.writeBooleanPreferenceToSharedPrefs(context, TelecomConstants.SHARED_PREF_KEY_TELECOM_REGISTERED, true);
    }

    public static boolean setAndGetTelecomSupported(@NonNull Context context) {
        boolean z = Utils.isOreoAndAbove() && !Utils.isFireOS() && DeviceInfo.isPhone(context);
        Utils.writeBooleanPreferenceToSharedPrefs(context, TelecomConstants.SHARED_PREF_KEY_TELECOM_SUPPORTED, z);
        GeneratedOutlineSupport.outline5("Is Telecom supported ", z, LOG);
        return z;
    }

    @TargetApi(26)
    public static void unregisterPhoneAccount(@NonNull String str, @NonNull Context context) {
        CommsLogger commsLogger = LOG;
        commsLogger.i("telecom unregisterPhoneAccount called from: " + str);
        MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.DEREGISTER_PHONE_ACCOUNT);
        CommsDaggerWrapper.getComponent().getTelecomManager().unregisterPhoneAccount(CommsDaggerWrapper.getComponent().getPhoneAccountHandle());
        Utils.writeBooleanPreferenceToSharedPrefs(context, TelecomConstants.SHARED_PREF_KEY_TELECOM_REGISTERED, false);
    }
}
