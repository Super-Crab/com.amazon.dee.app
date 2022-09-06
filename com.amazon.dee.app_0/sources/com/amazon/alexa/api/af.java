package com.amazon.alexa.api;

import android.util.Log;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.utils.validation.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class af {
    private static final String a = "af";

    private af() {
        throw new UnsupportedOperationException("don't instantiate");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaCaptionListener alexaCaptionListener) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaCaptionListener, "The provided AlexaCaptionListener was null.");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            AlexaServicesTools.getMessageSender(alexaServicesConnection).registerCaptionListener(alexaServicesConnection.getClient(), alexaServicesConnection.getListener(alexaCaptionListener));
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaCaptionListener alexaCaptionListener) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaCaptionListener, "The provided AlexaCaptionListener was null.");
        MessageReceiver<ApiType_CaptionListenerMessageType> removeListener = alexaServicesConnection.removeListener(alexaCaptionListener);
        try {
            AlexaServicesTools.checkAlexaBound(alexaServicesConnection);
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender messageSender = AlexaServicesTools.getMessageSender(alexaServicesConnection);
            if (removeListener == null) {
                Log.e(a, "can't deregister AlexaReadinessListener");
            } else {
                messageSender.deregisterCaptionListener(client, removeListener);
            }
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }
}
