package com.amazon.alexa.api;

import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes6.dex */
public class AlexaLocale {
    private static final String TAG = "AlexaLocale";

    private AlexaLocale() {
        throw new UnsupportedOperationException();
    }

    public static void deregisterListener(@NonNull AlexaAudioProviderConnection alexaAudioProviderConnection, @NonNull AlexaSettingsListener alexaSettingsListener) {
        String str;
        String str2;
        Preconditions.notNull(alexaAudioProviderConnection, "alexaAudioProviderConnection was null");
        Preconditions.notNull(alexaSettingsListener, "alexaAudioProviderConnection was null");
        if (!alexaAudioProviderConnection.isConnected()) {
            str = TAG;
            str2 = "not connected. return";
        } else {
            ExtendedClient client = alexaAudioProviderConnection.getClient();
            AlexaSettingsListenerProxy removeProxy = alexaAudioProviderConnection.removeProxy(alexaSettingsListener);
            if (removeProxy == null) {
                str = TAG;
                str2 = "proxy is not found";
            } else {
                AlexaAudioProviderManagerMessageSender alexaAudioProviderManagerMessageSender = (AlexaAudioProviderManagerMessageSender) alexaAudioProviderConnection.mo838get();
                if (alexaAudioProviderManagerMessageSender != null) {
                    try {
                        alexaAudioProviderManagerMessageSender.deregisterAlexaSettingsListener(client, removeProxy);
                        return;
                    } catch (RemoteException e) {
                        Log.e(TAG, AlexaServicesTools.MESSAGING_ERROR, e);
                        return;
                    }
                }
                str = TAG;
                str2 = "message sender is null";
            }
        }
        Log.w(str, str2);
    }

    public static java.util.Locale getLocale(@NonNull AlexaAudioProviderConnection alexaAudioProviderConnection) {
        Preconditions.notNull(alexaAudioProviderConnection, "alexaAudioProviderConnection was null");
        ExtendedClient client = alexaAudioProviderConnection.getClient();
        AlexaAudioProviderManagerMessageSender alexaAudioProviderManagerMessageSender = (AlexaAudioProviderManagerMessageSender) alexaAudioProviderConnection.mo838get();
        if (alexaAudioProviderManagerMessageSender != null) {
            try {
                return alexaAudioProviderManagerMessageSender.getLocale(client);
            } catch (RemoteException e) {
                Log.e(TAG, AlexaServicesTools.MESSAGING_ERROR, e);
                return null;
            }
        }
        return null;
    }

    public static void registerListener(@NonNull AlexaAudioProviderConnection alexaAudioProviderConnection, @NonNull AlexaSettingsListener alexaSettingsListener) {
        String str;
        String str2;
        Preconditions.notNull(alexaAudioProviderConnection, "alexaAudioProviderConnection was null");
        Preconditions.notNull(alexaSettingsListener, "alexaAudioProviderConnection was null");
        if (!alexaAudioProviderConnection.isConnected()) {
            str = TAG;
            str2 = "not connected. return";
        } else {
            ExtendedClient client = alexaAudioProviderConnection.getClient();
            AlexaSettingsListenerProxy proxy = alexaAudioProviderConnection.getProxy(alexaSettingsListener);
            AlexaAudioProviderManagerMessageSender alexaAudioProviderManagerMessageSender = (AlexaAudioProviderManagerMessageSender) alexaAudioProviderConnection.mo838get();
            if (alexaAudioProviderManagerMessageSender != null) {
                try {
                    alexaAudioProviderManagerMessageSender.registerAlexaSettingsListener(client, proxy);
                    return;
                } catch (RemoteException e) {
                    Log.e(TAG, AlexaServicesTools.MESSAGING_ERROR, e);
                    return;
                }
            }
            str = TAG;
            str2 = "message sender is null";
        }
        Log.w(str, str2);
    }
}
