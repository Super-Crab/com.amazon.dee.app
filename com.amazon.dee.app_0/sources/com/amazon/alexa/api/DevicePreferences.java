package com.amazon.alexa.api;

import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes6.dex */
public class DevicePreferences {
    private static final String TAG = "DevicePreferences";

    private DevicePreferences() {
        throw new UnsupportedOperationException();
    }

    public static AlexaPreferences getPreferences(AlexaAudioProviderConnection alexaAudioProviderConnection) {
        Preconditions.notNull(alexaAudioProviderConnection, "alexaAudioProviderConnection was null");
        if (alexaAudioProviderConnection.isConnected()) {
            try {
                ExtendedClient client = alexaAudioProviderConnection.getClient();
                AlexaAudioProviderManagerMessageSender alexaAudioProviderManagerMessageSender = (AlexaAudioProviderManagerMessageSender) alexaAudioProviderConnection.mo838get();
                if (alexaAudioProviderManagerMessageSender != null) {
                    return new AlexaPreferences(alexaAudioProviderManagerMessageSender.getPreferenceValues(client));
                }
                Log.e(TAG, "Message Sender is null");
            } catch (RemoteException e) {
                Log.e(TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        } else {
            Log.e(TAG, "Connection object is not connected");
        }
        Log.w(TAG, "Returning default preferences instead of actual preferences");
        return AlexaPreferences.builder().build();
    }

    public static boolean updatePreferences(AlexaAudioProviderConnection alexaAudioProviderConnection, AlexaPreferences alexaPreferences) {
        String str;
        String str2;
        Preconditions.notNull(alexaAudioProviderConnection, "alexaAudioProviderConnection was null");
        Preconditions.notNull(alexaPreferences, "preferences was null");
        if (!alexaAudioProviderConnection.isConnected()) {
            Log.e(TAG, "Connection object is not connected");
            return false;
        }
        try {
            ExtendedClient client = alexaAudioProviderConnection.getClient();
            AlexaAudioProviderManagerMessageSender alexaAudioProviderManagerMessageSender = (AlexaAudioProviderManagerMessageSender) alexaAudioProviderConnection.mo838get();
            if (alexaAudioProviderManagerMessageSender == null) {
                str = TAG;
                str2 = "Message Sender is null";
            } else if (alexaAudioProviderManagerMessageSender.updatePreferences(client, alexaPreferences.getBundle())) {
                return true;
            } else {
                str = TAG;
                str2 = "Update preferences did not complete successfully";
            }
            Log.e(str, str2);
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            return false;
        }
    }
}
