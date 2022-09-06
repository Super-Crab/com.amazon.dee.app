package com.amazon.alexa.api;

import android.util.Log;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.utils.validation.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class bj {
    private static final String a = "bj";

    private bj() {
        throw new UnsupportedOperationException("don't instantiate");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static AlexaReadyState a(@NonNull AlexaServicesConnection alexaServicesConnection) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            return AlexaServicesTools.getMessageSender(alexaServicesConnection).getReadyState(alexaServicesConnection.getClient());
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
            return AlexaReadyState.DEFAULT_STATE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaReadinessListener alexaReadinessListener) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaReadinessListener, "The provided AlexaReadinessListener was null.");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            AlexaServicesTools.getMessageSender(alexaServicesConnection).registerReadinessListener(alexaServicesConnection.getClient(), alexaServicesConnection.getListener(alexaReadinessListener));
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaReadinessListener alexaReadinessListener) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaReadinessListener, "The provided AlexaReadinessListener was null.");
        MessageReceiver<AlexaReadinessMessageType> removeListener = alexaServicesConnection.removeListener(alexaReadinessListener);
        try {
            AlexaServicesTools.checkAlexaBound(alexaServicesConnection);
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender messageSender = AlexaServicesTools.getMessageSender(alexaServicesConnection);
            if (removeListener == null) {
                Log.e(a, "can't deregister AlexaReadinessListener");
            } else {
                messageSender.deregisterReadinessListener(client, removeListener);
            }
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }
}
