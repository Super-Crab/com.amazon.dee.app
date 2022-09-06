package com.amazon.alexa.api;

import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes6.dex */
final class ad {
    public static final String a = "ad";

    private ad() {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaCapabilityAgentRegistration alexaCapabilityAgentRegistration) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        if (alexaServicesConnection.isConnected()) {
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
            if (alexaServicesMessageSender == null) {
                return;
            }
            try {
                alexaServicesMessageSender.disableExternalCapabilityAgent(client, alexaCapabilityAgentRegistration);
            } catch (RemoteException e) {
                Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }
    }
}
