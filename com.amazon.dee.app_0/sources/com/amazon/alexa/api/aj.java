package com.amazon.alexa.api;

import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.api.AlexaMetricsName;
import com.amazon.alexa.api.LeaderSelectionAuthority;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class aj {
    private static final String a = "aj";

    private aj() {
        throw new UnsupportedOperationException();
    }

    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
            if (alexaServicesMessageSender == null) {
                return;
            }
            alexaServicesMessageSender.stopRecognizing(client);
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    private static void a(AlexaServicesConnection alexaServicesConnection, AlexaMetricsName alexaMetricsName, @Nullable String str) {
        new MetricBroadcastSender(alexaServicesConnection).sendVoiceInteractionEvent(alexaMetricsName, str);
    }

    private static void a(AlexaServicesConnection alexaServicesConnection, @Nullable String str) {
        LeaderSelectionAuthority.LeaderSelectionFailureReason leaderSelectionFailureReason = alexaServicesConnection.getLeaderSelectionFailureReason();
        a(alexaServicesConnection, leaderSelectionFailureReason != null ? leaderSelectionFailureReason.equals(LeaderSelectionAuthority.LeaderSelectionFailureReason.DISABLED) ? AlexaMetricsName.VoiceInteraction.Failure.LEADER_DISABLED_ERROR : AlexaMetricsName.VoiceInteraction.Failure.LEADER_SELECTION_ERROR : AlexaMetricsName.VoiceInteraction.Failure.LOCAL_SERVICE_DISCONNECTED, str);
    }

    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection, String str, LaunchType launchType) {
        a(alexaServicesConnection, str, launchType, null);
    }

    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection, String str, LaunchType launchType, @Nullable AlexaUserInterfaceOptions alexaUserInterfaceOptions) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        if (!alexaServicesConnection.isConnected()) {
            Log.e(a, String.format(java.util.Locale.US, "Connection object is not connected. Cannot %s", "start"));
            a(alexaServicesConnection, str);
            return;
        }
        try {
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
            if (alexaServicesMessageSender == null) {
                b(alexaServicesConnection, str);
                return;
            }
            AlexaDialogExtras.Builder launchType2 = AlexaDialogExtras.builder().setInvocationType(str).setLaunchType(launchType);
            if (alexaUserInterfaceOptions != null) {
                launchType2.setAlexaUserInterfaceOptions(alexaUserInterfaceOptions);
            }
            alexaServicesMessageSender.startRecognizing(client, launchType2.build().getBundle());
        } catch (RemoteException e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
            b(alexaServicesConnection, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(@NonNull AlexaServicesConnection alexaServicesConnection) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
            if (alexaServicesMessageSender == null) {
                return;
            }
            alexaServicesMessageSender.cancelUserInteraction(client);
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    private static void b(AlexaServicesConnection alexaServicesConnection, @Nullable String str) {
        a(alexaServicesConnection, AlexaMetricsName.VoiceInteraction.Failure.BINDING_ERROR, str);
    }
}
