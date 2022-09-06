package com.amazon.alexa.api;

import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes6.dex */
class a {
    private static final String a = "a";

    private a() {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(@NonNull AlexaAudioProviderConnection alexaAudioProviderConnection) {
        Preconditions.notNull(alexaAudioProviderConnection, "The provided AlexaAudioProviderConnection was null.");
        if (!alexaAudioProviderConnection.isBoundToService()) {
            Log.e(a, "Connection is not bound to service");
            return false;
        }
        try {
            ExtendedClient client = alexaAudioProviderConnection.getClient();
            AlexaAudioProviderManagerMessageSender alexaAudioProviderManagerMessageSender = (AlexaAudioProviderManagerMessageSender) alexaAudioProviderConnection.mo838get();
            if (alexaAudioProviderManagerMessageSender == null) {
                return false;
            }
            return alexaAudioProviderManagerMessageSender.isUserLoggedIn(client);
        } catch (RemoteException e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
            return false;
        }
    }
}
