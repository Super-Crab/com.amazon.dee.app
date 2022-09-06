package com.amazon.deecomms.common.util;

import android.app.Activity;
import android.content.DialogInterface;
import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AlertDialog;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.messaging.provider.MessagingProviderWrapper;
import com.amazon.deecomms.remoteConfig.RemoteConfigKeys;
import com.amazon.deecomms.settings.IdentityPreferencesProvider;
import com.amazon.deecomms.util.SharedPreferencesUtils;
import com.google.common.base.Supplier;
import java.util.Arrays;
/* loaded from: classes12.dex */
public final class VoiceMessageTranscriptionConsent {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, VoiceMessageTranscriptionConsent.class);

    private VoiceMessageTranscriptionConsent() {
    }

    @Nullable
    public static Boolean getACMSUserPref() throws ServiceException {
        try {
            String str = new IdentityPreferencesProvider(IdentityPreferencesProvider.AuthenticationType.AS_COMMS_USER).get(CommsDaggerWrapper.getComponent().getCommsIdentityManager().getCommsId("getACMSUserPref", false), Constants.IDENTITY_PREFERENCE_VOICE_MSG_CONSENT);
            if (str == null) {
                return null;
            }
            return Boolean.valueOf(str);
        } catch (ServiceException e) {
            LOG.e("Unable to get toggle value for voice message transcription consent", e);
            throw e;
        }
    }

    @VisibleForTesting
    static boolean hasRecentAudioMsg(@NonNull Activity activity, @NonNull String str, @NonNull String str2, @Nullable Cursor cursor) {
        MessagingProviderWrapper messagingProviderWrapper = new MessagingProviderWrapper(activity, str, str2);
        if (cursor != null) {
            do {
                try {
                    if (!cursor.moveToNext()) {
                        return false;
                    }
                } finally {
                    cursor.close();
                }
            } while (!"message/audio".equalsIgnoreCase(messagingProviderWrapper.getMessageType(cursor.getString(cursor.getColumnIndex("conversation_id")), Long.parseLong(cursor.getString(cursor.getColumnIndex("message_id"))))));
            cursor.close();
            return true;
        }
        return false;
    }

    public static boolean pfmRequiresAccess() {
        String configString = CommsDaggerWrapper.getComponent().getArcusConfig().getConfigString(RemoteConfigKeys.ARCUS_VOICE_MSG_TRANSCRIPTION_PFM);
        return configString == null || Arrays.asList(configString.split("\\W")).contains(CommsDaggerWrapper.getComponent().getCommsIdentityManager().getPreferredMarketplace(false));
    }

    @Nullable
    public static Boolean setACMSUserPref(boolean z) {
        try {
            new IdentityPreferencesProvider(IdentityPreferencesProvider.AuthenticationType.AS_COMMS_USER).set(CommsDaggerWrapper.getComponent().getCommsIdentityManager().getCommsId("setACMSUserPref", false), Constants.IDENTITY_PREFERENCE_VOICE_MSG_CONSENT, Boolean.valueOf(z));
            return Boolean.valueOf(z);
        } catch (ServiceException e) {
            LOG.e("Unable to update preference for voice message transcription consent", e);
            return null;
        }
    }

    public static void setSharedPref(boolean z, @NonNull Activity activity) {
        if (z != ((Boolean) SharedPreferencesUtils.getCacheValue(activity, Constants.SHARED_PREF_VOICE_MSG_CONSENT, false)).booleanValue()) {
            SharedPreferencesUtils.persistCacheValues(activity, Constants.SHARED_PREF_VOICE_MSG_CONSENT, Boolean.valueOf(z));
        }
    }

    public static void showAlertErrorDialog(@NonNull Activity activity) {
        if (!activity.isFinishing()) {
            new AlertDialog.Builder(activity).setTitle(activity.getString(R.string.voice_msg_consent)).setMessage(activity.getString(R.string.generic_error_msg)).setPositiveButton(activity.getString(R.string.dialog_ok_button), new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.common.util.VoiceMessageTranscriptionConsent.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).setCancelable(true).create().show();
        }
    }

    public static boolean showConsentPopup(@NonNull Activity activity, @NonNull String str, @NonNull String str2, @NonNull Supplier<Cursor> supplier) {
        if (!pfmRequiresAccess() || !hasRecentAudioMsg(activity, str, str2, supplier.mo8291get())) {
            return false;
        }
        try {
            return getACMSUserPref() == null;
        } catch (ServiceException e) {
            LOG.e("Error while fetching ACMS user pref for Voice Message Transcription Consent", e);
            return false;
        }
    }
}
