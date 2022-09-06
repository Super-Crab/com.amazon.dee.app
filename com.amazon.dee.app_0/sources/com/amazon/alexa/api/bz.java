package com.amazon.alexa.api;

import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.AlexaMetricsName;
import com.amazon.alexa.api.DialogRequestDeniedReason;
import com.amazon.alexa.api.LeaderSelectionAuthority;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class bz {
    private static final String a = "bz";

    private bz() {
        throw new UnsupportedOperationException();
    }

    private static void a(AlexaServicesConnection alexaServicesConnection, AlexaMetricsName alexaMetricsName, @Nullable String str) {
        a(alexaServicesConnection, alexaMetricsName, str, "");
    }

    private static void a(AlexaServicesConnection alexaServicesConnection, AlexaMetricsName alexaMetricsName, @Nullable String str, String str2) {
        new MetricBroadcastSender(alexaServicesConnection).sendVoiceInteractionEvent(alexaMetricsName, str, null, str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaUserSpeechProvider alexaUserSpeechProvider, @NonNull AlexaDialogRequest alexaDialogRequest) {
        Preconditions.notNull(alexaServicesConnection, "alexaServicesConnection is null");
        Preconditions.notNull(alexaUserSpeechProvider, "alexaUserSpeechProvider is null");
        Preconditions.notNull(alexaDialogRequest, "alexaDialogRequest is null");
        try {
            try {
                try {
                    AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
                    AlexaServicesTools.getMessageSender(alexaServicesConnection).requestDialog(alexaServicesConnection.getClient(), alexaServicesConnection.getUserSpeechProviderMessageReceiver(alexaUserSpeechProvider), alexaDialogRequest.getBundle());
                } catch (IllegalArgumentException e) {
                    Log.e(a, e.getMessage(), e);
                    a(alexaServicesConnection, alexaUserSpeechProvider, alexaDialogRequest.getInvocationType());
                }
            } catch (RemoteException e2) {
                Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e2);
                a(alexaServicesConnection, AlexaMetricsName.VoiceInteraction.Failure.BINDING_ERROR, alexaDialogRequest.getInvocationType());
                alexaUserSpeechProvider.onDialogRequestDenied(new DialogRequestDeniedReason(DialogRequestDeniedReason.Reason.BINDING_ERROR, "Remote exception while attempting to request the dialog", e2));
            } catch (Exception e3) {
                Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e3);
                alexaUserSpeechProvider.onDialogRequestDenied(new DialogRequestDeniedReason(DialogRequestDeniedReason.Reason.INTERNAL_CLIENT_ERROR_MESSAGING, "Internal error while attempting to request a dialog", e3));
            }
        } catch (Throwable th) {
            alexaUserSpeechProvider.onDialogRequestDenied(new DialogRequestDeniedReason(DialogRequestDeniedReason.Reason.UNKNOWN, "Unknown error while attempting to request a dialog"));
            throw th;
        }
    }

    private static void a(AlexaServicesConnection alexaServicesConnection, AlexaUserSpeechProvider alexaUserSpeechProvider, @Nullable String str) {
        DialogRequestDeniedReason.Reason reason;
        String str2;
        LeaderSelectionAuthority.LeaderSelectionFailureReason leaderSelectionFailureReason = alexaServicesConnection.getLeaderSelectionFailureReason();
        if (leaderSelectionFailureReason == null) {
            a(alexaServicesConnection, AlexaMetricsName.VoiceInteraction.Failure.LOCAL_SERVICE_DISCONNECTED, str);
            reason = DialogRequestDeniedReason.Reason.LOCAL_SERVICE_DISCONNECTED;
            str2 = "The android service was disconnected while attempting to request the dialog";
        } else if (leaderSelectionFailureReason.equals(LeaderSelectionAuthority.LeaderSelectionFailureReason.DISABLED)) {
            a(alexaServicesConnection, AlexaMetricsName.VoiceInteraction.Failure.LEADER_DISABLED_ERROR, str);
            reason = DialogRequestDeniedReason.Reason.LEADER_DISABLED_ERROR;
            str2 = "The leader was disabled while attempting to request the dialog";
        } else {
            a(alexaServicesConnection, AlexaMetricsName.VoiceInteraction.Failure.LEADER_SELECTION_ERROR, str);
            reason = DialogRequestDeniedReason.Reason.LEADER_SELECTION_ERROR;
            str2 = "There was an error selecting the leader before attempting to request the dialog";
        }
        alexaUserSpeechProvider.onDialogRequestDenied(new DialogRequestDeniedReason(reason, str2));
    }
}
