package com.amazon.alexa.api;

import android.os.RemoteException;
/* loaded from: classes6.dex */
public class AttentionSystem {
    private AttentionSystem() {
        throw new UnsupportedOperationException();
    }

    public static void deregisterListener(AlexaAudioProviderConnection alexaAudioProviderConnection, AlexaStateListener alexaStateListener) {
        if (alexaAudioProviderConnection == null || alexaStateListener == null) {
            throw new IllegalArgumentException(String.format("The provided AlexaAudioProviderConnection(value=%s) or AlexaStateListener(value=%s) was null.", alexaAudioProviderConnection, alexaStateListener));
        }
        AlexaStateListenerProxy removeListener = alexaAudioProviderConnection.removeListener(alexaStateListener);
        if (removeListener == null || !alexaAudioProviderConnection.isBoundToService()) {
            return;
        }
        try {
            ExtendedClient client = alexaAudioProviderConnection.getClient();
            AlexaAudioProviderManagerMessageSender alexaAudioProviderManagerMessageSender = (AlexaAudioProviderManagerMessageSender) alexaAudioProviderConnection.mo838get();
            if (alexaAudioProviderManagerMessageSender == null) {
                return;
            }
            alexaAudioProviderManagerMessageSender.deregisterAlexaStateListener(client, removeListener);
            alexaAudioProviderConnection.removeListener(alexaStateListener);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public static void registerListener(AlexaAudioProviderConnection alexaAudioProviderConnection, AlexaStateListener alexaStateListener) {
        if (alexaAudioProviderConnection == null || alexaStateListener == null) {
            throw new IllegalArgumentException(String.format("The provided AlexaAudioProviderConnection(value=%s) or AlexaStateListener(value=%s).", alexaAudioProviderConnection, alexaStateListener));
        }
        if (!alexaAudioProviderConnection.isConnected()) {
            return;
        }
        bq bqVar = new bq(alexaStateListener);
        try {
            ExtendedClient client = alexaAudioProviderConnection.getClient();
            AlexaAudioProviderManagerMessageSender alexaAudioProviderManagerMessageSender = (AlexaAudioProviderManagerMessageSender) alexaAudioProviderConnection.mo838get();
            if (alexaAudioProviderManagerMessageSender == null) {
                return;
            }
            alexaAudioProviderManagerMessageSender.registerAlexaStateListener(client, bqVar);
            alexaAudioProviderConnection.addListener(alexaStateListener, bqVar);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
