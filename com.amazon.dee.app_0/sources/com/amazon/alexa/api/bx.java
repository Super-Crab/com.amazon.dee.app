package com.amazon.alexa.api;

import android.util.Log;
import com.amazon.alexa.api.DialogRequestDeniedReason;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.utils.validation.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class bx {
    private static final String a = "bx";

    private bx() {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaUserSpeechProvider alexaUserSpeechProvider) {
        Preconditions.notNull(alexaServicesConnection, "alexaServicesConnection is null");
        Preconditions.notNull(alexaUserSpeechProvider, "alexaUserSpeechProvider is null");
        MessageReceiver<AlexaUserSpeechProviderMessageType> removeUserSpeechProviderMessageReceiver = alexaServicesConnection.removeUserSpeechProviderMessageReceiver(alexaUserSpeechProvider);
        try {
            AlexaServicesTools.checkAlexaBound(alexaServicesConnection);
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender messageSender = AlexaServicesTools.getMessageSender(alexaServicesConnection);
            if (removeUserSpeechProviderMessageReceiver == null) {
                Log.e(a, "can't deregister user speech provider");
            } else {
                messageSender.deregisterUserSpeechProvider(client, removeUserSpeechProviderMessageReceiver);
            }
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaUserSpeechProvider alexaUserSpeechProvider, @NonNull AlexaUserSpeechProviderMetadata alexaUserSpeechProviderMetadata) {
        Preconditions.notNull(alexaServicesConnection, "alexaServicesConnection is null");
        Preconditions.notNull(alexaUserSpeechProvider, "alexaUserSpeechProvider is null");
        Preconditions.notNull(alexaUserSpeechProviderMetadata, "alexaUserSpeechProviderMetadata is null");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            AlexaServicesTools.getMessageSender(alexaServicesConnection).registerUserSpeechProvider(alexaServicesConnection.getClient(), alexaServicesConnection.getUserSpeechProviderMessageReceiver(alexaUserSpeechProvider), BundleTransformer.getDefaultInstance().toBundle(alexaUserSpeechProviderMetadata));
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
            if (!(alexaUserSpeechProvider instanceof LegacyUserSpeechProvider)) {
                return;
            }
            alexaUserSpeechProvider.onDialogRequestDenied(new DialogRequestDeniedReason(DialogRequestDeniedReason.Reason.UNKNOWN, "Unknown error while attempting to request a dialog", e));
        }
    }
}
